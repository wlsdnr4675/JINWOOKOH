import React, { useEffect, useState } from "react";
import parse from "html-react-parser";
import LoadScript from "webapp/common/helpers/LoadScript";
import PortfolioTwoItem from "./PortfolioTwoItem";
import ResumeListFilter from "./ResumeListFilter";


const ResumeList = ({
  resumes,
  title,
  tagline,
  backfont,
  filter,
  categoryName,
  classes
}) => {
  LoadScript("js/portfolio/portfolio-grid.js");

  console.log(resumes)
  console.log("sss",categoryName)

  return (<>
    <section id="portfolio" className={"pb-0 " + classes}>
      <div className="container">
        {title ? (
          <div className="row">
            <div className="col-md-12">
              <div className="section-title text-center">
                <h2
                  className="cardo-font default-color"
                  data-backfont={backfont || "Portfolios"}
                >
                  {tagline}
                </h2>
                <h1>{title && parse(title)}</h1>
                <hr className="center_line dark-bg" />
              </div>
            </div>
          </div>
        ) : null}
        {filter === true && Array.isArray(categoryName) ? (
          <ResumeListFilter categories={categoryName}  resume = {resumes} classes="mb-80" />
        ) : null}
        <div className="container-fluid remove-padding">
        <div className="row">
          <div className="col-md-12">
            <div id="portfolio-gallery" className="cbp">
              {resumes.map((item, i) => (
                <PortfolioTwoItem
                  key={i}
                  index={i}
                  title={item.title}
                  name={item.name}
                  image={item.fname}
                  categoryName={item.categoryName}
                />
              ))}
            </div>
          </div>
        </div>
      </div>
      </div>
      
    </section>
  </>);
};

export default ResumeList;
