package frc.robot;

import edu.wpi.first.math.geometry.Rotation2d;

public class Presets {

    public static class Arm {
        public static boolean OVERRIDE_AUTOMATIC_AIM = false;

        public static final Rotation2d INTAKE_POS = Rotation2d.fromRadians(-0.89);
        public static final Rotation2d NEUTRAL_POS = Rotation2d.fromRadians(-0.89);

        public static final Rotation2d POS_TOLERANCE = Rotation2d.fromRadians(0.0);

        public static Rotation2d COAST_SPEED = Rotation2d.fromRadians(100);

        public static Rotation2d SPEAKER_SPEED = Rotation2d.fromRadians(0); //TODO: Find a value for this

        public static final Rotation2d SPEAKER_SPEED_PREP = Rotation2d.fromRadians(200);
        public static final double SPEAKER_PERCENT = 0.9;

        public static final Rotation2d SPEED_TOLERANCE = Rotation2d.fromRadians(2);
    }

    public static final class Intake {
        public static final double INTAKE_SPEED = 0.9;
        public static final double SHOOT_SPEED = 0.8;

        public static final Rotation2d SPEED_TOLERANCE = Rotation2d.fromRadians(0);
    }

    public static final class Loader {
        public static final Rotation2d INTAKE_POS = Rotation2d.fromRadians(0);
        public static final Rotation2d NEUTRAL_POS = Rotation2d.fromRadians(0);
        public static final Rotation2d SHOOT_POS = Rotation2d.fromRadians(0);

        public static final Rotation2d POS_TOLERANCE = Rotation2d.fromDegrees(2);

        public static final double INTAKE_SPEED = -0.45;
        public static final double SHOOT_SPEED = -1;
        public static final double TO_SHOOTER_TRANSITION = -0.3;
        public static final double TO_LOADER_TRANSITION = 0.3;

        public static final Rotation2d SPEED_TOLERANCE = Rotation2d.fromRadians(0);
    }

}
