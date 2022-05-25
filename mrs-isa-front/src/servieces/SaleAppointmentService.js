import axios from 'axios';
import authHeader from "@/servieces/authHeader";

import Config from "../config.json";

const SALE_APPOINTMENT_API_BASE_URL = Config.BASE_URL + '/sale';

class SaleAppointmentService{
    getSaleAppointmentsById(offerId) {
        return axios.get(SALE_APPOINTMENT_API_BASE_URL+'/quick/reservation/' + offerId, {
            headers: authHeader()
        });
    }
}

export default new SaleAppointmentService();
