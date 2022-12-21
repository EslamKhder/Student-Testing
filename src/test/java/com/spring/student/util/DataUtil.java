package com.spring.student.util;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.student.model.Student;


@Service
public class DataUtil {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void delete(Object... entities) {
        for (Object entity : entities) {
            if (entity instanceof List) {
                delete((List<?>) entity);
            } else {
                delete(entity);
            }
        }
    }

    @Transactional
    private void delete(List<?> entityList) {
        for (Object entity : entityList) {
            delete(entity);
        }
    }

    @Transactional
    private void delete(Object entity) {
        try {
            if (entity instanceof Student) {
                final Student baseEntity = (Student) entityManager
                                .find(entity.getClass(), ((Student) entity).getId());
                entityManager.remove(baseEntity);
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
