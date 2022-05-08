import axios from 'axios';
import authHeader from "@/servieces/authHeader";

import Config from "../config.json";

const CLIENT_API_BASE_URL = Config.BASE_URL + '/review';

class ReviewServce{
    cottageReviewed(review) {
        return axios.post(CLIENT_API_BASE_URL+'/cottage', review, {
            headers: authHeader()
        });
    }
}

export default new ReviewServce();
