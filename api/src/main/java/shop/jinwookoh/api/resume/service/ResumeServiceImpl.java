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
@Transactional
public class ResumeServiceImpl extends AbstractService<ResumeDto> implements ResumeService {

    private final ResumeRepository repo;
    private final ResumeFileRepository fileRepo;

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

    @Override
    public String delete(ResumeDto resumeDto) {
        Resume resume = Resume.builder().resumeId(resumeDto.getResumeId()).build();
        repo.findById(resume.getResumeId()).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
        fileRepo.deleteByResumeId(resume.getResumeId());
        repo.delete(resume);
        return (repo.findById(resume.getResumeId()).isPresent()) ? "Delete Failed" : "Delete Success";
    }

    @Override
    public PageResultDto<ResumeDto, Resume> getAllDataPaging(int page) {
        return new PageResultDto<>(repo.getAllDataPaging(conditionPage(page)), makeDtoPage());
    }

    @Override
    public PageResultDto<ResumeDto, Resume> getUserPKDataPage(Long artistId, int page) {
        return new PageResultDto<>(repo.getUserPKDataPage(artistId, conditionPage(page)), makeDtoPage());
    }

    @Override
    public PageResultDto<ResumeDto, Resume> getCategoryPKDataPage(Long categoryId, int page) {
        return new PageResultDto<>(repo.getCategoryPKDataPage(categoryId, conditionPage(page)), makeDtoPage());
    }

    @Override
    public PageResultDto<ResumeDto, Resume> getCategoryAndUserDataPage(Long categoryId, Long artistId, int page) {

        return new PageResultDto<>(repo.getCategoryAndUserDataPage(categoryId, artistId, conditionPage(page)),
                makeDtoPage());
    }

    @Override
    public Page<Object[]> conditionSearch(String type, String keyword, int page) {

        return repo.searchPage(type, keyword, conditionPage(page));
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
