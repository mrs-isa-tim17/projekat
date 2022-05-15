import axios from 'axios';
import Config from "../config.json";
//import async from "async";

const COTTAGE_API_BASE_URL = Config.BASE_URL + '/cottage/site';

class CottageServce{
    getCottages(){
        return axios.get(COTTAGE_API_BASE_URL + "/all");
    }
    async getCottagesForHomePageView(){
        return axios.get(COTTAGE_API_BASE_URL + "/short");
    }
    filterCottages(filterParams){
        return axios.post(COTTAGE_API_BASE_URL + "/filter", filterParams);
    }

    sortCottageListByName(cottages) {
        return axios.post(COTTAGE_API_BASE_URL + "/sort/name", cottages);
    }
    sortCottageListByLocation(cottages) {
        return axios.post(COTTAGE_API_BASE_URL + "/sort/location", cottages);
    }

    sortCottageListByRating(cottages) {
        return axios.post(COTTAGE_API_BASE_URL + "/sort/rating", cottages);
    }

    sortCottageListByPrice(cottages) {
        return axios.post(COTTAGE_API_BASE_URL + "/sort/price", cottages);
    }

    sortCottageListByNumberOfRooms(cottages) {
        return axios.post(COTTAGE_API_BASE_URL + "/sort/rooms", cottages);
    }

    sortCottageListByNumberOfBeds(cottages) {
        return axios.post(COTTAGE_API_BASE_URL + "/sort/beds", cottages);
    }
}

export default new CottageServce();
