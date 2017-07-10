package com.mlj.ecbiz.dao.impl.product;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.mlj.ecbiz.model.product.PhotoDetail;
import com.mlj.ecbiz.dao.product.PhotoDetailDao;
import org.springframework.stereotype.Repository;
import com.chexun.base.framework.core.dao.impl.common.GenericDaoImpl;
import com.chexun.base.common.util.BeanMapConvertor;
import com.chexun.base.framework.core.entity.PageEntity;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
/**
 *
 * PhotoDetail
 * User:
 * Date: 2017-06-26
 */
 @Repository("photoDetailDao")
public class PhotoDetailDaoImpl extends GenericDaoImpl implements PhotoDetailDao{

    public Long addPhotoDetail(PhotoDetail photoDetail) {
        return this.insert("com.mlj.ecbiz.model.product.PhotoDetailMapper.createPhotoDetail",photoDetail);
    }
	public Long insertPhotoDetail(PhotoDetail photoDetail){
		return this.insert("com.mlj.ecbiz.model.product.PhotoDetailMapper.insertPhotoDetail",photoDetail);
	}
    public Long deletePhotoDetailById(Long id){
        return this.delete("com.mlj.ecbiz.model.product.PhotoDetailMapper.deletePhotoDetailById",id);
    }
    public Long deletePhotoDetailByObj(PhotoDetail photoDetail){
        return this.delete("com.mlj.ecbiz.model.product.PhotoDetailMapper.deletePhotoDetailByObj",photoDetail);
    }
    
	public Long deletePhotoDetailByIdList(List<Long > ids){
		return this.delete("com.mlj.ecbiz.model.product.PhotoDetailMapper.deletePhotoDetailByIdList",ids);
	}
    public Long updatePhotoDetail(PhotoDetail photoDetail) {
        return this.update("com.mlj.ecbiz.model.product.PhotoDetailMapper.updatePhotoDetail",photoDetail);
    }
    
    public Long updatePhotoDetailByObj(PhotoDetail photoDetail){
    	return this.update("com.mlj.ecbiz.model.product.PhotoDetailMapper.updatePhotoDetailByObj",photoDetail);
    }
    
    public Long updatePhotoDetailByObjAndConditions(PhotoDetail s,PhotoDetail c){
    	Map<String,PhotoDetail> map = new HashMap<String,PhotoDetail>();
    	map.put("s",s);
    	map.put("c",c);
    	return this.update("com.mlj.ecbiz.model.product.PhotoDetailMapper.updatePhotoDetailByObjAndConditions",map);
    }
	public void batchUpdatePhotoDetail(List<PhotoDetail> records){
		this.update("com.mlj.ecbiz.model.product.PhotoDetailMapper.batchUpdatePhotoDetail",records);
	}
    public PhotoDetail getPhotoDetailById(Long id) {
        return this.selectOne("com.mlj.ecbiz.model.product.PhotoDetailMapper.getPhotoDetailById",id);
    }
    
    public PhotoDetail getPhotoDetailByObj(PhotoDetail photoDetail) {
        return this.selectOne("com.mlj.ecbiz.model.product.PhotoDetailMapper.getPhotoDetailByObj",photoDetail);
    }

    
    public List<PhotoDetail> getPhotoDetailListByObj(PhotoDetail photoDetail){
        return this.selectList("com.mlj.ecbiz.model.product.PhotoDetailMapper.getPhotoDetailListByObj",photoDetail);
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<PhotoDetail> getPhotoDetailListPage(PhotoDetail photoDetail,Integer offset,Integer limit){
    	try {
			Map map = BeanMapConvertor.convertBean(photoDetail);
			map.put("offset",offset);
    		map.put("limit",limit);
        	return this.selectList("com.mlj.ecbiz.model.product.PhotoDetailMapper.getPhotoDetailListByMap",map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return null;
    }
    public Integer getPhotoDetailCountByObj(PhotoDetail photoDetail){
    	return this.selectOne("com.mlj.ecbiz.model.product.PhotoDetailMapper.getPhotoDetailCountByObj", photoDetail);
    }
    
    public List<PhotoDetail> getPhotoDetailPage(PhotoDetail photoDetail,PageEntity page) {
        Integer objectscount = getPhotoDetailCountByObj(photoDetail);
        if (objectscount == null || objectscount == 0) {
            page.setTotalResultSize(0);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return null;
        } else {
            page.setTotalResultSize(objectscount);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return getPhotoDetailListPage(photoDetail,(page.getCurrentPage() - 1) * page.getPageSize(),page.getPageSize());
        }
    }
    
    
    
     /**
    *以下为缓存查询用
    */
    public Long getPhotoDetailIdByObj(PhotoDetail photoDetail) {
        return this.selectOne("com.mlj.ecbiz.model.product.PhotoDetailMapper.getPhotoDetailIdByObj",photoDetail);
    }

    public List<Long> getPhotoDetailIdList(PhotoDetail photoDetail) {
        return this.selectList("com.mlj.ecbiz.model.product.PhotoDetailMapper.getPhotoDetailIdList",photoDetail);
    }
    
    public List<Long> getPhotoDetailIdListByObj(PhotoDetail photoDetail){
        return this.selectList("com.mlj.ecbiz.model.product.PhotoDetailMapper.getPhotoDetailIdListByObj",photoDetail);
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<Long> getPhotoDetailIdListPage(PhotoDetail photoDetail,Integer offset,Integer limit){
    	try {
			Map map = BeanMapConvertor.convertBean(photoDetail);
			map.put("offset",offset);
    		map.put("limit",limit);
        	return this.selectList("com.mlj.ecbiz.model.product.PhotoDetailMapper.getPhotoDetailIdListByMap",map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return null;
    }
    public List<Long> getPhotoDetailIdPage(PhotoDetail photoDetail,PageEntity page) {
        Integer objectscount = getPhotoDetailCountByObj(photoDetail);
        if (objectscount == null || objectscount == 0) {
            page.setTotalResultSize(0);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return null;
        } else {
            page.setTotalResultSize(objectscount);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return getPhotoDetailIdListPage(photoDetail,(page.getCurrentPage() - 1) * page.getPageSize(),page.getPageSize());
        }
    }
}
