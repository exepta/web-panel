import axios from "axios";
import authHeader from "./auth.header";

const REST_BASE_URL = "http://localhost:8080/api/rest/";

class AuthUrlService {

    getDashboard() {
        return axios.get(REST_BASE_URL + "dashboard", {headers: authHeader()});
    }

}

export default new AuthUrlService();