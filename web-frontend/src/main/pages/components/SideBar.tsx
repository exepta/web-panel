import '../../../resources/pages/dashboard/sidebar.css'
import {NavLink} from "react-router-dom";

import HomeIcon from '@mui/icons-material/Home';
import StorageIcon from '@mui/icons-material/Storage';
import BarChartIcon from '@mui/icons-material/BarChart';
import EventAvailableIcon from '@mui/icons-material/EventAvailable';
import LeagueTeams from '@mui/icons-material/Groups3';

const SideBar = () => {
    return (
        <div className="side-bar" id="side">
            <ul>
                <li>
                    <NavLink to='/' className="side-link">
                        <b></b>
                        <b></b>
                        <span className="icon-span"><HomeIcon className="icon"/></span>
                        <span className="side-text">Dashboard</span>
                    </NavLink>
                </li>
                <li>
                    <NavLink to='stats' className='side-link'>
                        <b></b>
                        <b></b>
                        <span className="icon-span"><BarChartIcon className="icon"/></span>
                        <span className="side-text">Stats</span>
                    </NavLink>
                </li>
                <li>
                    <NavLink to='league-teams' className='side-link'>
                        <b></b>
                        <b></b>
                        <span className="icon-span"><LeagueTeams className="icon"/></span>
                        <span className="side-text">League Teams</span>
                    </NavLink>
                </li>
                <li>
                    <NavLink to='events' className='side-link'>
                        <b></b>
                        <b></b>
                        <span className="icon-span"><EventAvailableIcon className="icon"/></span>
                        <span className="side-text">Events</span>
                    </NavLink>
                </li>
                <li>
                    <NavLink to='private-servers' className='side-link'>
                        <b></b>
                        <b></b>
                        <span className="icon-span"><StorageIcon className="icon"/></span>
                        <span className="side-text">Private Server (Beta)</span>
                    </NavLink>
                </li>
            </ul>
        </div>
    );
}

export default SideBar;