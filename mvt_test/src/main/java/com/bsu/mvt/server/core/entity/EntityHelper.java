package com.bsu.mvt.server.core.entity;

import com.bsu.mvt.server.rest.model.*;

import java.util.*;

public class EntityHelper {
    public static Sport convert(SportEntity e) {
        Sport s = new Sport();
        s.setId(e.getId());
        s.setName(e.getName());
        s.setValue(e.getValue());
        if (e.getReglament_id() != null && e.getReglament_id() != 0) {
            s.setReglament(new Reglament(e.getReglament_id()));
        }
        return s;
    }

    public static SportEntity convert(Sport s) {
        SportEntity se = new SportEntity();
        se.setId(s.getId());
        se.setName(s.getName());
        se.setValue(s.getValue());
        if (s.getReglament() != null && s.getReglament().getId() != 0) {
            se.setReglament_id(s.getReglament().getId());
        }
        return se;
    }

    public static SensorLocation convert(SensorLocationEntity e) {
        SensorLocation sl = new SensorLocation();
        sl.setId(e.getId());
        sl.setName(e.getName());
        sl.setValue(e.getValue());
        return sl;
    }

    public static SensorLocationEntity convert(SensorLocation sl) {
        SensorLocationEntity e = new SensorLocationEntity();
        e.setId(sl.getId());
        e.setName(sl.getName());
        e.setValue(sl.getValue());
        return e;

    }

    public static MotionType convert(MotionTypeEntity e) {
        MotionType mt = new MotionType();
        mt.setId(e.getId());
        mt.setName(e.getName());
        mt.setValue(e.getValue());
        if (e.getReglament_id() != null && e.getReglament_id() != 0) {
            mt.setReglament(new Reglament(e.getReglament_id()));
        }
        return mt;
    }

    public static MotionTypeEntity convert(MotionType mt) {
        MotionTypeEntity e = new MotionTypeEntity();
        e.setId(mt.getId());
        e.setName(mt.getName());
        e.setValue(mt.getValue());

        if (mt.getReglament() != null && mt.getReglament().getId() != 0) {
            e.setReglament_id(mt.getReglament().getId());
        }

        return e;

    }

    public static Sample convert(SampleEntity e) {
        Sample sa = new Sample();
        sa.setId(e.getId());
        sa.setUsage(e.getUsage());
        sa.setDescription(e.getDescription());
        sa.setPlayerLevel(PlayerLevel.get(e.getPlayer_level()));

        if (e.getReglament_id() != null && e.getReglament_id() != 0) {
            sa.setReglament(new Reglament(e.getReglament_id()));
        }
        if (e.getSport_id() != null && e.getSport_id() != 0) {
            sa.setSport(new Sport(e.getSport_id()));
        }
        return sa;
    }

    public static SampleEntity convert(Sample sa) {
        SampleEntity e = new SampleEntity();
        e.setId(sa.getId());
        e.setDescription(sa.getDescription());
        e.setUsage(sa.getUsage());
        e.setPlayer_level(sa.getPlayerLevel() != null ? sa.getPlayerLevel().name() : null);

        if (sa.getReglament() != null && sa.getReglament().getId() != 0) {
            e.setReglament_id(sa.getReglament().getId());
        }

        if (sa.getSport() != null && sa.getSport().getId() != 0) {
            e.setSport_id(sa.getSport().getId());
        }


        return e;
    }

    public static Reglament convert(ReglamentEntity e) {
        Reglament r = new Reglament();
        r.setId(e.getId());
        r.setName(e.getName());

        r.setMaxQualificationError(e.getMax_qualification_error());
        r.setMaxClassificationError(e.getMax_classification_error());
        r.setMinActivityMatch(e.getMin_activity_match());

        Set<Scope.Type> s = new HashSet<>();

        if (e.getScope_activity()) {
            s.add(Scope.Type.ACTIVITY);
        }
        if (e.getScope_sport()) {
            s.add(Scope.Type.SPORT);
        }
        if (e.getScope_sample()) {
            s.add(Scope.Type.SAMPLE);
        }
        if (e.getScope_motion_type()) {
            s.add(Scope.Type.MOTION_TYPE);
        }

        r.setScope(new Scope(s));

        return r;
    }

    public static ReglamentEntity convert(Reglament r) {
        ReglamentEntity e = new ReglamentEntity();
        e.setId(r.getId());
        e.setName(r.getName());

        e.setMax_qualification_error(r.getMaxQualificationError());
        e.setMax_classification_error(r.getMaxClassificationError());
        e.setMin_activity_match(r.getMinActivityMatch());

        Set<Scope.Type> v = r.getScope().getValues();

        e.setScope_activity(v.contains(Scope.Type.ACTIVITY));
        e.setScope_motion_type(v.contains(Scope.Type.MOTION_TYPE));
        e.setScope_sample(v.contains(Scope.Type.SAMPLE));
        e.setScope_sport(v.contains(Scope.Type.SPORT));

        return e;
    }

