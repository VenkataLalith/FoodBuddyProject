import React, { useState } from 'react';
import { useNavigate } from "react-router-dom";
import axios from 'axios';
import Constants from '../Constants/Constants';
import { useDispatch, useSelector } from 'react-redux';
import { loginAction } from '../redux/actions/LoginLogoutAction'
import { updateLoginStatusAction } from '../redux/actions/LoginLogoutAction'
// import Routes from './Routes';
// import * as React from 'react';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import FormControlLabel from '@mui/material/FormControlLabel';
import Checkbox from '@mui/material/Checkbox';
import Link from '@mui/material/Link';
import Paper from '@mui/material/Paper';
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import Typography from '@mui/material/Typography';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import logo from "../Images/download.png"
import ResponsiveAppBar from './Navbar';

const theme = createTheme();
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
        console.log(`Submitted form with username: ${username},  password: ${password}`);
    
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
            dispatch(updateLoginStatusAction(true))
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
    <>
    {/* <ResponsiveAppBar/> */}
    {/* // <Routing />
    // <div className='mx-auto'>
    //   <div style={{ marginTop: '64%' }}>
    //     <div className='Form-Container'>
    //       <form className='form'>

    //         <label> Username</label>
    //         <input className='input' placeholder='Please enter UserName' value={username} onChange={e => setUsername(e.target.value)}></input>

    //         <label> Password</label>
    //         <input className='input' type="password" placeholder='Please enter Password' value={password} onChange={e => setPassword(e.target.value)}></input>
    //       </form>
    //       <button type='submit' className='input1' onClick={submitForm}>Submit</button>
    //       <p className='mt-4'>
    //         Don't have an account?{" "}
    //         <span className='link' onClick={handleClick}>
    //           Sign up
    //         </span>
    //       </p>
    //     </div>
    //   </div>
    // </div> */}
    <ThemeProvider theme={theme}>
      <Grid container component="main" sx={{ height: '100vh' }}>
        <CssBaseline />
        <Grid
          item
          xs={false}
          sm={4}
          md={7}
          sx={{
            backgroundImage: `url(${logo})`,
            backgroundRepeat: 'no-repeat',
            backgroundColor: (t) =>
              t.palette.mode === 'light' ? t.palette.grey[50] : t.palette.grey[900],
            backgroundSize: 'cover',
            backgroundPosition: 'center',
          }}
        />
        <Grid item xs={12} sm={8} md={5} component={Paper} elevation={6} square>
          <Box
            sx={{
              my: 8,
              mx: 4,
              display: 'flex',
              flexDirection: 'column',
              alignItems: 'center',
            }}
          >
            <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
              <LockOutlinedIcon />
            </Avatar>
            <Typography component="h1" variant="h5">
              Sign in
            </Typography>
            <Box component="form" noValidate  sx={{ mt: 1 }}>
              <TextField onChange={e => setUsername(e.target.value)}
                margin="normal"
                required
                fullWidth
                id="username"
                label="User Name"
                name="username"
                autoComplete="username"
                autoFocus
              />
              <TextField onChange={e => setPassword(e.target.value)}
                margin="normal"
                required
                fullWidth
                name="password"
                label="Password"
                type="password"
                id="password"
                autoComplete="current-password"
              />
              {/* <FormControlLabel
                control={<Checkbox value="remember" color="primary" />}
                label="Remember me"
              /> */}
              <Button onClick={submitForm}
                type="submit"
                fullWidth
                variant="contained"
                sx={{ mt: 3, mb: 2 }}
              >
                Sign In
              </Button>
              <Grid container>
                {/* <Grid item xs>
                  <Link href="#" variant="body2">
                    Forgot password?
                  </Link>
                </Grid> */}
                <Grid item>
                  <Link  variant="body2" onClick={handleClick}>
                    {"Don't have an account? Sign Up"}
                  </Link>
                </Grid>
              </Grid>
              {/* <Copyright sx={{ mt: 5 }} /> */}
            </Box>
          </Box>
        </Grid>
      </Grid>
    </ThemeProvider>
    </>
  )
}  