package com.example.spring_shoping_api_example.service;

import com.example.spring_shoping_api_example.util.httpclient.OpenGraphParser;
import com.example.spring_shoping_api_example.util.httpclient.coupang.CoupangCrawler;
import com.example.spring_shoping_api_example.util.httpclient.jsop.JsopOpenGraphParser;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.AllArgsConstructor;

public class CoupangImageExtractor extends ConditionalImageExtractor {
    private static final String OG_IMAGE_PROPERTY ="og:image";
    private static final Pattern URL_PATTERN = Pattern.compile("^http[s]?:\\/\\/.+\\.coupang\\.com/");

    private final CoupangCrawler crawler;
    private final OpenGraphParser parser;

    public CoupangImageExtractor(CoupangCrawler crawler, OpenGraphParser parser) {
        this.crawler = crawler;
        this.parser = parser;
    }

    @Override
    public String extract(String productUrl) {
        String html = crawler.scrap(productUrl);
        return parser.parseOpenGraph(html, OG_IMAGE_PROPERTY);
    }

    @Override
    public boolean isInCharge(String productUrl) {
        Matcher matcher = URL_PATTERN.matcher(productUrl);
        return matcher.matches();
    }
}
