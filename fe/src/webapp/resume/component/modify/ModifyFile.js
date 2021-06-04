import React,{useState, useImperativeHandle, useEffect} from "react"
import { ModifyFileItem, ResumeService } from "webapp/resume/index"
import { useDispatch, useSelector } from "react-redux"
import { addFileList} from "webapp/resume/reducer/resume.reducer"


const ModifyFile = ({fileParam=[]}) => {

    const dispatch= useDispatch()
    const fileList = useSelector(state => state.resumes.fileList)
    console.log("MODIFY FILE's fileList.file :", fileList)

    const [uploadResult, setUploadResult] = useState(fileParam)

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
        <div className="container">
        <div className="col-md-12 col-sm-12 col-xs-12 mb-20">
            <label className="font-20px">첨부파일 추가 </label>
            <input type="file" name="imageFile" onChange={(e)=>uploadFile(e)} multiple={true}/>
            {fileList?.map(fileObj => {
                    return(
                    <ModifyFileItem key={fileObj.uuid} {...fileObj.file}/>
                    )})}          
        </div>
        </div>
     </>);
}
 
export default ModifyFile;