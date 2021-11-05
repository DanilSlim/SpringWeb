package com.habuma.spitter.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.habuma.spitter.base.Spitter;

@Repository
@Transactional(transactionManager = "txManager")
public class SpitterDAOImpl implements SpitterDAO {
	
	private SessionFactory sessionFactory;
	
	
	@Autowired
	public  SpitterDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}
	
	
	private Session getCurrentSession() {
		
		return this.sessionFactory.getCurrentSession();
	}
	

	@Override
	@Transactional(transactionManager = "txManager", propagation = Propagation.REQUIRED, readOnly = false)
	public void addSpitter(Spitter spitter) {
		getCurrentSession().save(spitter);

	}

	@Override
	public void editSpitter(Spitter spitter) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteSpitter(Spitter spitter) {
		getCurrentSession().delete(spitter);

	}

	
	@Override
	@Transactional(transactionManager = "txManager", propagation = Propagation.REQUIRED, readOnly = true)
	public List<Spitter> showAllSpitters() {
		@SuppressWarnings("unchecked")
		Query<Spitter> query=getCurrentSession().createQuery("select spitter from Spitter spitter");
		List<Spitter> sp=query.getResultList();
		
		@SuppressWarnings("unchecked")
		Query<Long> queryLong=getCurrentSession().createQuery("select count(*) from Spitter ");
		
		Long res=queryLong.uniqueResult();
		
		System.out.println("Spitters count: "+res);
		
		query.setFirstResult(1);
		
		query.setMaxResults(2);
		
		List<Spitter>sp1=query.getResultList();
		
		for(Spitter spit:sp1) {
			System.out.println ("Spitter ID: "+spit.getId()+" Spitter username: "+spit.getUserName());
		}
		return sp;
	}

	@Override
	public Spitter findSpitterById(Long id) {
		
		return getCurrentSession().get(Spitter.class, id);
	}

	@Override
	public Spitter findSpitterByUserName(String userName) {
		
		@SuppressWarnings("unchecked")
		Query<Spitter> query=getCurrentSession().createQuery(
				"select spitter from Spitter spitter where spitter.userName = :userName");
		
		query.setParameter("userName", userName);
		
		return query.uniqueResult();
	}


	@Override
	public List<Spitter> showNumSpitters(int Num) {
		// TODO Auto-generated method stub
		return null;
	}

}
