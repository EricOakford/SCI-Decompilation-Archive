;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	RANGEOSC.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1992
;;;;
;;;;	Author: 	Hugh Diedrichs
;;;;	Updated:	
;;;;
;;;;	Almost the same code as Class Oscillate except it accepts a range to 
;;;;	oscillate thru. If you specify a 0 for lCel or no lCel, will oscillate to
;;;;	client lastCel. If you dont specify a fCel then will oscillate from 0 to
;;;;	client lastcel and will infact act just like oscillate.If you want to
;;;;	cue someone but want to oscillate to lastcel, put a 0 for lCel.
;;;;	So setCycle: RangeOsc 5 0 0 self will oscillate between cels 0 to client
;;;;	lastCel 5 times then cue self. Also, cel will be set to fCel upon init so
;;;;	if you are on cel 4 and use rangeosc with fCel = 8, your cel will be set to 8
;;;;	upon init.
;;;;
;;;;	Classes:
;;;;		RangeOsc


(script# RANGEOSC)
(include game.sh)
(use Motion)


(class RangeOsc kindof Cycle

	(properties
		name 			"ROsc"
		cycles 		-1
		firstC 		0
		lastC 		0
		cycleDir		1
	)

	(method (init who howMany fCel lCel whoCares)
		(if (>= argc 2)
			(= cycles howMany)
		)
		(if (>= argc 5)
			(= caller whoCares)
		)
		(super init: who)
		(if (>= argc 3)
			(= firstC fCel)
			(if (>= argc 4)
				(if lCel
				 	(= lastC lCel)
				else
					(= lastC (client lastCel?))
				)
			else
				(= lastC (client lastCel?))
			)
		)
		(client cel: firstC)
	)

	(method (doit &tmp newCel)
		(= newCel (self nextCel:))
		(if (!= newCel (client cel?))
			(if
				(or 
					(> newCel lastC)
					(< newCel firstC)
				)
				(= cycleDir (- cycleDir))
				(self cycleDone:)
			else
				(client cel: newCel)
			)
		)
	)

	(method (cycleDone)
		(if cycles
;¯gtp¯			(client cel:(self nextCel:)) ;Ineffectual, nextCel will 
														  ;return current cel
			(if (> cycles 0)
				(-- cycles)
			)
		else
			(= completed TRUE)
			(self motionCue:)
		)
	)	
)