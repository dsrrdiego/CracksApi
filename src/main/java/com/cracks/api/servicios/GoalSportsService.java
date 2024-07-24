package com.cracks.api.servicios;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Service
public class GoalSportsService {
    @PersistenceContext
    private EntityManager entityManager;

    public ArrayList<String> getEventsGoals(Long id) {
        String jpql = "SELECT i.goal_sport_interest.title FROM Interest i WHERE TYPE(i.owner) = InterestEvent AND TYPE(i.goal_sport_interest)=Goals AND i.owner.event.id=:id";
        TypedQuery<String> query = entityManager.createQuery(jpql, String.class);
        query.setParameter("id", id);
        return (ArrayList<String>) query.getResultList();
    }

    public ArrayList<String> getEventsSports(Long id) {
        String jpql = "SELECT i.goal_sport_interest.title FROM Interest i WHERE TYPE(i.owner) = InterestEvent AND TYPE(i.goal_sport_interest)=Sports AND i.owner.event.id=:id";
        TypedQuery<String> query = entityManager.createQuery(jpql, String.class);
        query.setParameter("id", id);
        return (ArrayList<String>) query.getResultList();
    }
}
