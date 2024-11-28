package hyun.book.controller;

import hyun.book.domain.Member;
import hyun.book.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
@AutoConfigureMockMvc
MockMvc는 애플리케이션을 서버에 배포하지 않고도 테스트용 MVC 환경을 만들어
요청 및 전송, 응답 기능을 제공하는 유틸리티 클래스.
컨트롤러를 테스트할때 사용되는 클래스
 */
@SpringBootTest
@AutoConfigureMockMvc
class TestControllerTest {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    public void mockMvcSetUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
    }

    @AfterEach
    public void cleanUp() {
        memberRepository.deleteAll(); //member 테이블의 데이터들을 모두 삭제
    }

    ////////////////////
    /*
    given   테스트를 준비
    when    테스트를 실제로 진행
    then    테스트 결과를 검증
     */

    /*
    테스트 패턴
    Given   멤버를 저장한다
    When    멤버 리스트를 조회하는 API를 호출한다
    Then    응답 코드가 200 OK이고, 반환받은 값 중에 0번째 요소의 id와 name이 저장된 값과 같은지 확인한다.
    */
    @DisplayName("getAllMembers: 아티클 조회에 성공한다.")
    @Test
    public void getAllMembers() throws Exception {
        //given
        final String url = "/test";
        Member savedMember = memberRepository.save(new Member(1L, "홍길동"));

        //when
        final ResultActions result = mockMvc.perform(get(url) // perform()은 요청을 전송하는 역할
                .accept(MediaType.APPLICATION_JSON)); //accept()는 요청을 보낼 때 무슨 타입으로 응답을 받을지 결정

        //then
        result
                .andExpect(status().isOk()) // andExpect()는 응답을 검증. TestController에서 만든 API는 OK(200)을 반환
                .andExpect(jsonPath("$[0].id").value(savedMember.getId()))
                .andExpect(jsonPath("$[0].name").value(savedMember.getName()));
    }
}