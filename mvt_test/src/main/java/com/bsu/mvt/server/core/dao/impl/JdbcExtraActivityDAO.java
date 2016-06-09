package com.bsu.mvt.server.core.dao.impl;

import com.bsu.mvt.server.core.dao.ExtraActivityDAO;
import com.bsu.mvt.server.core.entity.ExtraActivityEntity;
import com.bsu.mvt.server.core.util.DBUtil;
import org.springframework.beans.factory.annotation.*;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.*;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

@Repository(value = "extraActivityDAO")
public class JdbcExtraActivityDAO extends JdbcDaoSupport implements ExtraActivityDAO {
    private RowMapper<ExtraActivityEntity> extraActivityRowMapper;

    private final static class ExtraActivityRowMapper implements RowMapper<ExtraActivityEntity> {
        public ExtraActivityEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            ExtraActivityEntity e = new ExtraActivityEntity();
            e.setId(DBUtil.getLong(rs, "id"));
            e.setActivity_id(DBUtil.getLong(rs, "activity_id"));
            e.setType(DBUtil.getString(rs, "type"));
            e.setKey(DBUtil.getString(rs, "key"));
            e.setValue(DBUtil.getString(rs, "value"));
            return e;
        }
    }

    @Autowired
    public JdbcExtraActivityDAO(@Qualifier("dataSource") DataSource dataSource) {
        setDataSource(dataSource);
        extraActivityRowMapper = new ExtraActivityRowMapper();
    }

    // extraActivity
    @Override
    public ExtraActivityEntity readExtraActivity(Long id) throws DataAccessException {
        String sql = "SELECT ea.* FROM extra_activity ea WHERE ea.id = ?";
        List<ExtraActivityEntity> list = getJdbcTemplate().query(sql, extraActivityRowMapper, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public Long createExtraActivity(final ExtraActivityEntity e) throws DataAccessException {
        final String sql = "INSERT INTO extra_activity (`activity_id`, `type`, `key`, `value`) VALUES(?,?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        if (getJdbcTemplate().update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
                        DBUtil.setLong(ps, 1, e.getActivity_id());
                        DBUtil.setString(ps, 2, e.getType());
                        DBUtil.setString(ps, 3, e.getKey());
                        DBUtil.setString(ps, 4, e.getValue());
                        return ps;
                    }
                },
                keyHolder) == 1) {
            return (Long) keyHolder.getKey();
        }

        return null;
    }

    @Override
    public boolean updateExtraActivity(ExtraActivityEntity e) throws DataAccessException {
        String sql = "UPDATE extra_activity SET `activity_id` = ?, `type` = ?, `key`  = ?, `value`  = ? WHERE id = ?";
        int res = getJdbcTemplate().update(sql, e.getActivity_id(), e.getType(), e.getKey(), e.getValue(), e.getId());
        return res == 1;
    }

    @Override
    public List<ExtraActivityEntity> listExtraActivity() throws DataAccessException {
        String sql = "SELECT ea.* FROM extra_activity ea";
        List<ExtraActivityEntity> list = getJdbcTemplate().query(sql, extraActivityRowMapper);
        return list.isEmpty() ? null : list;
    }

    @Override
    public List<ExtraActivityEntity> listExtraActivity(Long activity_id) throws DataAccessException {
        String sql = "SELECT ea.* FROM extra_activity ea WHERE ea.activity_id = ?";
        List<ExtraActivityEntity> list = getJdbcTemplate().query(sql, extraActivityRowMapper, activity_id);
        return list.isEmpty() ? null : list;
    }

    @Override
    public boolean deleteExtraActivityByActivity(Long activity_id) {
        String sql = "DELETE FROM extra_activity WHERE activity_id = ?";
        int res = getJdbcTemplate().update(sql, activity_id);
        return res == 1;
    }
}
