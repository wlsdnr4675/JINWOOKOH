import { Button } from "@material-ui/core";
import React, { useState} from "react";
import { useDispatch, useSelector } from "react-redux";
import { readResume } from "../reducer/resume.reduce";
import ResumeRead from "webapp/resume/component/ResumeRead"

const ResumeItem = ({ resumeId, image, title, name, category}) => {
  const dispatch = useDispatch()
  const [open, setOpen] = useState(false);
  
  const handleClose = () => setOpen(false)

  const  handleOpen = async () => {
    dispatch(readResume(resumeId))
    setOpen(true)
    console.log(resumeId)
  }
 
  
  return (
    <div className="col-md-3 mfp-iframe-holder ">
      
      <div className="portfolio-item"  onClick={handleOpen} >
          <img src="https://image.fmkorea.com/files/attach/new/20201123/2978469841/3198112949/3217831361/31b0611056044e5608778a0e080ce96a.jpeg" alt="" />
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
      <ResumeRead 
      open ={open} handleClose={() => handleClose() } 
      ></ResumeRead>
    </div>
  );
}


export default ResumeItem;
