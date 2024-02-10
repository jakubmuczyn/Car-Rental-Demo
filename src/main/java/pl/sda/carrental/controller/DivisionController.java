package pl.sda.carrental.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.sda.carrental.model.dataTransfer.DivisionDTO;
import pl.sda.carrental.model.dataTransfer.EmployeeDTO;
import pl.sda.carrental.model.dataTransfer.mappers.DivisionMapper;
import pl.sda.carrental.model.dataTransfer.mappers.EmployeeMapper;
import pl.sda.carrental.model.entity.Division;
import pl.sda.carrental.model.entity.userEntities.Employee;
import pl.sda.carrental.model.repository.AddressRepository;
import pl.sda.carrental.model.repository.DivisionRepository;
import pl.sda.carrental.model.repository.userRepositories.EmployeeRepository;
import pl.sda.carrental.service.DivisionService;

import java.util.List;

@Controller
public class DivisionController {
    private final DivisionRepository divisionRepository;
    private final DivisionMapper divisionMapper;
    private final AddressRepository addressRepository;
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final DivisionService divisionService;

    public DivisionController(DivisionRepository divisionRepository, DivisionMapper divisionMapper, AddressRepository addressRepository, EmployeeRepository employeeRepository, EmployeeMapper employeeMapper, DivisionService divisionService) {
        this.divisionRepository = divisionRepository;
        this.divisionMapper = divisionMapper;
        this.addressRepository = addressRepository;
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
        this.divisionService = divisionService;
    }
    @GetMapping("/divisions/new")
    public String newDivision(Model model) {
        model.addAttribute("division", new DivisionDTO());
        return "redirect:/oops";
    }
    @GetMapping("/divisions/{division_id}")
    public String editDivision(Model model, @PathVariable long division_id) {
        DivisionDTO divisionDTO = divisionMapper.getDivisionDTO(divisionRepository.findById(division_id).get());

        model.addAttribute("division", divisionDTO);
        model.addAttribute("addresses", addressRepository.findAll());
        model.addAttribute("positions", Employee.Position.values());
        return "divisionPanels/divisionEdit";
    }
    @PostMapping("/divisions/edit/save")
    public String saveDivision(DivisionDTO divisionDTO) {
        Employee manager = employeeRepository.getReferenceById(divisionDTO.getManager().getId());
        divisionRepository.save(divisionMapper.getDivisionObject(divisionDTO));
        return "redirect:/divisions";
    }
    @GetMapping("/divisions")
    public String getDivisions(Model model) {
       List<Division> divisions = divisionRepository.findAll();
       List<DivisionDTO> divisionDTOs = divisions.stream().map(divisionMapper::getDivisionDTO).toList();
       model.addAttribute("divisions", divisionDTOs);
       return "divisionPanels/divisionPanel";
    }
    @PostMapping("/employeeSelection")
    public String addEmployeesToDivision(@RequestParam(value = "selectedUsers", required = false) List<Long> selectedUserIds, @RequestParam Long division_id) {
        Division division = divisionRepository.getReferenceById(division_id);
        List<Employee> employees = employeeRepository.findAllById(selectedUserIds);
        divisionService.addEmployees(division,employees);
        return "redirect:/divisions";
    }

    @GetMapping("/employeeSelection/{division_id}")
    public String selectEmployees(Model model, @PathVariable Long division_id) {
        List<Employee> activeEmployees = employeeRepository.findAllByIsActiveIsTrue();
        model.addAttribute("users", activeEmployees.stream().map(employeeMapper::getDto).toList());
        model.addAttribute("division_id", division_id);
        return "divisionPanels/addEmployee";
    }

}
