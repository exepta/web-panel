import {isAuthenticated} from "../services/AuthService";
import {useEffect, useState} from "react";
import NavBar from "./components/NavBar";
import {useNavigate} from "react-router-dom";

const Dashboard = () => {

    const navigate = useNavigate();
    const [allowed, setAllowed] = useState(false);

    useEffect(() => {
        async function check() {
            const value = await isAuthenticated();
            if(!value) {
                navigate('auth');
            }
        }

        if(!allowed) {
            check().catch(error => { });
        }

    }, [allowed]);

    return (
        <>
            <NavBar />
        </>
    );
}

export default Dashboard;