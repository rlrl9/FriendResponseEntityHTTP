# FriendResponseEntityHTTP

(1) entity 패키지를 만들고 다음과 같은 필드를 갖는 Friend 라는 엔티티 클래스를 생성한다.

 스프링 부트 앱이 기동될 때 이 패키지 폴더를 스캔하도록 이 패키지 폴더의 정보를 추가한다.
 
id – int ( PK, auto increment )

fname – String

fage – Integer

(2) repository 패키지를 만들고 CRUD 가 가능한 FriendRepository 를 생성한다.

스프링 부트 앱이 기동될 때 이 패키지 폴더를 스캔하도록 이 패키지 폴더의 정보를 추가한다.

(3) CRUD 기능을 점검하는 테스트 클래스 FriendTest를 생성하여 입력, 추출, 수정, 삭제 등을
테스트 한다. 또한 데이터가 3개 정도 남아 있게 한다.

(4) controller 패키지에 FrindController 생성한다.

 - 친구 데이터의 명칭(URI)를 적당하게 정한다. → 자원에 대한 식별 가능한 이름
   
 - 친구 데이터의 전체 리스트를 JSON 형식으로 리턴하는 메서드를 구현한다. - GET 방식
   
 - ID 를 입력하면 해당 친구 데이터를 JSON 형식으로 리턴하는 메서드를 구현한다. - GET 방식
   
 - 친구 이름을 입력하면 해당 친구 데이터를 JSON 형식으로 리턴하는 메서드를 구현한다. –
GET 방식

 - 클라이언트에서 JSON 형식으로 전달된 데이터를 Friend 테이블에 저장하는 메서드를
구현한다. 이 땐 ID 외의 데이터만 전달한다. – POST 방식

- 클라이언트에서 JSON 형식으로 전달된 데이터를 Friend 테이블에 저장하는 메서드를
구현한다. 이 때는 ID 도 전달해야 한다. – PUT 방식

 - 클라이언트에서 전달된 ID 를 가지고 데이터를 삭제하는 메서드를 구현한다. – DELETE 방식
 모든 응답은 ResponseEntity 를 사용한다.

 - 전체 리스트 요청 : 응답 코드 200, 내용(없을 수도 있음)
   
 - ID 로 친구 데이터 1개 요청 : 존재하면 200과 함께 친구 정보
   
 존재하지 않으면 응답코드 404 와 함께 응답 헤더에 다음을 추가

 BAD_ID : id값
 
 - 입력 : 성공하면 응답코드 201(Created), 실패하면 500과 실패했다는 메시지
   
 - 수정과 삭제 : 성공하면 205(Rest Content), 실패하면 500과 실패했다는 메시지
 
상태코드를 지정하여 응답할 때는 org.springframework.http.HttpStatus의 해당 상수를
사용한다.

(5) 모든 테스트는 Talend API Tester 를 사용한다.
