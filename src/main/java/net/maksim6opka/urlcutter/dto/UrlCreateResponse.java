package net.maksim6opka.urlcutter.dto;

import net.maksim6opka.urlcutter.entity.Url;

import java.time.Instant;

public record UrlCreateResponse(
        String status,
        String details,
        UrlCreateData data
) {
    public static UrlCreateResponse from(Url url) {
        return new UrlCreateResponse(
                "success",
                "Successfully cut url",
                new UrlCreateData(
                        url.getId(),
                        url.getOriginalUrl(),
                        url.getShortenedUrl(),
                        url.getCreatedAt()
                )
        );
    }

    public record UrlCreateData(
            long id,
            String originalUrl,
            String shortUrl,
            Instant time
    ) {}
}
