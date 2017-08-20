package com.mlj.ecbiz.service.impl.trade;

import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mlj.ecbiz.model.trade.TradeOrders;
import com.mlj.ecbiz.dao.trade.TradeOrdersDao;
import com.mlj.ecbiz.service.trade.TradeOrdersService;
import com.chexun.base.framework.core.entity.PageEntity;
import com.chexun.base.cache.QueryProvider;
/**
 * TradeOrders管理接口
 * User: 
 * Date: 2017-08-20
 */
@Service("tradeOrdersService")
public class TradeOrdersServiceImpl implements TradeOrdersService{

 	@Autowired
    private TradeOrdersDao tradeOrdersDao;
    /**
     * 添加TradeOrders
     * @param tradeOrders 要添加的TradeOrders
     * @return id
     */
    public Long addTradeOrders(TradeOrders tradeOrders){
    	Long res = tradeOrdersDao.addTradeOrders(tradeOrders);
    	return res;
    }
	public Long insertTradeOrders(TradeOrders tradeOrders){
		Long res = tradeOrdersDao.insertTradeOrders(tradeOrders);
		
    	return res;
	}
    /**
     * 根据id删除一个TradeOrders
     * @param oid 要删除的id
     */
    public Long deleteTradeOrdersById(Long oid){
    	return tradeOrdersDao.deleteTradeOrdersById(oid);
    }
	public Long deleteTradeOrdersByObj(TradeOrders tradeOrders){
        return tradeOrdersDao.deleteTradeOrdersByObj(tradeOrders);
    }
    public Long deleteTradeOrdersByIdList(List<Long > ids){
    	
    	return tradeOrdersDao.deleteTradeOrdersByIdList(ids);
    }
    /**
     * 修改TradeOrders
     * @param tradeOrders 要修改的TradeOrders
     */
    public Long updateTradeOrders(TradeOrders tradeOrders){
     	return tradeOrdersDao.updateTradeOrders(tradeOrders);
    }
    
    public Long updateTradeOrdersByObj(TradeOrders tradeOrders){
    	return tradeOrdersDao.updateTradeOrdersByObj(tradeOrders);
    }
    
    public Long updateTradeOrdersByObjAndConditions(TradeOrders s,TradeOrders c){
    	return tradeOrdersDao.updateTradeOrdersByObjAndConditions(s,c);
    }
	public void batchUpdateTradeOrders(List<TradeOrders> records){
		tradeOrdersDao.batchUpdateTradeOrders(records);
	}
    /**
     * 根据id获取单个TradeOrders对象
     * @param oid 要查询的id
     * @return TradeOrders
     */
    
    public Integer getTradeOrdersCountByObj(TradeOrders tradeOrders){
    	return tradeOrdersDao.getTradeOrdersCountByObj(tradeOrders);
    }
    


    public TradeOrders getTradeOrdersById(Long oid){
    	return tradeOrdersDao.getTradeOrdersById( oid);
    }
    
     public TradeOrders getTradeOrdersByObj(TradeOrders tradeOrders) {
        return tradeOrdersDao.getTradeOrdersByObj(tradeOrders);
    }


    
    public List<TradeOrders> getTradeOrdersListByObj(TradeOrders tradeOrders){
        return tradeOrdersDao.getTradeOrdersListByObj(tradeOrders);
    }
    public List<TradeOrders> getTradeOrdersListPage(TradeOrders tradeOrders,Integer offset,Integer limit){
        return tradeOrdersDao.getTradeOrdersListPage(tradeOrders,offset,limit);
    }
    
    public List<TradeOrders> getTradeOrdersPage(TradeOrders tradeOrders,PageEntity page) {
        return tradeOrdersDao.getTradeOrdersPage(tradeOrders,page);
    }
}