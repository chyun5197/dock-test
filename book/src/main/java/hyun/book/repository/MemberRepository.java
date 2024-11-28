package hyun.book.repository;

import hyun.book.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//member 테이블과 Member 클래스를 매핑 작업하는 인터페이스
//DB에서 데이터를 가져온다.
//member라는 이름의 테이블에 접근해서 Member 클래스에 매핑하는 구현체
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}
