package shop.jinwookoh.api.resume.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import shop.jinwookoh.api.resume.domain.Resume;
import shop.jinwookoh.api.resume.domain.ResumeDto;
import shop.jinwookoh.api.resume.service.ResumeServiceImpl;

@Api
@RestController
@RequestMapping("/resume")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ResumeController {

    private final ResumeServiceImpl service;

    @PostMapping("/register")
    @ApiOperation(value = "${ResumeController.register}")
    public ResponseEntity<String> save(@RequestBody ResumeDto resume) {
        return ResponseEntity.ok(service.save(resume));
    }

    @GetMapping("/list")
    public ResponseEntity<List<Resume>> findAll() {

        return ResponseEntity.ok(service.getAllResume());
    }

    @GetMapping("/read/{resumeId}")
    @ApiOperation(value = "${ResumeContorller.read}")
    public ResponseEntity<ResumeDto> findById(@PathVariable("resumeId") Long resumeId) {

        return ResponseEntity.ok(service.findById(resumeId));
    }

    @PutMapping("/edit")
    @ApiOperation(value = "${ResumeController.edit}")
    public ResponseEntity<String> editResume(@RequestBody ResumeDto resume) {
        System.out.println("ReusemeId: " + resume.getResumeId());
        return ResponseEntity.ok(service.save(resume));
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "${ResumeController.delete}")
    public ResponseEntity<String> delete(@RequestBody ResumeDto resume) {
        // TODO Auto-generated method stub
        return ResponseEntity.ok(service.delete(resume));
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count(long id) {
        // TODO Auto-generated method stub
        return ResponseEntity.ok(service.count());
    }

}
