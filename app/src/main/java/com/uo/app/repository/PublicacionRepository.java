package com.uo.app.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.uo.app.models.Publicacion;

@Repository
public interface PublicacionRepository extends MongoRepository<Publicacion, String>{
    List<Publicacion> findByAutor(String autor);
}
