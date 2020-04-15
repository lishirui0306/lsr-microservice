package cn.lsr.user.controller.job;

import cn.lsr.quartz.entity.JobInfo;
import cn.lsr.quartz.service.JobTaskService;
import cn.lsr.utils.PageUtils;
import cn.lsr.utils.Result;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @Description: job控制器
 * @Package: lsr-microservice
 * @author: Hacker_lsr@126.com
 **/
@Controller
public class JobController {
    private static final Logger log = LoggerFactory.getLogger(JobController.class);
    /**
     * 注入jobService
     */
    @Autowired
    private JobTaskService jobTaskService;

    /**
     * 查询所有的job
     * @return
     */
    @GetMapping("/job/list")
    @ResponseBody
    public PageUtils list(@RequestParam Map<String, Object> params){
        int pageBNum = Integer.parseInt(params.get("page").toString());
        int pageSize = Integer.parseInt(params.get("limit").toString());
        Page page = PageHelper.startPage(pageBNum,pageSize);
        List<JobInfo> list = null;
        try {
            list = jobTaskService.list();
        } catch (Exception e) {
            throw new RuntimeException("查询job出错！"+e.getMessage());
        }
        return new PageUtils(list,page.getTotal());
    }

    /**
     * 新增job
     * @param jobInfo
     */
    @PostMapping("/job/add")
    @ResponseBody
    public Result  add(JobInfo jobInfo){
        try {
            jobTaskService.addJob(jobInfo);
        } catch (Exception e) {
            log.error("新增job失败！"+e.getMessage());
            return Result.error();
        }
        return Result.success();
    }

    /**
     * 修改job
     * @param jobInfo
     * @return
     */
    @PostMapping("/job/edit")
    @ResponseBody
    public Result edit(JobInfo jobInfo){
        try {
            jobTaskService.edit(jobInfo);
        } catch (Exception e) {
            log.error("修改job失败！"+e.getMessage());
            return Result.error();
        }
        return Result.success();
    }

    /**
     * 删除job
     * @param jobName
     * @param JobGroup
     * @return
     */
    @PostMapping("/job/delete")
    @ResponseBody
    public Result delete(String jobName,String JobGroup){
        try {
            jobTaskService.delete(jobName,JobGroup);
        } catch (Exception e) {
            log.error("删除job失败！"+e.getMessage());
            return Result.error();
        }
        return Result.success();
    }

    /**
     * 暂停job
     * @param jobName
     * @param JobGroup
     * @return
     */
    @PostMapping("/job/pause")
    @ResponseBody
    public Result pause(String jobName,String JobGroup){
        try {
            jobTaskService.pause(jobName,JobGroup);
        } catch (Exception e) {
            log.error("暂停job失败！"+e.getMessage());
            return Result.error();
        }
        return Result.success();
    }

    /**
     * 重启job
     * @param jobName
     * @param JobGroup
     * @return
     */
    @PostMapping("/job/resume")
    @ResponseBody
    public Result resume(String jobName,String JobGroup){
        try {
            jobTaskService.resume(jobName,JobGroup);
        } catch (Exception e) {
            log.error("重启job失败！"+e.getMessage());
            return Result.error();
        }
        return Result.success();
    }

    /**
     * 测试
     */
    @GetMapping("/job/test")
    @ResponseBody
    public void test(){
        JobInfo jobInfo = new JobInfo();
        jobInfo.setJobName("cn.lsr.user.job.JobExecute");
        jobInfo.setJobGroup("test");
        jobInfo.setJobDescription("lishirui");
        jobInfo.setCronExpression("0 */1 * * * ? ");
        jobTaskService.addJob(jobInfo);
    }
}
