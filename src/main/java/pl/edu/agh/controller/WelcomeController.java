package pl.edu.agh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.edu.agh.CommandExecutor;
import pl.edu.agh.model.Command;
import pl.edu.agh.utils.CommandExecutionException;
import pl.edu.agh.utils.UnrecognizedCommand;

import java.util.Map;

@Controller
public class WelcomeController {

    @Autowired
    CommandExecutor commandExecutor;

    @RequestMapping(value = "/visca", method = RequestMethod.GET)
    public ModelAndView visca(Map<String, Object> model) {

        model.put("model", new Command());
        model.put("commandTypes", pl.edu.agh.model.ViscaCommand.values());

        return new ModelAndView("visca", model);
    }

    @RequestMapping("/runCommand")
    public String runCommand(@ModelAttribute("model") Command command, BindingResult bindingResult, ModelMap modelMap) {

        try {
            String result = commandExecutor.executeCommand(command);
            modelMap.addAttribute("result", result);
        } catch (UnrecognizedCommand | CommandExecutionException e) {
            modelMap.addAttribute("error", e.getMessage());
        }

        return "visca";

    }

}
