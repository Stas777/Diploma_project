package com.bsu.mvt.server.core.dao.impl;

import com.bsu.mvt.server.core.dao.FileDAO;
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

@Repository(value = "fileDAO")
public class JdbcFileDAO extends JdbcDaoSupport implements FileDAO {
    private RowMapper<VideoFileEntity> videoFileRowMapper;
    private RowMapper<SensorDataFileEntity> sensorDataFileRowMapper;


    private final static class VideoFileRowMapper implements RowMapper<VideoFileEntity> {
        public VideoFileEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            VideoFileEntity e = new VideoFileEntity();
            e.setId(DBUtil.getLong(rs, "id"));
            e.setPath(DBUtil.getString(rs, "path"));
            e.setFile_hash(DBUtil.getString(rs, "file_hash"));
            e.setDescription(DBUtil.getString(rs, "description"));
            e.setSample_id(DBUtil.getLong(rs, "sample_id"));
            return e;
        }
    }

    private final static class SensorDataFileRowMapper implements RowMapper<SensorDataFileEntity> {
        public SensorDataFileEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            SensorDataFileEntity e = new SensorDataFileEntity();
            e.setId(DBUtil.getLong(rs, "id"));
            e.setPath(DBUtil.getString(rs, "path"));
            e.setFile_hash(DBUtil.getString(rs, "file_hash"));
            e.setDescription(DBUtil.getString(rs, "description"));
            e.setCreate_date(DBUtil.getDate(rs, "create_date"));
            e.setSample_id(DBUtil.getLong(rs, "sample_id"));
            e.setQualifier_key_id(DBUtil.getLong(rs, "qualifier_key_id"));
            return e;
        }
    }

    @Autowired
    public JdbcFileDAO(@Qualifier("dataSource") DataSource dataSource) {
        setDataSource(dataSource);
        videoFileRowMapper = new VideoFileRowMapper();
        sensorDataFileRowMapper = new SensorDataFileRowMapper();
    }

    // video file
    @Override
    public VideoFileEntity readVideoFile(Long id) throws DataAccessException {
        String sql = "SELECT vf.* FROM video_file vf WHERE vf.id = ?";
        List<VideoFileEntity> list = getJdbcTemplate().query(sql, videoFileRowMapper, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public Long createVideoFile(final VideoFileEntity e) throws DataAccessException {
        final String sql = "INSERT INTO video_file (path, file_hash, description, sample_id) VALUES(?,?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        if (getJdbcTemplate().update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
                        DBUtil.setString(ps, 1, e.getPath());
                        DBUtil.setString(ps, 2, e.getFile_hash());
                        DBUtil.setString(ps, 3, e.getDescription());
                        DBUtil.setLong(ps, 4, e.getSample_id());
                        return ps;
                    }
                },
                keyHolder) == 1) {
            return (Long) keyHolder.getKey();
        }

        return null;
    }

    @Override
    public boolean updateVideoFile(VideoFileEntity e) throws DataAccessException {
        String sql = "UPDATE video_file SET path = ?, file_hash = ?, description  = ?, sample_id  = ? WHERE id = ?";
        int res = getJdbcTemplate().update(sql, e.getPath(), e.getFile_hash(), e.getDescription(), e.getSample_id(), e.getId());
        return res == 1;
    }

    @Override
    public List<VideoFileEntity> listVideoFile() throws DataAccessException {
        String sql = "SELECT vf.* FROM video_file vf";
        List<VideoFileEntity> list = getJdbcTemplate().query(sql, videoFileRowMapper);
        return list.isEmpty() ? null : list;
    }

    @Override
    public List<VideoFileEntity> listVideoFile(Long sample_id) throws DataAccessException {
        String sql = "SELECT vf.* FROM video_file vf WHERE vf.sample_id = ?";
        List<VideoFileEntity> list = getJdbcTemplate().query(sql, videoFileRowMapper, sample_id);
        return list.isEmpty() ? null : list;
    }

    @Override
    public boolean deleteVideoFileBySample(Long sample_id) {
        String sql = "DELETE FROM video_file WHERE sample_id = ?";
        int res = getJdbcTemplate().update(sql, sample_id);
        return res == 1;
    }

    // sensor data file
    @Override
    public SensorDataFileEntity readSensorDataFile(Long id) throws DataAccessException {
        String sql = "SELECT sdf.* FROM sensor_data_file sdf WHERE sdf.id = ?";
        List<SensorDataFileEntity> list = getJdbcTemplate().query(sql, sensorDataFileRowMapper, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public Long createSensorDataFile(final SensorDataFileEntity e) throws DataAccessException {
        final String sql = "INSERT INTO sensor_data_file (path, file_hash, description, create_date, " +
                "sample_id, qualifier_key_id) VALUES(?,?,?,?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        if (getJdbcTemplate().update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
                        DBUtil.setString(ps, 1, e.getPath());
                        DBUtil.setString(ps, 2, e.getFile_hash());
                        DBUtil.setString(ps, 3, e.getDescription());
                        DBUtil.setDate(ps, 4, e.getCreate_date());
                        DBUtil.setLong(ps, 5, e.getSample_id());
                        DBUtil.setLong(ps, 6, e.getQualifier_key_id());
                        return ps;
                    }
                },
                keyHolder) == 1) {
            return (Long) keyHolder.getKey();
        }

        return null;
    }

    @Override
    public boolean updateSensorDataFile(SensorDataFileEntity e) throws DataAccessException {
        String sql = "UPDATE sensor_data_file SET path = ?, file_hash = ?, description  = ?, create_date = ?," +
                " sample_id  = ?, qualifier_key_id = ? WHERE id = ?";
        int res = getJdbcTemplate().update(sql, e.getPath(), e.getFile_hash(), e.getDescription(), e.getCreate_date(),
                e.getSample_id(), e.getQualifier_key_id(), e.getId());
        return res == 1;
    }

    @Override
    public List<SensorDataFileEntity> listSensorDataFile() throws DataAccessException {
        String sql = "SELECT sdf.* FROM sensor_data_file sdf";
        List<SensorDataFileEntity> list = getJdbcTemplate().query(sql, sensorDataFileRowMapper);
        return list.isEmpty() ? null : list;
    }

    @Override
    public List<SensorDataFileEntity> listSensorDataFile(Long sample_id) throws DataAccessException {
        String sql = "SELECT sdf.* FROM sensor_data_file sdf  WHERE sdf.sample_id = ?";
        List<SensorDataFileEntity> list = getJdbcTemplate().query(sql, sensorDataFileRowMapper, sample_id);
        return list.isEmpty() ? null : list;
    }

    @Override
    public boolean deleteSensorDataFileBySample(Long sample_id) {
        String sql = "DELETE FROM sensor_data_file WHERE sample_id = ?";
        int res = getJdbcTemplate().update(sql, sample_id);
        return res == 1;
    }
}
