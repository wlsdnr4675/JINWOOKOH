package shop.jinwookoh.api.crawl;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import shop.jinwookoh.api.artist.domain.Artist;
import shop.jinwookoh.api.artist.repository.ArtistRepository;
import shop.jinwookoh.api.common.dummyGenerator.DummyGenerator;

public class CrawlingMain {
    public static void main(String[] args) throws IOException {
        String url = "http://www.yck.kr/html/contents/magazine02";
        String cssQuery = ".txt > p.tit ";
        String filePath = "/Users/jinwookoh/JINWOOKOH/crawlingData/artist1.csv";
        Crawler crawler = new Crawler();
        Service service = new Service();
        crawler.setUrl(url);
        crawler.setCssQuery(cssQuery);
        Document artistDocument = service.connectUrl(crawler.getUrl());
        Elements artistElements = artistDocument.select(crawler.getCssQuery());
        DummyGenerator dum = new DummyGenerator();
        List<Artist> list = new ArrayList<>();
        try {
            DataOutputStream fw = new DataOutputStream(new FileOutputStream(filePath, true));

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
                System.out.println(artist.toString());
                list.add(artist);

            }
            if (list.isEmpty()) {
                System.out.println("크롤링 된 값이 없습니다. !");
            } else {
                for (Artist a : list) {
                    fw.writeUTF(a.toString() + "\n");
                    service.saveAll(a);

                }
            }
            fw.flush();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    @Autowired
    private ArtistRepository repo;

    List<Artist> saveAll(Artist artist) throws IOException {
        Crawler crawler = new Crawler();
        Document artistDocument = connectUrl(crawler.getUrl());
        Elements artistElements = artistDocument.select(crawler.getCssQuery());
        DummyGenerator dum = new DummyGenerator();
        List<Artist> list = new ArrayList<>();
        for (int i = 0; i < artistElements.size(); i++) {
            artist = new Artist();
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
        return repo.saveAll(list);
    }

    Document connectUrl(String url) throws IOException {
        return Jsoup.connect(url) // 클래스 안에 이너클래스
                .method(Connection.Method.GET)
                .userAgent("Mozilla/5.0 (X11; Linux x86_64; rv:10.0) " + "Gecko/20100101 Firefox/10.0 "
                        + "AppleWebKit/537.36 (KHTML, like Gecko) " + "Chrome/51.0.2704.106 Safari/537.36")
                .execute().parse();
    }

    List<?> scrapFunding(Crawler crawler) throws IOException {
        Document artistDocument = connectUrl(crawler.getUrl());
        Elements artistElements = artistDocument.select(crawler.getCssQuery());

        DummyGenerator dum = new DummyGenerator();
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

        }

        return null;
    }

}