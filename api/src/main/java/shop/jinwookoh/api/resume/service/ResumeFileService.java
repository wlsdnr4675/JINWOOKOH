package shop.jinwookoh.api.resume.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import shop.jinwookoh.api.resume.domain.ResumeFile;
import shop.jinwookoh.api.resume.domain.ResumeFileDto;

public interface ResumeFileService {

    String detailRegister(ResumeFileDto resumeFileDto);

    ArrayList<ResumeFileDto> registerFile(List<MultipartFile> uploadFiles);

    String deleteFile(Long fileId);

}
