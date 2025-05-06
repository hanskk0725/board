
# SimpleBoard 프로젝트 명세서

## 1. 프로젝트 개요
- **프로젝트명**: SimpleBoard
- **기술 스택**:
  - Backend: Java 17, Spring Boot, Spring Data JPA
  - Template Engine: Thymeleaf
  - Build Tool: Gradle
  - DB: H2 
- **기능 요약**:
  - 글 목록 조회
  - 글 작성
  - 글 상세 보기
  - 글 수정
  - 글 삭제

- **제한 사항**:
  - 로그인/회원 기능 없음
  - 비공개 게시글/댓글 기능 없음

## 2. 기능 요구사항
| 기능명 | 설명 |
|:---|:---|
| 글 목록 조회 | 전체 글을 최신순으로 목록 조회. 제목/작성자만 표시 |
| 글 작성 | 제목, 작성자명, 본문을 입력해 글 작성 |
| 글 상세 보기 | 게시글 클릭 시 상세내용 확인 |
| 글 수정 | 글 상세보기 화면에서 수정 가능 (작성자 확인은 없음) |
| 글 삭제 | 글 상세보기 화면에서 삭제 가능 |
| 댓글 작성 | 게시글 상세 보기 페이지에서 댓글 입력해 작성 |
| 댓글 삭제 | 댓글 옆 삭제 버튼을 통해 삭제 |

## 3. 데이터 모델 (Entity 설계)

### Post Entity
| 필드명 | 타입 | 설명 |
|:---|:---|:---|
| id | Long | 기본키, 자동 생성 |
| title | String | 게시글 제목 |
| content | String | 게시글 본문 |
| writer | String | 작성자 이름 |
| createdDate | LocalDateTime | 생성일 |
| modifiedDate | LocalDateTime | 수정일 (수정 시만 업데이트) |

## 4. 화면/URL 설계
| 페이지 | URL | 설명 |
|:---|:---|:---|
| 글 목록 | `/posts` | 전체 게시글 목록 |
| 글 작성 폼 | `/posts/new` | 글 작성 페이지 |
| 글 작성 처리 | `POST /posts` | 글 저장 요청 처리 |
| 글 상세 보기 | `/posts/{id}` | 특정 글 보기 |
| 글 수정 폼 | `/posts/{id}/edit` | 글 수정 폼 |
| 글 수정 처리 | `POST /posts/{id}/edit` | 수정 요청 처리 |
| 글 삭제 처리 | `POST /posts/{id}/delete` | 삭제 요청 처리 |

## 5. 개발 구조
- 패키지 구조 예시
```
com.example.board
├── controller       # 웹 요청 처리
├── service          # 비즈니스 로직
├── repository       # 데이터 액세스 (Spring Data JPA)
├── domain           # 엔티티
└── dto              # 폼 및 응답용 DTO
```
- 템플릿 구성 예시
```
resources/templates
├── posts
│   ├── list.html
│   ├── form.html
│   └── post.html

```

## 6. 개발 진행 순서
1. 프로젝트 초기 설정 - Spring Initializr로 프로젝트 생성
2. 기본 도메인(Post, Comment) 설계 및 DB 연결
3. 글 작성 기능 구현
4. 글 목록 조회 기능 구현
5. 글 상세 보기 기능 구현
6. 글 수정/삭제 기능 구현
7. 테스트 및 마무리

## 7. 어려웠던 부분
1. Optinal 반환 고려 해보려 했지만 사용 못함
2. 예외 처리 부분 미흡
3. 유효성 검증

## 8. 추후 확장 부분
- 페이징 처리 (Spring Data Pageable 활용)
- 유효성 검증 및 예외 처리
- 댓글 기능 추가
- 검색 기능 (제목, 작성자 기준)
- 글/댓글 비밀번호 설정 기능 (수정/삭제 시 확인용)

## 9. 추가 기능
- Spring Data JPA 적용
- 페이징 적용


## 추가기능 구현(2025/05/06)
1. postResitory 생성
2. 참조 변경(service)
3. 더미 데이터 추가
4. BaseTimeEntity 생성 등록
5. posts/post.html 파일 수정
    - 게시물 수정된 경우 최근수정일 표현
6. 게시글 페이징 처리

