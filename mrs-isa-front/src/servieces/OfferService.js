import axios from 'axios';

import Config from "../config.json";
import authHeader from "@/servieces/authHeader";

const OFFER_BASE_URL = Config.BASE_URL + '/offer/';

class OfferService {
    getPrice(fromUntilPriceType){
        return axios.post(OFFER_BASE_URL+'site/price', fromUntilPriceType);
    }
    subscribeToOffer(clientID, offerId){
        return axios.get(OFFER_BASE_URL+'subscribe/'+clientID + "/" + offerId,{
            headers: authHeader()
        });
    }
    unsubscribeForOffer(clientID, offerId){
        return axios.get(OFFER_BASE_URL+'unsubscribe/'+clientID + "/" + offerId,{
            headers: authHeader()
        });
    }

}

export default new OfferService();
