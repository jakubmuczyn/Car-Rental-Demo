package pl.sda.carrental.model.dto;

import pl.sda.carrental.model.entity.userEntities.User;

public interface DtoConverter<T extends User, W extends UserDTO> {
    T getUserClass(W dto);
    W getDto(T userClass) ;
}
