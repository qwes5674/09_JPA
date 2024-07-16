package com.ohgiraffers.section06.compositekey.subsection01.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable // 임베드 될 수 있는 복합 키타입을 지정할 때 사용하는 어노테이션
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MemberPk implements Serializable { // 복합키 설정할때 implements 사용 , Serializable -> 직렬화

    @Column(name = "member_no")
    private int memberNo;

    @Column(name = "member_id")
    private String memberId;

    // equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false; // getClass -> 타입을 받아옴
        MemberPk memberPk = (MemberPk) obj;
        return memberNo == memberPk.memberNo
                && Objects.equals(memberId, memberPk.memberId);
    }

    // hashCode
    @Override
    public int hashCode() {
       return Objects.hash(memberNo,memberId); // memberNo, memberId를 가진 hashCode 생성
    }

}
