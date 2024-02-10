package pl.sda.carrental.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.sda.carrental.model.dataTransfer.CreateDivisionDTO;
import pl.sda.carrental.model.dataTransfer.DivisionDTOForPanel;
import pl.sda.carrental.model.dataTransfer.mappers.DivisionMapperForPanel;
import pl.sda.carrental.model.dataTransfer.mappers.EmployeeMapper;
import pl.sda.carrental.model.entity.Address;
import pl.sda.carrental.model.entity.Division;
import pl.sda.carrental.model.entity.userEntities.Employee;
import pl.sda.carrental.model.repository.AddressRepository;
import pl.sda.carrental.model.repository.DivisionRepository;
import pl.sda.carrental.model.repository.userRepositories.EmployeeRepository;
import pl.sda.carrental.service.DivisionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class DivisionController {
    private final DivisionRepository divisionRepository;
    private final DivisionMapperForPanel divisionMapper;
    private final AddressRepository addressRepository;
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final DivisionService divisionService;

    public DivisionController(DivisionRepository divisionRepository, DivisionMapperForPanel divisionMapper, AddressRepository addressRepository, EmployeeRepository employeeRepository, EmployeeMapper employeeMapper, DivisionService divisionService) {
        this.divisionRepository = divisionRepository;
        this.divisionMapper = divisionMapper;
        this.addressRepository = addressRepository;
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
        this.divisionService = divisionService;
    }
    @GetMapping("/divisions/{division_id}")
    public String editDivision(Model model, @PathVariable long division_id) {
        DivisionDTOForPanel divisionDTO = divisionMapper.getDivisionDTO(divisionRepository.findById(division_id).get());
//        List<Address> addresses = new ArrayList<>();
        List<Address> addresses = new ArrayList<>();
        addresses.add(divisionDTO.getAddress());
        addresses.addAll(addressRepository.findAllUnusedAddresses());


        model.addAttribute("division", divisionDTO);
        model.addAttribute("addresses", addresses);
        model.addAttribute("positions", Employee.Position.values());
        return "divisionPanels/divisionEdit";
    }
    @PostMapping("/divisions/edit/save")
    public String saveDivision(DivisionDTOForPanel divisionDTO) {
        divisionRepository.save(divisionMapper.getDivisionObject(divisionDTO));
        return "redirect:/divisions/" + divisionDTO.getDivision_id();
    }
    @GetMapping("/divisions")
    public String getDivisions(Model model) {
       List<Division> divisions = divisionRepository.findAll();
       List<DivisionDTOForPanel> divisionDTOs = divisions.stream().map(divisionMapper::getDivisionDTO).toList();
       model.addAttribute("divisions", divisionDTOs);
       return "divisionPanels/divisionPanel";
    }

    @GetMapping("/divisions/{division_id}/edit/removeEmployee/{employee_id}")
    public String removeEmployeeFromDivision(@PathVariable Long division_id, @PathVariable Long employee_id) {
        Division division = divisionRepository.getReferenceById(division_id);
        Employee employee = employeeRepository.getReferenceById(employee_id);

        divisionService.removeEmployee(division, employee);
        return "redirect:/divisions/" + division_id;
    }
    @PostMapping("/employeeSelection")
    public String addEmployeesToDivision(@RequestParam(value = "selectedUsers", required = false) List<Long> selectedUserIds, @RequestParam Long division_id) {
        Division division = divisionRepository.getReferenceById(division_id);
        List<Employee> employees = employeeRepository.findAllById(selectedUserIds);
        divisionService.addEmployees(division,employees);
        return "redirect:/divisions/" + division_id;
    }

    @GetMapping("/employeeSelection/{division_id}")
    public String selectEmployees(Model model, @PathVariable Long division_id) {
        List<Employee> activeEmployees = employeeRepository.findAllActiveNonManagers();
        activeEmployees = activeEmployees.stream().filter(e -> e.getDivision() == null || !Objects.equals(e.getDivision().getDivision_id(), division_id)).toList();
        model.addAttribute("users", activeEmployees.stream().map(employeeMapper::getDto).toList());
        model.addAttribute("division_id", division_id);
        return "divisionPanels/addEmployee";
    }

    @GetMapping("/divisions/new")
    public String createDevision(Model model) {
        model.addAttribute("newDivision", new CreateDivisionDTO());
        model.addAttribute("employees", employeeRepository.findAllActiveNonManagers());
        return "divisionPanels/createDivision";
    }
    @PostMapping("/divisions/new")
    public String createDevision(CreateDivisionDTO newDivision) {
        divisionService.createDivision(newDivision);
        return "redirect:/divisions";
    }

}
