package frc.robot.commands.setters.units.arm;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Governor;
import frc.robot.Presets;
import frc.robot.RobotContainer;
import frc.robot.Governor.RobotState;
import frc.robot.subsystems.arm.ArmSubsystem;
import frc.robot.subsystems.drive.DriveSubsystem;

public class ArmToShoot extends Command{
    ArmSubsystem arm = RobotContainer.arm;
    DriveSubsystem drive = RobotContainer.drive;

    double angle;

    public ArmToShoot(){
        addRequirements(arm);
    }

    //TODO: Fill out this command
    @Override
    public void execute() {

    }
    @Override
    public boolean isFinished() {
        if(DriverStation.isAutonomous() && Governor.getDesiredRobotState() == RobotState.SHOOT) return Math.abs(angle - arm.getArmPivotAngle().getRadians()) < Presets.Arm.POS_TOLERANCE.getRadians() || !RobotContainer.sensors.getShooterSensor();
        else return Governor.getRobotState() != RobotState.SHOOT_PREP;
    }
}
