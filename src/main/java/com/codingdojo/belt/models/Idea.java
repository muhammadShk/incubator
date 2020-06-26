package com.codingdojo.belt.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;




@Entity
@Table(name="ideas")
public class Idea {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Size(min=3, message="Name cannot be empty or shorter than 3 letters")
	private String name;	

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
	        name = "likes", 
	        joinColumns = @JoinColumn(name = "idea_id"), 
	        inverseJoinColumns = @JoinColumn(name = "user_id")
	)
	private List<User> likedBy;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
    private User creator;
	
	@Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<User> getLikedBy() {
		return likedBy;
	}
	public void setLikedBy(List<User> likedBy) {
		this.likedBy = likedBy;
	}
	public User getCreator() {
		return creator;
	}
	public void setCreator(User creator) {
		this.creator = creator;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
}
