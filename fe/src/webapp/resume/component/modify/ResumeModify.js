import React, { useEffect, useState } from 'react'
import { makeStyles } from '@material-ui/core/styles';
import Dialog from '@material-ui/core/Dialog';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import IconButton from '@material-ui/core/IconButton';
import Typography from '@material-ui/core/Typography';
import CloseIcon from '@material-ui/icons/Close';
import Slide from '@material-ui/core/Slide';
import Slider from "react-slick";
import {ReadSidebar} from "webapp/resume/index";
import {useDispatch, useSelector } from 'react-redux';
import { Link } from 'react-router-dom';
import { deleteResume } from 'webapp/resume/reducer/resume.reducer';

const ResumeModify = ({open , handleClose}) => {

    const useStyles = makeStyles((theme) => ({
        appBar: {
          position: 'relative',
          backgroundColor:"#561571",
          fontSize: 30,
        },
        title: {
          marginLeft: theme.spacing(3),
          marginTop: "10px",
          flex: 1,
          color:  "white",
          fontSize: 25,
          textAlign: "center"
          
        },
        closeSize: {
            fontSize: 30,
            color: "white"

        },
        dialogSize : {
            margin: 'auto auto',
            width: '95%', 
            verticalAlign: "middle",
        },
      }));

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
      const Transition = React.forwardRef(function Transition(props, ref) {

        return <Slide direction="up" ref={ref} {...props} />;
      });  


    const dispatch = useDispatch();
    return (<>
        <Dialog TransitionComponent={Transition} className={useStyles().dialogSize}
        >
          <AppBar className={useStyles().appBar}>
            <Toolbar >
              <IconButton edge="start"  onClick={handleClose} aria-label="close">
                <CloseIcon className={useStyles().closeSize}/>
              </IconButton>
              <Typography className={useStyles().title}>
                  EDIT PAGE
              </Typography>

              <Link to={""}>
                <button className="btn btn-light-outline btn-square">
                EDIT</button></Link>
                <button className="btn  btn-light-outline btn-square" style={{marginTop: "4px"}}
                onClick={""}>
                DELETE</button>
            </Toolbar>
          </AppBar>
      <div className="container" style={{marginTop: "40px"}}>
        <div className="row">
          <div className="col-md-9 col-sm-9 col-xs-12 xs-mb-50">
            <div className="row">
              <div className="col-md-12 col-sm-12 col-xs-12 mb-20 xs-mb-50">
                <div className="post">
                  {/* {files.length > 0 ? (
                    <Slider {...settings}>
                      이미지
                    </Slider>
                  ) : (
                    <img
                      alt="이미지가 없습니다."
                    />
                  )} */}

                  <div className="post-info all-padding-20">
                    <h3>
                    파일타이틀 수정 가능
                    </h3>
                  </div>
                </div>
              </div>
              <div className="col-md-12 col-sm-12 col-xs-12 mb-20">
                <div className="blog-standard">
                  <blockquote>
                    <p>
                   파일 디테일 수정 가능
                    </p>
                  </blockquote>
                </div>
              </div>
            </div>
          </div>
          <ReadSidebar />
        </div>
      </div>
      

          
         
        </Dialog>
    </>)
} 

export default ResumeModify;
