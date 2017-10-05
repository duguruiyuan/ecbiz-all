package com.mlj.ecbiz.dao.product;
import java.util.List;
import com.mlj.ecbiz.model.product.ProductCompany;
import com.chexun.base.framework.core.entity.PageEntity;
/**
 * ProductCompany管理接口
 * User: 
 * Date: 2017-10-06
 */
public interface ProductCompanyDao {

    /**
     * 添加ProductCompany
     * @param productCompany 要添加的ProductCompany
     * @return id
     */
    public Long addProductCompany(ProductCompany productCompany);
	public Long insertProductCompany(ProductCompany productCompany);
    /**
     * 根据id删除一个ProductCompany
     * @param id 要删除的id
     */
    public Long deleteProductCompanyById(Long id);
    
    public Long deleteProductCompanyByObj(ProductCompany productCompany);

	public Long deleteProductCompanyByIdList(List<Long > ids);

    /**
     * 修改ProductCompany
     * @param productCompany 要修改的ProductCompany
     */
    public Long updateProductCompany(ProductCompany productCompany);
    
    public Long updateProductCompanyByObj(ProductCompany productCompany);
    
    public Long updateProductCompanyByObjAndConditions(ProductCompany s,ProductCompany c);
    public void batchUpdateProductCompany(List<ProductCompany> records);

    /**
     * 根据id获取单个ProductCompany对象
     * @param id 要查询的id
     * @return ProductCompany
     */
    public ProductCompany getProductCompanyById(Long id);
	public ProductCompany getProductCompanyByObj(ProductCompany productCompany);
    /**
     * 根据条件获取ProductCompany列表
     * @param productCompany 查询条件
     * @return List<ProductCompany>
     */
    public List<ProductCompany> getProductCompanyListByObj(ProductCompany productCompany);
    public List<ProductCompany> getProductCompanyListPage(ProductCompany productCompany,Integer offset,Integer limit);
    public Integer getProductCompanyCountByObj(ProductCompany productCompany);
    public List<ProductCompany> getProductCompanyPage(ProductCompany productCompany,PageEntity page);
    
    /**
    *以下为缓存查询用
    */
    public Long getProductCompanyIdByObj(ProductCompany productCompany);
    /**
     * 根据条件获取ProductCompany列表
     * @param productCompany 查询条件
     * @return List<ProductCompany>
     */
    public List<Long> getProductCompanyIdList(ProductCompany productCompany);
    public List<Long> getProductCompanyIdListByObj(ProductCompany productCompany);
    public List<Long> getProductCompanyIdListPage(ProductCompany productCompany,Integer offset,Integer limit);
    public List<Long> getProductCompanyIdPage(ProductCompany productCompany,PageEntity page);
}