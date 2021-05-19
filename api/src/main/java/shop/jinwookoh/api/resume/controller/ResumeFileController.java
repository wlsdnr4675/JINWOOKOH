package shop.jinwookoh.api.resume.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.jinwookoh.api.resume.service.ResumeServiceImpl;

@RestController
@RequiredArgsConstructor
@RequestMapping("/resume_file")
public class ResumeFileController {
    private ResumeServiceImpl service;

}
