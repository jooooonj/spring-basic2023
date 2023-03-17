package com.ll.basic1.member.repository;
import com.ll.basic1.member.entity.Member;
import lombok.Getter;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
@Getter
public class MemberRepository {
    private List<Member> memberRepo;
    public MemberRepository() {
        this.memberRepo = new ArrayList<Member>();
    }

    public Member findByUsername(String username) {
        Member findMember = null;
        for(Member member : memberRepo){
            if (member.getUsername().equals(username)) {
                findMember = member;
                break;
            }
        }
        return findMember;
    }


    public Member findById(long loginedMemberId) {
        Member findMember = null;
        for(Member member : memberRepo){
            if (member.getId()==loginedMemberId) {
                findMember = member;
                break;
            }
        }
        return findMember;
    }
}
