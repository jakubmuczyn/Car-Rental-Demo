package pl.sda.carrental.model.entity;

import pl.sda.carrental.model.repository.dto.CarDto;

import java.util.ArrayList;
import java.util.List;

public class CarMapper {

    public static CarDto map(Car car){
        CarDto carDto = new CarDto();
        carDto.setId(car.getId());
        carDto.setDivision(car.getDivision());
        carDto.setReservation(car.getReservation());
        carDto.setBrand(car.getBrand());
        carDto.setModel(car.getModel());
        carDto.setBody_type(car.getBody_type());
        carDto.setProduction_year(car.getProduction_year());
        carDto.setColor(car.getColor());
        carDto.setMileage(car.getMileage());
        carDto.setStatus(car.getStatus());
        carDto.setCost_per_day(car.getCost_per_day());
        return carDto;
    }

    public static Car map(CarDto carDto){
        Car car = new Car();
        car.setId(carDto.getId());
        car.setDivision(carDto.getDivision());
        car.setReservation(carDto.getReservation());
        car.setBrand(carDto.getBrand());
        car.setModel(carDto.getModel());
        car.setBody_type(carDto.getBody_type());
        car.setProduction_year(carDto.getProduction_year());
        car.setColor(carDto.getColor());
        car.setMileage(carDto.getMileage());
        car.setStatus(carDto.getStatus());
        car.setCost_per_day(carDto.getCost_per_day());
        return car;
    }

    public static List<CarDto> mapEntityListToDtoList(List<Car> cars){

        List<CarDto> result = new ArrayList<>();
        for (Car car: cars) {
            result.add(map(car));
        }
        return result;
    }

    public static List<Car> mapDtoListToEntityList(List<CarDto> dtos){

        List<Car> result = new ArrayList<>();
        for (CarDto dto: dtos) {
            result.add(map(dto));
        }
        return result;
    }
}
