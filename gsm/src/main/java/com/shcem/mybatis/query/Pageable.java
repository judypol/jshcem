package com.shcem.mybatis.query;

import com.shcem.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**分页参数，如果还有其他的查询条件，需要继承此类
 * Created by judysen on 2017/8/26.
 */
public class Pageable {
    private Integer pageSize = 10;//每页展示条数
    private Integer pageIndex; //第几页
    String orderByStr=null;

    List<OrderField> orderFields = new ArrayList<OrderField>();
    public Pageable(){

    }
    public Pageable(int pageIndex,int pageSize,OrderField ...orderFields){
        this.pageIndex=pageIndex;
        this.pageSize=pageSize;
        for(OrderField orderField:orderFields){
            this.orderFields.add(orderField);
        }
    }
    /**
     *
     * @return
     */
    public String getOrderByStr() {
        StringBuilder str = new StringBuilder();
        for (OrderField o : orderFields){
            if (StringUtils.isEmpty(str)){
                str.append("ORDER BY ");
            }else{
                str.append(",");
            }
            str.append(o.getOrderField());
            if (o.getOrderDesc()){
                str.append( "DESC");
            }else{
                str.append(" ASC");
            }
        }
        if (StringUtils.isEmpty(str)){
            orderByStr = str.toString();
        }
        return orderByStr;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public List<OrderField> getOrderFields() {
        return orderFields;
    }

    public void setOrderFields(List<OrderField> orderFields) {
        this.orderFields = orderFields;
    }
}
