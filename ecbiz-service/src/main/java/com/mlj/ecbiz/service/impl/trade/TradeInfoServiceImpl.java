package com.mlj.ecbiz.service.impl.trade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chexun.base.framework.core.entity.PageEntity;
import com.mlj.ecbiz.dao.trade.TradeInfoDao;
import com.mlj.ecbiz.model.trade.TradeInfo;
import com.mlj.ecbiz.service.trade.TradeInfoService;
/**
 * TradeInfo管理接口
 * User: 
 * Date: 2017-08-20
 */
@Service("tradeInfoService")
public class TradeInfoServiceImpl implements TradeInfoService{

 	@Autowired
    private TradeInfoDao tradeInfoDao;
    /**
     * 添加TradeInfo
     * @param tradeInfo 要添加的TradeInfo
     * @return id
     */
    public Long addTradeInfo(TradeInfo tradeInfo){
    	Long res = tradeInfoDao.addTradeInfo(tradeInfo);
    	return res;
    }
	public Long insertTradeInfo(TradeInfo tradeInfo){
		Long res = tradeInfoDao.insertTradeInfo(tradeInfo);
		
    	return res;
	}
    /**
     * 根据id删除一个TradeInfo
     * @param tid 要删除的id
     */
    public Long deleteTradeInfoById(Long tid){
    	return tradeInfoDao.deleteTradeInfoById(tid);
    }
	public Long deleteTradeInfoByObj(TradeInfo tradeInfo){
        return tradeInfoDao.deleteTradeInfoByObj(tradeInfo);
    }
    public Long deleteTradeInfoByIdList(List<Long > ids){
    	
    	return tradeInfoDao.deleteTradeInfoByIdList(ids);
    }
    /**
     * 修改TradeInfo
     * @param tradeInfo 要修改的TradeInfo
     */
    public Long updateTradeInfo(TradeInfo tradeInfo){
     	return tradeInfoDao.updateTradeInfo(tradeInfo);
    }
    
    public Long updateTradeInfoByObj(TradeInfo tradeInfo){
    	return tradeInfoDao.updateTradeInfoByObj(tradeInfo);
    }
    
    public Long updateTradeInfoByObjAndConditions(TradeInfo s,TradeInfo c){
    	return tradeInfoDao.updateTradeInfoByObjAndConditions(s,c);
    }
	public void batchUpdateTradeInfo(List<TradeInfo> records){
		tradeInfoDao.batchUpdateTradeInfo(records);
	}
    /**
     * 根据id获取单个TradeInfo对象
     * @param tid 要查询的id
     * @return TradeInfo
     */
    
    public Integer getTradeInfoCountByObj(TradeInfo tradeInfo){
    	return tradeInfoDao.getTradeInfoCountByObj(tradeInfo);
    }
    


    public TradeInfo getTradeInfoById(Long tid){
    	return tradeInfoDao.getTradeInfoById( tid);
    }
    
     public TradeInfo getTradeInfoByObj(TradeInfo tradeInfo) {
        return tradeInfoDao.getTradeInfoByObj(tradeInfo);
    }


    
    public List<TradeInfo> getTradeInfoListByObj(TradeInfo tradeInfo){
        return tradeInfoDao.getTradeInfoListByObj(tradeInfo);
    }
    public List<TradeInfo> getTradeInfoListPage(TradeInfo tradeInfo,Integer offset,Integer limit){
        return tradeInfoDao.getTradeInfoListPage(tradeInfo,offset,limit);
    }
    
    public List<TradeInfo> getTradeInfoPage(TradeInfo tradeInfo,PageEntity page) {
        return tradeInfoDao.getTradeInfoPage(tradeInfo,page);
    }
}