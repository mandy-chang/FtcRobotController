package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Hardware {

    public DcMotor leftFront;
    public DcMotor leftBack;
    public DcMotor rightFront;
    public DcMotor rightBack;
    public DcMotor intakeMotor;

    // Don't expose hwMap publicly
    private HardwareMap hwMap;

    // Initialize all motors
    public void init(HardwareMap hwMap) {
        this.hwMap = hwMap;

//        leftFront  = hwMap.get(DcMotor.class, "leftFront");
//        leftBack   = hwMap.get(DcMotor.class, "leftBack");
//        rightFront = hwMap.get(DcMotor.class, "rightFront");
        rightBack  = hwMap.get(DcMotor.class, "rightBack");
        intakeMotor = hwMap.get(DcMotor.class, "intakeMotor");

        // Set motor directions if needed
//        leftFront.setDirection(DcMotor.Direction.FORWARD);
//        leftBack.setDirection(DcMotor.Direction.FORWARD);
//        rightFront.setDirection(DcMotor.Direction.REVERSE);
        rightBack.setDirection(DcMotor.Direction.REVERSE);
        intakeMotor.setDirection(DcMotor.Direction.FORWARD);
    }
}
