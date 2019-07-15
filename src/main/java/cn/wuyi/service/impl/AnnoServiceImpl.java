package cn.wuyi.service.impl;

import cn.wuyi.dao.AnnoDao;
import cn.wuyi.dao.impl.AnnoDaoImpl;
import cn.wuyi.domain.Anno;
import cn.wuyi.domain.EasyUIPage;
import cn.wuyi.service.AnnoService;
import com.google.gson.*;

import java.util.List;

public class AnnoServiceImpl implements AnnoService {
    private AnnoDao annoDao = new AnnoDaoImpl();

    @Override
    public int addAnno(Anno anno) {
        return annoDao.addAnno(anno);
    }

    @Override
    public String selectAnno(int index, int row) {
        List<Anno> rows = annoDao.selectAnno(index, row);
        if(rows.isEmpty()){
            return null;
        }
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

        int total = annoDao.count();
        EasyUIPage easyUIPage = new EasyUIPage();
        easyUIPage.setTotal(total);
        easyUIPage.setRows(rows);
        return gson.toJson(easyUIPage);
    }

    @Override
    public Anno findById(Integer id) {
        return annoDao.findById(id);
    }

    @Override
    public int updateAnno(Anno anno) {
        return annoDao.updateAnno(anno);
    }

    @Override
    public int deleteAnno(Integer id) {
        return annoDao.deleteAnno(id);
    }
}
