package com.example.crawling_api.crawling.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "resume")
@Table(name = "resume_files")
public class ResumeFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resume_file_id")
    private Long resumeFileId;
    @Column(name = "uuid")
    private String uuid;
    @Column(name = "fname")
    private String fname;
    @Column(name = "rep_img")
    private Boolean repImg;
    @Column(name = "file_title")
    private String fileTitle;
    @Column(name = "file_detail", columnDefinition = "LONGTEXT")
    private String fileDetail;
    @Column(name = "file_worked_date")
    private String fileWorkedDate;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "resume_id")
    private Resume resume;

}
