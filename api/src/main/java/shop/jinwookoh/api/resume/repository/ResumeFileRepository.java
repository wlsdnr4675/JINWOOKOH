package shop.jinwookoh.api.resume.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import shop.jinwookoh.api.resume.domain.ResumeFile;

interface ResumeFileCustomRepository {
}

@Repository
public interface ResumeFileRepository extends JpaRepository<ResumeFile, Long>, ResumeFileCustomRepository {

}
