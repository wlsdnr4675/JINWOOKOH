package shop.jinwookoh.api.user.service;

import java.util.List;

import shop.jinwookoh.api.user.domain.MemberVo;
import shop.jinwookoh.api.user.domain.MemberDto;
import shop.jinwookoh.api.user.domain.MemberVo;

public interface MemberService {
    String signup(MemberVo member);

    MemberDto signin(MemberVo member);

    List<MemberVo> findAll();

}
