;;; Sierra Script 1.0 - (do not remove this comment)
(script# 20800)
(include sci.sh)
(use Main)
(use TorinEgo)
(use TPRoom)
(use GenDialog)
(use Script)
(use Polygon)
(use Feature)
(use Motion)
(use Actor)

(public
	roCliffTransporter 0
)

(instance oRSDHandler of VerbHandler
	(properties)
	
	(method (doVerb theVerb)
		(return
			(if (== theVerb 13)
				(ego setScript: 'LOOKUP_ERROR')
				(return 1)
			else
				(return 0)
			)
		)
	)
)

(instance foCryst of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 13)
		(= onMeCheck
			((Polygon new:)
				type: 0
				init:
					262
					0
					336
					-2
					344
					33
					349
					113
					362
					118
					373
					160
					370
					173
					390
					186
					399
					277
					317
					284
					249
					282
					245
					168
					262
					160
					257
					72
					265
					63
				yourself:
			)
		)
		(self setTotalWidth: 1)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(ego setScript: LOOKUP_ERROR)
			)
			(13
				(ego setScript: LOOKUP_ERROR)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance poTorin of Prop
	(properties)
)

(instance soGetOuttaDodge of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 2)
			)
			(1
				(ego setMotion: MoveTo 349 301 self)
			)
			(2
				(theGame handsOff:)
				(((ScriptID 64001 0) get: 9) moveTo: -3)
				(ego setHeading: 180 self)
			)
			(3
				(ego hide:)
				(LOOKUP_ERROR
					view: 20800
					loop: 0
					cel: 0
					posn: 349 301
					init:
					setCycle: CT 13 1 self
				)
			)
			(4
				(LOOKUP_ERROR setCycle: End self)
				(theSound lThumbLoop: 20801 self)
			)
			(5)
			(6
				(LOOKUP_ERROR dispose:)
				((ScriptID 64017 0) set: 95)
				(theGame handsOn:)
				(if (= temp0 (MakeMessageText 15 13 0 1 300))
					(TextDialog temp0 continueText)
					(temp0 dispose:)
					(= temp0 0)
				)
				(if (= temp0 (MakeMessageText 15 13 0 2 300))
					(TextDialog temp0 continueText)
					(temp0 dispose:)
					(= temp0 0)
				)
				(curRoom newRoom: -1502)
				(self dispose:)
			)
		)
	)
)

(instance roCliffTransporter of TPRoom
	(properties
		picture 20800
	)
	
	(method (init)
		(super init: &rest)
		(theMusic pageSize: 20800)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init: 1 305 2 314 630 312 628 283 316 292
					yourself:
				)
		)
		(LOOKUP_ERROR init:)
		(ego init: oPanner:)
		(ego oFlagValues: LOOKUP_ERROR)
		(theGame handsOff:)
		(ego posn: 650 300)
		(ego setMotion: MoveTo 600 300 (ScriptID 64020 0))
	)
	
	(method (setWander)
	)
	
	(method (intoPouch)
		(ego get: ((ScriptID 64001 0) get: 9))
	)
)
