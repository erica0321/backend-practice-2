package org.zerock.ex2.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.ex2.entity.Memo;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface MemoRepository extends JpaRepository<Memo, Long> {

  List<Memo> findByMnoBetweenOrderByMnoDesc(Long form, Long to);

  Page<Memo> findByMnoBetween(Long form, Long to, Pageable pageable);

  void deleteMemoByMnoLessThan(Long num);

  @Transactional
  @Modifying
  @Query("select m from Memo m order by m.mno desc")
  List<Memo> getListDesc();

  @Transactional
  @Modifying
  @Query("update Memo m set m.memoText = :memoText where m.mno = :mno")
  int updateMemoText(@Param("mno") Long mno, @Param("memoText") String memoText);
}
