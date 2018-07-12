package com.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.model.User;


/**
 * @author Administrator
 *用注解注册bean到ioc容器中，
 *拿到hibernatetemplate。
 *ht为框架模板，提供了
 *delete(Object entity): 删除指定持久化实例。

    deleteAll(Collection entities): 删除集合内全部持久化类实例。

    find(String queryString): 根据 HQL 查询字符串来返回实例集合。

    findByNamedQuery(String queryName): 根据命名查询返回实例集合。

    load或get(Classentity Class,Serializable id): 根据主键加载特定持久化类的实例。

    save(Object entity): 保存新的实例。

    saveOrUpdate(Object entity): 根据实例状态，选择保存或者更新。

    update(Object entity): 更新实例的状态，要求entity 是持久状态。

    setMaxResults(intmax Results): 设置分页的大小。
    
 *若超出模板的数据操作，需用户自定义sql语句
 *
 *
 */
@Component
public class UserDAO {
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	

	
	/**
	 * @param zjy
	 * 增删改查相关对于数据表的操作，返回的是相关操作的结果
	 * 用户自定义sql语句在DAO层实现
	 */
	public void save(User user){
		hibernateTemplate.save(user);
	}
	public void update(User user){
		hibernateTemplate.update(user);
	}
	public void delete(Integer id){
		User user=getUser(id);
		hibernateTemplate.delete(user);
	}
	public User getUser(Integer id){
		return (User)this.hibernateTemplate.load(User.class,id);
	}
	public List<User> findByUsername(String username){
		return (List<User>) hibernateTemplate.find("from User u where u.username = ?",username);
	}
	public List<User> queryByUsername(String username){
		return (List<User>) hibernateTemplate.find("from User u where u.username like ?","%"+username+"%");
	}
	
	public List<User> findAllUsers(){
		 return this.getHibernateTemplate().find("from User order by id");  
	}

}
