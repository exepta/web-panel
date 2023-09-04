export default function authHeader() {
    let json = localStorage.getItem('user');
    if(json === null) { json = ""; }
    const user = JSON.parse(json);

    if(user && user.accessToken)
        return { Authorization: 'Bearer ' + user.accessToken };

    return {};
}