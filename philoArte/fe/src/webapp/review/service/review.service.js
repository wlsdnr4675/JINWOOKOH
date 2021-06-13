import axios from 'axios'
import { useRef } from 'react';


const register=(fd)=>{

    console.log(fd)

    return axios.post(`/reviews/register`, fd,{
        headers:{
            'Content-Type': 'multipart/form-data'
        }
    })
    .then(response=>{
        return response.data
    })
}

const list=(pageResult)=>{
    console.log('service hireList pageRequest: ' + JSON.stringify(pageResult));

    const str = "page=" + (!pageResult.page?1:pageResult.page) +"&type="+ (pageResult.type) +"&keyword=" + (pageResult.keyword)
    
    return axios.get(`/reviews/list/pages?` + str)
}

const read=(reviewId)=>{
    return axios.get(`/reviews/read/${reviewId}`)
}

const modify=(review)=>{

    return axios.put(`/reviews/modify/`+review.reviewId, review,{
        headers:{
            'Content-Type': 'multipart/form-data'
        }
    })
    .then(response=>{
        return response.data
})
}

const deletes=(reviewId)=>{
    console.log("Delete Review")
    return axios.delete(`/reviews/remove/${reviewId}`, {data:{...reviewId}})
}

export default{register, list, read, modify, deletes}