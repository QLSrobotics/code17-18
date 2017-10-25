package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by William on 9/19/17.
 */
@TeleOp(name="DriveTrain", group="Team11920")
//@Disabled
public class DriveTrain extends LinearOpMode{
    //motor initialized here
    //comment out the motors' declaration not physically connected to the robot
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftFront;
    private DcMotor rightFront;
    private DcMotor leftBack;
    private DcMotor rightBack;
    private DcMotor claw;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        //mapping the motor in this program to the motor in robot configuration
        leftFront = hardwareMap.dcMotor.get("LF");
        rightFront = hardwareMap.dcMotor.get("RF");
        leftBack = hardwareMap.dcMotor.get("LB");
        rightBack = hardwareMap.dcMotor.get("RB");
        claw = hardwareMap.dcMotor.get("CLAW");

        waitForStart();
        runtime.reset();

        while(opModeIsActive()) {

            //joystick driving
            leftFront.setPower(gamepad1.left_stick_y + gamepad1.left_stick_x + gamepad1.right_stick_x);
            leftBack.setPower(gamepad1.left_stick_y - gamepad1.left_stick_x + gamepad1.right_stick_x);
            rightFront.setPower(-gamepad1.left_stick_y - gamepad1.left_stick_x + gamepad1.right_stick_x);
            rightBack.setPower(-gamepad1.left_stick_y + gamepad1.left_stick_x + gamepad1.right_stick_x);

            //controlling lift
            if (gamepad1.dpad_up) {
                claw.setPower(0.4);
            }
            if (gamepad1.dpad_down) {
                claw.setPower(-0.4);
            }

            //updating robot status and display on driver station
            telemetry.addData("Status", "Run Time: " + runtime.toString());

            //refresh
            telemetry.update();

            //always call at the bottom in while(opModeIsActive)
            idle();
        }

    }
}
