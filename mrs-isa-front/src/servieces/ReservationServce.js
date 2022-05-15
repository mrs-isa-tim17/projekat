import axios from 'axios';
import authHeader from "@/servieces/authHeader";

import Config from "../config.json";

const RESERVATION_API_BASE_URL = Config.BASE_URL + '/offer';

class ReservationServce{

    getPastCottageReservations(clientID){
        return axios.get(RESERVATION_API_BASE_URL + "/cottage/history/" + clientID, {
            headers : authHeader()
        });
    }
    getPastShipReservations(clientID){
        return axios.get(RESERVATION_API_BASE_URL + "/ship/history/" + clientID, {
            headers : authHeader()
        });
    }
    getPastAdventureReservations(clientID){
        return axios.get(RESERVATION_API_BASE_URL + "/adventure/history/" + clientID, {
            headers : authHeader()
        });
    }


    getSortedPastAdventureReservationsByName(clientID){
        return axios.get(RESERVATION_API_BASE_URL + "/adventure/history/name/" + clientID, {
            headers : authHeader()
        });
    }
    getSortedPastAdventureReservationsByDate(clientID){
        return axios.get(RESERVATION_API_BASE_URL + "/adventure/history/date/" + clientID, {
            headers : authHeader()
        });
    }
    getSortedPastAdventureReservationsByPrice(clientID){
        return axios.get(RESERVATION_API_BASE_URL + "/adventure/history/price/" + clientID, {
            headers : authHeader()
        });
    }
    getSortedPastAdventureReservationsByDuration(clientID){
        return axios.get(RESERVATION_API_BASE_URL + "/adventure/history/duration/" + clientID, {
            headers : authHeader()
        });
    }


    getSortedPastCottageReservationsByName(clientID){
        return axios.get(RESERVATION_API_BASE_URL + "/cottage/history/name/" + clientID, {
            headers : authHeader()
        });
    }
    getSortedPastCottageReservationsByDate(clientID){
        return axios.get(RESERVATION_API_BASE_URL + "/cottage/history/date/" + clientID, {
            headers : authHeader()
        });
    }
    getSortedPastCottageReservationsByPrice(clientID){
        return axios.get(RESERVATION_API_BASE_URL + "/cottage/history/price/" + clientID, {
            headers : authHeader()
        });
    }
    getSortedPastCottageReservationsByDuration(clientID){
        return axios.get(RESERVATION_API_BASE_URL + "/cottage/history/duration/" + clientID, {
            headers : authHeader()
        });
    }
    getSortedPastShipReservationsByName(clientID){
        return axios.get(RESERVATION_API_BASE_URL + "/ship/history/name/" + clientID, {
            headers : authHeader()
        });
    }
    getSortedPastShipReservationsByDate(clientID){
        return axios.get(RESERVATION_API_BASE_URL + "/ship/history/date/" + clientID, {
            headers : authHeader()
        });
    }
    getSortedPastShipReservationsByPrice(clientID){
        return axios.get(RESERVATION_API_BASE_URL + "/ship/history/price/" + clientID, {
            headers : authHeader()
        });
    }
    getSortedPastShipReservationsByDuration(clientID){
        return axios.get(RESERVATION_API_BASE_URL + "/ship/history/duration/" + clientID, {
            headers : authHeader()
        });
    }
}

export default new ReservationServce();
