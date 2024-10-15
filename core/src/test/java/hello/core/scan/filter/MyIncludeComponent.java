package hello.core.scan.filter;

import java.lang.annotation.*;

// 클래스, 인터페이스, Enum
@Target(ElementType.TYPE)

@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent {

}
