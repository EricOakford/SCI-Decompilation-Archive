;;; Sierra Script 1.0 - (do not remove this comment)
(script# 530)
(include game.sh)
(use Main)
(use LLRoom)
(use Talker)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm530 0
)

(local
	talkCount
	drape1Open
	drape2Open
	drape3Open
	drapeState
)
(instance rm530 of LLRoom
	(properties
		lookStr {Now you understand why this is such an exclusive room. It's quiet, lovely and private.}
		picture 530
		south 525
	)
	
	(method (init)
		(LoadMany VIEW 531 1530)
		(super init:)
		(self
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 0
						319 0
						319 189
						239 189
						255 148
						208 123
						183 120
						166 106
						92 106
						53 138
						55 159
						128 170
						129 189
						0 189
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						175 141
						203 137
						226 141
						249 154
						236 163
						220 166
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						202 160
						187 168
						161 163
						150 153
						187 153
					yourself:
				)
		)
		(drape1 init: stopUpd:)
		(drape2 init: stopUpd:)
		(drape3 init: stopUpd:)
		(cactus init:)
		(cart init:)
		(light init:)
		(theTable init:)
		(chair init:)
		(iceCream init:)
		(if (!= prevRoomNum 535)
			(table1 init:)
			(table2 init:)
			(table3 init:)
			(extra1 init:)
			(extra2 init:)
			(extra3 init:)
			(extra4 init:)
			(extra5 init:)
			(ego init: normalize: posn: 151 234 edgeHit: 0)
			(michelle init: setScript: sMichelleEating)
			(drape4 init: stopUpd:)
			(self setScript: sEnter)
		else
			(michelle init:)
			(ego
				init:
				view: 531
				setLoop: 1
				posn: 260 143
				setCel: 255
				setCycle: 0
			)
			(if (not (Btst fHardDiskCafeDone))
				(Bset fHardDiskCafeDone)
				(drape4 init: setCel: 0 stopUpd:)
				(self setScript: sCloseCurtain)
			else
				(drape4 init: setCel: 255 stopUpd:)
				(self setScript: sOpenCurtain)
			)
		)
		(addToPics doit:)
		(if (!= (theMusic number?) 535)
			(theMusic number: 535 setLoop: -1 flags: 1 play:)
		)
	)
)

