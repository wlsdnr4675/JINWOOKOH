package shop.jinwookoh.api.workFile.domain;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.jinwookoh.api.art.domain.Art;
import shop.jinwookoh.api.common.domain.BaseEntity;

@Entity
@Table(name = "work_files")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class WorkFile extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "work_file_no")
    private Long workFileNo;
    @Column(name = "uuid")
    private String uuid;
    @Column(name = "ofile_name")
    private String ofileName;
    @Column(name = "rep_img")
    private String repImg;

    @ManyToOne
    @JoinColumn(name = "art_id")
    private Art art;

}
