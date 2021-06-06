package com.example.crawling_api.repository;

import com.example.crawling_api.crawling.domain.Resume;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long> {

}
