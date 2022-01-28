package day01;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderService {

    private List<Order> orders = new ArrayList<>();


    public void saveOrder(Order order) {
        orders.add(order);
    }

    public List<Order> getOrdersByStatus(String status){
   return orders.stream()
        .filter(o->o.getStatus().equals(status))
           .collect(Collectors.toList());
    }

    public long countOrdersByStatus(String status){
        return orders.stream()
                .filter(o->o.getStatus().equals(status))
                .count();
    }
    public List<Order> getOrdersBetweenDates(LocalDate start, LocalDate end){
        return orders.stream()
                .filter(o->o.getOrderDate().isAfter(start))
                .filter(o->o.getOrderDate().isBefore(end))
                .collect(Collectors.toList());
    }

    public List<Order> sortOrderByStatusAndOrderDate(){
        return orders.stream()
                .sorted(Comparator.comparing(Order::getStatus).thenComparing(Order::getOrderDate))
                .toList();
    }
    public boolean hasOrderLessThan(int max){
    //    return orders.stream()
     //           .filter(o->o.getProducts().size()<max)
     //           .collect(Collectors.toList()).isEmpty();

        return orders.stream()
                .mapToInt(o->o.getProducts().size())
                .anyMatch(o-> o<max);
    }

    public Order getBiggestOrder(){
           return orders.stream()
                   .max(Comparator.comparing(o->o.getProducts().size())).orElseThrow(()-> new IllegalArgumentException("Hiba"));

           //    .max(new Comparator<Order>() {
               //             @Override
               //             public int compare(Order o1, Order o2) {
               //                 return o1.getProducts().size()-o2.getProducts().size();
               //             }
               //         }).orElseThrow(()-> new IllegalArgumentException("Hiba"));


    }
    public List<Order> getOrdersContainsCategory(String category){
     //   return orders.stream()
      //          .filter(o->o.hasProductCategory(category))
      //          .collect(Collectors.toList());

            return orders.stream()
           .filter(o->o.getProducts()
              .stream().anyMatch(p->p.getCategory().equals(category)))
              .collect(Collectors.toList());
    }

    public List<Product> findProductsOverPrice(int amount){
        return orders.stream()
                .flatMap(o->o.getProducts().stream())
                .filter(p->p.getPrice()>amount)
                .distinct()
                .toList();

    }

}