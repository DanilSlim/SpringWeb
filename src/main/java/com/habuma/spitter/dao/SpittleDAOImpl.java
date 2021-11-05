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
import com.habuma.spitter.base.Spittle;

@Repository
@Transactional(transactionManager = "txManager")
public class SpittleDAOImpl implements SpittleDAO {
	
	
	private SessionFactory sessionFactory;
	
	@Autowired
	public SpittleDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}
	
	
	private Session getCurrentSession() {
		
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public void addSpittle(Spittle spittle) {
		getCurrentSession().save(spittle);

	}

	@Override
	public void editSpittle(Spittle spittle) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteSpittle(Spittle spittle) {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional(transactionManager = "txManager",propagation = Propagation.REQUIRED,readOnly = true)
	public List<Spittle> showAllSpittle() {
			
		Session session=getCurrentSession();
		
		@SuppressWarnings("unchecked")
		Query<Spittle> querySpittle=session.createQuery("select spittle from Spittle spittle");
		
		return querySpittle.getResultList();
		
	}

	@Override
	@Transactional(transactionManager = "txManager",propagation = Propagation.REQUIRED,readOnly = true)
	public List<Spittle> showNumSpittle(int num) {
		
		Session session=getCurrentSession();
		
		List<Spittle> spittles = null;
		
		if(num<0) {
			num=0;
		}
		
		
		
		@SuppressWarnings("unchecked")
		Query<Long> query=session.createQuery("select count(*) from Spittle spittle");
		
		Long allSpittlesCount=query.uniqueResult();
		
		int lastRecentSpittles= 0;
		
		if(allSpittlesCount-num>=0) lastRecentSpittles=(int) (allSpittlesCount-num);
		else 
			lastRecentSpittles = 0;
		
		if(lastRecentSpittles>0) {
			
			@SuppressWarnings("unchecked")
			Query<Spittle> querySpittle=session.createQuery("select spittle from Spittle spittle");
			
			querySpittle.setFirstResult(lastRecentSpittles);
			querySpittle.setMaxResults(num);
			
			spittles=querySpittle.getResultList();
			
		}
		
		else if(lastRecentSpittles==0){
			
			@SuppressWarnings("unchecked")
			Query<Spittle>querySpittle=session.createQuery("select spittle from Spittle spittle");
			
			spittles=querySpittle.getResultList();
		}
		
		else if (lastRecentSpittles<0){
			
			//Тут надо выбросить исключение
		}
		
		return spittles;
	}

	@Override
	public Spittle findSpittleById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Spittle findSpittleByMessage(String message) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Spittle> findSpittlesBySpitter(Spitter spitter) {
		
		
		@SuppressWarnings("unchecked")
		Query<Spittle> querySpittle=getCurrentSession().createQuery(
				        "select spittle from Spittle spittle where spittle.spitter_id = :spitterId");
		
		querySpittle.setParameter("spitterId", spitter.getId());
		
		return querySpittle.getResultList();
	}

}
