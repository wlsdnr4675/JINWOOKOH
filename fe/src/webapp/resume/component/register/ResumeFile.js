import React,{useState, useImperativeHandle} from "react"
import axios from "axios"
import { ResumeService } from "webapp/resume"
import { useDispatch, useSelector } from "react-redux"
import { addFileList } from "webapp/resume/reducer/resume.reducer"

const ReusumeFile = ({cref, getUploadedFiles, fileParam=[]}) => {

    const dispatch= useDispatch()
    const fileList = useSelector(state => state.resumes.fileList)
    const [files, setFiles] = useState([])

    const [uploadResult, setUploadResult] = useState(fileParam)

    const [resumeFile, setResumeFile] = useState([{
        fileTitle: "",
        fileDetail: "",
        fileWorkedDate: "",
        repImg: false
        
    }]);

        const resumeFileChange = (e) => {
        e.stopPropagation()
        e.preventDefault()
        const {name, value} = e.target;
        setResumeFile({...resumeFile, [name] : value, uploadResult})
    }

    useImperativeHandle(cref, () => ({
        send() {
            getUploadedFiles(uploadResult)
            setFiles([])
            setUploadResult([])
        }
    }));
    
    const repImg = (e) => {
        e.stopPropagation()
        e.preventDefault()
        setResumeFile([{repImg: true}])
    }

    const uploadFile = (e) => {
        
        e.preventDefault() 
        console.dir(e.target.files);
        const formData = new FormData()
        const files = e.target.files
        for(let i = 0; i < files.length ; i++){
            formData.append("uploadFiles", files[i])
        }
        console.log("formData: "+JSON.stringify (formData))
        
        ResumeService.uploadFile(formData)
        .then(res => {
            console.log(res)
            res.data.forEach(uploadFileInfo =>  uploadResult.push(uploadFileInfo))
            console.log("uploadResult",uploadResult)
            setUploadResult(uploadResult.slice(0))
            console.log("resumeFile",resumeFile)

        })
        .then(
            // dispatch(addFileList(uploadResult))
        )
        console.log("fileList redux",fileList)
    }
    return (<> 
    <div className="container dark-color" style={{marginBottom: "40px", color: "#24182e"}} >
            <div className="">
                            <label className="font-20px">첨부파일</label>
                            <input type="file" name="imageFile"  onChange={uploadFile} multiple={true}
                            accept="image/*,application/pdf,application/vnd.ms-powerpoint" 
                            />
                            {uploadResult.map(uploadFile => {
                        return (
                            <div key={uploadFile.uuid}>
                                <img src={`http://localhost:8080/resume_file/display?fileName=${"s_" +uploadFile.uuid + "_" + uploadFile.fname}`}/>
                                {uploadFile.fname}
                                <br/>
                                <label className="font-20px">작품 작업 날짜</label>
                                <input className="mfp-title" type="text" name="fileWorkedDate" style={{color:"black", wordWrap:"break-word"}}
                                value={resumeFile.fileWorkedDate} placeholder="fileWorkedDate"
                                onChange={()=>uploadFile()}/>
                            <label className="font-20px">파일 제목</label>
                            <input type="text"  name="fileTitle" style={{color:"black"}}
                            value={resumeFile.fileTitle} placeholder="fileTitle"
                            onChange={(e)=>resumeFileChange(e)}/>
                            <label  className="font-20px">파일 세부사항</label>
                            <textarea  name="fileDetail" style={{color:"black", height:"200px"}}
                            value={resumeFile.fileDetail} placeholder="fileDetail"
                            onChange={(e)=>resumeFileChange(e)}/>
                            <label className="font-20px">대표이미지로 선택하시겠습니까?</label><br/>
                            <button className="btn btn-md btn-dark-outline btn-square mt-10" 
                            onClick={(e)=> repImg(e)}>
                            Yes</button>
                            <button className="btn btn-md btn-dark-outline btn-square mt-10">
                            NO</button>
                            </div>
                            )
                            })
                                }
                            
        </div>
    </div>
     </>);
}
 
export default ReusumeFile;