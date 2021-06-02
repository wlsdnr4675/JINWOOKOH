import React, { useState } from "react"
import { useDispatch } from "react-redux";
import { changeFileList, delFileList } from "webapp/resume/reducer/resume.reducer";
import ReusumeFile from "./ResumeFile";


const ResumeFileItem = ({uuid, fname}) => {
    const dispatch = useDispatch()

    const [resumeFile, setResumeFile] = useState({
        fileTitle: "",
        fileDetail: "",
        fileWorkedDate: "",
        uuid: uuid,
        fname: fname,
        repImg: false
    });
    const resumeFileChange =  (e) => {
        e.stopPropagation()
        e.preventDefault()
        const {name, value} = e.target;
        resumeFile [name] = value;
        setResumeFile({...resumeFile})
        console.log(resumeFile)
        dispatch(changeFileList({uuid: e.target.getAttribute("data-uuid"), file: {...resumeFile}}))
  
    }
    const repTrue = (e) => {
        e.stopPropagation()
        e.preventDefault()
        console.log(e.target.value)
        resumeFile.repImg = !resumeFile.repImg;
        setResumeFile({...resumeFile})
        dispatch(changeFileList({uuid: e.target.getAttribute("data-uuid"), file: {...resumeFile}}))
        console.log(e.target.value)
        console.log("ssss",resumeFile)
    }

    const resumeFileDelete=(e)=>{
        e.stopPropagation()
        e.preventDefault()
        dispatch(delFileList({uuid: e.target.getAttribute("data-uuid"), file: {...resumeFile}}))
    }
    return (<>
        <div> 
        <img src={`http://localhost:8080/resume_file/display?fileName=${resumeFile.uuid + "_" + resumeFile.fname}`}/><br/>
            {resumeFile.fname}
        <button className="btn mt-10" style={{marginLeft: "10px"}}
        value={resumeFile.repImg} name="repImg" data-uuid={resumeFile.uuid}
        onClick={(e)=>resumeFileDelete(e)}>
        파일업로드 취소</button>                        
        </div>
        <label className="font-20px">작품 작업 날짜</label>
        <input className="mfp-title" data-uuid={resumeFile.uuid} type="text" name="fileWorkedDate" style={{color:"black", wordWrap:"break-word"}}
        value={resumeFile.fileWorkedDate} placeholder="fileWorkedDate"
        onChange={(e) =>resumeFileChange(e)}/>
        <label className="font-20px">파일 제목</label>
        <input type="text" data-uuid={resumeFile.uuid}  name="fileTitle" style={{color:"black"}}
        value={resumeFile.fileTitle} placeholder="fileTitle"
        onChange={(e) =>resumeFileChange(e)}/>
        <label  className="font-20px">파일 세부사항</label>
        <textarea  name="fileDetail" data-uuid={resumeFile.uuid} style={{color:"black", height:"200px"}}
        value={resumeFile.fileDetail} placeholder="fileDetail"
        onChange={(e) =>resumeFileChange(e)}/>
        {!resumeFile.repImg ?<>
        <label className="font-20px">대표이미지로 선택하시겠습니까?</label><br/>
        <button className="btn btn-md btn-dark-outline btn-square mt-10"
        value={resumeFile.repImg} name="repImg" data-uuid={resumeFile.uuid}
        onClick={(e)=>repTrue(e)}>
        Yes</button>
        <button className="btn btn-md btn-dark-outline btn-square mt-10"
       value={resumeFile.repImg} name="repImg"
       onClick={(e)=>repTrue(e)}>
        NO</button> </>: <></>}
     </>);
}
 
export default ResumeFileItem;