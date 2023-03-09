package com.crud.producto.util;


import com.google.common.io.Resources;
import io.swagger.v3.oas.models.Operation;
import org.apache.commons.lang3.StringUtils;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

import java.net.URL;
import java.nio.charset.StandardCharsets;

@Component
public class MarkdownDescriptionPlugin implements OperationCustomizer {

    @Value("${server.servlet.context-path}")
    private String CONTEXT_PATH;


    @Override
    public Operation customize(Operation operation, HandlerMethod handlerMethod) {

        MarkdownDescriptionFile annotation = handlerMethod.getMethodAnnotation(MarkdownDescriptionFile.class);

        if(annotation!=null){
            if (!StringUtils.isBlank(annotation.value())) {
                String filename = annotation.value();
                String description;
                try {
                    final URL url = Resources.getResource("docs/descriptions/" + filename);
                    description = Resources.toString(url, StandardCharsets.UTF_8).replace("![picture_desc](../../static", "![picture_desc](");
                    if(!StringUtils.isBlank(CONTEXT_PATH)) description = description.replace("![picture_desc](", "![picture_desc](" + CONTEXT_PATH);
                } catch (Exception e) {
                    description = String.format("Markdown file %s not loaded", filename);
                }
                operation.setDescription(description);
            }
        }else{
            if(!StringUtils.isBlank(operation.getDescription()))operation.setDescription(operation.getDescription().replace("![picture_desc](", "![picture_desc](" + CONTEXT_PATH));
        }

        return operation;

    }
}
