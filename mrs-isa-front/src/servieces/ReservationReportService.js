import axios from 'axios';
import authHeader from "@/servieces/authHeader";

import Config from "../config.json";

const RESERVATION_REPORT_API_BASE_URL = Config.BASE_URL + '/reservation/report';

class ReservationReportService{
    saveReservationReport(resReport) {
        return axios.post(RESERVATION_REPORT_API_BASE_URL+'/save', resReport, {
            headers: authHeader()
        });
    }

    getUnprocessedReservationReports(){
        return axios.get(RESERVATION_REPORT_API_BASE_URL + '/unprocessed',{
            headers: authHeader()
        });
    }

    approveReservationReport(report){
        return axios.post(RESERVATION_REPORT_API_BASE_URL + '/approve',report,{
            headers: authHeader()
        });
    }

    rejectReservationReport(report){
        return axios.post(RESERVATION_REPORT_API_BASE_URL + '/reject',report,{
            headers: authHeader()
        });
    }


}

export default new ReservationReportService();
