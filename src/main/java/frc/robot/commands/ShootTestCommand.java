package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.arm.ArmSubsystem;
import frc.robot.subsystems.loader.LoaderSubsystem;

/*
 * The purpose of this command is to help find your heuristic equation for pitch aiming.
 * TODO: Make it so this command can:
 *      1) Take in an input for the arm angle and move the arm to the specified angle
 *      2) Set the speed of the shooter from user-input
 *      3) Set the speed of the loader from user-input
 */
public class ShootTestCommand extends Command{
    ArmSubsystem arm;
    LoaderSubsystem loader;
    
    public ShootTestCommand(ArmSubsystem arm, LoaderSubsystem loader){
        this.arm = arm;
        this.loader = loader;
    }
}
