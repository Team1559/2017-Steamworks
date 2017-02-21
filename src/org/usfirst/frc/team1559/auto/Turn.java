package org.usfirst.frc.team1559.auto;

import org.usfirst.frc.team1559.lib.BNO055;
import org.usfirst.frc.team1559.robot.DriveTrain;

public class Turn extends AutoCommand {

	double angle, speed;
	double startAngle;

	public Turn(double angle, double speed) {
		this.angle = angle;
		this.speed = speed;
	}

	@Override
	public void init() {
		this.startAngle = BNO055.getInstance(BNO055.opmode_t.OPERATION_MODE_IMUPLUS, BNO055.vector_type_t.VECTOR_EULER).getHeading();
	}

	@Override
	public void update() {
		double distFromTarget = angle - (BNO055.getInstance(BNO055.opmode_t.OPERATION_MODE_IMUPLUS, BNO055.vector_type_t.VECTOR_EULER).getHeading() - startAngle);
		double kP = 0.1;
		DriveTrain.getInstance().set(DriveTrain.FL, speed * kP * distFromTarget);
		DriveTrain.getInstance().set(DriveTrain.FR, speed * kP * distFromTarget);
		DriveTrain.getInstance().set(DriveTrain.RL, speed * kP * distFromTarget);
		DriveTrain.getInstance().set(DriveTrain.RR, speed * kP * distFromTarget);
	}

	@Override
	public void done() {
		DriveTrain.getInstance().set(DriveTrain.FL, 0);
		DriveTrain.getInstance().set(DriveTrain.FR, 0);
		DriveTrain.getInstance().set(DriveTrain.RL, 0);
		DriveTrain.getInstance().set(DriveTrain.RR, 0);
	}

	@Override
	public boolean isFinished() {
		if (angle > 0) {
			 return BNO055.getInstance(BNO055.opmode_t.OPERATION_MODE_IMUPLUS, BNO055.vector_type_t.VECTOR_EULER).getHeading() >= startAngle + angle;
		} else {
			 return BNO055.getInstance(BNO055.opmode_t.OPERATION_MODE_IMUPLUS, BNO055.vector_type_t.VECTOR_EULER).getHeading() <= startAngle + angle;
		}
	}
}
