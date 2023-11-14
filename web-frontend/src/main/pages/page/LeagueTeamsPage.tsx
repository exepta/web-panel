import '../../../resources/pages/dashboard/league-teams.css'
import teamImage from '../../../resources/images/pga.png';

import AddIcon from '@mui/icons-material/Add';
export const LeagueTeamsPage = () => {
    return (
        <>
            <div className="card card-400 blank-card">
                <div className="add-button">
                    <AddIcon className="add-icon" />
                </div>
            </div>
            <div className="card card-400 l-team-card">
                <div className="card-header">
                    <h3>Pheanix Gaming Area</h3>
                    <span>PGA</span>
                </div>
                <div className="card-content">
                    <img src={teamImage} alt=""/>
                </div>
                <div className="card-footer"></div>
            </div>
        </>
    );
}