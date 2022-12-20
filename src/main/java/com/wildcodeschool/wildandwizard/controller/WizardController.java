package com.wildcodeschool.wildandwizard.controller;

import com.wildcodeschool.wildandwizard.repository.WizardRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;

@Controller
public class WizardController {

    private WizardRepository repository = new WizardRepository();

    @GetMapping("/wizards")
    public String getAll(Model model) {

        model.addAttribute("wizards", repository.findAll());

        return "wizard_get_all";
    }

    @GetMapping("/wizard")
    public String getById(Model model, @RequestParam Long id) {

        model.addAttribute("wizard", repository.findById(id));

        return "wizard_get";
    }

    @GetMapping("/wizards/search")
    public String getByLastName(Model model, @RequestParam String lastName) {

        model.addAttribute("wizards", repository.findByLastName(lastName));

        return "wizard_get_all";
    }

    @PostMapping("/wizard/create")
    public String postWizard(Model model,
                             @RequestParam String firstName,
                             @RequestParam String lastName,
                             @RequestParam Date birthday,
                             @RequestParam String birthPlace,
                             @RequestParam(required = false, defaultValue = "") String biography,
                             @RequestParam(required = false, defaultValue = "false") boolean muggle
    ) {
        model.addAttribute("wizard", repository.save(firstName, lastName,
                birthday, birthPlace, biography, muggle));

        return "wizard_get";
    }

    @GetMapping("/wizard/update")
    public String getWizardUpdate(Model model,
                                  @RequestParam Long id) {

        model.addAttribute("wizard", repository.findById(id));

        return "wizard_update";
    }

    @PostMapping("/wizard/update")
    public String postWizardUpdate(Model model,
                                   @RequestParam Long id,
                                   @RequestParam String firstName,
                                   @RequestParam String lastName,
                                   @RequestParam Date birthday,
                                   @RequestParam String birthPlace,
                                   @RequestParam(required = false, defaultValue = "") String biography,
                                   @RequestParam(required = false, defaultValue = "false") boolean muggle
    ) {
        model.addAttribute("wizard", repository.update(id, firstName, lastName,
                birthday, birthPlace, biography, muggle));

        return "wizard_get";
    }

    @GetMapping("/wizard/delete")
    public String deleteWizard(@RequestParam Long id) {

        repository.deleteById(id);

        return "redirect:/wizards";
    }

}