    public static Activity convert(ActivityEntity e) {
        Activity a = new Activity();
        a.setId(e.getId());
        a.setStartTime(e.getStart_time());
        a.setDuration(e.getDuration());
        a.setMarkConfidence(MarkConfidence.get(e.getMark_confidence()));

        if (e.getSample_id() != null && e.getSample_id() != 0) {
            a.setSample(new Sample(e.getSample_id()));
        }
        if (e.getMotion_type_id() != null && e.getMotion_type_id() != 0) {
            a.setMotionType(new MotionType(e.getMotion_type_id()));
        }
        if (e.getReglament_id() != null && e.getReglament_id() != 0) {
            a.setReglament(new Reglament(e.getReglament_id()));
        }
        return a;
    }

    public static ActivityEntity convert(Activity a) {
        ActivityEntity e = new ActivityEntity();
        e.setId(a.getId());
        e.setStart_time(a.getStartTime());
        e.setDuration(a.getDuration());
        e.setMark_confidence(a.getMarkConfidence() != null ? a.getMarkConfidence().name() : null);

        if (a.getSample() != null && a.getSample().getId() != 0) {
            e.setSample_id(a.getSample().getId());
        }
        if (a.getMotionType() != null && a.getMotionType().getId() != 0) {
            e.setMotion_type_id(a.getMotionType().getId());
        }
        if (a.getReglament() != null && a.getReglament().getId() != 0) {
            e.setReglament_id(a.getReglament().getId());
        }
        return e;
    }

    public static QualifierKey convert(QualifierKeyEntity e) {
        QualifierKey q = new QualifierKey();
        q.setId(e.getId());
        q.setName(e.getName());
        q.setSignalThreshold(e.getSignal_threshold());
        q.setExtremeDelta(e.getExtreme_delta());
        q.setMinActivityDuration(e.getMin_activity_duration());


        if (e.getSport_id() != null && e.getSport_id() != 0) {
            q.setSport(new Sport(e.getSport_id()));
        }

        if (e.getSensor_location_id() != null && e.getSensor_location_id() != 0) {
            q.setSensorLocation(new SensorLocation(e.getSensor_location_id()));
        }
        return q;
    }

    public static QualifierKeyEntity convert(QualifierKey q) {
        QualifierKeyEntity e = new QualifierKeyEntity();
        e.setId(q.getId());
        e.setName(q.getName());
        e.setSignal_threshold(q.getSignalThreshold());
        e.setExtreme_delta(q.getExtremeDelta());
        e.setMin_activity_duration(q.getMinActivityDuration());

        if (q.getSensorLocation() != null && q.getSensorLocation().getId() != 0) {
            e.setSensor_location_id(q.getSensorLocation().getId());
        }
        if (q.getSport() != null && q.getSport().getId() != 0) {
            e.setSport_id(q.getSport().getId());
        }
        return e;
    }

    public static VideoFile convert(VideoFileEntity e) {
        VideoFile f = new VideoFile();
        f.setId(e.getId());
        f.setPath(e.getPath());
        f.setHash(e.getFile_hash());
        f.setDescription(e.getDescription());

        if (e.getSample_id() != null && e.getSample_id() != 0) {
            f.setSample(new Sample(e.getSample_id()));
        }
        return f;
    }

    public static VideoFileEntity convert(VideoFile f) {
        VideoFileEntity e = new VideoFileEntity();
        e.setId(f.getId());
        e.setPath(f.getPath());
        e.setFile_hash(f.getHash());
        e.setDescription(f.getDescription());

        if (f.getSample() != null && f.getSample().getId() != 0) {
            e.setSample_id(f.getSample().getId());
        }
        return e;
    }

    public static SensorDataFile convert(SensorDataFileEntity e) {
        SensorDataFile f = new SensorDataFile();
        f.setId(e.getId());
        f.setPath(e.getPath());
        f.setHash(e.getFile_hash());
        f.setDescription(e.getDescription());
        f.setCreateDate(e.getCreate_date());

        if (e.getSample_id() != null && e.getSample_id() != 0) {
            f.setSample(new Sample(e.getSample_id()));
        }
        if (e.getQualifier_key_id() != null && e.getQualifier_key_id() != 0) {
            f.setQualifierKey(new QualifierKey(e.getQualifier_key_id()));
        }
        return f;
    }

