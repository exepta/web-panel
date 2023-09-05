import '../../resources/pages/auth/auth.css';
import {useEffect, useRef, useState} from "react";

import GoogleIcon from '@mui/icons-material/Google';
import GitHubIcon from '@mui/icons-material/GitHub';
import TwitterIcon from '@mui/icons-material/Twitter';

import MailOutlineIcon from '@mui/icons-material/MailOutline';
import LockIcon from '@mui/icons-material/Lock';
import PersonIcon from '@mui/icons-material/Person';
import VerifiedUserIcon from '@mui/icons-material/VerifiedUser';

const USER_NAME_PATTERN = /^[a-zA-Z][a-zA-Z0-9-_]{3,25}$/;
const PASSWORD_PATTERN = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)([!@#$%^&*<>/?]).{8,}$/;

const Authentication = () => {

    const emailRef :any = useRef();
    const [swap, setSwap] = useState(false);

    const [email, setEmail] = useState('');
    const [validEmail, setValidEmail] = useState(false);

    const [username, setUsername] = useState('');
    const [validUsername, setValidUsername] = useState(false);

    const [password, setPassword] = useState('');
    const [validPassword, setValidPassword] = useState(false);

    useEffect(() => {
        emailRef.current.focus();
    }, []);

    useEffect(() => {
        const result = email.includes('@');
        setValidEmail(result);
    }, [email]);

    useEffect(() => {
        const result = USER_NAME_PATTERN.test(username);
        setValidUsername(result);
    }, [username]);

    useEffect(() => {
        const result = PASSWORD_PATTERN.test(password);
        setValidPassword(result);
    }, [password]);

    const handleLogin :any = async (event :Event) => {
        event.preventDefault();

        const checkMail = email.includes('@');
        const checkPassword = PASSWORD_PATTERN.test(password);
        if(!checkMail || !checkPassword) {
            return;
        }

        //Todo: Fetch here
    }

    const handleRegister :any = async (event :Event) => {
        event.preventDefault();
    }

    return (
        <>
            {
                swap ?
                    <section id="section-reg">
                        <div className="sec-header">
                            <h1>Welcome</h1>
                        </div>

                        <form onSubmit={handleRegister}>
                            <div className="input-con">
                                <div className="icon-box">
                                    <MailOutlineIcon className="icon" />
                                </div>
                                <input
                                    type="email"
                                    id="reg-email"
                                    ref={emailRef}
                                    placeholder="E-Mail"
                                    required
                                    autoComplete="off"
                                    onChange={(event) => setEmail(event.target.value)}
                                />
                            </div>

                            <div className="input-con">
                                <div className="icon-box">
                                    <PersonIcon className="icon" />
                                </div>
                                <input
                                    type="text"
                                    id="reg-user"
                                    placeholder="Username"
                                    required
                                    autoComplete="off"
                                    onChange={(event) => setUsername(event.target.value)}
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
                                    onChange={(event) => setPassword(event.target.value)}
                                />
                            </div>

                            <div className="text-con">
                                <p>Problems by creating new account?</p>
                            </div>

                            <button type="submit" className="send">Sign Up</button>

                            <h3>Already a member or social login?</h3>
                            <div className="switch" onClick={event => {setSwap(false)}}>Login</div>
                        </form>
                        <div className="sec-footer">
                            <div className="container">
                                <p>Impress</p>
                            </div>
                        </div>
                    </section>

                    :

                    <section id="section-log">
                        <div className="sec-header">
                            <h1>Welcome</h1>
                        </div>

                        <form onSubmit={handleLogin}>
                            <div className="input-con">
                                <div className="icon-box">
                                    <MailOutlineIcon className="icon" />
                                </div>
                                <input
                                    type="email"
                                    id="log-email"
                                    ref={emailRef}
                                    placeholder="E-Mail"
                                    required
                                    autoComplete="off"
                                    onChange={(event) => setEmail(event.target.value)}
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
                                    onChange={(event) => setPassword(event.target.value)}
                                />
                            </div>

                            <div className="text-con">
                                <div className="remember">
                                    <VerifiedUserIcon className="check"/>
                                    <p>Remember me</p>
                                </div>
                                <p className="forgot">Forgot password?</p>
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

                            <h3>Or</h3>
                            <div className="switch" onClick={event => {setSwap(true)}}>Sign Up</div>
                        </form>
                        <div className="sec-footer">
                            <div className="container">
                                <p>Impress</p>
                            </div>
                        </div>
                    </section>
            }
        </>
    );
};

export default Authentication