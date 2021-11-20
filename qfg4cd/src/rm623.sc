;;; Sierra Script 1.0 - (do not remove this comment)
(script# 623)
(include sci.sh)
(use Main)
(use GloryRm)
(use Scaler)
(use Polygon)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm623 0
)

(local
	local0
)
(instance rm623 of GloryRm
	(properties
		modNum 640
		picture 640
		east 630
		west 641
	)
	
	(method (init)
		(switch prevRoomNum
			(641 (ego loop: 2 posn: 83 80))
			(630 (ego posn: 292 110))
			(else  (ego posn: 292 110))
		)
		(if (< (ego x?) 100) (= local0 1))
		(ego
			init:
			setScaler: Scaler 100 100 189 0
			normalize:
			setPri: (if local0 68 else 0)
		)
		(super init: &rest)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init:
						62
						89
						36
						100
						31
						115
						39
						129
						102
						151
						92
						164
						33
						157
						34
						189
						319
						189
						319
						138
						293
						138
						298
						142
						287
						149
						184
						142
						174
						136
						146
						131
						114
						126
						91
						125
						44
						111
						59
						99
						98
						100
						109
						90
					yourself:
				)
		)
		(vLeftSteps ignoreActors: init:)
		(vShelfBarrels ignoreActors: approachVerbs: 4 init:)
		(vBigBarrels ignoreActors: init:)
		(vColumn ignoreActors: init:)
		((ScriptID 645 0) init:)
	)
	
	(method (dispose)
		(if script (script dispose:))
		(DisposeScript 645)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 1)
			(messager say: 0 1 0 0 0 623)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance sGetFlask of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego view: 31 setLoop: 0 1 setCel: 0 setCycle: End self)
			)
			(1
				(messager say: 13 4 23 1 self 640)
			)
			(2 (ego setCycle: Beg self))
			(3
				(getSound play:)
				(ego get: 9 normalize:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sLeave of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo (ego x?) (- (ego y?) 5) self)
			)
			(1
				(if (< (ego x?) 150)
					(curRoom newRoom: (curRoom west?))
				else
					(curRoom newRoom: (curRoom east?))
				)
			)
		)
	)
)

(instance vLeftSteps of View
	(properties
		noun 8
		modNum 640
		y 85
		view 693
	)
)

(instance vShelfBarrels of View
	(properties
		noun 13
		modNum 640
		approachX 109
		approachY 90
		x 125
		y 59
		z 20
		priority 108
		fixPriority 1
		view 6777
		cel 1
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(if (Btst 111)
				(messager say: 13 4 19 1 0 640)
			else
				(Bset 111)
				(curRoom setScript: sGetFlask)
			)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance vBigBarrels of View
	(properties
		noun 12
		modNum 640
		x 117
		y 44
		priority 108
		fixPriority 1
		view 6777
	)
)

(instance vColumn of View
	(properties
		modNum 640
		priority 255
		fixPriority 1
		view 689
	)
)

(instance getSound of Sound
	(properties
		number 934
	)
)
