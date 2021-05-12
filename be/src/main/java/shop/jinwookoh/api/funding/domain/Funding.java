package shop.jinwookoh.api.funding.domain;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fundings")
public class Funding {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "funding_id")
    private long fundingId;
    @Column(name = "funding_title")
    private String fundingTitle;
    @Column(name = "funding_content")
    private String fundingContent;
    @Column(name = "funding_money")
    private String fundingMoney;
    @Column(name = "delievery_fee") // 배송비
    private String delieveryFee;
    @Column(name = "funding_send") // 펀딩 상품 발송일
    private String fundingSend;
    @Column(name = "select_people") // 펀딩 상품 선택 인원
    private String selectPeople;
    @Column(name = "total_amount") // 펀딩 상품 총 수량
    private String totalAmount;
    @Column(name = "remain_amount") // 펀딩 상품 남은 수량
    private String remainundAmount;
    @Column(name = "supporter_id") // 서포터가 펀딩 (fk)
    private long supporterId;

}
