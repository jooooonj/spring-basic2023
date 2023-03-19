package com.ll.basic1.domain.member.repository;
import com.ll.basic1.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findById(long id);
    Optional<Member> findByUsername(String username);

}
