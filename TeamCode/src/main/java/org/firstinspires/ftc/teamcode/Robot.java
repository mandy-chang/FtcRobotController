package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Robot {

    // Use your Hardware class
    public Hardware hardware = new Hardware();

    // Optional: convenience reference for easier access
    public DcMotor rightBack;

    /** Initialize the robot hardware */
    public void init(HardwareMap hwMap) {
        // Initialize hardware using OpMode's hardwareMap
        hardware.init(hwMap);

        // Assign the rightBack motor for easier direct use
        rightBack = hardware.rightBack;
    }

    /** Convenience method to run just rightBack motor */
    public void runRightBack(double power) {
        if (rightBack != null) {
            rightBack.setPower(power);
        }
    }

    // Later: add similar methods for leftFront, leftBack, rightFront
}
