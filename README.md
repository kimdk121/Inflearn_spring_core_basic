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

@Autowired 매칭
1. 타입으로 매칭
2. 타입 매칭의 결과가 2개 이상일 때는 필드명으로 빈이름 매칭

@Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy

@Primary
의존관계 설정시 빈설정의 우선적으로 빈 매칭

@Qualifier 와 @Primary가 같이 있다면 @Qualifier가 우선권을 가진다

// Map을 통해 다형성의 빈을 저장하여 동적으로 사용할 수 있다.
private final Map<String, DiscountPolicy> policyMap;

1. 업무 로직 빈: 웹을 지원하는 컨트롤러, 서비스, 데이터 계층 로직을 처리하는 리포지토리 등이 해당되며 숫자가 매우 많고 유사한 패턴이 있기에 자동 기능을 사용하는것이 유리하다. 
2. 기술 지원 빈: 기술적인 문제나 공통 관심사(AOP)를 처리할 때 주로 사용하며 수가 매우 적고 어플리케이션 전역에 영향을 미치고 문제 파악에 어려운 점이 있어 수동 빈으로 등록하여 관리하는 것이 유리하다. 

빈 등록은 자동기능을 사용하되 직접 등록하는 기술 지원 객체는 수동으로 등록하고
다형성을 활용하는 비즈니스 로직은 수동 등록을 하여 명확하게 하는것이 좋다.

스프링 빈의 이벤트 라이프사이클
스프링 컨테이너 생성 -> 스프링 빈 생성 -> 의존관계 주입 -> 초기화 콜백 -> 사용 -> 소멸전 콜백 -> 스프링 종료

객체의 생성과 초기화를 분리하자.
생성자는 파라미터를 받고 메모리를 할당해서 객체를 생성하는 책임을 가진다.
반면에 초기화는 이렇게 생성된 값들을 활용해서 외부 커넥션을 연결하는 등 무거운 동작을 수행한다.
따라서 생성자 안에서 무거운 초기화 작업을 함께 하는 것 보다는 객체를 생성하는 부분과 초기화하는 부분을 명확하게 나누는 것이 유지보수 관점에서 좋다.

인터페이스 InitializingBean, DisposableBean
코드 자체가 스프링전용 인터페이스에 의존하는게 문제..
이젠 사용안함

빈 등록 초기화, 소멸 메소드
@Bean() 안에 적용하기 때문에 스프링 코드에 의존하지 않는다.
destroyMethod 는 지정하지 않아도 알아서 (close, shutdown) 찾아서 종료해준다.
외부 라이브러리에 적용 가능.

어노테이션 방식 (추천)
@PostConstruct, @PreDestroy
스프링 기술이 아니라 자바 표준이라 다른 컨테이너에서도 동작한다.
외부 라이브러리에는 적용 불가.

빈 스코프
싱글톤 : 기본 스코프, 스프링 컨테이너의 시작과 종료까지 유지되는 가장 넓은 범위의 스코프
프로토타입 : 프로토타입 빈의 생성과 의존관계 주입까지만 관여하고 더이상 관리하지 않음, 그러므로 @PreDestroy 같은 종료 메서드가 호출되지 않는다. 클라이언트가 직접 종료해줘야 한다. 
웹관련
1. request : 웹 요청이 들어오고 나갈때 까지 유지되는 스코프
2. session : 웹 세션이 생성되고 종료될 때 까지 유지되는 스코프
3. application : 서블릿 컨텍스트와 같은 범위로 유지되는 스코프

의존관계를 외부에서 주입받는건 Dependency Injection
직접 필요한 의존관계를 찾는건 Dependency Lookup

싱글톤 빈과 함께 프로토타입 스코프를 사용 시
1.
@Autowired
ObjectProvider<PrototypeBean> prototypeBeanProvider;
PrototypeBean prototypeBean = prototypeBeanProvider.getObject(); 

2.
dependency에 implementation 'jakarta.inject:jakarta.inject-api:2.0.1' 추가
@Autowired
private Provider<PrototypeBean> prototypeBeanProvider;
PrototypeBean prototypeBean = prototypeBeanProvider.get();

의존관계 추가가 필요없는 1번이 편리하지만 코드를 스프링이 아닌 다른 컨테이너에서도 사용할 수 있어야 한다면
2번의 자바표준 Provider를 사용한다.

