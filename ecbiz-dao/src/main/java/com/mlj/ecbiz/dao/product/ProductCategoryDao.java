package com.mlj.ecbiz.dao.product;
import java.util.List;
import com.mlj.ecbiz.model.product.ProductCategory;
import com.chexun.base.framework.core.entity.PageEntity;
/**
 * ProductCategory管理接口
 * User: 
 * Date: 2017-06-15
 */
public interface ProductCategoryDao {

    /**
     * 添加ProductCategory
     * @param productCategory 要添加的ProductCategory
     * @return id
     */
    public Long addProductCategory(ProductCategory productCategory);
	public Long insertProductCategory(ProductCategory productCategory);
    /**
     * 根据id删除一个ProductCategory
     * @param id 要删除的id
     */
    public Long deleteProductCategoryById(Long id);
    
    public Long deleteProductCategoryByObj(ProductCategory productCategory);

	public Long deleteProductCategoryByIdList(List<Long > ids);

    /**
     * 修改ProductCategory
     * @param productCategory 要修改的ProductCategory
     */
    public Long updateProductCategory(ProductCategory productCategory);
    
    public Long updateProductCategoryByObj(ProductCategory productCategory);
    
    public Long updateProductCategoryByObjAndConditions(ProductCategory s,ProductCategory c);
    public void batchUpdateProductCategory(List<ProductCategory> records);

    /**
     * 根据id获取单个ProductCategory对象
     * @param id 要查询的id
     * @return ProductCategory
     */
    public ProductCategory getProductCategoryById(Long id);
	public ProductCategory getProductCategoryByObj(ProductCategory productCategory);
    /**
     * 根据条件获取ProductCategory列表
     * @param productCategory 查询条件
     * @return List<ProductCategory>
     */
    public List<ProductCategory> getProductCategoryListByObj(ProductCategory productCategory);
    public List<ProductCategory> getProductCategoryListPage(ProductCategory productCategory,Integer offset,Integer limit);
    public Integer getProductCategoryCountByObj(ProductCategory productCategory);
    public List<ProductCategory> getProductCategoryPage(ProductCategory productCategory,PageEntity page);
    
    /**
    *以下为缓存查询用
    */
    public Long getProductCategoryIdByObj(ProductCategory productCategory);
    /**
     * 根据条件获取ProductCategory列表
     * @param productCategory 查询条件
     * @return List<ProductCategory>
     */
    public List<Long> getProductCategoryIdList(ProductCategory productCategory);
    public List<Long> getProductCategoryIdListByObj(ProductCategory productCategory);
    public List<Long> getProductCategoryIdListPage(ProductCategory productCategory,Integer offset,Integer limit);
    public List<Long> getProductCategoryIdPage(ProductCategory productCategory,PageEntity page);
    
   /**
    * 自己编写的接口
    */
    public List<ProductCategory> getProductCategoryListByParentId(Long parentId);
}