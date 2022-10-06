package org.firstinspires.ftc.teamcode.Autons.opModes;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.commands.rr.TrajectoryFollowerCommand;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.rr.MecanumDriveSubsystem;

@Autonomous(name = "ParkingAuton")
public class ParkingAuton extends CommandOpMode {

    private MecanumDriveSubsystem mecanumDriveS;

    //private ElaspsedTime time;  //TODO: Add delay for comp.

    private Pose2d startPose = new Pose2d(0.0,0.0,0);

    @Override
    public void initialize() {
       mecanumDriveS = new MecanumDriveSubsystem(new SampleMecanumDrive(hardwareMap), false);

        Trajectory traj0 = mecanumDriveS.trajectoryBuilder(startPose)
                .forward(32)
                .build();

        TrajectoryFollowerCommand autonomous = new TrajectoryFollowerCommand(mecanumDriveS, traj0);

        if(isStopRequested()) {
            return;
        }
        schedule();
    }
}
