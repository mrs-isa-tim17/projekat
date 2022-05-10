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


}
export default new ReservationService();