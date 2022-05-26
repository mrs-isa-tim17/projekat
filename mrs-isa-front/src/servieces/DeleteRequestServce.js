import axios from 'axios';
import authHeader from "@/servieces/authHeader";

import Config from "../config.json";

const ACCOUNT_API_BASE_URL = Config.BASE_URL + '/account';

class AccountServce{
    makeDeleteRequest(clientId, reason) {
        return axios.post(ACCOUNT_API_BASE_URL+'/delete/' + clientId , reason, {
            headers: authHeader()
        });
    }
    getUnprocessedDeleteRequests(){
     return axios.get(ACCOUNT_API_BASE_URL + '/delete/unprocessed/',{
         headers: authHeader()
     });
    }
    acceptDeleteRequest(id, text){
        return axios.post(ACCOUNT_API_BASE_URL + '/delete/accept/' + id, text, {
            headers: authHeader()
        });
    }
    rejectDeleteRequest(id, text){
        return axios.post(ACCOUNT_API_BASE_URL + '/delete/reject/' + id, text, {
            headers: authHeader()
        });
    }
}

export default new AccountServce();
