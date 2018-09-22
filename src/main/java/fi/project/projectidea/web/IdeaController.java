package fi.project.projectidea.web;

import fi.project.projectidea.domain.Idea;
import fi.project.projectidea.domain.IdeaRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Controller
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IdeaController {

    IdeaRepository ideaRepository;

    //Constructor dependency injection (recommended by the Spring team)
    @Autowired
    public IdeaController(IdeaRepository ideaRepository) {
        this.ideaRepository = ideaRepository;
    }

    //returns the login page
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    //redirects to idealist.html
    @GetMapping("/")
    public String index() {
        return "redirect:idealist";
    }

    //returns idealist.html page which consist of all the ideas
    @GetMapping("/idealist")
    public String ideaList(Model model) {
        model.addAttribute("ideas", ideaRepository.findAll());
        return "idealist";
    }

    /*
    returns addidea.html page where users can add ideas
    It sends a new instance of an Idea to the "addidea" page
     */
    @GetMapping("/addidea")
    public String addIdea(Model model) {
        model.addAttribute("idea", new Idea());
        return "addidea";
    }

    //Saves the added idea to a repository and then redirects to idealist.html pag
    @PostMapping("/save")
    public String save(Idea idea) {
        ideaRepository.save(idea);
        return "redirect:/idealist";
    }

    //Deletes the deleted idea from the repository  and then redirects to idealist.html page
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") long id) {
        ideaRepository.deleteById(id);
        return "redirect:/idealist";
    }

    //Edits the selected idea
    @GetMapping("/edit/{id}")
    public String editIdea(@PathVariable("id") long id, Model model) {
        Optional<Idea> idea = ideaRepository.findById(id);
        model.addAttribute("idea", idea);
        return "editidea";
    }

    //Return Ideas that contains the requested parameter in their name in JSON format
    @GetMapping("/ideas")
    public @ResponseBody
    List<Idea> ideas(@RequestParam(value = "name", required = false, defaultValue = "") String name) {
        List<Idea> ideas = (List<Idea>) ideaRepository.findAll();
        Iterator<Idea> i = ideas.iterator();
        List<Idea> matchedIdeas = new ArrayList<>();
        Idea tempIdea;
        while (i.hasNext()) {
            tempIdea = i.next();
            if (tempIdea.getName().toLowerCase().contains(name)) {
                matchedIdeas.add(tempIdea);
            }
        }
        return matchedIdeas;
    }
}