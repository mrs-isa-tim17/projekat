import axios from 'axios';
import authHeader from "@/servieces/authHeader";

import Config from "../config.json";

const RESERVATION_API_BASE_URL = Config.BASE_URL + '/offer';

class ReservationServce{

    getPastCottageReservations(clientID, fromElement, numberOfElementsForDisplay){
        let pagObj = this.formPaginationObj(fromElement, numberOfElementsForDisplay);
        return axios.post(RESERVATION_API_BASE_URL + "/cottage/history/" + clientID,  pagObj,{
            headers : authHeader()
        });
    }
    getPastShipReservations(clientID, fromElement, numberOfElementsForDisplay){
        let pagObj = this.formPaginationObj(fromElement, numberOfElementsForDisplay);
        return axios.post(RESERVATION_API_BASE_URL + "/ship/history/" + clientID, pagObj, {
            headers : authHeader()
        });
    }
    getPastAdventureReservations(clientID, fromElement, numberOfElementsForDisplay){
        let pagObj = this.formPaginationObj(fromElement, numberOfElementsForDisplay);
        return axios.post(RESERVATION_API_BASE_URL + "/adventure/history/" + clientID, pagObj, {
            headers : authHeader()
        });
    }


    getSortedPastAdventureReservationsByName(clientID, fromElement, numberOfElementsForDisplay){
        let pagObj = this.formPaginationObj(fromElement, numberOfElementsForDisplay);
        return axios.post(RESERVATION_API_BASE_URL + "/adventure/history/name/" + clientID, pagObj, {
            headers : authHeader()
        });
    }
    getSortedPastAdventureReservationsByDate(clientID, fromElement, numberOfElementsForDisplay){
        let pagObj = this.formPaginationObj(fromElement, numberOfElementsForDisplay);
        return axios.post(RESERVATION_API_BASE_URL + "/adventure/history/date/" + clientID, pagObj, {
            headers : authHeader()
        });
    }
    getSortedPastAdventureReservationsByPrice(clientID, fromElement, numberOfElementsForDisplay){
        let pagObj = this.formPaginationObj(fromElement, numberOfElementsForDisplay);
        return axios.post(RESERVATION_API_BASE_URL + "/adventure/history/price/" + clientID, pagObj, {
            headers : authHeader()
        });
    }
    getSortedPastAdventureReservationsByDuration(clientID, fromElement, numberOfElementsForDisplay){
        let pagObj = this.formPaginationObj(fromElement, numberOfElementsForDisplay);
        return axios.post(RESERVATION_API_BASE_URL + "/adventure/history/duration/" + clientID, pagObj,{
            headers : authHeader()
        });
    }


    getSortedPastCottageReservationsByName(clientID, fromElement, numberOfElementsForDisplay){
        let pagObj = this.formPaginationObj(fromElement, numberOfElementsForDisplay);
        return axios.post(RESERVATION_API_BASE_URL + "/cottage/history/name/" + clientID, pagObj, {
            headers : authHeader()
        });
    }
    getSortedPastCottageReservationsByDate(clientID, fromElement, numberOfElementsForDisplay){
        let pagObj = this.formPaginationObj(fromElement, numberOfElementsForDisplay);
        return axios.post(RESERVATION_API_BASE_URL + "/cottage/history/date/" + clientID, pagObj, {
            headers : authHeader()
        });
    }
    getSortedPastCottageReservationsByPrice(clientID, fromElement, numberOfElementsForDisplay){
        let pagObj = this.formPaginationObj(fromElement, numberOfElementsForDisplay);
        return axios.post(RESERVATION_API_BASE_URL + "/cottage/history/price/" + clientID, pagObj, {
            headers : authHeader()
        });
    }
    getSortedPastCottageReservationsByDuration(clientID, fromElement, numberOfElementsForDisplay){
        let pagObj = this.formPaginationObj(fromElement, numberOfElementsForDisplay);
        return axios.post(RESERVATION_API_BASE_URL + "/cottage/history/duration/" + clientID, pagObj, {
            headers : authHeader()
        });
    }
    getSortedPastShipReservationsByName(clientID, fromElement, numberOfElementsForDisplay){
        let pagObj = this.formPaginationObj(fromElement, numberOfElementsForDisplay);
        return axios.post(RESERVATION_API_BASE_URL + "/ship/history/name/" + clientID, pagObj, {
            headers : authHeader()
        });
    }
    getSortedPastShipReservationsByDate(clientID, fromElement, numberOfElementsForDisplay){
        let pagObj = this.formPaginationObj(fromElement, numberOfElementsForDisplay);
        return axios.post(RESERVATION_API_BASE_URL + "/ship/history/date/" + clientID, pagObj, {
            headers : authHeader()
        });
    }
    getSortedPastShipReservationsByPrice(clientID, fromElement, numberOfElementsForDisplay){
        let pagObj = this.formPaginationObj(fromElement, numberOfElementsForDisplay);
        return axios.post(RESERVATION_API_BASE_URL + "/ship/history/price/" + clientID, pagObj, {
            headers : authHeader()
        });
    }
    getSortedPastShipReservationsByDuration(clientID, fromElement, numberOfElementsForDisplay){
        let pagObj = this.formPaginationObj(fromElement, numberOfElementsForDisplay);
        return axios.post(RESERVATION_API_BASE_URL + "/ship/history/duration/" + clientID, pagObj, {
            headers : authHeader()
        });
    }


    formPaginationObj(fromElement, numberOfElementsForDisplay){
        return {
            fromElement: fromElement,
            numberToDisplay: numberOfElementsForDisplay
        }
    }

    reserveEntity(resObj) {
        return axios.post(RESERVATION_API_BASE_URL + "/reserve", resObj,  {
            headers : authHeader()
        });
    }

    getUpcomingReservationsForClient(id) {
        return axios.get(RESERVATION_API_BASE_URL + "/upcoming/" + id,  {
            headers : authHeader()
        });
    }
}

export default new ReservationServce();
