package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by hima on 12/8/17.
 */

@TeleOp(name="template", group="Team11920")
@Disabled

public class Tony_Drive extends LinearOpMode{

    /*
    intake motor right
    intake motoer left
        intake left shoulder
        outtake right shoulder
        stop dpad left


    front right wheel
    back right wheel
    back left wheel
    front left wheel

    folding motor (run at hald speed)
        up dpad up
        down dpad down
        stop dpad right

    dump servo + dump2 (x/y)
    algiment servo (a/b)
    back servo (lft right trigger)



    full suite of meccum motion

     */

    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor flWheel;
    private DcMotor blWheel;
    private DcMotor frWheel;
    private DcMotor brWheel;

    private DcMotor inR;
    private DcMotor inL;

    private DcMotor folMot;

    private Servo dumSer;
    private Servo alSer;
    private Servo dumSer2;
    private Servo backSer;

    public void runOpMode()throws InterruptedException{

        flWheel = hardwareMap.dcMotor.get("flw");
        blWheel = hardwareMap.dcMotor.get("blw");
        frWheel = hardwareMap.dcMotor.get("frw");
        brWheel = hardwareMap.dcMotor.get("brw");

        inL = hardwareMap.dcMotor.get("inl");
        inR = hardwareMap.dcMotor.get("inr");

        folMot = hardwareMap.dcMotor.get("folmot");

        dumSer = hardwareMap.servo.get("dser");
        dumSer2 = hardwareMap.servo.get("dser2");
        alSer = hardwareMap.servo.get("aser");
        backSer = hardwareMap.servo.get("bser");

        while(opModeIsActive()){
            //dump servo
            if (gamepad1.x){dumSer.setPosition(.75); dumSer2.setPosition(.5);}
            if (gamepad1.y){dumSer.setPosition(.5); dumSer2.setPosition(.75);}

            //algiment servo
            if (gamepad1.a){alSer.setPosition(.75);}
            if (gamepad1.b){alSer.setPosition(.5);}

            //back servo
            if(gamepad1.left_trigger>.5){backSer.setPosition(.75);}
            if(gamepad1.right_trigger>.5){backSer.setPosition(.5);}

            //folding mtoer
            if (gamepad1.dpad_up){folMot.setPower(0.5);}
            if (gamepad1.dpad_down){folMot.setPower(-0.5);}
            if (gamepad1.dpad_right){folMot.setPower(0);}

            //intakes
            if(gamepad1.left_bumper){inR.setPower(-1); inL.setPower(1);}
            if(gamepad1.right_bumper){inR.setPower(1); inL.setPower(-1);}
            if(gamepad1.dpad_left){inR.setPower(0); inL.setPower(0);}

            //tnak drive
            flWheel.setPower(gamepad1.left_stick_y);
            blWheel.setPower(gamepad1.left_stick_y);
            frWheel.setPower(gamepad1.right_stick_y);
            brWheel.setPower(gamepad1.right_stick_y);

//            //meccanm stuff
//            flWheel.setPower(gamepad1.right_stick_x+gamepad1.left_stick_y+gamepad1.left_stick_x);
//            frWheel.setPower(-gamepad1.right_stick_x+gamepad1.left_stick_y-gamepad1.left_stick_x);
//            brWheel.setPower(gamepad1.right_stick_x+gamepad1.left_stick_y-gamepad1.left_stick_x);
//            blWheel.setPower(-gamepad1.right_stick_x+gamepad1.left_stick_y+gamepad1.left_stick_x);






            telemetry.update();

        }
    }
}
