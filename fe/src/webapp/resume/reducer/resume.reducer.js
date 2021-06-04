import { Satellite } from "@material-ui/icons";
import{ createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import {ResumeService} from "webapp/resume/index";


export const listResume = createAsyncThunk(
    "resume/list",
    async (page) =>{
        const response = await ResumeService.resumeList(page);
        return response.data;
    }
)
export const registerResume = createAsyncThunk(
    "resume/register",
    async(data) =>{
        const response = await ResumeService.resumeRegister(data)
        return response.data;
    }
)

export const readResume = createAsyncThunk(
    "resume/read",
    async(resumeId) =>{
        const response = await ResumeService.resumeRead(resumeId);
        return response.data;
    }
)

export const modifyResume = createAsyncThunk(
    "resume/modify",
    async (data) =>{
        const response = await ResumeService.resumeModify(data);
        return response.data;
    }
)

export const deleteResume = createAsyncThunk(
    "resume/delete",
    async (data) => {
        const response = await ResumeService.resumeDelete(data)
        return response.data;
    }
)

export const searchResume = createAsyncThunk(
    "resume/search",
    async (param) => {
        const response = await ResumeService.resumeSearch(param)
        return response.data;
    }
)
export const countResume = createAsyncThunk(
    "resume/count",
    async (artistId) => {
        const response = await ResumeService.countResume(artistId)
        return response.data;
    }
)

export const listCategory = createAsyncThunk(
    "category/list",
    async () => {
        const response = await ResumeService.categoryList()
        return response.data;
    }
)


const isRejectedAction = action => (action.type.endsWith('rejected'))

const resumeSlice = createSlice({
    name: 'resumes',
    initialState: {
        pageResult:{
            dtoList:[],
            page: 1,
            pageList: [],
            start:1,
            end:1,
            prev:false,
            next:false,
        },
        count:{},
        crudResult:{},
        category:[],
        fileList:[],
        current:{
            resumeFiles:[]
        },
        type: "",
        keyword:"",
    },
    
    reducers: {
        changeSearch : (state, {payload}) =>{
        state.type = payload.type
        state.keyword = payload.keyword
    },
        addFileList : (state, {payload}) => {
            state.current.resumeFiles.push(payload)
        },
        delFileList : (state, {payload}) => {
            const idx = state.current.resumeFiles.findIndex((file) => {
                console.log("................" + file.uuid, payload.uuid)
                    return file.uuid === payload.uuid 
            })
            console.log("payload", payload)
            console.log("findFile: ", idx)
            state.current.resumeFiles.splice(idx,1)
        },
        changeFileList : (state, {payload}) => {
            console.log("changeFileList payload: " , payload)
            const filteredFiles= state.current.resumeFiles.filter(f => f.uuid !== payload.file.uuid)
            filteredFiles.push({uuid:payload.file.uuid, file:payload.file })
            state.current.resumeFiles = filteredFiles
        },
    },
    extraReducers: (builder) => {
        builder
        .addCase(listResume.fulfilled, (state,{payload}) => {
            state.pageResult = payload
        })
        .addCase(registerResume.fulfilled,(state,{payload}) => {
            state.crudResult = payload
        })
        .addCase(readResume.fulfilled,(state,{payload}) => {
            state.current = payload
        })
        .addCase(modifyResume.fulfilled,(state,{payload}) => {
            const resume = state.findIndex(resume => resume.resumeId == payload)
            return resume ? {...payload} : "선택하신 포트폴리오는 없는 포트폴리오입니다." + resume.resumeId;
        })
        .addCase(deleteResume.fulfilled,(state,{payload}) => {
            console.log("payload```````````````````````````",payload)
            state.crudResult = payload

        })
        .addCase(searchResume.fulfilled,(state,{payload}) => {
            state.pageResult = payload;
        })
        .addCase(countResume.fulfilled,(state,{payload}) => {
            state.count = payload;
        })
        .addCase(listCategory.fulfilled,(state,{payload}) => {
            state.category = payload;
        })
  
        
    
        .addMatcher(isRejectedAction).addDefaultCase()
        .addDefaultCase((state, payload)=>{})
    }
})

const {actions, reducer} = resumeSlice;
export const { changeSearch, addFileList, delFileList, changeFileList} = actions;
export default reducer;