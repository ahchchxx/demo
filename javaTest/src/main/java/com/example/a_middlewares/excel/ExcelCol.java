package com.example.a_middlewares.excel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * annotation for entity's field,
 *  matched with excel column title
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ExcelCol {
    String title();

    Class type() default String.class;// especially for date
    String format() default "";

    int index() default 0;// the index of duplicate col title
}