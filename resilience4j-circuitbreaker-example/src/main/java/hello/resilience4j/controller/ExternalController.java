package hello.resilience4j.controller;

import hello.resilience4j.service.ExternalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ExternalController {

    private final ExternalService externalService;

    @GetMapping("/external")
    public String callExternal() {
        return externalService.callExternalApi();
    }
}
