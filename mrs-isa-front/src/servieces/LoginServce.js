import axios from 'axios';

import Config from "../config.json";

const LOGIN_URL = Config.BASE_URL + '/book/site/login';

class LoginServce{
    login(user){
        console.log(JSON.stringify(user));
        return axios.post(LOGIN_URL, user);
    }
}

export default new LoginServce();
