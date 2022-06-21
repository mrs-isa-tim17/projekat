import axios from 'axios';

import Config from "../../config.json";
import authHeader from "@/servieces/authHeader";

const SHIP_OWNER_API_BASE_URL = Config.BASE_URL + '/ship/';

class ShipService{
    getAllShips(){
        return axios.get(SHIP_OWNER_API_BASE_URL+'site/all',{
            headers:authHeader()
        });
    }

    getShip(shipId){
        return axios.get(SHIP_OWNER_API_BASE_URL + 'detail/' + shipId, {
            headers:authHeader()
        })
    }

    getShipByOwner(ownerId){
        return axios.get(SHIP_OWNER_API_BASE_URL+ownerId,{
            headers:authHeader()
        });
    }
    saveShip(ship){
        return axios.post(SHIP_OWNER_API_BASE_URL + 'save', ship,{
            headers:authHeader()
        });
    }

    updateShip(id,ship){
        return axios.post(SHIP_OWNER_API_BASE_URL + 'update/' + id, ship,{
            headers: authHeader()
        });

    }
    deleteShip(shipId){

        return axios.delete(SHIP_OWNER_API_BASE_URL + 'delete/'+ shipId,{
            headers: authHeader()
        });
    }

    getActiveShipsForAdmin(){
        return axios.get(SHIP_OWNER_API_BASE_URL + 'admin/all',{
            headers: authHeader()
        });
    }
    getDeletedShips(){
        return axios.get(SHIP_OWNER_API_BASE_URL + 'admin/all/deleted/', {
            headers: authHeader()
        });
    }
}

export default new ShipService();
