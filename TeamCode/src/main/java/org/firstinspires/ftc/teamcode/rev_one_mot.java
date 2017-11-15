package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by hima on 11/14/17.
 */

@TeleOp(name="rev_one_mot", group="Team11920")


public class rev_one_mot extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftFront;
    private ColorSensor colourSens;
    private ColorSensor colourSens2;

    @Override
    public void runOpMode() {

        //mapping the motor in this program to the motor in robot configuration
        leftFront = hardwareMap.dcMotor.get("LF");
        colourSens = hardwareMap.colorSensor.get("imu");
        colourSens2 = hardwareMap.colorSensor.get("imu2");



        waitForStart();
        while (opModeIsActive()) {

            printCols();



            //telemetry.addData("green:" + colourSens.green())
            telemetry.update();
            idle();
        }
    }


    public void printCols(){

        telemetry.addData("red:", colourSens.red());
        telemetry.addData("green:", colourSens.green());
        telemetry.addData("blue:", colourSens.blue());

        telemetry.addData("alpha:", colourSens.alpha());
        telemetry.addData("argb:", colourSens.argb());


        telemetry.addData("red2:", colourSens2.red());
        telemetry.addData("green2:", colourSens2.green());
        telemetry.addData("blue2:", colourSens2.blue());

        telemetry.addData("alpha2:", colourSens2.alpha());
        telemetry.addData("argb2:", colourSens2.argb());


    }

    public void sleep(int i){
        long initial_time = System.currentTimeMillis();
        while(System.currentTimeMillis()-initial_time <i) {
        }
    }
}
