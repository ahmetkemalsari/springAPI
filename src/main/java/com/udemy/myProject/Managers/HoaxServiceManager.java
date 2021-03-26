package com.udemy.myProject.Managers;

import com.udemy.myProject.Models.Hoax;
import com.udemy.myProject.Models.User;
import com.udemy.myProject.Repositories.HoaxRepository;
import org.hibernate.validator.internal.metadata.PredefinedScopeBeanMetaDataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

@Service
public class HoaxServiceManager implements HoaxService {
    @Autowired
    HoaxRepository repository;
    @Autowired
    UserServiceManager userServiceManager;

    @Override
    public Page<Hoax> getHoaxes(Pageable page) {

        return repository.findAll(page);
    }

    @Override
    public Hoax save(Hoax hoax, User user) {
        hoax.setTimestamp(new Date());
        hoax.setUser(user);
        return repository.save(hoax);
    }
    @Override
    public Page<Hoax> getHoaxesOfUser(String username, Pageable page) {
        User inDb = userServiceManager.getByUsername(username);
        return  repository.findByUser(inDb,page);
        }

    @Override
    public Page<Hoax> getOldHoaxes(long id, String username, Pageable page) {
        Specification<Hoax> specification = idLessThan(id);
        if(username != null){
            User inDb = userServiceManager.getByUsername(username);
            specification.and(userIs(inDb));

        }

        return repository.findAll(specification,page);
    }



    @Override
    public long getNewHoaxesCount(long id, String username) {
        Specification<Hoax> specification = idGreaterThan(id);
        if(username != null){
            User inDb = userServiceManager.getByUsername(username);
            specification.and(userIs(inDb));

        }
        return repository.count(specification);
    }


    @Override
    public List<Hoax> getNewHoaxes(long id, String username, Sort sort) {
        Specification<Hoax> specification = idGreaterThan(id);
        if(username != null){
            User inDb = userServiceManager.getByUsername(username);
            specification.and(userIs(inDb));

        }
        return repository.findAll(specification,sort);

    }


    Specification<Hoax> idLessThan(long id){
        return ( root,  query, criteriaBuilder) -> {
                return criteriaBuilder.lessThan(root.get("id"),id);
            };
        };
    Specification<Hoax> userIs(User user){
        return ( root,  query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("user"),user);
        };

    };
    Specification<Hoax> idGreaterThan(long id){
        return ( root,  query, criteriaBuilder) -> {
            return criteriaBuilder.greaterThan(root.get("id"),id);
        };
    };
    }


