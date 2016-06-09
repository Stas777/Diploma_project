package com.bsu.mvt.server.core.dao.impl;

import com.bsu.mvt.server.core.dao.LogDAO;
import com.bsu.mvt.server.core.entity.*;
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

@Repository(value = "logDAO")
public class JdbcLogDAO extends JdbcDaoSupport implements LogDAO {
    private RowMapper<LogEntity> logRowMapper;

    private final static class LogRowMapper implements RowMapper<LogEntity> {
        public LogEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            LogEntity e = new LogEntity();
            e.setId(DBUtil.getLong(rs, "id"));
            e.setUser_id(DBUtil.getLong(rs, "user_id"));
            e.setSample_id(DBUtil.getLong(rs, "sample_id"));
            e.setAction_id(DBUtil.getLong(rs, "action_id"));
            e.setAction_name(DBUtil.getString(rs, "action_name"));
            e.setAction_data(DBUtil.getString(rs, "action_data"));
            e.setAction_date(DBUtil.getDate(rs, "action_date"));
            return e;
        }
    }

    @Autowired
    public JdbcLogDAO(@Qualifier("dataSource") DataSource dataSource) {
        setDataSource(dataSource);
        logRowMapper = new LogRowMapper();
    }

    // log
    @Override
    public LogEntity readLog(Long id) throws DataAccessException {
        String sql = "SELECT l.*, la.name AS action_name FROM log l INNER JOIN log_action la ON l.action_id = la.id WHERE l.id = ?";
        List<LogEntity> list = getJdbcTemplate().query(sql, logRowMapper, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public Long createLog(final LogEntity e) throws DataAccessException {
        final String sql = "INSERT INTO log (user_id, sample_id, action_id, action_data, action_date) VALUES(?,?,?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        if (getJdbcTemplate().update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
                        DBUtil.setLong(ps, 1, e.getUser_id());
                        DBUtil.setLong(ps, 2, e.getSample_id());
                        DBUtil.setLong(ps, 3, e.getAction_id());
                        DBUtil.setString(ps, 4, e.getAction_data());
                        DBUtil.setDate(ps, 5, e.getAction_date());

                        return ps;
                    }
                },
                keyHolder) == 1) {
            return (Long) keyHolder.getKey();
        }

        return null;
    }

    @Override
    public List<LogEntity> listLog() throws DataAccessException {
        String sql = "SELECT l.*, la.name AS action_name FROM log l INNER JOIN log_action la ON l.action_id = la.id ";
        List<LogEntity> list = getJdbcTemplate().query(sql, logRowMapper);
        return list.isEmpty() ? null : list;
    }

    @Override
    public List<LogEntity> listLogByUser(Long userId) throws DataAccessException {
        String sql = "SELECT l.*, la.name AS action_name FROM log l INNER JOIN log_action la ON l.action_id = la.id WHERE l.user_id = ?";
        List<LogEntity> list = getJdbcTemplate().query(sql, logRowMapper, userId);
        return list.isEmpty() ? null : list;
    }

    @Override
    public List<LogEntity> listLogBySample(Long sampleId) throws DataAccessException {
        String sql = "SELECT l.*, la.name AS action_name FROM log l INNER JOIN log_action la ON l.action_id = la.id WHERE l.sample_id = ?";
        List<LogEntity> list = getJdbcTemplate().query(sql, logRowMapper, sampleId);
        return list.isEmpty() ? null : list;
    }

    @Override
    public List<LogEntity> listLogByUserAndSample(Long userId, Long sampleId) throws DataAccessException {
        String sql = "SELECT l.*, la.name AS action_name FROM log l INNER JOIN log_action la ON l.action_id = la.id WHERE l.user_id = ? AND l.sample_id = ?";
        List<LogEntity> list = getJdbcTemplate().query(sql, logRowMapper, userId, sampleId);
        return list.isEmpty() ? null : list;
    }
}
