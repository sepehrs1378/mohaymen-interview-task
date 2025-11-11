# Messaging Backend (Spring Boot)

Simple messaging backend with register/login and per-sender inbox grouping.

## API examples
- Register:
  ```bash
  curl -X POST http://localhost:8080/api/auth/register \
    -H "Content-Type: application/json" \
    -d '{"uid":"user1","password":"11"}'
  ```

- Login (check exists):
  ```bash
  curl -X POST http://localhost:8080/api/auth/login -H "Content-Type: application/json" -d '{"uid":"user1","password":"11"}'
  ```

- Send message:
  ```bash
  curl -X POST http://localhost:8080/api/messages/send \
    -H "Content-Type: application/json" \
    -H "Authorization: Basic ..." \
    -d '{"toUid":"user2","content":"user1 -> user2, 1"}'
  ```

- Get inbox grouped by sender for me:
  ```bash
  curl http://localhost:8080/api/messages/inbox -H "Authorization: Basic ..."
  ```

- Get messages sent from `user1`:
  ```bash
  curl http://localhost:8080/api/messages/from/user1 -H "Authorization: Basic ..."
  ```
