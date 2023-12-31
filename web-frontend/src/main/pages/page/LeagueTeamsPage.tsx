import '../../../resources/pages/dashboard/league-teams.css'
import '../../../resources/pages/element/modal.css'

import {getTeams} from "../../services/LeagueService";
import {REST_POINT} from "../../services/LeagueService";
import axios from "axios";

import AddIcon from '@mui/icons-material/Add';
import React, {useEffect, useState} from "react";
import Card from "../components/Card";
import Modal from "../components/Modal";

const LeagueTeamsPage = () => {

    const [teams, setTeams] :any = useState();
    const [caught, setCaught] = useState(false);

    const [modalAdd, setModalAdd] = useState(false);

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

    const toggleAddModal = () => {
        setModalAdd(!modalAdd);
    }

    return (
        <>
            <div className="card card-400 blank-card" onClick={toggleAddModal}>
                <div className="add-button">
                    <AddIcon className="add-icon"/>
                </div>
            </div>
            {
                teams.map((team :any, key :any) => {
                    return (
                        <>
                            <Card key={key} team={team} />
                        </>
                    );
                })
            }
            <Modal active={modalAdd} headline={"Create Team"} onBack={toggleAddModal}/>
        </>
    );
}

export default LeagueTeamsPage;