package cs2020.experiment04.controller.dto;

import lombok.Data;

@Data
public class QueryInfo {
    String query; // 查询信息partyname
    int pageNum=1; // 当前页码
    int pageSize=1; // 每页最大数
    String username;
}
