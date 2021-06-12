import axios from 'axios';

const resumeList = (page) => axios.get(`/api/resume/list_page?page=` + page,
        {headers: { Authorization: 'JWT fefege'}
    });
const resumeRegister = (data) =>axios.post("/api/resume/register",data,
        {headers : { Authorization : localStorage.getItem('artist')}
    });
const resumeModify = (data) => axios.put(`/api/resume/edit`,data,
        {headers: { Authorization: 'JWT fefege'}

    });
const resumeRead = (resumeId) =>axios.get(`/api/resume/read/` + resumeId, 
        {headers: { Authorization: 'JWT fefege'}
    });
const resumeDelete = (resume) =>axios.delete(`/api/resume/delete`,{data: {...resume}},
        { headers: { Authorization: 'JWT fefege'}

    });

const resumeSearch = (param) => {
    const str = "page=" + (!param.page ? 1 : param.page) +"&type="+ (encodeURIComponent(param.type)) +"&keyword=" + (param.keyword)
    return axios.get(`/api/resume/search?` + str, 
    {headers: { Authorization: 'JWT fefege'}
    })}

const countResume = (artistId) => axios.get(`/api/resume/count/${artistId}` , 
        {headers: { Authorization: 'JWT fefege'}
    })

const categoryList = () => axios.get(`/api/category/findall`, 
        {headers: { Authorization: 'JWT fefege'}
    });
const uploadFile = (formData) => axios.post("/api/resume_file/upload_file",formData,
        {headers: { "Content-Type": "multipart/form-data",
                    Authorization: 'JWT fefege'}})

export default {resumeList, resumeRegister, resumeModify, resumeRead, resumeDelete , resumeSearch, countResume, categoryList, uploadFile}