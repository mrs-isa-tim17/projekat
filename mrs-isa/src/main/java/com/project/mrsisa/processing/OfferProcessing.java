package com.project.mrsisa.processing;

import com.project.mrsisa.domain.Cottage;
import com.project.mrsisa.domain.PeriodAvailability;
import com.project.mrsisa.domain.PeriodUnavailability;
import com.project.mrsisa.domain.Reservation;
import com.project.mrsisa.dto.simple_user.CottageForListViewDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OfferProcessing {

    public List<CottageForListViewDTO> filterByRating(List<CottageForListViewDTO> cottages, int rating, String ratingRelOp) {
        if (rating == 0)
            return cottages;
        if (ratingRelOp.equals(""))
            return cottages;
        List<CottageForListViewDTO> result = new ArrayList<CottageForListViewDTO>();
        for (CottageForListViewDTO c : cottages){
            if (checkWhetherBelongsToGroup(c.getMark(), rating, ratingRelOp))
                result.add(c);
        }
        return result;
    }

    private boolean checkWhetherBelongsToGroup(double value1, double value2, String ratingRelOp) {
        switch (ratingRelOp){
            case "==":
                return value1 == value2;
            case "<=":
                return value1 <= value2;
            case ">=":
                return value1 >= value2;
            case ">":
                return value1 > value2;
            case "<":
                return value1 < value2;
        }
        return false;
    }

    public List<Cottage> filterByBedQuntity(List<Cottage> cottages, double numberOfBeds, String bedsRelOp) {
        if (numberOfBeds == 0)
            return cottages;
        if (bedsRelOp.equals(""))
            return cottages;
        List<Cottage> result = new ArrayList<Cottage>();
        for (Cottage o : cottages){
            if (checkWhetherBelongsToGroup(o.getBedQuantity(), numberOfBeds, bedsRelOp))
                result.add(o);
        }
        return result;
    }

    public List<Cottage> filterByRoomQuntity(List<Cottage> cottages, double numberOfRooms, String roomsRelOp) {
        if (numberOfRooms == 0)
            return cottages;
        if (roomsRelOp.equals(""))
            return cottages;
        List<Cottage> result = new ArrayList<Cottage>();
        for (Cottage o : cottages){
            if (checkWhetherBelongsToGroup(o.getRoomQuantity(), numberOfRooms, roomsRelOp))
                result.add(o);
        }
        return result;
    }

    public List<CottageForListViewDTO> filterByPrice(List<CottageForListViewDTO> cottagesDTO, double price, String priceRelOp) {
        if (price == 0)
            return cottagesDTO;
        if (priceRelOp.equals(""))
            return cottagesDTO;
        List<CottageForListViewDTO> result = new ArrayList<CottageForListViewDTO>();
        for (CottageForListViewDTO c : cottagesDTO){
            if (checkWhetherBelongsToGroup(c.getMark(), price, priceRelOp))
                result.add(c);
        }
        return result;
    }

    public List<Cottage> filterByLocation(List<Cottage> cottages, double longitude, double latitude) {
        if (longitude == 0)
            return cottages;
        if (latitude == 0)
            return cottages;
        List<Cottage> result = new ArrayList<Cottage>();
        for (Cottage c : cottages){
            if (checkWhetherBelongsToGroupByDistance(c, longitude, latitude))
                result.add(c);
        }
        return result;
    }

    private boolean checkWhetherBelongsToGroupByDistance(Cottage c, double longitude, double latitude){
        if (countEuclideanDistance(c.getAddress().getLongitude(), c.getAddress().getLatitude(), longitude, latitude) < 1)
            return true;
        return false;
    }

    private double countEuclideanDistance(double lon1, double lat1, double lon2, double lat2){
        return Math.sqrt(Math.pow((lon1 - lon2), 2) + Math.pow((lat1 - lat2), 2));
    }

    public List<Cottage> filterByInterval(List<Cottage> cottages, LocalDateTime fromDate, LocalDateTime untilDate) {
        List<Cottage> result = new ArrayList<Cottage>();
        for (Cottage c : cottages){
            if (isGloballyFree(c, fromDate, untilDate)){
                result.add(c);
            }
        }
        return result;
    }

    public boolean isGloballyFree(Cottage c, LocalDateTime fromDate, LocalDateTime untilDate){
        if (c.getPeriodAvailabilities() == null)
            return false;
        if (!isDefinedAvailabilePeriod(c, fromDate, untilDate))
            return false;
        if (c.getPeriodUnavailabilities() == null || isDefinedUnvailabilePeriod(c, fromDate, untilDate))
            return false;
        if (c.getReservations() == null || isDefinedReservation(c, fromDate, untilDate))
            return false;
        return true;
    }

    private boolean isDefinedReservation(Cottage c, LocalDateTime fromDate, LocalDateTime untilDate) {
        for (Reservation pa : c.getReservations()){
            //before - less than zero				after - more than zero
            if (pa.getStartDate().compareTo(fromDate.toLocalDate()) > 0)
                if (pa.getEndDate().compareTo(untilDate.toLocalDate()) > 0)
                    return true;

            if (pa.getStartDate().compareTo(fromDate.toLocalDate()) < 0)
                if (pa.getEndDate().compareTo(untilDate.toLocalDate()) < 0)
                    return true;

            if (pa.getStartDate().compareTo(fromDate.toLocalDate()) < 0)
                if (pa.getEndDate().compareTo(untilDate.toLocalDate()) > 0)
                    return true;

            if (pa.getStartDate().compareTo(fromDate.toLocalDate()) > 0)
                if (pa.getEndDate().compareTo(untilDate.toLocalDate()) < 0)
                    return true;
        }
        return false;
    }

    private boolean isDefinedUnvailabilePeriod(Cottage c, LocalDateTime fromDate, LocalDateTime untilDate) {
        for (PeriodUnavailability pa : c.getPeriodUnavailabilities()){
            //before - less than zero				after - more than zero
            // un: od - do
            // search od - do
            if (pa.getStartDate().toLocalDate().compareTo(fromDate.toLocalDate()) > 0)
                if (pa.getEndDate().toLocalDate().compareTo(untilDate.toLocalDate()) > 0)
                    return true;

            if (pa.getStartDate().toLocalDate().compareTo(fromDate.toLocalDate()) < 0)
                if (pa.getEndDate().toLocalDate().compareTo(untilDate.toLocalDate()) < 0)
                    return true;

            if (pa.getStartDate().toLocalDate().compareTo(fromDate.toLocalDate()) < 0)
                if (pa.getEndDate().toLocalDate().compareTo(untilDate.toLocalDate()) > 0)
                    return true;

            if (pa.getStartDate().toLocalDate().compareTo(fromDate.toLocalDate()) > 0)
                if (pa.getEndDate().toLocalDate().compareTo(untilDate.toLocalDate()) < 0)
                    return true;

        }
        return false;
    }

    private boolean isDefinedAvailabilePeriod(Cottage c, LocalDateTime fromDate, LocalDateTime untilDate) {
        for (PeriodAvailability pa : c.getPeriodAvailabilities()){
            //before - less than zero				after - more than zero
            if (pa.getStartDate().toLocalDate().compareTo(fromDate.toLocalDate()) <= 0)
                if (pa.getEndDate().toLocalDate().compareTo(untilDate.toLocalDate()) >= 0) {
                    System.out.println("TRUEEEEEE AVA");

                    return true;
                }
        }
        return false;
    }
}
