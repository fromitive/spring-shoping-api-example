package com.example.spring_shoping_api_example.service;

import com.example.spring_shoping_api_example.util.httpclient.HtmlCrawler;
import com.example.spring_shoping_api_example.util.httpclient.OpenGraphParser;
import lombok.AllArgsConstructor;

public class OGImageExtractor extends ConditionalImageExtractor{
    public static final String OG_IMAGE_PROPERTY = "og:image";

    private final HtmlCrawler htmlCrawler;
    private final OpenGraphParser openGraphParser;

    public OGImageExtractor(HtmlCrawler htmlCrawler, OpenGraphParser openGraphParser) {
        this.htmlCrawler = htmlCrawler;
        this.openGraphParser = openGraphParser;
    }

    public String extract(String productUrl) {
        String html = htmlCrawler.scrap(productUrl);
        return openGraphParser.parseOpenGraph(html, OG_IMAGE_PROPERTY);
    }

    @Override
    public boolean isInCharge(String productUrl) {
        return true;
    }
}
