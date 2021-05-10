package shop.jinwookoh.api.user.repository;

import java.util.List;

import shop.jinwookoh.api.user.domain.MemberVo;
import shop.jinwookoh.api.user.domain.MemberVo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

interface MemberCustomRepository {

    @Query(value = "SELECT * FROM members  WHERE username= :username AND password= :password", nativeQuery = true)
    MemberVo signin(@Param("username") String username, @Param("password") String password);
}

@Repository
public interface MemberRepository extends JpaRepository<MemberVo, Long>, MemberCustomRepository {
    boolean existsByUsername(String username);

    MemberVo findByUsername(String username);

}
