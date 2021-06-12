import { combineReducers, configureStore, getDefaultMiddleware } from '@reduxjs/toolkit';
import logger from 'redux-logger';
import artistsSlice from 'webapp/artist/reducer/artist.reducer';
import reviewSlice from 'webapp/review/reducer/review.reducer';
import resumes from 'webapp/resume/reducer/resume.reducer';
import arts from 'webapp/art/reducer/art.reducer';
import fundings from 'webapp/funding/reducer/funding.reducer';
import replies from 'webapp/reply/reducer/reply.reducer';


const rootReducer = combineReducers({ artists: artistsSlice, reviews: reviewSlice, resumes, arts, fundings, replies });

//const persistedReducer = persistReducer(persistConfig, rootReducer);

export default configureStore({
    reducer: rootReducer,
    middleware: [...getDefaultMiddleware(), logger],
});
