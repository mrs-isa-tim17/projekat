import axios from 'axios';

import Config from "../config.json";

const AUTH_URL = Config.BASE_URL + '/book/site';

class LoginServce{
    login(user){
        return axios.post(AUTH_URL + "/login", user)
                .then((response) =>{
                    if (response.data.accessToken){
                        localStorage.user = JSON.stringify(response.data); //setItem('user', response.data);
                    }
                    return response.data;

                    })

            .catch(function (error) {
                console.log(error);
                if (error.response) {
                    // The request was made and the server responded with a status code
                    // that falls out of the range of 2xx
                    console.log(error.response.data);
                    console.log(error.response.status);
                    console.log(error.response.headers);
                } else if (error.request) {
                    // The request was made but no response was received
                    // `error.request` is an instance of XMLHttpRequest in the browser and an instance of
                    // http.ClientRequest in node.js
                    console.log(error.request);
                } else {
                    // Something happened in setting up the request that triggered an Error
                    console.log('Error', error.message);
                }
                console.log(error.config);
            });
    }
    logout(){
        localStorage.removeItem('user');
    }
}

export default new LoginServce();
