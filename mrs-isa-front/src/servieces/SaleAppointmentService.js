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

}
export default new SaleAppointmentService();
