package shop.jinwookoh.api.resume.service;

import shop.jinwookoh.api.resume.domain.Resume;
import shop.jinwookoh.api.resume.domain.ResumeDto;

public interface ResumeService {
    public ResumeDto findByResumeId(Long resumeId);
    // public Resume editResume(Long resumeId, Resume resume);

}
