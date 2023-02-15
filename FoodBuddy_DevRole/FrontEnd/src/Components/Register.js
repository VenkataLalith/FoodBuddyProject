import React, { useState } from 'react';

export const Register = () => {
    const [name, setName] = useState('');
    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const submitForm = (event) =>{
        event.preventDefault();
        console.log(`Submitted form with name: ${name}, username: ${username}, email: ${email}, and password: ${password}`);
    }
    

    return(
        <div className='Form-Container'>  
       <form className='form'>
        <label> Name</label>
        <input className='input'  placeholder='Please enter your name' value={name} onChange={e => setName(e.target.value)}></input>
       
       
      <label> Username</label>
        <input className='input'  placeholder='Please enter UserName' value={username} onChange={e => setUsername(e.target.value)}></input>
        
        <label> Password</label>
        <input className='input'  placeholder='Please enter Password' value={password} onChange={e => setPassword(e.target.value)}></input>
       
        <label> Email</label>
        <input className='input'  placeholder='Please enter your name' value={email} onChange={e => setEmail(e.target.value)}></input>
      </form>
      <button type='submit' className='input1' onClick={submitForm}>Submit</button>
       </div>
    )
    }  