package com.example.spring_shoping_api_example.service;

import java.util.List;
import java.util.NoSuchElementException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

public abstract class CombinationImageExtractor implements ProductImageExtractor {

    private List<ConditionalImageExtractor> extractors;

    public CombinationImageExtractor(List<ConditionalImageExtractor> extractors) {
        this.extractors = extractors;
    }

    @Override
    public String extract(String productUrl) {
        return extractors.stream()
                .filter(extractor -> extractor.isInCharge(productUrl))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("아무도 담당을 안한다고??"))
                .extract(productUrl);
    }
}
