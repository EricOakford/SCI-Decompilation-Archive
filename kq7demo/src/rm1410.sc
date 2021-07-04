;;; Sierra Script 1.0 - (do not remove this comment)
(script# 1410)
(include game.sh)
(use Main)
(use Procs)
(use KQRoom)
(use ExitFeature)
(use Print)
(use Polygon)
(use Feature)
(use Actor)
(use System)

(public
	rm1410 0
)

(instance rm1410 of KQRoom
	(properties
		picture 1410
	)
	
	(method (init)
		(super init:)
		(= south 1400)
		(Bset 21)
		(theShape init:)
		(theKey init:)
		(bowl init:)
		(southFeat init:)
		(theGame handsOn:)
	)
)

(instance egoDies of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(EgoDead 2 self)
			)
			(1
				(theGame handsOn:)
				(curRoom newRoom: 1400)
			)
		)
	)
)

(instance theShape of View
	(properties
		x 162
		y 80
		view 1400
	)
	
	(method (init)
		(super init:)
		(self setHotspot: 8 10)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(8
					(if (cast contains: theBead)
						(if (or (ego has: 12) (ego has: 13))
							(Prints {You can only have the shape or key.})
						else
							(self dispose:)
							(ego get: 22)
						)
					else
						(curRoom setScript: egoDies)
					)
					(return 1)
				)
			)
		)
	)
)

(instance theKey of View
	(properties
		x 182
		y 70
		view 1400
		cel 1
	)
	
	(method (init)
		(super init:)
		(self setHotspot: 8 10 16)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(8
					(if (cast contains: theBead)
						(if (or (ego has: 12) (ego has: 13))
							(Prints {You can only have the shape or key.})
						else
							(self dispose:)
							(ego get: 12)
						)
					else
						(curRoom setScript: egoDies)
					)
					(return 1)
				)
			)
		)
	)
)

(instance theBead of View
	(properties
		x 130
		y 100
		view 1400
		cel 2
	)
	
	(method (init)
		(super init:)
		(self setHotspot: 8 10)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(8
					(if
					(or (ego has: 12) (ego has: 22) (ego has: 13))
						(curRoom setScript: egoDies)
					else
						(ego get: 11)
						(Prints {You pick the bead back up.})
						(self dispose:)
					)
					(return 1)
				)
			)
		)
	)
)

(instance bowl of Feature
	
	(method (init)
		(super init:)
		(self
			setHotspot: 16 23 53
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init:
						192 39
						168 46
						167 54
						156 59
						153 75
						161 93
						186 105
						211 100
						230 84
						232 63
						218 45
					yourself:
				)
		)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(16
					(theBead init:)
					(ego put: 11)
					(Bset 12)
					(Prints
						{You hear a grinding sound and the head of the statue bobs up and down.}
					)
					(return 1)
				)
			)
		)
	)
)

(instance southFeat of ExitFeature
	(properties
		nsLeft 21
		nsTop 130
		nsRight 289
		nsBottom 142
		exitDir SOUTH
	)
	
	(method (init)
		(super init: &rest)
		(self setHotspot: 10 10)
	)
	
	(method (dispose)
		(walkHandler delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(return
			(cond 
				((event claimed?) (return TRUE))
				((and (& (event type?) walkEvent) (self onMe: event))
					(curRoom newRoom: 1400)
					(event claimed: TRUE)
				)
				((and scratch (not (event type?)) (self onMe: event))
					(= theExitFeature self)
					((self scratch?) doit:)
					(return (event claimed: TRUE))
				)
			)
		)
	)
)
