package fr.khaled.dev.demospringbootrestapi.repository;

import org.springframework.data.repository.CrudRepository;

import fr.khaled.dev.demospringbootrestapi.entity.Alien;

public interface AlienRepository extends CrudRepository<Alien, Integer> {

}
