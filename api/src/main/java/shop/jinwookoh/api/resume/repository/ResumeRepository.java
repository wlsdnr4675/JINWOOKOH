package shop.jinwookoh.api.resume.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import shop.jinwookoh.api.resume.domain.Resume;

interface ResumeCustomRepository {

    @EntityGraph(attributePaths = { "artist", "category" }, type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT r FROM Resume r order by r.resumeId asc")
    List<Resume> getAllResume();
}

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long>, ResumeCustomRepository {
}
