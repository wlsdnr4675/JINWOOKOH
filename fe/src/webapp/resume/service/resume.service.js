import axios from 'axios';

const resumeList = (page) => axios.get(`http://localhost:8080/resume/list_page?page=` + page);
const resumeRegister = (resume) =>axios.post(`http://localhost:8080/resume/register`,resume);
const resumeModify = (resume) =>axios.put(`http://localhost:8080/resume/edit`);
const resumeRead = (resumeId) =>axios.get(`http://localhost:8080/resume/read/` + resumeId);
const resumeDelete = (resume) =>axios.delete(`http://localhost:8080/resume/delete`);
const resumeSearch = (param) => {
    const str = "page=" + (!param.page ? 1 : param.page) +"&type="+ (encodeURIComponent(param.type)) +"&keyword=" + (param.keyword)
    return axios.get(`http://localhost:8080/resume/search?` + str) }
const countResume = (artistId) => axios.get(`http://localhost:8080/resume/count/${artistId}`)

const categoryList = () => axios.get(`http://localhost:8080/category/findall`);
const uploadFile = (formData) => axios.post("http://localhost:8080/resume_file/upload_file",formData,
        {headers: { "Content-Type": "multipart/form-data"}})
export default {resumeList, resumeRegister, resumeModify, resumeRead, resumeDelete , resumeSearch, countResume, categoryList, uploadFile}