# Chapter 2 : 스프링 코어

- [Chapter 2 : 스프링 코어](#chapter-2--스프링-코어)
  - [레시피 2-7 : 프로퍼티 파일에서 로케일마다 다른 다국어 메시지를 해석하기](#레시피-2-7--프로퍼티-파일에서-로케일마다-다른-다국어-메시지를-해석하기)
    - [MessageSource](#messagesource)
      - [String code](#string-code)
      - [String defaultMessage](#string-defaultmessage)
      - [Object\[\] args](#object-args)
      - [Locale locale](#locale-locale)
    - [다른 POJO에서 MessageResource 사용](#다른-pojo에서-messageresource-사용)

## 레시피 2-7 : 프로퍼티 파일에서 로케일마다 다른 다국어 메시지를 해석하기

스프링에서 국제화를 위한 메시지 리소스 번들을 추상화하기 위한 인터페이스로 `MessageResource`를 제공합니다.

`MessageResource`는 `Locale` 별로 분리된 리소스 번들 메시지를 해석하고 필요한 `i18n`(internationalization의 줄입말, 국제화) 데이터를 가져올 수 있습니다.

`MessageResource`를 사용하기 위해서는 해당 인터페이스의 구현체를 빈 인스턴스로 등록해야합니다. 이 때 빈 이름은 **⭐️반드시⭐️ `messageSource`라고 명명**해야합니다.

`MessageResource`의 구현체로는 `ResourceBundleMessageSource`와 `ReloadableResourceBundleMessageSource`가 있습니다.

`ReloadableResourceBundleMessageSource`는 `ResourceBundleMessageSource`와는 다르게 **어플리케이션 실행 중에 매시지 리소스 변경을 자동으로 감지**하여 `Reload`를 한다는 차이가 있습니다.

`ReloadableResourceBundleMessageSource`는 `setBasenames()` 메서드를 사용할 때  `classpath` 혹은 `file` 같은 **자원의 경로를 명시**해야합니다.

또한 `setCacheSeconds()` 메서드를 사용하여 메시지 파일 정보를 캐싱 주기를 초단위로 설정할 수 있습니다.

> `스프링 부트`를 사용하면 `MessageResource`를 자동으로 빈에 등록합니다.

> [Spring Docs - MessageSource](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/context/MessageSource.html)  
> [Spring Docs - ReloadableResourceBundleMessageSource](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/context/support/ReloadableResourceBundleMessageSource.html)  
> [Spring Docs - ResourceBundleMessageSource](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/context/support/ResourceBundleMessageSource.html)  

`MessageSource`의 `getMessage()` 메서드를 사용하면 빈으로 등록한 메시지를 가져올 수 있습니다.

`AppllicationContext`는 `MessageSource`를 상속받고 있기 때문에 컨테이너에서 바로 `getMessage()` 메서드를 호출할 수 있습니다.

### MessageSource

다음은 `MessageSource` 인터페이스에서 제공하는 `getMessage()` 메서드 입니다.

```java
public interface MessageSource {
	@Nullable
	String getMessage(String code, @Nullable Object[] args, @Nullable String defaultMessage, Locale locale);

	String getMessage(String code, @Nullable Object[] args, Locale locale) throws NoSuchMessageException;

	String getMessage(MessageSourceResolvable resolvable, Locale locale) throws NoSuchMessageException;
}
```

각 매개변수의 의미와 역할을 알아보겠습니다.

#### String code

`code`에 해당하는 메시지가 없는 경우 `NoSuchMessageException`가 발생합니다. 

#### String defaultMessage

`defaultMessage`를 매개변수로 전달하면 `code`에 해당하는 메시지가 없는 경우 `defaultMessage`가 반환됩니다.
 
#### Object[] args

`placeholder`를 사용해서 매개변수를 전달할 수 있습니다. 이때 `args`는 `Object[]`을 사용해서 전달하면 됩니다.

예제의 경우 `placeholder`를 사용해서 `alert.inventory.checkout` 메세지를 정의하고, `new Object[]{"[DVD-RW 3.0]", new Date()}`를 매개변수로 전달하고 있습니다.

```properties
alert.inventory.checkout=A shopping cart with {0} has been checked out at {1}.
```

```java
public class Main {
    public static void main(String[] args) throws Exception {
        // ...
        String alert_inventory = context.getMessage("alert.inventory.checkout", new Object[]{"[DVD-RW 3.0]", new Date()}, Locale.US);
        // ...
    }
    // ...
}
```

#### Locale locale

`MessageSource`는 `Locale`을 사용해서 각 언어에 맞는 파일을 자동으로 가져옵니다.

이 때 파일이름은 언더스코어(`_`)를 사용하여 `snake_case`로 작성해야합니다.

예제와 같이 `Locale.US`로 설정한 경우 파일 우선순위는 다음과 같습니다.

1. `messages_en_US.properties`
2. `messages_en.properties`
3. `messages.properties`

만약 `Locale.KOREA`로 설정한다면 다음과 같은 우선순위를 갖습니다.

1. `messages_ko_KR.properties`
2. `messages_ko.properties`
3. `messages.properties`

> `messages.properties`에는 기본적으로 사용하는 언어를 등록할 수 있습니다.

> [Java Docs - Locale](https://docs.oracle.com/javase/8/docs/api/java/util/Locale.html)  


### 다른 POJO에서 MessageResource 사용

컨테이너(`ApplicationContext`)를 직접 사용할 수 없는 다른 빈 인스턴스에서 `MessageResource`를 사용하려면 `MessageResource` 인스턴스를 주입 받아야합니다.

```java
@Component
@RequiredArgsConstructor
public class ExampleClass {
  private final MessageSource messageSource;  // 주입
}
```