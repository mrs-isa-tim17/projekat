import axios from 'axios';
import Config from "../config.json";

const ADVENTURES_API_BASE_URL = Config.BASE_URL + '/adventure/site';

class AdventureServce{
    getAdventures(){
        return axios.get(ADVENTURES_API_BASE_URL + "/all");
    }
    getAdventuresForHomePageView(){
        return axios.get(ADVENTURES_API_BASE_URL + "/short");
    }

    filterAdventures(filterParams) {
        return axios.post(ADVENTURES_API_BASE_URL + "/filter", filterParams);
    }
}

export default new AdventureServce();
