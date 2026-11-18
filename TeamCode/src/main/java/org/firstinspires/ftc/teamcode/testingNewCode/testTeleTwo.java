package org.firstinspires.ftc.teamcode.testingNewCode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class testTeleTwo extends OpMode {

    /*
     * ---------- Variables/Objects ----------
     */

    // ----- Mechanical System ----- //
    mechanics mechanics = new mechanics();

    // ----- Constants ----- //
    public static final boolean AUTOMATIC_SHOOTING = true;
    public static final double FLYWHEEL_TARGET_RPM = 4000.0;
    public static final double TICKS_PER_REV = 28.0;
    public static final double TARGET_TICKS_PER_SEC = (FLYWHEEL_TARGET_RPM/60) * TICKS_PER_REV;


    @Override
    public void init() {
        mechanics.init(hardwareMap);
    }

    @Override
    public void loop() {

        /*
         * ---------- Controls ----------
         * Takes in input every time this loops
         */
        double x = gamepad1.left_stick_x;
        double y = gamepad1.left_stick_y;
        double rx = gamepad1.right_stick_x;
        double intakeStrength = gamepad1.right_trigger - gamepad1.left_trigger;
        boolean launchOn = gamepad1.right_bumper;
        boolean flywheelOn = gamepad1.left_bumper;


        /*
         * ---------- Driving ----------
         */
        mechanics.driveJoysticksField(x, y, rx);

        /*
         * ---------- Intake ----------
         */
        mechanics.intakePower(intakeStrength);

        /*
         * ---------- Shooting ----------
         */
        if (AUTOMATIC_SHOOTING && launchOn) {
            mechanics.flywheelToLaunch(TARGET_TICKS_PER_SEC, intakeStrength);
        }
        else {
            mechanics.flywheelSpeed(TARGET_TICKS_PER_SEC, flywheelOn);
        }
    }
}
