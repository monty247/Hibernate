package com.jbk.product.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.persistence.criteria.CriteriaBuilder.In;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.jbk.product.confige.HibernateUtility;
import com.jbk.product.entity.Product;

public class ProductDao {

	private SessionFactory sessionFactory;

	public ProductDao() {
		sessionFactory = HibernateUtility.getSessionFactory();
	}

	public String saveProduct(Product product) {
		Session session = null;
		boolean isAdded = false;
		try {
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.save(product);
			transaction.commit();
			isAdded = true;
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (session != null) {
				session.close();
			}
		}
		if (isAdded) {
			return "saved...";
		} else {
			return "not saved..";
		}
	}

	public Product getProductById(int productId) {
		Session session = null;
		Product product = null;
		try {
			session = sessionFactory.openSession();
			product = session.get(Product.class, productId);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {

				session.close();
			}

		}
		return product;

	}

	public String deleteProductById(int productId) {
		Session session = null;
		String msg = null;

		try {

			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			Product product = session.get(Product.class, productId);
			if (product != null) {
				session.delete(product);
				transaction.commit();
				msg = "deleted";
			} else {
				msg = "product not exists with Id=" + productId;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return msg;
	}

	public List<Product> getAll() {
		Session session = null;
		List<Product> list = null;

		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Product.class);
			list = criteria.list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return list;

	}

	public List<Product> getCriteriaOfProducts() {
		Session session = null;
		List<Product> list = null;
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Product.class);

			criteria.setFirstResult(0);
			criteria.setMaxResults(3);
			// criteria.addOrder(Order.asc("productPrice"));
			// criteria.hashCode();
			list = criteria.list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();

			}
		}

		return list;
	}

	public List<Product> getResProduct() {
		Session session = null;
		List<Product> list = null;
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Product.class);
			// criteria.add(Restrictions.eq("productName" , "pen"));
			// criteria.add(Restrictions.between("productPrice", 10d, 100d));
//				Map<String, Object>map=new HashMap<>();
//				map.put("productName", "pen");
//				map.put("productPrice", 15d);
//				criteria.add(Restrictions.allEq(map));
			// criteria.add(Restrictions.idEq(101));
//				criteria.add(Restrictions.)
//				Criterion price=Restrictions.eq("productPrice", 20d);
//				Criterion name=Restrictions.eq("productName", "pen");
//				criteria.add(Restrictions.or(price,name));
			// criteria.add(Restrictions.in("productPrice", 10d,20d,400d));
			// criteria.add(Restrictions.ge("productPrice", 20d));
			// criteria.add(Restrictions.le("productPrice", 50d));
			criteria.add(Restrictions.like("productName", "%p%"));

			list = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();

			}
		}
		return list;
	}

	public double projectionProduct() {
		Session session = null;
		double sum = 0;
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Product.class);
			criteria.setProjection(Projections.sum("productPrice"));
			// criteria.setProjection(Projections.sum("productQnt"));
			// criteria.setProjection(Projections.avg("productPrice"));
//		criteria.setProjection(Projections.max("productPrice"));
			// criteria.setProjection(Projections.min("productPrice"));

			List<Double> list = criteria.list();
			sum = list.get(0);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();

			}
		}
		return sum;
	}

	public String updateProduct(Product product) {
		Session session = null;
		boolean isUpdated = false;
		try {
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			Product dbproduct=session.get(Product.class, product.getProductId());
			if(dbproduct!=null) {
				session.update(product);
				transaction.commit();
				isUpdated=true;

			}
					} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		if (isUpdated) {
			return "product updated";
		} else {
			return "product not exists";
		}

	}
}
