import axios from 'axios';

import Config from "../config.json";
import authHeader from "@/servieces/authHeader";

const COTTAGE_OWNER_API_BASE_URL = Config.BASE_URL + '/cottage/';

class CottageService{
    getAllCottages(){
        return axios.get(COTTAGE_OWNER_API_BASE_URL+'all',{
            headers:authHeader()
        });
    }

    getCottage(cottageId){
        return axios.get(COTTAGE_OWNER_API_BASE_URL + 'detail/' + cottageId, {
                headers:authHeader()
            })
    }

    getCottageByOwner(ownerId){
        return axios.get(COTTAGE_OWNER_API_BASE_URL+ownerId,{
            headers:authHeader()
        });
    }
    saveCottage(cottage){
        return axios.post(COTTAGE_OWNER_API_BASE_URL + 'save', cottage,{
            headers:authHeader()
        });
    }

    updateCottage(cottage){
        return axios.post(COTTAGE_OWNER_API_BASE_URL + 'update', cottage,{
            headers: authHeader()
        });

    }
    deleteCottage(cottageId){

        return axios.delete(COTTAGE_OWNER_API_BASE_URL + 'delete/'+cottageId,{
            headers: authHeader()
        });
    }
}

export default new CottageService();