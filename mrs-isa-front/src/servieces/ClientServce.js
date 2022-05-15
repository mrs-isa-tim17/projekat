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
    getClientPenalties(clientId){
        return axios.get(CLIENT_API_BASE_URL+'/penalties/'+clientId, {
            headers: authHeader()
        });
    }
    updateClient(clientID, client){
        return axios.post(CLIENT_API_BASE_URL + "/profile/" + clientID, client, {
            headers: authHeader()
        });
    }

    verify(code){
        return axios.get(CLIENT_API_BASE_URL + "/verify/" + code)
            .then((response) =>{
                console.log(response.data);
                if (response.data.accessToken){
                    localStorage.user = JSON.stringify(response.data); //setItem('user', response.data);
                }else{
                    localStorage.user = null;
                }

                return response.data;

            })
    }
}

export default new ClientServce();
