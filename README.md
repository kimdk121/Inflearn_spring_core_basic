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

@ComponentScan
Config class에 붙여 넣으면 Component를 Bean으로 등록한다.
그리고 이미 @SpringBootApplication에 @ComponentScan 설정이 되어있다

어노테이션에는 상속관계라는것이 없어서 어노테이션이 특정 어노테이션을 들고 있는 것을 인식할수 있는것은 자바기능이 아니고 스프링의 기능이다.
<br>

스프링 구조 어노테이션
1. @Controller : 스프링 MVC 컨트롤러로 인식
2. @Repository : 스프링 데이터 접근 계증으로 인식하고, 데이터 계층의 예외를 스프링 예외로 변환해줌.
3. @Configuration : 스프링 설정 정보로 인식하고, 스프링 빈이 싱글톤을 유지하도록 추가 처리를 한다.
4. @Service : 특별한 처리를 하지 않고, 개발자들이 핵심 비즈니스 로직이 여기 있겠구나하고 인식하는데 도움을 준다.

의존성 주입
1. 생성자 주입
@Autowired
public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
System.out.println("1. OrderServiceImpl.OrderServiceImpl");
this.memberRepository = memberRepository;
this.discountPolicy = discountPolicy;
}
2. 수정자 주입
@Autowired
public void setDiscountPolicy(DiscountPolicy discountPolicy) {
    System.out.println("discountPolicy = " + discountPolicy);
    this.discountPolicy = discountPolicy;
}
3. 필드주입
@Autowired
private MemberRepository memberRepository;
4. 일반메서드 주입
@Autowired
public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
    this.memberRepository = memberRepository;
    this.discountPolicy = discountPolicy; 
}

생성자가 1개일 때에는 @Autowired 생략해도 빈으로 등록된다. (수정자 주입이 있어도)
생성자 주입 -> 수정자 주입
필드주입은 테스트가 불편하여 권장하지 않음

@Nullable 이나 Optional<> 은 스프링 전반에서 사용가능