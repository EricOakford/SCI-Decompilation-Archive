;;; Sierra Script 1.0 - (do not remove this comment)
(script# rmDollCloseup) ;371
(include game.sh)
(use Main)
(use Intrface)
(use LLRoom)
(use Feature)

(public
	rm371 0
)

(local
	readPreventionMessage
)
(procedure (InitFeatures)
	(doll init:)
	(herMouth init:)
	(fLeftNipple init:)
	(fRightNipple init:)
	(fLeftTit init:)
	(fRightTit init:)
)

(instance rm371 of LLRoom
	(properties
		picture 375
	)
	
	(method (init)
		(InitFeatures)
		(ego init: z: 1000 hide:)
		(super init:)
	)
	
	(method (doit)
		(super doit: &rest)
		(if (ego mover?) (HandsOff) (curRoom newRoom: rmBedroom))
	)
)

(instance herMouth of Feature
	(properties
		x 156
		y 81
		nsTop 71
		nsLeft 142
		nsBottom 91
		nsRight 171
		description {her mouth}
		sightAngle 40
		lookStr {Her mouth reminds you of something.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(Printf 371 0 (self lookStr?))
			)
			(else 
				(doll doVerb: theVerb theItem)
			)
		)
	)
)

(instance fRightNipple of Feature
	(properties
		x 156
		y 172
		nsTop 166
		nsLeft 144
		nsBottom 179
		nsRight 169
		description {her right nipple}
		sightAngle 40
		lookStr {You used this to inflate half of her.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(Printf 371 0 (self lookStr?))
			)
			(verbTaste
				(Print 371 1)
			)
			(else 
				(doll doVerb: theVerb theItem)
			)
		)
	)
)

(instance fLeftNipple of Feature
	(properties
		x 250
		y 136
		nsTop 126
		nsLeft 238
		nsBottom 141
		nsRight 262
		description {her left nipple}
		lookStr {You used this to inflate half of her.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(Printf 371 0 (self lookStr?))
			)
			(verbTaste
				(Print 371 1)
			)
			(else 
				(doll doVerb: theVerb theItem)
			)
		)
	)
)

(instance fRightTit of Feature
	(properties
		x 157
		y 164
		nsTop 140
		nsLeft 122
		nsBottom 189
		nsRight 193
		description {that latex breast}
		sightAngle 40
		lookStr {At this particular moment, you feel her right breast is the best.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(Printf 371 0 (self lookStr?))
			)
			(verbDo
				(Print 371 2)
			)
			(else 
				(doll doVerb: theVerb theItem)
			)
		)
	)
)

(instance fLeftTit of Feature
	(properties
		x 239
		y 135
		nsTop 114
		nsLeft 199
		nsBottom 162
		nsRight 279
		description {that latex breast}
		sightAngle 40
		lookStr {At this particular moment, you feel her left breast is the best.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(Printf 371 0 (self lookStr?))
			)
			(verbDo
				(Print 371 2)
			)
			(else 
				(doll doVerb: theVerb theItem)
			)
		)
	)
)

(instance doll of Feature
	(properties
		x 162
		y 30
		nsLeft 39
		nsBottom 189
		nsRight 285
		description {the doll}
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(Print 371 3)
			)
			(verbDo (Print 371 4))
			(verbZipper
				(if readPreventionMessage
					(SolvePuzzle fScrewDoll 8)
					(Print 371 5)
					(Print 371 6)
					(Print 371 7)
					(HandsOff)
					(curRoom newRoom: rmBedroom)
				else
					(= readPreventionMessage TRUE)
					(Print 371 8)
				)
			)
			(verbTalk
				(Print 371 9)
				(Print 371 10 #at -1 140)
			)
			(verbTaste
				(Print 371 9)
				(Print 371 10 #at -1 140)
			)
			(verbUse
				(switch theItem
					(iGraffiti
						(Print 371 11)
					)
					(iPocketKnife
						(Print 371 12)
						(HandsOff)
						(curRoom newRoom: rmBedroom)
					)
					(else
						(Print 371 13)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)
