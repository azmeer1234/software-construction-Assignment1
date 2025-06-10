package org.organization;

import java.util.*;

// Interface for Task Performer
interface TaskPerformer {
    void executeTask();
}

// Different worker roles
class Illustrator implements TaskPerformer {
    public void executeTask() {
        System.out.println("Illustrator is drawing concepts...");
    }
}

class Musician implements TaskPerformer {
    public void executeTask() {
        System.out.println("Musician is composing background music...");
    }
}

class Developer implements TaskPerformer {
    public void executeTask() {
        System.out.println("Developer is building the system...");
    }
}

class QAEngineer implements TaskPerformer {
    public void executeTask() {
        System.out.println("QA Engineer is performing tests...");
    }
}

// Abstract production unit
abstract class Division {
    protected abstract List<TaskPerformer> assembleTeam();

    public void runProject() {
        List<TaskPerformer> team = assembleTeam();
        team.forEach(TaskPerformer::executeTask);
    }
}

// Specialized Divisions
class CreativeDivision extends Division {
    protected List<TaskPerformer> assembleTeam() {
        return List.of(new Illustrator(), new Musician());
    }
}

class TechnicalDivision extends Division {
    protected List<TaskPerformer> assembleTeam() {
        return List.of(new Developer(), new QAEngineer());
    }
}

// Application Runner
public class DivisionManager {
    public static void main(String[] args) {
        Division creative = new CreativeDivision();
        Division technical = new TechnicalDivision();

        System.out.println("Creative Division Output:");
        creative.runProject();

        System.out.println("\nTechnical Division Output:");
        technical.runProject();
    }
}
