package org.firstinspires.ftc.teamcode.Autons.paths;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.drive.Drive;
import com.acmerobotics.roadrunner.drive.MecanumDrive;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.commands.rr.TrajectoryFollowerCommand;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.rr.MecanumDriveSubsystem;

@Config
public class ParkingAuton extends SequentialCommandGroup {

    private Pose2d startPose = new Pose2d(0.0,0.0,0);
    public ParkingAuton(MecanumDriveSubsystem drive) {
        drive.setPoseEstimate(startPose);

        Trajectory traj0 = drive.trajectoryBuilder(startPose)
                .forward(32)
                .build();

        addCommands(new TrajectoryFollowerCommand(drive, traj0));
    }
}
