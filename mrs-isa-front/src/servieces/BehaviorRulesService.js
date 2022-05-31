import axios from 'axios';

import Config from "../config.json";
import authHeader from "@/servieces/authHeader";

const BEHAVIORAL_RULES_API_BASE_URL = Config.BASE_URL + '/behavior/';

class BehaviorRulesService {
    getAll(){
        return axios.get(BEHAVIORAL_RULES_API_BASE_URL + 'all', {
            headers: authHeader()
        });
    }

}
export default new BehaviorRulesService();