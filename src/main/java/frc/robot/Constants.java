// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.mechanisms.swerve.SwerveModule.ClosedLoopOutputType;
import com.ctre.phoenix6.mechanisms.swerve.SwerveModuleConstants;
import com.ctre.phoenix6.mechanisms.swerve.SwerveModuleConstants.SteerFeedbackType;
import com.ctre.phoenix6.mechanisms.swerve.SwerveModuleConstantsFactory;
import com.ctre.phoenix6.signals.GravityTypeValue;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.Filesystem;

//TODO: There are a lot of constants to find :) Good luck!
public final class Constants {
  public static final double TAU = 2 * Math.PI;
  public static final double PEAK_VOLTAGE = 12;

  public static final class AprilTags{
    public static final String FRONT_RIGHT_CAMERA_NAME = "";
    public static final String FRONT_LEFT_CAMERA_NAME = "";
    public static final String BACK_RIGHT_CAMERA_NAME = "";
    public static final String BACK_LEFT_CAMERA_NAME = "";

    /* For PhotonEstimator
    *             ^ 
    *             |
    *             Z        
    *      --------------
    *      |            |
    *      |            |
    *<-- X |     *Y     |
    *      |            |
    *      |            |
    *      --------------
    */
    public static final Transform3d ROBOT_TO_CAMERA_FRONT_LEFT = new Transform3d(Units.inchesToMeters(13.25)-0.04,Units.inchesToMeters(9.3), 0.25, new Rotation3d(0, -25./360*TAU, 20.*Constants.TAU/360));
    public static final Transform3d ROBOT_TO_CAMERA_FRONT_RIGHT = new Transform3d(Units.inchesToMeters(13.25)+0.04, Units.inchesToMeters(-9.3), 0.25, new Rotation3d(0, -25./360*TAU, -20.*Constants.TAU/360));
    public static final Transform3d ROBOT_TO_CAMERA_BACK_LEFT = new Transform3d(Units.inchesToMeters(-13.096)-0.03, Units.inchesToMeters(10.758)+0.03, 0.32, new Rotation3d(-15./360*TAU, -40./360*TAU, 32./360*TAU + TAU/2));
    public static final Transform3d ROBOT_TO_CAMERA_BACK_RIGHT = new Transform3d(Units.inchesToMeters(-13.096)+0.03, Units.inchesToMeters(-10.758)-0.03, 0.32, new Rotation3d(TAU/2 + 15./360*TAU, -40./360*TAU, -18./360*TAU-TAU/2));
    

    //With the Layout paths, REMEMBER you need to also upload the json file to the Photonvision GUI
    //This layout for some reason only works for the single tag estimation (as of 02/11/24) 
    public static final String LAYOUT_PATH = Filesystem.getDeployDirectory().getPath() + "/TagPositions.json";

    public static final double getXSD(double distance) {
      return 0.0312*distance - 0.0494;
    }

    public static final double getYSD(double distance) {
      return 0.0656*distance - 0.129;
    }
  }
  
  public static final class Arm{
    public static final String PIVOT_CANBUS = "";
    public static final String CANBUS = "rio";
    public static final String ENCODER_CANBUS = "";

    public static final int PIVOT_PORT = 0;

    public static final int SHOOTER_PORT = 0;
    public static final int SHOOTER_PORT_2 = 0;

    public static final int ENCODER_PORT = 0;
    public static final double ENCODER_OFFSET = -0.9232384977983 + 1.884110302;

    public static final int SHOOTER_SENSOR_PORT = 0;
    public static final int LOADER_SENSOR_PORT = 0;

    public static final double SHOOTER_GEAR_RATIO = 0;
    public static final double PIVOT_GEAR_RATIO = 0;

    public static final double PIVOT_STATOR_CURRENT_LIMIT = 45.0;
    public static final double PIVOT_SUPPLY_CURRENT_LIMIT = 15.0;

    public static final Rotation2d PIVOT_FORWARD_SOFT_LIMIT = Rotation2d.fromDegrees(-0.85);
    public static final Rotation2d PIVOT_REVERSE_SOFT_LIMIT = Rotation2d.fromRadians(-0.86);

