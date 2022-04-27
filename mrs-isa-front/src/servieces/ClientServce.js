import axios from 'axios';

import Config from "../config.json";

const CLIENT_API_BASE_URL = Config.BASE_URL + '/client/profile';

class ClientServce{
    getClient(clientID){
        return axios.get(CLIENT_API_BASE_URL+'/'+clientID);
    }
    updateClient(clientID, client){
        return axios.post(CLIENT_API_BASE_URL + "/" + clientID, client);
    }
}

export default new ClientServce();