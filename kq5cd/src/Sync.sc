;;; Sierra Script 1.0 - (do not remove this comment)
(script# 929)
(include sci.sh)
(use Main)
(use Timer)
(use Motion)
(use System)


(class Sync of Obj
	(properties
		syncTime -1
		syncCue $ffff
		prevCue $ffff
		syncNum -1
	)
	
	(method (syncStart param1)
		(DoSync 0 self param1)
		(if (!= syncCue -1) (= prevCue syncCue) (= syncTime 0))
	)
	
	(method (syncCheck)
		(if
			(and
				(!= syncCue -1)
				(or (u<= syncTime syncBias) (<= syncTime (DoAudio 6)))
			)
			(if (== (& $fff0 syncCue) 0)
				(= prevCue (| (& prevCue $fff0) syncCue))
			else
				(= prevCue syncCue)
			)
			(DoSync 1 self)
		)
	)
	
	(method (syncStop)
		(= prevCue -1)
		(DoSync 2)
	)
)

(class ScriptSync of Obj
	(properties
		prevSignal -1
		playing 0
	)
	
	(method (init param1)
		(if theSync (self cue:))
		((= theSync (Sync new:)) init: syncStart: param1)
		(if (!= (theSync prevCue?) -1)
			(= playing 1)
			(regions add: self)
		)
		(Timer set60ths: self (DoAudio 1 param1))
	)
	
	(method (doit &tmp theSyncPrevCue)
		(if (!= (theSync prevCue?) -1)
			(repeat
				(if (== (theSync prevCue?) -1) (break))
				(= theSyncPrevCue (theSync prevCue?))
				(theSync syncCheck:)
				(if (== theSyncPrevCue (theSync prevCue?)) (break))
			)
			(= prevSignal (theSync prevCue?))
		)
	)
	
	(method (cue)
		(Animate (cast elements?) 0)
		(= playing 0)
		(= prevSignal 32767)
		(regions delete: self)
		(if theSync (theSync syncStop: dispose:) (= theSync 0))
	)
	
	(method (handleEvent)
	)
)

(class MouthSync of Cycle
	(properties
		client 0
		caller 0
		cycleDir 1
		cycleCnt 0
		completed 0
	)
	
	(method (init param1 param2 &tmp temp0)
		(super init: param1)
		(if (IsObject theSync) (theSync syncStop: species))
		((= theSync (Sync new:)) syncStart: param2)
	)
	
	(method (doit &tmp clientLastCel theClientLastCel theSyncSyncTime_2 theSyncSyncTime temp4)
		(super doit:)
		(if (!= (theSync prevCue?) -1)
			(= theSyncSyncTime (theSync syncTime?))
			(= temp4 0)
			(repeat
				(= theSyncSyncTime_2 (theSync syncTime?))
				(theSync syncCheck:)
				(if (== theSyncSyncTime_2 (theSync syncTime?)) (break))
			)
			(if
				(and
					(!= theSyncSyncTime (theSync syncTime?))
					(!=
						(client cel?)
						(= theClientLastCel (& $000f (theSync prevCue?)))
					)
				)
				(= clientLastCel (client lastCel:))
				(if
					(or
						(< theClientLastCel 0)
						(> theClientLastCel clientLastCel)
					)
					(if (<= clientLastCel 1)
						(= theClientLastCel clientLastCel)
					else
						(repeat
							(if
								(!=
									(client cel?)
									(= theClientLastCel (Random 1 clientLastCel))
								)
								(break)
							)
						)
					)
				)
				(client cel: theClientLastCel)
			)
		else
			(= completed 1)
			(self cycleDone:)
		)
	)
	
	(method (dispose)
		(super dispose:)
		(if theSync (theSync dispose:) (= theSync 0))
	)
	
	(method (cue)
		(if theSync
			(theSync syncStop: dispose:)
			(= theSync 0)
			(if caller (caller cue:) (= caller 0))
		)
	)
)
