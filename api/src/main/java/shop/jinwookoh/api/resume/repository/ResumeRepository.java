package shop.jinwookoh.api.resume.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import shop.jinwookoh.api.resume.domain.Resume;

interface ResumeCustomRepository {

}

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long>, ResumeCustomRepository {

}
