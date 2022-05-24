import axios from 'axios';
import authHeader from "@/servieces/authHeader";

import Config from "../../config.json";

const COTTAGE_OWNER_API_BASE_URL = Config.BASE_URL + '/cottage_owner';

class CottageOwnerService{
    getOwner(coID){
        console.log(authHeader());
        return axios.get(COTTAGE_OWNER_API_BASE_URL+'/profile/'+coID, {
            headers: authHeader()
        });
    }
    updateOwner(coID, co){
        return axios.post(COTTAGE_OWNER_API_BASE_URL + "/update/profile/" + coID, co, {
            headers: authHeader()
        });
    }
    verify(code){
        return axios.get(COTTAGE_OWNER_API_BASE_URL + "/verify/" + code)
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

export default new CottageOwnerService();
