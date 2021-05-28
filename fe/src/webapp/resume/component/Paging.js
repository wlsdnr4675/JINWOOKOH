import React from "react";
import { useDispatch, useSelector } from "react-redux";
import { listResume } from "../reducer/resume.reduce";
import { Typography } from "@material-ui/core";

const PageList = () => {

    const {pageList, page, start, end, prev, next} = useSelector(state => state.resumes.pageResult)
    const dispatch = useDispatch();

   
    const movePage = (page) => {
        dispatch(listResume(page))
     
    }

    const list = pageList.map(i => <button className=""  key={i} onClick={() => movePage(i)}> {i} </button>)
    return (<>
    {prev? <button className="" onClick={() => movePage(start -1)} >prev</button>: <></>}
            {list}
            {next? <button className="" onClick={() => movePage(end + 1)} >next</button>: <></>}
    </>);
}
 
export default PageList;