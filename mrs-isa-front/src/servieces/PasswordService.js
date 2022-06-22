import axios from 'axios';

import Config from "../config.json";
import authHeader from "@/servieces/authHeader";

const PASSWORD_URL = Config.BASE_URL + '/book/site';

class PasswordService {
    matchPassword(passwords, id){
        console.log("kredencijali");
        console.log(id);
        console.log(passwords);
        return axios.post(PASSWORD_URL+'/password/matches/' +id,passwords,{
            headers:authHeader()
        });
    }

}

export default new PasswordService();