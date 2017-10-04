package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by William on 9/19/17.
 */
@TeleOp(name="DriveTrain", group="Team 11920")
@Disabled
public class DriveTrain extends LinearOpMode{
    //motor initialized here
    //comment out the motors' declaration not physically connected to the robot
    private DcMotor leftFront;
    private DcMotor rightFront;
    private DcMotor leftBack;
    private DcMotor rightBack;
    private DcMotor clawLeft;
    private DcMotor clawRight;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        //mapping the motor in this program to the motor in robot configuration
        leftFront = hardwareMap.dcMotor.get("LF");
        rightFront = hardwareMap.dcMotor.get("RF");
        leftBack = hardwareMap.dcMotor.get("LB");
        rightBack = hardwareMap.dcMotor.get("RB");
        clawLeft = hardwareMap.dcMotor.get("CL");
        clawRight = hardwareMap.dcMotor.get("CR");

    }
}
