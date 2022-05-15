import axios from 'axios';
import Config from "../config.json";

const SHIP_API_BASE_URL = Config.BASE_URL + '/ship/site';

class ShipServce{
    getShips(){
        return axios.get(SHIP_API_BASE_URL + "/all");
    }
    getShipsForHomePageView(){
        return axios.get(SHIP_API_BASE_URL + "/short");
    }

    filterShips(filterParams) {
        return axios.post(SHIP_API_BASE_URL + "/filter", filterParams);
    }

    sortShipListByName(ships) {
        return axios.post(SHIP_API_BASE_URL + "/sort/name", ships);
    }

    sortShipListByLocation(ships) {
        return axios.post(SHIP_API_BASE_URL + "/sort/location", ships);
    }

    sortShipListByRating(ship) {
        return axios.post(SHIP_API_BASE_URL + "/sort/rating", ship);
    }

    sortShipListByPrice(ships) {
        return axios.post(SHIP_API_BASE_URL + "/sort/price", ships);
    }

    sortShipListBySpeed(ships) {
        return axios.post(SHIP_API_BASE_URL + "/sort/speed", ships);
    }

    sortShipListByCapacity(ships) {
        return axios.post(SHIP_API_BASE_URL + "/sort/capacity", ships);
    }
}

export default new ShipServce();
