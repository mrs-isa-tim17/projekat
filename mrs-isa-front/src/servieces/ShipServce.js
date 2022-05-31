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

    filterShips(filterParams, fromElement, numberOfElementsForDisplay){
        filterParams = this.formFilterParamsObj(filterParams, fromElement, numberOfElementsForDisplay);
        return axios.post(SHIP_API_BASE_URL + "/filter", filterParams);
    }

    formFilterParamsObj(filterParams, fromElement, numberOfElementsForDisplay){
        filterParams.fromElement = fromElement;
        filterParams.numberToDisplay = numberOfElementsForDisplay;
        return filterParams;
    }

    /*filterShips(filterParams) {
        return axios.post(SHIP_API_BASE_URL + "/filter", filterParams);
    }*/

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

    search(searchParam) {
        return axios.post(SHIP_API_BASE_URL + "/search", searchParam);
    }

    getShip(offerId) {
        return axios.get(SHIP_API_BASE_URL + "/" + offerId);
    }

    getShipReviews(offerId) {
        return axios.get(SHIP_API_BASE_URL + "/review/" + offerId);
    }

    getReservationsForShip(offerId){
        console.log(offerId);
        return axios.get(SHIP_API_BASE_URL + '/calendar/reservation/' + offerId);
    }
}

export default new ShipServce();
