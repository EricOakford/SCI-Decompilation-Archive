;;; Sierra Script 1.0 - (do not remove this comment)
(script# 244)
(include sci.sh)
(use Main)
(use Motion)
(use Actor)
(use System)

(public
	initCyclers 0
)

(instance initCyclers of Code
	(properties)
	
	(method (doit)
		(Pong setCycle: multiCycler init:)
		(RedStreak init:)
		(GreenWobble init:)
		(ColorMove init:)
		(BlueWobble init:)
		(GoldRush init:)
		(RedSpot init:)
		(GreenSpot init:)
		(YellowSpot init:)
		(BlueSpot init:)
		(MedChart init:)
	)
)

(instance multiCycler of Cycle
	(properties)
	
	(method (doit &tmp temp0)
		(if (self nextCel:)
			(= temp0 0)
			(while (< temp0 (cast size?))
				(if ((cast at: temp0) isKindOf: MultiProp)
					((cast at: temp0) nextCel:)
				)
				(++ temp0)
			)
		)
	)
	
	(method (nextCel)
		(return
			(if
			(< (Abs (- gameTime cycleCnt)) (client cycleSpeed?))
				0
			else
				(= cycleCnt gameTime)
				1
			)
		)
	)
)

(class MultiProp of Prop
	(properties
		x 0
		y 0
		z 0
		heading 0
		noun 0
		modNum -1
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		sightAngle 26505
		actions 0
		onMeCheck $6789
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		yStep 2
		view -1
		loop 0
		cel 0
		priority 0
		underBits 0
		signal $0000
		lsTop 0
		lsLeft 0
		lsBottom 0
		lsRight 0
		brTop 0
		brLeft 0
		brBottom 0
		brRight 0
		scaleSignal $0000
		scaleX 128
		scaleY 128
		maxScale 128
		cycleSpeed 6
		script 0
		cycler 0
		timer 0
		detailLevel 0
		scaler 0
	)
	
	(method (checkDetail param1)
		(cond 
			((not detailLevel))
			(
				(<
					(if argc param1 else (theGame detailLevel:))
					detailLevel
				)
				(self stopUpd:)
			)
			(else (self startUpd:))
		)
	)
	
	(method (nextCel)
		(if (> (++ cel) (self lastCel:)) (= cel 0))
	)
)

(instance Pong of MultiProp
	(properties
		x 96
		y 131
		z 100
		noun 16
		view 249
		loop 10
		cel 10
		priority 14
		signal $4010
		detailLevel 2
	)
)

(instance RedStreak of MultiProp
	(properties
		x 240
		y 52
		noun 17
		view 249
		cel 5
		signal $4000
		detailLevel 2
	)
)

(instance ColorMove of MultiProp
	(properties
		x 94
		y 90
		noun 21
		view 249
		loop 8
		cel 2
		signal $4000
		detailLevel 2
	)
)

(instance GoldRush of MultiProp
	(properties
		x 99
		y 79
		noun 21
		view 249
		loop 9
		cel 2
		signal $4000
		detailLevel 2
	)
)

(instance GreenWobble of MultiProp
	(properties
		x 126
		y 99
		noun 21
		view 249
		loop 2
		signal $4000
		detailLevel 2
	)
)

(instance BlueWobble of MultiProp
	(properties
		x 113
		y 102
		noun 21
		view 249
		loop 3
		cel 2
		signal $4000
		detailLevel 2
	)
)

(instance RedSpot of MultiProp
	(properties
		x 142
		y 100
		noun 21
		view 249
		loop 4
		cel 13
		signal $4000
		detailLevel 2
	)
)

(instance GreenSpot of MultiProp
	(properties
		x 145
		y 100
		noun 21
		view 249
		loop 6
		cel 4
		signal $4000
		detailLevel 2
	)
)

(instance YellowSpot of MultiProp
	(properties
		x 143
		y 102
		noun 21
		view 249
		loop 7
		cel 6
		signal $4000
		detailLevel 2
	)
)

(instance BlueSpot of MultiProp
	(properties
		x 146
		y 102
		noun 21
		view 249
		loop 5
		cel 9
		signal $4000
		detailLevel 2
	)
)

(instance MedChart of MultiProp
	(properties
		x 148
		y 82
		noun 21
		view 249
		loop 1
		cel 8
		signal $4000
		detailLevel 2
	)
)
