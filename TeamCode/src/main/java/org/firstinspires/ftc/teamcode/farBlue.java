package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="farBlue", group="Team11920")


public class farBlue extends LinearOpMode {

    private ColorSensor colorSensorBack;    // Hardware Device Object
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftFront;
    private DcMotor rightFront;
    private DcMotor leftBack;
    private DcMotor rightBack;
    private Servo clawColour;
    private Servo clawFrontServo;
    private double colourThreshold = 100;  //color boundry between blue and red
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

        float hsvValues[] = {0F,0F,0F};
        final float values[] = hsvValues;
        colorSensorBack = hardwareMap.get(ColorSensor.class, "sensor_color_back");
        waitForStart();
        while (opModeIsActive()) {

            clawFrontServo.setPosition(-50);

            //william code
            // convert the RGB values to HSV values.
            Color.RGBToHSV(colorSensorBack.red() * 8, colorSensorBack.green() * 8, colorSensorBack.blue() * 8, hsvValues);

            //lower servo arm
            clawColour.setPosition(120);
            sleep(700);

            //reading color
            if ((hsvValues[0] > colourThreshold + 10)) {
                ballColour = "BLUE";
            }
            else if ((hsvValues[0] <= colourThreshold - 10 && hsvValues[0] > 0)) {
                ballColour = "RED";
            }

            //knocking ball
            switch (ballColour) {
                case "RED":
                    driveStraight(-1, 300);
                    break;
                case "BLUE":
                    driveStraight(1, 300);
                    break;
                default:
                    break;
            }

            //clear container
            ballColour = "";
            //program terminated
            clawColour.setPosition(-120);

            //hima code
            driveStraight(-1,5);
            turn(-1,2);
            driveStraight(-1,2);
            turn(1,2);

            clawFrontServo.setPosition(40);

            telemetry.update();
            idle();
        }
    }

    private void driveStraight(double speed, int time) {
        rightFront.setPower(-speed);
        rightBack.setPower(-speed);
        leftFront.setPower(speed);
        leftBack.setPower(speed);
        sleep(time);
        rightFront.setPower(0);
        rightBack.setPower(0);
        leftFront.setPower(0);
        leftBack.setPower(0);

    }

    //positive speed for left turn
    //negative speed for right turn
    private void turn(double speed, int time) {
        rightFront.setPower(speed);
        rightBack.setPower(speed);
        leftFront.setPower(speed);
        leftBack.setPower(speed);
        sleep(time);
        rightFront.setPower(0);
        rightBack.setPower(0);
        leftFront.setPower(0);
        leftBack.setPower(0);
    }
    public void sleep(int i){
        long initial_time = System.currentTimeMillis();
        while(System.currentTimeMillis()-initial_time <i) {
        }
    }
}
