package edu.hm.ba.kongo.infrastructure.auth.search;

import edu.hm.ba.kongo.infrastructure.auth.entities.Permission;
import edu.hm.ba.kongo.infrastructure.auth.repositories.PermissionRepository;
import de.muenchen.service.QueryService;
import org.hibernate.search.exception.EmptyQueryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/*
 * This file will be overwritten on every change of the model!
 * This file was automatically generated by GAIA.
 */
@BasePathAwareController
@ExposesResourceFor(Permission.class)
@RequestMapping("/permissions/search")
public class PermissionSearchController {

    @Autowired
    QueryService service;

    @Autowired
    PermissionRepository repository;

    @RequestMapping(method = RequestMethod.GET, value = "findFullTextFuzzy")
    @ResponseBody
    public ResponseEntity<?> findFullTextFuzzy(PersistentEntityResourceAssembler assembler, @Param("q") String q) {
        if (q == null)
            q = "";

        String[] annotatedFields = Stream
                .of(Permission.class.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(org.hibernate.search.annotations.Field.class))
                .map(Field::getName)
                .toArray(size -> new String[size]);

        Stream<Permission> permissionStream;
        try {
            permissionStream = service.query(q, Permission.class, annotatedFields).stream();
        } catch (EmptyQueryException e) {
            permissionStream = StreamSupport.stream(repository.findAll().spliterator(), false);
        }

        final List<PersistentEntityResource> collect = permissionStream.map(assembler::toResource).collect(Collectors.toList());
        return new ResponseEntity<Object>(new Resources<>(collect), HttpStatus.OK);
    }
}

