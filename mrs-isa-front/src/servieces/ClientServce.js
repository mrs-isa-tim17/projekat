import axios from 'axios';

import Config from "../config.json";

const CLIENT_API_BASE_URL = Config.BASE_URL + '/client/profile';

class ClientServce{
    getClient(){
        return axios.get(CLIENT_API_BASE_URL);
    }
}

export default new ClientServce();