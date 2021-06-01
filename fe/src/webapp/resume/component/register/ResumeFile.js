import React,{useState, useImperativeHandle} from "react"
import axios from "axios"
import { ResumeService } from "webapp/resume"
import { useDispatch, useSelector } from "react-redux"
import { addFileList, changeFileList } from "webapp/resume/reducer/resume.reducer"
import ResumeFileItem from "./ResumeFileItem"

const ResumeFile = ({cref, getUploadedFiles, fileParam=[]}) => {

    const dispatch= useDispatch()
    const fileList = useSelector(state => state.resumes.fileList)
    const [files, setFiles] = useState([])

    const [uploadResult, setUploadResult] = useState(fileParam)

    useImperativeHandle(cref, () => ({
        send() {
            getUploadedFiles(uploadResult)
            setFiles([])
            setUploadResult([])
        }
    }));

    const uploadFile = async (e) => {
        
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
            console.log("res : ",res)
            res.data.forEach(uploadFileInfo => {
                uploadResult.push(uploadFileInfo)
                dispatch(addFileList({uuid: uploadFileInfo.uuid, file: uploadFileInfo}))
            })
            setUploadResult(uploadResult.slice(0))
            console.log("uploadResult",uploadResult)
        })
    }
    return (<> 
        <div className="container dark-color" style={{marginBottom: "40px", color: "#24182e"}} >
            <div className="">
                <label className="font-20px">첨부파일</label>
                <input type="file" name="imageFile"  onChange={(e)=>uploadFile(e)} multiple={true}
                />
                {fileList?.map(fileObj => {
                    return(
                    <ResumeFileItem key={fileObj.uuid} {...fileObj.file}/>
                    )})}            
            </div>
        </div>
     </>);
}
 
export default ResumeFile;