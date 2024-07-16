package com.ohgiraffers.section03.primarykey.subsection02.table;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.List;

public class SequenceTableMappingTests {
    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;

    @BeforeAll
    public static void initFactor() {
        entityManagerFactory = Persistence.createEntityManagerFactory("jpatest");
    }

    @BeforeEach
    public void initManager() {
        entityManager = entityManagerFactory.createEntityManager();
    }

    @AfterAll
    public static void closeFactory() {
        entityManagerFactory.close();
    }

    @AfterEach
    public void closeManager() {
        entityManager.close();
    }

    @DisplayName("식별자 매핑 테스트")
    @Test
    public void identitySequenceMappingTable(){

        //given
        com.ohgiraffers.section03.primarykey.subsection02.table.Member member1 = new com.ohgiraffers.section03.primarykey.subsection02.table.Member();
        member1.setMemberId("user01");
        member1.setMemberPwd("pass01");
        member1.setNickname("홍길동");
        member1.setPhone("010-1234-5678");
        member1.setAddress("서울시 종로구");
        member1.setEnrollDate(LocalDate.now());
        member1.setMemberRole("ROLE_MEMBER");
        member1.setStatus("Y");

        com.ohgiraffers.section03.primarykey.subsection02.table.Member member2 = new Member();
        member2.setMemberId("user01");
        member2.setMemberPwd("pass01");
        member2.setNickname("홍길동");
        member2.setPhone("010-1234-5678");
        member2.setAddress("서울시 종로구");
        member2.setEnrollDate(LocalDate.now());
        member2.setMemberRole("ROLE_MEMBER");
        member2.setStatus("Y");

        //when
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try{
            entityManager.persist(member1);
            entityManager.persist(member2);
            transaction.commit();
        } catch (Exception e){
            transaction.rollback();
            throw new RuntimeException(e);
        }

        //then
        String jpql = "select a.memberNo from member_section03_sub02 a";
        List<Integer> members = entityManager.createQuery(jpql,Integer.class).getResultList();
        members.forEach(System.out::println);
    }
}
