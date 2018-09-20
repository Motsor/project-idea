package fi.project.projectidea.domain;

import org.springframework.data.repository.CrudRepository;

public interface IdeaRepository extends CrudRepository<Idea,Long> {
}
