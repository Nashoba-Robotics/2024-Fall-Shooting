package frc.robot.commands;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.lib.util.JoystickValues;
import frc.robot.subsystems.drive.DriveSubsystem;
import frc.robot.subsystems.joystick.JoystickSubsystem;

//TODO: Complete this function to Aim the robot towards the target
public class AimToSpeakerCommand extends Command{
    DriveSubsystem drive;
    JoystickSubsystem joysticks;

    JoystickValues leftJoystickValues;
    JoystickValues rightJoystickValues;

    public AimToSpeakerCommand(DriveSubsystem drive, JoystickSubsystem joysticks){
        this.drive = drive;
        this.joysticks = joysticks;

        addRequirements(drive);

        leftJoystickValues = new JoystickValues(0, 0);
        rightJoystickValues = new JoystickValues(0, 0);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {

        ChassisSpeeds chassisSpeeds = new ChassisSpeeds();

        leftJoystickValues = joysticks.getLeftJoystickValues()
            .shape(Constants.Joystick.MOVE_DEAD_ZONE, Constants.Joystick.TURN_SENSITIVITY)
            .swap()
            .applyAngleDeadzone(Constants.Joystick.ANGLE_DEAD_ZONE);
        rightJoystickValues = joysticks.getRightJoystickValues()
            .shape(Constants.Joystick.TURN_DEAD_ZONE, Constants.Joystick.TURN_SENSITIVITY);

        chassisSpeeds.vxMetersPerSecond = leftJoystickValues.x * Constants.Drive.MAX_VELOCITY;
        chassisSpeeds.vyMetersPerSecond = leftJoystickValues.y * Constants.Drive.MAX_VELOCITY;

        drive.set(chassisSpeeds);

    }

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return Math.abs(joysticks.getRightJoystickValues().x) >= 0.03;
    }
}
