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
        String url = "http://www.yck.kr/html/contents/magazine02_view?idx=6747&cate_idx=";
        String cssQuery = ".txt > p.tit ";
        String filePath = "/Users/jinwookoh/JINWOOKOH/crawlingData/artist.csv";
        Crawler crawler = new Crawler();
        Service service = new Service();
        crawler.setUrl(url);
        crawler.setCssQuery(cssQuery);
        Document artistDocument = service.connectUrl(crawler.getUrl());
        Elements aritisElements = artistDocument.select(crawler.getCssQuery());
        DummyGenerator dum = new DummyGenerator();
        List<Artist> list = new ArrayList<>();
        try {
            DataOutputStream fw = new DataOutputStream(new FileOutputStream(filePath, false));

            for (int i = 0; i < aritisElements.size(); i++) {
                Artist artist = new Artist();
                artist.setUsername(dum.makeUsername());
                artist.setPassword("12345678");
                artist.setName(aritisElements.get(i).text());
                artist.setEmail(dum.makeBirthday() + dum.makeEmail());
                artist.setPhoneNumber(dum.makePhone());
                artist.setAddress("서울시 정왕동");
                artist.setAffiliation(dum.makeSchool());

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

    public static class Artist {
        private String username;
        private String password;
        private String name;
        private String email;
        private String phoneNumber;
        private String address;
        private String affiliation;

        public void setName(String name) {
            this.name = name;
        }

        public String getAffiliation() {
            return affiliation;
        }

        public void setAffiliation(String affiliation) {
            this.affiliation = affiliation;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getName() {
            return name;
        }

    }
}

class Service {
    List<Artist> saveAll(Crawler crawler) throws IOException {
        Document artistDocument = connectUrl(crawler.getUrl());
        Elements artistElements = artistDocument.select(crawler.getCssQuery());
        DummyGenerator dum = new DummyGenerator();
        List<Artist> list = new ArrayList<>();
        for (int i = 0; i < artistElements.size(); i++) {
            Artist artist = new Artist();
            artist.setUsername(dum.makeUsername());
            artist.setPassword("1234567");
            artist.setName(artistElements.get(i).text());
            artist.setEmail(dum.makeBirthday() + dum.makeEmail());
            artist.setPhoneNumber(dum.makePhone());
            artist.setAddress("서울시정왕동");
            artist.setAffiliation(dum.makeSchool());

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
        DummyGenerator dum = new DummyGenerator();
        for (int i = 0; i < artistElements.size(); i++) {
            Artist artist = new Artist();
            artist.setUsername(dum.makeUsername());
            artist.setPassword("1234567");
            artist.setName(artistElements.get(i).text());
            artist.setEmail(dum.makeBirthday() + dum.makeEmail());
            artist.setPhoneNumber(dum.makePhone());
            artist.setAddress("서울시정왕동");
            artist.setAffiliation(dum.makeSchool());

        }

        return null;
    }

}