    public static final double PIVOT_MOTION_MAGIC_ACCELERATION = 0;
    public static final double PIVOT_MOTION_MAGIC_CRUISE_VELOCITY = 0;
    public static final double PIVOT_MOTION_MAGIC_JERK = 0;

    public static final InvertedValue PIVOT_INVERTED = InvertedValue.CounterClockwise_Positive;
    public static final NeutralModeValue PIVOT_NEUTRAL_MODE = NeutralModeValue.Brake;

    public static final Slot0Configs PIVOT_PID = new Slot0Configs()
    .withKV(0.0).withKS(0.0).withKG(0.0).withGravityType(GravityTypeValue.Arm_Cosine)
    .withKP(0).withKI(0).withKD(0.0);

    public static final double SHOOTER_STATOR_CURRENT_LIMIT = 70;
    public static final double SHOOTER_SUPPLY_CURRENT_LIMIT = 50;

    public static final InvertedValue SHOOTER_INVERTED = InvertedValue.CounterClockwise_Positive;
    public static final NeutralModeValue SHOOTER_NEUTRAL_MODE = NeutralModeValue.Coast;

    public static final Slot0Configs SHOOTER_PID = new Slot0Configs()
    .withKV(0.0).withKS(0.0)
    .withKP(0.0).withKI(0).withKD(0);
  }

  public static class Drive {
    public static final String CANBUS = "";

    public static final double WIDTH = Units.inchesToMeters(24); // ~0.57785m / 22.7in
    public static final double LENGTH = Units.inchesToMeters(24);
    public static final double DIAGONAL = Math.sqrt(WIDTH*WIDTH + LENGTH*LENGTH)/2;

    public static final SwerveDriveKinematics KINEMATICS = new SwerveDriveKinematics(
    new Translation2d(WIDTH/2, -LENGTH/2),
    new Translation2d(WIDTH/2, LENGTH/2),
    new Translation2d(-WIDTH/2, LENGTH/2),
    new Translation2d(-WIDTH/2, -LENGTH/2)
    );

    public static final double MAX_VELOCITY = 5; // MPS
    public static final double MAX_ACCELERATION = 0;

    public static final double MAX_ROTATION_VELOCITY = 12; // RadPS
    public static final double MAX_ROTATION_ACCELERATION = 0;

    public static final Slot0Configs steerGains0 = new Slot0Configs()
    .withKP(100).withKI(0).withKD(0.0)
          // .withKP(0).withKI(0).withKD(0)
    .withKS(0.16).withKV(2.80).withKA(0);

    public static final Slot0Configs steerGains1 = new Slot0Configs()
    .withKP(100).withKI(0).withKD(0.0)
    // .withKP(0).withKI(0).withKD(0)
    .withKS(0.18).withKV(2.84).withKA(0);
    
    public static final Slot0Configs steerGains2 = new Slot0Configs()
    .withKP(100).withKI(0).withKD(0.0)
    // .withKP(0).withKI(0).withKD(0)
    .withKS(0.19).withKV(2.85).withKA(0);

    public static final Slot0Configs steerGains3 = new Slot0Configs()
    .withKP(100).withKI(0).withKD(0.0)
    .withKS(0.19).withKV(2.91).withKA(0); 



    private static final Slot0Configs driveGains0 = new Slot0Configs()
    .withKP(0.3).withKI(0).withKD(0)
    // .withKP(0).withKI(0).withKD(0)
    .withKS(0.27).withKV(0.1234).withKA(0);

    private static final Slot0Configs driveGains1 = new Slot0Configs()
    .withKP(0.3).withKI(0).withKD(0)
    // .withKP(0).withKI(0).withKD(0)
    .withKS(0.27).withKV(0.1234).withKA(0);

    private static final Slot0Configs driveGains2 = new Slot0Configs()
    .withKP(0.3).withKI(0).withKD(0)
    // .withKP(0).withKI(0).withKD(0)
    .withKS(0.27).withKV(0.1234).withKA(0);

