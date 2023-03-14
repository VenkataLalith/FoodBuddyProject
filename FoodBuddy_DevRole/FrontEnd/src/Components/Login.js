import React, { useState } from 'react';
import { useNavigate } from "react-router-dom";
import axios from 'axios';
import Constants from '../Constants/Constants';
import { useDispatch, useSelector } from 'react-redux';
import { loginAction } from '../redux/actions/LoginLogoutAction'
import { updateLoginStatusAction } from '../redux/actions/LoginLogoutAction'
// import Routes from './Routes';

export const Login = () => {
const handleUsernameChange = (event) => {
    setUsername(event.target.value);
  };
  const handlePasswordChange = (event) => {
    setPassword(event.target.value);
  };

  const [password, setPassword] = useState('');
  const [username, setUsername] = useState('');
  const dispatch = useDispatch();
  // const navigate = useNavigate();
  const navigate = useNavigate();
  const handleClick = () => navigate('/register');

  const submitForm = (event) => {
    event.preventDefault();
    const formData ={
        username: username,
        password: password
        };
    callLoginApi(formData);
    setUsername("");
    setPassword("");
  }
   const callLoginApi = (formData) => {
     axios.post('/api/v1/login', formData)
       .then(response => {
         if(response.data==='success'){
            console.log('login success')
            dispatch(loginAction(username))
            navigate('/grp');
         }
         else{
            alert('Invalid Credentials.....pls re-enter proper credentials')
         }
       })
       .catch(error => {
         console.log(error);
       });
   };

  // const gotoSignUpPage = () => navigate("/register");

  return (
    <div className='mx-auto'>
      <div style={{ marginTop: '64%' }}>
        <div className='Form-Container'>
          <form className='form'>

            <label> Username</label>
            <input className='input' placeholder='Please enter UserName' value={username} onChange={e => setUsername(e.target.value)}></input>

            <label> Password</label>
            <input className='input' type="password" placeholder='Please enter Password' value={password} onChange={e => setPassword(e.target.value)}></input>
          </form>
          <button type='submit' className='input1' onClick={submitForm}>Submit</button>
          <p className='mt-4'>
            Don't have an account?{" "}
            <span className='link' onClick={handleClick}>
              Sign up
            </span>
          </p>
        </div>
      </div>
    </div>
  )
}  