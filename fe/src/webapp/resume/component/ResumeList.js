import React, { useEffect, useState } from "react";
import parse from "html-react-parser";
import LoadScript from "webapp/common/helpers/LoadScript";
import ResumeItem from "./ResumeItem";


const ResumeList = ({ title, tagline, backfont, resumes, categories,dash, dashColor }) => {

  LoadScript("js/portfolio/portfolio-grid.js");
  const {category , setCategory} = useState(categories)

  const filterButton = categories.map((category, i) => {
    return (<>
      <button key={i} onClick={()=> setCategory(category.name)}>{category}</button>
    </>)
  })

  const totalList = resumes.map( (resume, i) => {
    return (
      <ResumeItem 
        key={resume.resumeId}
        id={i}
        title={resume.title}
        image={resume.fname}
        category={resume.categoryName}
      />

    )});
  return (
    <section id="portfolio" className="pt-0 pb-0 ">
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
      <div className="container-fluid remove-padding">
          <div className="col-md-12">
                  {filterButton}
            <div id="portfolio-gallery" className="cbp">
                  {totalList}
            </div>
          </div>
        </div>

    </section>
  );
};

export default ResumeList;
