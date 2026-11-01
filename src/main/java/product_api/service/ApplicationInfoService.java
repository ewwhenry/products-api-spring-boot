package product_api.service;

import product_api.dto.response.ApplicationInfoResponse;
import org.springframework.stereotype.Service;

@Service
public class ApplicationInfoService {

    public ApplicationInfoResponse getApplicationInfo() {
        return new ApplicationInfoResponse(
                "Product API",
                "1.0.0",
                "Spring Boot"
        );
    }
}