import '../../resources/pages/auth/auth.css';
import {useState} from "react";

import GoogleIcon from '@mui/icons-material/Google';
import GitHubIcon from '@mui/icons-material/GitHub';
import TwitterIcon from '@mui/icons-material/Twitter';

import MailOutlineIcon from '@mui/icons-material/MailOutline';
import LockIcon from '@mui/icons-material/Lock';

const Authentication = () => {

    const [swap, setSwap] = useState(false);

    return (
        <>
            {
                swap ?
                    <section id="section-reg">
                        <div className="sec-header">
                            <h1>Welcome</h1>
                        </div>
                    </section>

                    :

                    <section id="section-log">
                        <div className="sec-header">
                            <h1>Welcome</h1>
                        </div>

                        <form>
                            <div className="input-con">
                                <div className="icon-box">
                                    <MailOutlineIcon className="icon" />
                                </div>
                                <input
                                    type="email"
                                    id="log-email"
                                    placeholder="E-Mail"
                                    required
                                    autoComplete="off"
                                />
                            </div>

                            <div className="input-con">
                                <div className="icon-box">
                                    <LockIcon className="icon" />
                                </div>
                                <input
                                    type="password"
                                    id="log-email"
                                    placeholder="Password"
                                    required
                                    autoComplete="off"
                                />
                            </div>

                            <div className="text-con">
                                <p>Forgot password?</p>
                            </div>

                            <button type="submit" className="send">Login</button>

                            <h3>You can use Social login's</h3>

                            <div className="social-box">
                                <div className="social-icon google">
                                    <GoogleIcon />
                                </div>

                                <div className="social-icon twitter">
                                    <TwitterIcon />
                                </div>

                                <div className="social-icon github">
                                    <GitHubIcon />
                                </div>
                            </div>
                        </form>
                    </section>
            }
        </>
    );
};

export default Authentication