import { Dialog, Modal } from '@material-ui/core';
import React, { useEffect, useState } from 'react'
import { useDispatch, useSelector } from 'react-redux';
import { readResume } from '../reducer/resume.reduce';

const ResumeRead = ({resumeId, open , handleClose}) => {
    const items = useSelector(state => state.resumes.current);
    const [resumeItem,setResumeItem] = useState([]);

    console.log("Read ResumeId",resumeId)
    console.log("2",items)
    console.log("3",handleClose)

    useEffect(()=>{
        setResumeItem(items)
    },[items]) 

    console.log("4",resumeItem)
    console.log("5", Array.isArray(resumeItem))
    console.log(resumeItem)
  
        console.log("resumeItem.name",resumeItem.name)
  
    
    return (<>
        <Dialog open ={open} onClose={handleClose}>
         <h2>{resumeItem.name}</h2>
        </Dialog>
    </>)
}

export default ResumeRead