;;; Sierra Script 1.0 - (do not remove this comment)
(script# PCYCLE)
(include game.sh)
(use Motion)
;;;;
;;;;	PCYCLE.SC
;;;;	(c) Sierra On-Line, Inc, 1990
;;;;
;;;;	Author: J. Mark Hood
;;;; Palette Cycling Class.  
;;;;
;;;; It tries to take the place of having many  different palette
;;;; cyclers by watching the parameter list.
;;;; If one number is passed it acts like a CycleTo cycling
;;;; from the current palette to the passed palette:
;;;; 	(aProp setCycle:PCycle 5) cycles from current palette to palette 5.
;;;; If two numbers are passed it cycles through the range specified
;;;; in the proper direction (i.e. first number to second):
;;;; 	(aProp setCycle:PCycle 5 2) cycles from palette 5 to palette 2
;;;; If three numbers are passed it cycles through this range a number of 
;;;; times:
;;;; 	(aProp setCycle:PCycle 5 2 4) cycles from palette 5 to palette 2, 
;;;;	4 times.
;;;; 
;;;; Of course, a caller can be added to any of the above conventions,
;;;; to cue as any decent Cycler should.


(class PCycle kindof CycleTo
	(properties
		client 0			;object whose cycling the class is controlling
		caller 0			;object to notify when cycling is completed
		cycleDir 1		;cycle direction (1 == forward, -1 == backward)
		startPal	0		;starting palette number for cycle
		endPal 0			;ending palette number for cycle
		cycleCnt 0		;"speed" related property
		completed 0
		howMany 1
	)
	
;;;	(methods
;;;		nextCel	;return value of next palette in the current cycling direction
;;;		cycleDone 		;method invoked when the cycling finishes
;;;		motionCue
;;;	)
	
	
	(method (init theObj p1 p2OrWhoCares howManyOrWhoCares whoCares)
		;Make theObj our client.
		(if argc 
			(= client theObj)
			(cond
				;; like a CycleTo
				((or (== argc 2) (and (== argc 3) (IsObject p2OrWhoCares)))
					(= startPal (client palette?))
					(= endPal p1)
					(if (== argc 3)
						(= caller p2OrWhoCares)
					)
				)
				;; Cycle a range once
				((or (== argc 3) (and (== argc 4) (IsObject howManyOrWhoCares)))
					(= startPal p1)
					(= endPal p2OrWhoCares)
					(if (== argc 4)
						(= caller howManyOrWhoCares)
					)
				)
				;; Cycles a range a number of times
				((>= argc 4) 
					(= startPal p1)
					(= endPal p2OrWhoCares)
					(= howMany howManyOrWhoCares)
					(if (== argc 5)
						(= caller whoCares)
					)
				)

			)
		)
		(= cycleDir
			(if (< endPal startPal)
				-1
			else
				1
			)
		)
		;Reset cycle counter.
		(= cycleCnt 0)
		(= completed FALSE)
	)
	
	(method (doit)
		(if (>= (++ cycleCnt) (client cycleSpeed?))
			(= cycleCnt 0)
			(client
				palette: (self nextCel?), ; make use of cycler methods
				forceUpd:
			)
			(if (not howMany)
				(= completed TRUE)
				(self cycleDone:)
			)
		)
	)

	(method (nextCel &tmp thePal)
		(= thePal
			(+ (client palette?) cycleDir)
		)
		(cond
			((> thePal endPal)
				(= thePal startPal)
				(-- howMany)
			)
			((< thePal startPal)
				(= thePal endPal)
				(-- howMany)
			)
		)
		(if (not howMany)
			(= thePal (client palette?))
		)
		(return thePal)
	)
	
)