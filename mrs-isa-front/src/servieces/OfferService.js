import axios from 'axios';

import Config from "../config.json";

const OFFER_BASE_URL = Config.BASE_URL + '/offer/';

class OfferService {
    getPrice(fromUntilPriceType){
        return axios.post(OFFER_BASE_URL+'site/price', fromUntilPriceType);
    }

}

export default new OfferService();
