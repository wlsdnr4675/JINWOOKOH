import React, { useEffect, useState } from 'react'
import { makeStyles } from '@material-ui/core/styles';
import Slide from '@material-ui/core/Slide';
import Slider from "react-slick";

import {useDispatch, useSelector } from 'react-redux';
import {ModifyFile, ModifySidebar} from 'webapp/resume/index';

const ResumeModify = () => {
  const settings = {
    dots: false,
    infinite: true,
    centerMode: true,
    autoplay: true,
    autoplaySpeed: 5000,
    slidesToShow: 1,
    slidesToScroll: 1,
    centerPadding: "0",
    className: "blog-grid-slider slick",
  };


    const dispatch = useDispatch();
    return (<>
       
      <div className="container" style={{marginTop: "40px"}}>
        <ModifyFile/>
          <div className="col-md-9 col-sm-9 col-xs-12 xs-mb-50">
            <div className="row">
              <div className="col-md-12 col-sm-12 col-xs-12 mb-20 xs-mb-50">
                <div className="post">
                <h3>포트폴리오 수정</h3>
                  <div className="post-info all-padding-20">
                    <h3>
                    포트폴리오타이틀 수정 가능
                    </h3>
                  </div>
                </div>
              </div>
              <div className="col-md-12 col-sm-12 col-xs-12 mb-20">
                <div className="blog-standard">
                  <blockquote>
                    <p>
                   포트폴리오디테일 수정 가능
                    </p>
                  </blockquote>
                </div>
              </div>
            </div>
          </div>
          <ModifySidebar />
      </div>
    </>)
} 

export default ResumeModify;
