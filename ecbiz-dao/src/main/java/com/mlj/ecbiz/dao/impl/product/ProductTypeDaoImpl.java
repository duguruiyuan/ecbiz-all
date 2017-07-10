package com.mlj.ecbiz.dao.impl.product;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.mlj.ecbiz.model.product.ProductType;
import com.mlj.ecbiz.dao.product.ProductTypeDao;
import org.springframework.stereotype.Repository;
import com.chexun.base.framework.core.dao.impl.common.GenericDaoImpl;
import com.chexun.base.common.util.BeanMapConvertor;
import com.chexun.base.framework.core.entity.PageEntity;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
/**
 *
 * ProductType
 * User:
 * Date: 2017-06-15
 */
 @Repository("productTypeDao")
public class ProductTypeDaoImpl extends GenericDaoImpl implements ProductTypeDao{

    public Long addProductType(ProductType productType) {
        return this.insert("com.mlj.ecbiz.model.product.ProductTypeMapper.createProductType",productType);
    }
	public Long insertProductType(ProductType productType){
		return this.insert("com.mlj.ecbiz.model.product.ProductTypeMapper.insertProductType",productType);
	}
    public Long deleteProductTypeById(Long id){
        return this.delete("com.mlj.ecbiz.model.product.ProductTypeMapper.deleteProductTypeById",id);
    }
    public Long deleteProductTypeByObj(ProductType productType){
        return this.delete("com.mlj.ecbiz.model.product.ProductTypeMapper.deleteProductTypeByObj",productType);
    }
    
	public Long deleteProductTypeByIdList(List<Long > ids){
		return this.delete("com.mlj.ecbiz.model.product.ProductTypeMapper.deleteProductTypeByIdList",ids);
	}
    public Long updateProductType(ProductType productType) {
        return this.update("com.mlj.ecbiz.model.product.ProductTypeMapper.updateProductType",productType);
    }
    
    public Long updateProductTypeByObj(ProductType productType){
    	return this.update("com.mlj.ecbiz.model.product.ProductTypeMapper.updateProductTypeByObj",productType);
    }
    
    public Long updateProductTypeByObjAndConditions(ProductType s,ProductType c){
    	Map<String,ProductType> map = new HashMap<String,ProductType>();
    	map.put("s",s);
    	map.put("c",c);
    	return this.update("com.mlj.ecbiz.model.product.ProductTypeMapper.updateProductTypeByObjAndConditions",map);
    }
	public void batchUpdateProductType(List<ProductType> records){
		this.update("com.mlj.ecbiz.model.product.ProductTypeMapper.batchUpdateProductType",records);
	}
    public ProductType getProductTypeById(Long id) {
        return this.selectOne("com.mlj.ecbiz.model.product.ProductTypeMapper.getProductTypeById",id);
    }
    
    public ProductType getProductTypeByObj(ProductType productType) {
        return this.selectOne("com.mlj.ecbiz.model.product.ProductTypeMapper.getProductTypeByObj",productType);
    }

    
    public List<ProductType> getProductTypeListByObj(ProductType productType){
        return this.selectList("com.mlj.ecbiz.model.product.ProductTypeMapper.getProductTypeListByObj",productType);
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<ProductType> getProductTypeListPage(ProductType productType,Integer offset,Integer limit){
    	try {
			Map map = BeanMapConvertor.convertBean(productType);
			map.put("offset",offset);
    		map.put("limit",limit);
        	return this.selectList("com.mlj.ecbiz.model.product.ProductTypeMapper.getProductTypeListByMap",map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return null;
    }
    public Integer getProductTypeCountByObj(ProductType productType){
    	return this.selectOne("com.mlj.ecbiz.model.product.ProductTypeMapper.getProductTypeCountByObj", productType);
    }
    
    public List<ProductType> getProductTypePage(ProductType productType,PageEntity page) {
        Integer objectscount = getProductTypeCountByObj(productType);
        if (objectscount == null || objectscount == 0) {
            page.setTotalResultSize(0);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return null;
        } else {
            page.setTotalResultSize(objectscount);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return getProductTypeListPage(productType,(page.getCurrentPage() - 1) * page.getPageSize(),page.getPageSize());
        }
    }
    
    
    
     /**
    *以下为缓存查询用
    */
    public Long getProductTypeIdByObj(ProductType productType) {
        return this.selectOne("com.mlj.ecbiz.model.product.ProductTypeMapper.getProductTypeIdByObj",productType);
    }

    public List<Long> getProductTypeIdList(ProductType productType) {
        return this.selectList("com.mlj.ecbiz.model.product.ProductTypeMapper.getProductTypeIdList",productType);
    }
    
    public List<Long> getProductTypeIdListByObj(ProductType productType){
        return this.selectList("com.mlj.ecbiz.model.product.ProductTypeMapper.getProductTypeIdListByObj",productType);
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<Long> getProductTypeIdListPage(ProductType productType,Integer offset,Integer limit){
    	try {
			Map map = BeanMapConvertor.convertBean(productType);
			map.put("offset",offset);
    		map.put("limit",limit);
        	return this.selectList("com.mlj.ecbiz.model.product.ProductTypeMapper.getProductTypeIdListByMap",map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return null;
    }
    public List<Long> getProductTypeIdPage(ProductType productType,PageEntity page) {
        Integer objectscount = getProductTypeCountByObj(productType);
        if (objectscount == null || objectscount == 0) {
            page.setTotalResultSize(0);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return null;
        } else {
            page.setTotalResultSize(objectscount);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return getProductTypeIdListPage(productType,(page.getCurrentPage() - 1) * page.getPageSize(),page.getPageSize());
        }
    }
}
