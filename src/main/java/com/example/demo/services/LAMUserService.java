package com.example.demo.services;

import com.example.demo.models.AutoShop;
import com.example.demo.models.LAMUser;
import com.example.demo.repositories.LAMUserRepository;
import com.example.demo.services.base.BaseServiceImpl;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

import static com.example.demo.utils.Constants.*;

@Service
@GraphQLApi
public class LAMUserService extends BaseServiceImpl<LAMUser, UUID, LAMUserRepository> {

    private final AutoShopService autoShopService;

    @Autowired
    public LAMUserService(LAMUserRepository repository, AutoShopService autoShopService) {
        super(repository);
        this.autoShopService = autoShopService;
    }

    @Override
    @GraphQLQuery(name = "users", description = "get all users")
    public Page<LAMUser> fetchAllPaged(
            @GraphQLArgument(name = "page", defaultValue = DEFAULT_PAGE) int page,
            @GraphQLArgument(name = "size", defaultValue = DEFAULT_PAGE_SIZE) int size
    ) {
        return super.fetchAllPaged(page, size);
    }

    @Override
    @GraphQLMutation(name = "saveUser")
    public LAMUser create(@GraphQLNonNull LAMUser user) {
        if (repository.existsByUsernameOrEmail(user.getUsername(), user.getEmail())) {
            throw new IllegalArgumentException(
                    String.format(
                            ALREADY_EXISTS_MESSAGE,
                            String.format("User with username/email: %s/%s", user.getUsername(), user.getEmail())
                    )
            );
        }
        return super.repository.save(user);
    }

    @Override
    @GraphQLMutation(name = "updateUser")
    public LAMUser update(@GraphQLNonNull UUID uuid, @GraphQLNonNull LAMUser user) {
        return super.update(uuid, user);
    }

    @Override
    @GraphQLMutation(name = "deleteUser")
    public boolean delete(@GraphQLNonNull UUID uuid) {
        return super.delete(uuid);
    }


    @GraphQLMutation(name = "addShopToFavorites")
    public Set<AutoShop> addToFavorites(
            @GraphQLNonNull UUID userId,
            @GraphQLNonNull UUID shopId
    ) {
        LAMUser user = findById(userId);
        AutoShop shop = autoShopService.findById(shopId);
        Set<AutoShop> favorites = user.getFavorites();
        favorites.add(shop);
        update(userId, user);
        return favorites;
    }
}
