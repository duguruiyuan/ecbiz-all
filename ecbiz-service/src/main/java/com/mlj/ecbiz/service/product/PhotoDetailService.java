package com.mlj.ecbiz.service.product;

import java.util.List;
import com.mlj.ecbiz.model.product.PhotoDetail;
import com.chexun.base.framework.core.entity.PageEntity; 
/**
 * PhotoDetail管理接口
 * User: 
 * Date: 2017-06-26
 */
public interface PhotoDetailService {

    /**
     * 添加PhotoDetail
     * @param photoDetail 要添加的PhotoDetail
     * @return id
     */
    public Long addPhotoDetail(PhotoDetail photoDetail);
	public Long insertPhotoDetail(PhotoDetail photoDetail);
    /**
     * 根据id删除一个PhotoDetail
     * @param id 要删除的id
     */
    public Long deletePhotoDetailById(Long id);
	public Long deletePhotoDetailByObj(PhotoDetail photoDetail);
    public Long deletePhotoDetailByIdList(List<Long > ids);
    /**
     * 修改PhotoDetail
     * @param photoDetail 要修改的PhotoDetail
     */
    public Long updatePhotoDetail(PhotoDetail photoDetail);
    public Long updatePhotoDetailByObj(PhotoDetail photoDetail);
    public Long updatePhotoDetailByObjAndConditions(PhotoDetail s,PhotoDetail c);
	public void batchUpdatePhotoDetail(List<PhotoDetail> records);
    /**
     * 根据id获取单个PhotoDetail对象
     * @param id 要查询的id
     * @return PhotoDetail
     */
    public PhotoDetail getPhotoDetailById(Long id);
	public PhotoDetail getPhotoDetailByObj(PhotoDetail photoDetail);
    /**
     * 根据条件获取PhotoDetail列表
     * @param photoDetail 查询条件
     * @return List<PhotoDetail>
     */
    public List<PhotoDetail> getPhotoDetailListByObj(PhotoDetail photoDetail);
    public List<PhotoDetail> getPhotoDetailListPage(PhotoDetail photoDetail,Integer offset,Integer limit);
    public Integer getPhotoDetailCountByObj(PhotoDetail photoDetail);
    public List<PhotoDetail> getPhotoDetailPage(PhotoDetail photoDetail,PageEntity page);
    
}