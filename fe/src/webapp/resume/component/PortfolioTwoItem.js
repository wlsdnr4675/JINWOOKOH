import React from "react";
const PortfolioTwoItem = ({ index, image, title, name, categoryName }) => {
  console.log("PortfolioTwoItem", JSON.stringify(title))
  console.log("PortfolioTwoItem", JSON.stringify(name))  

  return (
  <div
    className={
      "col-md-4"
    } 
  >
    <div className="portfolio-item">
      <a href="!#">
        <img src={require("webapp/images/resume/portfolio_first.jpeg")} alt=""/>
        <div className="portfolio-info dark-bg">
          <div className="centrize">
            <div className="v-center white-color">
              <h3>{title}</h3>
              <p>{name}</p>
              <p>{categoryName[0]}</p>
            </div>
          </div>
        </div>
      </a>
    </div>
  </div>
);
}


export default PortfolioTwoItem;
