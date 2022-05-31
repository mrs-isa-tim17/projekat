import axios from 'axios';

import Config from "../config.json";
import authHeader from "@/servieces/authHeader";

const PERCENTAGE_API_BASE_URL = Config.BASE_URL + '/percentage';

class PercentageService {
    getAllPercentages(){
        return axios.get(PERCENTAGE_API_BASE_URL + '/all', {
            headers: authHeader()
        });
    }

    defineNewPercentage(percentageDTO){
        return axios.post(PERCENTAGE_API_BASE_URL + '/define', percentageDTO,{
            headers: authHeader()
        });
    }

}
export default new PercentageService();
