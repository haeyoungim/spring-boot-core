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
-클래스의 인스턴스가 1개만 생성되는것을 보장하는 디자인 패턴
-싱글톤이 없으면 호출할때마다 객체를 생성하기 때문에 메모리 낭비가 심함.
1.static 영역에 객체 instance를 미리 하나 생성해서 올려둔다.
2.이 객체 인스턴스가 필요하면 오직 getInstance() 메서드를 통해서만 조회 할 수 있다.
이 메서드를 호출하면 항상 같은 인스턴스를 반환한다.
3.딱 1개의 객체 인스턴스만 존재해야 하므로, 생성자를 private으로 막아서 혹시라도 외부에서 new 키워드로
객체 인스턴스가 생성되는 것을 막는다.

spring container를 쓰면 spring이 싱글톤으로 해서 관리해준다.


싱클톤 컨테이너
-객체 인스턴스를 싱글톤으로 관리한다
-스프링 빈이 싱글톤으로 관리되는 빈이다
-스프링 컨테이너는 싱글톤 컨테이너 역할을 한다.
-싱글톤 객체를 생성하고 관리하는 기능을 싱글톤 레지스트리라 한다.
-스프링 컨테이너는 싱글턴 패턴의 단점을 해결하면서 객체를 싱글톤으로 유지한다
  -싱글톤 패턴을 위한 지저분한코드
  -DIP,OCP테스트,private 생성자로 부터 자유롭게 싱글톤을 사용 할 수 있다.