import React, { useState } from "react"
import { useDispatch, useSelector } from "react-redux";
import { changeFileList, delFileList } from "webapp/resume/reducer/resume.reducer";


const ModifyFileItem = ({uuid, fname, fileDetail, fileTitle, fileWorkedDate}) => {
    
    const dispatch = useDispatch()
    const fileList = useSelector(state => state.resumes.current.resumeFiles)
   
    const [resumeFile, setResumeFile] = useState({
        fileTitle: fileTitle,
        fileDetail: fileDetail,
        fileWorkedDate: fileWorkedDate,
        uuid: uuid,
        fname: fname,
        repImg: false
    });
    console.log("fnamefnamefnamefnamefname",resumeFile.fname)
    const resumeFileChange =  (e) => {
        e.stopPropagation()
        e.preventDefault()
        const {name, value} = e.target;
        resumeFile [name] = value;
        setResumeFile({...resumeFile})
        console.log("resumeFileChange : ", resumeFile)
        dispatch(changeFileList({uuid: e.target.getAttribute("data-uuid"), file: {...resumeFile}}))
  
    }
    const repTrue = (e) => {
        e.stopPropagation()
        e.preventDefault()
        resumeFile.repImg = !resumeFile.repImg;
        setResumeFile({...resumeFile})
        console.log("e.target.getAttribute: ", e.target.getAttribute("data-uuid"))
        dispatch(changeFileList({uuid: e.target.getAttribute("data-uuid"), file: {...resumeFile}}))
    }

    const resumeFileDelete=(e)=>{
        e.stopPropagation()
        e.preventDefault()
        dispatch(delFileList({uuid: e.target.getAttribute("data-uuid"), file: {...resumeFile}}))
    }

    return (<>
    <div className="container">
        <div className="col-md-12 col-sm-12 col-xs-12 mb-20 xs-mb-50">
        <img src={`http://localhost:8080/resume_file/display?fileName=${resumeFile.uuid + "_" + resumeFile.fname}`}/><br/>
            {resumeFile.fname}
        <button className="btn mt-10" style={{marginLeft: "10px"}}
        value={resumeFile.repImg} name="repImg" data-uuid={resumeFile.uuid}
        onClick={(e)=>resumeFileDelete(e)}>
        파일업로드 취소</button>                        
        <br/>
        <label className="font-20px">작품 작업 날짜</label>
        <input data-uuid={resumeFile.uuid} type="text" name="fileWorkedDate" style={{color:"black", wordWrap:"break-word"}}
        value={resumeFile.fileWorkedDate} placeholder="fileWorkedDate"
        onChange={(e) =>resumeFileChange(e)}/>
        <label className="font-20px">파일 제목</label>
        <input type="text" data-uuid={resumeFile.uuid}  name="fileTitle" style={{color:"black"}}
        value={resumeFile.fileTitle} placeholder="fileTitle"
        onChange={(e) =>resumeFileChange(e)}/>
        <label  className="font-20px">파일 세부사항</label>
        <textarea type="text"  name="fileDetail" data-uuid={resumeFile.uuid} style={{color:"black", height:"200px"}}
        value={resumeFile.fileDetail} placeholder="fileDetail"
        onChange={(e) =>resumeFileChange(e)}/>
        {!resumeFile.repImg ?<>
        <label className="font-20px">대표이미지로 선택하시겠습니까?</label><br/>
        <button className="btn btn-md btn-dark-outline btn-square mt-10"
        value={resumeFile.repImg} name="repImg" data-uuid={resumeFile.uuid}
        onClick={(e)=>repTrue(e)}>
        CHECK</button>
        </>: <></>}
        </div>
    </div>
     </>);
}
 
export default ModifyFileItem;