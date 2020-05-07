;;; Sierra Script 1.0 - (do not remove this comment)
(script# 988)
(include game.sh)
(use Motion)
(use Actor)


(class Extra of Prop
	(properties
		y 0
		x 0
		z 0
		heading 0
		yStep 2
		view 0
		loop 0
		cel 0
		priority 0
		underBits 0
		signal $0000
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		lsTop 0
		lsLeft 0
		lsBottom 0
		lsRight 0
		brTop 0
		brLeft 0
		brBottom 0
		brRight 0
		cycleSpeed 1
		script 0
		cycler 0
		timer 0
		cycleType 0
		hesitation 0
		pauseCel 0
		minPause 10
		maxPause 30
		minCycles 8
		maxCycles 20
		counter 0
		state $ffff
		cycles 0
	)
	
	(procedure (localproc_0148)
		(switch pauseCel
			(-1 (Random 0 (self lastCel:)))
			(-2 (self lastCel:))
			((== cycleType 0) pauseCel)
		)
	)
	
	
	(method (init)
		(= cel (localproc_0148))
		(self changeState: 0)
		(super init:)
	)
	
	(method (doit)
		(if
			(and
				(== cycleType 1)
				(== cel pauseCel)
				(!= pauseCel 0)
			)
			(== cycles (+ hesitation 1))
		)
		(if (and cycles (not (-- cycles))) (self cue:))
		(super doit:)
	)
	
	(method (cue)
		(if (& signal $0005)
		else
			(self changeState: (+ state 1))
		)
	)
	
	(method (stopExtra)
		(self setCel: (localproc_0148) stopUpd:)
	)
	
	(method (startExtra)
		(self changeState: 1)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== counter 0)
					(= cycles (Random minPause maxPause))
					(if (!= cycleType 0)
						(= counter (- (Random minCycles maxCycles) 1))
					)
				else
					(-- counter)
					(self cue:)
				)
			)
			(1
				(if (== cycleType 0)
					(self setCycle: Forward)
					(= cycles (Random minCycles maxCycles))
				else
					(self setCycle: EndLoop self)
				)
			)
			(2
				(if (== cycleType 2)
					(= cycles hesitation)
				else
					(self cue:)
				)
			)
			(3
				(if (== cycleType 2)
					(self setCycle: BegLoop self)
				else
					(self cue:)
				)
			)
			(4
				(self setCel: (localproc_0148))
				(self changeState: 0)
			)
		)
	)
)
