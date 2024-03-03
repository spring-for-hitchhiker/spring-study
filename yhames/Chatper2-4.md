# Chapter 2 : 스프링 코어

- [Chapter 2 : 스프링 코어](#chapter-2--스프링-코어)
  - [레시피 2-4 : @Resource와 @Inject를 붙여 POJO 자동 연결하기](#레시피-2-4--resource와-inject를-붙여-pojo-자동-연결하기)
    - [@Resource로 POJO 자동 연결하기](#resource로-pojo-자동-연결하기)
    - [@Inject로 POJO 자동 연결하기](#inject로-pojo-자동-연결하기)

## 레시피 2-4 : @Resource와 @Inject를 붙여 POJO 자동 연결하기

### @Resource로 POJO 자동 연결하기

자바 표준(`JSR-250`)에는 `@Autowired` 대신 `@Resource`를 지원한다. `@Resource`는 기능상 `@Autowired`와 `@Qualifier`를 합친 것과 같다.

### @Inject로 POJO 자동 연결하기

`JSR-330`에 `@Inject`가 추가되었다.

`@Resource`와 `@Autowired` 처럼 타입으로 POJO를 자동으로 연결하는 기능은 동일하지만, 이름으로 연결하기 위해서는 `@Qualifier` 어노테이션을 추가한 커스텀 어노테이션을 작성해야 한다.

```java
@Qualifier
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.PARAMETER})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface NumberPrefixAnnotation {
}
```

해당 어노테이션을 사용하기 위해서는 javax.inject 패키지를 반드시 가져와야한다.

```groovy
plugins {
    id 'java'
}

repositories {
    mavenCentral()
}

dependencies {
    implementation 'org.springframework:spring-context:5.3.32'
    implementation 'javax.inject:javax.inject:1'    // for JSR-330
}

jar {
    manifest.attributes('Main-Class': 'com.apress.springrecipes.sequence.Main')
}
```

> `@Resource`와 `@Inject`는 자바 표준 어노테이션으로 스프링 종속성을 갖지 않는다. 따라서 다른 프레임워크나 컨테이너를 사용한다면 표준 어노테이션을 고려해 볼 수 있다. 대부분 표준 어노테이션을 사용하는 것이 좋을 수 있지만 스프링에서 더 많은 기능을 제공하거나 스프링 프레임워크를 사용하는 프로젝트라면 스프링에서 제공하는 어노테이션을 사용하는 것이 좋다.