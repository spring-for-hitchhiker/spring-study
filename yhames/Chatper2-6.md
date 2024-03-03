# Chapter 2 : 스프링 코어

- [Chapter 2 : 스프링 코어](#chapter-2--스프링-코어)
  - [레시피 2-6 : 외부 리소스(텍스트, XML, 프로퍼티, 이미지 파일)의 데이터 사용하기](#레시피-2-6--외부-리소스텍스트-xml-프로퍼티-이미지-파일의-데이터-사용하기)
    - [properties 파일을 사용해서 POJO 초기값 설정](#properties-파일을-사용해서-pojo-초기값-설정)
    - [POJO에서 외부 리소스 파일을 사용](#pojo에서-외부-리소스-파일을-사용)
    - [@PostConstruct](#postconstruct)
    - [어플리케이션 종료시 할인율 출력](#어플리케이션-종료시-할인율-출력)

## 레시피 2-6 : 외부 리소스(텍스트, XML, 프로퍼티, 이미지 파일)의 데이터 사용하기

### properties 파일을 사용해서 POJO 초기값 설정

`@PropertySource` 어노테이션을 사용하면 `.properties` 파일의 `key-value` 데이터를 가져올 수 있습니다.

`@PropertySource`를 사용하기 위해서는 `PropertySourcePlaceholderConfigurer` 인스턴스를 초기화해야합니다.

```java
@PropertySource("classpath:discounts.properties")
public class ShopConfiguration {
  @Bean
  public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
      return new PropertySourcesPlaceholderConfigurer();
  }
}
```

`@PropertySource`의 옵션으로 `classpath`와 함께 `properties` 파일 이름을 명시하면 자바 클래스패스에서 해당 `properties` 파일을 찾습니다.

> 클래스패스(`classpath`)란 JVM이 클래스 파일을 탐색하는 경로를 의미합니다.  
> 파일 시스템 리소스는 `file` 접두어를 사용합니다.

다음으로 `@Value`를 사용하면 가져온 프로퍼티값을 자바 변수에 담을 수 있습니다.

```java
@PropertySource("classpath:discounts.properties")
public class ShopConfiguration {
  @Value("${specialcustomer.discount:0}")
  private double specialCustomerDiscountField;

  @Bean
  public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
      return new PropertySourcesPlaceholderConfigurer();
  }
}
```

`@Value`의 옵션은 `placeholder` 표현식을 사용합니다.

쌍따옴표(`""`) 안에 `${key:default_value}` 형식으로 구문을 선언하면 `properties` 파일에서 해당 키값을 찾습니다. 만약 해당하는 키를 찾지 못하면 기본값(`default_value`)을 할당합니다.

### POJO에서 외부 리소스 파일을 사용

`Resource` 인터페이스는 로우 레벨 자원들에 접근을 추상화하기 위한 인터페이스입니다. `Resource`를 사용하여 여러가지 종류의 자원들을 동일한 방식으로 제어할 수 있습니다.  
> [Spring Docs - Interface Resource](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/core/io/Resource.html)

`recipe_2_6_ii`는 텍스트 파일로 저장된 `배너 문자열`을 가져와서 출력하는 예제입니다.

```java
@PropertySource("classpath:discounts.properties")
public class ShopConfiguration {
  @Value("${specialcustomer.discount:0}")
  private double specialCustomerDiscountField;

  @Value("classpath:banner.txt")
  private Resource banner;

  @Bean
  public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
      return new PropertySourcesPlaceholderConfigurer();
  }
}
```

`placeholder` 구문을 사용한 예제와는 다르게 `@Value`의 옵션으로 `classpath`를 지정하여 해당 파일을 `Resource`형으로 가져오고 있습니다.

특정 패키지를 명시하기 위해서 `절대경로`를 사용할 수도 있고, 
파일 시스템을 사용하기 위해서 `file` 접두어를 사용할 수도 있습니다.
또한 클래스패스나 파일 시스템 뿐만 아니라 `URL`로 위치를 특정할 수도 있습니다. 


### @PostConstruct

예제에서는 배너를 출력하는 `showBanner()` 메서드에 `@PostConstruct` 어노테이션을 사용하여 **컨테이너가 구성되는 시점에** 해당 배너를 출력하고 있습니다.

`@PostConstruct`는 **의존관계 주입이 완료된 후 초기화를 수행**하는 메서드 입니다. 즉, 빈 인스턴스가 생성된 후에 자동으로 실행되며, 빈 라이프사이클 동안 오직 한 번만 수행됩니다.

### 어플리케이션 종료시 할인율 출력

배너를 출력하는데 `@PostConstruct`를 사용했던 것과 달리,
어플리케이션이 종료될 때 `discount.properties` 파일의 내용들을 출력할 때에는 빈을 가져와서 직접 출력하고 있습니다.

```java
public class Main {

    public static void main(String[] args) throws Exception {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(ShopConfiguration.class);

        Resource resource = new ClassPathResource("discounts.properties");
        Properties props = PropertiesLoaderUtils.loadProperties(resource);
        System.out.println("And don't forget our discounts!");
        System.out.println(props);
    }
}
```

`ClassPathResource` 클래스는 자바 클래스패스에 있는 리소스를 위한 `Resource` 인터페이스를 구현한 클래스입니다. 외부 파일 시스템에 있는 리소스는 `FileSystemResource`를 사용하며, URL로 외부 리소스에 액세스할 때는 UrlResource를 사용합니다.

> [Spring Docs - ClassPathResource](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/core/io/ClassPathResource.html)  
> [Spring Docs - FileSystemResource](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/core/io/FileSystemResource.html)  
> [Spring Docs - UrlResource](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/core/io/UrlResource.html)

`Properties`는 `Hashtable`(`HashMap`의 구버전)을 상속받아 구현한 클래스입니다. `properties`와 같은 설정 정보들을 불러올 때 사용합니다.

> [Java Docs - Properties](https://docs.oracle.com/javase/8/docs/api/java/util/Properties.html)
