package shop.jinwookoh.api.crawl;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import shop.jinwookoh.api.artist.domain.Artist;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class selenium {
    public static final String WEB_DRIVER_ID = "webdriver.chrome.driver"; // 드라이버 ID
    public static final String WEB_DRIVER_PATH = "/usr/local/bin/chromedriver"; // 드라이버 경로

    public static void main(String[] args) throws IOException {
        String url = "https://news.naver.com/main/read.nhn?mode=LSD&mid=shm&sid1=102&oid=018&aid=0004928141";
        String cssQuery = "u_cbox_text_wrap";
        String filePath = "/Users/jinwookoh/JINWOOKOH/crawlingData/school.cvs";
        WebDriver driver = new ChromeDriver();
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
        driver.get(url);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        List<Artist> list = new ArrayList<>();
        List<WebElement> el3 = driver.findElements(By.className(cssQuery));

        try {
            BufferedWriter fw = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(filePath, true), "euc-kr"));
            for (int i = 0; i < el3.size(); i++) {
                Artist artist = new Artist();
                artist.setAddress(el3.get(i).getText());
                System.out.println(artist.getAddress());
                list.add(artist);
            }
            if (list.isEmpty()) {
                System.out.println("크롤링 된 값이 없습니다. !");
            } else {
                for (Artist f : list) {
                    fw.write(f.getAddress() + ",");
                    fw.newLine();
                }
            }
            fw.flush();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
