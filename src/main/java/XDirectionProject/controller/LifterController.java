package XDirectionProject.controller;

import XDirectionProject.dto.LifterDTO;
import XDirectionProject.model.lifters.Lifter;
import XDirectionProject.service.LifterService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(name = "/lifter")
public class LifterController {

    private LifterService lifterService;

    public LifterController(LifterService lifterService) {
        this.lifterService = lifterService;
    }

    @GetMapping(value = "/{email}")
    public Lifter getLifterById(@PathVariable String email){
        return lifterService.findLifterByEmail(email);
    }

    @PostMapping
    public Lifter saveLifter(@RequestBody LifterDTO newLifter){

//        return lifterService.addLifter()
    }
}
