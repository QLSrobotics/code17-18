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

@TeleOp(name="relicRed", group="Team11920")


public class relicRed extends LinearOpMode {

    ColorSensor colorSensor;    // Hardware Device Object
    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor leftFront;
    private DcMotor rightFront;
    private DcMotor leftBack;
    private DcMotor rightBack;

    private Servo clawColour;
    private Servo clawFrontServo;

    private ColorSensor colSensFrnt;
    private ColorSensor colSensBack;


    int blue = 60;
    int red = 60;
    int minVal = 20;
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

        colSensBack = hardwareMap.colorSensor.get("CSB1");
        colSensFrnt = hardwareMap.colorSensor.get("CSF1");

        float hsvValues[] = {0F,0F,0F};
        final float values[] = hsvValues;
        colorSensor = hardwareMap.get(ColorSensor.class, "sensor_color_front");
        waitForStart();
        while (opModeIsActive()) {

            clawColour.setPosition(-50);
            if(checkCol() == "blue"){
                //this code goes back then lifts up the arm
                driveStraight(-1,2);
                clawColour.setPosition(40);

                driveStraight(1,5);

                driveStraight(1,2);
                clawFrontServo.setPosition(50);


            }else if (checkCol()=="red"){
                driveStraight(-1, 2);
                //this code makes the robot goes forward

                clawColour.setPosition(40);
                //moves the color sensor stick up

                driveStraight(-1, 5);
                //moves forward

                turn(-1,5);
                //turn left facing the cryptobox

                driveStraight(-1, 5);
                //moves forward

                clawFrontServo.setPosition(50);
                //open claw


            }else if (checkCol() == "undef"){
                clawColour.setPosition(40);
                //moves the color sensor stick up
                driveStraight(-1, 5);

                //moves forward
                turn(-1,7);
                //turn left facing the cryptobox
                driveStraight(-1, 5);
                //moves forward
                clawFrontServo.setPosition(50);
                //open claw

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

    public String checkCol(){
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

    public void sleep(int i){
        long initial_time = System.currentTimeMillis();
        while(System.currentTimeMillis()-initial_time <i) {
        }
    }
}
