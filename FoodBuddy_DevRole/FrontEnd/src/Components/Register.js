import React, { useState } from 'react';
import axios from 'axios';
import UserData from '../Constants/UserData';
import Constants from '../Constants/Constants';
import { useNavigate } from "react-router-dom";


export const Register = () => {
  const [firstName, setFirstName] = useState(UserData.firstNameame);
  const [lastName, setLastName] = useState(UserData.lastName);
  const [password, setPassword] = useState(UserData.password);
  const [email, setEmail] = useState(UserData.email);
  const navigate = useNavigate();
  const gotoLoginPage = () => navigate("/");

  const submitForm = (event) => {
    event.preventDefault();
    console.log(`Submitted form with firstname: ${firstName}, lastname: ${lastName}, email: ${email}, and password: ${password}`);
    const formData = {
      firstName: firstName,
      lastName: lastName,
      password: password,
      email: email
    };
    callRegisterapi(formData);
  }

  const callRegisterapi = (formData) => {
    axios.post('/api/v1/registration', formData)
      .then(response => {
        console.log(response);
      })
      .catch(error => {
        console.log(error);
      });
  };



  return (
    <div className='mx-auto my-auto'>
      <div style={{ marginTop: '50%' }}>
        <div className='Form-Container'>
          <form className='form'>
            <label> Fisrt Name</label>
            <input className='input' placeholder='Please enter your First Name' value={firstName} onChange={e => setFirstName(e.target.value)}></input>
            <label> Last Name</label>
            <input className='input' placeholder='Please enter Last Name' value={lastName} onChange={e => setLastName(e.target.value)}></input>

            <label> Password</label>
            <input className='input' placeholder='Please enter Password' value={password} onChange={e => setPassword(e.target.value)}></input>

            <label> Email</label>
            <input className='input' placeholder='Please enter your name' value={email} onChange={e => setEmail(e.target.value)}></input>
          </form>
          <button type='submit' className='input1' onClick={submitForm}>Submit</button>
          <p className='mt-4'>
            Already have an account?{" "}
            <span className='link' onClick={gotoLoginPage}>
              Login
            </span>
          </p>
        </div>
      </div>
    </div>
  )
}  