package org.zerock.ex2.repository;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.zerock.ex2.entity.Memo;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class MemoRepositoryTests {
  @Autowired
  MemoRepository memoRepository;

  //등록 테스트
  @Test
//  @Transactional
  public void testPageDefault() {
    IntStream.rangeClosed(1, 100).forEach(i -> {
      Memo memo =  Memo.builder().memoText("[ " + i + " ]").build();
      memoRepository.save(memo);
    });
  }

  //조회 테스트
  @Test
  public void testSelect() {
    Long mno = 100L;
    Optional<Memo> result = memoRepository.findById(mno);
    System.out.println("-----------------------------------");
    if(result.isPresent()) {
      Memo memo = result.get();
      System.out.println(memo);
    }
  }

  //수정 테스트
  @Test
  public void testUpdate() {
    Memo memo = Memo.builder().mno(100L).memoText("Update Text").build();
    System.out.println(memoRepository.save(memo));
  }

  //삭제 테스트
  @Test
  public void testDelete() {
    Long mno = 100L;
    memoRepository.deleteById(mno);
  }
}
