package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.mechanisms.theMotorsAndServos;



@TeleOp
public class testTeleOp extends OpMode {

    theMotorsAndServos motors = new theMotorsAndServos();

    @Override
    public void init() {
        motors.init(hardwareMap);
    }

    // For the Intake Toggle
    boolean prevInpGameA = false;
    boolean intakeOn = false;


    @Override
    public void loop() {

        // ----- Gamepad Stick Controls ----- //

        double x = gamepad1.left_stick_x;
        double y = gamepad1.left_stick_y; // I saw somewhere that this might need to be flipped to negative
        double rx = gamepad1.right_stick_x; // x for the right stick used for strafing
        double flySpeed = gamepad1.right_trigger;

        // Driving

        motors.drive(y, x, rx);

        // Outtake

        motors.turnOnFlywheel(flySpeed);

        if (gamepad1.x) {
            motors.turnOnWindmill(.2);
        }
        else {
            motors.turnOnWindmill(0);
        }

        // Toggle system for turning on and off the Intake with the A button on the gamepad

        if ((!prevInpGameA) && (gamepad1.a)) {
            intakeOn = !intakeOn;
        }
        if (intakeOn) {
            motors.turnOnIntake(.5);
        }
        else {
            motors.turnOnIntake(0);
        }

        prevInpGameA = gamepad1.a;

    }
}
