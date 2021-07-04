;;; Sierra Script 1.0 - (do not remove this comment)
(script# 1105)
(include game.sh)
(use Main)
(use Procs)
(use KQRoom)
(use Print)
(use Feature)
(use Actor)

(public
	rm1105 0
)

(instance rm1105 of KQRoom
	(properties
		picture 1110
	)
	
	(method (init)
		(super init:)
		(if
			(Print
				addText: {Do you want the key?}
				addButton: 1 {GIVE IT TO ME!} 5 10
				addButton: 0 {NO} 65 10
				init:
			)
			(ego get: 13)
		)
		(if (Btst 39) (keyHalf1 init:))
		(if (Btst 40) (keyHalf2 init:))
		(keyHole init:)
		(southFeat init:)
		(theGame handsOn:)
	)
)

(instance keyHalf1 of View
	(properties
		x 100
		y 80
		view 1110
	)
	
	(method (init)
		(super init:)
		(self setHotspot: 8 10)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(8
				(Bclr 39)
				(ego get: 5)
				(self dispose:)
			)
		)
	)
)

(instance keyHalf2 of View
	(properties
		x 100
		y 110
		view 1110
		loop 1
	)
	
	(method (init)
		(super init:)
		(self setHotspot: 8 10)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(8
				(Bclr 40)
				(ego get: 12)
				(self dispose:)
			)
		)
	)
)

(instance keyHole of Feature
	(properties
		noun 9
		nsLeft 116
		nsTop 36
		nsRight 211
		nsBottom 110
	)
	
	(method (init)
		(super init: &rest)
		(self setHotspot: 8 10 23 20 22)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(8
					(messager say: noun theVerb 14 0)
				)
				(23
					(Prints {The door opens.})
					(curRoom newRoom: 1100)
					(Bset 13)
					(return 1)
				)
				(20
					(if (Btst 40)
						(messager say: noun theVerb 15 0)
					else
						(ego put: 5)
						(keyHalf1 init:)
						(Bset 39)
					)
				)
				(22
					(if (Btst 39)
						(messager say: noun theVerb 15 0)
					else
						(ego put: 12)
						(keyHalf2 init:)
						(Bset 40)
					)
				)
			)
		)
	)
)

(instance southFeat of Feature
	(properties
		nsLeft 21
		nsTop 130
		nsRight 289
		nsBottom 142
	)
	
	(method (init)
		(super init: &rest)
		(walkHandler add: self)
		(self setHotspot: 10 10)
	)
	
	(method (dispose)
		(walkHandler delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if (& (event type?) walkEvent)
			(curRoom newRoom: 1100)
			(event claimed: TRUE)
		)
	)
)
