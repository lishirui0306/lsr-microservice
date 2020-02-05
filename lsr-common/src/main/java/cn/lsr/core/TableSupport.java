package cn.lsr.core;
import cn.lsr.constant.Constants;
import cn.lsr.utils.ServletClientUtils;

/**
 * = = 表格数据处理
 *
 * @Version:  1.0
 * @Author: Hacker_lsr@126.com
 */
public class TableSupport
{
    /**
     * 封装分页对象
     */
    public static PageInfoUtil getPageInfo()
    {
        PageInfoUtil PageInfo = new PageInfoUtil();
        PageInfo.setPageNum(ServletClientUtils.getParameterToInt(Constants.PAGE_NUM));
        PageInfo.setPageSize(ServletClientUtils.getParameterToInt(Constants.PAGE_SIZE));
        PageInfo.setOrderByColumn(ServletClientUtils.getParameter(Constants.ORDER_BY_COLUMN));
        PageInfo.setIsAsc(ServletClientUtils.getParameter(Constants.IS_ASC));
        return PageInfo;
    }


    public static PageInfoUtil buildPageRequest()
    {
        return getPageInfo();
    }
}
