package shop.philoarte.api.review.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import shop.philoarte.api.artist.domain.QArtist;
import shop.philoarte.api.review.domain.QReply;
import shop.philoarte.api.review.domain.QReview;
import shop.philoarte.api.review.domain.QReviewFile;
import shop.philoarte.api.review.domain.Review;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class SearchReviewRepositoryImpl extends QuerydslRepositorySupport implements SearchReviewRepository {

    public SearchReviewRepositoryImpl() {
        super(Review.class);
    }

    @Override
    public Page<Object[]> searchPage(String type, String keyword, Pageable pageable) {

        log.info("searchPage...........");

        QReview review = QReview.review;
        QArtist artist = QArtist.artist;
        QReply reply = QReply.reply;
        QReviewFile reviewFile = QReviewFile.reviewFile;

        JPQLQuery<Review> jpqlQuery = from(review);
        jpqlQuery.leftJoin(artist).on(review.artist.eq(artist));
        jpqlQuery.leftJoin(reply).on(reply.review.eq(review));
        jpqlQuery.leftJoin(reviewFile).on(reviewFile.review.eq(review));

        JPQLQuery<Tuple> tuple = jpqlQuery.select(review, artist, reply.count(), reviewFile);

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        BooleanExpression expression = review.reviewId.gt(0L);

        booleanBuilder.and(expression);

        if (type != null) {
            String[] typeArr = type.split("");

            log.info(Arrays.toString(typeArr));

            BooleanBuilder conditionBuilder = new BooleanBuilder();

            for (String t : typeArr) {
                switch (t) {
                    case "t":
                        conditionBuilder.or(review.title.contains(keyword));
                        break;
                    case "w":
                        conditionBuilder.or(artist.name.contains(keyword));
                        break;
                    case "c":
                        conditionBuilder.or(review.content.contains(keyword));
                        break;
                }
            }
            booleanBuilder.and(conditionBuilder);
        }
        tuple.where(booleanBuilder);

        //order by
        Sort sort = pageable.getSort();

        sort.stream().forEach(order -> {
            Order direction = order.isAscending() ? Order.ASC : Order.DESC;
            String prop = order.getProperty();

            PathBuilder orderByExpression = new PathBuilder(Review.class, "review");
            tuple.orderBy(new OrderSpecifier(direction, orderByExpression.get(prop)));
        });

        tuple.groupBy(review);

        //page 처리
        log.info(pageable.getOffset());
        log.info(pageable.getPageSize());
        System.out.println("========================");
        tuple.offset(pageable.getOffset());
        tuple.limit(pageable.getPageSize());

        List<Tuple> result = tuple.fetch();

        log.info(result);

        long count = tuple.fetchCount();

        log.info("COUNT : " + count);


        return new PageImpl<Object[]>(result.stream().map(t -> t.toArray()).collect(Collectors.toList()), pageable, count);
    }


    @Override
    public Review search() {
        log.info("search1...........");

        QReview review = QReview.review;
        QReply reply = QReply.reply;
        QArtist artist = QArtist.artist;

        JPQLQuery<Review> jpqlQuery = from(review);
        jpqlQuery.leftJoin(artist).on(review.artist.eq(artist));
        jpqlQuery.leftJoin(reply).on(reply.review.eq(review));

        JPQLQuery<Tuple> tuple = jpqlQuery.select(review, artist.name, reply.count());
        tuple.groupBy(review);

        log.info("-------------------------");
        log.info(tuple);
        log.info("-------------------------");

        List<Tuple> result = tuple.fetch();

        log.info(result);

        return null;
    }
}
