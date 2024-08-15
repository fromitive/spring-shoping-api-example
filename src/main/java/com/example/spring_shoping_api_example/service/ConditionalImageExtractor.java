package com.example.spring_shoping_api_example.service;

import java.net.MalformedURLException;

public abstract class ConditionalImageExtractor implements ProductImageExtractor{

    @Override
    abstract public String extract(String productUrl);

    abstract public boolean isInCharge(String productUrl);
}
