import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { countResume } from "webapp/resume/reducer/resume.reducer";

const ModifySidebar = ({ name, selfIntroduce, category, artistId}) => {
  const dispatch = useDispatch()


return (<>
  <div className="col-md-3 col-sm-3 col-xs-12">
    <div className="widget sidebar_widget">
      <h5 className="aside-title">아티스트 소개</h5>
      <form className="search-form" method="get">
      <h4 className="aside-title">아티스트 네임 readonly</h4>
      </form>
    </div>
    <div className="mb-50">
      <p className="mt-30">
        selfIntroduce 변경 가능
      </p>
    </div>
       
            <div className="sidebar_widget widget_categories mb-50">
              <h5 className="aside-title">Categories</h5>
                <ul>
                  <li>
                  
                    <p>카테고리 변화 가능</p>
                 
                  </li>
                </ul>
              </div>
               
    
    <div className="sidebar_widget widget_archive mb-50">
      <h5 className="aside-title">Archive</h5>
          카운트 받아오기 readOnly
    </div>
  </div>
</>)
};

export default ModifySidebar;