package cn.wuyi.dao.impl;

import cn.wuyi.dao.AnnoDao;
import cn.wuyi.domain.Anno;
import cn.wuyi.utils.JDBCUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AnnoDaoImpl implements AnnoDao {
    @Override
    public int addAnno(Anno anno) {
        String sql = "insert into anno_inf(title,content,date) value(?,?,?)";
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(anno.getDate());
        String[] info = {anno.getTitle(),anno.getContent(),date};
        int executeUpdate = JDBCUtils.executeUpdate(sql, info);
        return executeUpdate;
    }

    @Override
    public List<Anno> selectAnno(int page, int row) {
        String sql = "select * from anno_inf limit "+page+","+row;
        ResultSet resultSet = JDBCUtils.executeQuery(sql, null);
        List<Anno> list = new ArrayList<>();
        try {
            while(resultSet.next()){
                Anno anno = new Anno();
                anno.setId(resultSet.getInt(1));
                anno.setTitle(resultSet.getString(2));
                anno.setContent(resultSet.getString(3));
                anno.setDate(resultSet.getTimestamp(4));
                list.add(anno);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Anno findById(Integer id) {
        String sql ="select * from anno_inf where id = "+id;
        ResultSet resultSet = JDBCUtils.executeQuery(sql, null);
        Anno anno = null;
        try{
            if(resultSet.next()){
                anno = new Anno();
                anno.setId(resultSet.getInt(1));
                anno.setTitle(resultSet.getString(2));
                anno.setContent(resultSet.getString(3));
                anno.setDate(resultSet.getTimestamp(4));
            }
            return  anno;
        }catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public int updateAnno(Anno anno) {
        String sql = "update anno_inf set title=?,content=?,date=? where id="+anno.getId();
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(anno.getDate());
        String[] info = {anno.getTitle(),anno.getContent(),date};
        int executeUpdate = JDBCUtils.executeUpdate(sql, info);
        return executeUpdate;
    }

    @Override
    public int deleteAnno(Integer id) {
        String sql = "delete from anno_inf where id ="+ id;
        int executeUpdate = JDBCUtils.executeUpdate(sql, null);
        return executeUpdate;
    }

    @Override
    public int count() {
        String sql = "select count(*) from anno_inf";
        ResultSet resultSet = JDBCUtils.executeQuery(sql, null);
        try {
            if(resultSet.next()){
                int count = resultSet.getInt(1);
                return count;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
