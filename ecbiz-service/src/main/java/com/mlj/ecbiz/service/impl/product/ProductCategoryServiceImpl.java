package com.mlj.ecbiz.service.impl.product;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mlj.ecbiz.model.product.ProductCategory;
import com.mlj.ecbiz.dao.product.ProductCategoryDao;
import com.mlj.ecbiz.service.product.ProductCategoryService;
import com.chexun.base.framework.core.entity.PageEntity;
import com.chexun.base.cache.QueryProvider;
/**
 * ProductCategory管理接口
 * User: 
 * Date: 2017-06-15
 */
@Service("productCategoryService")
public class ProductCategoryServiceImpl implements ProductCategoryService{

 	@Autowired
    private ProductCategoryDao productCategoryDao;
    /**
     * 添加ProductCategory
     * @param productCategory 要添加的ProductCategory
     * @return id
     */
    public Long addProductCategory(ProductCategory productCategory){
    	Long res = productCategoryDao.addProductCategory(productCategory);
    	return res;
    }
	public Long insertProductCategory(ProductCategory productCategory){
		Long res = productCategoryDao.insertProductCategory(productCategory);
		
    	return res;
	}
    /**
     * 根据id删除一个ProductCategory
     * @param id 要删除的id
     */
    public Long deleteProductCategoryById(Long id){
    	return productCategoryDao.deleteProductCategoryById(id);
    }
	public Long deleteProductCategoryByObj(ProductCategory productCategory){
        return productCategoryDao.deleteProductCategoryByObj(productCategory);
    }
    public Long deleteProductCategoryByIdList(List<Long > ids){
    	
    	return productCategoryDao.deleteProductCategoryByIdList(ids);
    }
    /**
     * 修改ProductCategory
     * @param productCategory 要修改的ProductCategory
     */
    public Long updateProductCategory(ProductCategory productCategory){
     	return productCategoryDao.updateProductCategory(productCategory);
    }
    
    public Long updateProductCategoryByObj(ProductCategory productCategory){
    	return productCategoryDao.updateProductCategoryByObj(productCategory);
    }
    
    public Long updateProductCategoryByObjAndConditions(ProductCategory s,ProductCategory c){
    	return productCategoryDao.updateProductCategoryByObjAndConditions(s,c);
    }
	public void batchUpdateProductCategory(List<ProductCategory> records){
		productCategoryDao.batchUpdateProductCategory(records);
	}
    /**
     * 根据id获取单个ProductCategory对象
     * @param id 要查询的id
     * @return ProductCategory
     */
    
    public Integer getProductCategoryCountByObj(ProductCategory productCategory){
    	return productCategoryDao.getProductCategoryCountByObj(productCategory);
    }
    


    public ProductCategory getProductCategoryById(Long id){
    	return productCategoryDao.getProductCategoryById( id);
    }
    
     public ProductCategory getProductCategoryByObj(ProductCategory productCategory) {
        return productCategoryDao.getProductCategoryByObj(productCategory);
    }

//   //商品上级分类
 	public ProductCategory findCategoryListByCategory() {
 		return findCategoryTopLevel();
 	}
 	private ProductCategory findCategoryTopLevel() {
 		ProductCategory category=new ProductCategory();
 		List<ProductCategory>categoryList=productCategoryDao.getProductCategoryListByParentId(0L);
 		return findCategoryOtherLevel(categoryList,category);
 	}
 	private ProductCategory findCategoryOtherLevel(List<ProductCategory>categoryList,ProductCategory category) {
 		if (null!=categoryList && 0!=categoryList.size()) {
 			category.setChildCategoryList(categoryList);
 			for (ProductCategory item:categoryList) {
 				List<ProductCategory> itemList=findChildCategoryList(item.getId());
 				if (null!=itemList && 0!=itemList.size()) {
 					findCategoryOtherLevel(itemList,item);
 				}else{
 					continue;
 				}
 			}
 		}
 		return category;
 	}
 	private List<ProductCategory> findChildCategoryList(Long id) {
 		return productCategoryDao.getProductCategoryListByParentId(id);
 	}
    
    public List<ProductCategory> getProductCategoryListByObj(ProductCategory productCategory){
//    	List<ProductCategory> categoryList=new ArrayList<ProductCategory>();
//    	List<ProductCategory> productCategoryList=productCategoryDao.getProductCategoryListByObj(productCategory);
//    	for(ProductCategory category:productCategoryList){
//    		if(category.getParentId().equals(0)){
//    			
//    		}
//    	}	
        return productCategoryDao.getProductCategoryListByObj(productCategory);
    }
    public List<ProductCategory> getProductCategoryListPage(ProductCategory productCategory,Integer offset,Integer limit){
        return productCategoryDao.getProductCategoryListPage(productCategory,offset,limit);
    }
    
    public List<ProductCategory> getProductCategoryPage(ProductCategory productCategory,PageEntity page) {
        return productCategoryDao.getProductCategoryPage(productCategory,page);
    }
    
    
    
    /**
     * 自己编写的接口
     */
    public List<ProductCategory> getProductCategoryListByParentId(Long parentId){
    	return productCategoryDao.getProductCategoryListByParentId(parentId);
    }
    
}