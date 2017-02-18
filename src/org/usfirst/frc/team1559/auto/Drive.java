package org.usfirst.frc.team1559.auto;

import org.usfirst.frc.team1559.lib.Ramp;
import org.usfirst.frc.team1559.robot.DriveTrain;

public class Drive extends AutoCommand {

	double distance, speed;
	double startDist;
	Ramp ramp;

	public Drive(double distance, double speed) {
		this.distance = distance;
		this.startDist = DriveTrain.getInstance().getAvgEncoderPos();
		ramp = new Ramp(3,2);
	}

	@Override
	public void init() {
		this.startDist = DriveTrain.getInstance().getAvgEncoderPos();
	}

	@Override
	public void update() {
		DriveTrain.getInstance().set(DriveTrain.FL, ramp.rampMotorValueAuto(startDist, distance, -speed));
		DriveTrain.getInstance().set(DriveTrain.FR, ramp.rampMotorValueAuto(startDist, distance, speed));
		DriveTrain.getInstance().set(DriveTrain.RL, ramp.rampMotorValueAuto(startDist, distance, -speed));
		DriveTrain.getInstance().set(DriveTrain.RR, ramp.rampMotorValueAuto(startDist, distance, speed));
	}

	@Override
	public void done() {
		DriveTrain.getInstance().set(DriveTrain.FL, 0);
		DriveTrain.getInstance().set(DriveTrain.FR, 0);
		DriveTrain.getInstance().set(DriveTrain.RL, 0);
		DriveTrain.getInstance().set(DriveTrain.RR, 0);
		//DriveTrain.getInstance().resetEncoders();
	}

	@Override
	public boolean isFinished() {
		System.out.println("" + (distance + startDist + " : " + DriveTrain.getInstance().getAvgEncoderPos()));
		return DriveTrain.getInstance().getAvgEncoderPos() >= ((distance * 0.95) + startDist);
	}
}
