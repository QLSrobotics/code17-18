package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by hima on 11/3/17.
 */

@TeleOp(name="moveForwardAuto", group="Team11920")


public class moveForward extends LinearOpMode{

    ColorSensor colorSensor;    // Hardware Device Object
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftFront;
    private DcMotor rightFront;
    private DcMotor leftBack;
    private DcMotor rightBack;
    private Servo clawColour;
    private Servo clawFrontServo
    private double colourThreshold = 100;  //color boundry between blue and red
    private boolean detectingColour = true;
    @Override
    public void runOpMode() {

        //mapping the motor in this program to the motor in robot configuration
        leftFront = hardwareMap.dcMotor.get("LF");
        rightFront = hardwareMap.dcMotor.get("RF");
        leftBack = hardwareMap.dcMotor.get("LB");
        rightBack = hardwareMap.dcMotor.get("RB");
        clawColour = hardwareMap.servo.get("CC");
        clawFrontServo = hardwareMap.servo.get("CFS")

        float hsvValues[] = {0F,0F,0F};
        final float values[] = hsvValues;
        colorSensor = hardwareMap.get(ColorSensor.class, "sensor_color_front");
        waitForStart();
        while (opModeIsActive()) {

            clawFrontServo.setPosition(-50);



            /*Color.RGBToHSV(colorSensor.red() * 8, colorSensor.green() * 8, colorSensor.blue() * 8, hsvValues);

            clawColour.setPosition(-90);
            sleep(700);
            while(detectingColour) {
                if ((hsvValues[0] <= colourThreshold - 10 && hsvValues[0] > 0)) {
                    driveStraight(1, 500);
                }
                else if ((hsvValues[0] > colourThreshold + 10)) {
                    driveStraight(-1, 500);
                }
                detectingColour = false;
            }
            //program terminated
            clawColour.setPosition(90); */

            driveStraight(1,5);
            sleep(100);
            turn(-1,1);

            clawFrontServo.setPosition(60);

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
