
import './App.css';
import { Register } from './Components/Register';
import { Login } from './Components/Login';
import axios from 'axios';

function App() {
  axios.interceptors.request.use(config => {
    config.headers['Origin'] = 'http://localhost:3000';
    return config;
  });
  return (
    <div className="App">
     {/* <Login/> */}
       <Register/> 
    </div>
  );
}

export default App;
