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