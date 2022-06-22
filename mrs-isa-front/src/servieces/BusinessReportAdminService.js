import axios from 'axios';
import authHeader from "@/servieces/authHeader";

import Config from "../config.json";

const ADMIN_BUSINESS_API_BASE_URL = Config.BASE_URL + '/admin/business';

class BusinessReportAdminService {

    getReport(adminReportDTO){
        return axios.post(ADMIN_BUSINESS_API_BASE_URL + '/report', adminReportDTO,{
            headers: authHeader()
        });
    }

}
export default new BusinessReportAdminService();
