# Chapter 2 : 스프링 코어

- [Chapter 2 : 스프링 코어](#chapter-2--스프링-코어)
  - [레시피 2-5 : @Scope를 붙여 POJO 스코프 지정하기](#레시피-2-5--scope를-붙여-pojo-스코프-지정하기)

## 레시피 2-5 : @Scope를 붙여 POJO 스코프 지정하기

`getBean()` 메서드를 사용해서 빈 인스턴스를 요청하면

스프링 컨테이너에서는 빈 스코프에 따라 인스턴스를 생성하거나 이미 생성되어 있는 인스턴스를 반환합니다.

빈 인스턴스의 기본 스코프는 `singleton`입니다.

`@Scope`를 사용해서 빈 스코프를 지정할 수 있습니다.

지정할 수 있는 스코프의 종류는 다음과 같습니다.

| 스코프 | 설명 |
|:---:|:---|
| singleton | IoC 컨테이너 당 하나의 빈 인스턴스를 생성 |
| prototype | 요청할 때마다 새로 빈 인스턴스를 생성 |
| request | HTTP 요청당 하나의 빈 인스턴스를 생성 |
| session | HTTP 세션당 하나의 빈 인스턴스를 생성 |
| globalSession | 전역 HTTP 세션 당 하나의 빈 인스턴스를 생성 |

> `recipe_2_5_i`에서는 `ShoppingCart` 클래스의 빈 인스턴스가 `싱글턴`으로 생성되었기 때문에 `getBean()` 메서드를 여러번 호출해도 같은 인스턴스를 참조하는 반면,  

> `recipe_2_5_ii`에서는 `ShoppingCart` 클래스를 `@Scope("prototype")`로 설정하여 `getBean()` 메서드가 호출될 때마다 새로운 인스턴스를 생성한다.