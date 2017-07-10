package com.mlj.ecbiz.service.impl.product;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chexun.base.framework.core.entity.PageEntity;
import com.mlj.ecbiz.dao.product.ProductBrandDao;
import com.mlj.ecbiz.model.product.ProductBrand;
import com.mlj.ecbiz.service.product.ProductBrandService;
/**
 * ProductBrand管理接口
 * User: 
 * Date: 2017-06-21
 */
@Service("productBrandService")
public class ProductBrandServiceImpl implements ProductBrandService{

 	@Autowired
    private ProductBrandDao productBrandDao;
    /**
     * 添加ProductBrand
     * @param productBrand 要添加的ProductBrand
     * @return id
     */
    public Long addProductBrand(ProductBrand productBrand){
    	Long res = productBrandDao.addProductBrand(productBrand);
    	return res;
    }
	public Long insertProductBrand(ProductBrand productBrand){
		Long res = productBrandDao.insertProductBrand(productBrand);
		
    	return res;
	}
    /**
     * 根据id删除一个ProductBrand
     * @param id 要删除的id
     */
    public Long deleteProductBrandById(Long id){
    	return productBrandDao.deleteProductBrandById(id);
    }
	public Long deleteProductBrandByObj(ProductBrand productBrand){
        return productBrandDao.deleteProductBrandByObj(productBrand);
    }
    public Long deleteProductBrandByIdList(List<Long > ids){
    	
    	return productBrandDao.deleteProductBrandByIdList(ids);
    }
    /**
     * 修改ProductBrand
     * @param productBrand 要修改的ProductBrand
     */
    public Long updateProductBrand(ProductBrand productBrand){
     	return productBrandDao.updateProductBrand(productBrand);
    }
    
    public Long updateProductBrandByObj(ProductBrand productBrand){
    	return productBrandDao.updateProductBrandByObj(productBrand);
    }
    
    public Long updateProductBrandByObjAndConditions(ProductBrand s,ProductBrand c){
    	return productBrandDao.updateProductBrandByObjAndConditions(s,c);
    }
	public void batchUpdateProductBrand(List<ProductBrand> records){
		productBrandDao.batchUpdateProductBrand(records);
	}
    /**
     * 根据id获取单个ProductBrand对象
     * @param id 要查询的id
     * @return ProductBrand
     */
    
    public Integer getProductBrandCountByObj(ProductBrand productBrand){
    	return productBrandDao.getProductBrandCountByObj(productBrand);
    }
    


    public ProductBrand getProductBrandById(Long id){
    	return productBrandDao.getProductBrandById( id);
    }
    
     public ProductBrand getProductBrandByObj(ProductBrand productBrand) {
        return productBrandDao.getProductBrandByObj(productBrand);
    }


    
    public List<ProductBrand> getProductBrandListByObj(ProductBrand productBrand){
        return productBrandDao.getProductBrandListByObj(productBrand);
    }
    public List<ProductBrand> getProductBrandListPage(ProductBrand productBrand,Integer offset,Integer limit){
        return productBrandDao.getProductBrandListPage(productBrand,offset,limit);
    }
   
    public List<ProductBrand> getProductBrandPage(ProductBrand productBrand,PageEntity page) {
        return productBrandDao.getProductBrandPage(productBrand,page);
    }
    public List<ProductBrand> getProductBrandPage(Map map, PageEntity page) {
		return productBrandDao.getProductBrandPage(map,page);
	}
  
   
}