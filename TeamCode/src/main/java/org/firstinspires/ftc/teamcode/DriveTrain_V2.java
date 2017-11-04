package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="DriveTrain_V2", group="Team11920")
//@Disabled
public class DriveTrain_V2 extends LinearOpMode{
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
    private Servo clawColour;

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
        clawColour = hardwareMap.servo.get("CC");

        waitForStart();
        runtime.reset();

        while(opModeIsActive()) {

            //joystick driving
            leftFront.setPower(-gamepad1.left_stick_y*1.25);
            leftBack.setPower(-gamepad1.left_stick_y*1.25);
            rightFront.setPower(gamepad1.right_stick_y*1.25);
            rightBack.setPower(gamepad1.right_stick_y*1.25);

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
            if (gamepad1.a) {
                clawFrontServo.setPosition(-90);
            }
            if (gamepad1.b) {
                clawFrontServo.setPosition(90);
            }
            if (gamepad1.x) {
                clawColour.setPosition(180);
            }
            if (gamepad1.y) {
                clawColour.setPosition(0);
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
