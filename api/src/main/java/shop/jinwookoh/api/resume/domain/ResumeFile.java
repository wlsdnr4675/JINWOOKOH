package shop.jinwookoh.api.resume.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.jinwookoh.api.common.domain.BaseEntity;
import shop.jinwookoh.api.common.util.ModelMapperUtils;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "resume_files")
public class ResumeFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resume_file_id")
    private Long resumeFileId;
    @Column(name = "uuid")
    private String uuid;
    @Column(name = "file_title")
    private String fileTitle;
    @Column(name = "file_detail")
    private String fileDetail;
    @Column(name = "fname")
    private String fname;
    @Column(name = "rep_img")
    private Boolean repImg;
    @Column(name = "worked_date")
    private String workedDate;

    @ManyToOne
    private Resume resume;

    public void confirmResume(Resume resume) {
        this.resume = resume;
    }

    public void saveAll(ResumeFileDto resumeFileDto) {
        this.uuid = resumeFileDto.getUuid();
        this.fileTitle = resumeFileDto.getFileTitle();
        this.fileDetail = resumeFileDto.getFileDetail();
        this.fname = resumeFileDto.getFname();
        this.repImg = resumeFileDto.getRepImg();
        this.workedDate = resumeFileDto.getWorkedDate();
    }

    public static ResumeFile of(ResumeFileDto resumeFileDto) {

        return ModelMapperUtils.getModelMapper().map(resumeFileDto, ResumeFile.class);
    }

}
