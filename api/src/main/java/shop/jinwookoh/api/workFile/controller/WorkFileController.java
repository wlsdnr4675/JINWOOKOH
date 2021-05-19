package shop.jinwookoh.api.workFile.controller;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.jinwookoh.api.workFile.service.WorkFileServiceImpl;

@RestController
@RequiredArgsConstructor
public class WorkFileController {
    private final WorkFileServiceImpl service;

}
