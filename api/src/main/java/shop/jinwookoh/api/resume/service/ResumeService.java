package shop.jinwookoh.api.resume.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import shop.jinwookoh.api.resume.domain.Resume;
import shop.jinwookoh.api.resume.domain.ResumeDto;
import shop.jinwookoh.api.resume.domain.ResumeFile;
import shop.jinwookoh.api.resume.domain.ResumeFileDto;

public interface ResumeService {

    String resumeSaveWithFile(ResumeDto resumeDto);

    default ResumeFile dtoToEntityResumeFile(ResumeFileDto dto) {

        ResumeFile picture = ResumeFile.builder().uuid(dto.getUuid()).fname(dto.getFname()).repImg(dto.getRepImg())
                .build();
        return picture;
    }

    Page<Resume> getAllDataPaging(Pageable pageable);

    List<Resume> getAllResume();

    // Page<List<Resume>> getUserPKDataPage(Long artistId, Pageable pageable);

    // Page<List<Resume>> getCategoryPKDataPage(Long categoryId, Pageable pageable);

    // Page<List<Resume>> getCategoryAndUserDataPage(Long categoryId, Long artistId,
    // Pageable pageable);

    // Page<List<Resume>> searchUserNameDataPage(String username, Pageable
    // pageable);

    // Page<List<Resume>> searchNameDataPage(String name, Pageable pageable);

    // Page<List<Resume>> searchCategoryDataPage(String categoryName, Pageable
    // pageable);

    // Page<List<Resume>> searchCategoryAndUserDataPage(String name, String
    // categoryName, Pageable pageable);

    // Page<List<Resume>> searchTitleDataPage(String title, Pageable pageable);

    // Page<List<Resume>> searchDetailDataPage(String detail, Pageable pageable);

}
