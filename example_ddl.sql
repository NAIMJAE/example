-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        8.0.44 - MySQL Community Server - GPL
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  12.14.0.7165
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- example 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `example` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `example`;

-- 테이블 example.articles 구조 내보내기
CREATE TABLE IF NOT EXISTS `articles` (
  `article_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `contents` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`article_id`),
  KEY `FKlc3sm3utetrj1sx4v9ahwopnr` (`user_id`),
  CONSTRAINT `FKlc3sm3utetrj1sx4v9ahwopnr` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 example.articles:~25 rows (대략적) 내보내기
DELETE FROM `articles`;
INSERT INTO `articles` (`article_id`, `user_id`, `title`, `contents`, `created_at`, `updated_at`) VALUES
	(1, 3, 'JPA 페이징 조회가 느린 이유', 'Pageable로 조회했는데 count 쿼리가 같이 나가면서 느려지는 느낌이다. countQuery를 분리하거나 조건을 단순화하면 개선될 수 있다.', '2026-02-02 10:22:40', '2026-02-02 10:22:40'),
	(2, 1, '요즘 공부 루틴 정리', '아침 30분은 CS, 점심 이후엔 코드 실습, 밤엔 복습과 블로그 정리로 고정했다. 루틴만 잡혀도 불안감이 줄어든다.', '2026-02-01 11:05:12', '2026-02-05 23:10:07'),
	(3, 5, 'MySQL 인덱스, 어디에 걸어야 하나', 'WHERE에 자주 쓰는 컬럼, JOIN 키, ORDER BY에 자주 쓰는 컬럼 위주로 후보를 잡는다. 무작정 인덱스는 오히려 쓰기 성능을 깎는다.', '2026-02-04 22:10:55', '2026-02-04 22:10:55'),
	(4, 2, '예외 처리 기본 규칙', '없는 리소스는 NoSuchElementException처럼 의미가 있는 예외로. 나중에 @ControllerAdvice로 한 번에 묶기 쉽다.', '2026-02-01 13:40:18', '2026-02-01 13:40:18'),
	(5, 4, '컨텍스트 패스 설정 팁', '/article 같은 prefix를 붙이면 라우팅이 깔끔해진다. 다만 프록시/게이트웨이 쓰면 path가 겹치지 않게 주의한다.', '2026-02-03 15:03:09', '2026-02-03 15:03:09'),
	(6, 1, 'Spring Validation 실수 모음', '@Valid를 붙였는데 동작 안 하면 컨트롤러 파라미터 위치, DTO 필드 타입, starter-validation 의존성을 먼저 체크한다.', '2026-02-05 09:10:31', '2026-02-06 00:12:44'),
	(7, 3, 'DTO 프로젝션 장점', '엔티티를 그대로 노출하지 않고 필요한 필드만 내려주니 응답이 가볍다. 다만 JPQL 문자열이 길어져서 유지보수는 신경 써야 한다.', '2026-02-02 18:45:22', '2026-02-02 18:45:22'),
	(8, 2, '삭제 권한 체크는 어디서?', '서비스 계층에서 권한/소유권 검사를 해두면 컨트롤러가 얇아진다. 예제에서는 SecurityException으로 단순 처리했다.', '2026-02-01 20:08:05', '2026-02-07 10:20:13'),
	(9, 5, 'JPA updateInfo 메서드 스타일', 'null/blank 방어로 부분 업데이트를 허용했다. 실무에서는 patch DTO와 함께 검증 정책을 분리하는 편이 안전하다.', '2026-02-04 23:01:49', '2026-02-04 23:01:49'),
	(10, 4, '로그에 바인딩 값까지 찍기', 'SQL만 보면 ? 때문에 답답하다. org.hibernate.orm.jdbc.bind=trace를 켜면 파라미터 값까지 확인할 수 있다.', '2026-02-07 08:35:10', '2026-02-07 08:35:10'),
	(11, 3, 'JPQL LIKE 검색 다듬기', 'searchType/title/contents 분기에서 AND/OR 괄호가 헷갈린다. 조건을 명확히 묶어서 의도대로 동작하게 만들었다.', '2026-02-08 11:22:09', '2026-02-09 09:14:55'),
	(12, 1, '테이블 네이밍: users vs user', 'user는 예약어/충돌이 나는 DB도 있어서 users가 편하다. 다만 엔티티명은 User 그대로 두고 @Table로만 매핑했다.', '2026-02-05 12:30:00', '2026-02-05 12:30:00'),
	(13, 2, 'Page와 Slice 차이', 'Page는 count 쿼리로 전체 개수를 구한다. 더 가볍게 무한 스크롤만 필요하면 Slice가 낫다.', '2026-02-06 14:05:27', '2026-02-06 14:05:27'),
	(14, 4, 'createdAt/updatedAt 어디서 관리?', 'DB 디폴트로도 가능하고, JPA @PrePersist/@PreUpdate로도 가능하다. 예제에서는 JPA로만 단순하게 관리했다.', '2026-02-07 09:02:41', '2026-02-11 19:10:02'),
	(15, 5, '개발용 ddl-auto 추천', '완전 초기에는 create, 데이터 유지하고 싶으면 update. 운영은 validate/none이 안전하다.', '2026-02-10 01:15:33', '2026-02-10 01:15:33'),
	(16, 1, 'REST 응답 형태 고민', '지금은 DTO 그대로 내려주는데, 나중엔 {code, message, data}로 감싸도 된다. 예제에서는 단순하게 간다.', '2026-02-09 10:30:30', '2026-02-09 10:30:30'),
	(17, 3, 'N+1 문제 체감 포인트', '목록에서 user를 같이 보여주면 N+1이 터질 수 있다. DTO 프로젝션이나 fetch join으로 해결 가능하다.', '2026-02-08 18:12:15', '2026-02-08 18:12:15'),
	(18, 2, '컨트롤러가 두꺼워지는 신호', '검증/권한/비즈니스 로직이 컨트롤러에 쌓이면 분리 시점이다. 서비스로 이동하고 컨트롤러는 입출력만 맡긴다.', '2026-02-06 20:40:02', '2026-02-06 21:05:19'),
	(19, 5, 'MySQL TEXT vs VARCHAR', '본문은 TEXT가 편하고, 제목은 길이 제한을 두는 편이 좋다. 검색이 많으면 전문 검색(Fulltext)도 고려한다.', '2026-02-10 03:22:48', '2026-02-12 09:12:12'),
	(20, 4, '테스트 더블로 레포지토리 검증', '슬라이스 테스트(@DataJpaTest)로 레포지토리 동작을 빠르게 확인할 수 있다. 예제에서는 간단히 starter-test로 시작한다.', '2026-02-07 13:11:07', '2026-02-07 13:11:07'),
	(21, 1, '쿼리 로그 너무 많이 찍힐 때', 'show-sql을 끄고 org.hibernate.SQL만 debug로 조절하면 덜 시끄럽다. 운영에서는 로깅 레벨을 낮추는 게 기본이다.', '2026-02-11 08:01:09', '2026-02-11 08:01:09'),
	(22, 2, '권한 예외 메시지 정리', '“삭제 권한이 없음”처럼 짧고 명확하게. 예제에서는 SecurityException 하나로 단순 처리한다.', '2026-02-11 12:44:20', '2026-02-11 12:44:20'),
	(23, 3, '검색 키워드가 비었을 때 처리', '키워드가 null/blank면 전체 조회로 처리했다. 클라이언트 구현이 단순해진다.', '2026-02-09 19:33:33', '2026-02-09 19:33:33'),
	(24, 4, 'JOIN은 연관관계로 쓰기', 'JPQL에서 JOIN a.user u 형태가 가장 깔끔하다. 엔티티 매핑을 믿고 간다.', '2026-02-10 09:09:09', '2026-02-10 09:09:09'),
	(25, 5, '예제용 더미데이터 만들기', '회원/게시글/시간을 실제 서비스처럼 흉내 내면 화면 테스트가 쉬워진다. 페이징/검색도 바로 검증 가능하다.', '2026-02-12 20:05:50', '2026-02-12 20:05:50');

-- 테이블 example.users 구조 내보내기
CREATE TABLE IF NOT EXISTS `users` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `login_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 example.users:~5 rows (대략적) 내보내기
DELETE FROM `users`;
INSERT INTO `users` (`user_id`, `login_id`, `password`, `name`, `created_at`, `updated_at`) VALUES
	(1, 'user01', 'pass01', '홍길동', '2026-02-01 09:12:10', '2026-02-01 09:12:10'),
	(2, 'user02', 'pass02', '김철수', '2026-02-01 09:15:44', '2026-02-03 18:22:11'),
	(3, 'user03', 'pass03', '이영희', '2026-02-02 10:01:05', '2026-02-02 10:01:05'),
	(4, 'user04', 'pass04', '박민수', '2026-02-03 14:20:33', '2026-02-07 08:11:02'),
	(5, 'user05', 'pass05', '최지우', '2026-02-04 21:45:19', '2026-02-04 21:45:19');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
