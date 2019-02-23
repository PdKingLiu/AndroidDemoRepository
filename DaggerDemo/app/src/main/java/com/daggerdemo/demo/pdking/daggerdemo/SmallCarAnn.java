package com.daggerdemo.demo.pdking.daggerdemo;


import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Qualifier
@Retention(RUNTIME)
public @interface SmallCarAnn {
}
