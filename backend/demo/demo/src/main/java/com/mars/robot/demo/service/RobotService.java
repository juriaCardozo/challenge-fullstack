package com.mars.robot.demo.service;

import com.mars.robot.demo.model.Robot;
import org.springframework.stereotype.Service;

@Service
public class RobotService {
    private static final int GRID_SIZE = 5;

    public Robot executeCommands(String commands) {
        Robot robot = new Robot(0, 0, "N");
    
        for (char command : commands.toCharArray()) {
            if (isValidCommand(command)) {
                if (isValidMove(robot, command)) {
                    processCommand(robot, command);
                } else {
                    throw new IllegalArgumentException("Movimento inválido: " + command);
                }
            } else {
                throw new IllegalArgumentException("Comando inválido: " + command);
            }
        }
        return robot;
    }
    
    private boolean isValidCommand(char command) {
        return command == 'R' || command == 'L' || command == 'M';
    }


    private boolean isValidMove(Robot robot, char command) {
        Robot tempRobot = new Robot(robot.getxCoord(), robot.getyCoord(), robot.getOrientation());
        processCommand(tempRobot, command);

        return isValidPosition(tempRobot);
    }

    private void processCommand(Robot robot, char command) {
        switch (command) {
            case 'L':
                turnLeft(robot);
                break;
            case 'R':
                turnRight(robot);
                break;
            case 'M':
                moveForward(robot);
                break;
            default:
                break;
        }
    }

    private void turnLeft(Robot robot) {
        robot.setOrientation(getNewOrientation(robot.getOrientation(), -1));
    }

    private void turnRight(Robot robot) {
        robot.setOrientation(getNewOrientation(robot.getOrientation(), 1));
    }

    private String getNewOrientation(String currentOrientation, int direction) {
        String[] orientations = {"N", "E", "S", "W"};
        int currentIndex = indexOf(orientations, currentOrientation);
        int newIndex = (currentIndex + direction + orientations.length) % orientations.length;
        return orientations[newIndex];
    }

    private int indexOf(String[] array, String value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    private void moveForward(Robot robot) {
        switch (robot.getOrientation()) {
            case "N":
                robot.setyCoord(robot.getyCoord() + 1);
                break;
            case "E":
                robot.setxCoord(robot.getxCoord() + 1);
                break;
            case "S":
                robot.setyCoord(robot.getyCoord() - 1);
                break;
            case "W":
                robot.setxCoord(robot.getxCoord() - 1);
                break;
            default:
                break;
        }

        if (!isValidPosition(robot)) {
            throw new IllegalArgumentException("Movimento inválido: o robô não pode sair da área especificada.");
        }
    }

    private boolean isValidPosition(Robot robot) {
        return robot.getxCoord() >= 0 && robot.getxCoord() < GRID_SIZE &&
               robot.getyCoord() >= 0 && robot.getyCoord() < GRID_SIZE;
    }
}