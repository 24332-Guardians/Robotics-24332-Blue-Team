package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.mechanisms.theMotorsAndServos;

@Autonomous
public class moveBackAndForth extends OpMode {

    theMotorsAndServos motors = new theMotorsAndServos();

    @Override
    public void init() {
        motors.init(hardwareMap);
        for (int i = 0; i < 100; i++) {
            motors.drive(1, 0, 0);
        }
        for (int i = 0; i < 100; i++) {
            motors.drive(-1, 0, 0);
        }
        motors.drive(0,0,0);
    }

    @Override
    public void loop() {

    }
}
