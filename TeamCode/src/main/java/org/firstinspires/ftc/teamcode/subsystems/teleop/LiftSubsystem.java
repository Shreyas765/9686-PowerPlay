package org.firstinspires.ftc.teamcode.subsystems.teleop;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;

public class LiftSubsystem extends SubsystemBase {
    private Motor liftMotor;
    private int level;

    public LiftSubsystem(Motor mLiftMotor) {
        liftMotor = mLiftMotor;
        level = 0;
    }

    //Note: make sure that you adjust the times accordingly

    public void motorUp() {
        liftMotor.set(0.5);
    }

    public void motorDown() {
        liftMotor.set(-0.5);
    }


    //TODO: Viggy

}
