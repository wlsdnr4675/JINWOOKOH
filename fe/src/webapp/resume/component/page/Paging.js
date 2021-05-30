import React from "react";
import Icofont from "react-icofont";
import { useDispatch, useSelector } from "react-redux";
import {listResume} from "webapp/resume/reducer/resume.reducer";

const PageList = () => {

    const {pageList, page, start, end, prev, next} = useSelector(state => state.resumes.pageResult)
    const dispatch = useDispatch();

    const movePage = (page) => {
        dispatch(listResume(page))
     
    }

    const list = pageList.map(i => <button className="btn" key={i} onClick={() => movePage(i)}> {i} </button>)
    return (<>
      <div className="row mt-100">
        <div className="text-center">
          {prev? <button className="btn" onClick={() => movePage(start -1)} >
          <Icofont icon="long-arrow-left" className="mr-5 xs-display-none"/>
            prev
          </button> : <></>}
            {list}
          {next? <button className="btn" onClick={() => movePage(end + 1)} >next
         <Icofont icon="long-arrow-right" className="ml-5 xs-display-none"/>
          </button> : <></>}
        </div>
      </div>
    </>);
}
 
export default PageList;