import axios from 'axios'

const getList=(page) =>{
    return axios.get("/funding/list?page=" +page)
}
const fundingRegister = (data) =>{
    return axios.post("/funding/register", data)
}

const showDetail =(fundingId)=>{
    return axios.get(`/funding/${fundingId}`)
}

const fundingUpdate = (fundingId,data) =>{
    return axios.put(`/funding/edit/${fundingId}`,data)
    .then(res =>{
        return res.data
    })
}
const deleteFunding = (funding) =>{
    const del = window.confirm("글 삭제를 진행 하시겠습니까?")
    if(del){
    axios({
        url: `/funding/${funding}`,
        method:'DELETE'
    }).then(res =>{
        alert("삭제 완료")
    }).catch(err =>{
        alert("삭제과정에서 에러 발생" +err)
    })}
}
const fileUpload = (formData) => axios.post(`/funding_file/upload_file`,formData,
    {headers:{ "Content-Type": "multipart/form-data"}})

const deleteFile = (id) => axios.delete(`/funding_file/delete_file/${id}`)

const searchSomething = (page,keyword)=>{
    return axios.get("/funding/list/search",page,keyword)
}
export default {getList, fundingRegister,showDetail,deleteFunding,fundingUpdate,searchSomething,fileUpload,deleteFile}