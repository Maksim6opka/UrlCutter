# UrlCutter
A simple link shortening service

### How to start?

Write an .env file with the following content:

```
SPRING_DATASOURCE_URL=your_database_url_here
SPRING_DATASOURCE_USERNAME=your_database_username_here
SPRING_DATASOURCE_PASSWORD=your_database_password_here
```
Then run `docker compose -f compose.yaml up --build`