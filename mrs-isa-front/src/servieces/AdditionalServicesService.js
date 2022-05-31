import axios from 'axios';

import Config from "../config.json";
import authHeader from "@/servieces/authHeader";

const ADDITIONAL_SERVICE_API_BASE_URL = Config.BASE_URL + '/additional/';

class AdditionalServicesService {
    getAll(){
        return axios.get(ADDITIONAL_SERVICE_API_BASE_URL + 'all', {
            headers: authHeader()
        });
    }

}
export default new AdditionalServicesService();