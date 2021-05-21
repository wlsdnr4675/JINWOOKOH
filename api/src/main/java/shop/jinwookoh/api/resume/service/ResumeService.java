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

    String resumeSaveWithFile(ResumeDto resumeDto);

    default ResumeFile dtoToEntityResumeFile(ResumeFileDto dto) {

        ResumeFile picture = ResumeFile.builder().uuid(dto.getUuid()).fname(dto.getFname()).repImg(dto.getRepImg())
                .build();
        return picture;
    }

}
