package br.com.devdojo.examgenerator.persistence.repository;

import br.com.devdojo.examgenerator.persistence.model.Course;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends PagingAndSortingRepository<Course, Long> {
    @Query("select c from Course c where  c.id = ?1 and c.professor = ?#{principal.professor}")
    @Override
    Optional<Course> findById(Long id);

    @Query("select c from Course c where c.name like %?1% and c.professor = ?#{principal.professor}")
    List<Course> listCourses(String name);

    @Override
    @Query("delete from Course  c where c.id = ?1 and c.professor = ?#{principal.professor}")
    @Modifying
    void deleteById(Long id);

    @Query("delete from Course c where c = ?1 and c.professor = ?#{principal.professor}")
    @Modifying
    void delete(Course course);
}