package org.crypto.resource.members;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.crypto.core.crud.CrudEndPointImpl;
import org.crypto.core.features.ApiResponse;
import org.crypto.db.entities.Librarian;
import org.crypto.db.entities.Member;
import org.crypto.db.repositories.MemberRepository;

@Path("/members")
public class MemberManager extends CrudEndPointImpl<Member> {

    @Inject
    MemberRepository repo;

    @Inject
    public void initRepo() {
        setT(repo);
    }


}
