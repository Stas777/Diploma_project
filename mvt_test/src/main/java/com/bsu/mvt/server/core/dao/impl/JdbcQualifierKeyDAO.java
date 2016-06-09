package com.bsu.mvt.server.core.dao.impl;

import com.bsu.mvt.server.core.dao.QualifierKeyDAO;
import com.bsu.mvt.server.core.entity.QualifierKeyEntity;
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

@Repository(value = "qualifierKeyDAO")
public class JdbcQualifierKeyDAO extends JdbcDaoSupport implements QualifierKeyDAO {
    private RowMapper<QualifierKeyEntity> qualifierKeyRowMapper;

    private final static class QualifierKeyRowMapper implements RowMapper<QualifierKeyEntity> {
        public QualifierKeyEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            QualifierKeyEntity e = new QualifierKeyEntity();
            e.setId(DBUtil.getLong(rs, "id"));
            e.setName(DBUtil.getString(rs, "name"));
            e.setSignal_threshold(DBUtil.getFloat(rs, "signal_threshold"));
            e.setExtreme_delta(DBUtil.getFloat(rs, "extreme_delta"));
            e.setMin_activity_duration(DBUtil.getLong(rs, "min_activity_duration"));
            e.setSensor_location_id(DBUtil.getLong(rs, "sensor_location_id"));
            e.setSport_id(DBUtil.getLong(rs, "sport_id"));
            return e;
        }
    }

    @Autowired
    public JdbcQualifierKeyDAO(@Qualifier("dataSource") DataSource dataSource) {
        setDataSource(dataSource);
        qualifierKeyRowMapper = new QualifierKeyRowMapper();
    }

    // qualifierKey
    @Override
    public QualifierKeyEntity readQualifierKey(Long id) throws DataAccessException {
        String sql = "SELECT q.* FROM qualifier_key q WHERE q.id = ?";
        List<QualifierKeyEntity> list = getJdbcTemplate().query(sql, qualifierKeyRowMapper, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public Long createQualifierKey(final QualifierKeyEntity e) throws DataAccessException {
        final String sql = "INSERT INTO qualifier_key (name, signal_threshold, extreme_delta, min_activity_duration, " +
                "sensor_location_id, sport_id) VALUES(?,?,?,?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        if (getJdbcTemplate().update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
                        DBUtil.setString(ps, 1, e.getName());
                        DBUtil.setFloat(ps, 2, e.getSignal_threshold());
                        DBUtil.setFloat(ps, 3, e.getExtreme_delta());
                        DBUtil.setLong(ps, 4, e.getMin_activity_duration());
                        DBUtil.setLong(ps, 5, e.getSensor_location_id());
                        DBUtil.setLong(ps, 6, e.getSport_id());
                        return ps;
                    }
                },
                keyHolder) == 1) {
            return (Long) keyHolder.getKey();
        }

        return null;
    }

    @Override
    public boolean updateQualifierKey(QualifierKeyEntity e) throws DataAccessException {
        String sql = "UPDATE qualifier_key SET name = ?, signal_threshold = ?, extreme_delta  = ?, min_activity_duration  = ?, " +
                "sensor_location_id = ?, sport_id = ? WHERE id = ?";
        int res = getJdbcTemplate().update(sql, e.getName(), e.getSignal_threshold(), e.getExtreme_delta(), e.getMin_activity_duration(),
                e.getSensor_location_id(), e.getSport_id(), e.getId());
        return res == 1;
    }

    @Override
    public List<QualifierKeyEntity> listQualifierKey() throws DataAccessException {
        String sql = "SELECT q.* FROM qualifier_key q";
        List<QualifierKeyEntity> list = getJdbcTemplate().query(sql, qualifierKeyRowMapper);
        return list.isEmpty() ? null : list;
    }


}
