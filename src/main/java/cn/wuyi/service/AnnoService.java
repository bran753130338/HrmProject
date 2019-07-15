package cn.wuyi.service;

import cn.wuyi.domain.Anno;

public interface AnnoService {
    //添加公告
    int addAnno(Anno anno);

    //查找公告返回Json格式
    String selectAnno(int index,int row);

    //通过ID查找公告
    Anno findById(Integer id);

    //更新公告信息
    int updateAnno(Anno anno);

    //删除公告
    int deleteAnno(Integer id);
}
