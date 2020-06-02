;;; Sierra Script 1.0 - (do not remove this comment)
(script# 929)
(include game.sh)
(use Main)
(use Timer)
(use Motion)
(use System)


(class Sync of Object
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
			(= prevCue syncCue)
			(DoSync 1 self)
		)
	)
	
	(method (syncStop)
		(= prevCue -1)
		(DoSync 2)
	)
)

(class ScriptSync of Object
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
		(Timer setTicks: (DoAudio 1 param1) self)
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
		(if (IsObject theSync) (theSync syncStop: -propDict-))
		((= theSync (Sync new:)) syncStart: param2)
	)
	
	(method (doit &tmp clientLastCel theSyncPrevCue theSyncSyncTime_2 theSyncSyncTime temp4)
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
					(!= (client cel?) (theSync prevCue?))
				)
				(= theSyncPrevCue (theSync prevCue?))
				(= clientLastCel (client lastCel:))
				(if
					(or
						(< theSyncPrevCue 0)
						(> theSyncPrevCue clientLastCel)
					)
					(if (<= clientLastCel 1)
						(= theSyncPrevCue clientLastCel)
					else
						(repeat
							(if
								(!=
									(client cel?)
									(= theSyncPrevCue (Random 1 clientLastCel))
								)
								(break)
							)
						)
					)
				)
				(client cel: theSyncPrevCue)
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
