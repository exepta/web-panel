import '../../../resources/pages/dashboard/league-teams.css'
import teamImage from '../../../resources/images/pga.png';

import {getTeams} from "../../services/LeagueService";

import AddIcon from '@mui/icons-material/Add';
import React, {useEffect, useState} from "react";
const LeagueTeamsPage = () => {

    const [teams, setTeams] :any = useState();
    const [caught, setCaught] = useState(false);

    useEffect(() => {
        async function check() {
            const value :any = await getTeams();
            setTeams(value);
            setCaught(true);
        }

        if(!caught) {
            check().catch(error => { });
        }


    }, [caught]);

    if(teams === undefined)
    {
        return (<div>Loading...</div>);
    }

    return (
        <>
            <div className="card card-400 blank-card">
                <div className="add-button">
                    <AddIcon className="add-icon"/>
                </div>
            </div>
            {
                teams.map((team :any, key :any) => {
                    return (
                        <>
                            <div key={key} className="card card-400 l-team-card">
                                <div className="card-header">
                                    <h3>{team.name}</h3>
                                    <span>{team.prefix}</span>
                                </div>
                                <div className="card-content">
                                    <img src={teamImage} alt=""/>
                                </div>
                                <div className="card-footer" style={{"background": team.teamColor} as React.CSSProperties}></div>
                            </div>
                        </>
                    );
                })
            }
        </>
    );
}

export default LeagueTeamsPage;