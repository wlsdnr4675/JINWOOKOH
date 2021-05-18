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
public class ResumeServiceImpl extends AbstractService<ResumeDto> implements ResumeService {

    private final ResumeRepository repo;

    @Override
    public String save(ResumeDto t) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ResumeDto> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResumeDto findById(Long id) {
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
    public String delete(ResumeDto t) {
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

}
