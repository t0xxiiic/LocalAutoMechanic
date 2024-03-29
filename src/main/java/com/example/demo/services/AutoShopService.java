package com.example.demo.services;

import com.example.demo.models.AutoShop;
import com.example.demo.repositories.AutoShopRepository;
import com.example.demo.services.base.BaseServiceImpl;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

import static com.example.demo.utils.Constants.*;

@Service
@GraphQLApi
public class AutoShopService extends BaseServiceImpl<AutoShop, UUID, AutoShopRepository> {

    public static final String UNIQUE_SHOP_NAME_MSG = "Shop with name %s already exists.";
    public static final String SHOP_NOT_FOUND_MSG   = "Shop %s in %s not found.";

    @Autowired
    public AutoShopService(AutoShopRepository repository) {
        super(repository);
    }

    @GraphQLQuery(name = "getShopByNameAndCity")
    public AutoShop findByName(
            @GraphQLNonNull String shopName,
            @GraphQLNonNull String city
    ) {
        if (StringUtils.isAnyBlank(shopName, city)) {
            throw new IllegalArgumentException("Shop name and city must not be empty or blank.");
        }
        return repository
                .findByNameAndCityAllIgnoreCase(shopName, city)
                .orElseThrow(() -> new EntityNotFoundException(String.format(SHOP_NOT_FOUND_MSG, shopName, city)));
    }

    @Override
    @GraphQLQuery(name = "getShop")
    public AutoShop findById(@GraphQLNonNull UUID uuid) {
        return super.findById(uuid);
    }

    @Override
    @GraphQLQuery(name = "shops")
    public Page<AutoShop> fetchAllPaged(
            @GraphQLArgument(name = "page", defaultValue = DEFAULT_PAGE) int page,
            @GraphQLArgument(name = "size", defaultValue = DEFAULT_PAGE_SIZE) int size
    ) {
        //TODO change this from pages to get locations base on lat and long
        return super.fetchAllPaged(page, size);
    }

    @Override
    @GraphQLMutation(name = "saveShop")
    public AutoShop create(@GraphQLNonNull AutoShop shop) {
        if (repository.existsByName(shop.getName())) {
            throw new IllegalArgumentException(String.format(ALREADY_EXISTS_MESSAGE, shop.getName()));
        }
        return super.create(shop);
    }

    @Override
    @GraphQLMutation(name = "updateShop")
    public AutoShop update(@GraphQLNonNull UUID uuid, @GraphQLNonNull AutoShop shop) {
        return super.update(uuid, shop);
    }

    @Override
    @GraphQLMutation(name = "deleteShop")
    public boolean delete(@GraphQLNonNull UUID uuid) {
        return super.delete(uuid);
    }
}
