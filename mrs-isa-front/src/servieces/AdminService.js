import axios from 'axios';
import authHeader from "@/servieces/authHeader";

import Config from "../config.json";

const ADMIN_API_BASE_URL = Config.BASE_URL + '/admin';

class AdminService {
    getOwner(coID) {
        console.log(authHeader());
        return axios.get(ADMIN_API_BASE_URL + '/profile/' + coID, {
            headers: authHeader()
        });
    }

    updateOwner(coID, co) {
        return axios.post(ADMIN_API_BASE_URL + "/update/profile/" + coID, co, {
            headers: authHeader()
        });
    }
}

export default new AdminService();
