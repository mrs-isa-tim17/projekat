import axios from 'axios';
import Config from "../config.json";
import authHeader from "@/servieces/authHeader";

const REGISTRATION_REQUEST_API_BASE_URL = Config.BASE_URL + '/request/registration';

class RegistrationRequestService{
    getUnapprovedRequest(){
        return axios.get(REGISTRATION_REQUEST_API_BASE_URL + '/unprocessed', {
            headers: authHeader()
        });
    }

    approveRegistration(id){
        return axios.get(REGISTRATION_REQUEST_API_BASE_URL + '/approve/'+id,{
            headers: authHeader()
        });
    }

    rejectRegistration(id, text){
        return axios.post(REGISTRATION_REQUEST_API_BASE_URL + '/reject/'+id, text,{
            headers: authHeader()
        });
    }
}
export default new RegistrationRequestService();