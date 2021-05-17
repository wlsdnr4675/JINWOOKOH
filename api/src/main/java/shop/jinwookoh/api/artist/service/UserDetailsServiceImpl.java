package shop.jinwookoh.api.artist.service;

import shop.jinwookoh.api.artist.domain.Artist;
import shop.jinwookoh.api.artist.repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final ArtistRepository repository;

    @Transactional // 동기로 처리해야하는 부분에 사용. 특히 금융권 ATM 등
    @Override
    public UserDetails loadUserByUsername(String artistname) throws UsernameNotFoundException {
        // Optional의 객체로 감싼다.
        Optional<Artist> user = Optional.ofNullable(repository.findByName(artistname)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username : " + artistname)));

        return UserDetailsImpl.build(user.get()); // .get은 Optional 객체에서 끄집어 낸다.
    }
}
