package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by hima on 11/3/17.
 */

@TeleOp(name="farRed", group="Team11920")


public class farRed extends LinearOpMode {
    ColorSensor colorSensor;    // Hardware Device Object
    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor leftFront;
    private DcMotor rightFront;
    private DcMotor leftBack;
    private DcMotor rightBack;

    private Servo clawColour;
    private Servo clawFrontServo;

    ColorSensor colSensFrnt;
    ColorSensor colSensBack;

<<<<<<< HEAD

    int blue = 60;
    int red = 60;
    int minVal = 20;
=======
    int blue = 60; // variables will edit values
    int red = 60; // variables will edit values
    int minVal = 20; // variables will edit values
>>>>>>> 8457cfedb89cc3df4064993b17bf9cc03c44c552
    String frontBallCol;

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
        clawFrontServo = hardwareMap.servo.get("CFS");

        colSensBack = hardwareMap.colorSensor.get("CSB");
        colSensFrnt = hardwareMap.colorSensor.get("CSF");

        float hsvValues[] = {0F,0F,0F};
        final float values[] = hsvValues;
        colorSensor = hardwareMap.get(ColorSensor.class, "sensor_color_front");
        waitForStart();
        while (opModeIsActive()) {

            clawColour.setPosition(-50);

            //checks if the ball is red
            if(checkCol()== "red") {

                //Moves backwards to knock the blue ball off
                driveStraight(-1, 2);

                //lifts the arm
                clawColour.setPosition(40);

                //moves forward towards the shelf
                driveStraight(1, 7);

                //turns left a little bit towards the shelf
                turn(1, 1);

                //move forward to put the cube in the shelf
                driveStraight(1, 2);

                //opens claw
                clawFrontServo.setPosition(50);
            }
            //if the ball is blue
            else if (checkCol()== "blue") {

                //move forward to knock the blue ball
                driveStraight(1, 2);

                //lift the arm up
                clawColour.setPosition(40);

                //move forward to the shelf
                driveStraight(1, 5);

                //turn left facing the shelf
                turn(1, 5);

                //move forward to shelf
                driveStraight(1, 5);


                //open claw
                clawFrontServo.setPosition(50);
            }

            else if (checkCol()== "undef"){
                //lift the arm up
                clawColour.setPosition(40);

                //move forward to the shelf
                driveStraight(1, 5);

                //turn left facing the shelf
                turn(1, 5);

                //move forward to shelf
                driveStraight(1, 5);


                //open claw
                clawFrontServo.setPosition(50);

            }










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

    public String checkCol(){ // color sensor algorithm
        if (colSensFrnt.red()>= red && colSensBack.blue()>= blue){
            return "red";
        }else if(colSensFrnt.blue()>= blue && colSensBack.red()>= red) {
            return "blue";
        }else if (colSensFrnt.red()>= red && colSensBack.blue()<= minVal) {
            return "red";
        }else if (colSensFrnt.blue()>= blue && colSensBack.red()<= minVal ){
            return "blue";
        }else{
            return "undef";
        }
    }
}
