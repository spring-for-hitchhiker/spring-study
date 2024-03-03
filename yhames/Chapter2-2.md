# Chapter 2 : 스프링 코어

- [Chapter 2 : 스프링 코어](#chapter-2--스프링-코어)
  - [레시피 2-2 : 생성자 호출해서 POJO 생성하기](#레시피-2-2--생성자-호출해서-pojo-생성하기)

## 레시피 2-2 : 생성자 호출해서 POJO 생성하기

`IoC의 컨테이너`에 같은 추상 클래스를 상속하는 POJO(빈)을 해당 추상 클래스의 인스턴스로 등록할 수 있다.

하나의 추상 클래스를 사용하여 둘 이상의 POJO를 등록하면, 헤당 구성 클래스에서 `@Bean`을 추가한 `메서드 이름`으로 등록된다.

예를 들어 다음과 같은 코드가 있다면,

```java
@Configuration
public class ShopConfiguration {

    @Bean
    public Product aaa() {
        Battery p1 = new Battery("AAA", 2.5);
        p1.setRechargeable(true);
        return p1;
    }

    @Bean
    public Product cdrw() {
        Disc p2 = new Disc("CD-RW", 1.5);
        p2.setCapacity(700);
        return p2;
    }
}
```

해당 매서드 이름과 추상클래스를 사용하여 POJO(빈)을 가져올 수 있다.

```java
public class Main {

    public static void main(String[] args) throws Exception {

        ApplicationContext context =
                new AnnotationConfigApplicationContext(ShopConfiguration.class);

        Product aaa = context.getBean("aaa", Product.class);
        Product cdrw = context.getBean("cdrw", Product.class);
        System.out.println(aaa);
        System.out.println(cdrw);
    }
}
```

만약 다음과 같이 실행하면 오류가 발생한다.

```java
public class Main {

    public static void main(String[] args) throws Exception {

        ApplicationContext context =
                new AnnotationConfigApplicationContext(ShopConfiguration.class);

        Product aaa = context.getBean("aaa1", Product.class);   // Error
        Product cdrw = context.getBean("cdrw", Product.class);
        System.out.println(aaa);
        System.out.println(cdrw);
    }
}
```