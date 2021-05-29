import React from "react";
import { useDispatch, useSelector } from "react-redux";
import { listResume } from "../reducer/resume.reduce";
import Pagination from '@material-ui/lab/Pagination';

const PageList = () => {

    const {pageList, page, start, end, prev, next} = useSelector(state => state.resumes.pageResult)
    const dispatch = useDispatch();

    const movePage = (page) => {
        dispatch(listResume(page))
     
    }

    const list = pageList.map(i => <button className="btn" key={i} onClick={() => movePage(i)}> {i} </button>)
    return (<>
    <div className="pagination-centered">
        <div className="btn-lg">
        {prev? <button className="btn" onClick={() => movePage(start -1)} >prev</button> : <></>}
          {list}
        {next? <button className="btn" onClick={() => movePage(end + 1)} >next</button> : <></>}
      </div>
    </div>
    </>);
}
 
export default PageList;