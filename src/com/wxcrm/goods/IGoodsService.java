package com.wxcrm.goods;

import com.wxcrm.util.Page;

public interface IGoodsService 
{
	public Page queryGoodsFeilei(WcGoodsFenlei feilei);
	public void addGoodsFeilei(WcGoodsFenlei feilei);
	public WcGoodsFenlei getFeileiById(Integer id);
	public void updGoodsFenlei(WcGoodsFenlei feilei);
	public void addGoodsFenlei(WcGoodsFenlei fenlei);
	public Page queryGoods(WcGoods goods);
	public void addGoods(WcGoods goods);
	public WcGoods getGoodsById(Integer wgsId);
	public void updGoods(WcGoods goods);
	public void delGoods(WcGoods goods);
	public void delGoodsFenlei(WcGoodsFenlei fenlei);
	public void addGoodsIn(WcGoodsIn goodsIn);
	
}
