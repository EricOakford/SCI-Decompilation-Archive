;;; Sierra Script 1.0 - (do not remove this comment)
(script# 22)
(include game.sh)
(use Main)
(use Intrface)
(use SQRoom)
(use Polygon)
(use Feature)
(use LoadMany)
(use Motion)
(use System)

(public
	rm22 0
)

(instance rm22 of SQRoom
	(properties
		lookStr {Were you a proctologist, you might know what these oddly shaped bones are called. To you, though, they're just bones.}
		picture 22
		horizon 20
		north 19
		east 23
		south 25
		west 38
		walkOffTop 1
	)
	
	(method (init)
		(switch prevRoomNum
			(north
				(= style SCROLLUP)
				(ego init:)
				(HandsOn)
			)
			(south
				(= style SCROLLDOWN)
				(ego init:)
				(HandsOn)
			)
			(east
				(= style SCROLLRIGHT)
				(ego init:)
				(HandsOn)
			)
			(34
				(= thirstTimer 3000)
				(= currentFloor 2)
				(= style IRISOUT)
				(LoadMany VIEW 45)
				(self setScript: outOfHole)
			)
			(else 
				(ego init:)
				(= style FADEOUT)
				(HandsOn)
			)
		)
		(self setRegions: KERONA)
		(super init:)
		((ScriptID KERONA 1)
			x: 70
			y: 76
			approachX: 65
			approachY: 76
		)
		((ScriptID KERONA 2)
			x: 15
			y: 100
			approachX: 10
			approachY: 100
		)
		(exitHole init:)
		(if (== currentFloor 2)
			(self
				addObstacle:
					((Polygon new:)
						type: PBarredAccess
						init: 319 189 309 189 291 159 319 159
						yourself:
					)
					((Polygon new:)
						type: PBarredAccess
						init:
							139 189 136 184 127 181 109 177 96 162 114 147 107 143
							92 144 76 136 62 136 25 129 29 119 22 105 10 100 15 86
							84 78 65 44 77 37 78 25 56 0 147 0 289 83 309 134 302 146
							277 149 263 163 270 168 281 189
						yourself:
					)
			)
		else
			(if (== prevRoomNum north) (ego posn: 259 51))
			(self
				addObstacle:
					((Polygon new:)
						type: PBarredAccess
						init: 319 63 301 52 288 53 269 50 251 40 256 35 246 32 202 11 178 0 319 0
						yourself:
					)
					((Polygon new:)
						type: PBarredAccess
						init: 319 189 0 189 0 0 145 0 211 36 241 59 319 73
						yourself:
					)
			)
		)
	)
)

(instance outOfHole of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 2))
			(1
				(soundFx number: 366 loop: 1 play:)
				(ego
					init:
					posn: 295 154
					view: 45
					loop: 1
					cel: 0
					cycleSpeed: 5
					setCycle: CycleTo 2 1 self
				)
			)
			(2
				(soundFx number: 809 loop: 1 play:)
				(ego cel: 3)
				(= cycles 18)
			)
			(3
				(ego cycleSpeed: 7 setCycle: EndLoop self)
			)
			(4
				(NormalEgo 0 1 61)
				(ego loop: 4 heading: 135 setMotion: MoveTo 308 160 self)
				(EgoHeadMove (theEgoHead view?))
			)
			(5 (HandsOn) (self dispose:))
		)
	)
)

(instance exitHole of Feature
	(properties
		description {exit hole}
		onMeCheck cLRED
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(if (Btst fBlewUpLasers)
					(Print 22 0)
				else
					(Print 22 1)
				)
			)
			(verbDo
				(Print 22 2)
			)
			(verbTalk
				(Print 22 3)
			)
			(verbSmell
				(Print 22 4)
			)
			(verbTaste
				(Print 22 5)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)
