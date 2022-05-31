import axios from 'axios';

import Config from "../config.json";
import authHeader from "@/servieces/authHeader";

const USER_API_BASE_URL = Config.BASE_URL + '/user';

class UserService{
    getAllUsers(){
        return axios.get(USER_API_BASE_URL+'/all', {
            headers: authHeader()
        });
    }
    deleteUser(id){
        return axios.get(USER_API_BASE_URL + '/delete/'+id, {
            headers: authHeader()
        });
    }
    getDeletedUsers(){
        return axios.get(USER_API_BASE_URL + '/all/deleted/', {
            headers: authHeader()
        });
    }
}
export default new UserService();