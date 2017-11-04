package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="farRed", group="Team11920")

public class farRed extends LinearOpMode {

    private ColorSensor colorSensorBack;    // Hardware Device Object
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftFront;
    private DcMotor rightFront;
    private DcMotor leftBack;
    private DcMotor rightBack;
    private Servo clawColour;
    private Servo clawFrontServo;
    private final double COLOUR_THRESHOLD = 100;  //color boundry between blue and red
    private String ballColour = "";

    @Override
    public void runOpMode() {


        //mapping the motor in this program to the motor in robot configuration
        leftFront = hardwareMap.dcMotor.get("LF");
        rightFront = hardwareMap.dcMotor.get("RF");
        leftBack = hardwareMap.dcMotor.get("LB");
        rightBack = hardwareMap.dcMotor.get("RB");
        clawColour = hardwareMap.servo.get("CC");
        clawFrontServo = hardwareMap.servo.get("CFS");
//
//        rightBack.setDirection(DcMotor.Direction.REVERSE);
//        rightFront.setDirection(DcMotor.Direction.REVERSE);
//        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        float hsvValues[] = {0F,0F,0F};
        final float values[] = hsvValues;
        colorSensorBack = hardwareMap.get(ColorSensor.class, "sensor_color_back");
        waitForStart();
        while (opModeIsActive()) {

            clawFrontServo.setPosition(50);

            sleep(2000);

            //lower servo arm
            clawColour.setPosition(180);

            sleep(2000);

            // convert the RGB values to HSV values.
            Color.RGBToHSV(colorSensorBack.red() * 8, colorSensorBack.green() * 8, colorSensorBack.blue() * 8, hsvValues);

            sleep(2000);

            //reading color
            if ((hsvValues[0] > COLOUR_THRESHOLD)) {
                ballColour = "BLUE";
                telemetry.addLine("color is BLUE");
            }
            else if ((hsvValues[0] <= COLOUR_THRESHOLD && hsvValues[0] > 0)) {
                ballColour = "RED";
                telemetry.addLine("color is RED");
            }
            else {
                telemetry.addLine("color is not detected");
            }
            sleep(1000);
            //knocking ball
            switch (ballColour) {
                case "RED":
                    moveStraight(0.5,300);
                    sleep(1000);
                    clawColour.setPosition(0);
                    moveStraight(0.5,1000);
                    sleep(1000);
                    turn(0.45,950);
                    sleep(1000);
                    moveStraight(0.45,400);
                    sleep(1000);
                    break;
                case "BLUE":
                    moveStraight(-0.5,300);
                    sleep(1000);
                    moveStraight(0.5,600);
                    sleep(1000);
                    clawColour.setPosition(0);
                    moveStraight(0.5,1000);
                    sleep(1000);
                    turn(0.45,950);
                    sleep(1000);
                    moveStraight(0.5,400);
                    sleep(1000);
                    break;
                default:
                    clawColour.setPosition(0);
                    sleep(1000);
                    moveStraight(0.5,1200);
                    sleep(1000);
                    turn(0.45,950);
                    sleep(1000);
                    moveStraight(0.5,400);
                    sleep(1000);
                    break;
            }

            //clear container
            ballColour = "";
            //program terminated

            clawFrontServo.setPosition(0);

            telemetry.update();
            idle();
            break;
        }
    }

    public void moveStraight(double power, int time) {
        leftFront.setPower(power);
        leftBack.setPower(power);
        rightFront.setPower(-power);
        rightBack.setPower(-power);
        sleep(time);
        leftFront.setPower(0);
        leftBack.setPower(0);
        rightFront.setPower(0);
        rightBack.setPower(0);
    }
    //positive power turning left
    public void turn(double power, int time) {
        leftFront.setPower(power);
        leftBack.setPower(power);
        rightFront.setPower(power);
        rightBack.setPower(power);
        sleep(time);
        leftFront.setPower(0);
        leftBack.setPower(0);
        rightFront.setPower(0);
        rightBack.setPower(0);
    }

    public void sleep(int i){
        long initial_time = System.currentTimeMillis();
        while(System.currentTimeMillis()-initial_time <i) {
        }
    }


}
