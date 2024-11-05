package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;

/*
 * TODO: This command should:
 *      1) Take in user input of an angle to aim to and go to that angle
 *      2) Display the angle the robot thinks its at (Either through AdvantageKit or Shuffleboard)
 * 
 * Note: Some issues that you might encounter are:
 *      - Robot moving slowly past the setpoint
 *          - This is due to gyro drift at low speeds
 *          - 2 Solutions: Have april tags to help with angle measurement or make the robot move at a higher speed (More responsive PID)
 *      - Robot turning across the major arc rather than the minor arc
 *          - This is probably when the robot wants to move from -pi to pi. On default, the PID loop will not be continuous
 *          - Use this function: controller.enableContinuousInput(MIN_INPUT, MAX_INPUT);
 */
public class AimTuneCommand extends Command{
    
}
