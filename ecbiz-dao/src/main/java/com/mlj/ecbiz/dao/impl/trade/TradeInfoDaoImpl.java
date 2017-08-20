package com.mlj.ecbiz.dao.impl.trade;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.mlj.ecbiz.model.trade.TradeInfo;
import com.mlj.ecbiz.dao.trade.TradeInfoDao;
import org.springframework.stereotype.Repository;
import com.chexun.base.framework.core.dao.impl.common.GenericDaoImpl;
import com.chexun.base.common.util.BeanMapConvertor;
import com.chexun.base.framework.core.entity.PageEntity;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
/**
 *
 * TradeInfo
 * User:
 * Date: 2017-08-20
 */
 @Repository("tradeInfoDao")
public class TradeInfoDaoImpl extends GenericDaoImpl implements TradeInfoDao{

    public Long addTradeInfo(TradeInfo tradeInfo) {
        return this.insert("com.mlj.ecbiz.model.trade.TradeInfoMapper.createTradeInfo",tradeInfo);
    }
	public Long insertTradeInfo(TradeInfo tradeInfo){
		return this.insert("com.mlj.ecbiz.model.trade.TradeInfoMapper.insertTradeInfo",tradeInfo);
	}
    public Long deleteTradeInfoById(Long tid){
        return this.delete("com.mlj.ecbiz.model.trade.TradeInfoMapper.deleteTradeInfoById",tid);
    }
    public Long deleteTradeInfoByObj(TradeInfo tradeInfo){
        return this.delete("com.mlj.ecbiz.model.trade.TradeInfoMapper.deleteTradeInfoByObj",tradeInfo);
    }
    
	public Long deleteTradeInfoByIdList(List<Long > ids){
		return this.delete("com.mlj.ecbiz.model.trade.TradeInfoMapper.deleteTradeInfoByIdList",ids);
	}
    public Long updateTradeInfo(TradeInfo tradeInfo) {
        return this.update("com.mlj.ecbiz.model.trade.TradeInfoMapper.updateTradeInfo",tradeInfo);
    }
    
    public Long updateTradeInfoByObj(TradeInfo tradeInfo){
    	return this.update("com.mlj.ecbiz.model.trade.TradeInfoMapper.updateTradeInfoByObj",tradeInfo);
    }
    
    public Long updateTradeInfoByObjAndConditions(TradeInfo s,TradeInfo c){
    	Map<String,TradeInfo> map = new HashMap<String,TradeInfo>();
    	map.put("s",s);
    	map.put("c",c);
    	return this.update("com.mlj.ecbiz.model.trade.TradeInfoMapper.updateTradeInfoByObjAndConditions",map);
    }
	public void batchUpdateTradeInfo(List<TradeInfo> records){
		this.update("com.mlj.ecbiz.model.trade.TradeInfoMapper.batchUpdateTradeInfo",records);
	}
    public TradeInfo getTradeInfoById(Long tid) {
        return this.selectOne("com.mlj.ecbiz.model.trade.TradeInfoMapper.getTradeInfoById",tid);
    }
    
    public TradeInfo getTradeInfoByObj(TradeInfo tradeInfo) {
        return this.selectOne("com.mlj.ecbiz.model.trade.TradeInfoMapper.getTradeInfoByObj",tradeInfo);
    }

    
    public List<TradeInfo> getTradeInfoListByObj(TradeInfo tradeInfo){
        return this.selectList("com.mlj.ecbiz.model.trade.TradeInfoMapper.getTradeInfoListByObj",tradeInfo);
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<TradeInfo> getTradeInfoListPage(TradeInfo tradeInfo,Integer offset,Integer limit){
    	try {
			Map map = BeanMapConvertor.convertBean(tradeInfo);
			map.put("offset",offset);
    		map.put("limit",limit);
        	return this.selectList("com.mlj.ecbiz.model.trade.TradeInfoMapper.getTradeInfoListByMap",map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return null;
    }
    public Integer getTradeInfoCountByObj(TradeInfo tradeInfo){
    	return this.selectOne("com.mlj.ecbiz.model.trade.TradeInfoMapper.getTradeInfoCountByObj", tradeInfo);
    }
    
    public List<TradeInfo> getTradeInfoPage(TradeInfo tradeInfo,PageEntity page) {
        Integer objectscount = getTradeInfoCountByObj(tradeInfo);
        if (objectscount == null || objectscount == 0) {
            page.setTotalResultSize(0);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return null;
        } else {
            page.setTotalResultSize(objectscount);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return getTradeInfoListPage(tradeInfo,(page.getCurrentPage() - 1) * page.getPageSize(),page.getPageSize());
        }
    }
    
    
    
     /**
    *以下为缓存查询用
    */
    public Long getTradeInfoIdByObj(TradeInfo tradeInfo) {
        return this.selectOne("com.mlj.ecbiz.model.trade.TradeInfoMapper.getTradeInfoIdByObj",tradeInfo);
    }

    public List<Long> getTradeInfoIdList(TradeInfo tradeInfo) {
        return this.selectList("com.mlj.ecbiz.model.trade.TradeInfoMapper.getTradeInfoIdList",tradeInfo);
    }
    
    public List<Long> getTradeInfoIdListByObj(TradeInfo tradeInfo){
        return this.selectList("com.mlj.ecbiz.model.trade.TradeInfoMapper.getTradeInfoIdListByObj",tradeInfo);
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<Long> getTradeInfoIdListPage(TradeInfo tradeInfo,Integer offset,Integer limit){
    	try {
			Map map = BeanMapConvertor.convertBean(tradeInfo);
			map.put("offset",offset);
    		map.put("limit",limit);
        	return this.selectList("com.mlj.ecbiz.model.trade.TradeInfoMapper.getTradeInfoIdListByMap",map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return null;
    }
    public List<Long> getTradeInfoIdPage(TradeInfo tradeInfo,PageEntity page) {
        Integer objectscount = getTradeInfoCountByObj(tradeInfo);
        if (objectscount == null || objectscount == 0) {
            page.setTotalResultSize(0);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return null;
        } else {
            page.setTotalResultSize(objectscount);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return getTradeInfoIdListPage(tradeInfo,(page.getCurrentPage() - 1) * page.getPageSize(),page.getPageSize());
        }
    }
}
