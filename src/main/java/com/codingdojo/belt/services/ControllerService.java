package com.codingdojo.belt.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.codingdojo.belt.models.Idea;
import com.codingdojo.belt.models.User;
import com.codingdojo.belt.repositories.UserRepository;


@Service
public class ControllerService {
	private final UserRepository userRepo;
	private final IdeaRepository ideaRepo;
	
	
	public ControllerService(UserRepository userRepo, IdeaRepository ideaRepo) {
		this.userRepo = userRepo;
		this.ideaRepo = ideaRepo;
	}
	
	

/////////////////////////////////////////////////////////////////////////////////////////
//					CREATE
/////////////////////////////////////////////////////////////////////////////////////////	
	public Idea createIdea(@Valid Idea idea, Long creatorId) {
		User creator=findUserById(creatorId);
		idea.setCreator(creator);
		return ideaRepo.save(idea);
	}
	
/////////////////////////////////////////////////////////////////////////////////////////
//					READ
/////////////////////////////////////////////////////////////////////////////////////////	
	
	public List<Idea> allIdeas() {
		return ideaRepo.findAll();
	}
	
	public Idea findIdeaById(Long ideaId) {
		Optional<Idea> idea = ideaRepo.findById(ideaId);
    	if(idea.isPresent()) {
    		return idea.get();
    	} else {
    		return null;
    	}
	}

	public User findUserById(Long id) {
    	Optional<User> user = userRepo.findById(id);
    	if(user.isPresent()) {
    		return user.get();
    	} else {
    		return null;
    	}
    }
	
/////////////////////////////////////////////////////////////////////////////////////////
// 					UPDATE
/////////////////////////////////////////////////////////////////////////////////////////
	
	public Idea updateIdea(@Valid Idea idea, Long userId) {
		User creator= findUserById(userId);
    	idea.setCreator(creator);
		return ideaRepo.save(idea);
	}

	
	public Idea addLikeToIdea(Long ideaId, Long userId) {
		User user= findUserById(userId);
		Idea idea= findIdeaById(ideaId);
		idea.getLikedBy().add(user);
		return ideaRepo.save(idea);
	}
	
	public Idea removeLikeFromIdea(Long ideaId, Long userId) {
		User user= findUserById(userId);
		Idea idea= findIdeaById(ideaId);
		idea.getLikedBy().remove(user);
		return ideaRepo.save(idea);
	}
	
	
/////////////////////////////////////////////////////////////////////////////////////////////////
//							DELETE
/////////////////////////////////////////////////////////////////////////////////////////////////
	public void deleteIdea(Long ideaId) {
		ideaRepo.deleteById(ideaId);
	}

}
