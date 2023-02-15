import React,{useState} from 'react';

export const Login = () => {
   const [password, setPassword] = useState('');
    const [username, setUsername] = useState('');

    const submitForm = (event) =>{
      event.preventDefault();
      console.log(`Submitted form with username: ${username},and password: ${password}`);
  }
    return(
      <div className='Form-Container'>  
      <form className='form'>
             
     <label> Username</label>
       <input className='input'  placeholder='Please enter UserName' value={username} onChange={e => setUsername(e.target.value)}></input>
       
       <label> Password</label>
       <input className='input'  placeholder='Please enter Password' value={password} onChange={e => setPassword(e.target.value)}></input>
   
     </form>
     <button type='submit' className='input1' onClick={submitForm}>Submit</button>
      </div>
    )
    }  