package com.mlj.ecbiz.dao.product;
import java.util.List;
import java.util.Map;

import com.mlj.ecbiz.model.product.ProductBrand;
import com.chexun.base.framework.core.entity.PageEntity;
/**
 * ProductBrand管理接口
 * User: 
 * Date: 2017-06-21
 */
public interface ProductBrandDao {

    /**
     * 添加ProductBrand
     * @param productBrand 要添加的ProductBrand
     * @return id
     */
    public Long addProductBrand(ProductBrand productBrand);
	public Long insertProductBrand(ProductBrand productBrand);
    /**
     * 根据id删除一个ProductBrand
     * @param id 要删除的id
     */
    public Long deleteProductBrandById(Long id);
    
    public Long deleteProductBrandByObj(ProductBrand productBrand);

	public Long deleteProductBrandByIdList(List<Long > ids);

    /**
     * 修改ProductBrand
     * @param productBrand 要修改的ProductBrand
     */
    public Long updateProductBrand(ProductBrand productBrand);
    
    public Long updateProductBrandByObj(ProductBrand productBrand);
    
    public Long updateProductBrandByObjAndConditions(ProductBrand s,ProductBrand c);
    public void batchUpdateProductBrand(List<ProductBrand> records);

    /**
     * 根据id获取单个ProductBrand对象
     * @param id 要查询的id
     * @return ProductBrand
     */
    public ProductBrand getProductBrandById(Long id);
	public ProductBrand getProductBrandByObj(ProductBrand productBrand);
    /**
     * 根据条件获取ProductBrand列表
     * @param productBrand 查询条件
     * @return List<ProductBrand>
     */
    public List<ProductBrand> getProductBrandListByObj(ProductBrand productBrand);
    public List<ProductBrand> getProductBrandListPage(ProductBrand productBrand,Integer offset,Integer limit);
   
    public Integer getProductBrandCountByObj(ProductBrand productBrand);
    public List<ProductBrand> getProductBrandPage(ProductBrand productBrand,PageEntity page);
    public List<ProductBrand> getProductBrandPage(Map map,PageEntity page);
    
    /**
    *以下为缓存查询用
    */
    public Long getProductBrandIdByObj(ProductBrand productBrand);
    /**
     * 根据条件获取ProductBrand列表
     * @param productBrand 查询条件
     * @return List<ProductBrand>
     */
    public List<Long> getProductBrandIdList(ProductBrand productBrand);
    public List<Long> getProductBrandIdListByObj(ProductBrand productBrand);
    public List<Long> getProductBrandIdListPage(ProductBrand productBrand,Integer offset,Integer limit);
    public List<Long> getProductBrandIdPage(ProductBrand productBrand,PageEntity page);
    
}