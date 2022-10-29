package XDirectionProject.controller;

import XDirectionProject.dto.LifterDTO;
import XDirectionProject.model.lifters.Lifter;
import XDirectionProject.service.LifterService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(name = "/lifter")
public class LifterController {

    private ModelMapper modelMapper;
    private LifterService lifterService;

    public LifterController(LifterService lifterService) {
        this.lifterService = lifterService;
        this.modelMapper = new ModelMapper();
    }

    @GetMapping("/add-lifter/{email}")
    public ResponseEntity<LifterDTO> getLifterById(@PathVariable(value = "email") String email) {
        Lifter foundLifter = lifterService.findLifterByEmail(email);
        return ResponseEntity.ok(modelMapper.map(foundLifter, LifterDTO.class));
    }

    @PostMapping
    public ResponseEntity<LifterDTO> saveLifter(@RequestBody LifterDTO newLifter) {
        Lifter savedLifter = lifterService.addLifter(modelMapper.map(newLifter, Lifter.class));
        return ResponseEntity.ok(modelMapper.map(savedLifter, LifterDTO.class));
    }

    @DeleteMapping("/delete-lifter/{email}")
    public ResponseEntity deleteLifter(@PathVariable(value = "email") String email) {
        lifterService.deleteLifter(email);
        return new ResponseEntity("Lifter with " + email + " deleted successfully!", HttpStatus.OK);
    }
}