    private static final Slot0Configs driveGains3 = new Slot0Configs()
    .withKP(0.3).withKI(0).withKD(0)
    // .withKP(0).withKI(0).withKD(0)
    .withKS(0.27).withKV(0.1234).withKA(0);


    private static final ClosedLoopOutputType steerClosedLoopOutput = ClosedLoopOutputType.Voltage;
    private static final ClosedLoopOutputType driveClosedLoopOutput = ClosedLoopOutputType.Voltage;

    public static final double kSlipCurrentA = 60;

    public static final double kSpeedAt12VoltsMps = MAX_VELOCITY;

    // private static final double kCoupleRatio = 3.5714285714285716;
    private static final double kCoupleRatio = 0;

    public static final double kDriveGearRatio = 50./16 * 16/28 * 45/15;
    public static final double kSteerGearRatio = 21.428571428571427;
    private static final double kWheelRadiusInches = 1.94425; //Comp

    public static final double WHEEL_RADIUS = Units.inchesToMeters(kWheelRadiusInches);

    private static final boolean kSteerMotorReversed = true;
    private static final boolean kInvertLeftSide = false;
    private static final boolean kInvertRightSide = true;

    // These are only used for simulation
    private static final double kSteerInertia = 0.00001;
    private static final double kDriveInertia = 0.001;
    // Simulated voltage necessary to overcome friction
    private static final double kSteerFrictionVoltage = 0.25;
    private static final double kDriveFrictionVoltage = 0.25;

    private static final SwerveModuleConstantsFactory ConstantCreator0 = new SwerveModuleConstantsFactory()
      .withDriveMotorGearRatio(kDriveGearRatio)
      .withSteerMotorGearRatio(kSteerGearRatio)
      .withWheelRadius(kWheelRadiusInches)
      .withSlipCurrent(kSlipCurrentA)
      .withSteerMotorGains(steerGains0)
      .withDriveMotorGains(driveGains0)
      .withSteerMotorClosedLoopOutput(steerClosedLoopOutput)
      .withDriveMotorClosedLoopOutput(driveClosedLoopOutput)
      .withSpeedAt12VoltsMps(kSpeedAt12VoltsMps)
      .withSteerInertia(kSteerInertia)
      .withDriveInertia(kDriveInertia)
      .withSteerFrictionVoltage(kSteerFrictionVoltage)
      .withDriveFrictionVoltage(kDriveFrictionVoltage)
      .withFeedbackSource(SteerFeedbackType.FusedCANcoder)
      .withCouplingGearRatio(kCoupleRatio)
      .withSteerMotorInverted(kSteerMotorReversed);

    private static final SwerveModuleConstantsFactory ConstantCreator1 = new SwerveModuleConstantsFactory()
      .withDriveMotorGearRatio(kDriveGearRatio)
      .withSteerMotorGearRatio(kSteerGearRatio)
      .withWheelRadius(kWheelRadiusInches)
      .withSlipCurrent(kSlipCurrentA)
      .withSteerMotorGains(steerGains1)
      .withDriveMotorGains(driveGains1)
      .withSteerMotorClosedLoopOutput(steerClosedLoopOutput)
      .withDriveMotorClosedLoopOutput(driveClosedLoopOutput)
      .withSpeedAt12VoltsMps(kSpeedAt12VoltsMps)
      .withSteerInertia(kSteerInertia)
      .withDriveInertia(kDriveInertia)
      .withSteerFrictionVoltage(kSteerFrictionVoltage)
      .withDriveFrictionVoltage(kDriveFrictionVoltage)
      .withFeedbackSource(SteerFeedbackType.FusedCANcoder)
      .withCouplingGearRatio(kCoupleRatio)
      .withSteerMotorInverted(kSteerMotorReversed);

