package egovframework.evcar.common.annotation;

import java.lang.annotation.*;

/**
 * LoginCheck Annotation
 * Created by dongguk on 2017-06-02.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginCheck {

    boolean value() default true;
}
