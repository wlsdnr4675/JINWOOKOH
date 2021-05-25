package shop.jinwookoh.api.resume.service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import shop.jinwookoh.api.resume.domain.Resume;
import shop.jinwookoh.api.resume.domain.ResumeDto;
import shop.jinwookoh.api.resume.domain.ResumeFile;
import shop.jinwookoh.api.resume.domain.ResumeFileDto;
import shop.jinwookoh.api.resume.domain.page.PageResultDto;

public interface ResumeService {

    String resumeSaveWithFile(ResumeDto resumeDto);

    String editResume(ResumeDto resumeDto);

    default ResumeFile dtoToEntityResumeFile(ResumeFileDto dto) {

        return ResumeFile.builder().uuid(dto.getUuid()).fname(dto.getFname()).repImg(dto.getRepImg()).build();
    }

    default ResumeDto resumeEntityToDto(Resume en) {

        return ResumeDto.builder().resumeId(en.getResumeId()).title(en.getTitle()).detail(en.getDetail())
                .selfIntroduce(en.getSelfIntroduce()).resumeFiles(en.getResumeFiles().stream()
                        .map(resumeFile -> ResumeFileDto.of(resumeFile)).collect(Collectors.toList()))
                .artist(en.getArtist()).category(en.getCategory()).build();
    }

    default Pageable conditionPage(int page) {

        return PageRequest.of(page <= 0 ? 0 : page - 1, 5, Sort.Direction.DESC, "resumeId");

    }

    default Function<Resume, ResumeDto> makeDtoPage() {

        return (en -> resumeEntityToDto(en));
    }

    PageResultDto<ResumeDto, Resume> getAllDataPaging(int page);

    List<ResumeDto> getAllResume();

    PageResultDto<ResumeDto, Resume> getUserPKDataPage(Long artistId, int page);

    PageResultDto<ResumeDto, Resume> getCategoryPKDataPage(Long categoryId, int page);

    PageResultDto<ResumeDto, Resume> getCategoryAndUserDataPage(Long categoryId, Long artistId, int page);

    PageResultDto<ResumeDto, Resume> searchUserNameDataPage(String username, int page);

    PageResultDto<ResumeDto, Resume> searchNameDataPage(String name, int page);

    PageResultDto<ResumeDto, Resume> searchCategoryDataPage(String categoryName, int page);

    PageResultDto<ResumeDto, Resume> searchCategoryAndUserDataPage(String name, String categoryName, int page);

    PageResultDto<ResumeDto, Resume> searchTitleDataPage(String title, int page);

    PageResultDto<ResumeDto, Resume> searchDetailDataPage(String detail, int page);

}
