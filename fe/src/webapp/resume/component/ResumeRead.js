import { makeStyles } from '@material-ui/core/styles';
import Button from '@material-ui/core/Button';
import Dialog from '@material-ui/core/Dialog';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import IconButton from '@material-ui/core/IconButton';
import Typography from '@material-ui/core/Typography';
import CloseIcon from '@material-ui/icons/Close';
import Slide from '@material-ui/core/Slide';
import React, { useEffect, useState } from 'react'
import Slider from "react-slick";
import BlogSidebar from "webapp/resume/component/BlogSidebar";

import { useDispatch, useSelector } from 'react-redux';
import { readResume } from '../reducer/resume.reduce';

const ResumeRead = ({resumeId, open , handleClose}) => {

    const useStyles = makeStyles((theme) => ({
        appBar: {
          position: 'relative',
          backgroundColor:"#561571",
          fontSize: 30,
        },
        title: {
          marginLeft: theme.spacing(3),
          flex: 1,
          color:  "white",
          fontSize: 30,
          
        },
        closeSize: {
            fontSize: 30,
            color: "white"

        },
        dialogSize : {
            margin: 'auto auto',
            width: '95%', 
            textAlign : "center",
            verticalAlign: "middle",
        },
      }));
      
      const Transition = React.forwardRef(function Transition(props, ref) {

        return <Slide direction="up" ref={ref} {...props} />;
      });
      

    const items = useSelector(state => state.resumes.current);
    const files = items.resumeFiles;
    const [resumeItem,setResumeItem] = useState({});
    

    console.log("Read ResumeId",resumeId)
  
    useEffect(()=>{
        setResumeItem(items)
    },[items]) 

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
  
    const fileList = files.map((file, i)=>{
     
        return (<>
         
          <img src = "http://www.yck.kr/_data/attach/plupload/98b804da0483245b755ddd8b3cf0c9771616658727.jpg"/>
          <h2>{file.fname}</h2>
          
        </>)
    })
    
    return (<>
        <Dialog  fullScreen open={open} onClose={handleClose} TransitionComponent={Transition} className={useStyles().dialogSize}
        >
          <AppBar className={useStyles().appBar}>
            <Toolbar >
              <IconButton edge="start"  onClick={handleClose} aria-label="close">
                <CloseIcon className={useStyles().closeSize}/>
              </IconButton>
              <Typography variant="h6" className={useStyles().title}>
                {resumeItem.name} 의 포트폴리오
              </Typography>
              <Button color="inherit" onClick={handleClose} className="font-30px">
                수정하기
              </Button>
            </Toolbar>
          </AppBar>
      <div className="container" style={{marginTop: "40px"}}>
        <div className="row">
          <div className="col-md-9 col-sm-9 col-xs-12 xs-mb-50">
            <div className="row">
              <div className="col-md-12 col-sm-12 col-xs-12 mb-20 xs-mb-50">
                <div className="post">
                  {files.length > 0 ? (
                    <Slider {...settings}>
                      {fileList}
                    </Slider>
                  ) : (
                    <img
                      alt="이미지가 없습니다."
                    />
                  )}
                  <div className="post-metas">
                    <div className="post-metas-center">
                      <p className="post-date"></p>
                    </div>
                  </div>
                
                  <div className="post-info all-padding-20">
                    <h3>
                    
                    </h3>
                    <p>32132131232132132131231</p>
                  </div>
                </div>
              </div>
              <div className="col-md-12 col-sm-12 col-xs-12 mb-20">
                <div className="blog-standard">
                  <p>
                  321321321321321321
                  </p>
                  <blockquote>
                    <p>
                   321312312321321312321321321
                    </p>
                  </blockquote>
                </div>
                {resumeItem.categoryName ? (
                  <div className="post-tags">
                  {resumeItem.categoryName}
                  </div>
                ) : null}
                {/* <h2 className="recent-post-title">개인 작품</h2>
                <div className="row blog-style-01">
                  {data.slice(0, 2).map((post) =>
                    post.slides ? (
                      <div
                        className="col-md-6 col-sm-6 col-xs-12"
                        key={post.id}
                      >
                        <div className="post">
                          <Slider {...settings}>
                            {post.slides.map((image, i) => (
                              <div className="item" key={i}>
                                <img
                                  className="img-responsive"
                                  src={require("../../assets/images/" + image)}
                                  alt=""
                                />
                              </div>
                            ))}
                          </Slider>
                          <div className="post-metas">
                            <div className="post-metas-center">
                              <p className="post-date">{post.published}</p>
                            </div>
                          </div>
                          <div className="post-info all-padding-20">
                            <h3>
                              <a href={process.env.PUBLIC_URL + " blog "}>
                                {post.title}
                              </a>
                            </h3>
                            <p>{post.text}</p>
                          </div>
                        </div>
                      </div>
                    ) : (
                      <div
                        className="col-md-6 col-sm-6 col-xs-12"
                        key={post.id}
                      >
                        <div className="post">
                          <div className="post-img">
                            <img
                              className="img-responsive"
                              src={require("../../assets/images/" + post.image)}
                              alt=""
                            />
                            <div className="post-metas">
                              <div className="post-metas-center">
                                <p className="post-date">{post.published}</p>
                              </div>
                            </div>
                          </div>
                          <div className="post-info all-padding-20">
                            <h3>
                              <a href={process.env.PUBLIC_URL + " blog "}>
                                {post.title}
                              </a>
                            </h3>
                            <p>{post.text}</p>
                          </div>
                        </div>
                      </div>
                    )
                  )}
                </div> */}
              </div>
            </div>
            {/*  */}
          </div>
          <BlogSidebar />
        </div>
      </div>
      

          
         
        </Dialog>
    </>)
}

export default ResumeRead;
