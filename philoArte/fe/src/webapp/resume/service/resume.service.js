import axios from 'axios';

const loginInfo = 
    typeof window !== 'undefined' ? JSON.parse(localStorage.getItem('artist')) : null;


const resumeList = (page) => axios.get(`http://localhost:8080/resume/list_page?page=` + page,
        );
const resumeRegister = (data) =>axios.post("/api/resume/register",data,
        );
const resumeModify = (data) => axios.put(`/api/resume/edit`,data);
const resumeRead = (resumeId) =>axios.get(`/api/resume/read/` + resumeId);
const resumeDelete = (resume) =>axios.delete(`/api/resume/delete`,{data: {...resume}});

const resumeSearch = (param) => {
    const str = "page=" + (!param.page ? 1 : param.page) +"&type="+ (encodeURIComponent(param.type)) +"&keyword=" + (param.keyword)
    return axios.get(`/api/resume/search?` + str) }

const countResume = (artistId) => axios.get(`/api/resume/count/${artistId}`)

const categoryList = () => axios.get(`/api/category/findall`);
const uploadFile = (formData) => axios.post("/api/resume_file/upload_file",formData,
        {headers: { "Content-Type": "multipart/form-data"}})
export default {resumeList, resumeRegister, resumeModify, resumeRead, resumeDelete , resumeSearch, countResume, categoryList, uploadFile}