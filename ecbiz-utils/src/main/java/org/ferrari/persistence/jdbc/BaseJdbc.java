/**
 * 
 */
package org.ferrari.persistence.jdbc;

import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.ferrari.persistence.jdbc.iface.JdbcDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
/**
 * 
   * @create.date: 2009-5-7 上午09:40:46     
   * @comment: <p>TODO</p>
   * @see: org.ferrari.persistence.jdbc
   * @author: yuxinrong
   * @modify.by: yuxinrong
   * @modify.date: 2009-5-7 上午09:40:46
 */
@Transactional
public class BaseJdbc  implements JdbcDao{
	
	
	@SuppressWarnings("unused")
	private DataSource dataSource;
	private JdbcTemplate jdbcTemple;
	
	@Resource
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		jdbcTemple = new JdbcTemplate(dataSource);
	}

	
	
	public JdbcTemplate getJdbcTemple() {
		return jdbcTemple;
	}

    public   Object getEntity(Integer id){ return null;}
	public  void update(Object bean){}
	public  void save(Object bean){}
	public void delete(Integer id){}
	public  List<?> getList(){return null;}
	public  List<?> getList(String name){return null;}
	public  List<?> getList(final Object bean){ return null;}
	public  int getCount(){ return 0;}
	public List<?> getList(String name,Integer index,Integer enddex)
	{
		return null;
	}
}
