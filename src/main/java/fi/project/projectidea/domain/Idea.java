package fi.project.projectidea.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
//Data annotation from the Project Lombok library creates getters and setters for every argument
@Data
//FieldDefaults annotation sets the visibility of field to private
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Idea {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    long id;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "description", nullable = false)
    String description;

    @Column(name = "difficulty", nullable = false)
    String difficulty;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "uid")
    User user;

    public Idea() {
    }

    public Idea(User user) {
        this.user = user;
    }

    public Idea(String name, String description, String difficulty, User user) {
        this.name = name;
        this.description = description;
        this.difficulty = difficulty;
        this.user = user;
    }
}
