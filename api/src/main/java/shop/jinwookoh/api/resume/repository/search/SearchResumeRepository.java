package shop.jinwookoh.api.resume.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import shop.jinwookoh.api.resume.domain.Resume;

public interface SearchResumeRepository {

    Page<Object[]> searchPage(String type, String keyword, Pageable pageable);

}
