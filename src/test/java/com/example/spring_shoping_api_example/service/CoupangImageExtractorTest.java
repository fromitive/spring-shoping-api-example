package com.example.spring_shoping_api_example.service;

import static org.junit.jupiter.api.Assertions.*;

import com.example.spring_shoping_api_example.util.httpclient.HtmlCrawler;
import com.example.spring_shoping_api_example.util.httpclient.OpenGraphParser;
import com.example.spring_shoping_api_example.util.httpclient.coupang.CoupangCrawler;
import com.example.spring_shoping_api_example.util.httpclient.jsop.JsopCrawler;
import com.example.spring_shoping_api_example.util.httpclient.jsop.JsopOpenGraphParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CoupangImageExtractorTest {

    private HtmlCrawler htmlCrawler;
    private OpenGraphParser parser;
    private CoupangCrawler crawler;
    private CoupangImageExtractor extractor;

    @BeforeEach
    void setUp() {
        htmlCrawler = new JsopCrawler(3000);
        parser = new JsopOpenGraphParser();
        crawler = new CoupangCrawler(htmlCrawler);
        extractor = new CoupangImageExtractor(crawler, parser);
    }
    @DisplayName("쿠팡 이미지 추출 가능한겨?")
    @CsvSource({
            "https://www.coupang.com/vp/products/6714057601",
    })
    @ParameterizedTest
    void zz(String productUrl) {

        String imageUrl = extractor.extract(productUrl);
        System.out.println(imageUrl);
        assertTrue(!imageUrl.isEmpty());
    }

    @DisplayName("쿠팡 이미지 추출 가능한겨?")
    @Test
    void zz2() {
        String productUrl = "https://link.coupang.com/a/bNUscv";
        String imageUrl = extractor.extract(productUrl);
        System.out.println(imageUrl);
        assertTrue(!imageUrl.isEmpty());
    }
}