package shop.jinwookoh.api.crawl;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;

public class ApiExplorer {
    public static void main(String[] args) throws IOException {
        StringBuilder urlBuilder = new StringBuilder(
                "http://openapi.academyinfo.go.kr/openapi/service/rest/BasicInformationService"); /*
                                                                                                   * URL
                                                                                                   */
        urlBuilder.append("?" + URLEncoder.encode(
                "kD0nqMtdfYEiMGdXeT53ttBf4Ckxnq1OnG7luP4XnoWMM6tUpyeBEQ1HmRjQqagifmbaVmnJpj2kFHYqJGqXAw%3D%3D", "UTF-8")
                + "=서비스키"); /* Service Key */
        urlBuilder.append("&" + URLEncoder.encode(
                "kD0nqMtdfYEiMGdXeT53ttBf4Ckxnq1OnG7luP4XnoWMM6tUpyeBEQ1HmRjQqagifmbaVmnJpj2kFHYqJGqXAw%3D%3D", "UTF-8")
                + "=" + URLEncoder.encode("-", "UTF-8")); /* 발급받은 서비스키 */
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());
    }
}
