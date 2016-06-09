package com.bsu.mvt.server.rest.model;

public enum PlayerLevel {
    BEGINNER, AMATEUR, PROFESSIONAL;

    public static PlayerLevel get(String command) {
        if (command != null && !"".equals(command.trim())) {
            command = command.toUpperCase();
            for (PlayerLevel enumCommand : values()) {
                if (enumCommand.name().equals(command)) {
                    return enumCommand;
                }
            }
        }
        return null;
    }
}
