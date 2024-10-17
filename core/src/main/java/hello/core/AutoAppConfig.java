package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

// 프로젝트의
@Configuration
@ComponentScan(
        // 탐색할 패키지의 시작위치 지정
        basePackages = "hello.core",
        // AutoAppConfig.class 의 package 가 시작위치가 됨 (hello.core)
        basePackageClasses = AutoAppConfig.class,
        // 제외 목록
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

/*
    @Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
*/
}
