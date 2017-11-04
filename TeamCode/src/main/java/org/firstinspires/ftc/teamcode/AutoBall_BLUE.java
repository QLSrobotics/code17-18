/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

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

@Autonomous(name = "AutoBall_BLUE", group = "Team11920")
//@Disabled
public class AutoBall_BLUE extends LinearOpMode {

  private ColorSensor colorSensorBack;
  private ElapsedTime runtime = new ElapsedTime();
  private DcMotor leftFront;
  private DcMotor rightFront;
  private DcMotor leftBack;
  private DcMotor rightBack;
  private Servo clawColour;
  private Servo clawFrontServo;
  private double colourThreshold = 100; //color boundry between blue and red
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
          driveStraight(-1, 500);
          break;
        case "BLUE":
          driveStraight(1, 500);
          break;
        default:
          break;
      }

      //clear container
      ballColour = "";
      //program terminated
      clawColour.setPosition(-120);

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
