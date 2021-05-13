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

}
