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

    getUnprocessedComplaints(){
        return axios.get(COMPLAINT_API_BASE_URL+'/unprocessed', {
            headers: authHeader()
        });
    }
    answerComplaint(id, text){
        return axios.post(COMPLAINT_API_BASE_URL+'/answer/'+id, text,{
            headers: authHeader()
        });
    }
}

export default new ComplaintServce();
