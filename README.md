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

컴포넌트스캔
- `basePackages` : 탐색할 패키지의 시작 위치를 지정한다. 이 패키지를 포함해서 하위 패키지를 모두 탐색한다.
- `basePackageClasses` : 지정한 클래스의 패키지를 탐색 시작 위치로 지정한다.
  - 만약 지정하지 않으면 `@ComponentScan` 이 붙은 설정 정보 클래스의 패키지가 시작 위치가 된다.
- 권장 방법 : 설정 정보 클래스를 최상단 프로젝트에 두는것
- `@Component` : 컴포넌트 스캔에서 사용
- `@Controller` : 스프링 MVC 컨트롤러에서 사용
- `@Service` : 스프링 비즈니스 로직에서 사용
- `@Repository` : 스프링 데이터 접근 계층에서 사용 
- `@Configuration` : 스프링 설정 정보에서 사용
  - 컴포넌트 스캔의 용도 뿐만 아니라 다음 애노테이션이 있으면 스프링은 부가 기능을 수행한다.
  - `@Controller` : 스프링 MVC 컨트롤러로 인식
  - `@Repository` : 스프링 데이터 접근 계층으로 인식하고, 데이터 계층의 예외를 스프링 예외로 변환해준다. 
  - `@Configuration` : 앞서 보았듯이 스프링 설정 정보로 인식하고, 스프링 빈이 싱글톤을 유지하도록 추가 처리를 한다.
  - `@Service` : 사실 `@Service` 는 특별한 처리를 하지 않는다. 대신 개발자들이 핵심 비즈니스 로직이 여기에 있겠구나 라고 비즈니스 계층을 인식하는데 도움이 된다.
- `@Autowired`
  - 생성자에서 여러 의존관계도 한번에 주입받을 수 있다.
  - 생성자에 `@Autowired` 를 지정하면, 스프링 컨테이너가 자동으로 해당 스프링 빈을 찾아서 주입한다.
  - 이때 기본 조회 전략은 타입이 같은 빈을 찾아서 주입한다
  - `getBean(MemberRepository.class)` 와 동일하다고 이해하면 된다.


필터
- `includeFilters` : 컴포넌트 스캔 대상을 추가로 지정한다. 
  - include는 사용할 일이 거의없다.
- `excludeFilters` : 컴포넌트 스캔에서 제외할 대상을 지정한다.
- `@Component` 면 충분하기 때문에, `includeFilters` 를 사용할 일은 거의 없다. 
- `excludeFilters` 는 여러가지 이유로 간혹 사용할 때가 있지만 많지는 않다.
  특히 최근 스프링 부트는 컴포넌트 스캔을 기본으로 제공하는데, 개인적으로는 옵션을 변경하면서 사용하기 보다 는 스프링의 기본 설정에 최대한 맞추어 사용하는 것을 권장하고, 선호하는 편이다.

중복 등록과 충돌
- 수동 빈이 자동 빈을 오버라이딩 한다.
- 여러 설정들이 꼬여서 이런 결과가 만들어지는 경우가 대부분인데 잡기 어려운 버그가 만들어진다.
- 최근 스프링 부트에서는 수동 빈 등록과 자동 빈 등록이 충돌나면 오류가 발생하도록 기본 값을 바꾸었다.
- 명확하지 않은건 하면 안된다/애매한 상황을 만들지 않는다/어설픈 추상화,우선수위 X/빨리 오류내서 튕기게

의존관계 자동 주입
- 생성자 주입
- 수정자 주입(setter 주입)
- 필드 주입 
- 일반 메서드 주입

- 생성자 주입 
  - 생성자를 통해서 의존 관계를 주입 받는 방법
  - 생성자 호출시점에 딱 1번만 호출되는 것이 보장된다. 
  - **불변, 필수** 의존관계에 사용
  - **생성자가 딱 1개만 있으면 @Autowired를 생략해도 자동 주입 된다.** 물론 스프링 빈에만 해당한다.
