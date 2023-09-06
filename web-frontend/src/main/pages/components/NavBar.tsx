import '../../../resources/pages/dashboard/navbar.css'
import avatarImage from '../../../resources/images/test.png';

import MenuIcon from '@mui/icons-material/Menu';
import SearchIcon from '@mui/icons-material/Search';

const NavBar = () => {
    // Todo: give via props username and role
    return (
        <header>
            <div className="h-left">
                <div className="menu-box">
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
                        <h4>Exepta</h4>
                        <h6>Administrator</h6>
                    </div>
                    <div className="avatar-background">
                        <img src={avatarImage} alt=""/>
                    </div>
                </div>
            </div>
        </header>
    );
}

export default NavBar;