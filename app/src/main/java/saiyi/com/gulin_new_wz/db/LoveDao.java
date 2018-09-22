package saiyi.com.gulin_new_wz.db;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import saiyi.com.gulin_new_wz.MyApplication;
import saiyi.com.gulin_new_wz.db.gen.UserDao;

public class LoveDao {

    /**
     * 添加数据
     *
     * @param shop
     */
    public static void insertLove(User shop)
    {
        MyApplication.getDaoInstant().getUserDao().insert(shop);

    }

    /**
     * 删除数据
     *
     * @param id
     */
    public static void deleteLove(long id)
    {
        MyApplication.getDaoInstant().getUserDao().deleteByKey(id);
    }

    /** 删除表内所有数据*/

    public static void deleteLoveALL()
    {
        MyApplication.getDaoInstant().getUserDao().deleteAll();

    }

    /**
     * 更新数据
     *
     * @param shop
     */
    public static void updateLove(User shop) {
        MyApplication.getDaoInstant().getUserDao().update(shop);

    }

    /**
     * 查询条件为Type=TYPE_LOVE的数据
     *     这里设置是查询条件 按什么来查询的数据      后面的
     * @return
     */
    public static List<User> queryUserLove(long id) {                                               //这个意思 如果 type = TYPE_USER；这是个查询条件
                                                                                         //eq等于。gt大于。ge大于等于
        return MyApplication.getDaoInstant().getUserDao().queryBuilder().where(UserDao.Properties.Id.eq(id)).list();


    }



    /** 查询   获取整个表的数据集合,一句代码就搞定！*/

    public static List<User> queryUserALL()
    {
        return MyApplication.getDaoInstant().getUserDao().loadAll();

    }


    /**单条查询  通过id查找其类型字段值 */

    public  static String getTypeValue(int id)
    {
        QueryBuilder<User> qb = MyApplication.getDaoInstant().getUserDao().queryBuilder();
        qb.where(UserDao.Properties.Id.eq(id));      //通过id字段查询，具体使用可修改别的字段
        if (qb.list().size() > 0)
        {
            return qb.list().get(0).getName(); //根据id获取它的name字段的值。具体用，具体修改
        }
        else
        {
            return "0";
        }
    }
    /**单条查询  通过id查找其类型字段值 */

}