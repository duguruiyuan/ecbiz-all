package com.mlj.ecbiz.service.impl.product;

import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mlj.ecbiz.model.product.PhotoDetail;
import com.mlj.ecbiz.dao.product.PhotoDetailDao;
import com.mlj.ecbiz.service.product.PhotoDetailService;
import com.chexun.base.framework.core.entity.PageEntity;
import com.chexun.base.cache.QueryProvider;
/**
 * PhotoDetail管理接口
 * User: 
 * Date: 2017-06-26
 */
@Service("photoDetailService")
public class PhotoDetailServiceImpl implements PhotoDetailService{

 	@Autowired
    private PhotoDetailDao photoDetailDao;
    /**
     * 添加PhotoDetail
     * @param photoDetail 要添加的PhotoDetail
     * @return id
     */
    public Long addPhotoDetail(PhotoDetail photoDetail){
    	Long res = photoDetailDao.addPhotoDetail(photoDetail);
    	return res;
    }
	public Long insertPhotoDetail(PhotoDetail photoDetail){
		Long res = photoDetailDao.insertPhotoDetail(photoDetail);
		
    	return res;
	}
    /**
     * 根据id删除一个PhotoDetail
     * @param id 要删除的id
     */
    public Long deletePhotoDetailById(Long id){
    	return photoDetailDao.deletePhotoDetailById(id);
    }
	public Long deletePhotoDetailByObj(PhotoDetail photoDetail){
        return photoDetailDao.deletePhotoDetailByObj(photoDetail);
    }
    public Long deletePhotoDetailByIdList(List<Long > ids){
    	
    	return photoDetailDao.deletePhotoDetailByIdList(ids);
    }
    /**
     * 修改PhotoDetail
     * @param photoDetail 要修改的PhotoDetail
     */
    public Long updatePhotoDetail(PhotoDetail photoDetail){
     	return photoDetailDao.updatePhotoDetail(photoDetail);
    }
    
    public Long updatePhotoDetailByObj(PhotoDetail photoDetail){
    	return photoDetailDao.updatePhotoDetailByObj(photoDetail);
    }
    
    public Long updatePhotoDetailByObjAndConditions(PhotoDetail s,PhotoDetail c){
    	return photoDetailDao.updatePhotoDetailByObjAndConditions(s,c);
    }
	public void batchUpdatePhotoDetail(List<PhotoDetail> records){
		photoDetailDao.batchUpdatePhotoDetail(records);
	}
    /**
     * 根据id获取单个PhotoDetail对象
     * @param id 要查询的id
     * @return PhotoDetail
     */
    
    public Integer getPhotoDetailCountByObj(PhotoDetail photoDetail){
    	return photoDetailDao.getPhotoDetailCountByObj(photoDetail);
    }
    


    public PhotoDetail getPhotoDetailById(Long id){
    	return photoDetailDao.getPhotoDetailById( id);
    }
    
     public PhotoDetail getPhotoDetailByObj(PhotoDetail photoDetail) {
        return photoDetailDao.getPhotoDetailByObj(photoDetail);
    }


    
    public List<PhotoDetail> getPhotoDetailListByObj(PhotoDetail photoDetail){
        return photoDetailDao.getPhotoDetailListByObj(photoDetail);
    }
    public List<PhotoDetail> getPhotoDetailListPage(PhotoDetail photoDetail,Integer offset,Integer limit){
        return photoDetailDao.getPhotoDetailListPage(photoDetail,offset,limit);
    }
    
    public List<PhotoDetail> getPhotoDetailPage(PhotoDetail photoDetail,PageEntity page) {
        return photoDetailDao.getPhotoDetailPage(photoDetail,page);
    }
}