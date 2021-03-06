package fi.project.projectidea.web;

import fi.project.projectidea.domain.Idea;
import fi.project.projectidea.domain.IdeaRepository;
import fi.project.projectidea.domain.UserRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    UserRepository userRepository;

    //Constructor dependency injection of ideaRepository and userRepository
    @Autowired
    public IdeaController(IdeaRepository ideaRepository, UserRepository userRepository) {
        this.ideaRepository = ideaRepository;
        this.userRepository = userRepository;
    }

    //returns the login page
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    //redirects to idealist page
    @GetMapping("/")
    public String index() {
        return "redirect:idealist";
    }

    //returns idealist page which consist of all the ideas
    @GetMapping("/idealist")
    public String ideaList(Model model) {
        model.addAttribute("ideas", ideaRepository.findAll());
        return "idealist";
    }

    //returns the myideas page which consists of the ideas submitted by the current user
    @GetMapping("/myideas")
    public String myIdeas(Model model) {
        //returns current authentication
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        //returns the name of the current user
        String current = authentication.getName();

        model.addAttribute("ideas", userRepository.findByUsername(current).getIdeas());
        return "myideas";
    }

    /*
    returns addidea page where users can add ideas
    It sends a new instance of an Idea to the "addidea" page
     */
    @GetMapping("/addidea")
    public String addIdea(Idea idea, Model model) {
        model.addAttribute("idea", idea);
        return "addidea";
    }

    /*
    Finds the authenticated user and sets it as ideas author.
    Also saves the added idea to a repository and then redirects to idealist page.
     */
    @PostMapping("/save")
    public String save(Idea idea) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String current = authentication.getName();
        idea.setUser(userRepository.findByUsername(current));

        ideaRepository.save(idea);
        return "redirect:/idealist";
    }

    //Deletes the selected idea from the repository  and then redirects to idealist page
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

    /*
    Returns Idea that contains the requested parameter in their name in JSON format.
    For developers that want to get Ideas that contain certain words in their name in JSON format.
     */
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
