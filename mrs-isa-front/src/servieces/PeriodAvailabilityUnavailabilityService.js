import axios from 'axios';

import Config from "../config.json";
import authHeader from "@/servieces/authHeader";

const PERIOD_API_BASE_URL = Config.BASE_URL + '/period';

class PeriodAvailabilityUnavailabilityService {
    defineAvailability(adventureId, period){
        console.log(period)
        return axios.post(PERIOD_API_BASE_URL + '/availability/' + adventureId, period, {
            headers: authHeader()
        });
    }
    defineUnavailability(adventureId, period){
        console.log(period)
        return axios.post(PERIOD_API_BASE_URL + '/unavailability/' + adventureId, period, {
            headers: authHeader()
        });

    }
    getAvailabilityPeriods(adventureId){
        return axios.get(PERIOD_API_BASE_URL + '/availability/all/' + adventureId, {
            headers: authHeader()
        });
    }
    getUnavailabilityPeriods(adventureId){
        return axios.get(PERIOD_API_BASE_URL + '/unavailability/all/' + adventureId,{
            headers: authHeader()
        });

    }


}
export default new PeriodAvailabilityUnavailabilityService();