package com.mlj.ecbiz.dao.trade;
import java.util.List;
import com.mlj.ecbiz.model.trade.TradeOrders;
import com.chexun.base.framework.core.entity.PageEntity;
/**
 * TradeOrders管理接口
 * User: 
 * Date: 2017-08-20
 */
public interface TradeOrdersDao {

    /**
     * 添加TradeOrders
     * @param tradeOrders 要添加的TradeOrders
     * @return id
     */
    public Long addTradeOrders(TradeOrders tradeOrders);
	public Long insertTradeOrders(TradeOrders tradeOrders);
    /**
     * 根据id删除一个TradeOrders
     * @param oid 要删除的id
     */
    public Long deleteTradeOrdersById(Long oid);
    
    public Long deleteTradeOrdersByObj(TradeOrders tradeOrders);

	public Long deleteTradeOrdersByIdList(List<Long > ids);

    /**
     * 修改TradeOrders
     * @param tradeOrders 要修改的TradeOrders
     */
    public Long updateTradeOrders(TradeOrders tradeOrders);
    
    public Long updateTradeOrdersByObj(TradeOrders tradeOrders);
    
    public Long updateTradeOrdersByObjAndConditions(TradeOrders s,TradeOrders c);
    public void batchUpdateTradeOrders(List<TradeOrders> records);

    /**
     * 根据id获取单个TradeOrders对象
     * @param oid 要查询的id
     * @return TradeOrders
     */
    public TradeOrders getTradeOrdersById(Long oid);
	public TradeOrders getTradeOrdersByObj(TradeOrders tradeOrders);
    /**
     * 根据条件获取TradeOrders列表
     * @param tradeOrders 查询条件
     * @return List<TradeOrders>
     */
    public List<TradeOrders> getTradeOrdersListByObj(TradeOrders tradeOrders);
    public List<TradeOrders> getTradeOrdersListPage(TradeOrders tradeOrders,Integer offset,Integer limit);
    public Integer getTradeOrdersCountByObj(TradeOrders tradeOrders);
    public List<TradeOrders> getTradeOrdersPage(TradeOrders tradeOrders,PageEntity page);
    
    /**
    *以下为缓存查询用
    */
    public Long getTradeOrdersIdByObj(TradeOrders tradeOrders);
    /**
     * 根据条件获取TradeOrders列表
     * @param tradeOrders 查询条件
     * @return List<TradeOrders>
     */
    public List<Long> getTradeOrdersIdList(TradeOrders tradeOrders);
    public List<Long> getTradeOrdersIdListByObj(TradeOrders tradeOrders);
    public List<Long> getTradeOrdersIdListPage(TradeOrders tradeOrders,Integer offset,Integer limit);
    public List<Long> getTradeOrdersIdPage(TradeOrders tradeOrders,PageEntity page);
}