package com.example.demo.services;

import com.example.demo.models.Visit;
import com.example.demo.repositories.VisitRepository;
import com.example.demo.services.base.BaseServiceImpl;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@GraphQLApi
public class VisitService extends BaseServiceImpl<Visit, UUID, VisitRepository> {

    @Autowired
    public VisitService(VisitRepository repository) {
        super(repository);
    }

    @Override
    @GraphQLMutation(name = "updateVisit")
    public Visit update(@GraphQLNonNull UUID uuid, @GraphQLNonNull Visit visit) {
        return super.update(uuid, visit);
    }

    @Override
    @GraphQLMutation(name = "deleteVisit")
    public boolean delete(@GraphQLNonNull UUID uuid) {
        return super.delete(uuid);
    }
}
