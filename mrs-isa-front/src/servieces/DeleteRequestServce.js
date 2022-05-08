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
}

export default new AccountServce();
