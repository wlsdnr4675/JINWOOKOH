package shop.jinwookoh.api;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import lombok.extern.log4j.Log4j2;
import shop.jinwookoh.api.artist.domain.Artist;
import shop.jinwookoh.api.artist.repository.ArtistRepository;
import shop.jinwookoh.api.category.domain.Category;
import shop.jinwookoh.api.resume.domain.Resume;
import shop.jinwookoh.api.resume.domain.ResumeDto;
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
        for (long i = 1L; i < 3L; i++) {
            Artist artist = Artist.builder().artistId(i + 2).build();
            Category category = Category.builder().categoryId(i).build();
            Resume resume = Resume.builder().title(i + "번 제목3").detail(i + "번 디테일3").selfIntroduce(i + "번 소개글3")
                    .artist(artist).category(category).build();
            repo.save(resume);
        }

    }

    @Transactional
    @Commit
    @Test
    public void findAllResume() {

        repo.getAllResume();
    }

    @Transactional
    @Commit
    @Test
    public void getAllDataByAtistId() {
        Artist artist = Artist.builder().artistId(1L).build();
        Pageable pageable = PageRequest.of(0, 10);

        repo.getUserPKDataPage(artist.getArtistId(), pageable).forEach(resume -> {
            log.info("artistId: " + resume.getArtist());
            resume.getResumeFiles().stream().map(ResumeFile::getFname).forEach(System.out::println);
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
        repo.save(resume);
    }

    @Transactional
    @Commit
    @Test
    public void readResume() {
        repo.findById(5L).get();
        log.info("fsdfsd: " + repo.findById(5L).get().getResumeId());

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
        for (Long i = 291L; i < 297L; i++) {
            Resume resume = Resume.builder().resumeId(i).build();
            ResumeFile resumeFile = ResumeFile.builder().uuid(UUID.randomUUID().toString()).fname((i) + "번 파일네임3")
                    .repImg(true).fileTitle(i + "fileTitle2").fileDetail(i + "fileDetail2")
                    .fileWorkedDate(i + "fileWorkedDate2").resume(resume).build();
            fileRepo.save(resumeFile);
        }

    }

    @Transactional
    @Commit
    @Test
    public void searchList() {

        Pageable pageable = PageRequest.of(0, 10, Sort.by("artist").descending().and(Sort.by("title").ascending()));
        Page<Object[]> result = repo.searchPage("n", "오존", pageable);
        log.info(result.toString());
    }

    @Transactional
    @Commit
    @Test
    public void countByArtistId() {
        Long result = repo.countByArtistId(200L);
        log.info(result);
    }

}
