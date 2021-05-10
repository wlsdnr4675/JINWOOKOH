import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import  { getUserList }  from "../reducer/user.reducer";

const UserBoard = () => {

    const dispatch = useDispatch();
    const users = useSelector(state => state.users)
    
 
    useEffect(() =>{
        alert('1번 useEffect')
        dispatch(getUserList());
    },[]);



    return ( <>
    <div className="container">
        <div className="row">
        <table style={{border: "10"}}>
            <thead>
                <tr>
                    <th>username</th>
                    <th>name</th>
                    <th>email</th>
                </tr>
            </thead>
            <tbody>
            {users.map((user, id) => {
                return (
                    <tr key={id}>
                            <td>{user.username}</td>
                            <td>{user.name}</td>
                            <td>{user.email}</td>
                    </tr>)
            })}
            </tbody>
        </table>
           
        </div>
    </div>
    </> );
}
 
export default UserBoard;