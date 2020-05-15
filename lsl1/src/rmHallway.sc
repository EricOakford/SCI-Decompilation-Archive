;;; Sierra Script 1.0 - (do not remove this comment)
(script# rmHallway) ;120
(include game.sh)
(use Main)
(use Intrface)
(use LLRoom)
(use MoveCyc)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Motion)
(use Invent)
(use Actor)
(use System)

(public
	rm120 0
)

(local
	drunkMessage
	seenStinkMessage
	nearDrunk
	nutsMessage
	[ratCycle 33] = [2 0 249 128 2 1 225 119 2 2 202 130 2 3 196 136 2 4 188 134 2 5 167 117 2 6 142 87 2 7 121 63 -32768]
)
(instance rm120 of LLRoom
	(properties
		picture rmHallway
		east rmToilet
		south rmInsideBar
	)
	
	(method (init)
		(LoadMany SOUND 111 110 801 802 121)
		(LoadMany VIEW 120 122 808)
		(soundFx loop: 1 vol: 127 flags: mNOPAUSE)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 189 0 0 319 0 319 189 313 189 259 152 213
						149 210 145 240 142 240 140 213 140 211 136 242
						135 227 128 215 128 192 118 140 118 137 133 100
						146 85 146 85 153 107 158 107 163 77 174 81 182
						81 189
					yourself:
				)
		)
		(ego init:)
		(switch prevRoomNum
			(rmInsideBar
				(if (Random 0 1)
					(aRat cycleSpeed: howFast init: setScript: sRatScript)
				)
			)
			(rmToilet
				(aDoor setCel: 255)
				(HandsOff)
				(self setScript: sFromToilet)
				(theMusic number: 110 loop: -1 flags: mNOPAUSE play: 90)
			)
			(else  (ego posn: 160 160))
		)
		(if (== ((Inventory at: iRose) owner?) 120)
			(aRose init: approachVerbs: verbDo verbUse verbZipper verbTaste stopUpd:)
			(table
				lookStr:
					{There is a single, lovely, long-stemmed, red rose in a delicate bud vase standing incongruously on the table.}
			)
		else
			(table
				lookStr:
					{Isn't it funny how you can see the top of that table even though you're looking at it from the side?}
			)
		)
		(aDoor
			cycleSpeed: howFast
			init:
			stopUpd:
			approachVerbs: verbDo
		)
		(aDrunk
			cycleSpeed: howFast
			init:
			stopUpd:
			approachVerbs: verbDo verbTalk verbUse
		)
		(drunkHead cycleSpeed: howFast init: approachVerbs: verbDo verbTalk verbUse)
		(drunkLeg init: stopUpd: approachVerbs: verbDo verbTalk verbUse)
		(drunkArm cycleSpeed: howFast init: approachVerbs: verbDo verbTalk verbUse)
		(boxes init:)
		(barrels init:)
		(table init: approachVerbs: verbDo)
		(transom init:)
		(theFan init:)
		(theLight init:)
		(super init:)
	)
	
	(method (doit)
		(super doit: &rest)
		(cond 
			(script)
			(
				(and
					(Btst fToiletPaperOnShoe)
					(> (ego y?) 140)
					(not drunkMessage)
					(not seenStinkMessage)
					(== prevRoomNum 130)
				)
				(= drunkMessage 4)
				(= seenStinkMessage TRUE)
				(aDrunk setScript: sDrunkTalks)
			)
			((IsObjectOnControl ego cGREEN)
				(if (> nearDrunk -1)
					(if nutsMessage
						(Print 120 0)
						(= nutsMessage FALSE)
					else
						(Print 120 1)
						(= nutsMessage TRUE)
					)
				)
				(= nearDrunk -1)
			)
			((and (IsObjectOnControl ego cCYAN) (< nearDrunk 0)) (= nearDrunk 1))
		)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook (Print 120 2))
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance sRatScript of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(if
		(and (< (ego distanceTo: aRat) 80) (< state 2))
			(self state: 1)
			(= cycles 1)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(aRat setCycle: EndLoop)
				(= seconds (Random 3 6))
			)
			(1 (self init:))
			(2
				(aRat
					setLoop: 2
					setCel: 0
					setPri: (- (ego priority?) 2)
					setCycle: MoveCycle @ratCycle self
				)
			)
			(3
				(aRat dispose:)
				(self dispose:)
			)
		)
	)
)

