package shop.jinwookoh.api;

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
        Artist artist = Artist.builder().artistId(9L).build();
        Category category = Category.builder().categoryId(1L).build();
        Resume resume = Resume.builder().title("울랄라세션").detail(
                "한 페이지 짜리를 열 장으로 늘리는 것과 열 장 짜리를 한 페이지로 줄이는 것 중에 더 어려운 일은 단연코 후자다. 말을 엿가락처럼 끊임없이 늘리는 건 쉬운 일이다. 하지만 무수히 많은 말들 중 핵심을 짚어내는 건 문맥을 완벽히 이해하는 건 물론, 글에 대한 통찰과 센스도 필요한 일이다. 디자인에서도 빼기가 더 어렵다고 하지 않나. 과잉이 디폴트가 된 세계 속에서 ‘짧음’의 미학을 제대로 보여주는 작가가 있다.")
                .selfIntroduce("http://www.yck.kr/_data/file/bbsData/3ed3af8515da48f03ff6bbd43d3de850.png")
                .artist(artist).category(category).build();
        repo.save(resume);
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
        Resume resume = repo.findById(5L).orElseThrow(IllegalArgumentException::new);
        repo.delete(resume);
    }

    @Transactional
    @Commit
    @Test
    public void saveFile() {
        Resume resume = Resume.builder().resumeId(6L).build();
        ResumeFile resuemFile = ResumeFile.builder().uuid(UUID.randomUUID().toString()).fileTitle("흩날려라 천본앵")
                .fileDetail("이것은 이거다 내가 하고픈 말은 이거일듯").fname("fdsfdsfdsfdsfdssfdsfdsfdsfsdfsd.jpg").repImg(true)
                .workedDate("12/3").resume(resume).build();
        fileRepo.save(resuemFile);
    }

}
