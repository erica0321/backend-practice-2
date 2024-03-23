package org.zerock.ex2.repository;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.ex2.entity.Memo;

import java.util.stream.IntStream;

@SpringBootTest
public class MemoRepositoryTests {
  @Autowired
  MemoRepository memoRepository;

  @Test
  public void testDelete() {
    Long mno = 100L;
    memoRepository.deleteById(mno);
  }
}
