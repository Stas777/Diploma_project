package com.bsu.mvt.server.core.dao.impl;

import com.bsu.mvt.server.core.dao.CatalogDAO;
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
import java.util.*;

@Repository(value = "catalogDAO")
public class JdbcCatalogDAO extends JdbcDaoSupport implements CatalogDAO {
    private RowMapper<SportEntity> sportRowMapper;
    private RowMapper<SensorLocationEntity> sensorLocationRowMapper;
    private RowMapper<MotionTypeEntity> motionTypeRowMapper;
    private RowMapper<ReglamentEntity> reglamentRowMapper;

    private final static class SportRowMapper implements RowMapper<SportEntity> {
        public SportEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            SportEntity e = new SportEntity();
            e.setId(DBUtil.getLong(rs, "id"));
            e.setName(DBUtil.getString(rs, "name"));
            e.setReglament_id(DBUtil.getLong(rs, "reglament_id"));
            e.setValue(DBUtil.getInt(rs, "value"));
            return e;
        }
    }

    private final static class SensorLocationRowMapper implements RowMapper<SensorLocationEntity> {
        public SensorLocationEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            SensorLocationEntity e = new SensorLocationEntity();
            e.setId(DBUtil.getLong(rs, "id"));
            e.setName(DBUtil.getString(rs, "name"));
            e.setValue(DBUtil.getInt(rs, "value"));
            return e;
        }
    }

    private final static class MotionTypeRowMapper implements RowMapper<MotionTypeEntity> {
        public MotionTypeEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            MotionTypeEntity e = new MotionTypeEntity();
            e.setId(DBUtil.getLong(rs, "id"));
            e.setName(DBUtil.getString(rs, "name"));
            e.setReglament_id(DBUtil.getLong(rs, "reglament_id"));
            e.setValue(DBUtil.getInt(rs, "value"));
            return e;
        }
    }

    private final static class ReglamentRowMapper implements RowMapper<ReglamentEntity> {
        public ReglamentEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            ReglamentEntity e = new ReglamentEntity();
            e.setId(DBUtil.getLong(rs, "id"));
            e.setName(DBUtil.getString(rs, "name"));
            e.setMax_qualification_error(DBUtil.getFloat(rs, "max_qualification_error"));
            e.setMax_classification_error(DBUtil.getFloat(rs, "max_classification_error"));
            e.setMin_activity_match(DBUtil.getFloat(rs, "min_activity_match"));
            e.setScope_activity(DBUtil.getBoolean(rs, "scope_activity"));
            e.setScope_motion_type(DBUtil.getBoolean(rs, "scope_motion_type"));
            e.setScope_sample(DBUtil.getBoolean(rs, "scope_sample"));
            e.setScope_sport(DBUtil.getBoolean(rs, "scope_sport"));
            return e;
        }
    }

    @Autowired
    public JdbcCatalogDAO(@Qualifier("dataSource") DataSource dataSource) {
        setDataSource(dataSource);
        sportRowMapper = new SportRowMapper();
        sensorLocationRowMapper = new SensorLocationRowMapper();
        motionTypeRowMapper = new MotionTypeRowMapper();
        reglamentRowMapper = new ReglamentRowMapper();
    }

    // sport
    @Override
    public SportEntity readSport(Long id) throws DataAccessException {
        String sql = "SELECT s.* FROM sport s WHERE s.id = ?";
        List<SportEntity> list = getJdbcTemplate().query(sql, sportRowMapper, id);
//        List<SportEntity> list = getJdbcTemplate().query(sql, new Object[]{id}, new int[]{Types.BIGINT}, sportRowMapper);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public Long createSport(final SportEntity e) throws DataAccessException {
        final String sql = "INSERT INTO sport (name, reglament_id, value) VALUES(?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        if (getJdbcTemplate().update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
                        DBUtil.setString(ps, 1, e.getName());
                        DBUtil.setLong(ps, 2, e.getReglament_id());
                        DBUtil.setInteger(ps, 3, e.getValue());
                        return ps;
                    }
                },
                keyHolder) == 1) {
            return (Long) keyHolder.getKey();
        }

        return null;
    }

    @Override
    public boolean updateSport(SportEntity e) throws DataAccessException {
        String sql = "UPDATE sport SET name = ?, reglament_id  = ?, value = ? WHERE id = ?";
//        int res =  getJdbcTemplate().update(sql, new Object[]{e.getName(), e.getReglament_id(), e.getId()}, new int[]{Types.VARCHAR, Types.BIGINT, Types.BIGINT}) ;
        int res = getJdbcTemplate().update(sql, e.getName(), e.getReglament_id(), e.getValue(), e.getId());
        return res == 1;
    }

    @Override
    public List<SportEntity> listSport() throws DataAccessException {
        String sql = "SELECT s.* FROM sport s";
        List<SportEntity> list = getJdbcTemplate().query(sql, sportRowMapper);
        return list.isEmpty() ? null : list;
    }

    @Override
    public List<SportEntity> listSport(Long motionTypeId) throws DataAccessException {
        String sql = "SELECT s.* FROM sport s, sport_motion_type smt WHERE s.id = smt.sport_id AND smt.motion_type_id = ?";
        List<SportEntity> list = getJdbcTemplate().query(sql, sportRowMapper, motionTypeId);
        return list.isEmpty() ? null : list;
    }

    @Override
    public boolean assignSportToMotionType(Long sportId, List<Long> motionTypeIdList) throws DataAccessException {
        final String sql = "INSERT INTO sport_motion_type (sport_id, motion_type_id) VALUES(?,?)";
        List<Object[]> batchArgs = new ArrayList<>(motionTypeIdList.size());
        for (Long motionTypeId : motionTypeIdList) {
            batchArgs.add(new Object[]{sportId, motionTypeId});
        }
        int[] res = getJdbcTemplate().batchUpdate(sql, batchArgs);
        boolean result = true;
        for (int r : res) {
            if (r == 0) {
                result = false;
                break;
            }
        }
        return result;
    }

    @Override
    public boolean removeSportFromMotionType(Long sportId) throws DataAccessException {
        String sql = "DELETE FROM sport_motion_type WHERE sport_id = ?";
        int res = getJdbcTemplate().update(sql, sportId);
        return res == 1;    }

    // sensor location
    @Override
    public SensorLocationEntity readSensorLocation(Long id) throws DataAccessException {
        String sql = "SELECT sl.* FROM sensor_location sl WHERE sl.id = ?";
        List<SensorLocationEntity> list = getJdbcTemplate().query(sql, sensorLocationRowMapper, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public Long createSensorLocation(final SensorLocationEntity e) throws DataAccessException {
        final String sql = "INSERT INTO sensor_location (name, value) VALUES(?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        if (getJdbcTemplate().update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
                        DBUtil.setString(ps, 1, e.getName());
                        DBUtil.setInteger(ps, 2, e.getValue());
                        return ps;
                    }
                },
                keyHolder) == 1) {
            return (Long) keyHolder.getKey();
        }
        return null;
    }

    @Override
    public boolean updateSensorLocation(SensorLocationEntity e) throws DataAccessException {
        String sql = "UPDATE sensor_location SET name = ?, value = ? WHERE id = ?";
        int res = getJdbcTemplate().update(sql, e.getName(), e.getValue(), e.getId());
        return res == 1;
    }

    @Override
    public List<SensorLocationEntity> listSensorLocation() throws DataAccessException {
        String sql = "SELECT sl.* FROM sensor_location sl";
        List<SensorLocationEntity> list = getJdbcTemplate().query(sql, sensorLocationRowMapper);
        return list.isEmpty() ? null : list;
    }

    // motion type
    @Override
    public MotionTypeEntity readMotionType(Long id) throws DataAccessException {
        String sql = "SELECT mt.* FROM motion_type mt WHERE mt.id = ?";
        List<MotionTypeEntity> list = getJdbcTemplate().query(sql, motionTypeRowMapper, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public Long createMotionType(final MotionTypeEntity e) throws DataAccessException {
        final String sql = "INSERT INTO motion_type (name, reglament_id, value) VALUES(?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        if (getJdbcTemplate().update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
                        DBUtil.setString(ps, 1, e.getName());
                        DBUtil.setLong(ps, 2, e.getReglament_id());
                        DBUtil.setInteger(ps, 3, e.getValue());
                        return ps;
                    }
                },
                keyHolder) == 1) {
            return (Long) keyHolder.getKey();
        }
        return null;
    }

    @Override
    public boolean updateMotionType(MotionTypeEntity e) throws DataAccessException {
        String sql = "UPDATE motion_type SET name = ?, reglament_id = ?, value = ? WHERE id = ?";
        int res = getJdbcTemplate().update(sql, e.getName(), e.getReglament_id(), e.getValue(), e.getId());
        return res == 1;
    }

    @Override
    public List<MotionTypeEntity> listMotionType() throws DataAccessException {
        String sql = "SELECT mt.* FROM motion_type mt";
        List<MotionTypeEntity> list = getJdbcTemplate().query(sql, motionTypeRowMapper);
        return list.isEmpty() ? null : list;
    }

    @Override
    public List<MotionTypeEntity> listMotionType(Long sportId) throws DataAccessException {
        String sql = "SELECT mt.* FROM motion_type mt, sport_motion_type smt WHERE mt.id = smt.motion_type_id AND smt.sport_id = ?";
        List<MotionTypeEntity> list = getJdbcTemplate().query(sql, motionTypeRowMapper, sportId);
        return list.isEmpty() ? null : list;
    }

    @Override
    public boolean assignMotionTypeToSport(Long motionTypeId, List<Long> sportIdList) throws DataAccessException {
        final String sql = "INSERT INTO sport_motion_type (sport_id, motion_type_id) VALUES(?,?)";
        List<Object[]> batchArgs = new ArrayList<>(sportIdList.size());
        for (Long sportId : sportIdList) {
            batchArgs.add(new Object[]{sportId, motionTypeId});
        }
        int[] res = getJdbcTemplate().batchUpdate(sql, batchArgs);
        boolean result = true;
        for (int r : res) {
            if (r == 0) {
                result = false;
                break;
            }
        }
        return result;
    }

    @Override
    public boolean removeMotionTypeFromSport(Long motionTypeId) throws DataAccessException {
        String sql = "DELETE FROM sport_motion_type WHERE motion_type_id = ?";
        int res = getJdbcTemplate().update(sql, motionTypeId);
        return res == 1;
    }

    // reglament
    @Override
    public ReglamentEntity readReglament(Long id) throws DataAccessException {
        String sql = "SELECT r.* FROM reglament r WHERE r.id = ?";
        List<ReglamentEntity> list = getJdbcTemplate().query(sql, reglamentRowMapper, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public Long createReglament(final ReglamentEntity e) throws DataAccessException {
        final String sql = "INSERT INTO reglament (name, max_qualification_error, max_classification_error," +
                " min_activity_match, scope_sport, scope_sample, scope_activity, scope_motion_type) VALUES(?,?,?,?,?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        if (getJdbcTemplate().update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
                        DBUtil.setString(ps, 1, e.getName());
                        DBUtil.setFloat(ps, 2, e.getMax_qualification_error());
                        DBUtil.setFloat(ps, 3, e.getMax_classification_error());
                        DBUtil.setFloat(ps, 4, e.getMin_activity_match());
                        DBUtil.setBoolean(ps, 5, e.getScope_sport());
                        DBUtil.setBoolean(ps, 6, e.getScope_sample());
                        DBUtil.setBoolean(ps, 7, e.getScope_activity());
                        DBUtil.setBoolean(ps, 8, e.getScope_motion_type());
                        return ps;
                    }
                },
                keyHolder) == 1) {
            return (Long) keyHolder.getKey();
        }
        return null;
    }

    @Override
    public boolean updateReglament(ReglamentEntity e) throws DataAccessException {
        String sql = "UPDATE reglament SET name = ?, max_qualification_error = ?, max_classification_error = ?," +
                " min_activity_match = ?, scope_sport = ?, scope_sample = ?, scope_activity = ?, scope_motion_type = ?" +
                " WHERE id = ?";
        int res = getJdbcTemplate().update(sql, e.getName(), e.getMax_qualification_error(), e.getMax_classification_error(),
                e.getMin_activity_match(), e.getScope_sport(), e.getScope_sample(), e.getScope_activity(), e.getScope_motion_type(),
                e.getId());
        return res == 1;
    }

    @Override
    public List<ReglamentEntity> listReglament() throws DataAccessException {
        String sql = "SELECT r.* FROM reglament r";
        List<ReglamentEntity> list = getJdbcTemplate().query(sql, reglamentRowMapper);
        return list.isEmpty() ? null : list;
    }

}
