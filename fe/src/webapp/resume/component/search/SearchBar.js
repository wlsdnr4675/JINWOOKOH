import React, { useState } from "react";
import { useDispatch, useSelector } from "react-redux";

const SearchBar = () => {
    const searchList = useSelector(state => state.resumes.search)
    const dispatch = useDispatch();
    const [search, setSearch] =useState({
        type: "",
        keyword :""
    });
    const handleChange= (e) =>{
        e.stopPropagation()
        e.preventDefault()
        const {name, value} = e.target
        setSearch({...search , [name] : value})
        
    }
    console.log(search)
    return (<>
        <div style={{display:"inline-table"}}>
            <select type="search"style={{color : "black"}} name="type" value={search.type} onChange={(e)=> handleChange(e)} >
                <option value="u">아이디</option>
                <option value="n">작성자</option>
                <option value="c">카테고리</option>
                <option value="t">제목</option>
                <option value="d">내용</option>
            </select>
            <input type="select"style={{color : "black"}} name="keyword" value={search.keyword} onChange={(e)=> handleChange(e)}
            onKeypress="javascript:if(event.keyCode==13)"/>
            
        </div>
    

     </>);
}
 
export default SearchBar;