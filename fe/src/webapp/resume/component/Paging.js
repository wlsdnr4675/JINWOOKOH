import React from "react";
import { useDispatch, useSelector } from "react-redux";
import { listResume } from "../reducer/resume.reduce";

const PageList = () => {

    const {pageList, page, start, end, prev, next} = useSelector(state => state.resumes.pageResult)
    const dispatch = useDispatch();

   
    const movePage = (page,btn, color) => {
        dispatch(listResume(page))
     
    }

    const list = pageList.map(i => <button id="button" className="cbp-l-filters-big cbp-filter-item cbp-l-filters-text"  key={i} onClick={() => movePage(i)}> {i} </button>)
    return (<> 
        <div className="row mt-50">
    <div className="col-md-12">
      <div
        id="paging-button"
        className={"cbp-l-filters-buttonCenter"}
      >
            {prev? <button className="bp-l-filters-big cbp-filter-item bp-l-filters-text " onClick={() => movePage(start -1)} >prev</button>: <></>}
            {list}
            {next? <button className="bp-l-filters-big cbp-filter-item bp-l-filters-text" onClick={() => movePage(end + 1)} >next</button>: <></>}
      </div>
        </div>
        </div>
    </>);
}
 
export default PageList;