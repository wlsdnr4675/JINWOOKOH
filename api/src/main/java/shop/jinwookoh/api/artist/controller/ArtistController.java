package shop.jinwookoh.api.artist.controller;

import shop.jinwookoh.api.artist.domain.Artist;
import shop.jinwookoh.api.artist.domain.ArtistDto;
import shop.jinwookoh.api.artist.service.ArtistServiceImpl;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Api(tags = "artists")
@RequiredArgsConstructor
@Log
@RequestMapping("/artists")
public class ArtistController {

    private final ArtistServiceImpl service;
    private final ModelMapper modelMapper;

    @PostMapping("/signup")
    @ApiOperation(value = "${ArtistController.signup}")
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access Denied"),
            @ApiResponse(code = 422, message = "Artist - Username is alredy in use") })
    public ResponseEntity<String> signup(@ApiParam("Signup Artist") @RequestBody ArtistDto artist) throws IOException {
        log.info("회원가입 시작 +++++++++++++++++++++++++");
        return ResponseEntity.ok(service.signup(modelMapper.map(artist, Artist.class)));

    }

    @PostMapping("/signin")
    @ApiOperation(value = "${ArtistController.signin}")
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Somthing went wrong"),
            @ApiResponse(code = 422, message = "Invalid Artist-Username / Password supplied") })
    public ResponseEntity<ArtistDto> signin(@RequestBody ArtistDto artist) throws IOException {
        log.info("Artist Signin start :::::::::: " + artist);
        return ResponseEntity.ok(service.signin(modelMapper.map(artist, Artist.class)));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Artist>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    // @GetMapping("/{artistId}")
    // public Optional<Artist> findById(@PathVariable("artistId") Long artistId) {
    // System.out.println("회원정보 1개를 불러옵니다 ::::::::::");
    // return service.findById(artistId);
    // }

    @DeleteMapping("/{artistId}")
    @ApiOperation(value = "${ArtistController.delete}")
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access Denied"),
            @ApiResponse(code = 422, message = "Username is alredy in use") })
    public void deleteById(@PathVariable("artistId") Long artistId) {
        System.out.println("삭제를 시작합니다 :::: ");
        System.out.println("artistId :::::::: " + artistId);
        service.deleteById(artistId);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ArtistDto>> all() {
        log.info("로그인 하지 않은 사용자도 접근 가능한 URI");
        return ResponseEntity.ok(null);
    }

    @PostMapping("/{username}")
    public ResponseEntity<?> auth(@PathVariable String username) {
        log.info("로그인한 사용자만 접근 가능한 URI");
        return ResponseEntity.ok(null);
    }

    @PostMapping("/admin")
    public ResponseEntity<?> admin() {
        log.info("관리자만 접근 가능한 URI");
        return ResponseEntity.ok(null);
    }

}
