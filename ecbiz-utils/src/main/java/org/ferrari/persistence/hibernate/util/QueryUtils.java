 /**     
   * @create.date: 2009-5-7 上午09:18:08
   * @author: yuxinrong
   * @company: 车马驿站
   * @see:org.ferrari.exception 
   */ 
package org.ferrari.persistence.hibernate.util;



import org.hibernate.Query;

/**  
* @create.date: 2009-5-7 上午09:18:08     
* @comment: <p>TODO</p>
* @see: org.ferrari.persistence.hibernate.util
* @author: yuxinrong
* @modify.by: yuxinrong
* @modify.date: 2009-5-7 上午09:18:08
*/
public class QueryUtils {
	
	
	/**
	 * 
	 * @comment: <p>please description this method to do</p>  
	 * @create.date: 2009-5-7 ( 上午09:19:00 )
	 * @author: yuxinrong
	 * @param entity
	 * @see: <h1>org.ferrari.exception.cccc.update</h1>
	 * @modified.by: <if there modified by others,then write author name>
	 * @modified.date: <modified date time>
	 */
	public static String buildDianQL(Condition[] conditions) {
		
		

		StringBuffer ql = new StringBuffer("");
		if (conditions != null && conditions.length > 0) {
			ql.append(" where");
			for (int i = 0; i < conditions.length; i++) {
				if (conditions[i].getLogic() != null && i != 0)
					ql.append(" " + conditions[i].getLogic().getName());

				if (conditions[i].getLogic() != null) {
					if (!"AND".equals(conditions[i].getLogic().getName())) {
						
						String propertyName = conditions[i].getEnityPropertyName();
						
						if(propertyName!=null&&propertyName.startsWith("upper")){
							int num = propertyName.indexOf("upper(");
							ql.append(" "+propertyName.substring(0, num+6)+"o."+propertyName.substring(num+6));
						}else{
							ql.append(" o." + conditions[i].getEnityPropertyName());
						}
						
						ql.append(" " + conditions[i].getRelation().getName());
					}
				} else {
					String propertyName = conditions[i].getEnityPropertyName();
					
					if(propertyName!=null&&propertyName.startsWith("upper")){
						int num = propertyName.indexOf("upper(");
						ql.append(" "+propertyName.substring(0, num+6)+"o."+propertyName.substring(num+6));
					}else{
						ql.append(" o." + conditions[i].getEnityPropertyName());
					}
					
					ql.append(" " + conditions[i].getRelation().getName());
				}
				if (!conditions[i].getRelation().getName().startsWith("is")) {
//					if (!"is not null".equals(conditions[i].getRelation()
//							.getName()))
//					{
						if("in".equals(conditions[i].getRelation().getName()))
						{
							
							ql.append(" " + conditions[i].getQueryIn());
						}
						else
						{
						ql.append(" " + " ?");
						}
				//	}	
				}
			}
		}

//		return ql.toString();
		
		
		
		

/*		StringBuffer ql = new StringBuffer("");
		if (conditions != null && conditions.length > 0) {
			ql.append(" where");
			for (int i = 0; i < conditions.length; i++) {
				if (conditions[i].getLogic() != null && i != 0)
					ql.append(" " + conditions[i].getLogic().getName());
				

				if (conditions[i].getLogic() != null) {
					if (!"AND".equals(conditions[i].getLogic().getName())) {
						ql.append(" o." + conditions[i].getEnityPropertyName());
						ql.append(" " + conditions[i].getRelation().getName());
					}
				} else {
					ql.append(" o." + conditions[i].getEnityPropertyName());
					ql.append(" " + conditions[i].getRelation().getName());
				}
				if (!"is null".equals(conditions[i].getRelation().getName())) {
					if (!"is not null".equals(conditions[i].getRelation()
							.getName()))
						ql.append(" " + " ?");
				}
			}
		}*/
        //System.out.println("对应的sql:"+ql.toString());
		return ql.toString();
	}
/*
	public static String buildMaoQL(Condition[] conditions) {

		
		StringBuffer ql = new StringBuffer("");
		if (conditions != null && conditions.length > 0) {
			ql.append(" where");
			for (int i = 0; i < conditions.length; i++) 
	{
			
		if (conditions[i].getLogic() != null && i != 0)
		{	
			ql.append(" " + conditions[i].getLogic().getName());	
		}	
			if (conditions[i].getLogicNot() != null)
				ql.append(" not ");
			
		if (conditions[i].getLogic() != null)
		{
		if(!"AND".equals(conditions[i].getLogic().getName()))
		{
			ql.append(" o."+conditions[i].getEnityPropertyName()+" ");
			ql.append(conditions[i].getRelation().getName());
			
		}
		}
		else
		{
			ql.append(" o."+conditions[i].getEnityPropertyName()+" ");
			ql.append(conditions[i].getRelation().getName());
		}
		if("in".equals(conditions[i].getRelation().getName()))
			{
				ql.append(" (:"+conditions[i].getEnityPropertyName()+") ");
			}
		else if("<=".equals(conditions[i].getRelation().getName())||"<".equals(conditions[i].getRelation().getName()))
		{
			ql.append(" :"+conditions[i].getEnityPropertyName()+"2 ");
		}
		else{
			if(!"is null".equals(conditions[i].getRelation().getName()))
			{	
			if(!"is not null".equals(conditions[i].getRelation().getName()))
			
			ql.append(" :"+conditions[i].getEnityPropertyName()+" ");
			}
			}
			
		
		}
		}
			
		return ql.toString();
	}*/

