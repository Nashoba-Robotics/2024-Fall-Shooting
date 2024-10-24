package frc.robot.commands;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.lib.util.JoystickValues;
import frc.robot.subsystems.drive.DriveSubsystem;
import frc.robot.subsystems.drive.DriveSubsystem.DriveState;
import frc.robot.subsystems.joystick.JoystickSubsystem;

public class DriveCommand extends Command{
    
    private DriveSubsystem drive;
    private JoystickSubsystem joysticks;

    private JoystickValues leftJoystickValues;
    private JoystickValues rightJoystickValues;

    private ChassisSpeeds chassisSpeeds;

    public DriveCommand(DriveSubsystem drive, JoystickSubsystem joysticks) {
        this.drive = drive;
        this.joysticks = joysticks;

        addRequirements(drive);
        chassisSpeeds = new ChassisSpeeds(0, 0, 0);
        leftJoystickValues = new JoystickValues(0, 0);
        rightJoystickValues = new JoystickValues(0, 0);

    }

    @Override
    public void initialize() {
        drive.setDriveState(DriveState.DRIVER);
    }

    @Override
    public void execute() {

        leftJoystickValues = joysticks.getLeftJoystickValues()
            .shape(Constants.Joystick.MOVE_DEAD_ZONE, Constants.Joystick.TURN_SENSITIVITY)
            .swap()
            .applyAngleDeadzone(Constants.Joystick.ANGLE_DEAD_ZONE);
        rightJoystickValues = joysticks.getRightJoystickValues()
            .shape(Constants.Joystick.TURN_DEAD_ZONE, Constants.Joystick.TURN_SENSITIVITY);

        chassisSpeeds.vxMetersPerSecond = leftJoystickValues.x * Constants.Drive.MAX_VELOCITY;
        chassisSpeeds.vyMetersPerSecond = leftJoystickValues.y * Constants.Drive.MAX_VELOCITY;

        chassisSpeeds.omegaRadiansPerSecond = -rightJoystickValues.x * Constants.Drive.MAX_ROTATION_VELOCITY;

        drive.set(chassisSpeeds);

    }

    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
