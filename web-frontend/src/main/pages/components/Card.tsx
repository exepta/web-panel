import '../../../resources/pages/element/card.css'
import teamImage from "../../../resources/images/pga.png";
import React from "react";

const Card = (props :any) => {

    if(props.team === undefined) {
        return (<div>Loading...</div>);
    }

    return (
        <>
            <div className="card card-400 l-team-card">
                <div className="card-header">
                    <h3>{props.team.name}</h3>
                    <span>{props.team.prefix}</span>
                </div>
                <div className="card-content">
                    <img src={teamImage} alt=""/>
                </div>
                <div className="card-footer" style={{"background": props.team.teamColor} as React.CSSProperties}></div>
            </div>
        </>
    );
}

export default Card;