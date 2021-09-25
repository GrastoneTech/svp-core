package tech.grastone.svpcore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tech.grastone.svpcore.entities.PostEntity;

public interface PostRepository extends JpaRepository<PostEntity, String>{

}
