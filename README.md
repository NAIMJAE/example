# EXAMPLE ver.01 API 문서

# ARTICLE (게시글)

## 게시글 등록
### ※ REQUEST
- 설명 : 게시글 등록 API
- 요청 메서드 : POST
- 엔드포인트 : /example/article

| 필드 | 타입     | 필수  | 설명        |
|---|--------|-----|-----------|
| userId | Long   | YES | 작성자 회원 ID |
| title | String | YES | 게시글 제목    |
| contents | String | YES | 게시글 내용    |

```json
{
  "userId" : 1,
  "title" : "테스트 게시글 제목",
  "contents" : "테스트 게시글 내용"
}
```

### ※ RESPONSE
- 리턴값 : 등록된 게시글 ID (타입 : Long)
```json
1
```

---

## 게시글 상세 조회
### ※ REQUEST
- 설명 : 게시글 상세 조회 API
- 요청 메서드 : GET
- 엔드포인트 : /example/article/{articleId}

| 파라미터 | 타입   | 필수  | 설명      |
|---|------|-----|---------|
| articleId | Long | YES | 게시글 ID |

### ※ RESPONSE
- 리턴값 : 게시글 상세 정보 (JSON)

| 필드 | 타입 | 설명 |
|---|---|---|
| articleId | Long | 게시글 ID |
| title | String | 제목 |
| contents | String | 내용 |
| userInfo.userId | Long | 작성자 ID |
| userInfo.name | String | 작성자 이름 |
| createdAt | String | 작성일시 |
| updatedAt | String | 수정일시 |

```json
{
  "articleId": 1,
  "title": "테스트 게시글 제목",
  "contents": "테스트 게시글 내용",
  "userInfo": {
    "userId": 1,
    "name": "홍길동"
  },
  "createdAt": "2024-02-12T10:00:00",
  "updatedAt": "2024-02-12T10:00:00"
}
```

---

## 게시글 목록 조회 및 검색
### ※ REQUEST
- 설명 : 게시글 목록 조회 및 검색 API (페이징 포함)
- 요청 메서드 : GET
- 엔드포인트 : /example/article

| 파라미터 | 타입 | 필수 | 기본값 | 설명 |
|---|---|---|---|---|
| page | int | NO | 1 | 페이지 번호 |
| size | int | NO | 10 | 페이지 당 개수 |
| searchType | String | NO | null | 검색 조건 (title / contents) |
| searchKeyword | String | NO | null | 검색어 |

### ※ RESPONSE
- 리턴값 : 게시글 목록 및 페이징 정보 (JSON)

| 필드 | 타입 | 설명 |
|---|---|---|
| list | Array | 게시글 목록 (상세 조회 응답과 동일 구조) |
| page | int | 현재 페이지 번호 |
| size | int | 페이지 당 개수 |
| totalPages | int | 전체 페이지 수 |
| totalElements | long | 전체 게시글 수 |
| hasNext | boolean | 다음 페이지 존재 여부 |
| searchType | String | 적용된 검색 조건 |
| searchKeyword | String | 적용된 검색어 |

```json
{
  "list": [
    {
      "articleId": 1,
      "title": "테스트 게시글 제목",
      "contents": "테스트 게시글 내용",
      "userInfo": {
        "userId": 1,
        "name": "홍길동"
      },
      "createdAt": "...",
      "updatedAt": "..."
    }
  ],
  "page": 1,
  "size": 10,
  "totalPages": 5,
  "totalElements": 45,
  "hasNext": true,
  "searchType": "title",
  "searchKeyword": "테스트"
}
```

---

## 게시글 수정
### ※ REQUEST
- 설명 : 게시글 수정 API
- 요청 메서드 : PATCH
- 엔드포인트 : /example/article/{articleId}

| 파라미터 | 타입 | 필수 | 설명 |
|---|---|---|---|
| articleId | Long | YES | 게시글 ID (Path Variable) |

**Body:**

| 필드 | 타입 | 필수 | 설명 |
|---|---|---|---|
| userId | Long | YES | 작성자 확인용 ID |
| title | String | NO | 수정할 제목 |
| contents | String | NO | 수정할 내용 |

```json
{
  "userId": 1,
  "title": "수정된 제목",
  "contents": "수정된 내용"
}
```

### ※ RESPONSE
- 리턴값 : 성공 여부 (Boolean)
```json
true
```

---

## 게시글 삭제
### ※ REQUEST
- 설명 : 게시글 삭제 API
- 요청 메서드 : DELETE
- 엔드포인트 : /example/article/{articleId}

| 파라미터 | 타입 | 필수 | 설명 |
|---|---|---|---|
| articleId | Long | YES | 게시글 ID (Path Variable) |

**Body:**

| 필드 | 타입 | 필수 | 설명 |
|---|---|---|---|
| userId | Long | YES | 작성자 확인용 ID |

```json
{
  "userId": 1
}
```

### ※ RESPONSE
- 리턴값 : 성공 여부 (Boolean)
```json
true
```
