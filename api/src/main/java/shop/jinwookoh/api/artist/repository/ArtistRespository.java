package shop.jinwookoh.api.artist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import shop.jinwookoh.api.artist.domain.Artist;

@Repository
public interface ArtistRespository extends JpaRepository<Artist, Long> {

}
