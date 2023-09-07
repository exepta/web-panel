import {getUser} from "../services/AuthService";
import {useEffect, useState} from "react";
import NavBar from "./components/NavBar";
import {useNavigate} from "react-router-dom";

const Dashboard = () => {

    const navigate = useNavigate();
    const [caught, setCaught] = useState(false);
    const [user, setUser] = useState();

    useEffect(() => {
        async function check() {
            const value :any = await getUser();
            if(!value) {
                navigate('auth');
            }
            setUser(value);
            setCaught(true);
        }

        if(!caught) {
            check().catch(error => { });
        }

    }, [caught]);

    return (
        <>
            <NavBar user={user}/>

        </>
    );
}

export default Dashboard;