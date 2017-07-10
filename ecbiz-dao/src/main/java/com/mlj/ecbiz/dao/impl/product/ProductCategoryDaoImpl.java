package com.mlj.ecbiz.dao.impl.product;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.mlj.ecbiz.model.product.ProductCategory;
import com.mlj.ecbiz.dao.product.ProductCategoryDao;
import org.springframework.stereotype.Repository;
import com.chexun.base.framework.core.dao.impl.common.GenericDaoImpl;
import com.chexun.base.common.util.BeanMapConvertor;
import com.chexun.base.framework.core.entity.PageEntity;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
/**
 *
 * ProductCategory
 * User:
 * Date: 2017-06-15
 */
 @Repository("productCategoryDao")
public class ProductCategoryDaoImpl extends GenericDaoImpl implements ProductCategoryDao{

    public Long addProductCategory(ProductCategory productCategory) {
        return this.insert("com.mlj.ecbiz.model.product.ProductCategoryMapper.createProductCategory",productCategory);
    }
	public Long insertProductCategory(ProductCategory productCategory){
		return this.insert("com.mlj.ecbiz.model.product.ProductCategoryMapper.insertProductCategory",productCategory);
	}
    public Long deleteProductCategoryById(Long id){
        return this.delete("com.mlj.ecbiz.model.product.ProductCategoryMapper.deleteProductCategoryById",id);
    }
    public Long deleteProductCategoryByObj(ProductCategory productCategory){
        return this.delete("com.mlj.ecbiz.model.product.ProductCategoryMapper.deleteProductCategoryByObj",productCategory);
    }
    
	public Long deleteProductCategoryByIdList(List<Long > ids){
		return this.delete("com.mlj.ecbiz.model.product.ProductCategoryMapper.deleteProductCategoryByIdList",ids);
	}
    public Long updateProductCategory(ProductCategory productCategory) {
        return this.update("com.mlj.ecbiz.model.product.ProductCategoryMapper.updateProductCategory",productCategory);
    }
    
    public Long updateProductCategoryByObj(ProductCategory productCategory){
    	return this.update("com.mlj.ecbiz.model.product.ProductCategoryMapper.updateProductCategoryByObj",productCategory);
    }
    
    public Long updateProductCategoryByObjAndConditions(ProductCategory s,ProductCategory c){
    	Map<String,ProductCategory> map = new HashMap<String,ProductCategory>();
    	map.put("s",s);
    	map.put("c",c);
    	return this.update("com.mlj.ecbiz.model.product.ProductCategoryMapper.updateProductCategoryByObjAndConditions",map);
    }
	public void batchUpdateProductCategory(List<ProductCategory> records){
		this.update("com.mlj.ecbiz.model.product.ProductCategoryMapper.batchUpdateProductCategory",records);
	}
    public ProductCategory getProductCategoryById(Long id) {
        return this.selectOne("com.mlj.ecbiz.model.product.ProductCategoryMapper.getProductCategoryById",id);
    }
    
    public ProductCategory getProductCategoryByObj(ProductCategory productCategory) {
        return this.selectOne("com.mlj.ecbiz.model.product.ProductCategoryMapper.getProductCategoryByObj",productCategory);
    }

    
    public List<ProductCategory> getProductCategoryListByObj(ProductCategory productCategory){
        return this.selectList("com.mlj.ecbiz.model.product.ProductCategoryMapper.getProductCategoryListByObj",productCategory);
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<ProductCategory> getProductCategoryListPage(ProductCategory productCategory,Integer offset,Integer limit){
    	try {
			Map map = BeanMapConvertor.convertBean(productCategory);
			map.put("offset",offset);
    		map.put("limit",limit);
        	return this.selectList("com.mlj.ecbiz.model.product.ProductCategoryMapper.getProductCategoryListByMap",map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return null;
    }
    public Integer getProductCategoryCountByObj(ProductCategory productCategory){
    	return this.selectOne("com.mlj.ecbiz.model.product.ProductCategoryMapper.getProductCategoryCountByObj", productCategory);
    }
    
    public List<ProductCategory> getProductCategoryPage(ProductCategory productCategory,PageEntity page) {
        Integer objectscount = getProductCategoryCountByObj(productCategory);
        if (objectscount == null || objectscount == 0) {
            page.setTotalResultSize(0);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return null;
        } else {
            page.setTotalResultSize(objectscount);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return getProductCategoryListPage(productCategory,(page.getCurrentPage() - 1) * page.getPageSize(),page.getPageSize());
        }
    }
    
    
    
     /**
    *以下为缓存查询用
    */
    public Long getProductCategoryIdByObj(ProductCategory productCategory) {
        return this.selectOne("com.mlj.ecbiz.model.product.ProductCategoryMapper.getProductCategoryIdByObj",productCategory);
    }

    public List<Long> getProductCategoryIdList(ProductCategory productCategory) {
        return this.selectList("com.mlj.ecbiz.model.product.ProductCategoryMapper.getProductCategoryIdList",productCategory);
    }
    
    public List<Long> getProductCategoryIdListByObj(ProductCategory productCategory){
        return this.selectList("com.mlj.ecbiz.model.product.ProductCategoryMapper.getProductCategoryIdListByObj",productCategory);
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<Long> getProductCategoryIdListPage(ProductCategory productCategory,Integer offset,Integer limit){
    	try {
			Map map = BeanMapConvertor.convertBean(productCategory);
			map.put("offset",offset);
    		map.put("limit",limit);
        	return this.selectList("com.mlj.ecbiz.model.product.ProductCategoryMapper.getProductCategoryIdListByMap",map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return null;
    }
    public List<Long> getProductCategoryIdPage(ProductCategory productCategory,PageEntity page) {
        Integer objectscount = getProductCategoryCountByObj(productCategory);
        if (objectscount == null || objectscount == 0) {
            page.setTotalResultSize(0);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return null;
        } else {
            page.setTotalResultSize(objectscount);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return getProductCategoryIdListPage(productCategory,(page.getCurrentPage() - 1) * page.getPageSize(),page.getPageSize());
        }
    }
    /**
     * 自己编写的接口
     */
    
    public List<ProductCategory> getProductCategoryListByParentId(Long parentId) {
    	return this.selectList("com.mlj.ecbiz.model.product.ProductCategoryMapper.getProductCategoryListByParentId",parentId);
    }
}
