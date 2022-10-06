package org.firstinspires.ftc.teamcode.commands.TeleOp;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.teleop.TestMotorSubsystem;

public class TestMotorCommand extends CommandBase {
    TestMotorSubsystem sTestMotor;
    double dPower;

    public TestMotorCommand(TestMotorSubsystem testMotorSubsystem, double power) {
        sTestMotor = testMotorSubsystem;
        dPower = power;
        addRequirements(testMotorSubsystem);
    }

    @Override
    public void initialize() {
        sTestMotor.run(dPower);
    }

    @Override
    public void end(boolean interrupted) {
        sTestMotor.stop();
    }
}
