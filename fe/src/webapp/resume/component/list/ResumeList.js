import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import parse from "html-react-parser";
import "webapp/resume/css/ResumeCss.css"
import LoadScript from "webapp/common/helpers/LoadScript";
import {ResumeItem, ResumeRead} from "webapp/resume/index";
import {readResume, countResume} from "webapp/resume/reducer/resume.reducer"


const ResumeList = ({ title, tagline, backfont, resumes, dash, dashColor }) => {

  LoadScript("js/portfolio/portfolio-grid.js");
  
  const dispatch = useDispatch()

  const [open, setOpen] = useState(false);
  const [resumeNo, setResumeNo] = useState({})
  
  const handleClose = () => setOpen(false)

  
  const  handleOpen = async (e, resumeId, artistId) => {
    e.stopPropagation()
    e.preventDefault()
    await dispatch(readResume(resumeId))
    await dispatch(countResume(artistId))
    setResumeNo(resumeId)
    setOpen(true)
  }

  const totalList = resumes.map( (resume, i) => {

    return (
      <ResumeItem 
        key={i}
        fn = {handleOpen}
        resumeId={resume.resumeId}
        title={resume.title}
        name={resume.name}
        artistId={resume.artistId}
        resumeFiles={resume.resumeFiles}
        repImg={resume.repImg}
        category={resume.categoryName}
      />
    )});

  return (
    <section id="portfolio" className="pt-0 pb-0">
      <div className="container">
        <div className="row">
          <div className="col-md-12">
            <div className="section-title text-center">
              <h2 className="cardo-font default-color" data-backfont={backfont}>
                {tagline}
              </h2>
              <h1>{title && parse(title)}</h1>
              {dash === "show" ? <hr className={"center_line " + (dashColor === "dark" ? "dark-bg" : "default-bg")} /> : null}
            </div>
          </div>
        </div>
      </div>
      <div className="container-fluid remove-padding ">
          <div className="img-wrap">
            <div id="portfolio-gallery" >
                  {totalList}
                  {open ? <ResumeRead open={open} handleClose={() => handleClose()}
                  resumeId={resumeNo}/>:<></> }
            </div>
          </div>
        </div>

    </section>
  );
};

export default ResumeList;
