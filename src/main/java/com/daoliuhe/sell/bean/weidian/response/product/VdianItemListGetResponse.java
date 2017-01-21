package com.daoliuhe.sell.bean.weidian.response.product;

import com.daoliuhe.sell.bean.weidian.entity.Item;
import com.daoliuhe.sell.bean.weidian.response.AbstractResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

public class VdianItemListGetResponse extends AbstractResponse {

  private VdianItemListGetResult result;

  public VdianItemListGetResult getResult() {
    return result;
  }

  public void setResult(VdianItemListGetResult result) {
    this.result = result;
  }


  /***** VdianItemListGetResult *****/
  public static class VdianItemListGetResult {

    private ListItem[] items;

    @JsonProperty("item_num")
    private int itemNum;

    @JsonProperty("total_num")
    private int totalNum;

    public ListItem[] getItems() {
      return items;
    }

    public void setItems(ListItem[] items) {
      this.items = items;
    }

    public int getItemNum() {
      return itemNum;
    }

    public void setItemNum(int itemNum) {
      this.itemNum = itemNum;
    }

    public int getTotalNum() {
      return totalNum;
    }

    public void setTotalNum(int totalNum) {
      this.totalNum = totalNum;
    }

  }


  /***** ListItem *****/
  public static class ListItem extends Item {

    @JsonProperty("update_time")
    private String updateTime;

    public String getUpdateTime() {
      return updateTime;
    }

    public void setUpdateTime(String updateTime) {
      this.updateTime = updateTime;
    }

  }

}
