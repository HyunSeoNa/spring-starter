# Srpring bean 등록 방법
## Component Scan과 자동 의존 설정
* @Component를 포함하는 Annotation은 스프링 빈으로 자동 등록된다.
  * @Controller
  * @Service
  * @Repository
* @Autowired가 있으면 스프링이 연관된 객체를 스프링 컨테이너에서 찾아서 넣어준다.(=> DI(의존성 주입))
## 자바 코드로 직접 스프링 빈 등록하기
* @Service, @Repository, @Autowired 어노테이션을 제거하고 @Configuration을 활용한다.
* @Bean을 사용하여 직접 빈으로 등록한다.

<참고>
* 스프링은 스프링 컨테이너에 스프링 빈을 등록할 때, 기본으로 싱글톤으로 등록한다.
  * 싱글톤 : 유일하게 하나만 등록해서 공유한다. => 같은 스프링 빈이면 모두 같은 인스턴스
* 정형화된 컨트롤러, 서비스, 리포지토리 같은 코드는 컴포턴트 스캔을 사용한다.
* 상황에 따라 구현 클래스를 변경해야 하는 경우에는 설정을 통해 스프링 빈으로 등록한다.


# 스프링 DB 접근 기술
## JDBC
### 정의
* JDBC (Java Database Connectivity)
* SQL을 실행하며 결과를 처리할 수 있도록 해주는 표즌 인터페이스(API)
* 자바 코드로 직접 DB에 연결하고 SQL을 실행하는 방법

### JDBC 실행 순서
1. JDBC 드라이버 로드
2. DB 연결 - Connection 생성
3. SQL 작성 및 실행
4. 결과 처리
5. 사용 자원 해제

### JDBC 단점
1. 반복적이고 복잡한 코드
   * SQL 실행을 위해 Connection, PreparedStatement, ResultSet을 직접 작성해야한다.
   * try-catch-finally로 자원 해제 코드를 직접 작성해야한다.(=> 코드가 길어지고 가독성이 떨어진다.)

## JDBC Template
### 정의
* Springframework 에서 제공하는 JDBC 클래스
* 순수 JDBC 코드를 단순화하여 SQL 중심의 데이터 접근을 쉽게 만들어주는 도구
* 순수 JDBC의 반복적이고 복잡한 코드를 대신 처리

### JDBC Template 동작
1. DB Connection 관리 - Connection 자동 생성 및 해제
2. SQL 실행 - PreparedStatement 자동 생성 및 실행
3. 예외처리
4. 사용 자원 해제 - ResultSet, Statement, Connection 자동 해제 
5. 반복 코드 제거 - try-catch-finally 제거

### JDBC Template에서 순수 JDBC의 단점 개선점
1. 코드 복잡도
   * 코드 중복 제거
   * Connection, Statement, ResultSet 자동 관리
2. 사용 자원 자동 정리
   * close()를 수동으로 호출하지 않아도 됨.

## JPA (Java Persistence API)
### 정의
* 자바의 ORM(Object Relational Mapping) 표준 인터페이스
* 객체와 데이터베이스 테이블을 자동으로 매핑해주는 기술의 표준
* JPA는 규약이고, 구현체로는 Hibernate, EclipseLink, OpenJPA가 있다. 
* SpringBoot는 구현체를 기본적으로 Hibernate를 사용

### JPA 주요 기능
1. ORM - 객체와 데이터베이서의 테이블을 맵핑
2. Entity - 데이터베이스 테이블과 매핑되는 자바 클래스
3. EntityManager - 객체를 저장, 조회, 수정, 삭제하는 JPA의 중요 인터페이스
4. Persistence Context - 객체의 생명주기를 관리하는 캐시 같은 공간

### JPA에서 JDBC Template의 개선점
1. SQL을 직접 작성하지 않아도 된다.
2. ResultSet을 사용하지 않아도 객체가 자동으로 맵핑된다.
3. 데이터베이스의 종류가 변경되어도 수정 범위가 작다.

## Spring Data JPA
### 정의
* SpringFramework에서 제공하는 JPA 사용을 더 단순화 한 모듈
* 순수 JPA 반복 코드를 줄이고 CRUD 쿼리와 Repository 구현을 자동으로 생성해주는 기술

### Spring Data JPA 동작
1. 개발자가 Repository 인터페이스를 정의
2. Spring이 런타임에 해당 인터페이스 구현체를 자동으로 생성
3. 내부적으로 JPA(EntityManager)를 사용하여 SQL을 실행한다.

### Srping Data JPA 제공 Class
#### Spring Data
[CrudRepository]
1. save(S) : S
2. findOne(ID) : T
3. exists(ID) : boolean
4. count(): long
5. delete(T)

[PagingAndSortingRepository]
1. findAll(Sort) : Iterable\<T>
2. findAll(Pageable) : Page\<T>

##### Spring Data JPA
[JpaRepository]
1. findAll() : List\<T>
2. findALl(Sort) : List\<T>
3. findAll(Iterable\<ID>) : List\<T>
4. save(Iterable\<S>) : List\<S>
5. flush()
6. saveAndFlush(T) : T
7. deleteInBatch(Iterable\<T>)
8. deleteAllInBatch()
9. getOne(ID) : T

### Spring Data JPA에서 순수 JPA의 개선점
1. Repository 구현체 작성 불필요 - Repository 구현을 인터페이스만 정의하면 Spring이 런타임에 자동으로 생성해준다.
2. 기본 CRUD 코드 자동화 - persist(), find()를 직접 호출하지 않아도 된다.
3. JPQL 퀴리 대신에 메서드 이름 기반 쿼리를 자동 생성해준다.

# 기술 발전 요약
| 기술| 요약 | 개선 |
|----------|-----------------------|-----------------|
| 순수 JDBC | DB 연결과 SQL 직접 작성 | 기본적인 SQL 접근 |
| JDBC Template | 반복 코드 제거 | 사용 자원 관리 자동화 |
| 순수 JPA | SQL 자동 생성, ORM 적용 | 객체 중심 개발 |
| Spring Data JPA | Repository 자동 생성, 메서드 이름 기반 쿼리 | Repository, SQL 자동 생성 |
