package shop.jinwookoh.api.resume.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.jinwookoh.api.artist.domain.Artist;
import shop.jinwookoh.api.category.domain.Category;
import shop.jinwookoh.api.common.domain.BaseEntity;
import shop.jinwookoh.api.common.util.ModelMapperUtils;

@Entity
@Table(name = "resumes")
@NoArgsConstructor
@Getter
public class Resume extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resume_id")
    private Long resumeId;
    @Column(name = "title")
    private String title;
    @Column(name = "detail")
    private String detail;
    @Column(name = "main_pic")
    private String mainPic;
    @Column(name = "main_pic_title")
    private String mainPicTitle;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "artist_id")
    private Artist artist;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    public static Resume of(ResumeDto resumeDto) {
        Resume resume = ModelMapperUtils.getModelMapper().map(resumeDto, Resume.class);
        return resume;
    }

}
