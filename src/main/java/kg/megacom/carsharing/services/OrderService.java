package kg.megacom.carsharing.services;

import kg.megacom.carsharing.models.entities.Car;
import kg.megacom.carsharing.models.entities.Order;
import kg.megacom.carsharing.models.entities.User;

public interface OrderService {
    Order save(User mapToUser, Car mapToCar, String startDate, String endDate, double totalSum, Double km);
}