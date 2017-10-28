package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
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
    private DcMotor clawFront;
    private DcMotor clawBack;
    private Servo clawFrontServo;
    private Servo clawBackServoPos;
    private Servo clawBackServoClaw;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        //mapping the motor in this program to the motor in robot configuration
        leftFront = hardwareMap.dcMotor.get("LF");
        rightFront = hardwareMap.dcMotor.get("RF");
        leftBack = hardwareMap.dcMotor.get("LB");
        rightBack = hardwareMap.dcMotor.get("RB");
        clawFront = hardwareMap.dcMotor.get("CF");
        clawBack = hardwareMap.dcMotor.get("CB");
        clawFrontServo = hardwareMap.servo.get("CFS");
        clawBackServoPos = hardwareMap.servo.get("CBSP");
        clawBackServoClaw = hardwareMap.servo.get("CBSC");

        waitForStart();
        runtime.reset();

        while(opModeIsActive()) {

            //joystick driving
            leftFront.setPower(gamepad1.left_stick_y*1.2);
            leftBack.setPower(gamepad1.left_stick_y*1.2);
            rightFront.setPower(-gamepad1.right_stick_y*1.2);
            rightBack.setPower(-gamepad1.right_stick_y*1.2);



            //controlling lift
            //controlling lift
            if (gamepad1.dpad_up) {
                clawFront.setPower(-0.4);
                sleep(450);
                clawFront.setPower(0);

            }
            if (gamepad1.dpad_down) {
                clawFront.setPower(0.4);
                sleep(450);
                clawFront.setPower(0);
            }
            if (gamepad1.dpad_left) {
                clawBack.setPower(-0.4);
                sleep(450);
                clawBack.setPower(0);
            }
            if (gamepad1.dpad_right) {
                clawBack.setPower(0.4);
                sleep(450);
                clawBack.setPower(0);
            }
            if (gamepad1.a) {
                clawFrontServo.setPosition(30);
            }
            if (gamepad1.b) {
                clawFrontServo.setPosition(-30);
            }
            if (gamepad1.x) {
                clawBackServoClaw.setPosition(-50);
            }
            if (gamepad1.y) {
                clawBackServoClaw.setPosition(50);
            }
            if (gamepad1.left_bumper) {
                clawBackServoPos.setPosition(30);
            }
            if (gamepad1.right_bumper) {
                clawBackServoPos.setPosition(-30);
            }


            //updating robot status and display on driver station
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            //refresh
            telemetry.update();


        }

    }


    public void sleep(int i){
        long initial_time = System.currentTimeMillis();
        while(System.currentTimeMillis()-initial_time <i){

        }
    }
}
