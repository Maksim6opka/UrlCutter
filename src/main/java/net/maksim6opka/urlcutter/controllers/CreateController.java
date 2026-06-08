package net.maksim6opka.urlcutter.controllers;

import net.maksim6opka.urlcutter.dto.UrlCreateRequest;
import net.maksim6opka.urlcutter.dto.UrlCreateResponse;
import net.maksim6opka.urlcutter.entity.Url;
import net.maksim6opka.urlcutter.entity.UrlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateController {

    private final UrlService urlService;

    public CreateController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/")
    public ResponseEntity<UrlCreateResponse> Create(
            @RequestBody UrlCreateRequest request
    ) {
        if (request == null || request.originalUrl() == null || request.originalUrl().isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        Url url = urlService.create(request.originalUrl());

        return ResponseEntity.ok(UrlCreateResponse.from(url));
    }
}
