package net.maksim6opka.urlcutter.controllers;

import net.maksim6opka.urlcutter.entity.Url;
import net.maksim6opka.urlcutter.entity.UrlService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class RedirectController {

    private final UrlService urlService;

    public RedirectController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/short/{shortUrl}")
    public ResponseEntity<Void> redirectToOriginalUrl(@PathVariable String shortUrl) {
        return urlService.findOriginalUrlByShortUrl(shortUrl)
                .map(originalUrl -> ResponseEntity.status(HttpStatus.FOUND)
                        .header(HttpHeaders.LOCATION, originalUrl)
                        .<Void>build())
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
