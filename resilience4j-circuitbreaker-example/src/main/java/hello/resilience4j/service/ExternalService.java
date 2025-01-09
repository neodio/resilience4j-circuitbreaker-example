package hello.resilience4j.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExternalService {

    private final RestTemplate restTemplate;
    public static final String URL = "http://localhost:8082/getData";

    @CircuitBreaker(name = "response-api", fallbackMethod = "fallbackMethod")
    public String callExternalApi() {
        return restTemplate.getForObject(URL, String.class);
    }

    public String fallbackMethod(Exception e) {
        log.info("[fallbackMethod] circuitbreaker OPEN!");
        return "외부 서비스가 현재 사용 불가합니다. 잠시 후 다시 시도해주세요.";
    }
}
