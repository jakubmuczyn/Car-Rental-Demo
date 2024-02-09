package pl.sda.carrental.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
public class UiService {
    private boolean isDisplayHiddenUsers = false;
}
