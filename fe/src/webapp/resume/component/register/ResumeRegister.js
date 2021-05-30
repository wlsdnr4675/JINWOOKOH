import React from 'react'

import {Link} from 'react-router-dom';

import {RegisterSidebar} from "webapp/resume/index"

const ResumeRegister = () => {

    return (<> 
    <div className="container" style={{marginTop: "40px"}}>
        <div className="row">
            <div className="col-md-9 col-sm-9 col-xs-12 xs-mb-50">
                <div className="row">
                    <div className="col-md-12 col-sm-12 col-xs-12 mb-20 xs-mb-50">
                        <div className="post">
                            파일 업로드 하는 곳
                            파일 타이틀
                            파일디테일
                            파일 만든 날짜
                        <div className="post-info all-padding-20">
                        <h3>
                            레쥬메 타이틀
                        </h3>
                        </div>
                    </div>
                </div>
                    <div className="col-md-12 col-sm-12 col-xs-12 mb-20">
                        <div className="blog-standard">
                            <blockquote>
                                <p>레쥬메 디테일적는곳</p>
                            </blockquote>
                        </div>
                    </div>
                </div>
            </div>
            <div className="col-md-3 col-sm-3 col-xs-12">
                <div className="widget sidebar_widget">
                    <h5 className="aside-title">아티스트 소개</h5>
                    <form className="search-form" method="get">
                    <h4 className="aside-title">이름</h4>
                    </form>
                </div>
                <div className="mb-50">
                    <p className="mt-30">셀프 인트로 듀스</p>
                </div>
                <div className="sidebar_widget widget_categories mb-50">
                    <h5 className="aside-title">Categories</h5>
                        <ul>
                            <li>카테고리 선택</li>
                        </ul>
                </div>
                    <Link to = "/"> 
                        <p className="btn btn-md btn-dark-outline btn-square mt-10" >
                            REGISTER</p>
                    </Link>
                    < Link to = "/resume/home" > <p className="btn btn-md btn-dark-outline btn-square mt-10" >
                        BACK</p>
                    </Link>
            </div>
        </div>
    </div>
</>)
}

export default ResumeRegister