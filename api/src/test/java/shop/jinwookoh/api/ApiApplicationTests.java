package shop.jinwookoh.api;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@SpringBootTest
class SeleniumTestApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void testURL() throws Exception {

		String urlStr = "https://www.wadiz.kr/web/reward/api/comments/campaigns/109463?page=0&size=15&commentGroupType=CAMPAIGN";

		URL url = new URL(urlStr);

		byte[] buffer = new byte[1024 * 8];

		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		InputStream inputStream = url.openStream();

		while (true) {

			int count = inputStream.read(buffer);

			if (count == -1) {
				break;
			}
			bos.write(buffer, 0, count);
		}

		System.out.println(bos.toString("UTF-8"));

	}

	@Test
	public void testSelenium() throws Exception {

		WebDriver driver = new ChromeDriver();
		System.setProperty("webdriver.chrome.driver", "C:\\zzz\\chromedriver.exe");

		// Convenient
		driver.get("https://news.naver.com/main/read.nhn?mode=LSD&mid=shm&sid1=102&oid=018&aid=0004928141");

		Thread.sleep(1000);

		List<WebElement> replyList = driver.findElements(By.className("u_cbox_text_wrap"));

		for (WebElement webElement : replyList) {

			System.out.println(webElement.getText());
			System.out.println("-----------------------------------");
		}

	}

}