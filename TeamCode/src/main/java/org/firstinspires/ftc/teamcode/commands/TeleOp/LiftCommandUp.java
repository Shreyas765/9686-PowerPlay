package org.firstinspires.ftc.teamcode.commands.TeleOp;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.teleop.LiftSubsystem;

public class LiftCommandUp extends CommandBase {
    private LiftSubsystem liftSubsystem;

    public LiftCommandUp(LiftSubsystem cLiftSubsystem)  {
        liftSubsystem = cLiftSubsystem;
        addRequirements(cLiftSubsystem);
    }

    @Override
    public void execute() {
        liftSubsystem.motorUp();
    }


}
