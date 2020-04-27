package fr.khaled.dev.SpringbootJpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.khaled.dev.SpringbootJpa.model.Alien;

public interface AlienRepo extends CrudRepository<Alien, Integer> {

	List<Alien> findByTech(String tech);
	
	List<Alien> findByAidGreaterThan(int aid);
	
	@Query("FROM Alien WHERE tech = ?1 ORDER BY aname")
	List<Alien> findByTechSorted(String tech);
}
