package shop.jinwookoh.api.resume.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import shop.jinwookoh.api.resume.domain.Resume;
import shop.jinwookoh.api.resume.domain.ResumeDto;
import shop.jinwookoh.api.resume.domain.ResumeFile;
import shop.jinwookoh.api.resume.domain.ResumeFileDto;

public interface ResumeService {

    List<Resume> getAllResume();

    default Resume dtoToEntity(ResumeDto dto) {

        Resume entity = Resume.builder().title(dto.getTitle()).selfIntroduce(dto.getSelfIntroduce())
                .detail(dto.getDetail()).build();

        return entity;
    }

    default ResumeFile dtoToEntityResumeFile(ResumeFileDto dto) {

        ResumeFile picture = ResumeFile.builder().uuid(dto.getUuid()).fileTitle(dto.getFileTitle())
                .fileDetail(dto.getFileDetail()).fname(dto.getFname()).repImg(dto.getRepImg())
                .workedDate(dto.getWorkedDate()).build();
        return picture;
    }

}
