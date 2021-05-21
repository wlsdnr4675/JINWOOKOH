package shop.jinwookoh.api.resume.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import shop.jinwookoh.api.artist.domain.Artist;
import shop.jinwookoh.api.resume.domain.Resume;

interface ResumeCustomRepository {

    @EntityGraph(attributePaths = { "artist", "category" }, type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT r FROM Resume r order by r.resumeId asc")
    List<Resume> getAllResume();

    @EntityGraph(attributePaths = { "artist", "category" }, type = EntityGraph.EntityGraphType.FETCH)
    @Query("select r from Resume r group by r  order by r.resumeId desc")
    Page<Resume> getAllDataPaging(Pageable pageable);

    @EntityGraph(attributePaths = { "artist", "category" }, type = EntityGraph.EntityGraphType.FETCH)
    @Query("select r from Resume r WHERE r.artist.artistId= :artistId")
    Page<Resume> getUserPKDataPage(@Param("artistId") Long artistId, Pageable pageable);

    @EntityGraph(attributePaths = { "artist", "category" }, type = EntityGraph.EntityGraphType.FETCH)
    @Query("select r from Resume r WHERE r.category.categoryId=:categoryId")
    Page<Resume> getCategoryPKDataPage(@Param("categoryId") Long categoryId, Pageable pageable);

    @EntityGraph(attributePaths = { "artist", "category" }, type = EntityGraph.EntityGraphType.FETCH)
    @Query("select r from Resume r WHERE r.category.categoryId= :categoryId AND r.artist.artistId = :artistId")
    Page<Resume> getCategoryAndUserDataPage(@Param("categoryId") Long categoryId, @Param("artistId") Long artistId,
            Pageable pageable);

    // username으로 서치
    @EntityGraph(attributePaths = { "artist", "category" }, type = EntityGraph.EntityGraphType.FETCH)
    @Query("select r from Resume r WHERE r.artist.username= :username")
    Page<Resume> searchUserNameDataPage(@Param("username") String username, Pageable pageable);

    // name으로 서치
    @EntityGraph(attributePaths = { "artist", "category" }, type = EntityGraph.EntityGraphType.FETCH)
    @Query("select r from Resume r WHERE r.artist.name= :name")
    Page<Resume> searchNameDataPage(@Param("name") String name, Pageable pageable);

    // 카테고리이름으로 서치
    @EntityGraph(attributePaths = { "artist", "category" }, type = EntityGraph.EntityGraphType.FETCH)
    @Query("select r from Resume r WHERE r.category.categoryName= :categoryName")
    Page<Resume> searchCategoryDataPage(@Param("categoryName") String categoryName, Pageable pageable);

    // 카테고리 + 이름으로 서치
    @EntityGraph(attributePaths = { "artist", "category" }, type = EntityGraph.EntityGraphType.FETCH)
    @Query("select r from Resume r WHERE r.artist.name = :name AND r.category.categoryName= :categoryName")
    Page<Resume> searchCategoryAndUserDataPage(@Param("name") String name, @Param("categoryName") String categoryName,
            Pageable pageable);

    // %resumeTitle%로 서치
    @EntityGraph(attributePaths = { "artist", "category" }, type = EntityGraph.EntityGraphType.FETCH)
    @Query("select r from Resume r WHERE r.title LIKE %:title%")
    Page<Resume> searchTitleDataPage(@Param("title") String title, Pageable pageable);

    // %resumeDetail%로 서치
    @EntityGraph(attributePaths = { "artist", "category" }, type = EntityGraph.EntityGraphType.FETCH)
    @Query("select r from Resume r WHERE r.detail LIKE %:detail%")
    Page<Resume> searchDetailDataPage(@Param("detail") String detail, Pageable pageable);

}

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long>, ResumeCustomRepository {
}
