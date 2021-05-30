import React from "react";


const ResumeItem = ({ resumeId, image, title, name, category, fn}) => {
  return (
    <>
    <div className="col-md-6">
      <div className="portfolio-item" onClick={(e) => fn(e,resumeId)}>
          <img src="http://www.yck.kr/_data/file/bbsData/94666c2a8bc75a0ad679fa5403ab8b75.png" alt=""/>
          <div className="portfolio-info gradient-bg" style={{position: "relative"}}>
              <div className="v-center white-smoke">
                <h3>{title}</h3>
                <p>{name}</p>
                <p>{category}</p>
            </div>
          </div>
      </div>
    </div>
  </>);
}


export default ResumeItem;
