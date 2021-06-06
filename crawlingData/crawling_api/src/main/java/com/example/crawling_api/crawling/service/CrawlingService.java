package com.example.crawling_api.crawling.service;

import java.util.ArrayList;
import java.util.List;

import com.example.crawling_api.crawling.domain.Artist;
import com.example.crawling_api.crawling.domain.Category;
import com.example.crawling_api.crawling.domain.Resume;
import com.example.crawling_api.crawling.domain.ResumeFile;
import com.example.crawling_api.repository.ArtistRepository;
import com.example.crawling_api.repository.CategoryRepository;
import com.example.crawling_api.repository.ResumeFIleRepository;
import com.example.crawling_api.repository.ResumeRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CrawlingService {
    private final ArtistRepository repo;
    private final CategoryRepository cateRepo;
    private final ResumeFIleRepository fileRepo;
    private final ResumeRepository resumeRepo;

    public List<Artist> saveAll(List<Artist> list) {
        return repo.saveAll(list);
    };

    public List<Category> cateSave(List<Category> list) {
        return cateRepo.saveAll(list);
    };

    public List<ResumeFile> fileSave(List<ResumeFile> list) {
        return fileRepo.saveAll(list);
    };

    public List<Resume> resumeSave(List<Resume> list) {
        return resumeRepo.saveAll(list);
    };

}
