package org.crypto.db.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.crypto.db.entities.Member;

@ApplicationScoped
public class MemberRepository implements PanacheRepository<Member> {
    public Member findByMembershipNumber(String number) {
        return find("membershipNumber", number).firstResult();
    }
}
