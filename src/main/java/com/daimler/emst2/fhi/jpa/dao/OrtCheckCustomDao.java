package com.daimler.emst2.fhi.jpa.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Component;

import com.daimler.emst2.fhi.jpa.model.OrtCheck;

@Component
public class OrtCheckCustomDao {

    @PersistenceContext
    private EntityManager em;

    public List<OrtCheck> getSendAndCancelSendChecks(List<String> sendOrStornoSufixes) {
        /*
        List<OrtCheck> resultList;
        DetachedCriteria criteria = createQueryCriteria();
        List<String> sendOrStornoSufixes = OrtCheckEnum.getSendOrStornoSufixes();
        List<Criterion> critList = new ArrayList<Criterion>();
        for (String checkSuffix : sendOrStornoSufixes) {
            Criterion criterion = Restrictions.ilike(IOrtCheck.ORT_CHECK, checkSuffix, MatchMode.END);
            critList.add(criterion);
        }
        Criterion orCriterion = HibernateDaoHelper.createOrCriterion(critList);
        criteria.add(orCriterion);
        resultList = getHibernateTemplate().findByCriteria(criteria, 0, 10000);
        return resultList;
        */

        List<OrtCheck> resultList;
        CriteriaBuilder cb = em.getCriteriaBuilder();
        List<Predicate> critList = new ArrayList<>();
        
        CriteriaQuery<OrtCheck> query = cb.createQuery(OrtCheck.class);
        Root<OrtCheck> ortCheck = query.from(OrtCheck.class);
        Path<String> ortCheckPath = ortCheck.get("ortCheck");

        for (String checkSuffix : sendOrStornoSufixes) {
            critList.add(cb.like(cb.lower(ortCheckPath), "%" + checkSuffix.toLowerCase()));
        }

        query.select(ortCheck)
                .where(cb.or(critList.toArray(new Predicate[critList.size()])));

        resultList = em.createQuery(query)
                .setFirstResult(0)
                .setMaxResults(10000)
                .getResultList();

        return resultList;
    }
}