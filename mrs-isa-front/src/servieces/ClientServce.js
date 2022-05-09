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
    getPastCottageReservations(clientID){
        return axios.get(CLIENT_API_BASE_URL + "/cottage/history/" + clientID, {
            headers : authHeader()
        });
    }

    getPastShipReservations(clientID){
        return axios.get(CLIENT_API_BASE_URL + "/ship/history/" + clientID, {
            headers : authHeader()
        });
    }
    getSortedPastCottageReservationsByName(clientID){
        return axios.get(CLIENT_API_BASE_URL + "/cottage/history/name/" + clientID, {
            headers : authHeader()
        });
    }
    getSortedPastCottageReservationsByDate(clientID){
        return axios.get(CLIENT_API_BASE_URL + "/cottage/history/date/" + clientID, {
            headers : authHeader()
        });
    }
    getSortedPastCottageReservationsByPrice(clientID){
        return axios.get(CLIENT_API_BASE_URL + "/cottage/history/price/" + clientID, {
            headers : authHeader()
        });
    }
    getSortedPastCottageReservationsByDuration(clientID){
        return axios.get(CLIENT_API_BASE_URL + "/cottage/history/duration/" + clientID, {
            headers : authHeader()
        });
    }
    getSortedPastShipReservationsByName(clientID){
        return axios.get(CLIENT_API_BASE_URL + "/ship/history/name/" + clientID, {
            headers : authHeader()
        });
    }
    getSortedPastShipReservationsByDate(clientID){
        return axios.get(CLIENT_API_BASE_URL + "/ship/history/date/" + clientID, {
            headers : authHeader()
        });
    }
    getSortedPastShipReservationsByPrice(clientID){
        return axios.get(CLIENT_API_BASE_URL + "/ship/history/price/" + clientID, {
            headers : authHeader()
        });
    }
    getSortedPastShipReservationsByDuration(clientID){
        return axios.get(CLIENT_API_BASE_URL + "/ship/history/duration/" + clientID, {
            headers : authHeader()
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
