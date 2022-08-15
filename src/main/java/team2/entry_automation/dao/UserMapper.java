package team2.entry_automation.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import team2.entry_automation.entity.User;

/**
 * @author JackChern @create 2021-09-02 21:17
 */
@Component
@Mapper
public interface UserMapper {
    User selectById( int id );

    User selectByName( String name );

    int countByUserName( @Param("username")String username);

    User selectByEmail( String email );

    int insertUser( User user );


//    报错是因为有两个形参，MyBatis不知道哪个对应哪个，不知道为什么不能直接匹配形参名
//    加了@Param("id")就运行正常了
    int updateStatus( @Param("id") int id);

//   这是什么意思？更新头像
//    header:头像
    int updateHeader( @Param("id") int id, @Param("headerUrl") String headerUrl );

    int updatePassword( @Param("id") int id, @Param("password") String password );


}
