package shop.jinwookoh.api.resume.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import shop.jinwookoh.api.common.service.AbstractService;
import shop.jinwookoh.api.common.util.ModelMapperUtils;
import shop.jinwookoh.api.resume.domain.Resume;
import shop.jinwookoh.api.resume.domain.ResumeDto;
import shop.jinwookoh.api.resume.repository.ResumeRepository;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl extends AbstractService<Resume> implements ResumeService {

    private final ResumeRepository repo;

    @Override
    public ResumeDto findById(Long resumeId) {
        Resume resume = repo.findById(resumeId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + resumeId));
        ResumeDto resumeDto = ModelMapperUtils.getModelMapper().map(resume, ResumeDto.class);
        return resumeDto;
    }

    @Override
    @Transactional
    public String save(Resume resume) {
        return (repo.save(resume) != null) ? "Save Success" : "Failed";
    }

    @Override
    public List<Resume> findAll() {

        return repo.findAll();
    }

    @Override
    public Long count() {

        return repo.count();
    }

    @Override
    public Optional<Resume> getOne(long id) {

        return Optional.of(repo.getOne(id));
    }

    @Override
    public String delete(Resume resume) {
        repo.delete(resume);
        return (repo.findById(resume.getResumeId()).orElse(null) == null) ? "Delete Succes" : "Delete Failed";
    }

    @Override
    public void deleteAll() {

        repo.deleteAll();

    }

    @Override
    public Boolean existsById(long id) {

        return repo.existsById(id);
    }

    @Override
    public void deleteById(long id) {

        repo.existsById(id);

    }

}
