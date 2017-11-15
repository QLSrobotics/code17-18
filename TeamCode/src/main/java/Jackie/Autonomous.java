package Jackie;

/**
 * Created by JacKie825 on 11/14/17.
 */

public class Autonomous {
    package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

//import everything from existed code

    @Autonomous(name="farBlue", group="Team11920")

    public class farBlue extends LinearOpMode {

        private ColorSensor colorSensorBack;    // Hardware Device Object
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

            //mapping the motor like the config thing in the phone
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

                clawFrontServo.setPosition(90);

                sleep(1000);
                // sleep = set all motors to 0

                //lower arm
                clawColour.setPosition(180);
                sleep(1000);

                Color.RGBToHSV(colorSensorBack.red() * 8, colorSensorBack.green() * 8, colorSensorBack.blue() * 8, hsvValues);

                //color sensing
                if ((hsvValues[0] > COLOUR_THRESHOLD)) {
                    ballColour = "BLUE";
                }
                else if ((hsvValues[0] <= COLOUR_THRESHOLD && hsvValues[0] > 0)) {
                    ballColour = "RED";
                }

                sleep(1000);
                //pick which ball to fkock off
                switch (ballColour) {
                    case "RED":
                        moveStraight(-0.5,300);
                        sleep(1000);
                        clawColour.setPosition(0);
                        sleep(1000);
                        moveStraight(-0.5,700);
                        sleep(1000);
                        turn(-0.5,2000);
                        sleep(1000);
                        moveStraight(0.45,600);
                        sleep(1000);
                        break;
                    case "BLUE":
                        moveStraight(0.5,300);
                        sleep(1000);
                        clawColour.setPosition(0);
                        sleep(1000);
                        moveStraight(-0.5,1300);
                        sleep(1000);
                        turn(-0.5,2000);
                        sleep(1000);
                        moveStraight(0.45,600);
                        sleep(1000);
                        break;
                    default:
                        clawColour.setPosition(0);
                        sleep(1000);
                        moveStraight(-0.5,850);
                        sleep(1000);
                        turn(-0.5,2000);
                        sleep(1000);
                        moveStraight(0.45,600);
                        sleep(1000);
                        break;
                }
                
                ballColour = "";
                sleep(1000);

                clawFrontServo.setPosition(-90);

                telemetry.update();
                idle();
                break;
            }
        }
        //negative power because motors are hooked in the opposite side are fliped
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
}
