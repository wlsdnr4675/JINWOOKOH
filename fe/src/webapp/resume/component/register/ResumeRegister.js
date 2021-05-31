import React, { useCallback, useImperativeHandle, useEffect, useState } from 'react'
import { useDispatch, useSelector } from 'react-redux';
import {Link} from 'react-router-dom';
import { listCategory, registerResume } from 'webapp/resume/reducer/resume.reducer';
import axios from "axios";


const ResumeRegister = ({cref, getUploadedFiles, fileParam=[]}) => {
    const dispatch = useDispatch();
    const categories = useSelector(state => state.resumes.category);


    useEffect(()=>{
        dispatch(listCategory())
    },[])

    console.log(categories)

    const [resume, setResume] = useState({
        title:"",
        detail: "",
        selfIntroduce: "",
        artist: "",
        category: "",
    });

    const [resumeFile, setResumeFile] = useState([{
        fileTitle: "",
        fileDetail: "",
        fileWorkedDate: "",
        uuid: "",
        fname : "",
        repImg: false
        
    }]);
    

    const repImg = (e) => {
        e.stopPropagation()
        e.preventDefault()
        setResumeFile([{repImg: true}])
    }

    const fileAdd = (e) =>{
        e.stopPropagation()
        e.preventDefault()
        setResumeFile(resumeFile.length + 1)
    }

    const handleChange = (e) => {
        e.stopPropagation()
        e.preventDefault()
        const {name, value} = e.target;
        setResume({...resume, [name] : value})
    }

    const handleClick = (e) =>{
        e.stopPropagation()
        e.preventDefault()
        dispatch(registerResume())
    }

    const [files, setFiles] = useState([])
    const [uploadResult, setUploadResult] = useState(fileParam)

    useImperativeHandle(cref, () => ({

        send() {
            getUploadedFiles(uploadResult)
            setFiles([])
            setUploadResult([])
        }

    }));

    const uploadFile = useCallback(e => {
         e.preventDefault();
        console.dir(e.target.files);
        const formData = new FormData()
        const files = e.target.files
        for(let i = 0; i < files.length ; i++){
            formData.append("uploadFiles", files[i])
        }
        console.log("formData: "+JSON.stringify (formData))

        axios.post("http://localhost:8080/resume_file/upload_file",formData,
        {headers: { "Content-Type": "multipart/form-data"}})
        .then(res => {
            if (res.ok) {
                alert("Perfect! ");
              } else if (res.status == 401) {
                alert("Oops! ");
              }
            console.log("res" + res)

            res.data.forEach(uploadFileInfo =>  uploadResult.push(uploadFileInfo))

            console.log(JSON.stringify(uploadResult))

            setUploadResult(uploadResult.slice(0))
    })
    .catch(e => {
        alert("Error submitting form!");

    })
})

    return (<> 
    <div className="container" style={{marginTop: "40px"}}>
            <div className="col-md-9 col-sm-9 col-xs-12 xs-mb-50">
                <div className="row">
                    <div className="col-md-12 col-sm-12 col-xs-12 mb-20 xs-mb-50 ">
                            
                            <label className="font-20px">첨부파일</label>
                            <input type="file" name="imageFile" onChange={uploadFile} multiple={true}
                            accept="image/*,audio/*,video/mp4,video/x-m4v,application/pdf,application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.ms-powerpoint,application/vnd.openxmlformats-officedocument.presentationml.presentation,.csv" 
                            />
                            {uploadResult.map(uploadFile => {
                        return (
                            <div key={uploadFile.uuid}>
                                <img src={"http://localhost:8080/display?fileName=s_" + uploadFile.uuid+"_"+ uploadFile.fname }/>
                                {uploadFile.fname}
                            </div>
                            )
                            })
                                }
                            <label className="font-20px">작품 작업 날짜</label>
                            <input type="text" placeholder="fileWorkedDate"/>
                            <label className="font-20px">파일 제목</label>
                            <input type="text" placeholder="fileTitle"/>
                            <label  className="font-20px">파일 세부사항</label>
                            <textarea placeholder="fileDetail"/>
                            <label className="font-20px">대표이미지로 선택하시겠습니까?</label><br/>
                            <button className="btn btn-md btn-dark-outline btn-square mt-10" 
                            onClick={(e)=> repImg(e)}>
                            Yes</button>
                            <button className="btn btn-md btn-dark-outline btn-square mt-10">
                            NO</button>
                            <div style={{marginTop: "40px"}}>
                            <label  className="font-20px">파일 추가</label><br/>
                            <button className="btn btn-md btn-dark-outline btn-square mt-10" 
                            onClick={(e)=> fileAdd(e)}>
                            Yes</button>
                            <button className="btn btn-md btn-dark-outline btn-square mt-10">
                            NO</button>
                            </div>
                        <div className="post-info all-padding-20" style={{marginTop: "40px"}}>
                            <label className="font-20px">포트폴리오 타이틀을 적어주세요</label>
                            <input type="text" placeholder="resumeTitle"/>
                        </div>
                </div>
                    <div className="col-md-12 col-sm-12 col-xs-12 mb-20">
                        <div className="blog-standard">
                            <blockquote>
                            <label className="font-20px">포트폴리오의 세부사항을 적어주세요</label>
                            <textarea type="text" placeholder="resumeDetail"/>
                            </blockquote>
                        </div>
                    </div>
                </div>
            </div>
            <div className="col-md-3 col-sm-3 col-xs-12">
                <div className="widget sidebar_widget">
                    <h5 className="aside-title">아티스트 소개</h5>
                    <form className="search-form" method="post">
                    <h4 className="aside-title">이름</h4>
                    </form>
                </div>
                <div className="mb-50">
                    <label className="font-20px">자기소개글을 써주세요</label>
                    <textarea type="text" placeholder="resumeDetail"/>
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
</>)
}

export default ResumeRegister