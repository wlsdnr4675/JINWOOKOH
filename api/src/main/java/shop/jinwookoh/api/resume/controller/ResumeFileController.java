package shop.jinwookoh.api.resume.controller;

import java.io.File;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import shop.jinwookoh.api.resume.domain.ResumeFileDto;
import shop.jinwookoh.api.resume.service.ResumeFileServiceImpl;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/resume_file")
@CrossOrigin(origins = "*")
public class ResumeFileController {
    private final ResumeFileServiceImpl service;

    @PostMapping("/upload_file")
    public ResponseEntity<List<ResumeFileDto>> uploadFile(List<MultipartFile> files) {

        for (MultipartFile file : files) {
            System.out.println("file" + file);
            if (file.getContentType().startsWith("image") == false) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        }

        return ResponseEntity.ok(service.uploadFile(files));
    }

    @Value("${shop.jinwookoh.upload.path}")
    private String uploadPath;

    @GetMapping("/display/{fileName}")
    public ResponseEntity<byte[]> getFile(@PathVariable("fileName") String fileName) {
        ResponseEntity<byte[]> result = null;
        try {
            String srcFileName = URLDecoder.decode(fileName, "UTF-8");
            log.info("fiilName: " + srcFileName);
            File file = new File(uploadPath + File.separator + srcFileName);
            log.info("file: " + file);
            HttpHeaders header = new HttpHeaders();
            header.add("Content-Type", Files.probeContentType(file.toPath()));
            result = ResponseEntity.ok(FileCopyUtils.copyToByteArray(file));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return result;
    }

    @PutMapping(value = "/update_file/{resumeFileId}")
    public ResponseEntity<ArrayList<ResumeFileDto>> updatefile(List<MultipartFile> files) {
        // return ResponseEntity.ok(service.registerFile(files));
        return null;
    }

    @DeleteMapping(value = "/delete_file/{resumeFileId}")
    public ResponseEntity<String> deleteFile(@PathVariable("resumeFileId") Long resumeFileId) {

        return ResponseEntity.ok(service.deleteFile(resumeFileId));
    }
}