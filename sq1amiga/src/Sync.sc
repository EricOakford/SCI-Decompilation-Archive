;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	SYNC.SC
(script# SYNC)
(include game.sh)
(use Main)
(use Timer)
(use Motion)
(use System)

;IFDEF DBG
;(local
;	[syncsMissed 10]
;	missedNum
;	syncCounter
;)
;ENDIF


(class Sync kindof Object

	(properties
		name "Sync"
		syncTime -1
		syncCue -1
		prevCue -1
		syncNum -1
	)

;;;	(methods
;;;		syncStart
;;;		syncCheck
;;;		syncStop
;;;	)
	
	(method (syncStart number)
		(DoSync StartSync self number)
		(if (!= syncCue -1)
			(= prevCue syncCue)
			(= syncTime 0)
		)
	)

	(method (syncCheck)
;		(if (and (!= syncCue -1) (u<= syncTime (DoAudio Loc)))
		(if (!= syncCue -1)
			(if (or (u<= syncTime syncBias)  (<= syncTime (DoAudio Loc)))
				(= prevCue syncCue)
				(DoSync NextSync self)  ;; get next syncTime and syncCue
			)
		)
	)

	(method (syncStop)
		(= prevCue -1)
		(DoSync StopSync)
;		IFDEF DBG
;		(Printf "Syncs missed: %d\n%x_ %x_ %x _%x _%x_ %x_ %x"
;			syncCounter
;			[syncsMissed 0]
;			[syncsMissed 1]
;			[syncsMissed 2]
;			[syncsMissed 3]
;			[syncsMissed 4]
;			[syncsMissed 5]
;			[syncsMissed 6]
;		)
;		ENDIF
	)
)


(class ScriptSync kindof Object

	(properties
		prevSignal -1
		playing 0
	)
;;;	(methods
;;;		cue
;;;		handleEvent
;;;	)
	
	(method (init number)
;		(super init:)
		(if theSync
			(self cue:)
		)
		((= theSync (Sync new:))
			init:,
			syncStart: number
		)
		(if (!= (theSync prevCue?) -1)
			(= playing 1)
			(regions add: self)
		)
		(Timer set60ths: self (DoAudio WPlay number))
	)

	(method (doit &tmp oldSignal)
		
;		(super doit:)
		(if (!= (theSync prevCue?) -1)
			(repeat
				(if (== (theSync prevCue?) -1)
					(break)
				)
				(= oldSignal (theSync prevCue?))
				(theSync syncCheck:)
				(if (== oldSignal (theSync prevCue?))
					(break)
				)
			)
			(= prevSignal (theSync prevCue?))
;(if cfs
;	(= newLoc (DoAudio Loc))
;	(if (!= newLoc oldLoc)
;		(= oldLoc newLoc)
;		(if displayx
;			(Display "" #p_restore: displayx)
;		)
;		(Format @string "%u %u" prevSignal newLoc)
;		(= displayx
;			(Display
;				@string
;				#p_width: 	80
;				#p_at: 		20 135
;				#p_mode: 	teJustLeft
;				#p_font: 	8
;				#p_color: 	vWHITE
;				#p_save:
;			)
;		)
;	)
;)
		)
	)

	(method (cue)
		(Animate (cast elements?) FALSE)
		(= playing 0)
		(= prevSignal 32767)   ;  big-big positive number
		(regions delete: self)
		(if theSync
			(theSync syncStop:, dispose:)
			(= theSync 0)
		)
	)
	
	(method (handleEvent)
		(return)
	)
)

(class MouthSync kindof Cycle

;;;	(methods
;;;		cue
;;;	)

	(method (init theObj no &tmp a)
;		IFDEF DBG
;		(= missedNum 0)
;		(for ((= a 0)) (< a 10) ((++ a))
;			(= [syncsMissed a] 0)
;		)
;		(= syncCounter 0)
;		ENDIF
		(super init: theObj)
		(if (IsObject theSync)
			(theSync 
			  	syncStop: 
				dispose:
			)
		)
		((= theSync (Sync new:))
			syncStart: no
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
				(if (== oldSignal (theSync syncTime?))
					(break)
				)
;				IFDEF DBG
;				(if (> (++ cntr) 1)
;					(if (< missedNum 10)
;						(= [syncsMissed missedNum] oldSignal)
;						(++ missedNum)
;					)
;				)
;				ENDIF
			)
;			IFDEF DBG
;			(if (> cntr 1)
;				(+= syncCounter (- cntr 1))
;			)
;			ENDIF
			(if (and
					(!= theTime (theSync syncTime?))
					(!= (client cel?) (theSync prevCue?)))
				(= newCel (theSync prevCue?))
				(= numCels (client lastCel?))
				(if (or (< newCel 0) (> newCel numCels))
					(if (<= numCels 1)
						(= newCel numCels)
					else
						(repeat
							(if (!= (client cel?) (= newCel (Random 1 numCels)))
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
;			(if (== 2 (Random 1 3))  ;; wait just a bit
;				(client
;					setCel: (Random 0 (- (NumCels client) 1)) ;; pick random cel
;				)
;			)
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
				syncStop:,
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