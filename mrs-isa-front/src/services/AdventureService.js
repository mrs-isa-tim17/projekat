import axios from 'axios';

import Config from "../config.json";

const ADVENTURE_API_BASE_URL = Config.BASE_URL + '/adventure/detail/';

class AdventureService{
    getAllAdventures(){
        return axios.get(ADVENTURE_API_BASE_URL+'all/');
    }

    getAdventure(adventureId){
        console.log(adventureId)
        return axios.get(ADVENTURE_API_BASE_URL+adventureId);
    }
    saveAdventure(adventure){
        return axios.post(ADVENTURE_API_BASE_URL + 'save/', adventure);
    }

    updateAdventure(adventure){
        return axios.post(ADVENTURE_API_BASE_URL + 'update/', adventure);

    }
    deleteAdventure(adventureId){
        console.log(adventureId)
        return axios.delete(ADVENTURE_API_BASE_URL + 'delete/'+adventureId);
    }
}

export default new AdventureService();