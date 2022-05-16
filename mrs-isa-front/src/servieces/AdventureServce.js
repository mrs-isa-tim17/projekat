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

    filterAdventures(filterParams) {
        return axios.post(ADVENTURES_API_BASE_URL + "/filter", filterParams);
    }

    sortAdventureListByName(adventures) {
        return axios.post(ADVENTURES_API_BASE_URL + "/sort/name", adventures);
    }

    sortAdventureListByLocation(adventures) {
        return axios.post(ADVENTURES_API_BASE_URL + "/sort/location", adventures);

    }

    sortAdventureListByRating(adventures) {
        return axios.post(ADVENTURES_API_BASE_URL + "/sort/rating", adventures);

    }

    sortAdventureListByPrice(adventures) {
        return axios.post(ADVENTURES_API_BASE_URL + "/sort/price", adventures);

    }

    sortAdventureListByCapacity(adventures) {
        return axios.post(ADVENTURES_API_BASE_URL + "/sort/capacity", adventures);

    }

    search(searchParam) {
        return axios.post(ADVENTURES_API_BASE_URL + "/search", searchParam);
    }
}

export default new AdventureServce();
