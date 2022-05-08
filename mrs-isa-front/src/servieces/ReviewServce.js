import axios from 'axios';
import authHeader from "@/servieces/authHeader";

import Config from "../config.json";

const REVIEW_API_BASE_URL = Config.BASE_URL + '/complaint';

class ReviewServce{
    offerReviewed(complaint) {
        return axios.post(REVIEW_API_BASE_URL+'/offer', complaint, {
            headers: authHeader()
        });
    }
}

export default new ReviewServce();
