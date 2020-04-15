package cn.lsr.user.controller.user;

import cn.lsr.core.thread.PollThreadConfig;
import cn.lsr.core.thread.TestServerPollThread;
import cn.lsr.core.util.SpringUtil;
import cn.lsr.redis.lock.RedisInterFace;
import cn.lsr.redis.utils.RedisResult;
import cn.lsr.user.entity.user.User;
import cn.lsr.user.mapper.user.UserMapper;
import cn.lsr.utils.PageUtils;
import cn.lsr.utils.Result;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * = =  用户控制器
 *
 * @Version: 1.0
 * @Author: Hacker_lsr@126.com
 */
@Api(tags = "用户信息控制器")
@Controller
public class UserController {
    private TestServerPollThread testServerPollThread;
    /**
     * 注入redis服务
     */
    @Resource
    private RedisInterFace redisInterFace;

    // 获取consul 服务信息
    @Autowired
    private DiscoveryClient discoveryClient;
    /**
     * 功能描述: <br>
     * 〈〉用户mapper
     * @Param:
     * @Return:
     * @Author: Hacker_lsr@126.com
     */
    @Resource
    private UserMapper userMapper;
    private static String s = "user";
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    /**
     * 功能描述: <br>
     * 〈〉open 用户列表页面
     * @Param: []
     * @Return: java.lang.String
     * @Author: Hacker_lsr@126.com
     */
    @RequestMapping("/toUser")
    private String toUser(){
        return s+"/user_find";
    }
    /**
     * 功能描述: <br>
     * 〈〉查看用户信息
     * @Param: []
     * @Return: com.lsr.common.utils.PageUtils
     * @Author: Hacker_lsr@126.com
     */
    @RequiresPermissions("select")
    @RequestMapping("/userlist")
    @ResponseBody
    public PageUtils listUser(@RequestParam Map<String, Object> params){

        int pageBNum = Integer.parseInt(params.get("page").toString());
        int pageSize = Integer.parseInt(params.get("limit").toString());
        //获取consul 上的服务
        Page page = PageHelper.startPage(pageBNum,pageSize);
        PollThreadConfig pollThreadConfig = SpringUtil.getBean(PollThreadConfig.class);
        testServerPollThread = new TestServerPollThread(pollThreadConfig.getThreadId(),pollThreadConfig.getThreadName(),pollThreadConfig.getThreadName(),pollThreadConfig.getDelay(),pollThreadConfig.getTimeInterval());
        testServerPollThread.startup();
        List<User> list = userMapper.selectAll();
        return  new PageUtils(list,page.getTotal());
    }

    /**
     * 功能描述: <br>
     * 〈〉open 新增页面
     * @Param: []
     * @Return: java.lang.String
     * @Author: Hacker_lsr@126.com
     */
    @RequiresPermissions("admin")
    @RequestMapping("/user/add")
    public String toadd(){
        return s+"/user_add";
    }
    /**
     * 功能描述: <br>
     * 〈〉新增
     * @Param: [user]
     * @Return: com.lsr.common.utils.Result
     * @Author: Hacker_lsr@126.com
     */
    @RequiresPermissions("insert")
    @RequestMapping("/add/user")
    @ResponseBody
    public Result addUser(User user){

        user.setUid(UUID.randomUUID().toString());
        int s = userMapper.insertSelective(user);
        if(s!=0){
            return Result.success("操作成功！");
        }
        return Result.error("操作失败！");
    }
    /**
     * 功能描述: <br>
     * 〈〉修改更新
     * @Param: [user]
     * @Return: com.lsr.common.utils.Result
     * @Author: Hacker_lsr@126.com
     */
    @RequiresPermissions("update")
    @RequestMapping("/updateUser")
    @ResponseBody
    public Result updateUser(User user){
       //获取consul 上的服务
       userMapper.updateByPrimaryKeySelective(user);
        return Result.success("操作成功！");
    }
    /**
     * 功能描述: <br>
     * 〈〉根据主键删除
     * @Param: [uid]
     * @Return: com.lsr.common.utils.Result
     * @Author: Hacker_lsr@126.com
     */
    @RequiresPermissions("delete")
    @RequestMapping("/delete/user")
    @ResponseBody
    public Result deleteUser(String uid){
        String time = System.currentTimeMillis()+"";
        RedisResult lock = redisInterFace.lock(uid, time);
        if (lock.getStatus()==200){
            userMapper.deleteByPrimaryKey(uid);
            // 测试 数据源切换
            //userMapper.selecTest("admin");
        }else {
            throw new RuntimeException(lock.getMessages());
        }
        RedisResult unlock = redisInterFace.unlock(uid, time);
        if (unlock.isB()){
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1",6379);
        jedis.ping();
        System.out.println(jedis.ping());
    }

}
