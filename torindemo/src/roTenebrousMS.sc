;;; Sierra Script 1.0 - (do not remove this comment)
(script# 50800)
(include sci.sh)
(use Main)
(use TPRoom)
(use TPScript)
(use ExitFeature)
(use PolyPath)
(use Polygon)
(use Motion)

(public
	roTenebrousMS 0
)

(instance foBackStageExit of ExitFeature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setSpeedFraction: (ScriptID 64006 1))
		(self setRect: 298 88 408 228)
	)
	
	(method (doVerb)
		(ego setMotion: PolyPath 350 232 self)
	)
	
	(method (cue)
		(curRoom newRoom: -14636)
	)
)

(instance soPlayMovie of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					posn: 344 423
					setLoop: 7 1
					setMotion: MoveTo 83 282 self
				)
			)
			(1
				(ego setPri: 1 setMotion: MoveTo 6 441 self)
			)
			(2 (= ticks 30))
			(3
				(ego
					nCurPosY: 10
					posn: -10 267
					setLoop: -1
					setMotion: MoveTo 46 252 self
				)
			)
			(4
				(curRoom picture: -14735)
				(curRoom drawPic: -14735)
				(ego posn: -30 310 oPanner: setMotion: MoveTo 30 310 self)
			)
			(5
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance roTenebrousMS of TPRoom
	(properties
		picture -14736
	)
	
	(method (init)
		(super init: &rest)
		(music1 pageSize: -14836)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init:
						3
						285
						3
						310
						6
						313
						628
						314
						629
						288
						575
						283
						508
						252
						478
						232
						211
						233
						194
						251
						127
						287
					yourself:
				)
		)
		(foBackStageExit init:)
		(ego init: oPanner:)
		(curRoom setScript: soPlayMovie)
	)
	
	(method (setWander)
	)
	
	(method (intoPouch)
	)
)
