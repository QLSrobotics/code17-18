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

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftFront;
    private DcMotor rightFront;
    private DcMotor leftBack;
    private DcMotor rightBack;

    private Servo clawColour;
    private Servo clawFrontServo;

    ColorSensor colSensFrnt;
    ColorSensor colSensBack;

    int blue = 60; // variables will edit values
    int red = 60; // variables will edit values
    int minVal = 20; // variables will edit values
    String frontBallCol;
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



        waitForStart();
        while (opModeIsActive()) {

            clawFrontServo.setPosition(-50);

            sleep(2000);

            frontBallCol = checkCol();

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
