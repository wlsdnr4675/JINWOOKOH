import React, { useEffect, useState, useRef } from 'react'
import { useDispatch, useSelector } from 'react-redux';
import {Link} from 'react-router-dom';
import { addFileList, listCategory, registerResume } from 'webapp/resume/reducer/resume.reducer';
import axios from "axios";
import { ResumeFile } from 'webapp/resume/index';


const ResumeRegister = () => {

    const dispatch = useDispatch();

    const categories = useSelector(state => state.resumes.category);
    const fileList = useSelector(state => state.resumes.fileList)
    
    
    
    const [result, setResult] = useState(false)

    const requestRefresh = (result) => {

        console.log("REQUEST REFRESH")

        setResult(!result)
    }


    useEffect(()=>{
        dispatch(listCategory())
    },[])
    const [resume, setResume] = useState({
        title:"",
        detail: "",
        selfIntroduce: "",
        artist: "",
        category: "",
    });

    const resumeChange = (e) => {
        e.stopPropagation()
        e.preventDefault()
        const {name, value} = e.target;
        setResume({...resume, [name] : value})
    }

    const handleClick = async (e) =>{
        e.stopPropagation()
        e.preventDefault()
        const {name, value} = e.target;
        setResume({...resume, [name]: value})
        await dispatch(addFileList(resume))
        await requestRefresh()
    }


    const sendTextForm = (title, content) => {
        console.log("title: " + title)
        console.log("content: " + content)

    }

    const getUploadedFiles = (uplodedFilesResult) => {
        console.log("getUploadedFiles")
    }




    return (<> 
    <div className="container dark-color" style={{marginTop: "40px", color: "#24182e"}} >
                            <ResumeFile sendTextForm = {sendTextForm}
                             getUploadedFiles = {getUploadedFiles}/>
                            <div className="comment-content" style={{marginTop: "40px"}}>
                            <div className="post-body">
                                <div className="comment-header">
                                    <span className="writerName">
                                    <label className="font-20px">포트폴리오 타이틀을 적어주세요</label>
                                    <input type="text"  style={{color:"black"}} name="title"
                                    value={resume.title} placeholder="resumeTitle"
                                    onChange={(e)=>resumeChange(e)}/>
                                    </span>
                                <blockquote>
                                    <label className="font-20px">포트폴리오의 세부사항을 적어주세요</label>
                                    <input type="textarea" style={{color:"black", height:"400px"}} name="detail"
                                    value={resume.detail} placeholder="resumeDetail"
                                    onChange={(e)=>resumeChange(e)}/>
                                </blockquote>
                                </div>
                            </div>
                            <h5 className="aside-title">아티스트 소개</h5>
                        <form className="search-form" method="post">
                        <h4 className="aside-title">이름</h4>
                        </form>
                    <label className="font-20px">자기소개글을 써주세요</label>
                    <input type="textarea" name="selfIntroduce" style={{color:"black", height:"200px"}}
                    value={resume.selfIntroduce} placeholder="resumeDetail"
                    onChange={(e)=>resumeChange(e)}/>
                     <div className="sidebar_widget widget_categories mb-50" style={{marginBottom: "20px"}}>
                    <h5 className="aside-title">Categories</h5>
                        <ul>
                            <li>카테고리 선택</li>
                        </ul>
                        
                    </div>
                </div>
                <div style={{marginBottom: "20px", verticalAlign: "center"}}>
                    <p className="btn btn-md btn-dark-outline btn-square mt-10" onClick={(e)=>handleClick(e)} >
                            REGISTER</p>
                        < Link to = "/resume/home" > <p className="btn btn-md btn-dark-outline btn-square mt-10" >
                        BACK</p>
                        </Link>
                </div>               
            </div>
</>)
}

export default ResumeRegister