package com.mlj.ecbiz.service.product;

import java.util.List;
import com.mlj.ecbiz.model.product.ProductType;
import com.chexun.base.framework.core.entity.PageEntity; 
/**
 * ProductType管理接口
 * User: 
 * Date: 2017-06-15
 */
public interface ProductTypeService {

    /**
     * 添加ProductType
     * @param productType 要添加的ProductType
     * @return id
     */
    public Long addProductType(ProductType productType);
	public Long insertProductType(ProductType productType);
    /**
     * 根据id删除一个ProductType
     * @param id 要删除的id
     */
    public Long deleteProductTypeById(Long id);
	public Long deleteProductTypeByObj(ProductType productType);
    public Long deleteProductTypeByIdList(List<Long > ids);
    /**
     * 修改ProductType
     * @param productType 要修改的ProductType
     */
    public Long updateProductType(ProductType productType);
    public Long updateProductTypeByObj(ProductType productType);
    public Long updateProductTypeByObjAndConditions(ProductType s,ProductType c);
	public void batchUpdateProductType(List<ProductType> records);
    /**
     * 根据id获取单个ProductType对象
     * @param id 要查询的id
     * @return ProductType
     */
    public ProductType getProductTypeById(Long id);
	public ProductType getProductTypeByObj(ProductType productType);
    /**
     * 根据条件获取ProductType列表
     * @param productType 查询条件
     * @return List<ProductType>
     */
    public List<ProductType> getProductTypeListByObj(ProductType productType);
    public List<ProductType> getProductTypeListPage(ProductType productType,Integer offset,Integer limit);
    public Integer getProductTypeCountByObj(ProductType productType);
    public List<ProductType> getProductTypePage(ProductType productType,PageEntity page);
    
}