package fi.project.projectidea.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface IdeaRepository extends CrudRepository<Idea,Long> {
    Idea findByName(String name);
}
