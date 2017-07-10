package com.mlj.ecbiz.dao.impl.product;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.mlj.ecbiz.model.product.ProductBrand;
import com.mlj.ecbiz.dao.product.ProductBrandDao;
import org.springframework.stereotype.Repository;
import com.chexun.base.framework.core.dao.impl.common.GenericDaoImpl;
import com.chexun.base.common.util.BeanMapConvertor;
import com.chexun.base.framework.core.entity.PageEntity;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
/**
 *
 * ProductBrand
 * User:
 * Date: 2017-06-21
 */
 @Repository("productBrandDao")
public class ProductBrandDaoImpl extends GenericDaoImpl implements ProductBrandDao{

    public Long addProductBrand(ProductBrand productBrand) {
        return this.insert("com.mlj.ecbiz.model.product.ProductBrandMapper.createProductBrand",productBrand);
    }
	public Long insertProductBrand(ProductBrand productBrand){
		return this.insert("com.mlj.ecbiz.model.product.ProductBrandMapper.insertProductBrand",productBrand);
	}
    public Long deleteProductBrandById(Long id){
        return this.delete("com.mlj.ecbiz.model.product.ProductBrandMapper.deleteProductBrandById",id);
    }
    public Long deleteProductBrandByObj(ProductBrand productBrand){
        return this.delete("com.mlj.ecbiz.model.product.ProductBrandMapper.deleteProductBrandByObj",productBrand);
    }
    
	public Long deleteProductBrandByIdList(List<Long > ids){
		return this.delete("com.mlj.ecbiz.model.product.ProductBrandMapper.deleteProductBrandByIdList",ids);
	}
    public Long updateProductBrand(ProductBrand productBrand) {
        return this.update("com.mlj.ecbiz.model.product.ProductBrandMapper.updateProductBrand",productBrand);
    }
    
    public Long updateProductBrandByObj(ProductBrand productBrand){
    	return this.update("com.mlj.ecbiz.model.product.ProductBrandMapper.updateProductBrandByObj",productBrand);
    }
    
    public Long updateProductBrandByObjAndConditions(ProductBrand s,ProductBrand c){
    	Map<String,ProductBrand> map = new HashMap<String,ProductBrand>();
    	map.put("s",s);
    	map.put("c",c);
    	return this.update("com.mlj.ecbiz.model.product.ProductBrandMapper.updateProductBrandByObjAndConditions",map);
    }
	public void batchUpdateProductBrand(List<ProductBrand> records){
		this.update("com.mlj.ecbiz.model.product.ProductBrandMapper.batchUpdateProductBrand",records);
	}
    public ProductBrand getProductBrandById(Long id) {
        return this.selectOne("com.mlj.ecbiz.model.product.ProductBrandMapper.getProductBrandById",id);
    }
    
    public ProductBrand getProductBrandByObj(ProductBrand productBrand) {
        return this.selectOne("com.mlj.ecbiz.model.product.ProductBrandMapper.getProductBrandByObj",productBrand);
    }

    
    public List<ProductBrand> getProductBrandListByObj(ProductBrand productBrand){
        return this.selectList("com.mlj.ecbiz.model.product.ProductBrandMapper.getProductBrandListByObj",productBrand);
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<ProductBrand> getProductBrandListPage(ProductBrand productBrand,Integer offset,Integer limit){
    	try {
			Map map = BeanMapConvertor.convertBean(productBrand);
			map.put("offset",offset);
    		map.put("limit",limit);
        	return this.selectList("com.mlj.ecbiz.model.product.ProductBrandMapper.getProductBrandListByMap",map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return null;
    }
    public Integer getProductBrandCountByObj(ProductBrand productBrand){
    	return this.selectOne("com.mlj.ecbiz.model.product.ProductBrandMapper.getProductBrandCountByObj", productBrand);
    }
    
    public List<ProductBrand> getProductBrandPage(ProductBrand productBrand,PageEntity page) {
        Integer objectscount = getProductBrandCountByObj(productBrand);
        if (objectscount == null || objectscount == 0) {
            page.setTotalResultSize(0);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return null;
        } else {
            page.setTotalResultSize(objectscount);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return getProductBrandListPage(productBrand,(page.getCurrentPage() - 1) * page.getPageSize(),page.getPageSize());
        }
    }
    
    
     /**
    *以下为缓存查询用
    */
    public Long getProductBrandIdByObj(ProductBrand productBrand) {
        return this.selectOne("com.mlj.ecbiz.model.product.ProductBrandMapper.getProductBrandIdByObj",productBrand);
    }

    public List<Long> getProductBrandIdList(ProductBrand productBrand) {
        return this.selectList("com.mlj.ecbiz.model.product.ProductBrandMapper.getProductBrandIdList",productBrand);
    }
    
    public List<Long> getProductBrandIdListByObj(ProductBrand productBrand){
        return this.selectList("com.mlj.ecbiz.model.product.ProductBrandMapper.getProductBrandIdListByObj",productBrand);
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<Long> getProductBrandIdListPage(ProductBrand productBrand,Integer offset,Integer limit){
    	try {
			Map map = BeanMapConvertor.convertBean(productBrand);
			map.put("offset",offset);
    		map.put("limit",limit);
        	return this.selectList("com.mlj.ecbiz.model.product.ProductBrandMapper.getProductBrandIdListByMap",map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return null;
    }
    public List<Long> getProductBrandIdPage(ProductBrand productBrand,PageEntity page) {
        Integer objectscount = getProductBrandCountByObj(productBrand);
        if (objectscount == null || objectscount == 0) {
            page.setTotalResultSize(0);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return null;
        } else {
            page.setTotalResultSize(objectscount);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return getProductBrandIdListPage(productBrand,(page.getCurrentPage() - 1) * page.getPageSize(),page.getPageSize());
        }
    }
    
    /**手动添加**/
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<ProductBrand> getProductBrandListPage(Map map,Integer offset,Integer limit){
    	try {
			map.put("offset",offset);
    		map.put("limit",limit);
        	return this.selectList("com.mlj.ecbiz.model.product.ProductBrandMapper.getProductBrandListByMapPage",map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
    }
    public Integer getProductBrandCountByObj(Map map){
    	return this.selectOne("com.mlj.ecbiz.model.product.ProductBrandMapper.getProductBrandCountByObjPage", map);
    }
    public List<ProductBrand> getProductBrandPage(Map map,PageEntity page){
    	Integer objectscount = getProductBrandCountByObj(map);
        if (objectscount == null || objectscount == 0) {
            page.setTotalResultSize(0);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return null;
        } else {
            page.setTotalResultSize(objectscount);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return getProductBrandListPage(map,(page.getCurrentPage() - 1) * page.getPageSize(),page.getPageSize());
        }
    }
	
}