	public static void setQueryDianByCondition(int paramStartIndex,
			Query query, Condition[] conditions) {
		int j=0;
		if (conditions != null) {
			for (int i = 0; i < conditions.length; i++) {
				//conditions[i].getQueryValue() != null
				if (conditions[i].getQueryValue() != null) {
					if (conditions[i].getRelation() != null) {
						
	//	System.out.println("?"+(j)+"的位置对应的值："+conditions[i].getQueryValue());			
						
						if (conditions[i].getRelation().equals(
								RelationOperation.LIKE)) {
							query.setParameter(j + paramStartIndex, "%"
									+ conditions[i].getQueryValue() + "%");
						} else if (conditions[i].getRelation().equals(
								RelationOperation.LEFTLIKE)) {
							query.setParameter(j + paramStartIndex, "%"
									+ conditions[i].getQueryValue());
						} else if (conditions[i].getRelation().equals(
								RelationOperation.NOT_LEFTLIKE)) {
							query.setParameter(j + paramStartIndex, "%"
									+ conditions[i].getQueryValue());
						} else if (conditions[i].getRelation().equals(
								RelationOperation.RIGHTLIKE)) {
							query.setParameter(j + paramStartIndex,
									conditions[i].getQueryValue() + "%");
						} else if (conditions[i].getRelation().equals(
								RelationOperation.NOT_RIGHTLIKE)) {
							query.setParameter(j + paramStartIndex,
									conditions[i].getQueryValue() + "%");
						} else {
							
//								System.out.println("is null".equals(conditions[i].getRelation().getName()));	
//								System.out.println("有 is 语法"+"is null".equals(conditions[i].getRelation().getName())+conditions[i].getRelation().getName().startsWith("is"));
							if(conditions[i].getQueryValues()!=null)
							{
//								System.out.println("进来的是第"+i+" 个 condition ");
								for(int m=0;m<conditions[i].getQueryValues().length;m++)
								{
//									
//									System.out.println("in 语法 ?"+(m)+"的位置对应的值："+conditions[i].getQueryValues()[m]);	
									query.setParameter(j + paramStartIndex,
											conditions[i].getQueryValues()[m]);
									j++;
								}
							}
							else
							{
								
								
//								System.out.println("在QueryValue不为空的情况下:执行了第"+i +" 个contion  对应J: " +j);
							query.setParameter(j + paramStartIndex,
									conditions[i].getQueryValue());
							}
						}
					} else {
//						System.out.println("最后的else");
						query.setParameter(j + paramStartIndex, conditions[i]
								.getQueryValue());
						
					}
					if(conditions[i].getQueryValues()==null)
					j++;
				}
			
			}
		}
	}
	
	
/*	

	@SuppressWarnings("unchecked")
	public static void setQueryMaoByCondition(int paramStartIndex,
			Query query, Condition[] conditions) {
		if (conditions != null) {
			for (int i = 0; i < conditions.length; i++) {
				if (conditions[i].getQueryValue() != null) {
					if (conditions[i].getRelation() != null) {
						if (conditions[i].getRelation().equals(
								RelationOperation.LIKE)) {
							query.setParameter(conditions[i]
									.getEnityPropertyName(), "%"
									+ conditions[i].getQueryValue() + "%");
						} else if (conditions[i].getRelation().equals(
								RelationOperation.LEFTLIKE)) {
							query.setParameter(conditions[i]
									.getEnityPropertyName(), "%"
									+ conditions[i].getQueryValue());
						} else if (conditions[i].getRelation().equals(
								RelationOperation.RIGHTLIKE)) {
							query.setParameter(conditions[i]
									.getEnityPropertyName(), conditions[i]
									.getQueryValue()
									+ "%");
						} else {

							if (conditions[i].getQueryValue() instanceof Collection) {
								query.setParameterList(conditions[i]
										.getEnityPropertyName(),
										(Collection) conditions[i]
												.getQueryValue());
							} else if (conditions[i].getQueryValue() instanceof Object[]) {
								query.setParameterList(conditions[i]
										.getEnityPropertyName(),
										(Object[]) conditions[i]
												.getQueryValue());
							} else {
							
								query.setParameter(conditions[i]
										.getEnityPropertyName(), conditions[i]
										.getQueryValue());
							}

						}
					} else {

						if (conditions[i].getQueryValue() instanceof Collection) {
							query.setParameterList(conditions[i]
									.getEnityPropertyName(),
									(Collection) conditions[i].getQueryValue());
						} else if (conditions[i].getQueryValue() instanceof Object[]) {
							query.setParameterList(conditions[i]
									.getEnityPropertyName(),
									(Object[]) conditions[i].getQueryValue());
						} else {
							
							query.setParameter(conditions[i]
									.getEnityPropertyName(), conditions[i]
									.getQueryValue());
						}

					}
				}
			}
		}

	}*/
}