    private static final SwerveModuleConstantsFactory ConstantCreator2 = new SwerveModuleConstantsFactory()
      .withDriveMotorGearRatio(kDriveGearRatio)
      .withSteerMotorGearRatio(kSteerGearRatio)
      .withWheelRadius(kWheelRadiusInches)
      .withSlipCurrent(kSlipCurrentA)
      .withSteerMotorGains(steerGains2)
      .withDriveMotorGains(driveGains2)
      .withSteerMotorClosedLoopOutput(steerClosedLoopOutput)
      .withDriveMotorClosedLoopOutput(driveClosedLoopOutput)
      .withSpeedAt12VoltsMps(kSpeedAt12VoltsMps)
      .withSteerInertia(kSteerInertia)
      .withDriveInertia(kDriveInertia)
      .withSteerFrictionVoltage(kSteerFrictionVoltage)
      .withDriveFrictionVoltage(kDriveFrictionVoltage)
      .withFeedbackSource(SteerFeedbackType.FusedCANcoder)
      .withCouplingGearRatio(kCoupleRatio)
      .withSteerMotorInverted(!kSteerMotorReversed);

    private static final SwerveModuleConstantsFactory ConstantCreator3 = new SwerveModuleConstantsFactory()
      .withDriveMotorGearRatio(kDriveGearRatio)
      .withSteerMotorGearRatio(kSteerGearRatio)
      .withWheelRadius(kWheelRadiusInches)
      .withSlipCurrent(kSlipCurrentA)
      .withSteerMotorGains(steerGains3)
      .withDriveMotorGains(driveGains3)
      .withSteerMotorClosedLoopOutput(steerClosedLoopOutput)
      .withDriveMotorClosedLoopOutput(driveClosedLoopOutput)
      .withSpeedAt12VoltsMps(kSpeedAt12VoltsMps)
      .withSteerInertia(kSteerInertia)
      .withDriveInertia(kDriveInertia)
      .withSteerFrictionVoltage(kSteerFrictionVoltage)
      .withDriveFrictionVoltage(kDriveFrictionVoltage)
      .withFeedbackSource(SteerFeedbackType.FusedCANcoder)
      .withCouplingGearRatio(kCoupleRatio)
      .withSteerMotorInverted(kSteerMotorReversed);

    // Front Left
    private static final int kFrontLeftDriveMotorId = 5;
    private static final int kFrontLeftSteerMotorId = 1;
    private static final int kFrontLeftEncoderId = 1;

    private static final double kFrontLeftEncoderOffset = 0.162354;
    private static final double kFrontLeftXPosInches = 11.375;
    private static final double kFrontLeftYPosInches = 11.375;

    // Front Right
    private static final int kFrontRightDriveMotorId = 4;
    private static final int kFrontRightSteerMotorId = 0;
    private static final int kFrontRightEncoderId = 0;

    private static final double kFrontRightEncoderOffset = 0.224121;
    private static final double kFrontRightXPosInches = 11.375;
    private static final double kFrontRightYPosInches = -11.375;

    // Back Left
    private static final int kBackLeftDriveMotorId = 6;
    private static final int kBackLeftSteerMotorId = 2;
    private static final int kBackLeftEncoderId = 2;

    private static final double kBackLeftEncoderOffset = 0.279297;
    private static final double kBackLeftXPosInches = -11.375;
    private static final double kBackLeftYPosInches = 11.375;

    // Back Right
    private static final int kBackRightDriveMotorId = 7;
    private static final int kBackRightSteerMotorId = 3;
    private static final int kBackRightEncoderId = 3;

    private static final double kBackRightEncoderOffset = -0.172363;
    private static final double kBackRightXPosInches = -11.375;
    private static final double kBackRightYPosInches = -11.375;

    public static final SwerveModuleConstants MOD1_CONSTANTS = ConstantCreator1.createModuleConstants(
          kFrontLeftSteerMotorId, kFrontLeftDriveMotorId, kFrontLeftEncoderId, kFrontLeftEncoderOffset, Units.inchesToMeters(kFrontLeftXPosInches), Units.inchesToMeters(kFrontLeftYPosInches), kInvertLeftSide);
    public static final SwerveModuleConstants MOD0_CONSTANTS = ConstantCreator0.createModuleConstants(
          kFrontRightSteerMotorId, kFrontRightDriveMotorId, kFrontRightEncoderId, kFrontRightEncoderOffset, Units.inchesToMeters(kFrontRightXPosInches), Units.inchesToMeters(kFrontRightYPosInches), kInvertRightSide);
    public static final SwerveModuleConstants MOD2_CONSTANTS = ConstantCreator2.createModuleConstants(
          kBackLeftSteerMotorId, kBackLeftDriveMotorId, kBackLeftEncoderId, kBackLeftEncoderOffset, Units.inchesToMeters(kBackLeftXPosInches), Units.inchesToMeters(kBackLeftYPosInches), kInvertLeftSide);
    public static final SwerveModuleConstants MOD3_CONSTANTS = ConstantCreator3.createModuleConstants(
          kBackRightSteerMotorId, kBackRightDriveMotorId, kBackRightEncoderId, kBackRightEncoderOffset, Units.inchesToMeters(kBackRightXPosInches), Units.inchesToMeters(kBackRightYPosInches), !kInvertRightSide);

  }

