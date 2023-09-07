import {getUser} from "../services/AuthService";
import {useEffect, useState} from "react";
import NavBar from "./components/NavBar";
import {Outlet, useNavigate} from "react-router-dom";
import SideBar from "./components/SideBar";

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
            <SideBar />
            <Outlet />
        </>
    );
}

export default Dashboard;