package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.theMotorsAndServos;

@TeleOp
public class dcMotorPrac extends OpMode {

    theMotorsAndServos motors = new theMotorsAndServos();

    @Override
    public void init() {
        motors.init(hardwareMap);
    }

    @Override
    public void loop() {

        motors.turnOnIntake(.5);
        motors.drive(1, -1, 0);
    }
}
