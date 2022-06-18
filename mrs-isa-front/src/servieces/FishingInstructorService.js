import axios from 'axios';
import authHeader from "@/servieces/authHeader";

import Config from "../config.json";

const FISHING_INSTRUCTOR_API_BASE_URL = Config.BASE_URL + '/fishing/instructor';

class FishingInstructorService {
    getOwner(coID) {
        console.log(authHeader());
        return axios.get(FISHING_INSTRUCTOR_API_BASE_URL + '/profile/' + coID, {
            headers: authHeader()
        });
    }

    updateOwner(coID, co) {
        return axios.post(FISHING_INSTRUCTOR_API_BASE_URL + "/update/profile/" + coID, co, {
            headers: authHeader()
        });
    }
}

export default new FishingInstructorService();
