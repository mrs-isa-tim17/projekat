package com.project.mrsisa.processing;

import com.project.mrsisa.domain.*;
import com.project.mrsisa.dto.simple_user.CottageForListViewDTO;
import com.project.mrsisa.dto.simple_user.ShipForListViewDTO;

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
            if (checkWhetherBelongsToGroup(c.getPrice(), price, priceRelOp))
                result.add(c);
        }
        return result;
    }

    public List<Ship> filterBySpeed(List<Ship> ships, double speed, String speedRelOp) {
        if (speed == 0)
            return ships;
        if (speedRelOp.equals(""))
            return ships;
        List<Ship> result = new ArrayList<Ship>();
        for (Ship c : ships){
            if (checkWhetherBelongsToGroup(c.getMaxSpeed(), speed, speedRelOp))
                result.add(c);
        }
        return result;
    }

    public List<Ship> filterByCapacity(List<Ship> ships, double capacity, String capacityRelOp) {
        if (capacity == 0)
            return ships;
        if (capacityRelOp.equals(""))
            return ships;
        List<Ship> result = new ArrayList<Ship>();
        for (Ship c : ships){
            if (checkWhetherBelongsToGroup(c.getCapacity(), capacity, capacityRelOp))
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
    public List<Ship> filterByShipLocation(List<Ship> ships, double longitude, double latitude) {
        if (longitude == 0)
            return ships;
        if (latitude == 0)
            return ships;
        List<Ship> result = new ArrayList<Ship>();
        for (Ship c : ships){
            if (checkWhetherBelongsToGroupByDistance(c, longitude, latitude))
                result.add(c);
        }
        return result;
    }


    private boolean checkWhetherBelongsToGroupByDistance(Offer c, double longitude, double latitude){
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

    public List<Ship> filterShipByInterval(List<Ship> ships, LocalDateTime fromDate, LocalDateTime untilDate) {
        List<Ship> result = new ArrayList<Ship>();
        for (Ship c : ships){
            if (isGloballyFree(c, fromDate, untilDate)){
                result.add(c);
            }
        }
        return result;
    }

    public boolean isGloballyFree(Offer c, LocalDateTime fromDate, LocalDateTime untilDate){
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

    private boolean isDefinedReservation(Offer c, LocalDateTime fromDate, LocalDateTime untilDate) {
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

    private boolean isDefinedUnvailabilePeriod(Offer c, LocalDateTime fromDate, LocalDateTime untilDate) {
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

    private boolean isDefinedAvailabilePeriod(Offer c, LocalDateTime fromDate, LocalDateTime untilDate) {
        for (PeriodAvailability pa : c.getPeriodAvailabilities()){
            //before - less than zero				after - more than zero
            if (pa.getStartDate().toLocalDate().compareTo(fromDate.toLocalDate()) <= 0)
                if (pa.getEndDate().toLocalDate().compareTo(untilDate.toLocalDate()) >= 0) {
                    return true;
                }
        }
        return false;
    }

    public List<ShipForListViewDTO> filterShipsByRating(List<ShipForListViewDTO> ships, int rating, String ratingRelOp) {
        if (rating == 0)
            return ships;
        if (ratingRelOp.equals(""))
            return ships;
        List<ShipForListViewDTO> result = new ArrayList<ShipForListViewDTO>();
        for (ShipForListViewDTO c : ships){
            if (checkWhetherBelongsToGroup(c.getMark(), rating, ratingRelOp))
                result.add(c);
        }
        return result;
    }

    public List<ShipForListViewDTO> filterShipsByPrice(List<ShipForListViewDTO> cottagesDTO, double price, String priceRelOp) {
        if (price == 0)
            return cottagesDTO;
        if (priceRelOp.equals(""))
            return cottagesDTO;
        List<ShipForListViewDTO> result = new ArrayList<ShipForListViewDTO>();
        for (ShipForListViewDTO c : cottagesDTO){
            if (checkWhetherBelongsToGroup(c.getPrice(), price, priceRelOp))
                result.add(c);
        }
        return result;
    }
}
