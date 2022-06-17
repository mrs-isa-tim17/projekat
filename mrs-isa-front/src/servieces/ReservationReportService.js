import axios from 'axios';
import authHeader from "@/servieces/authHeader";

import Config from "../config.json";

const RESERVATION_REPORT_API_BASE_URL = Config.BASE_URL + '/reservationReport';

class ReservationReportService{
    saveReservationReport(resReport) {
        return axios.post(RESERVATION_REPORT_API_BASE_URL+'/save', resReport, {
            headers: authHeader()
        });
    }

    haveReservationReport(id) {
        return axios.get(RESERVATION_REPORT_API_BASE_URL+'/check/'+id,  {
            headers: authHeader()
        });
    }
}

export default new ReservationReportService();
