import { Register } from "./Register";
// import ReactDOM from "react-dom/client";
// import { BrowserRouter, Routes, Route } from "react-router-dom";
import { Route, Routes } from 'react-router-dom';

import {Login} from "./Login";


 export function Routing() {
    return(
            <>
            <Routes>
                <Route path='/' element={<Login />} />
                <Route path='/register' element={<Register />} />
            
            </Routes>
        </>
    );

    
    }



// export default Routing;