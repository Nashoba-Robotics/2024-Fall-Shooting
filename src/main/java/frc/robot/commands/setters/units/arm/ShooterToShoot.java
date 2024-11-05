package frc.robot.commands.setters.units.arm;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Presets;
import frc.robot.RobotContainer;
import frc.robot.subsystems.arm.ArmSubsystem;

public class ShooterToShoot extends Command{
    ArmSubsystem arm = RobotContainer.arm;
    Timer timer;

    public 
    ShooterToShoot(){
        addRequirements(arm);
        timer = new Timer();
    }

    @Override
    public void initialize() {
        timer.reset();
        timer.start();
    }

    @Override
    public void execute() {
        Rotation2d speed = Rotation2d.fromRadians(Presets.Arm.SPEAKER_SPEED.getRadians());
        arm.setShooterSpeed(speed);
    }

    @Override
    public boolean isFinished() {
        return Math.abs(arm.getShooterSpeed().getRadians()) >= Presets.Arm.SPEAKER_SPEED.getRadians();
    }
}
