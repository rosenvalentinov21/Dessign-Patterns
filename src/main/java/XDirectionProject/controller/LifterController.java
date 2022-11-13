package XDirectionProject.controller;

import XDirectionProject.dto.LifterDTO;
import XDirectionProject.model.lifters.Lifter;
import XDirectionProject.service.LifterService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lifter")
public class LifterController {

    private ModelMapper modelMapper;
    private LifterService lifterService;

    public LifterController(LifterService lifterService) {
        this.lifterService = lifterService;
        this.modelMapper = new ModelMapper();
    }

    @GetMapping("/get-lifter/{email}")
    public ResponseEntity<LifterDTO> getLifterById(@PathVariable(name = "email") String email) {
        Lifter foundLifter = lifterService.findLifterByEmail(email);
        return ResponseEntity.ok(modelMapper.map(foundLifter, LifterDTO.class));
    }

    @PostMapping("/add-lifter")
    public ResponseEntity<LifterDTO> saveLifter(@RequestBody LifterDTO newLifter) {
        Lifter savedLifter = lifterService.addLifter(modelMapper.map(newLifter, Lifter.class));
        return ResponseEntity.ok(modelMapper.map(savedLifter, LifterDTO.class));
    }

    @PostMapping("/update-lifter-details/{email}")
    public ResponseEntity<LifterDTO> updateLifter(@PathVariable(name = "email") String email, @RequestBody LifterDTO newLifterDetails) {
        Lifter updatedLifter = lifterService.updateLifter(email, newLifterDetails);
        return ResponseEntity.ok(modelMapper.map(updatedLifter, LifterDTO.class));
    }

    @DeleteMapping("/delete-lifter/{email}")
    public ResponseEntity deleteLifter(@PathVariable(value = "email") String email) {
        lifterService.deleteLifter(email);
        return new ResponseEntity("Lifter with email " + email + " deleted successfully!", HttpStatus.OK);
    }
}
