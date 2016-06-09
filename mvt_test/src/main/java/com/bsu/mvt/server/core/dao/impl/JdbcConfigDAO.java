package com.bsu.mvt.server.core.dao.impl;

import com.bsu.mvt.server.core.dao.ConfigDAO;
import com.bsu.mvt.server.core.entity.ConfigEntity;
import com.bsu.mvt.server.core.util.DBUtil;
import org.springframework.beans.factory.annotation.*;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

@Repository(value = "configDAO")
public class JdbcConfigDAO extends JdbcDaoSupport implements ConfigDAO {
    private RowMapper<ConfigEntity> configRowMapper;

    private final static class ConfigRowMapper implements RowMapper<ConfigEntity> {
        public ConfigEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            ConfigEntity e = new ConfigEntity();
            e.setName(DBUtil.getString(rs, "name"));
            e.setValue(DBUtil.getString(rs, "value"));
            return e;
        }
    }

    @Autowired
    public JdbcConfigDAO(@Qualifier("dataSource") DataSource dataSource) {
        setDataSource(dataSource);
        configRowMapper = new ConfigRowMapper();
    }


    @Override
    public List<ConfigEntity> listConfig() throws DataAccessException {
        String sql = "SELECT c.* FROM config c";
        List<ConfigEntity> list = getJdbcTemplate().query(sql, configRowMapper);
        return list.isEmpty() ? null : list;
    }
}
