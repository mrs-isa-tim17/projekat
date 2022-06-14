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

    formPaginationObj(fromElement, numberOfElementsForDisplay){
        return {
            fromElement: fromElement,
            numberToDisplay: numberOfElementsForDisplay
        }
    }
    getShip(offerId) {
        return axios.get(SHIP_API_BASE_URL + "/" + offerId);
    }

    getShipReviews(offerId, fromElement, numberOfElementsForDisplay) {
        let pagObj = this.formPaginationObj(fromElement, numberOfElementsForDisplay);
        return axios.post(SHIP_API_BASE_URL + "/review/" + offerId, pagObj);
    }

    getReservationsForShip(offerId){
        console.log(offerId);
        return axios.get(SHIP_API_BASE_URL + '/calendar/reservation/' + offerId);
    }
}

export default new ShipServce();
