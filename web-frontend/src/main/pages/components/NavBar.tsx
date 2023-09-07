import '../../../resources/pages/dashboard/profile.css'
import '../../../resources/pages/dashboard/navbar.css'
import avatarImage from '../../../resources/images/test.png';

import MenuIcon from '@mui/icons-material/Menu';
import SearchIcon from '@mui/icons-material/Search';
import NotificationsIcon from "@mui/icons-material/Notifications";
import Brightness4Icon from "@mui/icons-material/Brightness4";
import SettingsIcon from "@mui/icons-material/Settings";
import LogoutIcon from "@mui/icons-material/Logout";

const showSideBar = () => {
    const bar = document.querySelector('.side-bar');
    if(bar !== null) {
        if(!bar.classList.contains('side-bar-open')) {
            bar.classList.add('side-bar-open');
            return;
        }
        bar.classList.remove('side-bar-open');
    }
}

const NavBar = (props :any) => {
    // Todo: give via props username and role

    if(props.user === undefined) {
        return (<div>Loading...</div>);
    }

    let roleName :string = '';
    let firstname :string = props.user.firstname;
    let lastname :string = props.user.lastname;


    switch (props.user.role) {
        case 'USER':
            roleName = 'User';
            break;
        case 'ADMIN':
            roleName = 'Administrator';
            break;
        default:
            roleName = 'waiting...';
    }

    return (
        <header>
            <div className="h-left">
                <div className="menu-box" onClick={event => {showSideBar()}}>
                    <MenuIcon className="menu-icon"/>
                </div>
            </div>
            <div className="h-middle">
                <div className="search-box">
                    <div className="search-icon-box">
                        <SearchIcon className="search-icon"></SearchIcon>
                    </div>
                    <input
                        type="text"
                        id="header-search"
                        placeholder="Search..."
                    />
                </div>
            </div>
            <div className="h-right">
                <div className="avatar-box">
                    <div className="avatar-text-box">
                        <h4>{props.user.username}</h4>
                        <h6>{roleName}</h6>
                    </div>
                    <div className="avatar-background">
                        <img src={avatarImage} alt=""/>
                        <div className="profile-container">
                            <div className="profile-header">

                            </div>
                            <div className="profile-body">
                                <h3>
                                    {
                                    firstname === null && lastname === null
                                    ? props.user.username : firstname + ' ' + lastname
                                    }
                                </h3>
                                <h5>(status)</h5>
                            </div>
                            <div className="profile-footer">
                                <NotificationsIcon className="p-icon"/>
                                <Brightness4Icon className="p-icon" />
                                <SettingsIcon className="p-icon"/>
                                <LogoutIcon className="p-icon"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </header>
    );
}

export default NavBar;