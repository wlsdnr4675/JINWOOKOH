package shop.jinwookoh.api.resume.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.jinwookoh.api.artist.domain.Artist;
import shop.jinwookoh.api.category.domain.Category;
import shop.jinwookoh.api.common.domain.BaseEntity;
import shop.jinwookoh.api.common.util.ModelMapperUtils;

@Entity
@Table(name = "resumes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Resume extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resume_id")
    private Long resumeId;
    @Column(name = "title")
    private String title;
    @Column(name = "self_introduce")
    private String selfIntroduce;
    @Column(name = "detail")
    private String detail;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "resume", cascade = CascadeType.REMOVE)
    List<ResumeFile> resumeFiles = new ArrayList<>();

    public void changeTitle(String title) {
        this.title = title;
    }

    public void saveAll(ResumeDto resumeDto) {
        this.title = resumeDto.getTitle();
        this.selfIntroduce = resumeDto.getSelfIntroduce();
        this.detail = resumeDto.getDetail();
    }

    public static Resume of(ResumeDto resumeDto) {
        Resume resume = ModelMapperUtils.getModelMapper().map(resumeDto, Resume.class);
        return resume;
    }

}
