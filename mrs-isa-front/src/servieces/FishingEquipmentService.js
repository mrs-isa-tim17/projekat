import axios from 'axios';

import Config from "../config.json";
import authHeader from "@/servieces/authHeader";

const FISHING_EQUIPMENT_API_BASE_URL = Config.BASE_URL + '/fishing/';

class FishingEquipmentService {
    getAll(){
        return axios.get(FISHING_EQUIPMENT_API_BASE_URL + 'all', {
            headers: authHeader()
        });
    }

}
export default new FishingEquipmentService();