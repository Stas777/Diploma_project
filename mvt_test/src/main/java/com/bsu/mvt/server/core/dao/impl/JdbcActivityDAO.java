package com.bsu.mvt.server.core.dao.impl;

import com.bsu.mvt.server.core.dao.ActivityDAO;
import com.bsu.mvt.server.core.entity.ActivityEntity;
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

@Repository(value = "activityDAO")
public class JdbcActivityDAO extends JdbcDaoSupport implements ActivityDAO {
    private RowMapper<ActivityEntity> activityRowMapper;

    private final static class ActivityRowMapper implements RowMapper<ActivityEntity> {
        public ActivityEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            ActivityEntity e = new ActivityEntity();
            e.setId(DBUtil.getLong(rs, "id"));
            e.setStart_time(DBUtil.getLong(rs, "start_time"));
            e.setDuration(DBUtil.getLong(rs, "duration"));
            e.setSample_id(DBUtil.getLong(rs, "sample_id"));
            e.setMotion_type_id(DBUtil.getLong(rs, "motion_type_id"));
            e.setReglament_id(DBUtil.getLong(rs, "reglament_id"));
            e.setMark_confidence(DBUtil.getString(rs, "mark_confidence"));
            return e;
        }
    }

    @Autowired
    public JdbcActivityDAO(@Qualifier("dataSource") DataSource dataSource) {
        setDataSource(dataSource);
        activityRowMapper = new ActivityRowMapper();
    }

    // activity
    @Override
    public ActivityEntity readActivity(Long id) throws DataAccessException {
        String sql = "SELECT a.* FROM activity a WHERE a.id = ?";
        List<ActivityEntity> list = getJdbcTemplate().query(sql, activityRowMapper, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public Long createActivity(final ActivityEntity e) throws DataAccessException {
        final String sql = "INSERT INTO activity (start_time, duration, sample_id, motion_type_id, reglament_id, mark_confidence) VALUES(?,?,?,?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        if (getJdbcTemplate().update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
                        DBUtil.setLong(ps, 1, e.getStart_time());
                        DBUtil.setLong(ps, 2, e.getDuration());
                        DBUtil.setLong(ps, 3, e.getSample_id());
                        DBUtil.setLong(ps, 4, e.getMotion_type_id());
                        DBUtil.setLong(ps, 5, e.getReglament_id());
                        DBUtil.setString(ps, 6, e.getMark_confidence());
                        return ps;
                    }
                },
                keyHolder) == 1) {
            return (Long) keyHolder.getKey();
        }

        return null;
    }

    @Override
    public boolean updateActivity(ActivityEntity e) throws DataAccessException {
        String sql = "UPDATE activity SET start_time = ?, duration = ?, sample_id  = ?, motion_type_id  = ?," +
                " reglament_id = ?, mark_confidence = ? WHERE id = ?";
        int res = getJdbcTemplate().update(sql, e.getStart_time(), e.getDuration(), e.getSample_id(), e.getMotion_type_id(),
                e.getReglament_id(), e.getMark_confidence(), e.getId());
        return res == 1;
    }

    @Override
    public List<ActivityEntity> listActivity() throws DataAccessException {
        String sql = "SELECT a.* FROM activity a";
        List<ActivityEntity> list = getJdbcTemplate().query(sql, activityRowMapper);
        return list.isEmpty() ? null : list;
    }

    @Override
    public List<ActivityEntity> listActivity(Long sample_id) throws DataAccessException {
        String sql = "SELECT a.* FROM activity a WHERE a.sample_id = ?";
        List<ActivityEntity> list = getJdbcTemplate().query(sql, activityRowMapper, sample_id);
        return list.isEmpty() ? null : list;
    }

    @Override
    public boolean deleteActivityBySample(Long sample_id) {
        String sql = "DELETE FROM activity WHERE sample_id = ?";
        int res = getJdbcTemplate().update(sql, sample_id);
        return res == 1;
    }
}
