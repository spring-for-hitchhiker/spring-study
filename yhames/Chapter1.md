# Chapter 1 : 스프링 개발 툴

- [Chapter 1 : 스프링 개발 툴](#chapter-1--스프링-개발-툴)
  - [레시피 1-2 : 인텔리제이로 스프링 애플리케이션 빌드하기](#레시피-1-2--인텔리제이로-스프링-애플리케이션-빌드하기)
    - [build.gradle이 없는 문제](#buildgradle이-없는-문제)
    - [Spring 저장소 권한 정책 변경으로 인한 build 실패](#spring-저장소-권한-정책-변경으로-인한-build-실패)
    - [.gitignore 추가](#gitignore-추가)

## 레시피 1-2 : 인텔리제이로 스프링 애플리케이션 빌드하기

### build.gradle이 없는 문제

책에서 언급한대로 공식 깃허브(https://github.com/Apress/spring-5-recipes)에서 코드를 가져왔는데
`build.gradle`이 없는 문제가 있었다.

깃허브에 다시 가보니 처음 코드를 올리고 1년 뒤에
업데이트된 코드를 커밋이 아닌 `zip`파일로 올려놓았다.

해당 `zip`파일을 압축해제하여 찾아보니 `ch01/springintro`에 `build.gradle`이 포함되어 있었다.

### Spring 저장소 권한 정책 변경으로 인한 build 실패

인텔리제이에서 해당 `build.gradle`을 사용했지만 다음과 같은 오류가 발생하면서 빌드에 실패했다.

```shell
Could not HEAD 'https://repo.spring.io/libs-milestone/org/springframework/spring-core/5.0.0.RC2/spring-core-5.0.0.RC2.pom'. Received status code 401 from server: 
```

`repositories`에서 접근 권한 문제(`401`, `Unauthorized`)가 발생했다고 생각되어 찾아보니 접근할 수 있는 URL(https://repo.spring.io/ui/native/milestone)이 존재해서 주소를 변경해 보았다.

```groovy
repositories {
//    maven { url 'https://repo.spring.io/libs-milestone' }
    maven { url 'https://repo.spring.io/ui/native/milestone' }
    maven { url 'https://repo.spring.io/release' }
}
```

하지만 여전히 다음과 같은 오류가 발생했다.

```shell
Could not HEAD 'https://repo.spring.io/ui/native/milestone/org/springframework/spring-core/5.0.0.RC2/spring-core-5.0.0.RC2.pom'. Received status code 403 from server: Forbidden
```

웹에서 접속하면 문제가 발생하지 않는데, `build`할 때만 Forbidden되는 것이 이상해서 공식 사이트(https://spring.io/blog/2022/12/14/notice-of-permissions-changes-to-repo-spring-io-january-2023)를 찾아보니 권한 문제로 인해 https://start.spring.io 에서 사용하는 것 외에는 접근 권한을 막아버린 것으로 보였다.

다행히 친절하게 `Maven Central`을 사용하라는 안내가 나와있어서 `Maven Central` 저장소를 사용하는 방식으로 수정했다.

> `5.0.0.RC2` 버전이 지원되지 않아서 스프링 5에서 가장 최신 버전(현재 기준 `5.3.2`)으로 변경했다.

```groovy
// ...

repositories {
    mavenCentral()
}

dependencies {
    implementation group: 'org.springframework', name: 'spring-core', version: '5.3.32'
    implementation group: 'org.springframework', name: 'spring-expression', version: '5.3.32'
    implementation group: 'org.springframework', name: 'spring-beans', version: '5.3.32'
    implementation group: 'org.springframework', name: 'spring-context', version: '5.3.32'
}
```



### .gitignore 추가

빌드 된 파일들까지 깃허브에 올라가는 상황을 방지하기 위해 `.gitignore`을 상위 폴더에 추가했다.

`.gitignore`을 위헤 `Intellij`의 `.gitignore` 플러그인을 사용했다.
