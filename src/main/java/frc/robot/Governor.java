package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.commands.setters.groups.ToIntake;
import frc.robot.commands.setters.groups.ToNeutral;
import frc.robot.commands.setters.groups.ToShoot;
import frc.robot.commands.setters.groups.ToShootPrep;

public class Governor {
    private static RobotState state = RobotState.UNKNOWN;

    private static RobotState queuedState = RobotState.UNKNOWN;
    private static RobotState desiredState = RobotState.UNKNOWN;
    private static RobotState lastState = RobotState.UNKNOWN;

    public static boolean cleanUp = false;
    
    public enum RobotState {
        NEUTRAL,    //Wyoming
        ZERO, //?

        UNKNOWN,    //Ohio
        MISC,   //Florida

        TRANSITION, //Interstate Highway

        INTAKE, //Mississippi
        SOURCE, //Massachusetts
        SHOOT_PREP, //New Hampshire
        SHOOT,  //Texas
    }

    public static void setRobotState(RobotState robotState) {
        setRobotState(robotState, false);
    }

    public static void setRobotState(RobotState robotState, boolean override) {
        desiredState = robotState;
        lastState = state;
        if(state == RobotState.TRANSITION && !override) queuedState = robotState;
        if(robotState == RobotState.UNKNOWN || robotState == RobotState.MISC) override = true;
        if(override || state != RobotState.TRANSITION) {
            if(robotState != RobotState.UNKNOWN) state = RobotState.TRANSITION;
            if(robotState == RobotState.MISC) state = RobotState.MISC;
            switch (robotState) {
                case NEUTRAL:
                    toNeutral();
                    break;
                case ZERO:
                    break;
                case UNKNOWN:
                    break;
                case MISC:
                    state = RobotState.MISC;
                    CommandScheduler.getInstance().schedule(new InstantCommand(()->{}, RobotContainer.arm, RobotContainer.loader, RobotContainer.intake));
                    break;
                case TRANSITION:
                    System.out.println("How did I get here?");
                    break;
                case INTAKE:
                    toIntake();
                    break;
                case SHOOT_PREP:
                    toShootPrep();
                    break;
                case SHOOT:
                    toShoot();
                    break;
                default:
                    break;
            }
        }
    }

    public static RobotState getRobotState() {
        return state;
    }
    public static RobotState getDesiredRobotState(){
        return desiredState;
    }
    public static RobotState getLastRobotState()  {
        return lastState;
    }

    public static RobotState getQueuedState() {
        return queuedState;
    }

    public static void setQueuedState(RobotState queuedState) {
        Governor.queuedState = queuedState;
    }

    private static void toNeutral() {
        CommandScheduler.getInstance().schedule(new ToNeutral());
    }
    private static void toIntake() {
        CommandScheduler.getInstance().schedule(new ToIntake());
    }
    private static void toShootPrep() {
        CommandScheduler.getInstance().schedule(new ToShootPrep());
    }
    private static void toShoot() {
        CommandScheduler.getInstance().schedule(new ToShoot());
    }

    public static Command getSetStateCommand(RobotState state) {
        lastState = state;
        return new InstantCommand(() -> Governor.state = state);
    }

    public static void setCleanupMode(boolean clean){
        cleanUp = clean;
    }
}