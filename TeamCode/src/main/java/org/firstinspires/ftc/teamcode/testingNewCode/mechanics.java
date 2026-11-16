package org.firstinspires.ftc.teamcode.testingNewCode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class mechanics {

    /*
     * ---------- Defining Variables ----------
     */

    // ----- Intake System ----- //
    private DcMotor Intake;
    private CRServo LIntakeServo;
    private CRServo RIntakeServo;

    // ----- Outtake System ----- //
    private DcMotorEx Flywheel;
    private CRServo LOuttakeServo;
    private CRServo ROuttakeServo;

    // ----- Drivetrain ----- //
    private DcMotor FRMotor;
    private DcMotor FLMotor;
    private DcMotor BRMotor;
    private DcMotor BLMotor;

    // ----- IMU ----- //
    private IMU imu;


    public void init(HardwareMap hwMap) {

        /*
         * ---------- Setting Up the Hardware ----------
         */

        // ----- Intake Setup ----- //
        Intake = hwMap.get(DcMotor.class, "Intake");
        LIntakeServo = hwMap.get(CRServo.class, "IntakeLeft");
        RIntakeServo = hwMap.get(CRServo.class, "IntakeRight");

        // ----- Outtake Setup ----- //
        Flywheel = hwMap.get(DcMotorEx.class, "Flywheel");
        LOuttakeServo = hwMap.get(CRServo.class, "LOutServo");
        ROuttakeServo = hwMap.get(CRServo.class, "ROutServo");

        // ----- Drivetrain ----- //
        FRMotor = hwMap.get(DcMotor.class, "FRMotor");
        FLMotor = hwMap.get(DcMotor.class, "FLMotor");
        BRMotor = hwMap.get(DcMotor.class, "BRMotor");
        BLMotor = hwMap.get(DcMotor.class, "BLMotor");

        /*
         * ---------- IMU ----------
         */
        imu = hwMap.get(IMU.class, "imu");

        // Tells the imu what direction the robot controller is facing
        RevHubOrientationOnRobot RevOrientation = new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.RIGHT,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD
        );

        imu.initialize(new IMU.Parameters(RevOrientation));

        /*
         * ---------- Setting Directions Of Motors ----------
         * Makes the motors work in positives
         */

        Intake.setDirection(DcMotorSimple.Direction.FORWARD);
        LIntakeServo.setDirection(DcMotorSimple.Direction.FORWARD);
        RIntakeServo.setDirection(DcMotorSimple.Direction.REVERSE);

        Flywheel.setDirection(DcMotorSimple.Direction.REVERSE);
        LOuttakeServo.setDirection(DcMotorSimple.Direction.FORWARD);
        ROuttakeServo.setDirection(DcMotorSimple.Direction.FORWARD);

        /*
         * ---------- Setting Run Mode For The Motors ----------
         * Sets the motors to be able to run at a certain speed instead of
         * however much power is put into the motor
         * Also setting the PIDF constants for the launcher
         */

        Intake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        Flywheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Flywheel.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, new PIDFCoefficients(40, 0, 0, 12.5));

        FRMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FLMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BRMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BLMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        /*
         * ---------- Setting Zero Power Behavior For Drivetrain ----------
         * Stops the motor from spinning when no power is being applied to the motors
         */

        FRMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FLMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BRMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BLMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public double getHeading(AngleUnit angleUnit) {
        return imu.getRobotYawPitchRollAngles().getYaw(angleUnit);
    }

    public void driveJoysticksBot(double x, double y, double rx) {

        /*
         * ---------- Mecanum Math ----------
         * Bot based mecanum drive
         */

        // Denominator corrects the motors so that they are never above 1 or below -1
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);

        // Adds or subtracts the input
        FLMotor.setPower((-y + x + rx)/denominator);
        BLMotor.setPower((-y - x + rx)/denominator);

        FRMotor.setPower((y + x + rx)/denominator);
        BRMotor.setPower((y - x + rx)/denominator);
    }

    public void driveJoysticksField(double x, double y, double rx) {

        /*
         * ---------- Field Centric Math ----------
         * Field based mecanum drive
         */

        double theta = Math.atan2(x, y);
        double r = Math.hypot(y, x);

        theta = AngleUnit.normalizeRadians(theta - (this.getHeading(AngleUnit.RADIANS)));

        double newX = r * Math.sin(theta);
        double newY = r * Math.cos(theta);

        this.driveJoysticksBot(newX, newY, rx);
    }

    public void intakePower(double speed) {
        /*
         * ---------- Set Intake Servo's Speeds ----------
         */

        Intake.setPower(speed);
        LIntakeServo.setPower(speed);
        RIntakeServo.setPower(speed);
    }

    public void flywheelSpeed(double speed, boolean on) {
        /*
         * ---------- Set Flywheel Speed ----------
         * Make sure to have the target ticks per second with this
         */

        if (on) {
            Flywheel.setVelocity(speed);
        }
    }

    public void outtakePower(double speed, boolean on) {
        /*
         * ---------- Set Outtake Servo's Speeds ----------
         */

        if (on) {
            LOuttakeServo.setPower(speed);
            ROuttakeServo.setPower(speed);
        }
    }

    public void flywheelToLaunch(double speed, double intakeStrength) {
        /*
         * ---------- Set Flywheel and then launch ----------
         */

        Flywheel.setVelocity(speed);

        if (Flywheel.getVelocity() >= (speed * 0.9)) {
            LOuttakeServo.setPower(0.75);
            ROuttakeServo.setPower(0.75);
            Intake.setPower(Math.min(intakeStrength + 0.5, 1));
        }
        else {
            LOuttakeServo.setPower(0.0);
            ROuttakeServo.setPower(0.0);
            Intake.setPower(0.0);
        }
    }
}