(instance sTalk of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(switch register
					(0
						(Say Michelle_Milken 530 0 #dispose #caller self)
					)
					(1
						(Say Michelle_Milken 530 1 #dispose #caller self)
					)
					(2
						(Say Michelle_Milken 530 2 #dispose #caller self)
					)
				)
			)
			(1
				(switch register
					(0
						(TimePrint 530 3 #at -1 185)
						(self dispose:)
					)
					(1
						(TimePrint 530 4 #at -1 185)
						(self dispose:)
					)
					(2
						(TimePrint 530 5 #at -1 185)
						(curRoom setScript: sDoMichelle)
					)
				)
			)
		)
	)
)

(instance iceCream of PicView
	(properties
		x 244
		y 109
		description {her ice cream}
		sightAngle 90
		view 530
		loop 6
		cel 5
		signal (| fixedLoop fixedCel)
	)
)

(instance extra1 of PicView
	(properties
		x 63
		y 89
		description {the couple}
		sightAngle 90
		view 530
		loop 6
		cel 1
		priority 5
		signal (| fixedLoop fixedCel fixPriOn)
	)
)

(instance extra2 of PicView
	(properties
		x 80
		y 88
		description {the couple}
		sightAngle 90
		view 530
		loop 6
		cel 2
		priority 5
		signal (| fixedLoop fixedCel fixPriOn)
	)
)

(instance extra3 of PicView
	(properties
		x 184
		y 88
		description {the couple}
		sightAngle 90
		view 530
		loop 6
		priority 4
		signal (| fixedLoop fixedCel fixPriOn)
	)
)

(instance extra4 of PicView
	(properties
		x 119
		y 78
		description {the couple}
		sightAngle 90
		view 530
		loop 6
		cel 3
		priority 4
		signal (| fixedLoop fixedCel fixPriOn)
	)
)

(instance extra5 of PicView
	(properties
		x 140
		y 77
		description {the couple}
		sightAngle 90
		view 530
		loop 6
		cel 4
		priority 5
		signal (| fixedLoop fixedCel fixPriOn)
	)
)

(instance michelle of Prop
	(properties
		x 237
		y 143
		z 40
		description {her}
		view 530
		loop 5
		priority 1
		signal (| fixedLoop fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(theTable doVerb: theVerb theItem)
	)
)

(instance chair of Feature
	(properties
		x 179
		y 144
		nsTop 126
		nsLeft 162
		nsBottom 163
		nsRight 196
		description {your chair}
		sightAngle 40
		lookStr {It's the only place you've got to sit. And you like it!}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 530 6)
			)
			(verbWalk
				(TimePrint 530 7)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance cactus of Feature
	(properties
		x 91
		y 143
		nsTop 107
		nsLeft 43
		nsBottom 180
		nsRight 140
		description {the cactus}
		sightAngle 40
		onMeCheck cGREEN
		lookStr {Isn't it funny how this cactus blooms when none of the others do.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(Say ego 530 8)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance table1 of Feature
	(properties
		x 67
		y 95
		nsTop 73
		nsLeft 45
		nsBottom 107
		nsRight 90
		description {the table}
		sightAngle 40
		lookStr {Here's another couple having a wonderful evening while you wander around all alone again!}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 530 9)
			)
			(verbTalk
				(if (not drape1Open)
					(HandsOff)
					(Say ego 530 10 #at -1 185)
					(TimePrint 530 11 #dispose drape1)
				else
					(super doVerb: theVerb theItem)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance table2 of Feature
	(properties
		x 132
		y 195
		nsTop 60
		nsLeft 109
		nsBottom 92
		nsRight 155
		description {the table}
		sightAngle 40
		lookStr {Here's another couple having a wonderful evening while you wander around New York City all alone!}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 530 9)
			)
			(verbTalk
				(if (not drape2Open)
					(HandsOff)
					(Say ego 530 12 #at -1 185)
					(TimePrint 530 13 #dispose drape2)
				else
					(super doVerb: theVerb theItem)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance table3 of Feature
	(properties
		x 187
		y 95
		nsTop 67
		nsLeft 173
		nsBottom 104
		nsRight 201
		description {the table}
		sightAngle 40
		lookStr {Here's another couple having a wonderful evening while you wander around all alone again!}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 530 9)
			)
			(verbTalk
				(if (not drape3Open)
					(HandsOff)
					(Say ego 530 14 #at -1 185 #dispose drape3)
				else
					(super doVerb: theVerb theItem)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance theTable of Feature
	(properties
		x 246
		y 140
		nsTop 75
		nsLeft 214
		nsBottom 132
		nsRight 279
		description {the table}
		sightAngle 40
		lookStr {A breathtakingly beautiful black woman sits alone at a booth, playing slowly and sensually with her dessert. It's Michelle Milken. You've found her, Larry!}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 530 15)
			)
			(verbUse
				(TimePrint 530 16)
			)
			(verbZipper
				(TimePrint 530 17)
			)
			(verbTalk
				(switch (++ talkCount)
					(1
						(Say ego 530 18 #at -1 185)
						(TimePrint 530 19)
						(Say Michelle_Milken 530 20 108)
					)
					(2
						(Say ego 530 21 #at -1 185)
						(curRoom setScript: sTalk 0 0)
					)
					(3
						(Say ego 530 22 #at -1 185)
						(Say ego 530 23 #at -1 185)
						(curRoom setScript: sTalk 0 1)
					)
					(4
						(Say ego 530 24 #at -1 185)
						(Say Michelle_Milken 530 25 108)
					)
					(else 
						(Say ego 530 26 #at -1 185)
						(curRoom setScript: sTalk 0 2)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance cart of Feature
	(properties
		x 206
		y 139
		nsTop 125
		nsLeft 188
		nsBottom 154
		nsRight 225
		description {the serving cart}
		sightAngle 40
		lookStr {It looks like this cart is the only table available.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 530 27)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance light of Feature
	(properties
		x 144
		y 140
		z 86
		nsTop 40
		nsLeft 121
		nsBottom 65
		nsRight 167
		description {the lamp}
		sightAngle 40
		onMeCheck cBLUE
		lookStr {It casts an even illumination.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance sEnter of Script
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 199 155 self)
			)
			(1
				(ego
					view: 531
					posn: 193 151
					setLoop: 0
					setCel: 0
					setCycle: 0
				)
				(= cycles 1)
			)
			(2
				(ego setLoop: 0 setCel: 1 setPri: 14)
				(= cycles 1)
			)
			(3
				(ego setLoop: 0 setCycle: EndLoop self)
			)
			(4
				(HandsOn)
				(User canControl: FALSE)
				(theIconBar disable: ICON_WALK)
				(self dispose:)
			)
		)
	)
)

(instance sCloseCurtain of Script
	
	(method (doit)
		(super doit: &rest)
		(if
			(and
				(== (theMusic number?) 535)
				(== (theMusic prevSignal?) 10)
			)
			(theMusic number: 538 play:)
		)
	)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0 (HandsOff) (= seconds 2))
			(1 (drape4 setCycle: EndLoop self))
			(2 (= seconds 4))
			(3 (curRoom newRoom: 535))
		)
	)
)

(instance sDoMichelle of Script
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setCycle: BegLoop self)
			)
			(1
				(ego normalize: setMotion: PolyPath 260 143 self)
			)
			(2
				(ego view: 531 setLoop: 1 setCel: 0 setCycle: EndLoop self)
			)
			(3
				(ego setCel: (ego cel?) setMotion: MoveTo 263 142 self)
			)
			(4 (= seconds 5))
			(5 (curRoom newRoom: 535))
		)
	)
)

(instance sMichelleEating of Script
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(switch (Random 1 4)
					(1 (= cycles (= state 1)))
					(2 (= seconds 4))
					(else 
						(= state 6)
						(= cycles 1)
					)
				)
			)
			(1 (self init:))
			(2
				(michelle setLoop: 4 setCel: 0 setCycle: EndLoop self)
			)
			(3 (= seconds (Random 2 4)))
			(4
				(michelle setCycle: BegLoop self)
			)
			(5 (= seconds 2))
			(6 (self init:))
			(7
				(michelle setLoop: 5 setCel: 0 setCycle: EndLoop self)
			)
			(8 (= seconds 2))
			(9 (self init:))
		)
	)
)

(instance sOpenCurtain of Script
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(Say Michelle_Milken 530 28 #dispose)
				(drape4 setCycle: BegLoop self)
			)
			(2 (HandsOn) (ego normalize:))
		)
	)
)

(instance drape1 of Prop
	(properties
		x 89
		y 36
		description {the curtain}
		view 530
		loop 1
		priority 7
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 530 29)
			)
			(verbDo
				(TimePrint 530 30)
			)
			(verbTalk
				(if drape1Open
					(switch (Random 1 3)
						(1 (TimePrint 530 31))
						(2 (TimePrint 530 32))
						(3 (TimePrint 530 33))
					)
				else
					(table1 doVerb: verbTalk)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
	
	(method (cue)
		(super cue:)
		(self setCycle: EndLoop)
		(table1 dispose:)
		(extra1 dispose:)
		(extra2 dispose:)
		(= drape1Open TRUE)
		(HandsOn)
		(User canControl: FALSE)
		(theIconBar disable: 0)
	)
)

(instance drape2 of Prop
	(properties
		x 134
		y 101
		description {the curtain}
		view 530
		loop 3
		signal ignrAct
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 530 30)
			)
			(verbLook
				(TimePrint 530 29)
			)
			(verbTalk
				(if drape2Open
					(drape1 doVerb: verbTalk)
				else
					(table2 doVerb: verbTalk)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
	
	(method (cue)
		(super cue:)
		(switch (++ drapeState)
			(1
				(TimePrint 530 34 #dispose self)
			)
			(2
				(self setCycle: EndLoop)
				(table2 dispose:)
				(extra4 dispose:)
				(extra5 dispose:)
				(= drape2Open TRUE)
				(HandsOn)
				(User canControl: FALSE)
				(theIconBar disable: ICON_WALK)
			)
		)
	)
)

(instance drape3 of Prop
	(properties
		x 211
		y 35
		description {the curtain}
		view 530
		loop 2
		priority 7
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 530 30)
			)
			(verbLook
				(TimePrint 530 29)
			)
			(verbTalk
				(if drape3Open
					(drape1 doVerb: verbTalk)
				else
					(table3 doVerb: verbTalk)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
	
	(method (cue)
		(super cue:)
		(TimePrint 530 35)
		(self setCycle: EndLoop)
		(table3 dispose:)
		(extra3 dispose:)
		(= drape3Open TRUE)
		(HandsOn)
		(User canControl: 0)
		(theIconBar disable: 0)
	)
)

(instance drape4 of Prop
	(properties
		x 279
		y 55
		description {the curtain}
		view 530
		priority 14
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb)
		(drape1 doVerb: theVerb)
	)
)

(instance Michelle_Milken of Talker
	(properties
		nsTop 15
		nsLeft 30
		view 1530
		loop 3
		viewInPrint 1
		name "Michelle Milken"
	)
	
	(method (init)
		(= bust talkerBust)
		(= eyes talkerEyes)
		(= mouth talkerMouth)
		(super init:)
	)
)

(instance talkerBust of View
	(properties
		view 1530
		loop 1
	)
)

(instance talkerEyes of Prop
	(properties
		nsTop 27
		nsLeft 20
		view 1530
		loop 2
		cycleSpeed 30
	)
)

(instance talkerMouth of Prop
	(properties
		nsTop 37
		nsLeft 26
		view 1530
	)
)
