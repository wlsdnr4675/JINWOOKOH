import React, { useState } from "react"
import { useDispatch } from "react-redux";
import { changeFileList } from "webapp/resume/reducer/resume.reducer";
import ReusumeFile from "./ResumeFile";


const ResumeFileItem = ({uuid, fname}) => {
    console.log(fname)
    const dispatch = useDispatch()

    const [resumeFile, setResumeFile] = useState({
        fileTitle: "",
        fileDetail: "",
        fileWorkedDate: "",
        repImg: false,
        uuid: uuid,
        fname: fname
    });
    const resumeFileChange =  (e) => {
        e.stopPropagation()
        e.preventDefault()
        const currentUuid= e.target.getAttribute("data-uuid")
        const {name, value} = e.target;
        resumeFile [name] = value;
        setResumeFile({...resumeFile })
        console.log(resumeFile)
        dispatch(changeFileList({uuid: currentUuid, file: {...resumeFile}}))
  
    }



    return (<> 
    <div>
          <img src={`http://localhost:8080/resume_file/display?fileName=${"w_"+resumeFile.uuid + "_" + resumeFile.fname}`}/>
                                {resumeFile.fname}
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
        <label className="font-20px">대표이미지로 선택하시겠습니까?</label><br/>
        <button className="btn btn-md btn-dark-outline btn-square mt-10"
        name="repImag" value={resumeFile.repImg}
        onClick={(e) => resumeFileChange(e)}>
        Yes</button>
        <button className="btn btn-md btn-dark-outline btn-square mt-10">
        NO</button>
    </div>


     </>);
}
 
export default ResumeFileItem;