package com.bsu.mvt.server.core.dao.impl;

import com.bsu.mvt.server.core.dao.SampleDAO;
import com.bsu.mvt.server.core.entity.SampleEntity;
import com.bsu.mvt.server.core.util.DBUtil;
import com.bsu.mvt.server.rest.model.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.*;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

@Repository(value = "sampleDAO")
public class JdbcSampleDAO extends JdbcDaoSupport implements SampleDAO {
    private RowMapper<SampleEntity> sampleRowMapper;

    private final static class SampleRowMapper implements RowMapper<SampleEntity> {
        public SampleEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            SampleEntity e = new SampleEntity();
            e.setId(DBUtil.getLong(rs, "id"));
            e.setReglament_id(DBUtil.getLong(rs, "reglament_id"));
            e.setSport_id(DBUtil.getLong(rs, "sport_id"));
            e.setDescription(DBUtil.getString(rs, "description"));
            e.setUsage(DBUtil.getString(rs, "sample_usage") != null ? Usage.valueOf(DBUtil.getString(rs, "sample_usage")) : null);
            e.setPlayer_level(DBUtil.getString(rs, "player_level"));
            return e;
        }
    }

    @Autowired
    public JdbcSampleDAO(@Qualifier("dataSource") DataSource dataSource) {
        setDataSource(dataSource);
        sampleRowMapper = new SampleRowMapper();
    }

    // sample
    @Override
    public SampleEntity readSample(Long id) throws DataAccessException {
        String sql = "SELECT sa.* FROM sample sa WHERE sa.id = ?";
        List<SampleEntity> list = getJdbcTemplate().query(sql, sampleRowMapper, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public Long createSample(final SampleEntity e) throws DataAccessException {
        final String sql = "INSERT INTO sample (sample_usage, description, sport_id, reglament_id, player_level) VALUES(?,?,?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        if (getJdbcTemplate().update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
                        DBUtil.setStringAsChar(ps, 1, e.getUsage().name());
                        DBUtil.setString(ps, 2, e.getDescription());
                        DBUtil.setLong(ps, 3, e.getSport_id());
                        DBUtil.setLong(ps, 4, e.getReglament_id());
                        DBUtil.setString(ps, 5, e.getPlayer_level());
                        return ps;
                    }
                },
                keyHolder) == 1) {
            return (Long) keyHolder.getKey();
        }

        return null;
    }

    @Override
    public boolean updateSample(SampleEntity e) throws DataAccessException {
        String sql = "UPDATE sample SET sample_usage = ?, description = ?, sport_id  = ?, reglament_id  = ?, player_level = ? WHERE id = ?";
        int res = getJdbcTemplate().update(sql, e.getUsage().name(), e.getDescription(), e.getSport_id(), e.getReglament_id(), e.getPlayer_level(),
                e.getId());
        return res == 1;
    }

    @Override
    public List<SampleEntity> listSample() throws DataAccessException {
        String sql = "SELECT sa.* FROM sample sa";
        List<SampleEntity> list = getJdbcTemplate().query(sql, sampleRowMapper);
        return list.isEmpty() ? null : list;
    }

    @Override
    public List<SampleEntity> listSample(Long sportId) throws DataAccessException {
        String sql = "SELECT sa.* FROM sample sa WHERE sa.sport_id = ?";
        List<SampleEntity> list = getJdbcTemplate().query(sql, sampleRowMapper, sportId);
        return list.isEmpty() ? null : list;
    }


}
