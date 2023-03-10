import { combineReducers } from "redux";
import { LoginLogoutReducer } from "./LoginLogoutReducer";

const reducers = combineReducers({
    loginLogoutReducer : LoginLogoutReducer
})

export default reducers