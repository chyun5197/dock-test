package hyun.book.Study;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class JUnitQuiz {
    @Test
    public void junitTest(){
        String name1 = "홍길동";
        String name2 = "홍길동";
        String name3 = "홍길은";

        //모든 변수가 null이 아닌지 확인
        assertThat(name1).isNotNull();
        assertThat(name2).isNotNull();
        assertThat(name3).isNotNull();

        //n1 n2 같은지
        assertThat(name1).isEqualTo(name2);

        //n1 n3 다른지
        assertThat(name1).isNotEqualTo(name3);
    }

    @Test
    public void junitTest2(){
        int number1 = 15;
        int number2 = 0;
        int number3 = -5;

        //n1 양수인지
        assertThat(number1).isPositive();

        //n2 0인지
        assertThat(number2).isZero();

        //n3 음수인지
        assertThat(number3).isNegative();

        //n1이 n2보다 큰지
        assertThat(number1).isGreaterThan(number2);

        //n3가 n2보다 작은지
        assertThat(number3).isLessThan(number2);
    }
}
