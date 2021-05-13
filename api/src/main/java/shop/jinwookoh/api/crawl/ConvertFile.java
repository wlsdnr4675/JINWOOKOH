package shop.jinwookoh.api.crawl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Scanner;

public class ConvertFile {

    public static void main(String[] args) throws Exception {

        File origin = new File("/Users/jinwookoh/JINWOOKOH/crawlingData/artist.csv");

        System.out.println(origin);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        InputStream fin = new FileInputStream(origin);

        while (true) {

            int data = fin.read();

            if (data == -1) {
                break;
            }

            bos.write(data);
        }

        String str = bos.toString();

        System.out.println(str);

    }
}
