package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ServoImpl;


public class theMotorsAndServos {
    private DcMotor Intake;
    private DcMotor Flywheel;

    //-------Drivetrain------
    private DcMotor FRMotor;
    private DcMotor FLMotor;
    private DcMotor BRMotor;
    private DcMotor BLMotor;
    private CRServo WindmillOut;
    private CRServo LeftIntake;
    private CRServo RightIntake;

    public void init(HardwareMap hwMap) {

        // The "DcMotor.RunMode.RUN_USING_ENCODER" makes it so that it will spin at a set velocity

        // ----- Intake ----- //

        Intake = hwMap.get(DcMotor.class, "Intake");
        Intake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // ----- Outtake ----- //

        Flywheel = hwMap.get(DcMotor.class, "Flywheel");
        Flywheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // ----- Drivetrain Hardware Mapping ----- //

        FRMotor = hwMap.get(DcMotor.class, "FRMotor");
        FRMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        FLMotor = hwMap.get(DcMotor.class, "FLMotor");
        FLMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        BRMotor = hwMap.get(DcMotor.class, "BRMotor");
        BRMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        BLMotor = hwMap.get(DcMotor.class, "BLMotor");
        BLMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // ----- Servos ----- //

        WindmillOut = hwMap.get(CRServo.class, "Windmill");
        LeftIntake = hwMap.get(CRServo.class, "IntakeLeft");
        RightIntake = hwMap.get(CRServo.class, "IntakeRight");
    }

    public void turnOnIntake(double speed) {
        Intake.setPower(speed);
        LeftIntake.setPower(speed/2);
        RightIntake.setPower(speed/2);
    }

    public void drive(double y, double x, double rx) {
        // ----- The Mecanum Drive Math ----- //
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);

        FLMotor.setPower((-y + x + rx)/denominator);
        BLMotor.setPower((-y - x + rx)/denominator);

        FRMotor.setPower((y + x + rx)/denominator);
        BRMotor.setPower((y - x + rx)/denominator);

    }

    public void turnOnFlywheel(double speed) {
        Flywheel.setPower(speed);
    }

    public void turnOnWindmill(double speed){
        WindmillOut.setPower(speed);
    }
}
