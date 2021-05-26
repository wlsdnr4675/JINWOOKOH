import React from "react";
import { useDispatch, useSelector } from "react-redux";
import { listResume } from "../reducer/resume.reduce";

const PageList = () => {

    const {pageList, page, start, end, prev, next} = useSelector(state => state.resumes.pageResult)
    const dispatch = useDispatch();
    const movePage = (page) => {
        dispatch(listResume(page))
    }
    const list = pageList.map(i => <button className="btn btn-outline-dark"  key={i} onClick={() => movePage(i)}> {i} </button>)
    return (<> 
        <div className="row mt-50">
    <div className="col-md-12">
      <div
        id="portfolio-gallery-filter"
        className={"cbp-l-filters-alignCenter text-sm-center "}
      >

            {prev? <button className="btn btn-outline-dark" onClick={() => movePage(start -1)} >prev</button>: <></>}
            {list}
            {next? <button className="btn btn-outline-dark" onClick={() => movePage(end + 1)} >next</button>: <></>}
      </div>
        </div>
        </div>
    </>);
}
 
export default PageList;