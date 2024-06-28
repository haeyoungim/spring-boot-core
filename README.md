- ApplicationContext = spring container
- 기존 : Appconfig 사용해서 객체 생성하고 DI
  현재 : @Configuration이 붙은 AppConfig를 설정 정보로 사용하여 @Bean이라 적힌 메소드를 모두 호출해 반환된 객체를 스프링 컨테이너에 등록
- 스프링 컨테이너에 등록된 객체 = 스프링 빈
- @Bean이 붙은 메서드의 명을 스프링 빈의 이름으로 사용
- 스프링 빈은 applicationContext.getBean 메서드를 사용해서 찾을 수 있다.
- 스프링 컨테이너 -> 스프링 빈 객체 등록 -> 스프링 컨테이너 에서 스프링 빈 찾아 사용

- 모든 빈 출력하기
- ac.getBeanDefinitionNames() : 스프링에 등록된 모든 빈 이름을 조회
- ac.getBean() : 빈 이름으로 빈 객체(인스턴스)를 조회
- Role ROLE_APPLICATION : 직접 등록한 애플리케이션 빈
- Role ROLE_INFRASTRUCTURE : 스피링이 내부에서 사용하는 빈



- 자바의 모든 최상위 객체는 object
- object 타입으로 조회하면 모든 스프링 빈을 조회하게됨



- ApplicationContext는 BeanFactory의 기능을 상속받는다.
- ApplicationContext는 빈 관리기능 + 편리한 부가 기능을 제공한다.
- BeanFactory를 직접 사용할 일은 거의 없다. 부가기능이 포함 된 ApplicationContext를 사용한다.
- BeanFactory나 ApplicationContext를 스프링 컨테이너라 한다.



Singleton
- 클래스의 인스턴스가 1개만 생성되는것을 보장하는 디자인 패턴
- 싱글톤이 없으면 호출할때마다 객체를 생성하기 때문에 메모리 낭비가 심함.
1. static 영역에 객체 instance를 미리 하나 생성해서 올려둔다.
2. 이 객체 인스턴스가 필요하면 오직 getInstance() 메서드를 통해서만 조회 할 수 있다.
이 메서드를 호출하면 항상 같은 인스턴스를 반환한다.
3. 딱 1개의 객체 인스턴스만 존재해야 하므로, 생성자를 private으로 막아서 혹시라도 외부에서 new 키워드로
객체 인스턴스가 생성되는 것을 막는다.

spring container를 쓰면 spring이 싱글톤으로 해서 관리해준다.


싱클톤 컨테이너
- 객체 인스턴스를 싱글톤으로 관리한다
- 스프링 빈이 싱글톤으로 관리되는 빈이다
- 스프링 컨테이너는 싱글톤 컨테이너 역할을 한다.
- 싱글톤 객체를 생성하고 관리하는 기능을 싱글톤 레지스트리라 한다.
- 스프링 컨테이너는 싱글턴 패턴의 단점을 해결하면서 객체를 싱글톤으로 유지한다
  - 싱글톤 패턴을 위한 지저분한코드
  - DIP,OCP테스트,private 생성자로 부터 자유롭게 싱글톤을 사용 할 수 있다.

싱글톤 방식의 주의점
- 객체 인스턴스를 하나만 생성해서 공유하는 싱글톤 방식(싱글톤 패턴,스프링 싱글톤 컨테이너)은 여러 클라이언트가 하나의 같은 객체 인스턴스를 공유하기 때문에 싱글톤 객체는 상태를 유지(stateful)하게 설계하면 안된다.
- 무상태(stateless)로 설계해야 한다!
  - 특정 클라이언트에 의존적인 필드 존재 X
  - 특정 클라이언트가 값을 변경할 수 있는 필드 존재 X
  - 가급적 읽기만 가능 ( 값 수정 X )
  - 필드 대신에 자바에서 공유되지 않는, 지역변수,파라미터,ThreadLocal 등을 사용해야 한다.
- 스프링 빈의 필드에 공유값을 설정하면 정말 큰 장애 발생!

- WEB 요청 -> 사용자에 각각 Thread 할당
- ThreadA가 사용자A 코드를 호출하고 ThreadB가 사용자B 코드를 호출한다 가정
 -> 사용자 A의 주문금액은 10000원인데 20000원이라는 결과가 나옴.
- 공유 필드는 조심해야함. 스프링 빈은 항상 무상태(stateless)로 설계
- 내 아이딘데 다른 사람 이름이 보이거나 다른사람 결제내역이 보이는 상황 발생

@Configuration
- 싱글톤을 보장
- @Bean memberService -> new MemoryMemberRepository()
- @Bean orderService -> new MemoryMemberRepository()
  - 싱글톤이 깨진다? @Configuration 때문에 안깨진다
- CGLIB라는 바이트코드 조작 라이브러리를 사용해서 AppConfig 클래스를 상속받은 임의의 다른 클래스를 만들고, 그 다른 클래스를 스프링 빈으로 등록한것.
- @Configuration 없이 @Bean만 사용해도 스프링 빈이 등록 되지만 싱글톤은 보장 되지 않음
- memberRepository 처럼 의존관계 주입이 필요해 메서드를 직접 호출할때 싱글톤 보장 안됨
- 스프링 설정 정보는 항상 @Configuration 사용!