  public static final class Misc {
    public static final int GYRO_PORT = 0;
    public static final String PIGEON_CANBUS = "";

    public static final double DELETE_DISTANCE_RANGE = 0.5; //m
    public static final double OPERATOR_ANGLE_CORRECTION = 0.01; //rad

    public static final double CLOSE_FAR_CUTOFF = 3.15;
    public static final double SOURCE_AMP_CUTOFF = 4.1;
  }

  public static final class Joystick {
    public static final int DRIVER_PORT = 0;
    public static final int OPERATOR_PORT = 1;

    public static final double MOVE_DEAD_ZONE = 0.02;
    public static final double TURN_DEAD_ZONE = 0.02;

    public static final double ANGLE_DEAD_ZONE = Constants.TAU / 72;

    public static final double MOVE_SENSITIVITY = 1.5;
    public static final double TURN_SENSITIVITY = 2;
}

  public static final class Field {
    // public static final Translation2d SPEAKER_POSITION = new Translation2d(0, 0);
    public static final Translation3d TARGET_POSITION = new Translation3d(0, 0, 0); //y = 5.75

    public static final double LENGTH = 16.451;
    public static final double WIDTH = 8.211;
  }

  public static final class Intake{
    public static final String CANBUS = "";

    public static final int PORT = 0;

  }

  public static final class Loader {
    public static final String CANBUS = "";

    public static final int PIVOT_PORT = 0;
    public static final int ROLLER_PORT = 0;

    // public static final double PIVOT_GEAR_RATIO = 9.*7*30/18;
    public static final double PIVOT_GEAR_RATIO = 70.;

    public static final double ROLLER_GEAR_RATIO = 7;

    public static final double PIVOT_STATOR_CURRENT_LIMIT = 20;
    public static final double PIVOT_SUPPLY_CURRENT_LIMIT = 20;

    public static final Rotation2d PIVOT_FORWARD_SOFT_LIMIT = Rotation2d.fromDegrees(100);
    public static final Rotation2d PIVOT_REVERSE_SOFT_LIMIT = Rotation2d.fromDegrees(-30);

    public static final double PIVOT_MOTION_MAGIC_ACCELERATION = 3;
    public static final double PIVOT_MOTION_MAGIC_CRUISE_VELOCITY = 1.35;
    public static final double PIVOT_MOTION_MAGIC_JERK = 0;

    public static final InvertedValue PIVOT_INVERTED = InvertedValue.CounterClockwise_Positive;
    public static final NeutralModeValue PIVOT_NEUTRAL_MODE = NeutralModeValue.Brake;

    public static final Slot0Configs PIVOT_PID = new Slot0Configs()
    .withKV(0).withKS(0.0)
    .withKP(0).withKI(0).withKD(0);

    public static final double ROLLER_STATOR_CURRENT_LIMIT = 60;
    public static final double ROLLER_SUPPLY_CURRENT_LIMIT = 60;

    public static final InvertedValue ROLLER_INVERTED = InvertedValue.Clockwise_Positive;
    public static final NeutralModeValue ROLLER_NEUTRAL_MODE = NeutralModeValue.Coast;
}

  public static final class Sensors{
    public static final int SHOOTER_PORT_1 = 0;
    public static final int SHOOTER_PORT_2 = 0;
    public static final int LOADER_PORT = 0;
    public static final int INTAKE_PORT = 0;
  }
}
