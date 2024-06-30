package hello.core.scan.filter;


import java.lang.annotation.*;

//type : 클래스레벨에 붇음
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent {
}
