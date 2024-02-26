# Chapter 2 : 스프링 코어

- [Chapter 2 : 스프링 코어](#chapter-2--스프링-코어)
  - [레시피 2-3 : POJO 레퍼런스와 자동 연결을 이용해 다른 POJO와 상호 작용하기](#레시피-2-3--pojo-레퍼런스와-자동-연결을-이용해-다른-pojo와-상호-작용하기)
    - [자바 구성 클래스에서 POJO 참조하기](#자바-구성-클래스에서-pojo-참조하기)
    - [POJO 필드에 @Autowired를 붙여 자동 연결하기](#pojo-필드에-autowired를-붙여-자동-연결하기)
      - [Setter 주입](#setter-주입)
      - [필드 주입](#필드-주입)
      - [생성자 주입](#생성자-주입)
    - [모호한 자동 연결 명시하기](#모호한-자동-연결-명시하기)
      - [@Primary](#primary)
      - [@Qualifier](#qualifier)
      - [@Import](#import)

## 레시피 2-3 : POJO 레퍼런스와 자동 연결을 이용해 다른 POJO와 상호 작용하기

자바 구성 클래스에서 정의된 POJO(빈)들이 서로 참조하기 위해서는 다음과 같은 방식으로 설정해야한다.

1. 구성 클래스에서 의존 관계 주입
2. `@Autowired`를 사용해서 자동으로 의존관계 주입

### 자바 구성 클래스에서 POJO 참조하기

구성 클래스에서 POJO 간의 의존성을 주입하기 위해서는 일반적으로 자바 메서드를 호출하듯이 생성하면 된다.

```java
@Configuration
public class SequenceConfiguration {

    @Bean
    public DatePrefixGenerator datePrefixGenerator() {
        DatePrefixGenerator dpg = new DatePrefixGenerator();
        dpg.setPattern("yyyyMMdd");
        return dpg;
    }

    @Bean
    public SequenceGenerator sequenceGenerator() {
        SequenceGenerator sequence = new SequenceGenerator(); // 인스턴스 생성 -> POJO(빈) 객체를 가져온다.
        sequence.setInitial(100000);
        sequence.setSuffix("A");
        sequence.setPrefixGenerator(datePrefixGenerator()); // 의존관계 주입  -> POJO(빈) 객체를 주입한다.
        return sequence;
    }
}
```

### POJO 필드에 @Autowired를 붙여 자동 연결하기

`@Autowired`를 사용하면 `@Component`를 통해 등록된 빈 객체를 자동으로 찾아서 연결한다.

`@Autowired`는 배열이나 Type-safe 컬렉션(List, Map 등)에도 적용된다.

`@Autowired`를 통한 의존관계 주입은 다음과 같이 3가지 방식이 있다.
* Setter 주입
* 필드 주입
* 생성자 주입

#### Setter 주입

`Setter`를 이용한 주입 방식은 메서드의 이름과 개수에 상관 없이 적용이 가능하다.

`Setter` 주입 방식은 주입받는 객체가 **변경**되는 경우에 사용할 수 있다.

```java
public class SequenceGenerator {
    // ...
    @Autowired
    public void myOwnCustomInjectionName(PrefixGenerator prefixGenerator) {
        this.prefixGenerator = prefixGenerator;
    }
    // ...
}
```

#### 필드 주입

필드 주입 방식은 스프링 컨테이너에서 타입을 찾아 자동으로 빈 객체가 주입된다.

하지만 필드 주입 방식은 `@Autowired` 어노테이션을 사용해야하기 때문에 DI 프레임워크에 대한 의존도가 높고 (생성자 주입 방식에서는 생략이 가능하다)

`빈` 생성과 조립의 시점이 분리되어 있기 때문에 `순환 참조 오류`를 검출하지 못한다는 단점이 있다.

일부 테스트를 위한 코드가 아니라면, 대부분 생성자 주입 방식을 권장한다.

```java
public class SequenceGenerator {
    // ...
    @Autowired
    private PrefixGenerator[] prefixGenerators;
    // ...
}
```

#### 생성자 주입

생성자 주입 방식은 생성자 매개변수가 몇 개든 각 매개변수와 호환되는 빈 객체를 주입한다.

```java
@Service
public class SequenceService {
    private final SequenceDao sequenceDao;

    @Autowired
    public SequenceService(SequenceDao sequenceDao) {
      this.sequenceDao = sequenceDao;
    }
}
```

생성자가 하나뿐이라면 `@Autowired`를 생략할 수 있다.

```java
@Service
public class SequenceService {
    private final SequenceDao sequenceDao;

    public SequenceService(SequenceDao sequenceDao) {
      this.sequenceDao = sequenceDao;
    }
}
```

생성자 주입 방식 다음과 같은 장점이 있다.

1. DI 프레임워크에 대한 의존도가 가장 낮고, 
2. 객체를 불변하게 선언할 수 있다.
3. 또한 Lombok을 사용했을 때 더 간편하게 사용할 수 있고
4. 순환 참조 에러를 방지할 수 있다는 장점이 있다.


### 모호한 자동 연결 명시하기

#### @Primary

같은 타입의 빈이 여러개일 때 `@Primary` 어노테이션을 사용하면 해당 빈이 우선적으로 주입된다.

#### @Qualifier

위와 같은 상황에서 `@Qualifier`를 사용하여 `빈 이름`으로 명시할 수 있다.

#### @Import

`@Import`를 사용하면 특정 설정 클래스에서 정의한 빈 객체를 현재 구성 클래스로 가져올 수 있다.

예제 `recipe_2_3_vii`를 보면 `SequenceConfiguration`에서 `@Value`와 `SpEL`을 사용하여 `PrefixConfiguration`에 정의된 `datePrefixGenerator` 객체를 가져오는 것을 볼 수 있다.