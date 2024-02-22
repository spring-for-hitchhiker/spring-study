## [POJO]
Plain Old Java Object 의 약자로 특별한 제한이 없는 일반적인 객체로   
EJB 등에서 사용되는 Java Bean이 아닌 Getter와 Setter로 구성된 가장 순수한 형태의 기본 클래스를 POJO 라고 한다   

### [AtomicInteger]
**원자성을 보장하는 Interger**

멀티 스레드 환경에서 동기화 문제를 별도의 synchronized 키워드 없이 해결하기 위해서 고안된 방법   
여러 스레드 간에 안전하게 공유할 수 있는 원자적으로 연산되는 정수 값을 제공한다   

기본적으로 `CAS 알고리즘` 으로 작동을하여 멀티 스레드 환경에서 안전하게 정수 값을 증감할 수 있다   

## [IoC 컨테이너]


[spring doc](https://docs.spring.io/spring-framework/docs/3.2.x/spring-framework-reference/html/beans.html)


기본적으로 스프링은 @Configuration, @Bean, @componet, @Repository, @Service, @Controller 가 달린 클래스를 모두 감지한다   
이때, 하나 이상의 포함 / 제외 필터를 적용해서 스캐닝과정을 커스터마이징 할 수 있다   
**!! 모든 패키지를 일일이 스캐닝하면 시동 과정이 쓸데없이 느려질 수 있다**

스프링이 지원하는 필터 표현식은 4종류이다    
annotation, assignable 은 각각 필터 대상 애너테이션 타입 및 클래스/인터페이스를 지정하며   
regex, aspectj 는 각자 정규표현식과 AspectJ 포인트컷 표현식으로 클래스를 매치하는 용도로 쓰인다   
use-defalult-filters 속성으로 기본 필터를 해제할 수도 있다   

