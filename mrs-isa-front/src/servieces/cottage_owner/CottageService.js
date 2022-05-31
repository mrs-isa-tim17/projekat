import axios from 'axios';

import Config from "../../config.json";
import authHeader from "@/servieces/authHeader";

const COTTAGEE_API_BASE_URL = Config.BASE_URL + '/cottage/';

class CottageService{
    getAllCottages(){
        return axios.get(COTTAGEE_API_BASE_URL+'all',{
            headers:authHeader()
        });
    }

    getCottage(cottageId){
        return axios.get(COTTAGEE_API_BASE_URL + 'detail/' + cottageId, {
                headers:authHeader()
            })
    }

    getCottageByOwner(ownerId){
        return axios.get(COTTAGEE_API_BASE_URL+ownerId,{
            headers:authHeader()
        });
    }
    saveCottage(cottage){
        return axios.post(COTTAGEE_API_BASE_URL + 'save', cottage,{
            headers:authHeader()
        });
    }

    updateCottage(cottage){
        return axios.post(COTTAGEE_API_BASE_URL + 'update', cottage,{
            headers: authHeader()
        });

    }
    deleteCottage(cottageId){

        return axios.delete(COTTAGEE_API_BASE_URL + 'delete/'+cottageId,{
            headers: authHeader()
        });
    }

    getReservationPeriods(Id){
        return axios.get( COTTAGEE_API_BASE_URL+ 'reservation/periods/' + Id, {
            headers: authHeader()
        });
    }

    getActiveCottagesForAdmin(){
        return axios.get(COTTAGEE_API_BASE_URL + 'admin/all/', {
            headers: authHeader()
        });
    }

    getDeletedCottages(){
        return axios.get(COTTAGEE_API_BASE_URL + 'admin/all/deleted/', {
            headers: authHeader()
        });
    }


}

export default new CottageService();