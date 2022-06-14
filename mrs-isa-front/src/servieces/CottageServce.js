import axios from 'axios';
import Config from "../config.json";

const COTTAGE_API_BASE_URL = Config.BASE_URL + '/cottage/site';

class CottageServce{
    async getCottagesForHomePageView(){
        return axios.get(COTTAGE_API_BASE_URL + "/short");
    }
    filterCottages(filterParams, fromElement, numberOfElementsForDisplay){
        filterParams = this.formFilterParamsObj(filterParams, fromElement, numberOfElementsForDisplay);
        return axios.post(COTTAGE_API_BASE_URL + "/filter", filterParams);
    }
    formFilterParamsObj(filterParams, fromElement, numberOfElementsForDisplay){
        filterParams.fromElement = fromElement;
        filterParams.numberToDisplay = numberOfElementsForDisplay;
        return filterParams;
    }
    getCottage(cottageId){
        return axios.get(COTTAGE_API_BASE_URL + "/" + cottageId);
    }

    getCottageReviews(offerId, fromElement, numberOfElementsForDisplay) {
        let pagObj = this.formPaginationObj(fromElement, numberOfElementsForDisplay);
        return axios.post(COTTAGE_API_BASE_URL + "/review/" + offerId, pagObj);
    }
    formPaginationObj(fromElement, numberOfElementsForDisplay){
        return {
            fromElement: fromElement,
            numberToDisplay: numberOfElementsForDisplay
        }
    }
}

export default new CottageServce();
