import {combineReducers, configureStore, getDefaultMiddleware} from "@reduxjs/toolkit";
import users from 'webapp/user/reducer/user.reducer'

import logger from "redux-logger";

const rootReducer = combineReducers({users});

export default configureStore({
    reducer: rootReducer,
    middleware: [...getDefaultMiddleware(), logger],
});