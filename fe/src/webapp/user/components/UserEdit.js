import React, { useState, useEffect, useCallback } from "react";
import axios from 'axios';

const UserEditForm = () => {
    const [user, setUser] = useState({});
    const {userno, username, uname, email, phoneNo} = user;
    
    const updateUser = () =>{
        axios.get(`http://localhost:8080/users/${localStorage.getItem('userno')}`)
        .then(res => {
            console.log("기존데이터 get", JSON.stringify(res.data))
            setUser({
                userno: res.data.userno,
                username: res.data.username,
                uname : res.data.uname,
                email : res.data.email,
                phoneNo : res.data.phoneNo
            })
        })
        .catch(err => {
            alert(err)
        })
    }

    useEffect(() =>{
        updateUser()
    },[]);

    const handleChange = useCallback ( e =>{
        const{name, value} = e.target;
        setUser({...user, [name] : value})}, [user]);

    const handleSubmit = useCallback ( (e) =>{
        e.preventDefault();
        axios.put(`http://localhost:8080/users/${localStorage.getItem('userno')}`,{
            userno,
            username,
            uname,
            email,
            phoneNo
        })
        .then(res => {
            alert(res)
            window.location = "/"
        })
        .catch(err =>{
            alert(err)
        })
        
    },[userno,
        username,
        uname,
        email,
        phoneNo])
    
    return ( <>
    <form>
        <label>아이디</label>
        <input type="text" name="usernam" value={username || ""} onChange={handleChange}/> 
        <label>이름</label>
        <input type="text" name="uname" value={uname || ""} onChange={handleChange}/> 
        <label>이메일</label>
        <input type="text" name="email" value={email || ""} onChange={handleChange}/> 
        <label>핸드폰번호</label>
        <input type="text" name="phoneNo" value={phoneNo || ""} onChange={handleChange}/> 
        <button className="button-primary" type="submit" onClick={()=>handleSubmit}>Edit item</button>
    </form>
    
    </> );
}
 
export default UserEditForm;