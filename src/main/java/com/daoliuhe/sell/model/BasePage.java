package com.daoliuhe.sell.model;

/**
 * 分页基础类
 *
 * @author 21829
 * @create 2017-01-04 15:33
 **/
public class BasePage {
    private int page = 1;

    private int rows = 10;

    public int getBeginIndex() {
        return (page - 1) * rows;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
}
