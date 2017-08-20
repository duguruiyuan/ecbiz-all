package com.mlj.ecbiz.service.trade;

import java.util.List;
import com.mlj.ecbiz.model.trade.TradeInfo;
import com.chexun.base.framework.core.entity.PageEntity; 
/**
 * TradeInfo管理接口
 * User: 
 * Date: 2017-08-20
 */
public interface TradeInfoService {

    /**
     * 添加TradeInfo
     * @param tradeInfo 要添加的TradeInfo
     * @return id
     */
    public Long addTradeInfo(TradeInfo tradeInfo);
	public Long insertTradeInfo(TradeInfo tradeInfo);
    /**
     * 根据id删除一个TradeInfo
     * @param tid 要删除的id
     */
    public Long deleteTradeInfoById(Long tid);
	public Long deleteTradeInfoByObj(TradeInfo tradeInfo);
    public Long deleteTradeInfoByIdList(List<Long > ids);
    /**
     * 修改TradeInfo
     * @param tradeInfo 要修改的TradeInfo
     */
    public Long updateTradeInfo(TradeInfo tradeInfo);
    public Long updateTradeInfoByObj(TradeInfo tradeInfo);
    public Long updateTradeInfoByObjAndConditions(TradeInfo s,TradeInfo c);
	public void batchUpdateTradeInfo(List<TradeInfo> records);
    /**
     * 根据id获取单个TradeInfo对象
     * @param tid 要查询的id
     * @return TradeInfo
     */
    public TradeInfo getTradeInfoById(Long tid);
	public TradeInfo getTradeInfoByObj(TradeInfo tradeInfo);
    /**
     * 根据条件获取TradeInfo列表
     * @param tradeInfo 查询条件
     * @return List<TradeInfo>
     */
    public List<TradeInfo> getTradeInfoListByObj(TradeInfo tradeInfo);
    public List<TradeInfo> getTradeInfoListPage(TradeInfo tradeInfo,Integer offset,Integer limit);
    public Integer getTradeInfoCountByObj(TradeInfo tradeInfo);
    public List<TradeInfo> getTradeInfoPage(TradeInfo tradeInfo,PageEntity page);
    
}