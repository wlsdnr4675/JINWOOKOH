package com.example.crawling_api.repository;

import com.example.crawling_api.crawling.domain.ResumeFile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeFIleRepository extends JpaRepository<ResumeFile, Long> {

}
