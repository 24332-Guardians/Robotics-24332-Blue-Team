package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.mechanisms.theMotos;



@TeleOp
public class testTeleOp extends OpMode {

    theMotos motors = new theMotos();

    @Override
    public void init() {
        motors.init(hardwareMap);
    }

    @Override
    public void loop() {

        // x and y for the left stick (y is flipped on the gamepad so it needs to be flipped here)
        double x = gamepad1.left_stick_x;
        double y = gamepad1.left_stick_y;
        // x for the right stick used for strafing
        double rx = gamepad1.right_stick_x;

        motors.drive(y, x, rx);

        if (gamepad1.a) {
            motors.turnOnIntake(.5);
        }

    }
}