- 수정자 주입(setter 주입)
  - setter라 불리는 필드의 값을 변경하는 수정자 메서드를 통해서 의존관계를 주입하는 방법이다.
  - **선택, 변경** 가능성이 있는 의존관계에 사용
  - 자바빈 프로퍼티 규약의 수정자 메서드 방식을 사용하는 방법이다.
  - `@Autowired` 의 기본 동작은 주입할 대상이 없으면 오류가 발생한다. 주입할 대상이 없어도 동작하게 하 려면 `@Autowired(required = false)` 로 지정하면 된다.
  - 자바빈 프로퍼티 : 자바에서는 과거부터 필드의 값을 직접 변경하지 않고, setXxx, getXxx 라는 메서드를 통해서 값을 읽거나 수정하는 규칙을 만들었는데, 그것이 자바빈 프로퍼티 규약이다.
- 필드 주입
  - 코드가 간결해서 많은 개발자들을 유혹하지만 외부에서 변경이 불가능해서 테스트 하기 힘들다는 치명적인 단점이 있다.
  - DI 프레임워크가 없으면 아무것도 할 수 없다.
  - **사용하지 말자!**
    - 애플리케이션의 실제 코드와 관계 없는 테스트 코드
    - 스프링 설정을 목적으로 하는 @Configuration 같은 곳에서만 특별한 용도로 사용
  -`@Bean` 에서 파라미터에 의존관계는 자동 주입된다. 수동 등록시 자동 등록된 빈의 의존 관계가 필요할 때 문제를 해결할 수 있다.
- 일반 메서드 주입
  - 일반 메서드를 통해서 주입 받을 수 있다.
  - 한번에 여러 필드를 주입 받을 수 있다. 
  - 일반적으로 잘 사용하지 않는다.
- 의존관계 자동 주입은 스프링 컨테이너가 관리하는 스프링 빈이어야 동작한다.

- 옵션 처리
- 주입할 스프링 빈이 없어도 동작해야 할때
- 자동 주입 대상을 옵션으로 처리하는 방법
  - `@Autowired(required=false)` : 자동 주입할 대상이 없으면 수정자 메서드 자체가 호출 안됨 
  - `org.springframework.lang.@Nullable` : 자동 주입할 대상이 없으면 null이 입력된다. 
  - `Optional<>` : 자동 주입할 대상이 없으면 `Optional.empty` 가 입력된다.
- @Nullable, Optional은 스프링 전반에 걸쳐서 지원된다. 예를 들어서 생성자 자동 주입에서 특정 필드에 만 사용해도 된다.

- 생성자 주입
  - 대부분의 의존관계 주입은 한번 일어나면 애플리케이션 종료시점까지 의존관계를 변경할 일이 없다. 오히려 대부 분의 의존관계는 애플리케이션 종료 전까지 변하면 안된다.(불변해야 한다.)
  - 수정자 주입을 사용하면, setXxx 메서드를 public으로 열어두어야 한다.
  - 누군가 실수로 변경할 수 도 있고, 변경하면 안되는 메서드를 열어두는 것은 좋은 설계 방법이 아니다.
  - 생성자 주입은 객체를 생성할 때 딱 1번만 호출되므로 이후에 호출되는 일이 없다. 따라서 불변하게 설계할 수 있다.
  - **final 키워드**
    - 생성자 주입을 사용하면 필드에 `final` 키워드를 사용할 수 있다. 그래서 생성자에서 혹시라도 값이 설정되지 않는 오
      류를 컴파일 시점에 막아준다.
  - 수정자 주입을 포함한 나머지 주입 방식은 모두 생성자 이후에 호출되므로, 필드에 `final` 키워드를 사용 할 수 없다. 오직 생성자 주입 방식만 `final` 키워드를 사용할 수 있다.
  - 생성자 주입 방식을 선택하는 이유는 여러가지가 있지만, 프레임워크에 의존하지 않고, 순수한 자바 언어의 특징 을 잘 살리는 방법이기도 하다.
  - 기본으로 생성자 주입을 사용하고, 필수 값이 아닌 경우에는 수정자 주입 방식을 옵션으로 부여하면 된다. 생성자 주입과 수정자 주입을 동시에 사용할 수 있다.
  - 항상 생성자 주입을 선택해라! 그리고 가끔 옵션이 필요하면 수정자 주입을 선택해라. 필드 주입은 사용하지 않는 게 좋다.

- 롬복
- `@RequiredArgsConstructor` : final이 붙은 필드를 모아서 생 성자를 자동으로 만들어준다
- 최근에는 생성자를 딱 1개 두고, `@Autowired` 를 생략하는 방법을 주로 사용한다. 여기에 Lombok 라이브러리의 `@RequiredArgsConstructor` 함께 사용하면 기능은 다 제공하면서, 코드는 깔끔하게 사용할 수 있다.

