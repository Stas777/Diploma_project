package com.bsu.mvt.server.swagger.config;

import com.fasterxml.classmate.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mangofactory.swagger.configuration.ExtensibilityModule;
import com.mangofactory.swagger.models.*;
import com.bsu.mvt.server.rest.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

import static com.mangofactory.swagger.models.IgnorableTypeRule.ignorable;

public class ExampleExtensibilityModule extends ExtensibilityModule {
    private final ObjectMapper documentationObjectMapper;

    @Autowired
    public ExampleExtensibilityModule(ObjectMapper documentationObjectMapper) {
        this.documentationObjectMapper = documentationObjectMapper;
    }

    @Override
    protected void customizeTypeProcessingRules(List<TypeProcessingRule> rules) {
        rules.add(ignorable(UriComponentsBuilder.class));
        rules.addAll(responseEntityAlternate());

    }

    public static List<AlternateTypeProcessingRule> responseEntityAlternate() {
        TypeResolver resolver = new TypeResolver();
        List<AlternateTypeProcessingRule> r = new ArrayList<>();
        r.add(alternate(resolver.resolve(ResponseEntity.class, Activity.class), resolver.resolve(Activity.class)));
//        r.add(alternate(resolver.resolve(ResponseEntity.class, new GenericType<List<Activity>>() {}), resolver.resolve(new GenericType<List<Activity>>() {})));
        r.add(alternate(resolver.resolve(ResponseEntity.class, resolver.resolve(List.class, Activity.class)), resolver.resolve(resolver.resolve(List.class, Activity.class))));
        r.add(alternate(resolver.resolve(ResponseEntity.class, Config.class), resolver.resolve(Config.class)));
        r.add(alternate(resolver.resolve(ResponseEntity.class, MotionType.class), resolver.resolve(MotionType.class)));
        r.add(alternate(resolver.resolve(ResponseEntity.class, resolver.resolve(List.class, MotionType.class)), resolver.resolve(resolver.resolve(List.class, MotionType.class))));
        r.add(alternate(resolver.resolve(ResponseEntity.class, QualifierKey.class), resolver.resolve(QualifierKey.class)));
        r.add(alternate(resolver.resolve(ResponseEntity.class, resolver.resolve(List.class, QualifierKey.class)), resolver.resolve(resolver.resolve(List.class, QualifierKey.class))));
        r.add(alternate(resolver.resolve(ResponseEntity.class, Reglament.class), resolver.resolve(Reglament.class)));
        r.add(alternate(resolver.resolve(ResponseEntity.class, resolver.resolve(List.class, Reglament.class)), resolver.resolve(resolver.resolve(List.class, Reglament.class))));
        r.add(alternate(resolver.resolve(ResponseEntity.class, Sample.class), resolver.resolve(Sample.class)));
        r.add(alternate(resolver.resolve(ResponseEntity.class, resolver.resolve(List.class, Sample.class)), resolver.resolve(resolver.resolve(List.class, Sample.class))));
        r.add(alternate(resolver.resolve(ResponseEntity.class, Scope.class), resolver.resolve(Scope.class)));
        r.add(alternate(resolver.resolve(ResponseEntity.class, SensorDataFile.class), resolver.resolve(SensorDataFile.class)));
        r.add(alternate(resolver.resolve(ResponseEntity.class, SensorLocation.class), resolver.resolve(SensorLocation.class)));
        r.add(alternate(resolver.resolve(ResponseEntity.class, resolver.resolve(List.class, SensorLocation.class)), resolver.resolve(resolver.resolve(List.class, SensorLocation.class))));
        r.add(alternate(resolver.resolve(ResponseEntity.class, Sport.class), resolver.resolve(Sport.class)));
        r.add(alternate(resolver.resolve(ResponseEntity.class, resolver.resolve(List.class, Sport.class)), resolver.resolve(resolver.resolve(List.class, Sport.class))));
        r.add(alternate(resolver.resolve(ResponseEntity.class, Usage.class), resolver.resolve(Usage.class)));
        r.add(alternate(resolver.resolve(ResponseEntity.class, VideoFile.class), resolver.resolve(VideoFile.class)));
        r.add(alternate(resolver.resolve(ResponseEntity.class, Session.class), resolver.resolve(Session.class)));
        r.add(alternate(resolver.resolve(ResponseEntity.class, User.class), resolver.resolve(User.class)));
        r.add(alternate(resolver.resolve(ResponseEntity.class, resolver.resolve(List.class, User.class)), resolver.resolve(resolver.resolve(List.class, User.class))));
        r.add(alternate(resolver.resolve(ResponseEntity.class, resolver.resolve(List.class, Log.class)), resolver.resolve(resolver.resolve(List.class, Log.class))));
        r.add(alternate(resolver.resolve(ResponseEntity.class, Extra.class), resolver.resolve(Extra.class)));
        r.add(alternate(resolver.resolve(ResponseEntity.class, resolver.resolve(List.class, Extra.class)), resolver.resolve(resolver.resolve(List.class, Extra.class))));
        r.add(alternate(resolver.resolve(ResponseEntity.class, ExtraFile.class), resolver.resolve(ExtraFile.class)));
        r.add(alternate(resolver.resolve(ResponseEntity.class, resolver.resolve(List.class, ExtraFile.class)), resolver.resolve(resolver.resolve(List.class, ExtraFile.class))));

        return r;
    }

    public static AlternateTypeProcessingRule alternate(ResolvedType original, ResolvedType alternate) {
        return new AlternateTypeProcessingRule(original, alternate);
    }
}