    public static SensorDataFileEntity convert(SensorDataFile f) {
        SensorDataFileEntity e = new SensorDataFileEntity();
        e.setId(f.getId());
        e.setPath(f.getPath());
        e.setFile_hash(f.getHash());
        e.setDescription(f.getDescription());
        e.setCreate_date(f.getCreateDate());

        if (f.getSample() != null && f.getSample().getId() != 0) {
            e.setSample_id(f.getSample().getId());
        }

        if (f.getQualifierKey() != null && f.getQualifierKey().getId() != 0) {
            e.setQualifier_key_id(f.getQualifierKey().getId());
        }
        return e;
    }

    public static User convert(UserEntity e) {
        User u = new User();
        u.setId(e.getId());
        u.setFistName(e.getFirst_name());
        u.setLastName(e.getLast_name());
        u.setEmail(e.getEmail());
        // do not return password for security reason
//        u.setPassword(e.getPassword());

        return u;
    }

    public static UserEntity convert(User u) {
        UserEntity e = new UserEntity();
        e.setId(u.getId());
        e.setFirst_name(u.getFistName());
        e.setLast_name(u.getLastName());
        e.setEmail(u.getEmail());
        e.setPassword(u.getPassword());
        return e;
    }

    public static Log convert(LogEntity e) {
        Log l = new Log();
        l.setId(e.getId());
        l.setAction(new Action(e.getAction_id(), e.getAction_name(), e.getAction_data(), e.getAction_date()));
        l.setSample(new Sample(e.getSample_id()));
        l.setUser(new User(e.getUser_id()));

        return l;
    }

    public static LogEntity convert(Log l) {
        LogEntity e = new LogEntity();
        e.setId(l.getId());
        e.setAction_id(l.getAction().getId());
        e.setAction_data(l.getAction().getData());
        e.setAction_date(l.getAction().getDate());
        e.setSample_id(l.getSample().getId());
        e.setUser_id(l.getUser().getId());
        return e;
    }

    public static Extra convert(ExtraSampleEntity e) {
        Extra b = new Extra();
        b.setId(e.getId());
        b.setType(e.getType());
        b.setKey(e.getKey());
        b.setValue(e.getValue());

        if (e.getSample_id() != null && e.getSample_id() != 0) {
            b.setSample(new Sample(e.getSample_id()));
        }
        return b;
    }

    public static ExtraSampleEntity convert2ExtraSample(Extra b) {
        ExtraSampleEntity e = new ExtraSampleEntity();
        e.setId(b.getId());
        e.setType(b.getType());
        e.setKey(b.getKey());
        e.setValue(b.getValue());

        if (b.getSample() != null && b.getSample().getId() != 0) {
            e.setSample_id(b.getSample().getId());
        }
        return e;
    }

    public static Extra convert(ExtraActivityEntity e) {
        Extra b = new Extra();
        b.setId(e.getId());
        b.setType(e.getType());
        b.setKey(e.getKey());
        b.setValue(e.getValue());

        if (e.getActivity_id() != null && e.getActivity_id() != 0) {
            b.setActivity(new Activity(e.getActivity_id()));
        }
        return b;
    }

    public static ExtraActivityEntity convert2ExtraActivity(Extra b) {
        ExtraActivityEntity e = new ExtraActivityEntity();
        e.setId(b.getId());
        e.setType(b.getType());
        e.setKey(b.getKey());
        e.setValue(b.getValue());

        if (b.getActivity() != null && b.getActivity().getId() != 0) {
            e.setActivity_id(b.getActivity().getId());
        }
        return e;
    }

    public static ExtraFile convert(ExtraFileEntity e) {
        ExtraFile b = new ExtraFile();
        b.setId(e.getId());
        b.setDescription(e.getDescription());
        b.setDisplayName(e.getDisplay_name());
        b.setHash(e.getFile_hash());
        b.setPath(e.getPath());

        if (e.getSample_id() != null && e.getSample_id() != 0) {
            b.setSample(new Sample(e.getSample_id()));
        }

        return b;
    }

    public static ExtraFileEntity convert2ExtraFile(ExtraFile b) {
        ExtraFileEntity e = new ExtraFileEntity();
        e.setId(b.getId());
        e.setDescription(b.getDescription());
        e.setDisplay_name(b.getDisplayName());
        e.setFile_hash(b.getHash());
        e.setPath(b.getPath());

        if (b.getSample() != null && b.getSample().getId() != 0) {
            e.setSample_id(b.getSample().getId());
        }
        return e;
    }

}
