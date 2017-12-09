package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
/**
 * Created by hima on 12/6/17.
 */

@TeleOp(name="Tonytest", group="tony")

public class tonyTest extends LinearOpMode{
    private DcMotor a; // setup motor a
    private DcMotor b; // setup motor b
    private DcMotor c; // setup motor c
    private DcMotor d; // setup motor d

    public void runOpMode() throws InterruptedException {
        a = hardwareMap.dcMotor.get("a"); //
        b = hardwareMap.dcMotor.get("b"); //
        c = hardwareMap.dcMotor.get("c"); //
        d = hardwareMap.dcMotor.get("d"); //

        waitForStart();


        while(opModeIsActive()){
            double power = gamepad1.left_stick_y; // control is joystick

            a.setPower(power); // a forward
            b.setPower(power); // b forward
            c.setPower(-power); // c backward
            d.setPower(-power); //  dbackward

            telemetry.update();



        }
    }


}
