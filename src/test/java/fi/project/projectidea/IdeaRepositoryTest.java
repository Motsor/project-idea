package fi.project.projectidea;

import fi.project.projectidea.domain.Idea;
import fi.project.projectidea.domain.IdeaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class IdeaRepositoryTest {

    //There's already two objects in ideaRepository created by data.sql file
    @Autowired
    private IdeaRepository ideaRepository;

    //Tests the creation and saving of an instance of Idea class
    @Test
    public void createAndSave() {
        //Creates a new instance of the class Idea
        Idea idea = new Idea("Test", "This is a test", "Beginner");

        //Saves the created instance to ideaRepository
        ideaRepository.save(idea);

        //Checks if the values given in the creation of the instance are given correctly
        assertThat(idea.getName()).isEqualTo("Test");

        //Checks if the created instance is saved in the repository correctly
        assertThat(ideaRepository.findByName("Test")).isNotNull();
    }

    //Tests the searching of a repository
    @Test
    public void search() {
        Idea idea = new Idea("Test", "This is a test", "Beginner");
        ideaRepository.save(idea);
        assertThat(ideaRepository.findByName("Test").getName()).isEqualTo("Test");
    }

    //Tests the delete functionality of the repository
    //Note! The size of the idea repo is essentially two
    @Test
    public void delete() {
        Idea idea = new Idea("Test", "This is a test", "Beginner");
        ideaRepository.save(idea);
        ideaRepository.delete(idea);
        List<Idea> ideas = (List<Idea>) ideaRepository.findAll();
        assertThat(ideas).hasSize(2);
    }

}