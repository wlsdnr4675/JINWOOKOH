import axios from 'axios';
import { Signup } from '..';


const findAll = () => axios.get("http://localhost:8080/users/findAll")

const signup = () =>{
   
    const del = window.confirm("회원가입 하시겠습니까? ")
    if(del){
        axios({
            url: `http://localhost:8080/users/signup`,
            method: 'post',
            headers: {'Content-Type': 'application/json', 'Authorization': 'JWT fefege..'},
            data: Signup()
        })
        .then(resp =>{
            alert('회원가입이 완료되었습니다.')
            //history.pushState("/")
        })
        .catch(err => {
            alert(err)
        })
    }
}

const signin = () =>{
   
        axios({
            url: `http://localhost:8080/users/signin`,
            method: 'post',
            headers: {'Content-Type': 'application/json', 'Authorization': 'JWT fefege..'},
            data: {}
        })
        .then(res => {
            if(res.data.token){
                localStorage.setItem("cartuser", JSON.stringify(res.data.user));
                localStorage.setItem("token", res.data.token);
                //history.goBack()
            }else{
                alert(`토큰값이 없습니다.`)
            }
        })
        .catch(err => {
            alert('아이디 혹은 비밀번호가 일치하지 않습니다.')
        })
    }

const useredit = e =>{
    e.preventDefault();
    const del = window.confirm("정보를 수정하시겠습니까?")
    if(del){
        axios({
            url: `http://localhost:8080/users/useredit` + {},
            method: 'put',
            headers: {'Content-Type': 'application/json', 'Authorization': 'Bearer ' + localStorage.getItem("token")},
            data: {}
        }).then(res => {
            //history.pushState('/profile/update-user')
            window.location.reload()
        }).catch(err => {
            alert(err)
        })
        
    }
        
}
const userdelte = e =>{
    e.preventDefault();
    const del = window.confirm("정말 탈퇴하시겠습니까?")
    if(del){
        axios({
            url: `http://localhost:8080/users/userdelete` + {},
            method: 'delete',
            headers: {'Content-Type': 'application/json', 'Authorization': 'Bearer ' + localStorage.getItem("token")},
        }).then(res =>{
            alert(`탈퇴되었습니다.`)
            localStorage.removeItem("cartuser")
            localStorage.removeItem("token")
            //history.pushState("/")
        }).catch(err =>{
            alert(err)
        })
}
}

export default {findAll, signup, signin, useredit, userdelte};