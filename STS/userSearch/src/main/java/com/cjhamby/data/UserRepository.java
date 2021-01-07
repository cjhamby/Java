package com.cjhamby.data;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/*
 * why write methods to access the database, when Spring does it for me? :-)
 * the following methods are implemented by Spring Crud Repository
 * which magically handles Hibernate session management
 * 
 *  - long count()
 *  - void delete( T )
 *  - void deleteAll()
 *  - void deleteAll( Iterable <T> )
 *  - void deleteById( ID ) 
 *  - boolean existsById( ID )
 *  - Iterable <T> findAll()
 *  - Iterable <T> findAllById( Iterable <ID> )
 *  - Optional <T> findById( ID )
 *  - <S extends T> save ( S )
 *  - Iterable<S> saveAll ( Iterable<S> ) 
 */

@Repository
public interface UserRepository extends CrudRepository<MyUser, Integer>{

}
