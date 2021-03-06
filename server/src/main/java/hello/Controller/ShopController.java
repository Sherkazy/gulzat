package hello.Controller;

import hello.Config;
import hello.Model.*;
import hello.Model.RequestDto.AddSellerRequest;
import hello.Model.RequestDto.AddShopRequest;
import hello.Model.Repository.ShopRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Component
@Controller
public class ShopController {

    @RequestMapping(value = "/shops")
    public @ResponseBody
    Iterable<Shop> listShops() {

        return  getRepository().findAll();

    }

    @RequestMapping(value = "/shops/delete/{id}")
    public @ResponseBody
    boolean deleteShop(@PathVariable("id") long id) {

        getRepository().delete(id);
        return true;
    }

    @RequestMapping(value = "/addShop",method = RequestMethod.POST)
    public @ResponseBody
    Shop addShop(@RequestBody AddShopRequest addShopRequest) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        Shop shop= new Shop(addShopRequest.getName(),addShopRequest.getContacts());
        getRepository().save(shop);
        return shop;
    }

    public ShopRepository getRepository() {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        return context.getBean(ShopRepository.class);
    }
}

