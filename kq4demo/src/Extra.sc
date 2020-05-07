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
		startUpd 1
		script 0
		cycleSpeed 0
		timer 0
		pauseCel 0
		minPause 0
		maxPause 0
		minCycles 10
		maxCycles 30
		stopExtra 8
		startExtra 20
		nearestDist 0
		state $ffff
		cycles 0
	)
	
	(procedure (localproc_0148)
		(switch maxPause
			(-1 (Random 0 (self cycler?)))
			(-2 (self cycler?))
			((== pauseCel 0) maxPause)
		)
	)
	
	
	(method (init)
		(= cel (localproc_0148))
		(self changeState: 0)
		(super init:)
	)
	
	(method (doit)
		(if
		(and (== pauseCel 1) (== cel maxPause) (!= maxPause 0))
			(== cycles (+ minPause 1))
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
	
	(method (higher)
		(self ignoreActors: (localproc_0148) posn:)
	)
	
	(method (lower)
		(self changeState: 1)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== nearestDist 0)
					(= cycles (Random minCycles maxCycles))
					(if (!= pauseCel 0)
						(= nearestDist (- (Random stopExtra startExtra) 1))
					)
				else
					(-- nearestDist)
					(self cue:)
				)
			)
			(1
				(if (== pauseCel 0)
					(self setCycle: Forward)
					(= cycles (Random stopExtra startExtra))
				else
					(self setCycle: CycleTo self)
				)
			)
			(2
				(if (== pauseCel 2)
					(= cycles minPause)
				else
					(self cue:)
				)
			)
			(3
				(if (== pauseCel 2)
					(self setCycle: EndLoop self)
				else
					(self cue:)
				)
			)
			(4
				(self ignoreActors: (localproc_0148))
				(self changeState: 0)
			)
		)
	)
)
