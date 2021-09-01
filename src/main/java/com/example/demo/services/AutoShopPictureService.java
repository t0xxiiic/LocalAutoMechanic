package com.example.demo.services;

import com.example.demo.models.AutoShop;
import com.example.demo.models.AutoShopPicture;
import com.example.demo.repositories.AutoShopPictureRepository;
import com.example.demo.services.base.BaseServiceImpl;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@GraphQLApi
public class AutoShopPictureService extends BaseServiceImpl<AutoShopPicture, UUID, AutoShopPictureRepository> {
    private final AutoShopService shopService;

    @Autowired
    public AutoShopPictureService(AutoShopPictureRepository repository, AutoShopService shopService) {
        super(repository);
        this.shopService = shopService;
    }

    @GraphQLMutation(name = "saveShopPicture")
    public AutoShopPicture create(@GraphQLNonNull String picURL, @GraphQLNonNull UUID shopId) {
        AutoShop        shop            = shopService.findById(shopId);
        AutoShopPicture autoShopPicture = new AutoShopPicture();
        autoShopPicture.setShop(shop);
        autoShopPicture.setPictureURL(picURL);
        return create(autoShopPicture);
    }

    @GraphQLMutation(name = "saveMultipleShopPictures")
    public List<AutoShopPicture> createBatch(@GraphQLNonNull UUID shopId, @GraphQLNonNull List<String> urls) {
        AutoShop              shop           = shopService.findById(shopId);
        List<AutoShopPicture> picturesToSave = new ArrayList<>();

        urls.forEach(url -> {
            AutoShopPicture autoShopPicture = new AutoShopPicture();
            autoShopPicture.setShop(shop);
            autoShopPicture.setPictureURL(url);
            picturesToSave.add(autoShopPicture);
        });

        return super.repository.saveAll(picturesToSave);
    }

    @Override
    @GraphQLMutation(name = "deleteShopPicture")
    public boolean delete(@GraphQLNonNull UUID uuid) {
        return super.delete(uuid);
    }
}
