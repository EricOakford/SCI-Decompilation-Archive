;;; Sierra Script 1.0 - (do not remove this comment)
(script# 804)
(include sci.sh)
(use Main)
(use PolyPath)
(use Motion)
(use System)

(public
	sEnter 0
	sGiveMsg 1
	sShowRunes 2
	sGetTheBush 3
	sFetchBonsai 4
)

(local
	local0
)
(instance sEnter of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(switch prevRoomNum
					(790
						(ego setMotion: PolyPath (- (ego x?) 25) (ego y?) self)
					)
					(else 
						(ego setMotion: PolyPath (+ (ego x?) 25) (ego y?) self)
					)
				)
			)
			(1
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sGiveMsg of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if local0
					(self dispose:)
				else
					(= local0 1)
					(self cue:)
				)
			)
			(1
				(messager say: 15 6 4 0 self)
			)
			(2 (self dispose:))
		)
	)
)

(instance sShowRunes of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 152 162 self)
			)
			(1
				((ScriptID 800 1)
					init:
					approachVerbs: 43
					setCycle: End self
				)
			)
			(2
				(messager say: 4 60 0 0 self)
			)
			(3
				((ScriptID 800 1)
					view: 804
					setLoop: 6 1
					setCel: 0
					x: 168
					y: 179
					z: 30
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sGetTheBush of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo 148 169 self)
			)
			(1
				(ego
					view: 4
					setLoop: 0 1
					setCel: 0
					posn: 141 169
					setCycle: CT 4 1 self
				)
			)
			(2
				((ScriptID 800 2) hide: dispose:)
				(= seconds 1)
			)
			(3
				(ego setCel: 4 setCycle: CT 0 -1 self)
			)
			(4
				(Bset 335)
				(ego solvePuzzle: 415 15 get: 48 normalize:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sFetchBonsai of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego trySkill: 27)
				(if (Btst 353)
					((ScriptID 800 2) hide:)
					(ego solvePuzzle: 415 15)
				)
				(self cue:)
			)
			(1
				(if (Btst 353)
					(messager say: 15 6 43 0 self)
				else
					(messager say: 15 6 35 0 self)
				)
			)
			(2
				(if (Btst 353)
					(ego get: 48)
					((ScriptID 800 2) dispose:)
					(Bset 335)
					(= seconds 1)
				else
					(self dispose:)
				)
			)
			(3 (self dispose:))
		)
	)
)
