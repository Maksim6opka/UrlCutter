package net.maksim6opka.urlcutter.entity;

import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class UrlService {
    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public Url create(String originalUrl) {
        Optional<Url> existingUrl = urlRepository.findByOriginalUrl(originalUrl);

        if (existingUrl.isPresent()) {
            return existingUrl.get();
        }

        String shortUrl = generateShortUrl();

        while (urlRepository.existsByShortenedUrl(shortUrl)) {
            shortUrl = generateShortUrl();
        }

        Url url = new Url(originalUrl, shortUrl);

        return urlRepository.save(url);
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
