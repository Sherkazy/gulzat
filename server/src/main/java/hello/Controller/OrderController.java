package hello.Controller;

import hello.Config;
import hello.Model.*;
import hello.Model.RequestDto.AddOrderRequest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
/**
* Created by Aza on 09.12.13.
*/
@Component
@Controller
public class OrderController {

    @RequestMapping(value = "/orders")
    public @ResponseBody
    Iterable<OrderedProducts> listOrders() {

        return  getRepository().findAll();
    }

    @RequestMapping(value = "/addOrder",method = RequestMethod.POST)
    public @ResponseBody
    OrderedProducts addOrder(@RequestBody AddOrderRequest addOrderRequest) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
//        SellerRepository sellerRepository = context.getBean(SellerRepository.class);
//        Seller seller = sellerRepository.findOne(addOrderRequest.getSellerId());
        OrderedProducts order = new OrderedProducts (addOrderRequest.getOrderedProduct(),addOrderRequest.getPrice());
//        order.setSeller(seller);
        getRepository().save(order);
        return order;
    }

//    @RequestMapping(value = "/addOrder/{orderedProduct}")
//    public @ResponseBody
//    boolean addOrder(@PathVariable("orderedProduct") String orderedProduct) {
//        Iterable<OrderedProducts> orders = getRepository().findByOrderedProduct(orderedProduct);
//        getRepository().save(orders);
//        return true;
//    }



    public OrderRepository getRepository() {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        return context.getBean(OrderRepository.class);
    }
}
