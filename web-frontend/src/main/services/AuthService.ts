import axios from "axios";

export const REST_POINT = 'http://localhost:8080/api/v0/auth/';

export const getUser = async () => {
    let login = undefined;
    const key = localStorage.getItem('JWT');
    if(key === null) {
        return false;
    }

    await axios.get(REST_POINT + 'currentUser',
        { headers:
                {
                    'Authorization': key,
                    'Accept': 'application/json',
                    'Access-Control-Allow-Origin': '*',
                    'Content-Type': 'application/json'
                }
        }
    ).then(response => {
        login = response.data;
    }).catch(error => {
        if(error.response) {
            console.log('error')
        } else {
            console.log('Cant connect to server!'); //Todo: better handling.
        }
    });

    return login;
}