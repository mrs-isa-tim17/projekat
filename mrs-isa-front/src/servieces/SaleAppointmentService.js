import axios from 'axios';
import Config from "../config.json";
import authHeader from "@/servieces/authHeader";

const SALE_APPOINTMENT_API_BASE_URL = Config.BASE_URL + '/sale';

class SaleAppointmentService{

    defineSaleAppointmentForOffer(offerId, saleAppointmentDTO){
        return axios.post(SALE_APPOINTMENT_API_BASE_URL + '/define/'+offerId, saleAppointmentDTO, {
            headers: authHeader()
        });
    }

    defineSaleAppointmentForCottage(offerId,saleAppointmentDTO){
        return axios.post(SALE_APPOINTMENT_API_BASE_URL + '/cottage/define/'+offerId, saleAppointmentDTO, {
            headers: authHeader()
        });
    }

    getSaleAppointmentsById(offerId) {
        return axios.get(SALE_APPOINTMENT_API_BASE_URL+'/quick/reservation/' + offerId, {
            headers: authHeader()
        });
    }

    reserveSaleAppointment(reserveObj){
        return axios.post(SALE_APPOINTMENT_API_BASE_URL + "/quick/reserve", reserveObj, {
            headers: authHeader()
        });
    }

    gatAllSaleAppontmentsForOffer(offerId){
        return axios.get(SALE_APPOINTMENT_API_BASE_URL + '/quick/reservation/periods/' + offerId, {
            headers: authHeader()
        });
    }
}
export default new SaleAppointmentService();
