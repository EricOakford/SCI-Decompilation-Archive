;;; Sierra Script 1.0 - (do not remove this comment)
(script# SYNC)
(include game.sh)
(use Main)
(use Timer)
(use Motion)
(use System)


(class Sync of Object
	(properties
		syncTime -1
		syncCue -1
		prevCue -1
		syncNum -1
	)
	
	(method (syncStart modNum)
		(DoSync StartSync self modNum)
		(if (!= syncCue -1)
			(= prevCue syncCue)
			(= syncTime 0)
		)
	)
	
	(method (syncCheck)
		(if
			(and
				(!= syncCue -1)
				(or (u<= syncTime syncBias) (<= syncTime (DoAudio Loc)))
			)
			(= prevCue syncCue)
			(DoSync NextSync self)
		)
	)
	
	(method (syncStop)
		(= prevCue -1)
		(DoSync StopSync)
	)
)

(class ScriptSync of Object
	(properties
		prevSignal -1
		playing 0
	)
	
	(method (init modNum)
		(if theSync (self cue:))
		((= theSync (Sync new:))
			init: syncStart: modNum
		)
		(if (!= (theSync prevCue?) -1)
			(= playing TRUE)
			(regions add: self)
		)
		(Timer set60ths: self (DoAudio WPlay modNum))
	)
	
	(method (doit &tmp oldSignal)
		(if (!= (theSync prevCue?) -1)
			(repeat
				(if (== (theSync prevCue?) -1) (break))
				(= oldSignal (theSync prevCue?))
				(theSync syncCheck:)
				(if (== oldSignal (theSync prevCue?)) (break))
			)
			(= prevSignal (theSync prevCue?))
		)
	)
	
	(method (cue)
		(Animate (cast elements?) FALSE)
		(= playing FALSE)
		(= prevSignal 32767)
		(regions delete: self)
		(if theSync
			(theSync syncStop: dispose:)
			(= theSync 0)
		)
	)
	
	(method (handleEvent)
	)
)

(class MouthSync of Cycle
	
	(method (init theObj modNum &tmp numCels)
		(super init: theObj)
		(if (IsObject theSync)
			(theSync syncStop: dispose:)
		)
		((= theSync (Sync new:))
			syncStart: modNum
		)
	)
	
	(method (doit &tmp numCels newCel oldSignal theTime cntr)
		(super doit:)
		(if (!= (theSync prevCue?) -1)
			(= theTime (theSync syncTime?))
			(= cntr 0)
			(repeat
				(= oldSignal (theSync syncTime?))
				(theSync syncCheck:)
				(if (== oldSignal (theSync syncTime?)) (break))
			)
			(if
				(and
					(!= theTime (theSync syncTime?))
					(!= (client cel?) (theSync prevCue?))
				)
				(= newCel (theSync prevCue?))
				(= numCels (client lastCel:))
				(if
					(or
						(< newCel 0)
						(> newCel numCels)
					)
					(if (<= numCels 1)
						(= newCel numCels)
					else
						(repeat
							(if
								(!=
									(client cel?)
									(= newCel (Random 1 numCels))
								)
								(break)
							)
						)
					)
				)
				(client cel: newCel)
			)
		else
			(= completed TRUE)
			(self cycleDone:)
		)
	)
	
	(method (dispose)
		(super dispose:)
		(if theSync
			(theSync dispose:)
			(= theSync 0)
		)
	)
	
	(method (cue)
		(if theSync
			(theSync
				syncStop:
				dispose:
			)
			(= theSync 0)
			(if caller
				(caller cue:)
				(= caller 0)
			)
		)
	)
)
