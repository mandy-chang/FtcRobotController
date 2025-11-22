package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Pinpoint TeleOp", group="Linear OpMode")
public class PinpointTeleOp extends LinearOpMode {

    // Declare OpMode members
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftFrontDrive = null;
    private DcMotor leftBackDrive = null;
    private DcMotor rightFrontDrive = null;
    private DcMotor rightBackDrive = null;
    private DcMotor intakeMotor = null;

    @Override
    public void runOpMode() {

        // Initialize the hardware variables
        leftFrontDrive = hardwareMap.get(DcMotor.class, "leftFrontDrive");
        leftBackDrive = hardwareMap.get(DcMotor.class, "leftBackDrive");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "rightFrontDrive");
        rightBackDrive = hardwareMap.get(DcMotor.class, "rightBackDrive");
        intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");

        // Set motor directions
        leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        leftBackDrive.setDirection(DcMotor.Direction.REVERSE);
        rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotor.Direction.FORWARD);
        intakeMotor.setDirection(DcMotor.Direction.REVERSE);  // Adjust direction if needed

        // Wait for the game to start
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        runtime.reset();

        // Main loop
        while (opModeIsActive()) {
            double max;

            // Get joystick inputs for omni-directional movement
            double axial = -gamepad1.left_stick_y;  // Forward/backward
            double lateral = gamepad1.left_stick_x;  // Strafe left/right
            double yaw = gamepad1.right_stick_x;  // Rotate

            // Calculate motor powers based on joystick input
            double leftFrontPower = axial + lateral + yaw;
            double rightFrontPower = axial - lateral - yaw;
            double leftBackPower = axial - lateral + yaw;
            double rightBackPower = axial + lateral - yaw;

            // Normalize power values
            max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
            max = Math.max(max, Math.abs(leftBackPower));
            max = Math.max(max, Math.abs(rightBackPower));

            if (max > 1.0) {
                leftFrontPower /= max;
                rightFrontPower /= max;
                leftBackPower /= max;
                rightBackPower /= max;
            }

            // Intake control using gamepad buttons
            if (gamepad1.a) {
                intakeMotor.setPower(1);  // Spin intake forward
            } else if (gamepad1.b) {
                intakeMotor.setPower(-1);  // Spin intake in reverse
            } else {
                intakeMotor.setPower(0);  // Stop intake motor
            }

            // Set motor power for omni drive
            leftFrontDrive.setPower(leftFrontPower);
            rightFrontDrive.setPower(rightFrontPower);
            leftBackDrive.setPower(leftBackPower);
            rightBackDrive.setPower(rightBackPower);

            // Show telemetry data
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Front left/Right", "%4.2f, %4.2f", leftFrontPower, rightFrontPower);
            telemetry.addData("Back left/Right", "%4.2f, %4.2f", leftBackPower, rightBackPower);
            telemetry.addData("Intake Power", intakeMotor.getPower());  // Display intake motor power
            telemetry.update();
        }
    }
}
