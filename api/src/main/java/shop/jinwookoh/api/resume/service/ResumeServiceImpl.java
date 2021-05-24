package shop.jinwookoh.api.resume.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import shop.jinwookoh.api.common.service.AbstractService;
import shop.jinwookoh.api.resume.domain.Resume;
import shop.jinwookoh.api.resume.domain.ResumeDto;
import shop.jinwookoh.api.resume.domain.ResumeFile;
import shop.jinwookoh.api.resume.domain.ResumeFileDto;
import shop.jinwookoh.api.resume.domain.page.PageResultDto;
import shop.jinwookoh.api.resume.repository.ResumeFileRepository;
import shop.jinwookoh.api.resume.repository.ResumeRepository;

@Service
@RequiredArgsConstructor
@Log4j2
public class ResumeServiceImpl extends AbstractService<ResumeDto> implements ResumeService {

    private final ResumeRepository repo;
    private final ResumeFileRepository fileRepo;

    @Transactional
    @Override
    public String resumeSaveWithFile(ResumeDto resumeDto) {

        Resume resume = Resume.of(resumeDto);
        resume.saveAll(resumeDto);
        log.info(resume.getDetail());
        List<ResumeFileDto> files = resumeDto.getResumeFiles();
        if (!files.isEmpty()) {
            files.forEach(resumeFileDtos -> {
                ResumeFile rf = dtoToEntityResumeFile(resumeFileDtos);
                rf.saveDetails(resumeFileDtos);
                rf.confirmResume(resume);
                fileRepo.save(rf);
            });
        }

        return (repo.save(resume) != null) ? "Save Success" : "Save Failed";
    }

    @Transactional
    @Override
    public String editResume(ResumeDto resumeDto) {
        Resume resume = Resume.of(resumeDto);
        resume = repo.getOne(resumeDto.getResumeId());
        resume.saveAll(resumeDto);
        List<ResumeFileDto> files = resumeDto.getResumeFiles();
        if (!files.isEmpty()) {
            files.forEach(resumeFileDtos -> {
                ResumeFile rf = dtoToEntityResumeFile(resumeFileDtos);
                rf = fileRepo.getOne(resumeFileDtos.getResumeFileId());
                rf.saveDetails(resumeFileDtos);
                fileRepo.save(rf);
            });
        }
        return (repo.save(resume) != null) ? "Update Success" : "Update Failed";
    }

    @Override
    public ResumeDto findById(Long resumeId) {
        Resume resume = repo.findById(resumeId).orElseThrow(IllegalArgumentException::new);

        return ResumeDto.of(resume);
    }

    @Transactional
    @Override
    public String delete(ResumeDto resumeDto) {
        Resume resume = Resume.builder().resumeId(resumeDto.getResumeId()).build();
        repo.findById(resume.getResumeId()).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
        fileRepo.deleteByResumeId(resume.getResumeId());
        repo.delete(resume);
        return (repo.findById(resume.getResumeId()).isPresent()) ? "Delete Success" : "Delete Failed";
    }

    @Override
    public PageResultDto<ResumeDto, Resume> getAllDataPaging(int page) {
        Pageable pageable = PageRequest.of(page <= 0 ? 0 : page - 1, 5, Sort.Direction.DESC, "resumeId");
        Page<Resume> result = repo.getAllDataPaging(pageable);
        Function<Resume, ResumeDto> fn = (en -> resumeEntityToDto(en));
        return new PageResultDto<>(result, fn);
    }

    @Override
    public PageResultDto<ResumeDto, Resume> getUserPKDataPage(Long artistId, int page) {
        Pageable pageable = PageRequest.of(page <= 0 ? 0 : page - 1, 5, Sort.Direction.DESC, "resumeId");
        Page<Resume> result = repo.getAllDataPaging(pageable);
        Function<Resume, ResumeDto> fn = (en -> resumeEntityToDto(en));
        return new PageResultDto<>(result, fn);
    }

