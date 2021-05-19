package shop.jinwookoh.api.workFile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import shop.jinwookoh.api.workFile.domain.WorkFile;

@Repository
public interface WorkFileRespository extends JpaRepository<WorkFile, Long> {

}
