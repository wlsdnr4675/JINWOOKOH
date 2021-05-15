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

import shop.jinwookoh.api.common.dummyGenerator.DummyGenerator;
import shop.jinwookoh.api.supporter.domain.Supporter;

public class CrawlingSupporter {
    public static void main(String[] args) throws IOException {
        String url = "http://www.yck.kr/html/contents/magazine02_view?idx=6787&cate_idx=7";
        String cssQuery = ".txt .tit";
        String filePath = "/Users/jinwookoh/JINWOOKOH/crawlingData/supporter.csv";
        SupporterCrawler crawler = new SupporterCrawler();
        Service service = new Service();
        crawler.setUrl(url);
        crawler.setCssQuery(cssQuery);
        Document supporterDocument = service.connectUrl(crawler.getUrl());
        Elements supporterElements = supporterDocument.select(crawler.getCssQuery());
        DummyGenerator dum = new DummyGenerator();
        List<Supporter> list = new ArrayList<>();
        try {
            DataOutputStream fw = new DataOutputStream(new FileOutputStream(filePath, false));

            for (int i = 0; i < supporterElements.size(); i++) {
                Supporter supporter = new Supporter();
                supporter.setUsername(dum.makeUsername());
                supporter.setPassword("12345678");
                supporter.setName(supporterElements.get(i).text());
                supporter.setSupporterEmail(dum.makeBirthday() + dum.makeEmail());
                supporter.setSupporterPhoneNumber(dum.makePhone());

                System.out.println(supporter.toString());

                list.add(supporter);
            }
            if (list.isEmpty()) {
                System.out.println("크롤링 된 값이 없습니다. !");
            } else {
                for (Supporter s : list) {
                    fw.writeUTF(s.toString() + "\n");
                }
            }
            fw.flush();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class SupporterCrawler {
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

    public static class Supporter {

        private String username;

        private String password;

        private String name;

        private String supporterEmail;

        private String supporterPhoneNumber;

        public String getSupporterPhoneNumber() {
            return supporterPhoneNumber;
        }

        public void setSupporterPhoneNumber(String supporterPhoneNumber) {
            this.supporterPhoneNumber = supporterPhoneNumber;
        }

        public String getSupporterEmail() {
            return supporterEmail;
        }

        public void setSupporterEmail(String supporterEmail) {
            this.supporterEmail = supporterEmail;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

    }
}

class SupporterService {
    List<Supporter> saveAll(SupporterCrawler crawler) throws IOException {
        Document supporterDocument = connectUrl(crawler.getUrl());
        Elements supporterElements = supporterDocument.select(crawler.getCssQuery());
        DummyGenerator dum = new DummyGenerator();
        List<Supporter> list = new ArrayList<>();
        for (int i = 0; i < supporterElements.size(); i++) {
            Supporter supporter = new Supporter();
            supporter.setUsername(dum.makeUsername());
            supporter.setPassword("12345678");
            supporter.setName(supporterElements.get(i).text());
            supporter.setSupporterEmail(dum.makeBirthday() + dum.makeEmail());
            supporter.setSupporterPhoneNumber(dum.makePhone());

            list.add(supporter);
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

    List<?> scrapFunding(SupporterCrawler crawler) throws IOException {
        Document supporterDocument = connectUrl(crawler.getUrl());
        Elements supporterElements = supporterDocument.select(crawler.getCssQuery());
        DummyGenerator dum = new DummyGenerator();
        for (int i = 0; i < supporterElements.size(); i++) {
            Supporter supporter = new Supporter();
            supporter.setUsername(dum.makeUsername());
            supporter.setPassword("12345678");
            supporter.setName(supporterElements.get(i).text());
            supporter.setSupporterEmail(dum.makeBirthday() + dum.makeEmail());
            supporter.setSupporterPhoneNumber(dum.makePhone());

        }

        return null;
    }

}
