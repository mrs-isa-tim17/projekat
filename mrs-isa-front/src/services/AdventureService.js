import axios from 'axios';

import Config from "../config.json";

const ADVENTURE_API_BASE_URL = Config.BASE_URL + '/adventure/detail/';

class AdventureService{
    getAdventure(adventureId){
        console.log(adventureId)
        return axios.get(ADVENTURE_API_BASE_URL+adventureId);
    }
    saveAdventure(adventure){
        return axios.post(ADVENTURE_API_BASE_URL + 'save/', adventure);
    }
}

export default new AdventureService();