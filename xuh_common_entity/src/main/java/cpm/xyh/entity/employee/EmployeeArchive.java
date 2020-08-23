package cpm.xyh.entity.employee;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 员工归档
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeArchive implements Serializable {
    private static final long serialVersionUID = 5768915936056289957L;
    /**
     * ID
     */

    private String id;
    /**
     * 操作人
     */
    private String opUser;
    /**
     * 月份
     */
    private String month;
    /**
     * 企业ID
     */
    private String companyId;
    /**
     * 总人数
     */
    private Integer totals;
    /**
     * 在职人数
     */
    private Integer payrolls;
    /**
     * 离职人数
     */
    private Integer departures;
    /**
     * 数据
     */
    private String data;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
}
