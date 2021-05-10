package shop.jinwookoh.api.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import shop.jinwookoh.api.common.service.AbstractService;
import shop.jinwookoh.api.security.doamin.SecurityProvider;
import shop.jinwookoh.api.security.exception.SecurityRuntimeException;
import shop.jinwookoh.api.user.domain.MemberVo;
import shop.jinwookoh.api.user.domain.Role;
import shop.jinwookoh.api.user.domain.MemberDto;
import shop.jinwookoh.api.user.repository.MemberRepository;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository repo;
    private final PasswordEncoder passwordEncoder;
    private final SecurityProvider securityProvider;
    private final AuthenticationManager authenticationManager;

    private final ModelMapper modelMapper;

    @Override
    public String signup(MemberVo member) {
        // TODO Auto-generated method stub
        if (!repo.existsByUsername(member.getUsername())) {
            member.setPassword(passwordEncoder.encode(member.getPassword()));
            List<Role> list = new ArrayList<>();
            list.add(Role.USER);
            member.setRoles(list);
            repo.save(member);
            return securityProvider.createToken(member.getUsername(), member.getRoles());
        } else {
            throw new SecurityRuntimeException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public MemberDto signin(MemberVo member) {
        // TODO Auto-generated method stub
        try {

            MemberVo loginedMember = repo.signin(member.getUsername(), member.getPassword());
            MemberDto memberDto = modelMapper.map(member, MemberDto.class);
            String token = securityProvider.createToken(member.getUsername(),
                    repo.findByUsername(member.getUsername()).getRoles());
            log.info(":::::::::::::::ISSUED TOKEN::::::::::::::::", token);
            memberDto.setToken(token);
            return memberDto;
        } catch (AuthenticationException e) {
            throw new SecurityRuntimeException("Invalid Username / Password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public List<MemberVo> findAll() {

        // TODO Auto-generated method stub
        return repo.findAll();
    }
}
