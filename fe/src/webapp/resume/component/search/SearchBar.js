import React, { useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import {searchResume} from "webapp/resume/reducer/resume.reduce"

const SearchBar = () => {
    const searchList = useSelector(state => state.resumes.searchResult)
    const page = searchList.page
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

    const onClick  = (e) =>{
        e.stopPropagation()
        e.preventDefault()
        console.log("ㅇㅇㅇㅇㅇ" , JSON.stringify(search))
        console.log("ㅇㅇㅇ" , JSON.stringify(page))
        dispatch(searchResume(search , page))
        
    }
    const handlePress =(e) =>{
        if(e.key === 'Enter') {
            onClick(e)
        }
   
}
    return (<>
        <div style={{display:"inline-table"}}>
            <select type="search"style={{color : "black"}}  name="type" value={search.type} onChange={(e)=> handleChange(e)} >
                <option value="">검색어를 선택해주세요</option>
                <option value="u">아이디</option>
                <option value="n">작성자</option>
                <option value="c">카테고리</option>
                <option value="t">제목</option>
                <option value="d">내용</option>
            </select>
            <input type="select"style={{color : "black"}}  placeholder="검색어를 입력하세요" 
            name="keyword" value={search.keyword} onChange={(e)=> handleChange(e)}
            onKeyPress={(e)=> handlePress(e)}/>
            <button onClick={(e) => onClick(e)}>검색</button>
        </div>
    

     </>);
}
 
export default SearchBar;