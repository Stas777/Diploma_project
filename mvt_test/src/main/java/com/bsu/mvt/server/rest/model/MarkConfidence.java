package com.bsu.mvt.server.rest.model;

public enum MarkConfidence {
    LOW, MEDIUM, HIGH;

    public static MarkConfidence get(String command) {
        if (command != null && !"".equals(command.trim())) {
            command = command.toUpperCase();
            for (MarkConfidence enumCommand : values()) {
                if (enumCommand.name().equals(command)) {
                    return enumCommand;
                }
            }
        }
        return null;
    }
}