(instance sFromToilet of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					x: 262
					y: 125
					setPri: (- (ego priority?) 2)
					setMotion: PolyPath 216 130 self
				)
			)
			(1
				(soundFx number: 802 loop: 1 play:)
				(aDoor setCycle: BegLoop self)
				(ego setPri: -1)
			)
			(2 (HandsOn) (self dispose:))
		)
	)
)

(instance sToToilet of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(soundFx number: 801 loop: 1 play:)
				(aDoor setCycle: EndLoop)
				(ego
					egoSpeed:
					setPri: (- (ego priority?) 1)
					setMotion: MoveTo 262 (ego y?) self
				)
			)
			(1
				(ego userSpeed: setPri: -1)
				(theMusic fade:)
				(curRoom newRoom: 130)
			)
		)
	)
)

(instance sDrunkTalks of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== (drunkHead loop?) 1)
					(drunkHead setCycle: EndLoop self)
				else
					(= cycles 1)
				)
			)
			(1
				(drunkHead loop: 2 cel: 0 setCycle: EndLoop self)
			)
			(2
				(switch drunkMessage
					(1 (Print 120 3))
					(4 (Print 120 4))
				)
				(= cycles 1)
			)
			(3 (self dispose:))
		)
	)
)

(instance sDrunkDrinks of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(if
		(and (== state 5) (== (soundFx prevSignal?) -1))
			(= cycles 1)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== (drunkHead loop?) 1)
					(drunkHead setCycle: EndLoop self)
				else
					(= cycles 1)
				)
			)
			(1
				(ego
					egoSpeed:
					view: 808
					loop: 2
					cel: 0
					setCycle: EndLoop self
				)
			)
			(2
				(drunkArm loop: 4 cel: 0 setCycle: EndLoop self)
			)
			(3
				(ego setCycle: BegLoop self)
				(drunkArm setCycle: BegLoop)
			)
			(4
				(drunkHead loop: 3 cel: 0 setCycle: EndLoop self)
			)
			(5
				(soundFx number: 111 loop: 1 play:)
				(Print 120 5 #at -1 20 #dispose)
			)
			(6
				(NormalEgo 0)
				(HandsOn)
				(if modelessDialog (modelessDialog dispose:))
				(drunkHead setCycle: BegLoop self)
			)
			(7
				(drunkHead loop: 2 cel: 0 setCycle: EndLoop self)
			)
			(8
				(switch drunkMessage
					(2 (Print 120 6))
					(3
						(Print 120 7)
						(Print 120 8)
						(soundFx number: 121 setLoop: 1 play:)
						(Print 120 9)
						(soundFx play:)
						(Print 120 9)
						(soundFx play:)
						(Print 120 9)
						(soundFx play:)
						(if
						(or (ego mover?) (!= (ego x?) 229) (!= (ego y?) 143))
							(Print 120 10)
							(= drunkMessage 2)
						else
							(HandsOff)
							(Print 120 11)
							(Print 120 12)
						)
					)
				)
				(if (== drunkMessage 3) (= state (+ state 1)))
				(= cycles 1)
			)
			(9 (self dispose:))
			(10
				(drunkArm loop: 5 cel: 0 setCycle: EndLoop self)
			)
			(11
				(SolvePuzzle fGetRemoteControl 2)
				(ego
					egoSpeed:
					get: iRemoteControl
					view: 808
					loop: 2
					cel: 0
					setCycle: EndLoop self
				)
			)
			(12 (ego setCycle: BegLoop self))
			(13
				(drunkArm loop: 5 cel: 0 setCycle: BegLoop self)
			)
			(14
				(NormalEgo 0)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sGetRose of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					egoSpeed:
					view: 808
					loop: 1
					cel: 0
					setCycle: EndLoop self
				)
			)
			(1
				(aRose z: 1000 dispose:)
				(SolvePuzzle fGetRose 1)
				(ego setCycle: BegLoop self)
			)
			(2
				(table
					lookStr:
						{Isn't it funny how you can see the top of that table even though you're looking at it from the side?}
				)
				(NormalEgo 1)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance aRose of View
	(properties
		x 107
		y 107
		description {the rose}
		approachX 118
		approachY 142
		lookStr {There is a single, lovely, long-stemmed, red rose in a delicate bud vase standing incongruously on the table.}
		view 120
		signal ignrAct
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbTaste (Print 120 13))
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance drunkHead of Prop
	(properties
		x 256
		y 146
		z 10
		approachX 229
		approachY 143
		view 122
		loop 1
		priority 10
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(aDrunk doVerb: theVerb theItem)
	)
)

