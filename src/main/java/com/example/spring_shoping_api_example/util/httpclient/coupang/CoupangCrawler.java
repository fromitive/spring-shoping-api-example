package com.example.spring_shoping_api_example.util.httpclient.coupang;

import com.example.spring_shoping_api_example.util.httpclient.HtmlCrawler;
import com.example.spring_shoping_api_example.util.httpclient.OpenGraphParser;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.AllArgsConstructor;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

public class CoupangCrawler implements HtmlCrawler {
    private static final Pattern SHORT_URL_PATTERN = Pattern.compile("^http[s]:\\/\\/link.coupang.com");

    private final HtmlCrawler crawler;

    public CoupangCrawler(HtmlCrawler crawler) {
        this.crawler = crawler;
    }

    @Override
    public String scrap(String productUrl){
        try {
            if(isShortUrl(productUrl)) {
                return crawler.scrap(translateUrl(productUrl));
            }
            return crawler.scrap(productUrl);
        } catch(IOException e) {
            return "";
        }
    }

    private boolean isShortUrl(String productUrl) {
        Matcher matcher = SHORT_URL_PATTERN.matcher(productUrl);
        return matcher.find();
    }

    private String translateUrl(String productUrl) throws IOException {
        return Jsoup.connect(productUrl)
                .execute()
                .url()
                .toString();
    }
}
