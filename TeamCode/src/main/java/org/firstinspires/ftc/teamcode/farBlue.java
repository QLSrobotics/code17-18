package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="farBlue", group="Team11920")

public class farBlue extends LinearOpMode {

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
    static final double COUNTS_PER_MOTOR_REV = 1120;    // eg: Andy mark Motor Encoder
    static final double WHEEL_DIAMETER_INCHES = 4.0;     // For figuring circumference
    double countsl = 0;
    double countsr = 0;
    @Override
    public void runOpMode() {

        //mapping the motor in this program to the motor in robot configuration
        leftFront = hardwareMap.dcMotor.get("LF");
        rightFront = hardwareMap.dcMotor.get("RF");
        leftBack = hardwareMap.dcMotor.get("LB");
        rightBack = hardwareMap.dcMotor.get("RB");
        clawColour = hardwareMap.servo.get("CC");
        clawFrontServo = hardwareMap.servo.get("CFS");

        rightBack.setDirection(DcMotor.Direction.REVERSE);
        rightFront.setDirection(DcMotor.Direction.REVERSE);
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        float hsvValues[] = {0F,0F,0F};
        final float values[] = hsvValues;
        colorSensorBack = hardwareMap.get(ColorSensor.class, "sensor_color_back");
        waitForStart();
        while (opModeIsActive()) {

            clawFrontServo.setPosition(-50);

            // convert the RGB values to HSV values.
            Color.RGBToHSV(colorSensorBack.red() * 8, colorSensorBack.green() * 8, colorSensorBack.blue() * 8, hsvValues);

            //lower servo arm
            clawColour.setPosition(180);
            sleep(1000);

            //reading color
            if ((hsvValues[0] > COLOUR_THRESHOLD)) {
                ballColour = "BLUE";
            }
            else if ((hsvValues[0] <= COLOUR_THRESHOLD && hsvValues[0] > 0)) {
                ballColour = "RED";
            }

            //knocking ball
            switch (ballColour) {
                case "RED":
                    drive(-1, 1);
                    break;
                case "BLUE":
                    drive(1, 1);
                    break;
                default:
                    break;
            }

            //clear container
            ballColour = "";
            //program terminated
            clawColour.setPosition(-180);

            sleep(1000);

            drive(-1,1);
            turn(-1,1);
            drive(-1,1);
            turn(1,1);

            clawFrontServo.setPosition(40);

            telemetry.update();
            idle();
            break;
        }
    }
    //in memory of Mr. Harsha
    public void drive(double distance, double power) {
        double x;
        x = (distance * 12 * COUNTS_PER_MOTOR_REV) / (WHEEL_DIAMETER_INCHES * 3.1415);
        countsl=countsl-x;
        countsr=countsr-x;
        leftFront.setTargetPosition((int)countsl);
        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftFront.setPower(-1*power);
        leftBack.setTargetPosition((int)countsl);
        leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBack.setPower(-1*power);
        rightFront.setTargetPosition((int)countsr);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setPower(-1*power);
        rightBack.setTargetPosition((int)countsr);
        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBack.setPower(-1*power);
        while (leftFront.isBusy() && leftBack.isBusy() && rightFront.isBusy() && rightBack.isBusy()) {

        }
    }


    public void turn(double distance, double power) {
        double x;
        x = (distance * 12 * COUNTS_PER_MOTOR_REV) / (WHEEL_DIAMETER_INCHES * 3.14159);
        countsl=countsl-x;
        countsr=countsr+x;
        leftFront.setTargetPosition((int)countsl);
        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftFront.setPower(-1*power);
        leftBack.setTargetPosition((int)countsl);
        leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBack.setPower(-1*power);
        rightFront.setTargetPosition((int)countsr);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setPower(power);
        rightBack.setTargetPosition((int)countsr);
        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBack.setPower(power);
        while (leftFront.isBusy() && leftBack.isBusy() && rightFront.isBusy() && rightBack.isBusy()) {

        }
    }
    public void sleep(int i){
        long initial_time = System.currentTimeMillis();
        while(System.currentTimeMillis()-initial_time <i) {
        }
    }
}
