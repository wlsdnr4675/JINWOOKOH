package com.example.crawling_api.crawling.crawler;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.jni.Time;
import org.apache.tomcat.util.json.JSONParser;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import com.example.crawling_api.crawling.domain.*;
import com.example.crawling_api.crawling.service.CrawlingService;
import com.example.crawling_api.dummies.DummyGenerator;
import com.example.crawling_api.repository.ArtistRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;

@Component
@NoArgsConstructor
public class CrawlingMain {
    public static void main(String[] args) throws IOException {

        String url = "http://www.yck.kr/html/contents/magazine02";
        String cssQuery = ".txt > p.tit ";
        Crawler crawler = new Crawler();
        Service service = new Service();
        crawler.setUrl(url);
        crawler.setCssQuery(cssQuery);
        Document artistDocument = service.connectUrl(crawler.getUrl());
        Elements artistElements = artistDocument.select(crawler.getCssQuery());
        DummyGenerator dum = new DummyGenerator();
        List<Artist> list = new ArrayList<>();

        for (int i = 0; i < artistElements.size(); i++) {
            Artist artist = new Artist();
            artist.setUsername(dum.makeUsername());
            artist.setPassword("12345678");
            artist.setName(artistElements.get(i).text());
            artist.setEmail(dum.makeBirthday() + dum.makeEmail());
            artist.setPhoneNumber(dum.makePhone());
            artist.setAddress("경기도 시흥시 정왕동 2002-12");
            artist.setSchool(dum.makeSchool());
            artist.setDepartment(dum.makeSubject());
            list.add(artist);

        }
        System.out.println(list.size());
        
    }

}

class Crawler {
    private String url;
    private String cssQuery;
    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCssQuery() {
        return cssQuery;
    }

    public void setCssQuery(String cssQuery) {
        this.cssQuery = cssQuery;
    }
}

class Service {

    Document connectUrl(String url) throws IOException {
        return Jsoup.connect(url) // 클래스 안에 이너클래스
                .method(Connection.Method.GET)
                .userAgent("Mozilla/5.0 (X11; Linux x86_64; rv:10.0) " + "Gecko/20100101 Firefox/10.0 "
                        + "AppleWebKit/537.36 (KHTML, like Gecko) " + "Chrome/51.0.2704.106 Safari/537.36")
                .execute().parse();
    }
}

interface Repository extends JpaRepository<Artist, Long> {

}