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
    async(resume) =>{
        const response = await ResumeService.resumeRegister(resume)
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
    async (resume) =>{
        const response = await ResumeService.resumeModify(resume);
        return response.data;
    }
)

export const deleteResume = createAsyncThunk(
    "resume/delete",
    async (resumeId) => {
        const response = await ResumeService.resumeDelete(resumeId)
        return response.data;
    }
)

export const searchResume = createAsyncThunk(
    "resume/search",
    async (param) => {
        console.log("pppppppaaaagggggeeeeee",param.page)
        const response = await ResumeService.resumeSearch(param)
        return response.data;
    }
)
export const countResume = createAsyncThunk(
    "resume/count",
    async (artistId) => {
        console.log("pppppppaaaagggggeeeeee",artistId)
        const response = await ResumeService.resumeSearch(artistId)
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
        category:{},
        fileList:[],
        type: "",
        keyword:"",
    },
    
    reducers: {
        changeSearch : (state, {payload}) =>{
        state.type = payload.type
        state.keyword = payload.keyword
    },
        addFileList : (state, {payload}) => {
            state.fileList.push(payload)
        },
        delFileList : (state, {payload}) => {
            state.fileList.filter((fileList) => fileList.uuid !== payload.uuid)
        },
        changeFileList : (state, {payload}) => {
            const filteredFiles= state.fileList?.filter(f => f.uuid !== payload.file.uuid)
            filteredFiles.push({uuid:payload.file.uuid, file:payload.file })
            state.fileList = filteredFiles
        },
    },
    extraReducers: (builder) => {
        builder
        .addCase(listResume.fulfilled, (state,{payload}) => {
            state.pageResult = payload
        })
        .addCase(registerResume.fulfilled,(state,{payload}) => {
            state.resume.push(...payload)
        })
        .addCase(readResume.fulfilled,(state,{payload}) => {
            state.current = payload
        })
        .addCase(modifyResume.fulfilled,(state,{payload}) => {
            const resume = state.findIndex(resume => resume.resumeId == payload)
            return resume ? {...payload} : "선택하신 포트폴리오는 없는 포트폴리오입니다." + resume.resumeId;
        })
        .addCase(deleteResume.fulfilled,(state,{payload}) => {
            return state.filter(resume => resume.resumeId == payload)
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