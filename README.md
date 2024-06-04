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
- //커밋테스트 심어져라