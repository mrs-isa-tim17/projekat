import axios from 'axios';
import authHeader from "@/servieces/authHeader";

import Config from "../config.json";

const REVIEW_API_BASE_URL = Config.BASE_URL + '/review';

class ReviewServce{
    offerReviewed(complaint) {
        return axios.post(REVIEW_API_BASE_URL+'/offer', complaint, {
            headers: authHeader()
        });
    }

    getReviews(id){
        return axios.get(REVIEW_API_BASE_URL+'/all/'+id, {
            headers: authHeader()
        });
    }
    getRating(id){
        return axios.get(REVIEW_API_BASE_URL+'/rate/'+id, {
            headers: authHeader()
        });
    }

}

export default new ReviewServce();
