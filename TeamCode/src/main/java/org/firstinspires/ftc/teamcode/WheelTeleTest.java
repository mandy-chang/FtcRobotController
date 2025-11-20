package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Test RightBack Motor", group="TeleOp")
public class WheelTeleTest extends OpMode {

    private Hardware robot = new Hardware();

    @Override
    public void init() {
        robot.init(hardwareMap);
        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }

    @Override
    public void loop() {
        // Right joystick: translation
        double y = -gamepad1.right_stick_y; // forward/back
        double x = gamepad1.right_stick_x;  // strafe left/right

        // Left joystick: rotation
        double rot = gamepad1.left_stick_x;

        // Calculate rightBack power (single motor test)
        // Mecanum formula: backRight = y + x - rot
        double rightBackPower = y + x - rot;

        // Clip power to [-1,1]
        rightBackPower = Math.max(-1.0, Math.min(1.0, rightBackPower));

        // Set motor power
        robot.rightBack.setPower(rightBackPower);

        // Telemetry
        telemetry.addData("RightBack Motor Power", rightBackPower);
        telemetry.update();
    }
}
