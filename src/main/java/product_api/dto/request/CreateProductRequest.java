package product_api.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public record CreateProductRequest(
        @NotBlank(message = "Product name is required")
        @Size(max = 120, message = "Product name cannot exceed 120 characters")
        String name,

        @NotNull(message = "Product price is required")
        @DecimalMin(value = "0.01", message = "Product price must be greater than zero")
        @Digits(integer = 10, fraction = 2, message = "Invalid price format")
        BigDecimal price
){}
