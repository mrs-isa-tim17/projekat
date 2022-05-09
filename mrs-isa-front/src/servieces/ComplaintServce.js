import axios from 'axios';
import authHeader from "@/servieces/authHeader";

import Config from "../config.json";

const COMPLAINT_API_BASE_URL = Config.BASE_URL + '/complaint';

class ComplaintServce{
    complaintOnOffer(review) {
        return axios.post(COMPLAINT_API_BASE_URL+'/offer', review, {
            headers: authHeader()
        });
    }
}

export default new ComplaintServce();
