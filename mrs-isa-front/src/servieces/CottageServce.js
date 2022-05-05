import axios from 'axios';
import Config from "../config.json";

const COTTAGE_API_BASE_URL = Config.BASE_URL + '/cottage/site';

class CottageServce{
    getCottages(){
        return axios.get(COTTAGE_API_BASE_URL + "/all");
    }
}

export default new CottageServce();
