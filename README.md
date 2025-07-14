# 디지털 방명록 REST API 서버

## 프로젝트 개요

디지털 방명록 REST API 서버는 사용자가 간편하게 메시지를 남기고 공유할 수 있는 백엔드 시스템입니다. 
오프라인 행사나 학교 프로젝트 등에서 빠르게 작성하고 확인할 수 있는 시스템의 필요성을 바탕으로 개발되었습니다.

## 주요 기능

- 전체 방명록 조회: 등록된 모든 메시지를 시간순으로 조회
- 방명록 작성: 작성자, 메시지, 비밀번호를 입력해 새 글 등록
- 방명록 상세 조회: ID로 특정 메시지 조회
- 방명록 수정: 비밀번호 확인 후 메시지 수정
- 방명록 삭제: 비밀번호 확인 후 메시지 삭제

## 기술 스택

- Java
- Spring Boot
- Spring Data JPA
- 데이터베이스 (H2/MySQL 등)
- Swagger/OpenAPI (API 문서화)

## API 명세

### Base URL
```
http://localhost:8080
```

### 엔드포인트

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/guestbook` | 전체 방명록 조회 |
| POST | `/guestbook` | 방명록 작성 |
| GET | `/guestbook/{id}` | 방명록 상세 조회 |
| PUT | `/guestbook/{id}` | 방명록 수정 |
| DELETE | `/guestbook/{id}` | 방명록 삭제 |

### 요청/응답 예시

#### 방명록 작성 (POST /guestbook)
```json
// Request
{
  "author": "홍길동",
  "message": "안녕하세요! 좋은 서비스네요.",
  "password": "1234"
}

// Response
{
  "id": 1,
  "author": "홍길동",
  "message": "안녕하세요! 좋은 서비스네요.",
  "createdAt": "2024-01-15T10:30:00",
  "updatedAt": "2024-01-15T10:30:00"
}
```

#### 전체 방명록 조회 (GET /guestbook)
```json
// Response
[
  {
    "id": 1,
    "author": "홍길동",
    "message": "안녕하세요! 좋은 서비스네요.",
    "createdAt": "2024-01-15T10:30:00",
    "updatedAt": "2024-01-15T10:30:00"
  },
  {
    "id": 2,
    "author": "김영희",
    "message": "멋진 프로젝트입니다!",
    "createdAt": "2024-01-15T11:00:00",
    "updatedAt": "2024-01-15T11:00:00"
  }
]
```

## 실행 방법

### 1. 프로젝트 클론
```bash
git clone [repository-url]
cd guestbook-service
```

### 2. 애플리케이션 실행
```bash
# IDE에서 실행하거나
# 빌드 도구를 사용하여 실행
```

### 3. 접속 확인
- API 서버: http://localhost:8080
- Swagger UI: http://localhost:8080/swagger-ui/index.html (설정된 경우)

## API 문서

Swagger가 설정된 경우 UI를 통해 실시간으로 API를 테스트하고 문서를 확인할 수 있습니다.

**접속 URL**: http://localhost:8080/swagger-ui/index.html

## 프로젝트 구조

```
src/
├── main/
│   ├── java/
│   │   └── [package]/
│   │       ├── controller/          # REST API 컨트롤러
│   │       ├── service/             # 비즈니스 로직 계층
│   │       ├── repository/          # 데이터 접근 계층
│   │       ├── entity/              # JPA 엔티티
│   │       ├── dto/                 # 데이터 전송 객체
│   │       ├── exception/           # 예외 처리
│   │       └── config/              # 설정 클래스
│   └── resources/
│       └── application.yml          # 애플리케이션 설정
└── test/                           # 테스트 코드
```

## 개발 환경 설정

애플리케이션 설정은 `application.yml` 또는 `application.properties` 파일에서 관리됩니다.

### 데이터베이스 설정 예시
```yaml
# application.yml
spring:
  datasource:
    url: [데이터베이스 URL]
    driver-class-name: [드라이버 클래스]
    username: [사용자명]
    password: [비밀번호]
  jpa:
    hibernate:
      ddl-auto: [DDL 옵션]
    show-sql: [SQL 출력 여부]
```

## 테스트 실행

프로젝트에 테스트 코드가 포함된 경우 다음과 같이 실행할 수 있습니다:

```bash
# 빌드 도구에 따라 실행 방법이 다를 수 있음
```

## 보안 기능

- 비밀번호 기반 인증: 수정/삭제 시 비밀번호 확인
- 입력 유효성 검사: `@Valid`, `@NotBlank` 등을 활용한 데이터 검증
- 전역 예외 처리: `@RestControllerAdvice`를 통한 일관된 에러 응답

## 개발 후기

이 프로젝트를 통해 Spring Boot 기반의 REST API 서버 개발 전 과정을 경험할 수 있었습니다. 
특히 다음과 같은 기술들을 실제로 적용해보며 백엔드 개발에 대한 이해를 깊게 할 수 있었습니다:

- 3계층 구조 설계: Controller, Service, Repository 계층의 역할과 책임 분리
- JPA를 활용한 데이터베이스 연동: 엔티티 설계와 데이터 접근 처리
- Swagger를 통한 API 문서화: 자동화된 API 문서 생성과 테스트
- 전역 예외 처리: 일관된 에러 응답 구조 구성
- 유효성 검사: Bean Validation을 활용한 데이터 검증

