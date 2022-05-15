import axios from 'axios';
import Config from "../config.json";

const SHIP_API_BASE_URL = Config.BASE_URL + '/ship/site';

class CottageServce{
    getShips(){
        return axios.get(SHIP_API_BASE_URL + "/all");
    }
    getShipsForHomePageView(){
        return axios.get(SHIP_API_BASE_URL + "/short");
    }

    filterShips(filterParams) {
        return axios.post(SHIP_API_BASE_URL + "/filter", filterParams);
    }
}

export default new CottageServce();
