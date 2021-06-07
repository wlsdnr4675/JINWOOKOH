package shop.philoarte.api.review.domain;

import lombok.*;
import shop.philoarte.api.common.domain.BaseEntity;
import shop.philoarte.api.common.domain.BaseEntity;

import javax.persistence.*;

@ToString(exclude="review")
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "review_files")
public class ReviewFile extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_file_id")
    private Long reviewFileId;
    @Column(name = "uuid")
    private String uuid;

    @Column(name = "img_name")
    private String imgName;

    private String path;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="review_id")
    private Review review;

}
