import axios from 'axios';

import Config from "../config.json";
import authHeader from "@/servieces/authHeader";

const RESERVATION_API_BASE_URL = Config.BASE_URL + '/offer';

class ReservationService{

    getFutureReservations(offerId){
        return axios.get(RESERVATION_API_BASE_URL+'/reservations/future/'+offerId,{
            headers:authHeader()
        });
    }

    getPastReservations(offerId){
        return axios.get(RESERVATION_API_BASE_URL+'/reservations/past/'+offerId,{
            headers:authHeader()
        });
    }


    getReservationsReportYearly(ownerId){
        return axios.get(RESERVATION_API_BASE_URL+'/reportYearly/'+ownerId,{
            headers:authHeader()
        });
    }

    getReservationsReportYearlyShip(ownerId){
        return axios.get(RESERVATION_API_BASE_URL+'/reportYearly/ship/'+ownerId,{
            headers:authHeader()
        });
    }

    getAllReservationsForOffer(offerId){
        return axios.get(RESERVATION_API_BASE_URL + '/reservation/periods/' + offerId, {
            headers:authHeader()
        });
    }

    getReservationsReportMonthly(ownerId) {
        return axios.get(RESERVATION_API_BASE_URL + '/reportMonthly/' + ownerId, {
            headers: authHeader()
        });
    }
    getReservationsReportMonthlyShip(ownerId) {
        return axios.get(RESERVATION_API_BASE_URL + '/reportMonthly/ship/' + ownerId, {
            headers: authHeader()
        });
    }

    getReservationsReportWeeklyShip(ownerId) {
        return axios.get(RESERVATION_API_BASE_URL + '/reportWeekly/ship/' + ownerId, {
            headers: authHeader()
        });
    }

    getReservationsPeriodShip(ownerId,start,end){
        let config = {
            headers: authHeader(),
            params: {
                start_date: start,
                end_date: end
            }
        }
        return axios.get(RESERVATION_API_BASE_URL + '/reportPeriod/ship/' + ownerId, config);
    }

    getReservationsPeriod(ownerId,start,end){
        let config = {
            headers: authHeader(),
            params: {
                start_date: start,
                end_date: end
            }
        }
        return axios.get(RESERVATION_API_BASE_URL + '/reportPeriod/' + ownerId, config);
    }
}

export default new ReservationService();