package shop.jinwookoh.api.resume.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.jinwookoh.api.resume.repository.ResumeFileRepository;

@Service
@RequiredArgsConstructor
public class ResumeFileServiceImp implements ResumeFileService {
    private final ResumeFileRepository repo;

}
