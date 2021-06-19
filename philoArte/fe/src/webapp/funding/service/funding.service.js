import axios from 'axios'


const userInfo = typeof window !== `undefined` ? JSON.parse(localStorage.getItem('artist')) : null;
const SERVER = 'http://13.209.194.227:8080'
const getList=(page) =>{
    return axios({
        url:`${SERVER}/funding/list?page=` +page,
        method: 'get',
        headers:{
            Authorization: 'JWT fefege...'
        }
    })
}
const fundingRegister = (data) =>{
    return axios({
        url: `${SERVER}/funding/register`,
        method: "post",
        data: data,
        headers:{
            Authorization:  `Bearer ${userInfo.token}`
        }
    })
}

const showDetail =(fundingId)=>{
    return axios({
        url: `${SERVER}/funding/${fundingId}`,
        method: 'get',
        headers:{
            Authorization: 'JWT fefege...'
        }
    })
}

const fundingUpdate = (fundingId,data) =>{
    return axios({
        url: `${SERVER}/funding/edit/${fundingId}`,
        method: 'put',
        data: data,
        headers:{
            Authorization: 'JWT fefege...'
        }
    }).then(res =>{
        return res.data
    })
}
const deleteFunding = (funding) =>{
    const del = window.confirm("글 삭제를 진행 하시겠습니까?")
    if(del){
    axios({
        url: `${SERVER}/funding/${funding}`,
        method:'DELETE',
        headers:{
            Authorization: 'JWT fefege...'
        }
    }).then(res =>{
        alert("삭제 완료")
    }).catch(err =>{
        alert("삭제과정에서 에러 발생" +err)
    })}
}
const fileUpload = (formData) => {
    return axios({
        url: `${SERVER}/funding_file/upload_file`,
        method: 'post',
        data: formData,
        headers:{ "Content-Type": "multipart/form-data",
        Authorization: 'JWT fefege...'}
    })
}

const deleteFile = (id) =>{
    return axios({
        url: `${SERVER}/funding_file/delete_file/${id}`,
        method: 'delete',
        headers:{
            Authorization: 'JWT fefege...'
        }
    })
}

const searchSomething = (page,keyword)=>{
    return axios({
        url: "${SERVER}/funding/list/search",
        method: page,keyword,
        headers:{
            Authorization: 'JWT fefege...'
        }
    })
    
}
export default {getList, fundingRegister,showDetail,deleteFunding,fundingUpdate,searchSomething,fileUpload,deleteFile}