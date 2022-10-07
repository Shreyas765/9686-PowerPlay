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
import org.firstinspires.ftc.teamcode.commands.TeleOp.TestMotorCommand;
import org.firstinspires.ftc.teamcode.subsystems.teleop.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.teleop.TestMotorSubsystem;

@TeleOp(name = "Main")
public class MainTeleOp extends CommandOpMode {
    // MOTORS
    private Motor fL, fR, bL, bR;
    private Motor testMotor;

    // SUBSYSTEMS
    private DriveSubsystem driveSubsystem;
    private TestMotorSubsystem testMotorSubsystem;

    // COMMANDS
    private DriveCommand driveCommand;
    private TestMotorCommand testMotorCommand;

    // EXTRAS
    private GamepadEx gPad1;
    private RevIMU revIMU;

    // CONSTANTS
    private final double DRIVE_MULT = 1.0;
    private final double MOTOR_SPEED = 0.75;

    @Override
    public void initialize() {
        // Initializing Motors
        fL = new Motor(hardwareMap, "frontLeft");
        fR = new Motor(hardwareMap, "frontRight");
        bL = new Motor(hardwareMap, "backLeft");
        bR = new Motor(hardwareMap, "backRight");

        testMotor = new Motor(hardwareMap, "testMotor");

        // Initializing Extras
        gPad1 = new GamepadEx(gamepad1);

        // Initializing Commands and Subsystems
        driveSubsystem = new DriveSubsystem(fL, fR, bL, bR, revIMU);
        driveCommand = new DriveCommand(driveSubsystem, gPad1::getLeftX, gPad1::getLeftY, gPad1::getRightX, DRIVE_MULT);
        testMotorSubsystem = new TestMotorSubsystem(testMotor);
        testMotorCommand = new TestMotorCommand(testMotorSubsystem, MOTOR_SPEED);


        // Motor Settings
        fL.motor.setDirection(DcMotor.Direction.REVERSE);
        fR.motor.setDirection(DcMotor.Direction.FORWARD);
        bL.motor.setDirection(DcMotor.Direction.REVERSE);
        bR.motor.setDirection(DcMotor.Direction.FORWARD);

        fL.motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fR.motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bL.motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bR.motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Sets default command for drivetrain
        register(driveSubsystem);
        driveSubsystem.setDefaultCommand(driveCommand);


        //Game-pad
//        gPad1.getGamepadButton(GamepadKeys.Button.X)
//                .whenHeld(new StartEndCommand(()-> testMotor.set(MOTORSPEED), () -> testMotor.stopMotor()));

        gPad1.getGamepadButton(GamepadKeys.Button.X)
                .whenHeld(testMotorCommand);

    }
}
