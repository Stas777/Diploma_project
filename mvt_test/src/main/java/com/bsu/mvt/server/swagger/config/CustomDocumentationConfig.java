package com.bsu.mvt.server.swagger.config;

import com.mangofactory.swagger.*;
import com.mangofactory.swagger.configuration.DocumentationConfig;
import org.springframework.context.annotation.*;

@Configuration
@Import(DocumentationConfig.class)
public class CustomDocumentationConfig {
    @Bean
    public EndpointComparator endPointComparator() {
        return new NameEndPointComparator();
    }

    @Bean
    public OperationComparator operationComparator() {
        return new NameOperationComparator();
    }
}
