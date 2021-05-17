package shop.jinwookoh.api.artist.service;

import shop.jinwookoh.api.artist.domain.Artist;
import shop.jinwookoh.api.artist.domain.ArtistDto;

import java.util.List;
import java.util.Optional;

public interface ArtistService {
    String signup(Artist artist);

    ArtistDto signin(Artist artist);

    // List<Artist> findAll();
    void deleteById(Long artistId);

}