(instance drunkArm of Actor
	(properties
		x 251
		y 133
		approachX 229
		approachY 143
		view 122
		loop 4
		priority 10
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(aDrunk doVerb: theVerb theItem)
	)
)

(instance drunkLeg of View
	(properties
		x 246
		y 149
		approachX 229
		approachY 143
		view 122
		loop 6
		priority 10
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(aDrunk doVerb: theVerb theItem)
	)
)

(instance aDrunk of Prop
	(properties
		x 251
		y 141
		description {Robin}
		approachX 229
		approachY 143
		view 122
		priority 9
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb theItem &tmp temp0)
		(switch theVerb
			(verbLook
				(ego
					setHeading: (GetAngle (ego x?) (ego y?) (self x?) (self y?)) self
				)
			)
			(verbDo (Print 120 16))
			(verbTalk
				(= drunkMessage TRUE)
				(self setScript: sDrunkTalks)
			)
			(verbTaste (Print 120 17))
			(verbUse
				(switch theItem
					(iWallet (-- dollars) (Print 120 18))
					(iWhiskey
						(ego put: iWhiskey)
						(if (ego has: iRemoteControl) (= drunkMessage 2) else (= drunkMessage 3))
						(HandsOff)
						(self setScript: sDrunkDrinks)
					)
					(iWine	;EO: Not possible to have the wine here legitimately
						(Print 120 19)
					)
					(iWatch
						(Print 120 20)
						(Print 120 21)
					)
					(iBreathSpray
						(Print 120 22)
						(Print 120 23)
					)
					(iRing (Print 120 24))
					(else  (Print 120 25))
				)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
	
	(method (cue)
		(super cue:)
		(Print 120 14)
		(Print 120 15)
	)
)

(instance aRat of Prop
	(properties
		x 249
		y 128
		description {the rat}
		view 120
		loop 1
		priority 14
		signal (| ignrAct fixPriOn)
	)
)

(instance aDoor of Prop
	(properties
		x 251
		y 136
		description {the bathroom door}
		approachX 216
		approachY 130
		lookStr {The door bears the scars of too many late night brawls.}
		view 120
		loop 3
		priority 8
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(HandsOff)
				(curRoom setScript: sToToilet)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance boxes of Feature
	(properties
		x 51
		y 45
		nsTop -1
		nsBottom 91
		nsRight 102
		description {the stack of boxes}
		sightAngle 40
		lookStr {You didn't know Lefty was into boxing!}
	)
)

(instance barrels of Feature
	(properties
		x 165
		y 49
		nsLeft 128
		nsBottom 99
		nsRight 202
		description {the barrels}
		sightAngle 40
		lookStr {Those barrels are stacked better than any date you've ever had! But they do look as though they might fall over on top of you at any moment.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo (Print 120 26))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance table of Feature
	(properties
		x 94
		y 118
		nsTop 95
		nsLeft 86
		nsBottom 142
		nsRight 112
		description {the table}
		sightAngle 40
		approachX 118
		approachY 142
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(if (not (CheckItemOwner iRose))
					(Print 120 27)
				else
					(ego get: 7)
					(HandsOff)
					(curRoom setScript: sGetRose)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance transom of Feature
	(properties
		x 238
		y 135
		z 80
		nsTop 45
		nsLeft 222
		nsBottom 65
		nsRight 254
		description {the transom}
		sightAngle 40
		lookStr {A rancid glow leaks from the next room.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(3 (Print 120 28))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance theFan of Feature
	(properties
		x 26
		y 189
		z 158
		nsBottom 63
		nsRight 53
		description {the fan}
		sightAngle 40
		lookStr {This is Lefty's biggest fan!}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(3 (Print 120 28))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance theLight of Feature
	(properties
		x 278
		y 140
		z 120
		nsLeft 257
		nsBottom 41
		nsRight 300
		description {the light}
		sightAngle 40
		lookStr {Lefty thought he ordered a "Bud Light!"}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo (Print 120 28))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)
