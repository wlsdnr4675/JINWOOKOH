package shop.jinwookoh.api.resume.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import shop.jinwookoh.api.resume.domain.ResumeFileDto;
import shop.jinwookoh.api.resume.service.ResumeFileServiceImpl;

@RestController
@RequiredArgsConstructor
@RequestMapping("/resume_file")
@Log4j2
public class ResumeFileController {
    private ResumeFileServiceImpl service;

    @PostMapping("/upload_file")
    public ResponseEntity<String> uploadFile(@RequestBody ArrayList<MultipartFile> files) {

        for (MultipartFile file : files) {
            String ofname = file.getOriginalFilename();
            if (file.getContentType().startsWith("image") == false) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
            ofname.trim();
            String url = service.registerFile(file);
        }
        return null;

    }
}