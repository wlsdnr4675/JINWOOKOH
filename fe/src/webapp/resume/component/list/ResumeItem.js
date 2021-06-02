import React from "react";


const ResumeItem = ({ resumeId, resumeFiles, title, name, category, fn, artistId}) => {
  

  const fileImg = resumeFiles.map(file => {
    return(
      file.repImg ?
      <img key={file.resumeFileId} src={`http://localhost:8080/resume_file/display?fileName=${"s_" + file.uuid + "_" + file.fname}`} alt="이미지가 없습니다" />
      : <></>
    )
  })
  
  return (<>
    <div className="col-md-4">
      <div className="portfolio-item" onClick={(e) => fn(e,resumeId, artistId)}>
           {fileImg}
          <div className="portfolio-info gradient-bg" style={{position: "relative"}}>
              <div className="v-center white-smoke">
                <h3>{title}</h3>
                <p>{name}</p>
                <p>{category}</p>
            </div>
          </div>
      </div>
    </div>
  </>);
}


export default ResumeItem;
