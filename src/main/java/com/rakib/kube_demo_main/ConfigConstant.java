package com.rakib.kube_demo_main;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfigConstant {
    @Value("${child.base-url}")
    private String BASE_URL;

    public String getBaseURL() {
        return BASE_URL;
    }
}
