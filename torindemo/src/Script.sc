;;; Sierra Script 1.0 - (do not remove this comment)
(script# SCRIPT)
(include game.sh)
(use Main)
(use System)


(class Script kindof Object
	;; A Script is a kind of Object which has a state, methods to
	;; change that state, and code to execute when the state changes.
	;; It is used to model a sequence of actions which should be
	;; executed by an object, such as an Actor walking to the base of
	;; some stairs, walking up the stairs, and opening a door.
	
	(properties
		client 		0 		;the Object whose actions are controlled by this Script
		state 		-1		;the current state of the script
		start 		0 		;the starting state of the script
		timer 		0 		;the ID of a timer whose client is this Script
		cycles 		0 		;the number of cycles to wait before changing state
		seconds 		0		;the number of seconds to wait before changing state
		lastSeconds 0		;<private>
		ticks			0		;the number of ticks to wait before changing state
		lastTicks 	0		;<private>
		register		0		;open architecture property, can be anything the
								;programmer wants it to be, including a list
		script		0		;sub-scripts provide subroutine-style functionality
		caller		0		;who should we cue when we're disposed
		next			0		;who should we CHAIN to when we're disposed
								;can be pointer to an instance of Script or
								;number of file that has a Script as public zero
	)
;;;	(methods
;;;		changeState		;change to new state
;;;		cue				;change to next state
;;;		handleEvent		;handle events passed to this Script
;;;		setScript		;set new sub-script
;;;	)

	(method (doit &tmp thisSeconds)
		(if script
			(script doit:)
		)
		(cond
			(cycles
				(if (not (-- cycles))
					(self cue:)
				)
			)
			(seconds
				(= thisSeconds (GetTime SysTime1))
				(if (!= lastSeconds thisSeconds)
					(= lastSeconds thisSeconds)
					(if (not (-- seconds))
						(self cue:)
					)
				)
			)
			(ticks
				(if (<= (-= ticks	(Abs (- gameTime	lastTicks))) 0)
					(= ticks 0)
					(= lastTicks gameTime)
					(self cue:)
				else
					(= lastTicks gameTime)
				)
			)
		)
		
		; Note!  No code can be added below the above cond because a clone
		;	may be disposed of before this point.
	)

	(method (init who whoCares reg)
		(= lastTicks gameTime)
		(if (>= argc 1)			((= client who) script: self)	;double link!
			(if (>= argc 2) 		(= caller whoCares)
				(if (>= argc 3) 	(= register reg)
				)
			)
		)
		(= state (- start 1))
		(self cue:)
	)

	(method (dispose &tmp theNextScript)
		(if script
			(script dispose:)
		)
		(if timer
			(timer dispose:)
		)
		(if client
			(client script: (= theNextScript next))

			(cond
				((not theNextScript)					;no next, nothing to do
					FALSE
				)
				((== newRoomNum curRoomNum)		;have next, no room change
					(theNextScript init: client)
				)
				(else										;room change
					(theNextScript dispose:)		;clean up clones!
				)
			)
		)

		;;NOTE: client's script property MUST be changed BEFORE cue'ing caller
		;;      We DON'T cue caller on room changes.
		;;
		(if (and	caller (== newRoomNum curRoomNum))
			(caller cue: register)	;return register value
		)
		
		;;the following cleanup statement allow a "disposed" static script 
		;;to be reused, even though you shouldn't do it! More proper is to 
		;;Clone a script if it will be used many times.
		;;
		(= script (= timer (= client (= next (= caller 0)))))
		(= seconds (= cycles (= ticks 0)))
		
		(super dispose:)
	)

	(method (changeState newState)
		(= state newState)
	)

	(method (cue)
		(if client			;; Don't changeState if we're an orphan script
			(= lastTicks gameTime)
			(self changeState: (+ state 1) &rest)
		)
	)

	(method (setScript newScript)
		(if script
			(script dispose:)
		)
		
		(if newScript
			(newScript init: self &rest)	;init sets our script property!
		)
	)

	(method (handleEvent event)
		(if script
			(script handleEvent: event)
		)
		(return (event claimed?))
	)
)