package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        //컴포넌트 스캔해서 자동으로 스프링빈으로 등록하는데 그 중에 뺄 것 지정
        //다른 AppConfig,TestConfig도 빈으로 등록이 돼서 실행이 됨 기존 예제 코드를 살리기 위해 작성
//        basePackages = "hello.core.member",
//        basePackageClasses = AutoAppConfig.class,
        excludeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION,classes = Configuration.class)
)
public class AutoAppConfig {
    //hello.core에 있기때문에 여기있는 폴더는 모두 빈 등록
    //설정 정보 클래스의 위치를 프로젝트 최상단에 두자.(basePackages 사용X)

}
