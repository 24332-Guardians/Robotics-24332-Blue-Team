package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.mechanisms.theMotorsAndServos;

@Autonomous
public class moveBackAndForth extends OpMode {

    //theMotorsAndServos motors = new theMotorsAndServos();
    ElapsedTime runtime = new ElapsedTime();

    @Override
    public void init() {
        //motors.init(hardwareMap);
    }

    @Override
    public void start() {
        runtime.reset();
    }

    @Override
    public void loop() {
        while (runtime.seconds() < 1.0) {
            //motors.drive(1, 0, 0);
            telemetry.addData("drivetrain","forward");
            telemetry.update();
        }

        while (runtime.seconds() < 2.0) {
            //motors.drive(-1, 0, 0);
            telemetry.addData("drivetrain","backward");
            telemetry.update();
        }

        //motors.drive(0,0,0);
        telemetry.addData("drivetrain","stopped");
        telemetry.update();
    }
}
