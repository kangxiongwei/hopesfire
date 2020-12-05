package com.kxw.hopesfire.dm.rules;

import lombok.Builder;
import lombok.Data;

/**
 * 图的边
 *
 * @author kangxiongwei
 * @date 2020-12-04
 */
@Data
@Builder
public class GraphEdge {

    private String edgeId;      //边ID
    private String edgeName;    //边名称
    private String sourceId;    //原始ID
    private String targetId;    //目标ID
    private String condition;   //条件
    private int priority;       //优先级 0-99，越大优先级越低，默认分支100独占

}
