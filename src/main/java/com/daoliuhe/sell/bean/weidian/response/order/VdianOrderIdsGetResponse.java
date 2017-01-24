package com.daoliuhe.sell.bean.weidian.response.order;

import com.daoliuhe.sell.bean.weidian.response.AbstractResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 查询当天的订单列表
 */
public class VdianOrderIdsGetResponse extends AbstractResponse {

    private VdianOrderGetResult result;

    public VdianOrderGetResult getResult() {
        return result;
    }

    public void setResult(VdianOrderGetResult result) {
        this.result = result;
    }

    public static class VdianOrderGetResult {

        private int total;

        @JsonProperty("order_ids")
        private List<String> orderIds;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<String> getOrderIds() {
            return orderIds;
        }

        public void setOrderIds(List<String> orderIds) {
            this.orderIds = orderIds;
        }
    }
}
