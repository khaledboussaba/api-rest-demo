package fr.khaled.dev.demospringbootrestapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.khaled.dev.demospringbootrestapi.entity.Alien;
import fr.khaled.dev.demospringbootrestapi.repository.AlienRepository;

@RestController
@RequestMapping("/aliens")
public class AlienResource {
	
	@Autowired
	private AlienRepository repo;

	@GetMapping
	public List<Alien> getAliens() {
		List<Alien> aliens = (List<Alien>)repo.findAll();
		return aliens;
	}
	
	@GetMapping("/alien/{id}")
	public Optional<Alien> getAlien(@PathVariable int id) {
		Optional<Alien> alien = repo.findById(id);
		return alien;
	}
	
	@PostMapping("/alien")
	public Alien createAlien(@RequestBody Alien alien) {
		return repo.save(alien);
	}
	
	@PutMapping("/alien/{id}")
	public Alien updateAlien(@RequestBody Alien newAlien, @PathVariable int id) {
		return repo.findById(id)
				.map(alien -> {
					alien.setName(newAlien.getName());
					alien.setPoints(newAlien.getPoints());
					return repo.save(alien);
				})
				.orElseGet(() -> {
					newAlien.setId(id);
					return repo.save(newAlien);
				});
	}
	
	@DeleteMapping("/alien/{id}")
	public void deleteAlien(@PathVariable int id) {
		repo.deleteById(id);
	}
	
}
