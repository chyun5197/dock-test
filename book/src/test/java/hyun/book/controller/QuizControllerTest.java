package hyun.book.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class QuizControllerTest {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper; //객체와 JSON 간의 변환을 처리해준다

    @BeforeEach
    public void mockMvcSetUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
    }

    ////////////////Quiz/////////////////
    @DisplayName("quiz(): GET /quiz?code=1 이면 응답 코드는 201, " +
            "응답 본문은 Created!를 리턴한다.")
    @Test
    public void getQuiz1() throws Exception{
        //given
        final String url = "/quiz?code=1";

        //when
        final ResultActions result = mockMvc.perform(get(url) // perform()은 요청을 전송하는 역할
                .param("code", "1")
        );

        //then
        result
                .andExpect(status().isCreated())
                .andExpect(content().string("Created!"));
    }

    @DisplayName("quiz(): GET /quiz?code=2 이면 응답 코드는 400," +
            "응답 본문은 Bad Request!를 리턴한다.")
    @Test
    public void getQuiz2() throws Exception{
        //when
        final String url = "/quiz?code=2";

        //given
        final ResultActions result = mockMvc.perform(get(url)
                .param("code", "2")
        );

        //then
        result
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Bad Request!"));
    }

    @DisplayName("quiz(): POST /quiz에 요청 본문이 {\"value\":1이면" +
            "응답 코드는 403, 응답 본문은 Forbidden!을 리턴한다.")
    @Test
    public void postQuiz1() throws Exception{
        //given
        final String url = "/quiz";

        //when
        final ResultActions result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new Code(1)))
        );

        //then
        result
                .andExpect(status().isForbidden())
                .andExpect(content().string("Forbidden!"));
    }

    @DisplayName("quiz(): POST /quiz에 요청 본문이 {\"value\":13이면" +
            "응답 코드는 200, 응답 본문은 Ok!을 리턴한다.")
    @Test
    public void postQuiz13() throws Exception{
        //given
        final String url = "/quiz";

        //when
        final ResultActions result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new Code(13)))
        );

        //then
        result
                .andExpect(status().isOk())
                .andExpect(content().string("Ok!"));
    }


}