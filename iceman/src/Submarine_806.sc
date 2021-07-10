;;; Sierra Script 1.0 - (do not remove this comment)
(script# 806)
(include game.sh)
(use Main)
(use TimedControler)
(use EgoDead)
(use Eval)
(use System)

(public
	proc806_0 0
	accelerator 1
)

(procedure (proc806_0)
)

(class Submarine of Object
	(properties
		_depth 35
		_absHeading 0
		pitch 0
		roll 0
		rudder 0
		_vSpeed 0
		hSpeed 0
		bowBallast 60
		emergencyBallast 480
		throttle 0
		rpms 0
		longitude 0
		latitude 0
		misslesLeft 4
		torpedosLeft 10
		decoysLeft 4
		flags $0008
		waterTemp 12
		lastH 0
		lastM 0
		floor 10000
		ceiling 0
		wayPoint1X 0
		wayPoint1Y 0
		wayPoint2X 0
		wayPoint2Y 0
		wayPoint3X 0
		wayPoint3Y 0
		wayPoint4X 0
		wayPoint4Y 0
		wayPoint5X 0
		wayPoint5Y 0
		wheelX 158
		diveLeverY 144
		throttleY 179
	)
	
	(method (init)
		(= lastH gLastH)
		(= lastM gLastM)
		(headingController init: self 392)
		(accelerator init: self 358)
	)
	
	(method (doit &tmp temp0)
		(if _vSpeed (self depth: (+ _vSpeed _depth)))
		(headingController doit:)
		(accelerator doit:)
	)
	
	(method (vSpeed)
		(= _vSpeed
			(-
				(/
					(+
						(*
							2
							(- (sign (self pitch?)))
							(CosMult (self pitch?) (self hSpeed?))
						)
						(/
							(+ (- 480 emergencyBallast) (* 4 (- 60 bowBallast)))
							4
						)
					)
					30
				)
			)
		)
	)
	
	(method (dive param1 &tmp temp0)
		(if 1 (= temp0 (* param1 20)) (self bowBallast: temp0))
		(self pitch: (* (- param1 3) 10) vSpeed:)
	)
	
	(method (turn param1)
		(self
			rudder: (/ param1 (if howFast 9 else 4))
			roll: (/ (* param1 2) 3)
		)
	)
	
	(method (accel param1 param2)
		(if argc
			(accelerator incPerTime: param1)
			(if (>= argc 2) (accelerator cycles: param2))
		)
	)
	
	(method (depth the_depth)
		(if (not argc) (return _depth))
		(cond 
			((< the_depth 35) (return (= _depth 35)))
			((> the_depth 5000) (EgoDead 926 2 0 806 0))
			((> the_depth floor)
				(if hSpeed
					(EgoDead 926 3 0 806 1)
				else
					(return (= _depth floor))
				)
			)
			((< the_depth ceiling) (EgoDead 926 4 0 806 2))
		)
		(return (= _depth the_depth))
	)
	
	(method (absHeading param1)
		(return
			(if argc
				(= _absHeading (umod param1 360))
			else
				_absHeading
			)
		)
	)
)

(instance accelerator of TimedControler
	(properties
		incPerTime 1
	)
	
	(method (drive &tmp temp0 submarineThrottle)
		(= incPerTime
			(sign
				(= temp0
					(-
						(if
							(or
								(==
									(Abs (= submarineThrottle (Submarine throttle?)))
									1
								)
								(not submarineThrottle)
							)
							(* submarineThrottle 5)
						else
							(* (- submarineThrottle 1) 20)
						)
						(Submarine hSpeed?)
					)
				)
			)
		)
		(= temp0 (Abs temp0))
		(= cycles (+ (* howFast (/ (- 60 temp0) 8)) 1))
		(super drive:)
		(Submarine
			rpms: (* (Sqrt (* (Submarine hSpeed?) 300)) 10)
		)
		(theObj vSpeed:)
	)
)

(instance headingController of TimedControler
	(properties
		cyclCnt 5
		incPerTime 1
	)
	
	(method (drive &tmp temp0)
		(= temp0
			(/ (* (Eval theObj 356) (Eval theObj 358)) 5)
		)
		(= incPerTime (sign temp0))
		(cond 
			((not (= temp0 (Abs temp0))))
			((> temp0 20)
				(= incPerTime (* incPerTime 6))
				(= cycles (+ (* howFast 2) 1))
			)
			((> temp0 10)
				(= incPerTime (* incPerTime (if howFast 3 else 6)))
				(= cycles (+ (* howFast 4) 1))
			)
			(else
				(= incPerTime (* incPerTime 2))
				(= cycles (+ (* howFast 8) 1))
			)
		)
		(super drive:)
	)
)
