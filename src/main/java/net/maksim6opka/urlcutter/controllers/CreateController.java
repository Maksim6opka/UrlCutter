package net.maksim6opka.urlcutter.controllers;

import net.maksim6opka.urlcutter.dto.UrlCreateRequest;
import net.maksim6opka.urlcutter.dto.UrlCreateResponse;
import net.maksim6opka.urlcutter.entity.Url;
import net.maksim6opka.urlcutter.entity.UrlRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.Random;

@RestController
public class CreateController {

    private final UrlRepository urlRepository;

    public CreateController(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }
    @PostMapping("/short")
        public ResponseEntity<UrlCreateResponse> Create(
                @RequestBody UrlCreateRequest request
        ) {
            if (request == null) {
                return ResponseEntity.badRequest().build();
            }

            Optional<Url> existingUrl = urlRepository.findByOriginalUrl(request.originalUrl());

            if (existingUrl.isPresent()) {
                return ResponseEntity.ok(UrlCreateResponse.from(existingUrl.get()));
            }

            String shortUrl = generateShortUrl();

            while (urlRepository.existsByShortenedUrl(shortUrl)) {
                shortUrl = generateShortUrl();
            }

            Url url = new Url(request.originalUrl(), shortUrl);
            Url savedUrl = urlRepository.save(url);

            return ResponseEntity.ok(UrlCreateResponse.from(savedUrl));
        }

        private String generateShortUrl() {

        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random rng = new Random();

        StringBuilder result = new StringBuilder(6);

        for (int i = 0; i < 6; i++) {
            int index = rng.nextInt(chars.length());
            result.append(chars.charAt(index));
        }

        return result.toString();
    }

}
