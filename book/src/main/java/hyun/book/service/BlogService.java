package hyun.book.service;

import hyun.book.domain.Article;
import hyun.book.dto.AddArticleRequest;
import hyun.book.dto.UpdateArticleRequest;
import hyun.book.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor // final이나 @NotNull 붙은 필드의 생성자 추가
@Service
public class BlogService {
    private final BlogRepository blogRepository;

    //블로그 글 추가 메서드
    public Article save(AddArticleRequest request){
        return blogRepository.save(request.toEntity());
    }

    //전체 조회
    public List<Article> findAll(){
        return blogRepository.findAll();
    }

    //조회 1개(id로)
    public Article findById(long id){
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " +  id));
    }

    //글 삭제(id로)
    public void delete(long id){
        blogRepository.deleteById(id);
    }

    //글 수정(id로)
    @Transactional // 트랜잭션은 DB의 데이터를 바꾸기 위한 작업 단위를 말한다. (수정, 삭제 충돌 안되게)
    public Article update(long id, UpdateArticleRequest request){
        Article article = blogRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("not found: " + id));

        article.update(request.getTitle(), request.getContent());

        return article;
    }
}
