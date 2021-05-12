package shop.jinwookoh.api.user.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import shop.jinwookoh.api.user.domain.UserVo;
import shop.jinwookoh.api.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository repository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserVo> user = Optional.ofNullable(repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username Not Found with username: " + username)));
        return UserDetailsImpl.build(user.get());
    }

}
