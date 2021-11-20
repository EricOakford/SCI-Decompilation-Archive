;;; Sierra Script 1.0 - (do not remove this comment)
(script# 802)
(include sci.sh)
(use Main)
(use PolyPath)
(use Jump)
(use Motion)
(use System)

(public
	sClimbAboard 0
	sClimbDown 1
)

(instance sClimbAboard of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setStep: 3 2 setMotion: PolyPath 152 164 self)
			)
			(1
				(ego setMotion: MoveTo 177 161 self)
			)
			(2
				(ego
					view: 7
					setLoop: 4 1
					setCel: 0
					setPri: 160
					posn: 188 125
					setCycle: End self
				)
			)
			(3
				(ego
					posn: 187 127
					normalize:
					ignoreActors: 1
					setMotion: MoveTo 247 68 self
				)
			)
			(4
				(ego
					view: 56
					setLoop: 4 1
					setCel: 0
					posn: 248 69
					setCycle: Fwd
					setMotion: JumpTo 271 65 self
				)
			)
			(5
				(ego posn: 269 62 normalize:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sClimbDown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 270 62 self)
			)
			(1
				(ego
					view: 56
					setLoop: 1 1
					setCel: 0
					posn: 264 60
					setCycle: Fwd
					setMotion: JumpTo 240 67 self
				)
			)
			(2
				(ego
					posn: 241 69
					normalize:
					ignoreActors: 1
					setMotion: MoveTo 187 127 self
				)
			)
			(3
				(ego
					view: 7
					setLoop: 4 1
					setPri: 160
					posn: 191 125
					setCycle: Beg self
				)
			)
			(4
				(ego
					posn: 185 161
					normalize:
					setMotion: MoveTo 128 166 self
				)
			)
			(5
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)
