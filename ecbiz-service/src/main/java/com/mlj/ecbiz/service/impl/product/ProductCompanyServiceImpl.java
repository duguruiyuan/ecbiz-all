package com.mlj.ecbiz.service.impl.product;

import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mlj.ecbiz.model.product.ProductCompany;
import com.mlj.ecbiz.dao.product.ProductCompanyDao;
import com.mlj.ecbiz.service.product.ProductCompanyService;
import com.chexun.base.framework.core.entity.PageEntity;
import com.chexun.base.cache.QueryProvider;
/**
 * ProductCompany管理接口
 * User: 
 * Date: 2017-10-06
 */
@Service("productCompanyService")
public class ProductCompanyServiceImpl implements ProductCompanyService{

 	@Autowired
    private ProductCompanyDao productCompanyDao;
    /**
     * 添加ProductCompany
     * @param productCompany 要添加的ProductCompany
     * @return id
     */
    public Long addProductCompany(ProductCompany productCompany){
    	Long res = productCompanyDao.addProductCompany(productCompany);
    	return res;
    }
	public Long insertProductCompany(ProductCompany productCompany){
		Long res = productCompanyDao.insertProductCompany(productCompany);
		
    	return res;
	}
    /**
     * 根据id删除一个ProductCompany
     * @param id 要删除的id
     */
    public Long deleteProductCompanyById(Long id){
    	return productCompanyDao.deleteProductCompanyById(id);
    }
	public Long deleteProductCompanyByObj(ProductCompany productCompany){
        return productCompanyDao.deleteProductCompanyByObj(productCompany);
    }
    public Long deleteProductCompanyByIdList(List<Long > ids){
    	
    	return productCompanyDao.deleteProductCompanyByIdList(ids);
    }
    /**
     * 修改ProductCompany
     * @param productCompany 要修改的ProductCompany
     */
    public Long updateProductCompany(ProductCompany productCompany){
     	return productCompanyDao.updateProductCompany(productCompany);
    }
    
    public Long updateProductCompanyByObj(ProductCompany productCompany){
    	return productCompanyDao.updateProductCompanyByObj(productCompany);
    }
    
    public Long updateProductCompanyByObjAndConditions(ProductCompany s,ProductCompany c){
    	return productCompanyDao.updateProductCompanyByObjAndConditions(s,c);
    }
	public void batchUpdateProductCompany(List<ProductCompany> records){
		productCompanyDao.batchUpdateProductCompany(records);
	}
    /**
     * 根据id获取单个ProductCompany对象
     * @param id 要查询的id
     * @return ProductCompany
     */
    
    public Integer getProductCompanyCountByObj(ProductCompany productCompany){
    	return productCompanyDao.getProductCompanyCountByObj(productCompany);
    }
    


    public ProductCompany getProductCompanyById(Long id){
    	return productCompanyDao.getProductCompanyById( id);
    }
    
     public ProductCompany getProductCompanyByObj(ProductCompany productCompany) {
        return productCompanyDao.getProductCompanyByObj(productCompany);
    }


    
    public List<ProductCompany> getProductCompanyListByObj(ProductCompany productCompany){
        return productCompanyDao.getProductCompanyListByObj(productCompany);
    }
    public List<ProductCompany> getProductCompanyListPage(ProductCompany productCompany,Integer offset,Integer limit){
        return productCompanyDao.getProductCompanyListPage(productCompany,offset,limit);
    }
    
    public List<ProductCompany> getProductCompanyPage(ProductCompany productCompany,PageEntity page) {
        return productCompanyDao.getProductCompanyPage(productCompany,page);
    }
}