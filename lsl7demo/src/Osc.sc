;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	OSC.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1989
;;;;
;;;;	Author: 	J. Mark Hood
;;;;	Updated:
;;;;
;;;;	Cycles client's cel forward and then backward then forward again
;;;;	continuously (by default) or a specified number of times.
;;;;
;;;;	Classes:
;;;;		Oscillate


(script# OSC)
(include game.sh)
(use Motion)


(class Oscillate kindof Cycle
	(properties
		name				"Osc"
		howManyCycles	-1
		cycleDir			1
	)

	;Some observations (March '94):
	;(1)"howManyCycles" is a bad name (as implemented). The number of
	;*half*-cycles executed is given by howManyCycles+1.
	;(2)As coded the Cycler maintains cels 0 and lastCel for twice the
	;normal time. The code in cycleDone which was (presumably) to change
	;the cel will never accomplish it since there is no change of gameTime
	;between the calls to nextCel. I have commented out that ineffectual
	;code ---gtp

	(method (init who howMany whoCares)
		(if (>= argc 2)
			(= howManyCycles howMany)
			(if (>= argc 3)
				(= caller whoCares)
			)
		)
		(super init:who)
	)

	(method (doit &tmp newCel)
		(= newCel (self nextCel:))
		(if (!= newCel (client cel?))
			(if
				(or 
					(> newCel clientLastCel)
					(< newCel 0)
				)
				(= cycleDir (- cycleDir))
				(self cycleDone:)
			else
				(client cel: newCel)
			)
		)
	)

	(method (cycleDone)
		(if howManyCycles
;¯gtp¯			(client cel:(self nextCel:)) ;This can never change cel
			(if (> howManyCycles 0)
				(-- howManyCycles)
			)
		else
			(= completed TRUE)
			(self motionCue:)
		)
	)	
)