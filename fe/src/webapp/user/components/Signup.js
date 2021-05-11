import React from 'react';
import {Link} from 'react-router-dom';
import { useCallback, useState } from "react";
import { useDispatch, useSelector } from 'react-redux';
import { signup } from '../reducer/user.reducer';

const Signup = () => {

    const dispatch = useDispatch();

    const [user, setUser] = useState({
        username : "",
        password : "",
        name: "",
        email: ""
    });

    const handleChange = useCallback(e =>{
        const { name, value} = e.target;
             setUser({
            ...user,[name] : value
        })
    },[user])
    const onCreate = e =>{
        e.preventDefault();
            dispatch(signup(user));
            
    }
    


    return ( <>
        <form onSubmit={onCreate} method={'post'}>
            
            <label>아이디</label>
            <input style={{background: 'black'}}className="u-full-width" type="text" name="username" value={user.username} onChange={handleChange} />
            <label>비밀번호</label>
            <input style={{background: 'black'}}className="u-full-width" type="password" name="password" value={user.password} onChange={handleChange} />
            <label>이름</label>
            <input style={{background: 'black'}}className="u-full-width" type="text" name="uname" value={user.uname} onChange={handleChange} />
            <label>이메일</label>
            <input style={{background: 'black'}}className="u-full-width" type="email" name="email" value={user.email} onChange={handleChange} />  
            <button type='submit' onClick={onCreate}>회원가입</button>
            <Link to={'/'}><button>취소</button></Link>
            

        </form>
    </> );
}
 
export default Signup;