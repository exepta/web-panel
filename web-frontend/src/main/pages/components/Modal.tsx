import React from "react";

import avatarImage from '../../../resources/images/pga.png';

import FirstPageIcon from '@mui/icons-material/FirstPage';
import AddIcon from '@mui/icons-material/Add';
import CreateIcon from '@mui/icons-material/Create';

const Modal = (props :any) => {
    return (
        <>
            <div className={props.active ? 'modal-show' : 'modal-hide'}>
                <div className="modal-navigation-bar">
                    <FirstPageIcon className="icon" onClick={props.onBack} />
                    <span>League Team / Create Team</span>
                </div>
                <div className="modal-header">
                    <h1>{props.headline}</h1>
                </div>
                <div className="modal-body">
                    <form action="">
                        <div className="input-container">
                            <h3>Team Name</h3>
                            <input type="text"
                                   id="cre-name"
                                   placeholder="Name"
                                   required
                                   autoComplete="off"
                            />
                        </div>

                        <div className="input-container">
                            <h3>Team Prefix</h3>
                            <input type="text"
                                   id="cre-prefix"
                                   placeholder="Prefix"
                                   required
                                   autoComplete="off"
                            />
                        </div>

                        <div className="input-container">
                            <h3>Team Color</h3>
                            <input type="color"
                                   id="cre-color"
                                   required
                                   autoComplete="off"
                                   placeholder="#c843d2"
                            />
                        </div>

                        <div className="input-extended">
                            <h3>Team Members</h3>
                            <div className="member-box">

                                <div className="member-add">
                                    <AddIcon className="add-icon" />
                                </div>

                            </div>
                        </div>
                    </form>

                    <div className="preview-content">
                        <div className="preview-container">
                            <div className="preview-header">
                                <div className="preview-avatar-box">
                                    <img src={avatarImage} alt=""/>
                                </div>
                                <div className="edit-box">
                                    <CreateIcon className="edit-icon" />
                                </div>
                            </div>
                            <div className="preview-body">

                            </div>
                            <div className="preview-footer">

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </>
    );
}

export default Modal;