import React from "react";
import Icofont from "react-icofont";

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
    <div className="row mt-100">
              <div className="col-md-12">
                <div className="text-center">
                  <div className="pagination dark-color">
                    <ul>
                      <li>
                        <a href={process.env.PUBLIC_URL}>
                          <Icofont
                            icon="long-arrow-left"
                            className="mr-5 xs-display-none"
                          />
                          Prev
                        </a>
                      </li>
                      <li className="active">
                        <a href={process.env.PUBLIC_URL}>1</a>
                      </li>
                      <li>
                        <a href={process.env.PUBLIC_URL}>2</a>
                      </li>
                      <li>
                        <a href={process.env.PUBLIC_URL}>3</a>
                      </li>
                      <li>
                        <a href={process.env.PUBLIC_URL}>
                          Next
                          <Icofont
                            icon="long-arrow-right"
                            className="ml-5 xs-display-none"
                          />
                        </a>
                      </li>
                    </ul>
                  </div>
                </div>
              </div>
            </div>
    </>);
}
 
export default PageList;