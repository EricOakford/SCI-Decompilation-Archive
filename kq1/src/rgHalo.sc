;;; Sierra Script 1.0 - (do not remove this comment)
(script# HALO)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)

(public
	rgHalo 0
)

(instance rgHalo of Region	
	(method (init)
		(Load VIEW 702)
		(halo
			init:
			hide:
			ignoreHorizon:
			illegalBits: 0
			ignoreActors:
			setCycle: Forward
		)
		(super init:)
	)

	(method (doit &tmp temp0)
		;EO: This has been newly decompiled. It should be tested.
		(if haloTimer
			(cond 
				(
					(not
						(if (== (ego view?) 34)
							(if (== (ego loop?) 2) else (== (ego loop?) 3))
						)
					)
					(= temp0 (/ (+ (ego nsLeft?) (ego nsRight?)) 2))
					(if (ego mover?)
						(switch (ego loop?)
							(0
								(+= temp0 (ego xStep?))
							)
							(1
								(-= temp0 (ego xStep?))
							)
						)
					)
					(halo
						posn: temp0 (/ (+ (ego nsTop?) (ego nsBottom?)) 2)
						setPri: (ego priority?)
						show:
					)
				)
			)
		else
			(if (cast contains: halo)
				(halo dispose:)
			)
		)
	)
)

(instance halo of Actor
	(properties
		view 702
		signal $0000
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((super handleEvent: event)
				(return)
			)
			((Said 'look,check/halo,glow,cloud,spell')
				(self doVerb: verbLook)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 616 0)
			)
		)
	)
)
