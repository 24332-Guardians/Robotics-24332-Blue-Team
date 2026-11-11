package org.firstinspires.ftc.teamcode.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.theMotorsAndServos;

@TeleOp
public class declanTeleOp extends OpMode {

    theMotorsAndServos motors = new theMotorsAndServos();

    @Override
    public void init() {
        motors.init(hardwareMap);
    }

    // For the Intake Toggle
    boolean prevInpGameA = false;
    boolean intakeOn = false;
    boolean prevInpGameX = false;
    boolean outtakeOn = false;


    @Override
    public void loop() {

        // ----- Gamepad Stick Controls ----- //

        double x = gamepad1.left_stick_x;
        double y = gamepad1.left_stick_y;
        double rx = gamepad1.right_stick_x;
        double flySpeed = gamepad1.right_trigger;

        // Driving

        motors.drive(y, x, rx);

        // Outtake

        motors.turnOnFlywheel(flySpeed);


        if ((!prevInpGameX) && (gamepad1.x)) {
            outtakeOn = !outtakeOn;
        }
        if (outtakeOn) {
            motors.pushingOut(.5);
        }
        else {
            motors.pushingOut(0);
        }

        // Toggle system for turning on and off the Intake with the A button on the gamepad

        if ((!prevInpGameA) && (gamepad1.a)) {
            intakeOn = !intakeOn;
        }
        if (intakeOn) {
            motors.turnOnIntake(.75);
        }
        else {
            motors.turnOnIntake(0);
        }

        prevInpGameA = gamepad1.a;
        prevInpGameX = gamepad1.x;
    }
}
