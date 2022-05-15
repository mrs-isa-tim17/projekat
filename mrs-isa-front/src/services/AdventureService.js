import axios from 'axios';

import Config from "../config.json";
import authHeader from "@/servieces/authHeader";

const ADVENTURE_API_BASE_URL = Config.BASE_URL + '/adventure/detail/';

class AdventureService{
    getAllAdventures(){
        return axios.get(ADVENTURE_API_BASE_URL+'all/', {
            headers: authHeader()
        });
    }
    getAdventure(adventureId){
        console.log(adventureId)
        return axios.get(ADVENTURE_API_BASE_URL+adventureId, {
            headers: authHeader()
        });
    }
    saveAdventure(adventure){
        return axios.post(ADVENTURE_API_BASE_URL + 'save/', adventure, {
            headers: authHeader()
        });
    }
    updateAdventure(adventure){
        return axios.post(ADVENTURE_API_BASE_URL + 'update/', adventure, {
            headers: authHeader()
        });

    }
    deleteAdventure(adventureId){
        console.log(adventureId)
        return axios.delete(ADVENTURE_API_BASE_URL + 'delete/'+adventureId, {
            headers: authHeader()
        });
    }
    defineAvailability(adventureId, period){
        console.log(period)
        return axios.post(ADVENTURE_API_BASE_URL + 'period/availability/' + adventureId, period, {
            headers: authHeader()
        });
    }
    defineUnavailability(adventureId, period){
        console.log(period)
        return axios.post(ADVENTURE_API_BASE_URL + 'period/unavailability/' + adventureId, period, {
            headers: authHeader()
        });

    }
}

export default new AdventureService();