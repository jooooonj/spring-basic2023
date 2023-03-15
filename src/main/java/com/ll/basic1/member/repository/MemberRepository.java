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
        memberRepo.add(new Member("user1", "1234"));
        memberRepo.add(new Member("abc", "12345"));
        memberRepo.add(new Member("test", "12346"));
        memberRepo.add(new Member("love", "12347"));
        memberRepo.add(new Member("like", "12348"));
        memberRepo.add(new Member("giving", "12349"));
        memberRepo.add(new Member("thanks", "123410"));
        memberRepo.add(new Member("hello", "123411"));
        memberRepo.add(new Member("good", "123412"));
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
