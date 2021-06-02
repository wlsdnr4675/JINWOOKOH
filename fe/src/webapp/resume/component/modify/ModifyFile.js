import React,{useState, useImperativeHandle} from "react"
import { ModifyFileItem, ResumeService } from "webapp/resume"
import { useDispatch, useSelector } from "react-redux"
import { addFileList, changeFileList } from "webapp/resume/reducer/resume.reducer"


const ModifyFile = ({cref, getUploadedFiles, fileParam=[]}) => {

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

    return (<> 
            <label className="font-20px">첨부파일</label>
            <input type="file" name="imageFile"/>
            <ModifyFileItem /> 
     </>);
}
 
export default ModifyFile;