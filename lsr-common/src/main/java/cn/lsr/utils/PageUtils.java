package cn.lsr.utils;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: table专用返回类
 * @ProjectName: lsr-boot-cloud
 * @ClassName: PageUtils
 * @Version: 1.0
 * @Author: Hacker_lsr@126.com
 */
public class PageUtils implements Serializable {
    private static final long serialVersionUID = 1L;
    private long total;
    private List<?> rows;

    public PageUtils(List<?> list, long total) {
        this.rows = list;
        this.total = total;
    }
    public long getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

}
