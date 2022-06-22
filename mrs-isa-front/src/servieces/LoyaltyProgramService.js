import axios from 'axios';

import Config from "../config.json";
import authHeader from "@/servieces/authHeader";

const LOYALTY_API_BASE_URL = Config.BASE_URL + '/loyalty/';

class LoyaltyProgramService {
    getLoyaltyScaleForRole(role){
        return axios.get(LOYALTY_API_BASE_URL + 'scale/all/'+role, {
            headers: authHeader()
        });
    }

    defineNewScale(id, scale){
        return axios.post(LOYALTY_API_BASE_URL + 'scale/define/' + id, scale, {
            headers: authHeader()
        });
    }

    getPointsForUser(user){
        return axios.get(LOYALTY_API_BASE_URL + 'points/all/' + user, {
            headers: authHeader()
        });
    }

    defineNewPoint(points) {
        return axios.post(LOYALTY_API_BASE_URL + 'points/define', points, {
            headers: authHeader()
        });
    }

}
export default new LoyaltyProgramService();