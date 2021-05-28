import { Dialog } from '@material-ui/core';
import React, { useEffect, useState } from 'react'
import { useDispatch, useSelector } from 'react-redux';
import { readResume } from '../reducer/resume.reduce';

const ResumeRead = ({open, handleClose}) => {
    const items = useSelector(state => state.resumes.current);
    console.log("sss",items)
  
    
    return (<>
        <Dialog open ={open} onClose={handleClose}>
         <button >dfdfdfdfdfdfdfdfdf</button>
        </Dialog>
    </>)
}

export default ResumeRead