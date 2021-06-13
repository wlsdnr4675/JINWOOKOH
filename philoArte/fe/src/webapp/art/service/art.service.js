import axios from 'axios'
const artList = (page) => {
  console.log("list", page)
  return axios.get(`/arts/list?page=${page}`)
}
const artSearch = (param) => {
  console.log("page", param)
  const str = "page=" + (!param.page ? 1 : param.page) + "&type=" + (param.type) + "&keyword=" + (param.keyword)
  console.log(str)
  return axios.get(`/arts/search?` + str)
}
const artRegister = (payload) => axios.post(`/arts/register`, payload)
const artUpload = (formData) => axios.post(`/art_files/upload`, formData,
  {
    headers: {
      "Content-Type": "multipart/form-data"
    }
  }
)
const artFileDelete = (payload) => axios.delete(`/art_files/delete`, payload)
const artRead = (payload) => axios.get(`/arts/read/${payload}`)
const artModify= (payload) => axios.put(`/arts/modify`, payload)
const artDelete = (payload) => {
  return axios({
    url: `/arts/delete`,
    method: 'delete',
    data: payload
  })
}
const artCount = (payload) => axios.get(`/arts/count/${payload}`)
const artPosts = (payload) => axios.get(`/arts/list/${payload}`)
const categoryList = () => axios.get(`/category/findall`)
export default { artList, artRegister, artUpload, artFileDelete, artRead, artModify, artDelete, artSearch, artCount, artPosts, categoryList }