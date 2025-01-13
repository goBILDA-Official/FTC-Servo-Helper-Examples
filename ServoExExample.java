/*   MIT License
 *   Copyright (c) [2025] [Base 10 Assets, LLC]
 *
 *   Permission is hereby granted, free of charge, to any person obtaining a copy
 *   of this software and associated documentation files (the "Software"), to deal
 *   in the Software without restriction, including without limitation the rights
 *   to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *   copies of the Software, and to permit persons to whom the Software is
 *   furnished to do so, subject to the following conditions:

 *   The above copyright notice and this permission notice shall be included in all
 *   copies or substantial portions of the Software.

 *   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *   FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *   AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *   LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *   SOFTWARE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.PwmControl;
import com.qualcomm.robotcore.hardware.ServoImplEx;


/*
 * This file shows some of the cool features included in ServoImplEx. The extended implementation
 * of the standard Servo class.
 */

@TeleOp(name="Servo Extended Example", group="Concept")
@Disabled
public class ServoExExample extends LinearOpMode {

    // Declare OpMode member.
    private ServoImplEx servo = null;


    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");

        /*
         * Initialize the hardware variables, string here must exactly match the name of a configured
         * servo in the Robot Configuration on your Driver Station.
         */
        servo = hardwareMap.get(ServoImplEx.class, "servo");

        /*
         * This sets the PWM Range, which is the maximum and minimum signal length that the servo
         * can accept. This changes from servo-to-servo. Some (like the goBILDA Dual Mode Servos)
         * can accept a slightly wider range. Sending a wider range allows the servo to rotate further
         * when you change the control signal.
         * By default the REV hub outputs a signal of 600µsec when set to 0, and 2400µsec when set to 1.
         * Using .setPwmRange() we are extending that out to 500µsec min, and 2500µsec max.
         * Some servos (like Hitec Linear Servos) have narrower PWM ranges than the stock.
         * Most servos show this "PWM range" as a spec on the product page. If you have
         * a servo that doesn't work (especially from a brand that doesn't focus on FTC), double
         * check the PWM range! Sometimes sending an out-of-range signal results in no response.
         */
        servo.setPwmRange(new PwmControl.PwmRange(500,2500));

        /*
         * Here we send the min and max PWM values we set over telemetry.
         */
        telemetry.addData("Servo min PWM signal", servo.getPwmRange().usPulseLower);
        telemetry.addData("Servo max PWM signal", servo.getPwmRange().usPulseUpper);
        telemetry.update();


        /*
         * Wait for the game to start (driver presses START)
         */
        waitForStart();

        /*
         * Run until the end of the match (driver presses STOP)
         */
        while (opModeIsActive()) {

            /*
             * Another feature of ServoImplEx is that you can enable and disable the PWM output
             * of each servo individually. This can be handy if you want a servo to go "limp".
             * Some servos still hold a position even if you disable the PWM output. So test
             * your servos and see what their response is when you enable/disable the PWM signal.
             */
            if(gamepad1.a){
                servo.setPwmEnable();
                servo.setPosition(0.5);
            } else if (gamepad1.b){
                servo.setPwmDisable();
            }

            // Show the servo position
            telemetry.addData("Servo Enabled", servo.isPwmEnabled());
            telemetry.update();
        }
    }
}
