package shop.jinwookoh.api.resume.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
    public String resumeSaveWithFile(ResumeDto resumeDto) {

        Resume resume = Resume.of(resumeDto);
        resume.saveAll(resumeDto);
        log.info(resume.getDetail());
        ArrayList<ResumeFileDto> files = resumeDto.getResumeFiles();
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
    public Page<Resume> getAllDataPaging(Pageable pageable) {

        return repo.getAllDataPaging(pageable);
    }

    @Transactional
    @Override
    public ResumeDto findById(Long resumeId) {
        Resume resume = repo.findById(resumeId).orElseThrow(IllegalArgumentException::new);
        System.out.println("Service REsumeId: " + resumeId);
        ResumeDto resumeDto = ResumeDto.of(resume);
        System.out.println("Service resumeDto: " + resumeDto);

        return resumeDto;
    }

    @Override
    public String delete(ResumeDto resumeDto) {
        Resume resume = Resume.builder().resumeId(resumeDto.getResumeId()).build();
        System.out.println("SERVICE DELETE" + resumeDto.getResumeId());
        System.out.println("SERVICE DELETE MODEL" + resume.getResumeId());

        repo.delete(resume);
        return (repo.findById(resume.getResumeId()) == null) ? "Delete Success" : "Delete Failed";
    }

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
