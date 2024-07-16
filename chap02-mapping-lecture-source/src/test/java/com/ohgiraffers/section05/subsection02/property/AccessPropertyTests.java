package com.ohgiraffers.section05.subsection02.property;

import jakarta.persistence.*;
import org.junit.jupiter.api .*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccessPropertyTests {

        private static EntityManagerFactory entityManagerFactory;
        private static EntityManager entityManager;

        @BeforeAll
        public static void initFactor () {
        entityManagerFactory = Persistence.createEntityManagerFactory("jpatest");
    }

        @BeforeEach
        public void initManager () {
        entityManager = entityManagerFactory.createEntityManager();
    }

        @AfterAll
        public static void closeFactory () {
        entityManagerFactory.close();
    }

        @AfterEach
        public void closeManager () {
            entityManager.close();
        }

        @DisplayName("프로퍼티 접근 테스트")
        @Test
        public void AccessPropertyTest(){

            //given
            Member member = new Member();
            member.setMemberNo(1);
            member.setMemberId("user01");
            member.setMemberPwd("pass01");
            member.setNickname("홍길동");

            //when
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();

            try{
                entityManager.persist(member);
                transaction.commit();
            } catch (Exception e){
                transaction.rollback();
                throw new RuntimeException();
            }
            //then
            String jpql = "select a.nickname from member_section05_sub02 a where a.memberNo = 1";
            String registNickName = entityManager.createQuery(jpql, String.class).getSingleResult();
            assertEquals("홍길동님",registNickName);
            }
    }


