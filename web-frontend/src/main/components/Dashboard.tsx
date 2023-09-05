import App from "../App";
import {isAuthenticated} from "../services/AuthService";
import {useEffect, useState} from "react";

const Dashboard = () => {

    const [allowed, setAllowed] = useState(false);

    useEffect(() => {
        async function check() {
            const value = await isAuthenticated();
            setAllowed(value);
        }

        if(!allowed) {
            check().catch(error => { });
        }

    }, [allowed]);

    return (
        <>
            { allowed
                ?
                <div>Welcome...</div>
                :
                <App/>
            }
        </>
    );
}

export default Dashboard;