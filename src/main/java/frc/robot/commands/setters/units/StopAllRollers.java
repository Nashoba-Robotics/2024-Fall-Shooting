package frc.robot.commands.setters.units;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
import frc.robot.subsystems.arm.ArmSubsystem;
import frc.robot.subsystems.intake.IntakeSubsystem;
import frc.robot.subsystems.loader.LoaderSubsystem;

public class StopAllRollers extends Command{
    ArmSubsystem arm = RobotContainer.arm;
    IntakeSubsystem intake = RobotContainer.intake;
    LoaderSubsystem loader = RobotContainer.loader;
    
    public StopAllRollers(){
        addRequirements(arm, intake, loader);
    }

    @Override
    public void initialize() {
        loader.setRollerSpeed(0);
        intake.setSpeed(0);
    }

    @Override
    public void execute() {
        loader.setRollerSpeed(0);
        intake.setSpeed(0);

    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
