;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64908)
(include game.sh)
(use Main)
(use Actor)

(public
	SpeedTest 0
)

(procedure (SpeedTest &tmp newActor temp1 temp2 temp3 temp4)
	((= newActor (Actor new:))
		view: -556
		loop: 0
		cel: 0
		init: gRoomCast
		posn: 200 240
	)
	(= temp2 0)
	(= temp3 -1)
	(UpdatePlane gTopPlane)
	(FrameOut)
	(= temp1 (+ (GetTime) 120))
	(while (< (GetTime) temp1)
		(= temp3 (* temp3 -1))
		(newActor x: (+ (newActor x?) temp3))
		(UpdateScreenItem newActor)
		(FrameOut)
		(++ temp2)
	)
	(newActor dispose:)
	(return temp2)
)

(instance fred of Actor
	(properties
		view -556
	)
	
	(method (doit)
		(++ x)
	)
)
