package org.firstinspires.ftc.teamcode.TeleOp;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.StartEndCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.RevIMU;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.commands.TeleOp.DriveCommand;
import org.firstinspires.ftc.teamcode.commands.TeleOp.LiftCommandDown;
import org.firstinspires.ftc.teamcode.commands.TeleOp.LiftCommandUp;
import org.firstinspires.ftc.teamcode.subsystems.teleop.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.teleop.LiftSubsystem;

@TeleOp(name = "Main")
public class MainTeleOp extends CommandOpMode {
    // MOTORS
    private Motor fL, fR, bL, bR;
    private Motor liftMotor;

    // SUBSYSTEMS
    private DriveSubsystem driveSubsystem;
    private LiftSubsystem liftSubsystem;

    // COMMANDS
    private DriveCommand driveCommand;
    private LiftCommandUp liftCommandUp;
    private LiftCommandDown liftCommandDown;

    // EXTRAS
    private GamepadEx gPad1;
    private RevIMU revIMU;

    // CONSTANTS
    private final double DRIVE_MULT = 1.0;

    @Override
    public void initialize() {
        // Initializing Motors
        fL = new Motor(hardwareMap, "frontLeft");
        fR = new Motor(hardwareMap, "frontRight");
        bL = new Motor(hardwareMap, "backLeft");
        bR = new Motor(hardwareMap, "backRight");

        liftMotor = new Motor(hardwareMap, "liftMotor");

        // Initializing Extras
        gPad1 = new GamepadEx(gamepad1);

        // Initializing Subsystems
        driveSubsystem = new DriveSubsystem(fL, fR, bL, bR, revIMU);
        liftSubsystem = new LiftSubsystem(liftMotor);

        // Initializing Commands
        driveCommand = new DriveCommand(driveSubsystem, gPad1::getLeftX, gPad1::getLeftY, gPad1::getRightX, DRIVE_MULT);
        liftCommandUp = new LiftCommandUp(liftSubsystem);
        liftCommandDown = new LiftCommandDown(liftSubsystem);


        // Motor Settings
        fL.motor.setDirection(DcMotor.Direction.REVERSE);
        fR.motor.setDirection(DcMotor.Direction.FORWARD);
        bL.motor.setDirection(DcMotor.Direction.REVERSE);
        bR.motor.setDirection(DcMotor.Direction.FORWARD);

        fL.motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fR.motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bL.motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bR.motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        //GamePad controls:
        gPad1.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER).whenHeld(
                (new DriveCommand(driveSubsystem,gPad1::getLeftX, gPad1::getLeftY, gPad1::getRightX, DRIVE_MULT)));

        gPad1.getGamepadButton(GamepadKeys.Button.DPAD_UP).whenHeld(liftCommandUp);
        gPad1.getGamepadButton(GamepadKeys.Button.DPAD_DOWN).whenHeld(liftCommandDown);




        // Sets default command for drivetrain
        register(driveSubsystem);
        driveSubsystem.setDefaultCommand(driveCommand);


    }
}
