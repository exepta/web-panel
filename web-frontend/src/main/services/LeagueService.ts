import axios from "axios";

export const REST_POINT = 'http://localhost:8080/api/v0/league/';

export const getTeam = async ( uid:string ) => {
    let result = undefined;
    const key = localStorage.getItem('JWT');
    if(uid === null || key === null)
    {
        return false;
    }

    await axios.get(REST_POINT + `team/${uid}`,
        { headers:
                {
                'Authorization': key,
                'Accept': 'application/json',
                'Access-Control-Allow-Origin': '*',
                'Content-Type': 'application/json'
                }
        }).then( response => {
            result = response.data;
            console.log(result)
    }).catch( error => {
        if(error.response)
        {
            console.log('error');
        }
        else
        {
            console.log('Need more handling!');
        }
    });

    return result;
}

export const getTeams = async() => {
    let result = undefined;
    const key = localStorage.getItem('JWT');
    if(key === null)
    {
        return false;
    }

    await axios.get(REST_POINT + 'teams',
        { headers:
                {
                    'Authorization': key,
                    'Accept': 'application/json',
                    'Access-Control-Allow-Origin': '*',
                    'Content-Type': 'application/json'
                }
        }).then( response => {
        result = response.data;
        console.log(result)
    }).catch( error => {
        if(error.response)
        {
            console.log('error');
        }
        else
        {
            console.log('Need more handling!');
        }
    });

    return result;
}