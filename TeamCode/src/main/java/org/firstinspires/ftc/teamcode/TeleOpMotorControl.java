package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Mecanum Drive Full", group="TeleOp")
public class TeleOpMecanum extends OpMode {

    private Hardware robot = new Hardware(); // Your Hardware class

    @Override
    public void init() {
        // Initialize all hardware using the OpMode's hardwareMap
        robot.init(hardwareMap);

        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }

    @Override
    public void loop() {
        // Right joystick controls translation
        double y = -gamepad1.right_stick_y; // Forward/back
        double x = gamepad1.right_stick_x;  // Strafe left/right

        // Left joystick X controls rotation
        double rot = gamepad1.left_stick_x;

        // Mecanum formulas
        double frontLeftPower  = y + x + rot;
        double backLeftPower   = y - x + rot;
        double frontRightPower = y - x - rot;
        double backRightPower  = y + x - rot;

        // Clip powers to [-1, 1]
        frontLeftPower  = Math.max(-1.0, Math.min(1.0, frontLeftPower));
        backLeftPower   = Math.max(-1.0, Math.min(1.0, backLeftPower));
        frontRightPower = Math.max(-1.0, Math.min(1.0, frontRightPower));
        backRightPower  = Math.max(-1.0, Math.min(1.0, backRightPower));

        // Set motor powers
        robot.leftFront.setPower(frontLeftPower);
        robot.leftBack.setPower(backLeftPower);
        robot.rightFront.setPower(frontRightPower);
        robot.rightBack.setPower(backRightPower);

        // Telemetry for debugging
        telemetry.addData("Motor Powers", "LF: %.2f, LB: %.2f, RF: %.2f, RB: %.2f",
                frontLeftPower, backLeftPower, frontRightPower, backRightPower);
        telemetry.update();
    }
}
