;;; Sierra Script 1.0 - (do not remove this comment)
(script# 415)
(include sci.sh)
(use Main)
(use rLab)
(use PolyPath)
(use Polygon)
(use Motion)
(use Actor)
(use System)

(public
	rm415 0
)

(instance rm415 of LabRoom
	(properties
		south 400
	)
	
	(method (init)
		(if (== ((inventory at: 11) owner?) curRoomNum)
			(theSkull init: stopUpd:)
			(curRoom
				addObstacle:
					((Polygon new:)
						type: 2
						init:
							0
							189
							0
							0
							319
							0
							319
							189
							190
							189
							190
							185
							276
							185
							264
							172
							261
							178
							237
							178
							198
							155
							206
							152
							240
							151
							205
							151
							190
							143
							117
							143
							117
							153
							90
							164
							72
							157
							38
							185
							130
							185
							130
							189
						yourself:
					)
					((Polygon new:)
						type: 2
						init: 196 152 180 152 176 149 181 146 195 146 200 149
						yourself:
					)
			)
		else
			(curRoom
				addObstacle:
					((Polygon new:)
						type: 2
						init:
							0
							189
							0
							0
							319
							0
							319
							189
							190
							189
							190
							185
							276
							185
							264
							172
							261
							178
							237
							178
							198
							155
							206
							152
							240
							151
							205
							151
							190
							143
							117
							143
							117
							153
							90
							164
							72
							157
							38
							185
							130
							185
							130
							189
						yourself:
					)
			)
		)
		(skelton1 init: stopUpd:)
		(skelton2 init: stopUpd:)
		(skelton3 init: stopUpd:)
		(super init: &rest)
		(curRoom setScript: (ScriptID 30 1))
		((ScriptID 30 0) initCrypt: 1)
	)
	
	(method (notify)
		((ScriptID 30 3) show:)
	)
)

(instance theSkull of View
	(properties
		x 189
		y 151
		noun 12
		modNum 400
		view 403
		loop 6
		cel 3
		signal $4000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(curRoom setScript: getSkull)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance skelton1 of View
	(properties
		x 87
		y 138
		noun 10
		modNum 400
		view 403
		loop 6
		signal $4000
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 0)
			(messager say: 10 5 0 1)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance skelton2 of View
	(properties
		x 239
		y 163
		noun 10
		modNum 400
		view 403
		loop 6
		cel 1
		signal $4000
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 0)
			(messager say: 10 5 0 1)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance skelton3 of View
	(properties
		x 227
		y 143
		noun 10
		modNum 400
		view 403
		loop 6
		cel 2
		signal $4000
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 0)
			(messager say: 10 5 0 1)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance getSkull of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 204 151 self)
			)
			(1
				(ego setHeading: 270)
				(= cycles 8)
			)
			(2
				(ego
					normal: 0
					view: 401
					setLoop: 1
					cycleSpeed: 6
					posn: 193 153
					setCycle: CycleTo 3 1 self
				)
			)
			(3
				(messager say: 12 5 0 1 self 400)
			)
			(4
				(theGame givePoints: 1)
				(theSkull dispose:)
				(ego setCycle: EndLoop self)
			)
			(5
				((curRoom obstacles?) dispose:)
				(theGame handsOn:)
				(ego posn: 204 151 get: 11 reset: 1)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: 2
							init:
								0
								189
								0
								0
								319
								0
								319
								189
								190
								189
								190
								185
								276
								185
								264
								172
								261
								178
								237
								178
								198
								155
								206
								152
								240
								151
								205
								151
								190
								143
								117
								143
								117
								153
								90
								164
								72
								157
								38
								185
								130
								185
								130
								189
							yourself:
						)
				)
				(self dispose:)
			)
		)
	)
)
