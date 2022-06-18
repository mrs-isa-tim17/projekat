package com.project.mrsisa.service;

import com.project.mrsisa.converter.LocalDateTimeToString;
import com.project.mrsisa.domain.*;
import com.project.mrsisa.dto.ReserveEntityDTO;
import com.project.mrsisa.dto.client.SuccessOfCancelReservationDTO;
import com.project.mrsisa.exception.AlreadyCanceled;
import com.project.mrsisa.exception.NotAvailable;
import com.project.mrsisa.exception.NotDefinedValue;
import com.project.mrsisa.exception.TooHighPenaltyNumber;
import com.project.mrsisa.repository.ReservationRepository;

import lombok.SneakyThrows;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {
    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    ClientService clientService;

    @Autowired
    CottageService cottageService;

    @Autowired
    ShipService shipService;

    @Autowired
    AdventureService adventureService;

    @Autowired
    AdditionalServicesService additionalServicesService;

    @Autowired
    PricelistService pricelistService;

    @Autowired
    PeriodAvailabilitySerivce periodAvailabilitySerivce;

    @Autowired
    PeriodUnavailabilityService periodUnavailabilityService;

    @Autowired
    CancelConditionService cancelConditionService;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Environment env;

    @Autowired
    private ImageService imageService;

    public List<Reservation> getCottageHistoryReservation(Long id){
        return reservationRepository.findCottageReservationHistory(id, OfferType.COTTAGE.getValue());
    }

    public List<Reservation> getAdventureHistoryReservation(Long id){
        return reservationRepository.findCottageReservationHistory(id, OfferType.ADVENTURE.getValue());
    }

    public List<Reservation> getShipHistoryReservation(Long id){
        return reservationRepository.findCottageReservationHistory(id, OfferType.SHIP.getValue());
    }

    public Reservation getReservationById(Long id){
        return reservationRepository.findById(id).orElse(null);
    }

    public Reservation save(Reservation r){
        System.out.println(r.toString());
        return reservationRepository.save(r);
    }

    public List<Reservation> getPastHistoryReservation(Long offer_id){
        return reservationRepository.findPastReservationHistory(offer_id);
    }

    public List<Reservation> getFutureHistoryReservation(Long offer_id){
        return reservationRepository.findFutureReservationHistory(offer_id);
    }

    public List<Reservation> getListOfReservationByOfferInInterval(long offerId, LocalDateTime fromDate, LocalDateTime untilDate){
        return reservationRepository.findCurrentReservationInInterval(offerId, fromDate, untilDate);
    }

	public List<Reservation> getAllReservationsForOffer(Long id) {
		return reservationRepository.findAllReservationsForOffer(id);
	}

    public List<Reservation> getCurrentReservationsForOffer(Long id) {
        return reservationRepository.findCurrentReservationsForOffer(id);
    }

    public List<Reservation> getReservationsForMonthAndYear(Long id,int month,int year){
        return reservationRepository.getAllReservationsForMonthAndYear(id,month,year);
    }

    public List<Reservation> getReservationForMonth(Long id){
        return reservationRepository.getAllReservationsForMonth(id);
    }

    public List<Reservation> getReservationForWeek(Long id){
        return reservationRepository.getAllReservationsForWeek(id);
    }

    public List<Reservation> getReservationForPeriod(Long id, String start,String end){
        System.out.println("id"+id+"datttt"+start);
        return reservationRepository.getAllReservationsForPeriod(id,start,end);
    }

	public boolean haveFutureReservations(Long offerId) {
		List<Reservation> futureReservations = reservationRepository.findFutureReservationHistory(offerId);
		if(futureReservations.size()==0) {
			return false;
		}
		return true;
	}

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Reservation makeReservation(ReserveEntityDTO reserveEntityDTO) throws AlreadyCanceled, NotDefinedValue, NotAvailable, MessagingException, MailSendException, TooHighPenaltyNumber {
        Reservation r = new Reservation();
        if (reserveEntityDTO.getClientId() == -1){
            r.setClient(null);
        }else{
            r.setClient(clientService.findOne(reserveEntityDTO.getClientId()));
            if (r.getClient().getPenaltyNumber() >= 3)
                throw new TooHighPenaltyNumber("Zbog broja penala ste onemogućeni da rezervišete.");
            if (checkIfCanceledReservationWithSameParametars(r.getClient(), reserveEntityDTO))
                throw new AlreadyCanceled("Već je otkazao rezervaciju sa istim parametrima");
        }
        reserveEntity(reserveEntityDTO, r);
        return r;
        //if (r.getClient() != null)
        //    sendMailAboutReservation(r.getClient(), r);
    }

//throws NotDefinedValue, NotAvailable, MailSendException
    @SneakyThrows
    public void reserveEntity(ReserveEntityDTO reserveEntityDTO, Reservation r) {
        Offer o;
        //Reservation r = new Reservation();

        try {
            if (reserveEntityDTO.getOfferType().equals("cottage")) {
                o = cottageService.findOneTryOccupation(reserveEntityDTO.getOfferId());
                r.setOfferType(OfferType.COTTAGE);
            } else if (reserveEntityDTO.getOfferType().equals("ship")) {
                try {
                    o = shipService.findOneTryOccupation(reserveEntityDTO.getOfferId());
                } catch (Exception e) {
                    o = null;
                    e.printStackTrace();
                }
                r.setOfferType(OfferType.SHIP);
            } else if (reserveEntityDTO.getOfferType().equals("adventure")) {
                o = adventureService.findOneTryOccupation(reserveEntityDTO.getOfferId());
                r.setOfferType(OfferType.ADVENTURE);
            } else {
                throw new NotDefinedValue("Dobijeni tip entiteta nije validan");
            }
        }catch (Exception e){
            throw new NotDefinedValue("Entitet nije definisano");
        }
        OfferService offerService = new OfferService();
        o.setReservations(getListOfReservationByOfferInInterval(o.getId(), reserveEntityDTO.getFromDate(), reserveEntityDTO.getUntilDate()));
        o.setPeriodAvailabilities(periodAvailabilitySerivce.getListOfAvailability(o.getId(), reserveEntityDTO.getFromDate(), reserveEntityDTO.getUntilDate()));
        o.setPeriodUnavailabilities(periodUnavailabilityService.getListOfUnavailability(o.getId(), reserveEntityDTO.getFromDate(), reserveEntityDTO.getUntilDate()));
        boolean res = offerService.isGloballyFree(o, reserveEntityDTO.getFromDate(), reserveEntityDTO.getUntilDate());
        if (!res){
            throw new NotAvailable("Neko je stigao pre");
        }
        List<AdditionalServices> additionalServices = formAdditionalServices(reserveEntityDTO.getChosenAdditionalServices());
        r.setStartDateTime(reserveEntityDTO.getFromDate());
        r.setEndDateTime(reserveEntityDTO.getUntilDate());
        r.setAdditionalServices(additionalServices);
        double price = countPriceOfReservation(r.getOfferType(), o, r.getStartDateTime(), r.getEndDateTime(), additionalServices);
        if (price == 0)
            throw new NotDefinedValue("Izračunavanje cene je neuspešno");
        r.setPrice(price);
        r.setQuick(false);
        r.setCanceled(false);
        //r.setClient(client);
        r.setOffer(o);
        r.setShipOwnerPresent(reserveEntityDTO.isShipOwnerPresent());//!!!!!!!!!!!!
        r.setReviewed(false);
        reservationRepository.save(r);
    }

    public void sendMailAboutReservation(Client client, Reservation r) throws MessagingException {
        String subject = "Potvrda o rezervaciji";
        LocalDateTimeToString dateTimeFormatter = new LocalDateTimeToString();
        String mailContent = "<p>Uspešno se rezervisali entitet: " + r.getOffer().getName() +
                ", u periodu od <strong> " + dateTimeFormatter.format(r.getStartDateTime()) + "</strong> do: <strong>" + dateTimeFormatter.format(r.getEndDateTime()) + "</strong>.</p>";

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        helper.setText(mailContent, true);
        helper.setTo(client.getEmail());
        helper.setSubject(subject);
        helper.setFrom(env.getProperty("spring.mail.username"));
        javaMailSender.send(mimeMessage);
    }


    private boolean checkIfCanceledReservationWithSameParametars(Client client, ReserveEntityDTO reserveEntityDTO) {
        Reservation r = reservationRepository.checkIfClientCanceledReservationWithSameParametars(client.getId(), reserveEntityDTO.getOfferId(), reserveEntityDTO.getFromDate(), reserveEntityDTO.getUntilDate());
        if (r == null)
            return false;
        else
            return true;
    }

    private double countPriceOfReservation(OfferType offerType, Offer o, LocalDateTime startDateTime, LocalDateTime endDateTime, List<AdditionalServices> additionalServices) {
        double price = 0;
        try {
            if (offerType == OfferType.COTTAGE) {
                long days = ChronoUnit.DAYS.between(startDateTime.toLocalDate(), endDateTime.toLocalDate());//req.getUntilDate() - req.getFromDate();
                price = days * pricelistService.getCurrentPriceOfOffer(o.getId());
            } else if (offerType == OfferType.ADVENTURE) {
                long minutes = ChronoUnit.MINUTES.between(startDateTime, endDateTime);//req.getUntilDate() - req.getFromDate();
                price = minutes / 60.0 * pricelistService.getCurrentPriceOfOffer(o.getId());
            } else if (offerType == OfferType.SHIP) {
                long minutes = ChronoUnit.MINUTES.between(startDateTime, startDateTime);//req.getUntilDate() - req.getFromDate();
                price = minutes / 60.0 * pricelistService.getCurrentPriceOfOffer(o.getId());
            }
            price = addAdditionalServicesToPrice(price, additionalServices);
            return price;
        }catch (Exception e){
            return 0;
        }
    }

    public double addAdditionalServicesToPrice(double price, List<AdditionalServices> additionalServices) {
        for (AdditionalServices a : additionalServices){
            price += a.getPrice();
        }
        return price;
    }

    private List<AdditionalServices> formAdditionalServices(List<String> chosenAdditionalServices) {
        List<AdditionalServices> services = new ArrayList<>();
        for (String addString : chosenAdditionalServices){
            AdditionalServices add = additionalServicesService.findOneByName(addString);
            services.add(add);
        }
        return services;
    }

    public List<Reservation> getReservationsForShipInPeriodWhenShipOwnerIsPresent(Ship s, LocalDateTime fromDate, LocalDateTime untilDate){
        return reservationRepository.getReservationsForShipInPeriodWhenShipOwnerIsPresent(s.getId(), fromDate, untilDate);
    }

    @Transactional
    public List<Reservation> getUpcomingReservationsForClient(long id) {
        List<Reservation> res = reservationRepository.getUpcomingReservationsForClient(id);
        for(Reservation r : res){
            if (r.getOfferType() == OfferType.COTTAGE){
                Cottage c = cottageService.findOne(r.getOffer().getId());
                r.setOffer(c);
            } else if (r.getOfferType() == OfferType.SHIP){
                r.setOffer(shipService.findOne(r.getOffer().getId()));
            }else if (r.getOfferType() == OfferType.ADVENTURE){
                r.setOffer(adventureService.findOneById(r.getOffer().getId()));
            }
            r.getOffer().setImages(imageService.findAllByOfferId(r.getOffer().getId()));
        }
        return res;
    }

    @Transactional
    public SuccessOfCancelReservationDTO cancelReservation(long id) throws NotDefinedValue {
        Reservation r = reservationRepository.findById(id).orElse(null);
        if (r == null)
            throw new NotDefinedValue("Rezervacija ne postoji");
/*
        if (r.getOfferType() == OfferType.COTTAGE){
            r.setOffer(cottageService.findOne(r.getOffer().getId()));
        } else if (r.getOfferType() == OfferType.SHIP){
            r.setOffer(shipService.findOne(r.getOffer().getId()));
        }else if (r.getOfferType() == OfferType.ADVENTURE){
            r.setOffer(adventureService.findOneById(r.getOffer().getId()));
        }
*/
        List<CancelCondition> cancelConditions = cancelConditionService.findAllByOfferIdOrderdByDays(r.getOffer().getId());

        long numberOfDaysUntilStart = ChronoUnit.DAYS.between(LocalDateTime.now(), r.getStartDateTime());//LocalDateTime.now()

        CancelCondition chosenCancelCondition = null;
        for(CancelCondition cc : cancelConditions){
            if (cc.getDays() > numberOfDaysUntilStart){
                chosenCancelCondition = cc;
                break;
            }
        }

        SuccessOfCancelReservationDTO res = new SuccessOfCancelReservationDTO();
        res.setSuccessful(true);
        r.setCanceled(true);
        reservationRepository.save(r);
        if (chosenCancelCondition == null){
            res.setTaken(0);
        }else{
            //count taken
            double precent = chosenCancelCondition.getPrecent();
            if (precent == 0)
                res.setTaken(0);
            else{
                double taken = r.getPrice() * (precent / 100);
                res.setTaken(taken);
            }
        }

        return res;
    }

    public void setReservationRepository(ReservationRepository reservationRepository){
        this.reservationRepository = reservationRepository;
    }

    public Reservation findOneById(Long id){
        return reservationRepository.findById(id).orElseGet(null);
    }

    public void setCottageService(CottageService cottageService) {
        this.cottageService = cottageService;
    }

    public PeriodAvailabilitySerivce getPeriodAvailabilitySerivce() {
        return periodAvailabilitySerivce;
    }

    public void setPeriodAvailabilitySerivce(PeriodAvailabilitySerivce periodAvailabilitySerivce) {
        this.periodAvailabilitySerivce = periodAvailabilitySerivce;
    }

    public void setPeriodUnavailabilityService(PeriodUnavailabilityService periodUnavailabilityService) {
        this.periodUnavailabilityService = periodUnavailabilityService;
    }

    public void setPricelistService(PricelistService pricelistService) {
        this.pricelistService = pricelistService;
    }
    public List<Reservation> getReservationsForPeriod(LocalDate startDate, LocalDate endDate){
    	return reservationRepository.getReservationsForPeriod(startDate, endDate);
    }
    
    public List<Reservation>findAllQuickReservationsForOffer(Long id){
    	return reservationRepository.findAllQuickReservationsForOffer(id);
    }
    
    public List<Reservation> findAllOrdinaryReservationsForOffer(Long id){
    	return reservationRepository.findAllOrdinaryReservationsForOffer(id);
    }
}
