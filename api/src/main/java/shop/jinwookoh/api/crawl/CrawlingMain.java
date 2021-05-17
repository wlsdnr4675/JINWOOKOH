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

import shop.jinwookoh.api.artist.domain.Artist;
import shop.jinwookoh.api.common.dummyGenerator.DummyGenerator;

public class CrawlingMain {
    public static void main(String[] args) throws IOException {
        String url = "http://www.yck.kr/html/contents/magazine02";
        String url2 = "https://namu.wiki/w/%EC%98%88%EC%88%A0%EB%8C%80%ED%95%99";
        String cssQuery = ".txt > p.tit ";
        String cssQuery2 = ".wiki-list .wiki-paragraph > a.wiki-link-internal";
        String filePath = "/Users/jinwookoh/JINWOOKOH/crawlingData/artist1.csv";
        Crawler crawler = new Crawler();
        Service service = new Service();
        crawler.setUrl(url);
        crawler.setUrl2(url2);
        crawler.setCssQuery(cssQuery);
        crawler.setCssQuery2(cssQuery2);
        Document artistDocument = service.connectUrl(crawler.getUrl());
        Elements artistElements = artistDocument.select(crawler.getCssQuery());
        Document schoolDocument = service.connectUrl(crawler.getUrl2());
        Elements schoolElements = schoolDocument.select(crawler.getCssQuery2());
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
    private String url2;
    private String cssQuery;
    private String cssQuery2;
    private String category;

    public void setUrl2(String url2) {
        this.url2 = url2;
    }

    public String getUrl2() {
        return url2;
    }

    public void setCssQuery2(String cssQuery2) {
        this.cssQuery2 = cssQuery2;
    }

    public String getCssQuery2() {
        return cssQuery2;
    }

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
    List<Artist> saveAll(Crawler crawler) throws IOException {
        Document artistDocument = connectUrl(crawler.getUrl());
        Elements artistElements = artistDocument.select(crawler.getCssQuery());
        Document schoolDocument = connectUrl(crawler.getUrl2());
        Elements schoolElements = schoolDocument.select(crawler.getCssQuery2());
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
        return list;
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
        Document schoolDocument = connectUrl(crawler.getUrl2());
        Elements schoolElements = schoolDocument.select(crawler.getCssQuery2());
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
