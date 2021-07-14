package org.simple.lifeiseasy.dao;

import java.util.List;
import java.util.UUID;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
//import org.hibernate.query.Query;
import org.simple.lifeiseasy.hibernatemultitenancy.models.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		Session session = sessionFactory.getCurrentSession();
		session.setDefaultReadOnly(true);
		session.setFlushMode(FlushMode.MANUAL);
		return session;
	}

	/*
	 * public List<UserEntity> getAll() { CriteriaBuilder cb =
	 * getSession().getCriteriaBuilder(); CriteriaQuery<UserEntity> criteriaQuery =
	 * cb.createQuery(UserEntity.class); Root<UserEntity> root =
	 * criteriaQuery.from(UserEntity.class); criteriaQuery.select(root);
	 * Query<UserEntity> query = getSession().createQuery(criteriaQuery); return
	 * query.getResultList(); }
	 * 
	 * public UserEntity getById(String id) { CriteriaBuilder cb =
	 * getSession().getCriteriaBuilder(); CriteriaQuery<UserEntity> criteriaQuery =
	 * cb.createQuery(UserEntity.class); Root<UserEntity> root =
	 * criteriaQuery.from(UserEntity.class); criteriaQuery.select(root);
	 * criteriaQuery.where(cb.equal(root.get("uuid"), UUID.fromString(id)));
	 * Query<UserEntity> query = getSession().createQuery(criteriaQuery); return
	 * query.uniqueResult(); }
	 */
	
	@Transactional
	public UserEntity getById1(String id) {
	    Criteria criteria = sessionFactory.getCurrentSession().createCriteria(UserEntity.class);
	    criteria.add( Restrictions.eq("uuid", UUID.fromString(id)));
	    criteria.setCacheable(true);
	    return (UserEntity)criteria.uniqueResult();
	}
	
	public List<UserEntity> getAll1() {
	    Criteria criteria = sessionFactory.getCurrentSession().createCriteria(UserEntity.class);
	    criteria.setCacheable(true);
	    return (List<UserEntity>)criteria.list();
	}

}
