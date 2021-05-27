import React, { useState } from "react";
const PortfolioTwoItem = ({ index, image, title, name, category}) => {
  

  

  return (
    <div className={"col-md-3 mfp-iframe-holder " + category}>
     
      <div className="portfolio-item" >
          <img src={require("webapp/images/resume/yg.jpeg")} alt="" />
          <div className="portfolio-info gradient-bg">
            <div className="centrize">
              <div className="v-center white-smoke">
                <h3>{title}</h3>
                <p>{category}</p>
              </div>
            </div>
          </div>
      </div>
    </div>
  );
}


export default PortfolioTwoItem;
