package com.ohgiraffers.section06.compositekey.subsection01.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity(name="member_section06_sub01")
@Table(name="tbl_member_section06_sub01")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Member {
    // memberNo, memberId primary key 로 지정 -> MemberPk 값으로 지정

   @EmbeddedId
    private MemberPk memberPk;

   @Column(name = "phone")
    private String phone;

   @Column(name = "address")
    private String address;
}
