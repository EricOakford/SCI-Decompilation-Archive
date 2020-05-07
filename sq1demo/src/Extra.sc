;;; Sierra Script 1.0 - (do not remove this comment)
(script# EXTRA)
(include game.sh)
(use Motion)
(use Actor)

(enum
	waitAsec
	startAction
	hesitate
	continueAction
	endAction
)

(class Extra of Prop
	(properties
		cycleSpeed 1
		cycleType 0
		hesitation 0
		pauseCel 0
		minPause 10
		maxPause 30
		minCycles 8
		maxCycles 20
		counter 0
		state -1
		cycles 0
	)
	
	(procedure (InitialCel)
		(switch pauseCel
			(ExtraLastCel (Random 0 (self lastCel:)))
			(ExtraRandomCel (self lastCel:))
			((== cycleType ExtraForward) pauseCel)
		)
	)
	
	
	(method (init)
		(= cel (InitialCel))
		(self changeState: waitAsec)
		(super init:)
	)
	
	(method (doit)
		(if
			(and
				(== cycleType (InitialCel))
				(== cel pauseCel)
				(!= pauseCel 0)
			)
			(= cycles (+ hesitation 1))
		)
		(if (and cycles (not (-- cycles))) (self cue:))
		(super doit:)
	)
	
	(method (cue)
		(if (& signal (| notUpd stopUpdOn))
		else
			(self changeState: (+ state 1))
		)
	)
	
	(method (stopExtra)
		(self setCel: (InitialCel) stopUpd:)
	)
	
	(method (startExtra)
		(self changeState: startAction)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(waitAsec
				(if (<= counter 0)
					(if (!= cycleType ExtraForward)
						(= counter (- (Random minCycles maxCycles) 1))
					)
					(if (not (= cycles (Random minPause maxPause)))
						(self cue:)
					)
				else
					(-- counter)
					(self cue:)
				)
			)
			(startAction
				(cond 
					((== cycleType ExtraForward)
						(self setCycle: Forward)
						(= cycles (Random minCycles maxCycles))
					)
					((and (== cycleType ExtraEndAndBeginLoop)
							(== pauseCel ExtraRandomCel))
							(self setCycle: BegLoop self)
					)
					(else (self setCycle: EndLoop self))
				)
			)
			(hesitate
				(if (and hesitation (== cycleType ExtraEndAndBeginLoop))
					(= cycles hesitation)
				else
					(self cue:)
				)
			)
			(continueAction
				(if (== cycleType ExtraEndAndBeginLoop)
					(if (!= pauseCel ExtraRandomCel)
						(self setCycle: BegLoop self)
					else
						(self setCycle: EndLoop self)
					)
				else
					(self cue:)
				)
			)
			(4
				(self setCel: (InitialCel))
				(self changeState: waitAsec)
			)
		)
	)
)
