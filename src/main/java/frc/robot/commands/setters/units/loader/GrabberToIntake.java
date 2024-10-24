package frc.robot.commands.setters.units.loader;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Presets;
import frc.robot.RobotContainer;
import frc.robot.subsystems.loader.LoaderSubsystem;

public class GrabberToIntake extends Command {
    private LoaderSubsystem loader = RobotContainer.loader;    

    public GrabberToIntake() {
        addRequirements(loader);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        double loaderSpeed = RobotContainer.sensors.getShooterSensor() ? 0 : Presets.Loader.INTAKE_SPEED;
        loader.setRollerSpeed(loaderSpeed);
    }

    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
        return true;
    }

}
