package com.example.spring_shoping_api_example.util.httpclient.jsop;

import com.example.spring_shoping_api_example.util.httpclient.OpenGraphParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsopOpenGraphParser implements OpenGraphParser {

    @Override
    public String parseOpenGraph(String html, String property) {
        Document document = Jsoup.parse(html);
        Element head = document.head();
        Elements meta = head.getElementsByTag("meta");
        return meta.stream()
                .filter(element -> element.hasAttr("property"))
                .filter(element -> element.attribute("property").getValue().equals(property))
                .map(element -> element.attribute("content").getValue())
                .findFirst()
                .orElse("");
    }
}
