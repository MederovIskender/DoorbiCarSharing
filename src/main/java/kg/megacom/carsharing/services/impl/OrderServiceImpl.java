package kg.megacom.carsharing.services.impl;

import kg.megacom.carsharing.models.entities.Car;
import kg.megacom.carsharing.models.entities.Order;
import kg.megacom.carsharing.models.entities.User;
import kg.megacom.carsharing.repositories.OrderRepository;
import kg.megacom.carsharing.services.OrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order save(User user, Car car, String startDate, String endDate, double totalSum, Double km) {
        Order order = new Order();

        order.setUser(user);
        order.setCar(car);
        order.setStartDate(LocalDate.parse(startDate, formatter));
        order.setEndDate(LocalDate.parse(endDate, formatter));
        order.setSum(totalSum);
        order.setKm(km);

        return orderRepository.save(order);
    }
}