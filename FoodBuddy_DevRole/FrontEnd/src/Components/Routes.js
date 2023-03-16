import { Register } from "./Register";
// import ReactDOM from "react-dom/client";
// import { BrowserRouter, Routes, Route } from "react-router-dom";
import { Route, Routes } from 'react-router-dom';

import { Login } from "./Login";
import { GroupManagement } from "./GroupManagement";
import { InventoryManagement } from "./InventoryManagement";
import {HomePage} from "./Home";


export function Routing() {
    return (
        <>
            <Routes>
                <Route path='/' element={<Login />} />
                <Route path='/register' element={<Register />} />
                {/* <Route path='/grp' element={<GroupManagement />} />
                <Route path='/inv' element={<InventoryManagement />} /> */}
                <Route path='/home' element={<HomePage />} />
            </Routes>
        </>
    );


}



// export default Routing;