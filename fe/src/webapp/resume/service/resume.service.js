import axios from 'axios';

const resumeList = () =>{ axios.get(`http://localhost:8080/resume/list`)};
const resumeRegister = () =>{axios.post(`http://localhost:8080/resume/register`)};
const resumeModify = () =>{axios.put(`http://localhost:8080/resume/edit`)};
const resumeRead = (resumeId) =>{axios.get(`http://localhost:8080/resume/read/${resumeId}`)};
const resumeDelete = () =>{axios.delete(`http://localhost:8080/resume/delete`)};

export default {resumeList, resumeRegister, resumeModify, resumeRead, resumeDelete}; 