package org.zerock.ex2.repository;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.zerock.ex2.entity.Memo;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class MemoRepositoryTests {
  @Autowired
  MemoRepository memoRepository;

  //등록 테스트
  @Test
//  @Transactional
  public void testInsert() {
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

  //페이징 처리
  @Test
  public void testPageDefault() {
    Pageable pageable = PageRequest.of(0, 10);
    Page<Memo> result = memoRepository.findAll(pageable);
    System.out.println(result);
    System.out.println("--------------------------");
    System.out.println("Total Pages: " + result.getTotalPages());
    System.out.println("Total Count: " + result.getTotalElements());
    System.out.println("Page Number: " + result.getNumber());
    System.out.println("Page Size: " + result.getSize());
    System.out.println("has next page? : " + result.hasNext());
    System.out.println("first page?: " + result.isFirst());
  }

  //조건 추가
  @Test
  public void testSort() {
    Sort sort1 = Sort.by("mno").descending();
    Pageable pageable = PageRequest.of(0, 10, sort1);
    Page<Memo> result = memoRepository.findAll(pageable);

    result.get().forEach(memo -> {System.out.println(memo);});
  }

  //쿼리 추가
  @Test
  public void testQueryMethods() {
    List<Memo> list = memoRepository.findByMnoBetweenOrderByMnoDesc(70L, 80L);
    for(Memo memo : list){
        System.out.println(memo);
    }
  }

  @Test
  public void testQueryMethodWithPageable() {
    Pageable pageable = PageRequest.of(0, 10, Sort.by("mno").descending());
    Page<Memo> result = memoRepository.findByMnoBetween(10L, 50L, pageable);

    result.get().forEach(memo -> System.out.println(memo));
  }

  @Test
  @Commit
  @Transactional
  public void testDeleteQueryMethod() {
    memoRepository.deleteMemoByMnoLessThan(10L);
  }

  @Test
  public void getListDesc(){
    List<Memo> memo = memoRepository.getListDesc();
    System.out.println(memo);
  }

  @Test
  @Commit
  @Transactional
  public void updateMemoText() {
    int i = memoRepository.updateMemoText(10L, "update by query");
  }
}
