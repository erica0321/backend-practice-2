package org.zerock.ex2.entity;

import jakarta.persistence.*;
import lombok.ToString;

@Entity
@Table(name= "tb1_memo")
@ToString
public class Memo{
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long mno;
}
