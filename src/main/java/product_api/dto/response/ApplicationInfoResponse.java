package product_api.dto.response;

public record ApplicationInfoResponse(
        String application,
        String version,
        String framework
) {}