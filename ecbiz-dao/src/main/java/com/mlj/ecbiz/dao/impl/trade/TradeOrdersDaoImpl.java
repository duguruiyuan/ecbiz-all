package com.mlj.ecbiz.dao.impl.trade;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.mlj.ecbiz.model.trade.TradeOrders;
import com.mlj.ecbiz.dao.trade.TradeOrdersDao;
import org.springframework.stereotype.Repository;
import com.chexun.base.framework.core.dao.impl.common.GenericDaoImpl;
import com.chexun.base.common.util.BeanMapConvertor;
import com.chexun.base.framework.core.entity.PageEntity;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
/**
 *
 * TradeOrders
 * User:
 * Date: 2017-08-20
 */
 @Repository("tradeOrdersDao")
public class TradeOrdersDaoImpl extends GenericDaoImpl implements TradeOrdersDao{

    public Long addTradeOrders(TradeOrders tradeOrders) {
        return this.insert("com.mlj.ecbiz.model.trade.TradeOrdersMapper.createTradeOrders",tradeOrders);
    }
	public Long insertTradeOrders(TradeOrders tradeOrders){
		return this.insert("com.mlj.ecbiz.model.trade.TradeOrdersMapper.insertTradeOrders",tradeOrders);
	}
    public Long deleteTradeOrdersById(Long oid){
        return this.delete("com.mlj.ecbiz.model.trade.TradeOrdersMapper.deleteTradeOrdersById",oid);
    }
    public Long deleteTradeOrdersByObj(TradeOrders tradeOrders){
        return this.delete("com.mlj.ecbiz.model.trade.TradeOrdersMapper.deleteTradeOrdersByObj",tradeOrders);
    }
    
	public Long deleteTradeOrdersByIdList(List<Long > ids){
		return this.delete("com.mlj.ecbiz.model.trade.TradeOrdersMapper.deleteTradeOrdersByIdList",ids);
	}
    public Long updateTradeOrders(TradeOrders tradeOrders) {
        return this.update("com.mlj.ecbiz.model.trade.TradeOrdersMapper.updateTradeOrders",tradeOrders);
    }
    
    public Long updateTradeOrdersByObj(TradeOrders tradeOrders){
    	return this.update("com.mlj.ecbiz.model.trade.TradeOrdersMapper.updateTradeOrdersByObj",tradeOrders);
    }
    
    public Long updateTradeOrdersByObjAndConditions(TradeOrders s,TradeOrders c){
    	Map<String,TradeOrders> map = new HashMap<String,TradeOrders>();
    	map.put("s",s);
    	map.put("c",c);
    	return this.update("com.mlj.ecbiz.model.trade.TradeOrdersMapper.updateTradeOrdersByObjAndConditions",map);
    }
	public void batchUpdateTradeOrders(List<TradeOrders> records){
		this.update("com.mlj.ecbiz.model.trade.TradeOrdersMapper.batchUpdateTradeOrders",records);
	}
    public TradeOrders getTradeOrdersById(Long oid) {
        return this.selectOne("com.mlj.ecbiz.model.trade.TradeOrdersMapper.getTradeOrdersById",oid);
    }
    
    public TradeOrders getTradeOrdersByObj(TradeOrders tradeOrders) {
        return this.selectOne("com.mlj.ecbiz.model.trade.TradeOrdersMapper.getTradeOrdersByObj",tradeOrders);
    }

    
    public List<TradeOrders> getTradeOrdersListByObj(TradeOrders tradeOrders){
        return this.selectList("com.mlj.ecbiz.model.trade.TradeOrdersMapper.getTradeOrdersListByObj",tradeOrders);
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<TradeOrders> getTradeOrdersListPage(TradeOrders tradeOrders,Integer offset,Integer limit){
    	try {
			Map map = BeanMapConvertor.convertBean(tradeOrders);
			map.put("offset",offset);
    		map.put("limit",limit);
        	return this.selectList("com.mlj.ecbiz.model.trade.TradeOrdersMapper.getTradeOrdersListByMap",map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return null;
    }
    public Integer getTradeOrdersCountByObj(TradeOrders tradeOrders){
    	return this.selectOne("com.mlj.ecbiz.model.trade.TradeOrdersMapper.getTradeOrdersCountByObj", tradeOrders);
    }
    
    public List<TradeOrders> getTradeOrdersPage(TradeOrders tradeOrders,PageEntity page) {
        Integer objectscount = getTradeOrdersCountByObj(tradeOrders);
        if (objectscount == null || objectscount == 0) {
            page.setTotalResultSize(0);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return null;
        } else {
            page.setTotalResultSize(objectscount);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return getTradeOrdersListPage(tradeOrders,(page.getCurrentPage() - 1) * page.getPageSize(),page.getPageSize());
        }
    }
    
    
    
     /**
    *以下为缓存查询用
    */
    public Long getTradeOrdersIdByObj(TradeOrders tradeOrders) {
        return this.selectOne("com.mlj.ecbiz.model.trade.TradeOrdersMapper.getTradeOrdersIdByObj",tradeOrders);
    }

    public List<Long> getTradeOrdersIdList(TradeOrders tradeOrders) {
        return this.selectList("com.mlj.ecbiz.model.trade.TradeOrdersMapper.getTradeOrdersIdList",tradeOrders);
    }
    
    public List<Long> getTradeOrdersIdListByObj(TradeOrders tradeOrders){
        return this.selectList("com.mlj.ecbiz.model.trade.TradeOrdersMapper.getTradeOrdersIdListByObj",tradeOrders);
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<Long> getTradeOrdersIdListPage(TradeOrders tradeOrders,Integer offset,Integer limit){
    	try {
			Map map = BeanMapConvertor.convertBean(tradeOrders);
			map.put("offset",offset);
    		map.put("limit",limit);
        	return this.selectList("com.mlj.ecbiz.model.trade.TradeOrdersMapper.getTradeOrdersIdListByMap",map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return null;
    }
    public List<Long> getTradeOrdersIdPage(TradeOrders tradeOrders,PageEntity page) {
        Integer objectscount = getTradeOrdersCountByObj(tradeOrders);
        if (objectscount == null || objectscount == 0) {
            page.setTotalResultSize(0);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return null;
        } else {
            page.setTotalResultSize(objectscount);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return getTradeOrdersIdListPage(tradeOrders,(page.getCurrentPage() - 1) * page.getPageSize(),page.getPageSize());
        }
    }
}
