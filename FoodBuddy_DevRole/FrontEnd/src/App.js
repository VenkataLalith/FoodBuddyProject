
import './App.css';
import { Register } from './Components/Register';
import { Login } from './Components/Login';
import {Routing} from './Components/Routes.js'
import { BrowserRouter } from 'react-router-dom';
import axios from 'axios';

function App() {
  axios.interceptors.request.use(config => {
    config.headers['Origin'] = 'http://localhost:3000';
    return config;
  });
  return (
    <div className="App">
       <BrowserRouter>
       <Routing /> 
       </BrowserRouter>
      
     {/* <Login/> */}
       {/* <Register/>  */}
    </div>
  );
}

export default App;
