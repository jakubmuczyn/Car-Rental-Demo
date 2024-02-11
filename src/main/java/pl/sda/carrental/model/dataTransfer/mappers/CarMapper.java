package pl.sda.carrental.model.dataTransfer.mappers;

import pl.sda.carrental.model.entity.Car;
import pl.sda.carrental.model.dataTransfer.CarDTO;

import java.util.ArrayList;
import java.util.List;

public class CarMapper {

    public static CarDTO map(Car car){
        CarDTO carDto = new CarDTO();
        carDto.setId(car.getId());
        carDto.setDivision(car.getDivision());
        carDto.setReservation(car.getReservation());
        carDto.setBrand(car.getBrand());
        carDto.setModel(car.getModel());
        carDto.setBodyType(car.getBody_type());
        carDto.setProductionYear(car.getProduction_year());
        carDto.setColor(car.getColor());
        carDto.setMileage(car.getMileage());
        carDto.setStatus(car.getStatus());
        carDto.setCostPerDay(car.getCost_per_day());
        return carDto;
    }

    public static Car map(CarDTO carDto){
        Car car = new Car();
        car.setId(carDto.getId());
        car.setDivision(carDto.getDivision());
        car.setReservation(carDto.getReservation());
        car.setBrand(carDto.getBrand());
        car.setModel(carDto.getModel());
        car.setBody_type(carDto.getBodyType());
        car.setProduction_year(carDto.getProductionYear());
        car.setColor(carDto.getColor());
        car.setMileage(carDto.getMileage());
        car.setStatus(carDto.getStatus());
        car.setCost_per_day(carDto.getCostPerDay());
        return car;
    }

    public static List<CarDTO> mapEntityListToDtoList(List<Car> cars){

        List<CarDTO> result = new ArrayList<>();
        for (Car car: cars) {
            result.add(map(car));
        }
        return result;
    }

    public static List<Car> mapDtoListToEntityList(List<CarDTO> dtos){

        List<Car> result = new ArrayList<>();
        for (CarDTO dto: dtos) {
            result.add(map(dto));
        }
        return result;
    }
}
