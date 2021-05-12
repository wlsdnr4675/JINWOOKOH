package shop.jinwookoh.api.work.dmain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import shop.jinwookoh.api.category.domain.Category;
import shop.jinwookoh.api.user.domain.UserVo;

@Data
@Entity
@Table(name = "works")
public class Work {
    @Entity
    @Table(name = "works")
    public class Work {
        @Id
        @GeneratedValue
        @Column(name = "work_id")
        private long workId;
        @Column
        private String title;
        @Column
        private String description;
        @Column(name = "main_img")
        private String mainImg;
        @Column(name = "reg_date")
        private Date regDate;
        @Column(name = "edit_date")
        private Date editDate;
        
        @ManyToOne
        @JoinColumn(name = "artist_id")
        Artist artist;

        @ManyToOne
        @JoinColumn(name = "category_id")
        Category category;

}
