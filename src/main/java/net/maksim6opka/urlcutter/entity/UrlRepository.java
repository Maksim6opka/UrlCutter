package net.maksim6opka.urlcutter.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlRepository extends JpaRepository<Url, Long> {
    Optional<Url> findByShortenedUrl(String shortenedUrl);
    Optional<Url> findByOriginalUrl(String originalUrl);

    boolean existsByShortenedUrl(String shortenedUrl);
    boolean existsByOriginalUrl(String originalUrl);
}
