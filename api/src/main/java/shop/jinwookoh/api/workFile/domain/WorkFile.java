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

import lombok.Data;
import lombok.NoArgsConstructor;
import shop.jinwookoh.api.art.domain.Art;

@Entity
@Table(name = "work_files")
@NoArgsConstructor
@Data
public class WorkFile {
    @Id
    @GeneratedValue
    @Column(name = "uuid")
    private UUID uuid;
    @Column(name = "ofile_name")
    private String ofileName;
    @Column(name = "rep_img")
    private String repImg;

    @ManyToOne
    @JoinColumn(name = "art_id")
    private Art art;

}
