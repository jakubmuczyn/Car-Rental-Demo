package pl.sda.carrental.model.repository.mapper;

import pl.sda.carrental.model.entity.Division;
import pl.sda.carrental.model.repository.dto.DivisionDto;

import java.util.ArrayList;
import java.util.List;

public class DivisionMapper {

    public static DivisionDto map(Division division){
        DivisionDto DivisionDto = new DivisionDto();
        DivisionDto.setDivision_id(division.getDivision_id());
        DivisionDto.setAddress(division.getAddress());
        return DivisionDto;
    }

    public static Division map(DivisionDto DivisionDto){
        Division Division = new Division();
        Division.setDivision_id(DivisionDto.getDivision_id());
        Division.setAddress(DivisionDto.getAddress());
        return Division;
    }

    public static List<DivisionDto> mapEntityListToDtoList(List<Division> divisions){

        List<DivisionDto> result = new ArrayList<>();
        for (Division division: divisions) {
            result.add(map(division));
        }
        return result;
    }

    public static List<Division> mapDtoListToEntityList(List<DivisionDto> dtos){

        List<Division> result = new ArrayList<>();
        for (DivisionDto dto: dtos) {
            result.add(map(dto));
        }
        return result;
    }
}
