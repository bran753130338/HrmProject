package cn.wuyi.dao;

import cn.wuyi.domain.Anno;

import java.util.List;

public interface AnnoDao {
    //添加公告
    int addAnno(Anno anno);

    //使用easyui提供的分页查询，page页码，初始值1；row，每页显示行数
    List<Anno> selectAnno(int page,int row);

    //根据编号查询
    Anno findById(Integer id);

    //更新操作
    int updateAnno(Anno anno);

    //删除操作
    int deleteAnno(Integer id);

    int count();
}
