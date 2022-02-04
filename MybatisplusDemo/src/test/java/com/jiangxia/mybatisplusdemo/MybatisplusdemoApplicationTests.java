package com.jiangxia.mybatisplusdemo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiangxia.mybatisplusdemo.bean.User;
import com.jiangxia.mybatisplusdemo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.jws.soap.SOAPBinding;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class MybatisplusdemoApplicationTests {

    @Autowired
    //下述userMapper会红色报错，可以不理会 也能运行，解决办法：UserMapper上加@Service或者@Mapper、@Repository注解
    UserMapper userMapper;
    @Test
    void contextLoads() {
    }

    @Test
    //查询所有数据
    //sql底层执行过程：
    //    Creating a new SqlSession
    //    SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@70211e49] was not registered for synchronization because synchronization is not active
    //    JDBC Connection [HikariProxyConnection@1335256857 wrapping com.mysql.cj.jdbc.ConnectionImpl@18ac53e8] will not be managed by Spring
    //==>  Preparing: SELECT id,name,age,email FROM user
    //==> Parameters:
    //            <==    Columns: id, name, age, email
    //<==        Row: 1, 亚索, 24, yasuo@lol.com
    //<==        Row: 2, 李青, 26, liqing@lolo.com
    //<==        Row: 3, 艾瑞莉娅, 23, daomei@lolo.com
    //<==        Row: 4, 卡莎, 24, kasha@lol.com
    //<==        Row: 5, 薇恩, 31, weien@lolo.com
    //<==        Row: 6, 盖伦, 44, gailun@lolo.com
    //<==      Total: 6

    public void findAll(){
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    //插入数据
    @Test
    public void insertUser(){
        User user = new User();
        user.setName("deranwen");
        user.setAge(23);
        user.setEmail("delaiwen@lol.com");
        int insertCount = userMapper.insert(user);
        System.out.println("插入数据的条数是："+insertCount);
    }

    //测试插入数据自动填充
    @Test
    public void insertUser1(){
        User user = new User();
        user.setName("艾希2");
        user.setAge(33);
        user.setEmail("hanbing2@lol.com");
        int insertCount = userMapper.insert(user);
        System.out.println("插入数据的条数是："+insertCount);
    }

    //更新
    @Test
    public void updateUser(){
        User user = new User();
        user.setId(1l);
        user.setAge(99);
        int updateCount = userMapper.updateById(user);
        System.out.println(updateCount);
    }

    //测试修改数据自动填充
    @Test
    public void updateUser1(){
        User user = new User();
        user.setId(1l);
        user.setName("托儿索");
        int updateCount = userMapper.updateById(user);
        System.out.println(updateCount);
    }

    //测试乐观锁
    @Test
    public void testOptimisticLocker(){
        User user = userMapper.selectById(1489253660100706305l);
        user.setAge(11110);
        userMapper.updateById(user);
    }

    //多个id的批量查询
    @Test
    public void selectuserbatchids(){
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1,2,3));
        System.out.println(users);
    }

    //根据条件查询
    @Test
    public void selectuserbymap(){
        HashMap<String,Object> map = new HashMap<>();
        map.put("age",26);
        map.put("email","liqing@lolo.com");
        List<User> users = userMapper.selectByMap(map);
        System.out.println(users);
    }

    //分页查询
    @Test
    public void pagetest(){
        //1、创建page对象
        // 传入两个参数：当前页和每页显示的记录数
        Page<User> page = new Page<>(1,2);
        //调用mp分页查询的方法，掉用mp分页查询过程中，底层封装，
        // 把分页所有数据封装到page对象里面
        //两个参数：一个是page对象，一个是条件对象，可以为null
        userMapper.selectPage(page,null);

        //通过page对象获取分页数据
        //当前页
        System.out.println(page.getCurrent());
        //每页数据list集合
        System.out.println(page.getRecords());
        //每页显示的记录数
        System.out.println(page.getSize());
        //总记录数
        System.out.println(page.getTotal());
        //总页数
        System.out.println(page.getPages());
        //是否有下一页、上一页
        System.out.println(page.hasNext());
        System.out.println(page.hasPrevious());
    }

    //根据id删除操作
    @Test
    public void deletebyid(){
        int result = userMapper.deleteById(1489438016182263810l);
        System.out.println(result);
    }

    //根据id批量删除操作
    @Test
    public void deletebybatchid(){
        int result = userMapper.deleteBatchIds(Arrays.asList(1489160121316552706l,1488850305184493570l));
        System.out.println(result);
    }

    //按照条件删除
    @Test
    public void deletebymap(){
        HashMap<String,Object> map = new HashMap<>();
        map.put("age",44);
        map.put("email","gailun@lolo.com");
        int result = userMapper.deleteByMap(map);
        System.out.println(result);
    }

    //测试 逻辑删除:先插入一条数据，自动填充deleted为0，删除后字段变成1,执行findall方法会排除delete等于1的数据
    @Test
    public void testLogicDelete() {
        int result = userMapper.deleteById(2l);
        System.out.println(result);
    }

    //测试复杂的条件查询
    @Test
    public void testSelectQuery(){
        //创建queryWrapper对象
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //通过QueryWrapper设置条件
        //ge、gt、le、lt
        //查询age>=30记录
        //第一个参数字段名称，第二个参数设置值
        //queryWrapper.ge("age",30);

        //eq、ne
        //queryWrapper.eq("name","德莱文");
        //queryWrapper.ne("name","德莱文");

        //between
        //查询年龄 20-30
        // queryWrapper.between("age",20,30);

        //like
        //queryWrapper.like("name","艾");

        //orderByDesc
        // queryWrapper.orderByDesc("id");

        //last
        //queryWrapper.last("limit 1");

        //指定要查询的列
        queryWrapper.select("id","name");

        List<User> users = userMapper.selectList(queryWrapper);
        System.out.println(users);
    }
}
