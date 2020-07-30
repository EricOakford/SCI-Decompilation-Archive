;;; Sierra Script 1.0 - (do not remove this comment)
(script# 30)
(include game.sh)
(use Main)
(use Intrface)
(use SQRoom)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Motion)
(use Actor)
(use System)

(public
	rm30 0
)

(instance rm30 of SQRoom
	(properties
		lookStr {This is one end of what appears to be a large cavern. The only way to go is to the left.}
		picture 30
		west 31
	)
	
	(method (init)
		(= currentFloor 2)
		(elevdoor init:)
		(shaftDoor init:)
		(blueRocks init:)
		(greenRocks init:)
		(gravel init:)
		(brokenRock init:)
		(LoadMany VIEW 130 46 63 45 130)
		(LoadMany SOUND 438 421)
		(if (== prevRoomNum 31)
			(ego init:)
			(self setScript: fromGrateRoom)
			(= style SCROLLLEFT)
		else
			(ego init: normal: 0)
			(self setScript: fromElevator)
		)
		(self
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						311 187 302 173 275 166 211 159 192 156 181 149
						189 127 188 118 143 123 139 105 131 101 90 102
						70 121 0 111 0 0 319 0 319 189 0 189 0 186 48 182
						64 182 107 177 162 187
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 86 117 109 109 136 118 134 126 111 127 84 123
					yourself:
				)
		)
		(super init: &rest)
		(if (not (Btst fGotStalagmite))
			(Load SOUND 441)
			(stalagmite init:)
		)
		(if (Btst fGooInCave)
			(stickyPlant init:)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbDo
				(Print 30 0)
			)
			(verbTalk
				(Print 30 1)
			)
			(verbSmell
				(Print 30 2)
			)
			(verbTaste
				(Print 30 3)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance stickyPlant of Prop
	(properties
		x 117
		y 89
		description {chunk of sticky stuff}
		sightAngle 45
		view 130
		loop 3
		priority 9
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(if (ego has: iPlant)
					(Print 30 4)
				else
					(ego setScript: takeThePlant)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance takeThePlant of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 133 131 self)
			)
			(1
				(ego view: 46 setLoop: 2 cel: 0 setCycle: CycleTo 4 1 self)
			)
			(2
				(stickyPlant dispose:)
				(ego setCycle: EndLoop self)
			)
			(3
				(ego get: iPlant)
				(Bclr fGooInCave)
				(NormalEgo 1 1 61)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance stalagmite of Prop
	(properties
		x 118
		y 89
		description {stalagmite chunk}
		sightAngle 45
		view 130
		priority 9
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(if (cast contains: self)
					(Print 30 5)
					(Print 30 6)
				else
					(Print 30 7)
				)
			)
			(verbDo
				(if (cast contains: self)
					(ego setScript: takeTheRock)
				else
					(Print 30 7)
				)
			)
			(verbSmell
				(Print
					{The stalagmite itself has no smell. However, something adhering to the stalagmite does seem to generate a maggot-gagging aroma.}
				)
			)
			(verbTaste
				(if (Btst 22)
					(Print
						{Hhhaaaachhhh!!! Now why did you have to go and lick the end of that thing! That was terrible! Didn't you get a clue from the nose message?}
					)
				else
					(Print
						{Skillfully maneuvering your tongue along the stalagmite removes the surface-coating of grit and, 
						in turn, transforms your tastebuds into the organic equivalent of double-aught sandpaper.}
					)
					(Print 30 8)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance takeTheRock of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 134 129 self)
			)
			(1
				(Face ego stalagmite)
				(= cycles 2)
			)
			(2
				(ego view: 46 setLoop: 0 cel: 0 setCycle: CycleTo 2 1 self)
			)
			(3
				(ego moveSpeed: 5 cycleSpeed: 5 cel: 3)
				(= cycles 2)
			)
			(4
				(soundFx number: 447 loop: 1 play:)
				(stalagmite dispose:)
				(ego setCycle: EndLoop self)
				(ego get: iRock)
				(Bset fGotStalagmite)
			)
			(5
				(Print 30 9 #at 10 20)
				(HandsOn)
				(stickyPlant init:)
				(NormalEgo 1 1 61)
				(self dispose:)
				(Bset fGooInCave)
			)
		)
	)
)

(instance fromGrateRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego posn: 15 133 setMotion: MoveTo 45 133 self)
			)
			(1
				(NormalEgo 0 1 61)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance fromElevator of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					show:
					setPri: 2
					view: 63
					setLoop: 1
					setCel: 0
					yStep: 10
					ignoreControl: -32768
					setCycle: 0
					x: 231
					y: 20
					setMotion: MoveTo 231 30 self
				)
			)
			(1
				(ego setCel: 1 setMotion: MoveTo 231 40 self)
			)
			(2
				(ego setCel: 2 setMotion: MoveTo 231 52 self)
				(soundFx number: 433 loop: 1 play:)
				(elevdoor setCycle: BegLoop elevdoor)
			)
			(3 (ego hide:) (= cycles 6))
			(4
				(ego
					show:
					view: 45
					setLoop: 2
					cel: 0
					x: 178
					y: 175
					setPri: 11
					yStep: 2
					cycleSpeed: 8
					moveSpeed: 8
					setCycle: CycleTo 3 1 self
				)
			)
			(5
				(music number: 403 loop: 1 play:)
				(= cycles 12)
			)
			(6
				(ego setCycle: EndLoop self)
				(soundFx number: 433 loop: 1 play:)
				(elevdoor setCycle: EndLoop elevdoor)
			)
			(7
				(music number: 421 loop: -1 play:)
				(if (Btst fGotStalagmite)
					(Print 30 10 #at 40 10)
				else
					(Print 30 11 #at 40 10)
				)
				(HandsOn)
				(NormalEgo 1 1 61)
				(ego setPri: -1)
				(= cycles 15)
			)
			(8
				(elevdoor stopUpd:)
				(self dispose:)
			)
		)
	)
)

