package product_api.controller;

import product_api.dto.response.ApiResponse;
import product_api.dto.response.ApplicationInfoResponse;
import product_api.service.ApplicationInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final ApplicationInfoService applicationInfoService;

    public HelloController(ApplicationInfoService applicationInfoService) {
        this.applicationInfoService = applicationInfoService;
    }

    @GetMapping("/")
    public ApiResponse hello() {
        return new ApiResponse(
                "Hello from Spring Boot!",
                "success"
        );
    }

    @GetMapping("/api/info")
    public ApplicationInfoResponse getApplicationInfo() {
        return applicationInfoService.getApplicationInfo();
    }
}