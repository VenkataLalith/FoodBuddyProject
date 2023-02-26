import React, { useState } from 'react';
import { useNavigate } from "react-router-dom";

// import Routes from './Routes';

export const Login = () => {
  const [password, setPassword] = useState('');
  const [username, setUsername] = useState('');
  // const navigate = useNavigate();
  const navigate = useNavigate();
  const handleClick = () => navigate('/register');

  const submitForm = (event) => {
    event.preventDefault();
    console.log(`Submitted form with username: ${username},and password: ${password}`);
  }
  // const callLoginapi = (formData) => {
  //   axios.post('/api/v1/login', formData)
  //     .then(response => {
  //       console.log(response);
  //     })
  //     .catch(error => {
  //       console.log(error);
  //     });
  // };
  // const gotoSignUpPage = () => navigate("/register");

  return (
    <div className='mx-auto'>
      <div style={{ marginTop: '64%' }}>
        <div className='Form-Container'>
          <form className='form'>

            <label> Username</label>
            <input className='input' placeholder='Please enter UserName' value={username} onChange={e => setUsername(e.target.value)}></input>

            <label> Password</label>
            <input className='input' placeholder='Please enter Password' value={password} onChange={e => setPassword(e.target.value)}></input>

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