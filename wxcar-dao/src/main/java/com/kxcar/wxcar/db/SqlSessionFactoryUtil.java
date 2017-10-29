package com.kxcar.wxcar.db;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by uncar_000 on 20161211.
 */
public class SqlSessionFactoryUtil
{
    private static SqlSessionFactory sqlSessionFactory = null;
    private static final Class CLASS_LOCK = SqlSessionFactoryUtil.class;

    private SqlSessionFactoryUtil(){}


    public static SqlSessionFactory getInstance(){
        String resource = "mybatis-config.xml";
        Properties properties = null;
        //TODO: Max Yu 加入数据库连接串解密逻辑
        try{
            InputStream inputStream = Resources.getResourceAsStream(resource);
            //Reader reader = Resources.getResourceAsReader(resource);
            synchronized (CLASS_LOCK){
                if(sqlSessionFactory == null)sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            }

        }
        catch(IOException ex){

            Logger.getLogger(SqlSessionFactoryUtil.class.getName()).log(Level.SEVERE,null,ex);
        }
        catch(Exception e){
            Logger.getLogger(SqlSessionFactoryUtil.class.getName()).log(Level.SEVERE,null,e);
        }



        return sqlSessionFactory;
    }


    public SqlSession openSqlSession(){
        return sqlSessionFactory.openSession();
    }


}
