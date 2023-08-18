package com.innoventes.test.app.interceptor;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.innoventes.test.app.util.CustomHttpServletRequestWrapper;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class WebSiteURLValidationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getMethod().equalsIgnoreCase("POST")) {
            String requestBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> payload = objectMapper.readValue(requestBody, new TypeReference<Map<String, Object>>() {});

            String webSiteURL = (String) payload.get("webSiteURL");
            if (webSiteURL != null && !isValidURL(webSiteURL)) {
                payload.put("webSiteURL", null);
            }

            String modifiedRequestBody = objectMapper.writeValueAsString(payload);
            CustomHttpServletRequestWrapper customRequest = new CustomHttpServletRequestWrapper(request, modifiedRequestBody);
            request = customRequest;
        }
        return true; // Continue processing the request
    }

    private boolean isValidURL(String webSiteURL) {
        String urlRegex = "^(http|https)://([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?$";
        Pattern pattern = Pattern.compile(urlRegex);
        Matcher matcher = pattern.matcher(webSiteURL);
        return matcher.matches();
    }
}
