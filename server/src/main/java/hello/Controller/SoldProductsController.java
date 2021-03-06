package hello.Controller;

import hello.Config;
import hello.Model.CountSoldProduct;
import hello.Model.SoldProducts;
import hello.Model.SoldProductsRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Component
public class SoldProductsController {

    @RequestMapping(value = "/soldproducts/{Name}")
    public @ResponseBody
    Iterable<SoldProducts> listsoldProducts(@PathVariable("Name") String Name) {   // бул жерде да id
        return  getRepository().findByName(Name);
    }
    @RequestMapping(value = "/soldproducts/sum/{Name}")
    public @ResponseBody
    CountSoldProduct sumSoldProducts(@PathVariable("Name") String Name) {   // бул жерде да id
        CountSoldProduct countSoldProduct = new CountSoldProduct();
        Iterable<SoldProducts> products = getRepository().findByName(Name);
        for (SoldProducts soldProducts : products ) {
            int count = soldProducts.getCount();
            Double price = soldProducts.getPrice();
            countSoldProduct.setCountProduct(countSoldProduct.getCountProduct()+count);
            countSoldProduct.setSumProduct(countSoldProduct.getSumProduct()+count*price);
        }

        return countSoldProduct;
    }
    @RequestMapping(value = "/soldproducts")
    public @ResponseBody
    Iterable<SoldProducts> listsoldProducts() {
        return  getRepository().findAll();
    }

    public SoldProductsRepository getRepository() {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        return context.getBean(SoldProductsRepository.class);
    }
}
