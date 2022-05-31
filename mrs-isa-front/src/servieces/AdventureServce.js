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
    filterAdventures(filterParams, fromElement, numberOfElementsForDisplay){
        filterParams = this.formFilterParamsObj(filterParams, fromElement, numberOfElementsForDisplay);
        return axios.post(ADVENTURES_API_BASE_URL + "/filter", filterParams);
    }

    formFilterParamsObj(filterParams, fromElement, numberOfElementsForDisplay){
        filterParams.fromElement = fromElement;
        filterParams.numberToDisplay = numberOfElementsForDisplay;
        return filterParams;
    }
    getAdvanture(offerId) {
        return axios.get(ADVENTURES_API_BASE_URL + "/" + offerId);
    }

    getAdventureReviews(offerId) {
        return axios.get(ADVENTURES_API_BASE_URL + "/review/" + offerId);
    }
}

export default new AdventureServce();
