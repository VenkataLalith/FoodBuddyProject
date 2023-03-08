import { Register } from "./Register";
// import ReactDOM from "react-dom/client";
// import { BrowserRouter, Routes, Route } from "react-router-dom";
import { Route, Routes } from 'react-router-dom';

import { Login } from "./Login";
import { GroupManagement } from "./GroupManagement";


export function Routing() {
    return (
        <>
            <Routes>
                <Route path='/' element={<Login />} />
                <Route path='/register' element={<Register />} />
                <Route path='/grp' element={<GroupManagement />} />
            </Routes>
        </>
    );


}



// export default Routing;