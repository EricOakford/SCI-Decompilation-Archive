;;; Sierra Script 1.0 - (do not remove this comment)
(script# VELOCITY_MOVER)
(include game.sh)
(use Main)
(use Motion)
(use System)


(class VelocityMover of MoveTo
	(properties
		vel 30
		grav1x 160
		grav1y 100
		oldGravx 160
		oldGravy 100
		targetScale 0
		isScale 0
		willLand 0
		grav1 10
	)
	
	(method (init actor xTo yTo toCall toLand &tmp DX DY cx cy)
		(if argc
			(= vel 10)
			(= willLand (= isScale (= caller 0)))
			(= client actor)
			(if (> argc 1)
				(= grav1x xTo)
				(= grav1y yTo)
				(= oldGravx xTo)
				(= oldGravy yTo)
				(if (> argc 3)
					(= caller toCall)
					(if (> argc 4) (= willLand toLand))
				)
			)
			(self moveDone:)
		else
			(= yLast (= xLast (= completed 0)))
			(= b-moveCnt (+ 1 (client moveSpeed?) gameTime))
			(if (= cy (client cycler?))
				(cy cycleCnt: b-moveCnt)
			)
			(InitBresen self)
		)
	)
	
	(method (moveDone &tmp temp0 temp1 temp2 temp3 temp4 temp5 temp6 toX toY [temp9 100])
		(if
			(and
				willLand
				(<= (Abs (- (client x?) grav1x)) 5)
				(<= (Abs (- (client y?) grav1y)) 5)
			)
			(= completed TRUE)
			(if caller
				(= doMotionCue TRUE)
				(return)
			else
				(self motionCue:)
				(return)
			)
		)
		(if
		(>= (= temp2 (+ (- 360 (client heading?)) 90)) 360)
			(= temp2 (- temp2 360))
		)
		(if
			(>=
				(= temp1
					(+
						(-
							360
							(GetAngle (client x?) (client y?) grav1x grav1y)
						)
						90
					)
				)
				360
			)
			(= temp1 (- temp1 360))
		)
		(self readjustWell: temp1 temp2)
		(= temp3 (* (CosMult temp2 vel) 100))
		(= temp3
			(+
				(client x?)
				(if (> (mod temp3 100) 44) 1 else 0)
				(/ temp3 100)
			)
		)
		(= temp4 (* (SinMult temp2 vel) 100))
		(= temp4
			(-
				(client y?)
				(+ (if (> (mod temp4 100) 44) 1 else 0) (/ temp4 100))
			)
		)
		(= temp5
			(+
				(if
					(>
						(mod (= temp5 (* (CosMult temp1 grav1) 100)) 100)
						44
					)
					1
				else
					0
				)
				(/ temp5 100)
			)
		)
		(= temp6
			(+
				(if
					(>
						(mod (= temp6 (* (SinMult temp1 grav1) 100)) 100)
						44
					)
					1
				else
					0
				)
				(/ temp6 100)
			)
		)
		(= toX (+ temp5 temp3))
		(= toY (- temp4 temp6))
		(= vel
			(Sqrt
				(+
					(* (- toX (client x?)) (- toX (client x?)))
					(* (- (client y?) toY) (- (client y?) toY))
				)
			)
		)
		(client
			setHeading: (GetAngle (client x?) (client y?) toX toY)
		)
		(self x: toX y: toY)
		(self init:)
	)
	
	(method (onTarget)
		(return
			(if willLand
				(if
					(and
						(<= (Abs (- (client x?) grav1x)) 5)
						(<= (Abs (- (client y?) grav1y)) 5)
					)
					(= completed TRUE)
					(if caller
						(= doMotionCue TRUE)
						(return TRUE)
					else
						(self motionCue:)
						(return TRUE)
					)
				else
					(if (< vel 0)
						(= vel (+ vel 1))
					else
						(= vel (- vel 1))
					)
					(return FALSE)
				)
			else
				(return FALSE)
			)
		)
	)
	
	(method (motionCue)
		(client posn: grav1x grav1y)
		(super motionCue:)
	)
	
	(method (readjustWell param1 param2)
		(if (OneOf param1 0 90 180 270)
			(= grav1x (- oldGravx (- (Random 1 20) 10)))
		else
			(= grav1x oldGravx)
		)
		(if (OneOf param2 0 90 180 270)
			(= grav1y (- oldGravy (- (Random 1 20) 10)))
		else
			(= grav1y oldGravy)
		)
	)
)