(instance brokenRock of Feature
	(properties
		description {shattered rock formation}
		sightAngle 45
		onMeCheck $4000
	)
	
	(method (doVerb theVerb)
		(stalagmite doVerb: theVerb &rest)
	)
)

(instance elevdoor of Prop
	(properties
		x 204
		y 151
		sightAngle 45
		lookStr {It looks like your standard, run-of-the-mill pneumatic transport. This particular model only goes down, however. And it's not a pleasant ride.}
		view 130
		loop 1
		cel 4
		priority 11
		signal (| ignrAct fixPriOn)
		cycleSpeed 4
	)
	
	(method (doVerb theVerb)
		(shaftDoor doVerb: theVerb &rest)
	)
	
	(method (cue)
		(soundFx stop:)
	)
)

(instance shaftDoor of Feature
	(properties
		x 222
		y 76
		description {pneumatic transport}
		sightAngle 45
		onMeCheck $0020
		lookStr {It looks like your standard run-of-the-mill pneumatic transport. This particular model only descends, however. And it's not a pleasant ride.}
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbDo
				(Print 30 12)
			)
			(verbTaste
				(Print 30 13)
			)
			(verbSmell
				(Print 30 14)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance blueRocks of Feature
	(properties
		description {some stalagmites}
		sightAngle 45
		onMeCheck NEARCHECK
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 30 15)
			)
			(verbDo
				(Print 30 16)
			)
			(verbTalk
				(Print 30 17)
			)
			(verbSmell
				(Print 30 18)
			)
			(verbTaste
				(Print 30 19)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance greenRocks of Feature
	(properties
		description {Stalactites}
		sightAngle 45
		onMeCheck FARCHECK
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 30 20)
			)
			(verbDo
				(Print 30 21)
			)
			(verbSmell
				(Print 30 22)
			)
			(verbTaste
				(Print 30 23)
				(Print 30 24)
				(Print 30 25)
				(Print 30 26)
				(Print 30 27)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance gravel of Feature
	(properties
		description {loose gravel}
		sightAngle 45
		onMeCheck $0008
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 30 28)
			)
			(verbDo
				(Print 30 29)
			)
			(verbSmell
				(Print 30 30)
			)
			(verbTaste
				(Print 30 31)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)