    @Override
    public PageResultDto<ResumeDto, Resume> getCategoryPKDataPage(Long categoryId, int page) {
        Pageable pageable = PageRequest.of(page <= 0 ? 0 : page - 1, 5, Sort.Direction.DESC, "resumeId");
        Page<Resume> result = repo.getAllDataPaging(pageable);
        Function<Resume, ResumeDto> fn = (en -> resumeEntityToDto(en));
        return new PageResultDto<>(result, fn);
    }

    @Override
    public PageResultDto<ResumeDto, Resume> getCategoryAndUserDataPage(Long categoryId, Long artistId, int page) {
        Pageable pageable = PageRequest.of(page <= 0 ? 0 : page - 1, 5, Sort.Direction.DESC, "resumeId");
        Page<Resume> result = repo.getAllDataPaging(pageable);
        Function<Resume, ResumeDto> fn = (en -> resumeEntityToDto(en));
        return new PageResultDto<>(result, fn);
    }

    @Override
    public PageResultDto<ResumeDto, Resume> searchUserNameDataPage(String username, int page) {
        Pageable pageable = PageRequest.of(page <= 0 ? 0 : page - 1, 5, Sort.Direction.DESC, "resumeId");
        Page<Resume> result = repo.getAllDataPaging(pageable);
        Function<Resume, ResumeDto> fn = (en -> resumeEntityToDto(en));
        return new PageResultDto<>(result, fn);
    }

    @Override
    public PageResultDto<ResumeDto, Resume> searchNameDataPage(String name, int page) {
        Pageable pageable = PageRequest.of(page <= 0 ? 0 : page - 1, 5, Sort.Direction.DESC, "resumeId");
        Page<Resume> result = repo.getAllDataPaging(pageable);
        Function<Resume, ResumeDto> fn = (en -> resumeEntityToDto(en));
        return new PageResultDto<>(result, fn);
    }

    @Override
    public PageResultDto<ResumeDto, Resume> searchCategoryDataPage(String categoryName, int page) {
        Pageable pageable = PageRequest.of(page <= 0 ? 0 : page - 1, 5, Sort.Direction.DESC, "resumeId");
        Page<Resume> result = repo.getAllDataPaging(pageable);
        Function<Resume, ResumeDto> fn = (en -> resumeEntityToDto(en));
        return new PageResultDto<>(result, fn);
    }

    @Override
    public PageResultDto<ResumeDto, Resume> searchCategoryAndUserDataPage(String name, String categoryName, int page) {
        Pageable pageable = PageRequest.of(page <= 0 ? 0 : page - 1, 5, Sort.Direction.DESC, "resumeId");
        Page<Resume> result = repo.getAllDataPaging(pageable);
        Function<Resume, ResumeDto> fn = (en -> resumeEntityToDto(en));
        return new PageResultDto<>(result, fn);
    }

    @Override
    public PageResultDto<ResumeDto, Resume> searchTitleDataPage(String title, int page) {
        Pageable pageable = PageRequest.of(page <= 0 ? 0 : page - 1, 5, Sort.Direction.DESC, "resumeId");
        Page<Resume> result = repo.getAllDataPaging(pageable);
        Function<Resume, ResumeDto> fn = (en -> resumeEntityToDto(en));
        return new PageResultDto<>(result, fn);
    }

    @Override
    public PageResultDto<ResumeDto, Resume> searchDetailDataPage(String detail, int page) {
        Pageable pageable = PageRequest.of(page <= 0 ? 0 : page - 1, 5, Sort.Direction.DESC, "resumeId");
        Page<Resume> result = repo.getAllDataPaging(pageable);
        Function<Resume, ResumeDto> fn = (en -> resumeEntityToDto(en));
        return new PageResultDto<>(result, fn);
    }

    @Override
    public List<ResumeDto> getAllResume() {
        List<Resume> resumes = repo.getAllResume();
        return resumes.stream().map(resume -> ResumeDto.of(resume)).collect(Collectors.toList());
    }

    @Override
    public List<ResumeDto> findAll() {

        return null;
    }

    @Override
    public Long count() {

        return repo.count();
    }

    @Override
    public Optional<ResumeDto> getOne(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteAll() {
        // TODO Auto-generated method stub

    }

    @Override
    public Boolean existsById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub

    }

    @Override
    public String save(ResumeDto t) {
        // TODO Auto-generated method stub
        return null;
    }

}
