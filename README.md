```markdown
# URL Shortener Service

My implementation of https://roadmap.sh/projects/url-shortening-service
A simple URL shortener service built with Spring Boot and Maven. This service allows you to shorten URLs, retrieve their stats, and manage them with basic CRUD operations.

## Features

- Shorten a URL
- Retrieve URL stats
- Update or delete a shortened URL
- Increment access count on URL retrieval

## Endpoints

### POST /shorten

Shortens a given URL.

**Request Body**:
```json
{
  "url": "https://www.example.com/some/long/url"
}
```

**Response**:
```json
{
  "id": "1",
  "url": "https://www.example.com/some/long/url",
  "shortCode": "abc123",
  "createdAt": "2021-09-01T12:00:00Z",
  "updatedAt": "2021-09-01T12:00:00Z",
  "accessCount": 10
}
```

### GET /shorten/{shortCode}/stats

Returns the stats for a shortened URL.

**Request**:
```plaintext
GET /shorten/abc123/stats
```

**Response**:
```json
{
  "id": "1",
  "url": "https://www.example.com/some/long/url",
  "shortCode": "abc123",
  "createdAt": "2021-09-01T12:00:00Z",
  "updatedAt": "2021-09-01T12:00:00Z",
  "accessCount": 10
}
```

### PUT /shorten/{shortCode}

Update the URL associated with a shortened code.

**Request Body**:
```json
{
  "url": "https://www.new-url.com"
}
```

**Response**:
```json
{
  "id": "1",
  "url": "https://www.new-url.com",
  "shortCode": "abc123",
  "createdAt": "2021-09-01T12:00:00Z",
  "updatedAt": "2021-09-02T12:00:00Z",
  "accessCount": 10
}
```

### DELETE /shorten/{shortCode}

Delete the shortened URL.

**Response**:
- `204 No Content` if the URL was successfully deleted.
- `404 Not Found` if the shortened URL does not exist.

## Technologies Used

- Spring Boot
- SQLite
- Maven

## How to Run

1. Clone the repository.
2. Ensure you have Java 11+ installed.
3. Run `mvn spring-boot:run`.
4. The service will be available at `http://localhost:8080`.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
```