조회 빈이 2개 이상일때
- `@Autowired` 는 타입(Type)으로 조회한다.
- 타입으로 조회하기 때문에, 마치 다음 코드와 유사하게 동작한다.
  - `ac.getBean(DiscountPolicy.class)`
- 타입으로 조회하면 선택된 빈이 2개 이상일 때 문제가 발생한다.
  - `DiscountPolicy` 의 하위 타입인 `FixDiscountPolicy` , `RateDiscountPolicy` 둘다 스프링 빈으로 선언했음.
    - NoUniqueBeanDefinitionException` 오류가 발생한다. -> 하나의 빈을 기대했는데 두개가 나옴
    - 이때 하위 타입으로 지정할 수 도 있지만, 하위 타입으로 지정하는 것은 DIP를 위배하고 유연성이 떨어진다. 
    - 그리고 이름만 다르고, 완전히 똑같은 타입의 스프링 빈이 2개 있을 때 해결이 안된다.
    - 스프링 빈을 수동 등록해서 문제를 해결해도 되지만, 의존 관계 자동 주입에서 해결하는 여러 방법이 있다
- 해결방법
  - `@Autowired` 필드 명 매칭
    - `@Autowired` 는 타입 매칭을 시도하고, 이때 여러 빈이 있으면 필드 이름, 파라미터 이름으로 빈 이름을 추가 매칭한다.
    - @Autowired
      private DiscountPolicy rateDiscountPolicy
    - **필드 명 매칭은 먼저 타입 매칭을 시도 하고 그 결과에 여러 빈이 있을 때 추가로 동작하는 기능이다.**
    - 1. 타입매칭
    - 2. 타입매칭의결과가2개이상일때필드명,파라미터명으로빈이름매칭
  - `@Qualifier` @Qualifier끼리 매칭 빈 이름 매칭
    - `@Qualifier` 는 추가 구분자를 붙여주는 방법이다. 주입시 추가적인 방법을 제공하는 것이지 빈 이름을 변경하는 것은 아니다.
    - @Component
      @Qualifier("mainDiscountPolicy")
      public class RateDiscountPolicy implements DiscountPolicy {}
    - 생성자 자동 주입
      @Autowired
      public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) {
         this.memberRepository = memberRepository;
         this.discountPolicy = discountPolicy;
      }
    - **수정자 자동 주입 예시**
      @Autowired
      public DiscountPolicy setDiscountPolicy(@Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) {
      this.discountPolicy = discountPolicy;
      }
    - 1. @Qualifier끼리 매칭
    - 2. 빈이름매칭
    - 3. `NoSuchBeanDefinitionException` 예외 발생
  - `@Primary` 사용
    -`@Primary` 는 우선순위를 정하는 방법이다. @Autowired 시에 여러 빈이 매칭되면 `@Primary` 가 우선권을 가진다.
  - **@Primary, @Qualifier 활용**
    코드에서 자주 사용하는 메인 데이터베이스의 커넥션을 획득하는 스프링 빈이 있고, 코드에서 특별한 기능으로 가끔 사용하는 서브 데이터베이스의 커넥션을 획득하는 스프링 빈이 있다고 생각해보자. 메인 데이터베이스의 커넥션을 획득하 는 스프링 빈은 `@Primary` 를 적용해서 조회하는 곳에서 `@Qualifier` 지정 없이 편리하게 조회하고, 서브 데이터베 이스 커넥션 빈을 획득할 때는 `@Qualifier` 를 지정해서 명시적으로 획득 하는 방식으로 사용하면 코드를 깔끔하게 유지할 수 있다. 물론 이때 메인 데이터베이스의 스프링 빈을 등록할 때 `@Qualifier` 를 지정해주는 것은 상관없다.
  - 우선순위 @Qualifier > @Primary

애노테이션 직접 만들기
- `@Qualifier("mainDiscountPolicy")` : 문자로 적으면 컴파일 시 타입 체크가 안된다.
- `@Qualifier` 에 있는 애노테이션 사용해서 애노테이션 새로 만듦(@MainDiscountPolicy)
- 애노테이션에는 상속이라는 개념이 없다 .

조회한 빈이 모두 필요할 때, List, Map
- 해당 타입의 모든 스프링빈이 필요할때 ( rateDiscount , fixDiscount )
- 
