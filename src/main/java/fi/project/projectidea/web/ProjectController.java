package fi.project.projectidea.web;

import fi.project.projectidea.domain.Idea;
import fi.project.projectidea.domain.IdeaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class ProjectController {

    private IdeaRepository ideaRepository;

    //Constructor dependency injection (recommended by the Spring team)
    @Autowired
    public ProjectController(IdeaRepository ideaRepository) {
        this.ideaRepository = ideaRepository;
    }

    //returns index.html page which is the welcome page
    @GetMapping("/")
    public String index() {
        return "index";
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

    //Saves the added idea to a repository
    @PostMapping("/save")
    public String save(Idea idea) {
        ideaRepository.save(idea);
        return "redirect:/idealist";
    }

    //Deletes the deleted idea from the repository
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
}
