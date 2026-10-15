package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.theMotos;

@TeleOp
public class dcMotorPrac extends OpMode {

    theMotos motors = new theMotos();

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
