import { Button } from "@material-ui/core";
import React, { useEffect, useState} from "react";
import { useDispatch, useSelector } from "react-redux";
import { readResume } from "../reducer/resume.reduce";

const ResumeItem = ({ resumeId, image, title, name, category, fn}) => {
 
  
  return (
    <>
    <div className="col-md-3 mfp-iframe-holder ">
      <div className="portfolio-item" onClick={(e) => fn(e,resumeId)}>
          <img src="https://blog.kakaocdn.net/dn/otVOc/btqNXGGOw0Y/FukcealGgBrcUPMBtYU5J0/img.gif" alt=""/>
          <div className="portfolio-info gradient-bg">
            <div className="centrize">
              <div className="v-center white-smoke">
                <h3>{title}</h3>
                <h4>{name}</h4>
                <p>{category}</p>
              </div>
            </div>
          </div>
      </div>
    </div>
  </>);
}


export default ResumeItem;
