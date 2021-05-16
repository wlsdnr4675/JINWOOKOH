import { combineReducers, configureStore, getDefaultMiddleware } from "@reduxjs/toolkit";
import logger from "redux-logger"
import {resumes} from "webapp/resume/reducer/resume.reduce"

const rootReducer = combineReducers({resumes});

export default configureStore({
    reducer: rootReducer,
    middleware: [...getDefaultMiddleware(), logger],
});