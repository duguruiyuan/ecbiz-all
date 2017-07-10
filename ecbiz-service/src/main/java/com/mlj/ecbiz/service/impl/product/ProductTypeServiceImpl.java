package com.mlj.ecbiz.service.impl.product;

import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mlj.ecbiz.model.product.ProductType;
import com.mlj.ecbiz.dao.product.ProductTypeDao;
import com.mlj.ecbiz.service.product.ProductTypeService;
import com.chexun.base.framework.core.entity.PageEntity;
import com.chexun.base.cache.QueryProvider;
/**
 * ProductType管理接口
 * User: 
 * Date: 2017-06-26
 */
@Service("productTypeService")
public class ProductTypeServiceImpl implements ProductTypeService{

 	@Autowired
    private ProductTypeDao productTypeDao;
    /**
     * 添加ProductType
     * @param productType 要添加的ProductType
     * @return id
     */
    public Long addProductType(ProductType productType){
    	Long res = productTypeDao.addProductType(productType);
    	return res;
    }
	public Long insertProductType(ProductType productType){
		Long res = productTypeDao.insertProductType(productType);
		
    	return res;
	}
    /**
     * 根据id删除一个ProductType
     * @param id 要删除的id
     */
    public Long deleteProductTypeById(Long id){
    	return productTypeDao.deleteProductTypeById(id);
    }
	public Long deleteProductTypeByObj(ProductType productType){
        return productTypeDao.deleteProductTypeByObj(productType);
    }
    public Long deleteProductTypeByIdList(List<Long > ids){
    	
    	return productTypeDao.deleteProductTypeByIdList(ids);
    }
    /**
     * 修改ProductType
     * @param productType 要修改的ProductType
     */
    public Long updateProductType(ProductType productType){
     	return productTypeDao.updateProductType(productType);
    }
    
    public Long updateProductTypeByObj(ProductType productType){
    	return productTypeDao.updateProductTypeByObj(productType);
    }
    
    public Long updateProductTypeByObjAndConditions(ProductType s,ProductType c){
    	return productTypeDao.updateProductTypeByObjAndConditions(s,c);
    }
	public void batchUpdateProductType(List<ProductType> records){
		productTypeDao.batchUpdateProductType(records);
	}
    /**
     * 根据id获取单个ProductType对象
     * @param id 要查询的id
     * @return ProductType
     */
    
    public Integer getProductTypeCountByObj(ProductType productType){
    	return productTypeDao.getProductTypeCountByObj(productType);
    }
    


    public ProductType getProductTypeById(Long id){
    	return productTypeDao.getProductTypeById( id);
    }
    
     public ProductType getProductTypeByObj(ProductType productType) {
        return productTypeDao.getProductTypeByObj(productType);
    }


    
    public List<ProductType> getProductTypeListByObj(ProductType productType){
        return productTypeDao.getProductTypeListByObj(productType);
    }
    public List<ProductType> getProductTypeListPage(ProductType productType,Integer offset,Integer limit){
        return productTypeDao.getProductTypeListPage(productType,offset,limit);
    }
    
    public List<ProductType> getProductTypePage(ProductType productType,PageEntity page) {
        return productTypeDao.getProductTypePage(productType,page);
    }
}