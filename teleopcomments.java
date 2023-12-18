// Declares package for FIRST Tech Challenge used for encapsulating related classes and code
package org.firstinspires.ftc.teamcode;

// Importing various classes and packages needed for the program, mostly from the RoadRunner library 
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.tuning.TuningOpModes;

/**
 * FTC WIRES TeleOp Example
 *
 */

// An annotation which specifies the metadata for the Tele-operated mode. Sets the name of the TeleOp to "FTC Wires TeleOp" and assigns it to a specific group. 
@TeleOp(name = "FTC Wires TeleOp", group = "00-Teleop")

// Declares a class. Inherits functionality from the LinearOpMode class, hence the word "extend". 
public class FTCWiresTeleOpMode extends LinearOpMode {

    // Method is an override of the "runOpMode" defined in LinearOpMode. Contains main logic and is called when the program is executed. 
    @Override
    public void runOpMode() throws InterruptedException {
      
        // Initializes a double float variable
        double SLOW_DOWN_FACTOR = 0.5; //TODO Adjust to driver comfort
        telemetry.addData("Initializing FTC Wires (ftcwires.org) TeleOp adopted for Team:","TEAM NUMBER");
        telemetry.update();

        // This conditional block checks the type of drive class specified in "TuningOpModes.DRIVE_CLASS". 
        if (TuningOpModes.DRIVE_CLASS.equals(MecanumDrive.class)) {
            MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));

            waitForStart();

            while (opModeIsActive()) {
                telemetry.addData("Running FTC Wires (ftcwires.org) TeleOp Mode adopted for Team:","TEAM NUMBER");
                drive.setDrivePowers(new PoseVelocity2d(
                        new Vector2d(
                                -gamepad1.left_stick_y * SLOW_DOWN_FACTOR,
                                -gamepad1.left_stick_x * SLOW_DOWN_FACTOR
                        ),
                        -gamepad1.right_stick_x * SLOW_DOWN_FACTOR
                ));

                drive.updatePoseEstimate();

                //telemetry.addData("LF Encoder", drive.leftFront.getCurrentPosition());
                //telemetry.addData("LB Encoder", drive.leftBack.getCurrentPosition());
                //telemetry.addData("RF Encoder", drive.rightFront.getCurrentPosition());
                //telemetry.addData("RB Encoder", drive.rightBack.getCurrentPosition());

                telemetry.addLine("Current Pose");
                telemetry.addData("x", drive.pose.position.x);
                telemetry.addData("y", drive.pose.position.y);
                telemetry.addData("heading", Math.toDegrees(drive.pose.heading.log()));
                telemetry.update();
            }
        } else if (TuningOpModes.DRIVE_CLASS.equals(TankDrive.class)) {
            TankDrive drive = new TankDrive(hardwareMap, new Pose2d(0, 0, 0));

            waitForStart();

            while (opModeIsActive()) {
                drive.setDrivePowers(new PoseVelocity2d(
                        new Vector2d(
                                -gamepad1.left_stick_y * SLOW_DOWN_FACTOR,
                                0.0
                        ),
                        -gamepad1.right_stick_x * SLOW_DOWN_FACTOR
                ));

                drive.updatePoseEstimate();

                telemetry.addData("x", drive.pose.position.x);
                telemetry.addData("y", drive.pose.position.y);
                telemetry.addData("heading", drive.pose.heading);
                telemetry.update();
            }
        } else {
            throw new AssertionError();
        }
    }
}
