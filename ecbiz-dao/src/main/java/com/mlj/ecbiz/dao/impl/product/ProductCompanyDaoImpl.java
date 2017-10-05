package com.mlj.ecbiz.dao.impl.product;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.mlj.ecbiz.model.product.ProductCompany;
import com.mlj.ecbiz.dao.product.ProductCompanyDao;
import org.springframework.stereotype.Repository;
import com.chexun.base.framework.core.dao.impl.common.GenericDaoImpl;
import com.chexun.base.common.util.BeanMapConvertor;
import com.chexun.base.framework.core.entity.PageEntity;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
/**
 *
 * ProductCompany
 * User:
 * Date: 2017-10-06
 */
 @Repository("productCompanyDao")
public class ProductCompanyDaoImpl extends GenericDaoImpl implements ProductCompanyDao{

    public Long addProductCompany(ProductCompany productCompany) {
        return this.insert("com.mlj.ecbiz.model.product.ProductCompanyMapper.createProductCompany",productCompany);
    }
	public Long insertProductCompany(ProductCompany productCompany){
		return this.insert("com.mlj.ecbiz.model.product.ProductCompanyMapper.insertProductCompany",productCompany);
	}
    public Long deleteProductCompanyById(Long id){
        return this.delete("com.mlj.ecbiz.model.product.ProductCompanyMapper.deleteProductCompanyById",id);
    }
    public Long deleteProductCompanyByObj(ProductCompany productCompany){
        return this.delete("com.mlj.ecbiz.model.product.ProductCompanyMapper.deleteProductCompanyByObj",productCompany);
    }
    
	public Long deleteProductCompanyByIdList(List<Long > ids){
		return this.delete("com.mlj.ecbiz.model.product.ProductCompanyMapper.deleteProductCompanyByIdList",ids);
	}
    public Long updateProductCompany(ProductCompany productCompany) {
        return this.update("com.mlj.ecbiz.model.product.ProductCompanyMapper.updateProductCompany",productCompany);
    }
    
    public Long updateProductCompanyByObj(ProductCompany productCompany){
    	return this.update("com.mlj.ecbiz.model.product.ProductCompanyMapper.updateProductCompanyByObj",productCompany);
    }
    
    public Long updateProductCompanyByObjAndConditions(ProductCompany s,ProductCompany c){
    	Map<String,ProductCompany> map = new HashMap<String,ProductCompany>();
    	map.put("s",s);
    	map.put("c",c);
    	return this.update("com.mlj.ecbiz.model.product.ProductCompanyMapper.updateProductCompanyByObjAndConditions",map);
    }
	public void batchUpdateProductCompany(List<ProductCompany> records){
		this.update("com.mlj.ecbiz.model.product.ProductCompanyMapper.batchUpdateProductCompany",records);
	}
    public ProductCompany getProductCompanyById(Long id) {
        return this.selectOne("com.mlj.ecbiz.model.product.ProductCompanyMapper.getProductCompanyById",id);
    }
    
    public ProductCompany getProductCompanyByObj(ProductCompany productCompany) {
        return this.selectOne("com.mlj.ecbiz.model.product.ProductCompanyMapper.getProductCompanyByObj",productCompany);
    }

    
    public List<ProductCompany> getProductCompanyListByObj(ProductCompany productCompany){
        return this.selectList("com.mlj.ecbiz.model.product.ProductCompanyMapper.getProductCompanyListByObj",productCompany);
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<ProductCompany> getProductCompanyListPage(ProductCompany productCompany,Integer offset,Integer limit){
    	try {
			Map map = BeanMapConvertor.convertBean(productCompany);
			map.put("offset",offset);
    		map.put("limit",limit);
        	return this.selectList("com.mlj.ecbiz.model.product.ProductCompanyMapper.getProductCompanyListByMap",map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return null;
    }
    public Integer getProductCompanyCountByObj(ProductCompany productCompany){
    	return this.selectOne("com.mlj.ecbiz.model.product.ProductCompanyMapper.getProductCompanyCountByObj", productCompany);
    }
    
    public List<ProductCompany> getProductCompanyPage(ProductCompany productCompany,PageEntity page) {
        Integer objectscount = getProductCompanyCountByObj(productCompany);
        if (objectscount == null || objectscount == 0) {
            page.setTotalResultSize(0);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return null;
        } else {
            page.setTotalResultSize(objectscount);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return getProductCompanyListPage(productCompany,(page.getCurrentPage() - 1) * page.getPageSize(),page.getPageSize());
        }
    }
    
    
    
     /**
    *以下为缓存查询用
    */
    public Long getProductCompanyIdByObj(ProductCompany productCompany) {
        return this.selectOne("com.mlj.ecbiz.model.product.ProductCompanyMapper.getProductCompanyIdByObj",productCompany);
    }

    public List<Long> getProductCompanyIdList(ProductCompany productCompany) {
        return this.selectList("com.mlj.ecbiz.model.product.ProductCompanyMapper.getProductCompanyIdList",productCompany);
    }
    
    public List<Long> getProductCompanyIdListByObj(ProductCompany productCompany){
        return this.selectList("com.mlj.ecbiz.model.product.ProductCompanyMapper.getProductCompanyIdListByObj",productCompany);
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<Long> getProductCompanyIdListPage(ProductCompany productCompany,Integer offset,Integer limit){
    	try {
			Map map = BeanMapConvertor.convertBean(productCompany);
			map.put("offset",offset);
    		map.put("limit",limit);
        	return this.selectList("com.mlj.ecbiz.model.product.ProductCompanyMapper.getProductCompanyIdListByMap",map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return null;
    }
    public List<Long> getProductCompanyIdPage(ProductCompany productCompany,PageEntity page) {
        Integer objectscount = getProductCompanyCountByObj(productCompany);
        if (objectscount == null || objectscount == 0) {
            page.setTotalResultSize(0);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return null;
        } else {
            page.setTotalResultSize(objectscount);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return getProductCompanyIdListPage(productCompany,(page.getCurrentPage() - 1) * page.getPageSize(),page.getPageSize());
        }
    }
}
