package shop.jinwookoh.api.file.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "files")
public class File {
    @Id
    @GeneratedValue
    @Column(name = "file_id")
    private long fileId;
    @Column(name = "file_tile")
    private String fileTitle;
    @Column(name = "file_detail")
    private String fileDetail;
    @Column(name = "upload_date")
    private Date uploadDate;
    @Column(name = "edit_date")
    private Date editDate;
    @Column(name = "file_img")
    private String fileImg;
    @Column(name = "ofile_name")
    private Date ofile_name;
    @Column(name = "nfile_name")
    private Date nfile_name;
    @Column(name = "fsize")
    private Date fsize;

    // 작성자는 아티스트 엔티티에서 연결
    // Works엔티티와 연결 (다 대 1)

}
