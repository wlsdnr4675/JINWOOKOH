import React from "react";
import Icofont from "react-icofont";
import imgAbout from "webapp/images/about-me.jpg";

const ReadSidebar = ({name, selfIntroduce, category}) => (
  <div className="col-md-3 col-sm-3 col-xs-12">
    <div className="widget sidebar_widget">
      <h5 className="aside-title">아티스트 소개</h5>
      <form className="search-form" method="get">
      <h4 className="aside-title">{name}</h4>
      </form>
    </div>
    <div className="mb-50">
      <p className="mt-30">
        {selfIntroduce}
      </p>
    </div>
        {category ? (
            <div className="sidebar_widget widget_categories mb-50">
              <h5 className="aside-title">Categories</h5>
                <ul>
                  <li>
                  
                    <p>{category}</p>
                 
                  </li>
                </ul>
              </div>
                ) : null}
    
    <div className="sidebar_widget widget_archive mb-50">
      <h5 className="aside-title">Archive</h5>
      <ul>
        <li>
          <a href={process.env.PUBLIC_URL}>January 2018</a> <span>14</span>
        </li>
        <li>
          <a href={process.env.PUBLIC_URL}>May 2017</a> <span>8</span>
        </li>
        <li>
          <a href={process.env.PUBLIC_URL}>June 2017</a> <span>63</span>
        </li>
        <li>
          <a href={process.env.PUBLIC_URL}>Febuary 2012</a> <span>45</span>
        </li>
        <li>
          <a href={process.env.PUBLIC_URL}>April 2016</a> <span>88</span>
        </li>
      </ul>
    </div>
  </div>
);

export default ReadSidebar;
