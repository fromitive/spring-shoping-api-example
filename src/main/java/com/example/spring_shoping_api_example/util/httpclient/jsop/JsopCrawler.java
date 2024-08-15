package com.example.spring_shoping_api_example.util.httpclient.jsop;

import com.example.spring_shoping_api_example.util.httpclient.HtmlCrawler;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.jsoup.Jsoup;

public class JsopCrawler implements HtmlCrawler {

    private final int timeoutMilliseconds;

    public JsopCrawler(int timeoutMilliseconds) {
        this.timeoutMilliseconds = timeoutMilliseconds;
    }


    @Override
    public String scrap(String productUrl) {
        try {
            return ScrapWithAuth(productUrl, getCookies(productUrl));
        } catch (IOException | RuntimeException e) {
            e.printStackTrace();
            return "";
        }
    }

    private Map<String, String> getCookies(String productUrl) throws IOException {
        return Jsoup.connect(productUrl)
                .timeout(timeoutMilliseconds)
                .headers(getCommonHeaders(productUrl))
                .execute().cookies();
    }

    private String ScrapWithAuth(String productUrl, Map<String, String> cookies) throws IOException {
        return Jsoup.connect(productUrl)
                .timeout(timeoutMilliseconds)
                .headers(getCommonHeaders(productUrl))
                .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.15; rv:129.0) Gecko/20100101 Firefox/129.0")
                .cookies(cookies)
                .get()
                .html();
    }

    private Map<String, String> getCommonHeaders(String productUrl) throws MalformedURLException {
        Map<String, String> headers = new HashMap<>();
        headers.put("Host", parseHostFromProductUrl(productUrl));
        headers.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.15; rv:129.0) Gecko/20100101 Firefox/129.0");
        headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/png,image/svg+xml,*/*;q=0.8");
        headers.put("Accept-Language", "ko-KR,ko;q=0.8,en-US;q=0.5,en;q=0.3");
        headers.put("Accept-Encoding", "gzip, deflate, br, zstd");
        headers.put("DNT", "1");
        headers.put("Sec-GPC", "1");
        headers.put("Connection", "keep-alive");
        headers.put("Upgrade-Insecure-Requests", "1");
        headers.put("Sec-Fetch-Dest", "document");
        headers.put("Sec-Fetch-Mode", "navigate");
        headers.put("Sec-Fetch-Site", "none");
        headers.put("Sec-Fetch-User", "?1");
        headers.put("Priority", "u=0, i");
        return headers;
    }

    private String parseHostFromProductUrl(String productUrl) throws MalformedURLException {
        URL parsedUrl = new URL(productUrl);
        return parsedUrl.getHost();
    }
}
