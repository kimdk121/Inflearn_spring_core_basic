# Inflearn_spring_core_basic
ORM(Object Relational Mapping) - 객체와 관계형 데이터베이스의 데이터를 자동으로 매핑
ORM 시장은 JPA가 대다수고 그 구현체는 하이버네이트가 80%정도 <br>
다형성 - 하나의 객체가 여러 가지 타입을 가질 수 있는 것

객체지향 프로그래밍 <br>
여러개의 독립된 객체들의 모임을 가지고 객체들 끼리의 메세지를 주고받아 데이터를 처리하도록 한다.

좋은 객체 지향 설계의 5가지 원칙(SOLID)
1. SRP 단일 책임 원칙
  한 클래스는 하나의 책임만 가져야 한다.
2. OCP 개방-폐쇄 원칙
  소프트웨어 요소는 확장에는 열려 있으나 변경에는 닫혀 있어야 한다.
3. LSP 리스코프 치환 원칙
  프로그램의 객체는 프로그램의 정확성을 깨뜨리지 않으면서 하위 타입의 인스턴스로 바꿀 수 있어야 한다
4. ISP 인터페이스 분리 원칙
  특정 클라이언트를 위한 인터페이스 여러 개가 범용 인터페이스 하나보다 낫다
5. DIP 의존관계 역전 원칙
  구현 클래스보다 역할인 인터페이스에 의존해야 한다

다형성만으로는 OCP, DIP를 지키기 힘든데 스프링은 가능하게 만듬

AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class); <br>
bean 객체의 확인 및 관리

BeanFactory
- 스프링 컨테이너의 최상위 인터페이스
- 스프링 빈을 관리하고 조회

ApplicationContext
- BeanFactory 기능을 모두 상속받아서 제공하고 편리한 부가기능을 포함한다.

1. AnnotationConfigApplicationContext
   - AnnotatedBeanDefinitionReader가 AppConfig.class 설정 정보를 읽어옴
2. GenericXmlApplicationContext
   - XmlBeanDefinitionReader가 appConfig.xml 설정 정보를 읽어옴
3. XxxApplicationContext
   - XxxBeanDefinitionReader가 appConfig.xxx 의 설정 정보를 읽어옴
  
Singleton 패턴 <br>
AppConfig를 new로 생성하게되면 트래픽에따라 객체를 계속 생성하고 소멸하게 된다.
싱글톤 패턴을 사용하면 객체를 처음에 한번 생성하고 공유하도록 하게한다.
