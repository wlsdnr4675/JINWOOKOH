package shop.jinwookoh.api.resume.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import shop.jinwookoh.api.common.service.AbstractService;
import shop.jinwookoh.api.resume.domain.Resume;
import shop.jinwookoh.api.resume.domain.ResumeDto;
import shop.jinwookoh.api.resume.domain.ResumeFile;
import shop.jinwookoh.api.resume.domain.ResumeFileDto;
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
    public String save(ResumeDto resumeDto) {

        Resume resume = Resume.of(resumeDto);
        resume.saveAll(resumeDto);
        log.info(resume.getDetail());

        ArrayList<ResumeFileDto> files = resumeDto.getResumeFiles();
        if (!files.isEmpty()) {

            files.forEach(ResumeFileDto -> {
                ResumeFile rf = dtoToEntityResumeFile(ResumeFileDto);
                rf.confirmResume(resume);
                fileRepo.save(rf);

            });
        }
        return (repo.save(resume) != null) ? "Save Success" : "Save Failed";
    }

    @Transactional
    @Override
    public List<Resume> getAllResume() {
        // TODO Auto-generated method stub
        return repo.getAllResume();
    }

    @Override
    public List<ResumeDto> findAll() {
        // TODO Auto-generated method stub

        return null;
    }

    @Override
    public ResumeDto findById(Long id) {
        Resume resume = repo.findById(id).orElseThrow(IllegalArgumentException::new);
        ResumeDto resumeDto = ResumeDto.of(resume);
        return resumeDto;
    }

    @Override
    public Long count() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<ResumeDto> getOne(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String delete(ResumeDto resumeDto) {
        Resume resume = Resume.of(resumeDto);
        repo.delete(resume);

        return (repo.findById(resume.getResumeId()) == null) ? "Delete Success" : "Delete Failed";
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

}
