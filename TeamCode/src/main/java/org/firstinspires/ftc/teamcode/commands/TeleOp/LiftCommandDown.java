package org.firstinspires.ftc.teamcode.commands.TeleOp;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.teleop.LiftSubsystem;

public class LiftCommandDown extends CommandBase {
    private LiftSubsystem liftSubsystem;

    public LiftCommandDown(LiftSubsystem cLiftSubsystem)  {
        liftSubsystem = cLiftSubsystem;
        addRequirements(cLiftSubsystem);
    }

    @Override
    public void initialize() {
        liftSubsystem.motorDown();
    }

    @Override
    public void end(boolean interrupted) {
        liftSubsystem.stop();
    }
}
