import axios from 'axios';
import authHeader from "@/servieces/authHeader";

import Config from "../config.json";

const CLIENT_API_BASE_URL = Config.BASE_URL + '/client';

class ClientServce{
    getClient(clientID){
        console.log(authHeader());
        return axios.get(CLIENT_API_BASE_URL+'/profile/'+clientID, {
            headers: authHeader()
        });
    }
    updateClient(clientID, client){
        return axios.post(CLIENT_API_BASE_URL + "/profile/" + clientID, client, {
            headers: authHeader()
        });
    }
}

export default new ClientServce();
