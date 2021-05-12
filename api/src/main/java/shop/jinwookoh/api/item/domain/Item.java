package shop.jinwookoh.api.item.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.NoArgsConstructor;
import shop.jinwookoh.api.artist.domain.Artist;
import shop.jinwookoh.api.category.domain.Category;
import shop.jinwookoh.api.file.domain.File;
import shop.jinwookoh.api.supporter.domain.Supporter;

@Entity
@Data
@NoArgsConstructor
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long itemId;
    @Column(name = "title")
    private String title;
    @Column(name = "writer") // creatorId
    private String writer;
    @Column(name = "content")
    private String content;
    @Column(name = "goal_price")
    private String goalPrice;
    @Column(name = "view_cnt")
    private int viewCnt;
    @Temporal(TemporalType.TIMESTAMP) // 등록일
    @Column(name = "reg_date")
    private Date regDate;
    @Temporal(TemporalType.TIMESTAMP) // 수정일
    @Column(name = "edit_date")
    private Date editDate;
    @Column(name = "like_Cnt") // 좋아요숫자
    private int likeCnt;
    @Column(name = "dislike_Cnt")
    private int dislikeCnt;
    @Column(name = "like_check")
    private int likeCheck;
    @ManyToOne
    private Artist artist; // 글작성자, 좋아요 연결
    @ManyToOne
    private Supporter supporter; // 좋아요 누른 사람 연결
    @OneToMany
    private List<File> fileList;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
