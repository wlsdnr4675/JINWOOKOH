package shop.jinwookoh.api;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Commit;

import lombok.extern.log4j.Log4j2;
import shop.jinwookoh.api.artist.domain.Artist;
import shop.jinwookoh.api.category.domain.Category;
import shop.jinwookoh.api.resume.domain.Resume;
import shop.jinwookoh.api.resume.domain.ResumeFile;
import shop.jinwookoh.api.resume.repository.ResumeFileRepository;
import shop.jinwookoh.api.resume.repository.ResumeRepository;
import shop.jinwookoh.api.resume.service.ResumeFileServiceImpl;

@SpringBootTest
@Log4j2
public class RepoTest {
    @Autowired
    private ResumeRepository repo;
    @Autowired
    private ResumeFileRepository fileRepo;

    @Transactional
    @Commit
    @Test
    public void saveResume() throws Exception {
        for (long i = 20L; i < 40L; i++) {
            Artist artist = Artist.builder().artistId(i).build();
            Category category = Category.builder().categoryId(3L).build();
            Resume resume = Resume.builder().title(i + "번 제목").detail(i + "번 디테일").selfIntroduce(i + "번 소개글")
                    .artist(artist).category(category).build();
            repo.save(resume);
        }

    }

    @Transactional
    @Commit
    @Test
    public void findAllResume() {
        repo.getAllResume().forEach(action -> {
            log.info(action.getResumeId());
        });
        ;
    }

    @Transactional
    @Commit
    @Test
    public void getAllDataByAtistId() {
        Artist artist = Artist.builder().artistId(7L).build();
        Pageable pageable = PageRequest.of(0, 10);

        repo.getUserPKDataPage(artist.getArtistId(), pageable).forEach(resume -> {
            log.info(resume.getResumeId());
        });
    }

    @Transactional
    @Commit
    @Test
    public void getAllDataByCategoryId() {
        Category category = Category.builder().categoryId(3L).build();
        Pageable pageable = PageRequest.of(0, 10);

        repo.getCategoryPKDataPage(category.getCategoryId(), pageable).forEach(resume -> {
            log.info(resume.getResumeId());
        });
    }

    @Transactional
    @Commit
    @Test
    public void getAllDataByCategoryIdAndUserId() {
        Category category = Category.builder().categoryId(1L).build();
        Artist artist = Artist.builder().artistId(7L).build();
        Pageable pageable = PageRequest.of(0, 10);

        repo.getCategoryAndUserDataPage(category.getCategoryId(), artist.getArtistId(), pageable).forEach(resume -> {
            log.info(resume.getResumeId());
        });
    }

    @Transactional
    @Commit
    @Test
    public void searchALldataByUserName() {
        Artist artist = Artist.builder().username("2cp47").build();
        Pageable pageable = PageRequest.of(0, 10);

        repo.searchUserNameDataPage(artist.getUsername(), pageable).forEach(resume -> {
            log.info(resume.getResumeId());
        });
    }

    @Transactional
    @Commit
    @Test
    public void searchALldataByName() {
        Artist artist = Artist.builder().username("피크닉").build();
        Pageable pageable = PageRequest.of(0, 10);

        repo.searchNameDataPage(artist.getUsername(), pageable).forEach(resume -> {
            log.info(resume.getResumeId());
        });
    }

    @Transactional
    @Commit
    @Test
    public void searchALldataByCategoryName() {
        Category category = Category.builder().categoryName("운동").build();
        Pageable pageable = PageRequest.of(0, 20);

        repo.searchCategoryDataPage(category.getCategoryName(), pageable).forEach(resume -> {
            log.info(resume.getResumeId());
        });
    }

    @Transactional
    @Commit
    @Test
    public void searchAllDataByCategoryAndArtist() {
        Category category = Category.builder().categoryName("인물").build();
        Artist artist = Artist.builder().name("피크닉").build();
        Pageable pageable = PageRequest.of(0, 10);

        repo.searchCategoryAndUserDataPage(category.getCategoryName(), artist.getName(), pageable).forEach(resume -> {
            log.info(resume.getResumeId());
            log.info(resume.getCategory().getCategoryName().toString());
        });
    }

    @Transactional
    @Commit
    @Test
    public void searchAllDataByTitle() {
        Resume resume = Resume.builder().title("13").build();
        Pageable pageable = PageRequest.of(0, 10);

        repo.searchTitleDataPage(resume.getTitle(), pageable).forEach(a -> {
            log.info(a.getResumeId());
            log.info(a.getTitle());
        });
    }

    @Transactional
    @Commit
    @Test
    public void searchAllDataByDetail() {
        Resume resume = Resume.builder().detail("13번 디").build();
        Pageable pageable = PageRequest.of(0, 10);

        repo.searchDetailDataPage(resume.getDetail(), pageable).forEach(a -> {
            log.info(a.getResumeId());
            log.info(a.getDetail());
        });
    }

    @Transactional
    @Commit
    @Test
    public void findAllResumeByPaging() {

        Pageable pageable = PageRequest.of(0, 10);

        repo.getAllDataPaging(pageable).get().forEach(resume -> {
            log.info(resume);
            log.info(resume.getCategory());
            log.info(resume.getArtist());
            log.info("--------------------");
        });
    }

    @Transactional
    @Commit
    @Test
    public void updateResume() {
        Artist artist = Artist.builder().artistId(7L).build();
        Category category = Category.builder().categoryId(2L).build();
        Resume resume = repo.findById(5L).get();
        resume.changeTitle("dsfdsfdsfds");
        log.info("fdsfdsfdsfdsfdsfdsfdsfdsfdsfdsfds" + resume.toString());
        // Resume.builder().title("철저한 익명들의 시니컬한 유머, dhwlsdrn 작가").detail(
        // "늘리는 것과 열 장 짜리를 한 페이지로 줄이는 것 중에 더 어려운 일은 단연코 후자다. 말을 엿가락처럼 끊임없이 늘리는 건 쉬운 일이다.
        // 하지만 무수히 많은 말들 중 핵심을 짚어내는 건 문맥을 완벽히 이해하는 건 물론, 글에 대한 통찰과 센스도 필요한 일이다. 디자인에서도
        // 빼기가 더 어렵다고 하지 않나. 과잉이 디폴트가 된 세계 속에서 ‘짧음’의 미학을 제대로 보여주는 작가가 있다.")
        // .mainPic("http://www.yck.kr/_data/file/bbsData/3ed3af8515da48f03ff6bbd43d3de850.png")
        // .mainPicTitle("ㅙㅐㅐㅐ").artist(artist).category(category).build();
        repo.save(resume);
    }

    @Transactional
    @Commit
    @Test
    public void readResume() {
        repo.findById(5L).orElse(null);
    }

    @Transactional
    @Commit
    @Test
    public void deleteResume() {
        Resume resume = repo.findById(3L).orElseThrow(IllegalArgumentException::new);
        repo.delete(resume);
    }

    @Transactional
    @Commit
    @Test
    public void saveFile() {
        for (Long i = 20L; i < 40L; i++) {
            Resume resume = Resume.builder().resumeId(i).build();
            ResumeFile resumeFile = ResumeFile.builder().uuid(UUID.randomUUID().toString()).fileTitle(i + "번 타이틀")
                    .fileDetail((i) + "번 파일디테일").fname((i) + "번 파일네임").repImg(true).workedDate(i + "/" + (i + 1))
                    .resume(resume).build();
            fileRepo.save(resumeFile);
        }

    }

}
