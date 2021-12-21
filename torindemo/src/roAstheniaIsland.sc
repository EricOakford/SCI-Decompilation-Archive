;;; Sierra Script 1.0 - (do not remove this comment)
(script# 40200)
(include sci.sh)
(use Main)
(use TPRoom)
(use TPScript)
(use PolyPath)
(use Polygon)
(use Feature)
(use Motion)

(public
	roAstheniaIsland 0
)

(instance foCannonBall of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1)
		(self setRect: 99 125 154 164)
	)
	
	(method (doVerb)
		(ego setScript: LOOKUP_ERROR)
	)
)

(instance foCaretakers of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1)
		(self setRect: 308 159 428 177)
	)
	
	(method (doVerb)
		(ego setScript: {foCatapult})
	)
)

(instance foCatapult of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1)
		(self setRect: 221 164 248 179)
	)
	
	(method (doVerb)
		(ego setScript: LOOKUP_ERROR)
	)
)

(instance soGoToCatapult of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (< (ego x?) 230)
					(= global248 0)
					(ego setMotion: PolyPath 220 179 self)
				else
					(= global248 1)
					(ego setMotion: PolyPath 248 178 self)
				)
			)
			(1 (curRoom newRoom: -23436))
		)
	)
)

(instance soGoToCannonBall of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 154 168 self)
			)
			(1 (curRoom newRoom: -23336))
		)
	)
)

(instance soGoToCaretakers of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 307 178 self)
			)
			(1 (curRoom newRoom: -23536))
		)
	)
)

(instance soWalkInFromEntrance of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 64018 0)
					posn: 273 169
					init:
					oPanner:
					nCurPosY: 10
					xStep: 2
					yStep: 1
					setScript: LOOKUP_ERROR self
				)
				(ego posn: 283 169 setMotion: MoveTo 297 174 self)
			)
			(1)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soBoogleWalkIn of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 64018 0)
					bSwing: 0
					setMotion: MoveTo 307 174 self
				)
			)
			(1
				((ScriptID 64018 0) bSwing: 1)
				(self dispose:)
			)
		)
	)
)

(instance roAstheniaIsland of TPRoom
	(properties
		picture -25336
	)
	
	(method (init)
		(super init: &rest)
		(theMusic pageSize: -25336)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init:
						162
						165
						135
						169
						191
						180
						224
						184
						228
						177
						245
						179
						246
						185
						316
						184
						306
						172
						263
						175
						253
						172
						219
						173
						191
						171
					yourself:
				)
		)
		(LOOKUP_ERROR init:)
		(LOOKUP_ERROR init:)
		(LOOKUP_ERROR init:)
		(ego init: oPanner: nCurPosY: 10 xStep: 2 yStep: 1)
		(switch prevRoomNum
			(-25436
				(ego setScript: LOOKUP_ERROR)
				(return)
			)
			(-23536
				(ego posn: 307 178)
				((ScriptID 64018 0) posn: 297 178)
			)
			(-23436
				(if global248
					(ego posn: 248 178)
					((ScriptID 64018 0) posn: 258 178)
				else
					(ego posn: 220 179)
					((ScriptID 64018 0) posn: 210 179)
				)
			)
			(-23336
				(ego posn: 154 168)
				((ScriptID 64018 0) posn: 164 168)
			)
			(else 
				(ego posn: 297 174)
				((ScriptID 64018 0) posn: 307 174)
			)
		)
		(theGame handsOn:)
		((ScriptID 64018 0)
			init:
			oPanner:
			nCurPosY: 10
			xStep: 2
			yStep: 1
		)
	)
	
	(method (setWander)
	)
	
	(method (intoPouch)
	)
)
