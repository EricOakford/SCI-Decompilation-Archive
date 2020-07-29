;;; Sierra Script 1.0 - (do not remove this comment)
(script# 280)
(include game.sh)
(use Main)
(use LLRoom)
(use Polygon)
(use Feature)
(use Sound)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm280 0
)

(local
	nearBlonde
	nearRedHead
	numberCalled
	callCued
	onPhone
	girlsFighting
	[str 500]
)
(instance rm280 of LLRoom
	(properties
		lookStr {This end of the lobby is distinguished only by a bank of pay telephones against the far wall.}
		picture 280
		west 270
	)
	
	(method (init)
		(ego init: normalize:)
		(switch prevRoomNum
			(west
				(if (and (> gBlondeX 330) (> gRedHeadX 330))
					(blonde
						init:
						view: 276
						setLoop: 2
						posn: 38 84
						setCycle: Forward
						cycleSpeed: 6
						moveSpeed: 10
						setScript: sFight
					)
					(redHead init: hide:)
				else
					(blonde
						init:
						posn: (- gBlondeX 319) 82
						setLoop: gBlondeLoop
						setScript: sBlonde
					)
					(redHead
						init:
						posn: (- gRedHeadX 319) 82
						setLoop: gRedHeadLoop
						setScript: sRedHead
					)
				)
				(= style 12)
			)
			(else 
				(blonde
					init:
					x: (Random 8 22)
					y: 82
					setLoop: 4
					setScript: sBlonde
				)
				(redHead
					init:
					x: (Random 45 68)
					y: 82
					setLoop: 4
					setScript: sRedHead
				)
				(ego posn: 16 160 edgeHit: 0)
			)
		)
		(super init:)
		(self
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 0
						319 0
						319 189
						0 189
						0 187
						316 187
						315 161
						264 112
						124 112
						120 117
						83 117
						68 112
						0 112
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						216 127
						218 122
						230 119
						255 119
						263 119
						268 124
						267 132
						225 132
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						9 175
						27 147
						181 147
						198 158
						230 158
						236 161
						234 166
						214 166
						208 175
					yourself:
				)
		)
		(lostDesk init:)
		(largePlant init:)
		(smallPlant init:)
		(post init:)
		(ashtray init:)
		(theCounter init:)
		(sofa init:)
		(phones init: approachVerbs: verbLook verbDo verbUse)
		(phoneBook1 init: approachVerbs: verbLook verbDo verbUse)
		(phoneBook2 init: approachVerbs: verbLook verbDo verbUse)
		(Load SCRIPT DIALER)
		(switch currentCity
			(LOS_ANGELES
				(blonde description: {Buffi})
				(redHead description: {Muffi})
				(p1 init:)
				(p2 init:)
				(p3 init:)
				(p4 init:)
				(ph1 init:)
				(ph2 init:)
				(ph3 init:)
				(ph4 init:)
				(ad1 loop: 8 cel: 0 init:)
				(ad2 loop: 8 cel: 1 init:)
				(ad3 loop: 8 cel: 2 init:)
				(ad4 loop: 8 cel: 3 init:)
			)
			(NEW_YORK
				(blonde description: {Tracie})
				(redHead description: {Stacie})
				(thePhone
					init:
					x: 155
					y: 80
					heading: 0
					nsLeft: 147
					nsTop: 70
					nsBottom: 91
					nsRight: 163
					approachX: 138
					approachY: 113
				)
				(phoneHandle init: posn: 152 85)
				(p2 init:)
				(p3 init:)
				(p4 init:)
				(ph2 init:)
				(ph3 init:)
				(ph4 init:)
				(ad1 loop: 9 cel: 0 init:)
				(ad2 loop: 9 cel: 1 init:)
				(ad3 loop: 9 cel: 2 init:)
				(ad4 loop: 9 cel: 3 init:)
			)
			(ATLANTIC_CITY
				(blonde description: {Cherri})
				(redHead description: {Barri})
				(thePhone
					init:
					x: 176
					y: 80
					heading: 0
					nsLeft: 169
					nsTop: 70
					nsBottom: 91
					nsRight: 183
					approachX: 160
					approachY: 113
				)
				(phoneHandle init: posn: 173 85)
				(p1 init:)
				(p3 init:)
				(p4 init:)
				(ph1 init:)
				(ph3 init:)
				(ph4 init:)
				(ad1 loop: 10 cel: 0 init:)
				(ad2 loop: 10 cel: 1 init:)
				(ad3 loop: 10 cel: 2 init:)
				(ad4 loop: 10 cel: 3 init:)
			)
			(MIAMI
				(blonde description: {Dixie})
				(redHead description: {Trixie})
				(thePhone
					init:
					x: 200
					y: 80
					heading: 0
					nsLeft: 192
					nsTop: 70
					nsBottom: 91
					nsRight: 206
					approachX: 183
					approachY: 113
				)
				(phoneHandle init: posn: 196 85)
				(p1 init:)
				(p2 init:)
				(p4 init:)
				(ph1 init:)
				(ph2 init:)
				(ph4 init:)
				(ad1 loop: 11 cel: 0 init:)
				(ad2 loop: 11 cel: 1 init:)
				(ad3 loop: 11 cel: 2 init:)
				(ad4 loop: 11 cel: 3 init:)
			)
		)
		(addToPics doit:)
		(HandsOn)
		(StartTimer (Random 40 120) 0 self)
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(ego mover?)
				(== (ego view?) 550)
				(< (ego loop?) 8)
			)
			(switch (ego cel?)
				(1
					(if (!= (stepSound number?) 260)
						(stepSound number: 260 play:)
					)
				)
				(4
					(if (!= (stepSound number?) 261)
						(stepSound number: 261 play:)
					)
				)
			)
		)
		(cond 
			(script)
			((== (ego edgeHit?) WEST)
				(= gRedHeadX (redHead x?))
				(= gRedHeadLoop (redHead loop?))
				(= gBlondeX (blonde x?))
				(= gBlondeLoop (blonde loop?))
			)
			((ego inRect: -3 105 86 124)
				(if (and (not girlsFighting) (not nearBlonde))
					(= nearBlonde TRUE)
					(blonde setScript: sBlonde 0 -100)
				)
				(if (and (not girlsFighting) (not nearRedHead))
					(= nearRedHead TRUE)
					(redHead setScript: sRedHead 0 -100)
				)
			)
		)
	)
	
	(method (doVerb theVerb &tmp [str2 100])
		(switch theVerb
			(verbLook
				(Format @str2 280 0
					(switch currentCity
						(LOS_ANGELES
							{Los Angeles}
						)
						(NEW_YORK
							{New York}
						)
						(ATLANTIC_CITY
							{Atlantic City}
						)
						(MIAMI
							{Miami}
						)
					)
				)
				(TimePrint @str2)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (cue)
		(if (not onPhone)
			(announcement
				number: (Random 273 275)
				setLoop: 1
				flags: 1
				play:
			)
		)
		(StartTimer (Random 20 40) 2 self)
	)
	
	(method (newRoom n)
		(blonde dispose:)
		(redHead dispose:)
		(globalTimer dispose: delete:)
		(super newRoom: n)
	)
	
	(method (notify phoneNum)
		(= numberCalled phoneNum)
		(switch phoneNum
			(4668
				(cond 
					((!= currentCity NEW_YORK)
						(curRoom setScript: sPhone 0 50)
					)
					((not (Btst fLimoHere))
						(= callCued TRUE)
						(curRoom setScript: sPhone 0 10)
					)
					(else
						(TimePrint 280 1)
						(curRoom setScript: sPhone 0 60)
					)
				)
			)
			(4468
				(cond 
					((!= currentCity ATLANTIC_CITY)
						(curRoom setScript: sPhone 0 50)
					)
					((not (Btst fLimoHere))
						(= callCued TRUE)
						(curRoom setScript: sPhone 0 30)
					)
					(else
						(TimePrint 280 1)
						(curRoom setScript: sPhone 0 60)
					)
				)
			)
			(8544
				(cond 
					((!= currentCity MIAMI)
						(curRoom setScript: sPhone 0 50)
					)
					((and (not (Btst fFlightAvailable)) (not (Btst fLimoHere)))
						(= callCued TRUE)
						(curRoom setScript: sPhone 0 20)
					)
					(else
						(TimePrint 280 1)
						(curRoom setScript: sPhone 0 60)
					)
				)
			)
			(1272
				(cond 
					((!= currentCity MIAMI)
						(curRoom setScript: sPhone 0 50)
					)
					((not (Btst fCalledForGreenCard))
						(= callCued TRUE)
						(curRoom setScript: sPhone 0 40)
					)
					(else
						(TimePrint 280 1)
						(curRoom setScript: sPhone 0 60)
					)
				)
			)
			(3627
				(if (== currentCity MIAMI)
					(TimePrint 280 1)
					(curRoom setScript: sPhone 0 60)
				else
					(TimePrint 280 2)
					(curRoom setScript: sPhone 0 60)
				)
			)
			(-1
				(TimePrint 280 3)
				(curRoom setScript: sPhone 0 60)
			)
			(else 
				(TimePrint 280 2)
				(curRoom setScript: sPhone 0 60)
			)
		)
	)
)

(instance lostDesk of Feature
	(properties
		x 300
		y 91
		nsTop 55
		nsLeft 282
		nsBottom 128
		nsRight 319
		description {the lost and found department}
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 280 4)
			)
			(verbDo
				(TimePrint 280 5)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance largePlant of Feature
	(properties
		y 1
		nsTop 72
		nsLeft 76
		nsBottom 113
		nsRight 123
		description {the plant}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 280 6)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance smallPlant of Feature
	(properties
		y 1
		nsTop 167
		nsLeft 256
		nsBottom 189
		nsRight 319
		description {the plant}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 280 6)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance post of Feature
	(properties
		y 1
		nsLeft 225
		nsBottom 129
		nsRight 258
		description {the post}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 280 7)
			)
			(verbDo
				(TimePrint 280 8)
			)
			(verbTalk
				(TimePrint 280 9)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance ashtray of Feature
	(properties
		x 216
		y 150
		nsTop 136
		nsLeft 207
		nsBottom 164
		nsRight 226
		description {the ashtray}
		sightAngle 90
	)
	
	(method (doVerb theVerb theItem &tmp [temp0 30])
		(switch theVerb
			(verbLook
				(TimePrint 280 10)
			)
			(verbDo
				(if (not (Random 0 1))
					(TimePrint 280 11)
				else
					(TimePrint 280 12)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance theCounter of Feature
	(properties
		x 43
		y 65
		nsTop 41
		nsBottom 89
		nsRight 87
		description {the counter}
		sightAngle 90
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(Format @str 280 13
					(blonde description?)
					(redHead description?)
				)
				(TimePrint @str)
			)
			(verbDo
				(Format @str 280 14
					(blonde description?)
					(redHead description?)
				)
				(TimePrint @str)
				(TimePrint 280 15 67 -1 185)
			)
			(verbTalk
				(TimePrint 280 16)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance sofa of Feature
	(properties
		x 113
		y 153
		nsTop 127
		nsLeft 25
		nsBottom 180
		nsRight 202
		description {the sofa}
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 280 17)
			)
			(verbDo
				(TimePrint 280 18)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance blonde of Actor
	(properties
		sightAngle 40
		view 277
		signal ignrAct
		illegalBits $0000
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(Format @str 280 19 (blonde description?))
				(TimePrint @str)
			)
			(verbDo
				(Format @str 280 20 (blonde description?))
				(TimePrint @str)
			)
			(verbTalk
				(TimePrint 280 21)
				(TimePrint 280 22)
			)
			(verbUse
				(Format @str 280 23 (blonde description?))
				(TimePrint @str)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance redHead of Actor
	(properties
		sightAngle 90
		view 276
		signal ignrAct
		illegalBits $0000
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(Format @str 280 24 (redHead description?))
				(TimePrint @str)
				(TimePrint 280 25)
			)
			(verbDo
				(TimePrint 280 26)
				(TimePrint 280 27)
			)
			(verbTalk
				(TimePrint 280 21)
				(TimePrint 280 28)
			)
			(verbUse
				(Format @str 280 23 (redHead description?))
				(TimePrint @str)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance sRedHead of Script
	
	(method (changeState newState &tmp theRegister redHeadX)
		(switch (= state newState)
			(0
				(= redHeadX (redHead x?))
				(if register
					(= theRegister register)
				else
					(switch (Random 1 2)
						(1
							(= theRegister (Random 8 65))
						)
						(2
							(= theRegister (Random -100 -12))
						)
					)
				)
				(if
					(and
						(not register)
						(or
							(and
								(< theRegister (blonde x?))
								(< (blonde x?) redHeadX)
							)
							(and
								(< redHeadX (blonde x?))
								(< (blonde x?) theRegister)
							)
						)
					)
					(= cycles 1)
				else
					(redHead
						setLoop: -1
						setCycle: Forward
						setMotion: MoveTo theRegister 82 self
					)
				)
			)
			(1
				(redHead setLoop: 4 setCycle: 0 setMotion: 0)
				(= seconds (Random 3 7))
			)
			(2
				(= nearRedHead (= register 0))
				(self init:)
			)
		)
	)
)

(instance sBlonde of Script
	
	(method (changeState newState &tmp theRegister blondeX)
		(switch (= state newState)
			(0
				(= blondeX (blonde x?))
				(if register
					(= theRegister register)
				else
					(switch (Random 1 2)
						(1
							(= theRegister (Random 8 65))
						)
						(2
							(= theRegister (Random -100 -12))
						)
					)
				)
				(if
					(and
						(not register)
						(or
							(and
								(< theRegister (redHead x?))
								(< (redHead x?) blondeX)
							)
							(and
								(< blondeX (redHead x?))
								(< (redHead x?) theRegister)
							)
						)
					)
					(= cycles 1)
				else
					(blonde
						setLoop: -1
						setCycle: Forward
						setMotion: MoveTo theRegister 82 self
					)
				)
			)
			(1
				(blonde setLoop: 3 setCycle: 0 setMotion: 0)
				(= seconds (Random 3 7))
			)
			(2
				(= nearBlonde (= register 0))
				(self init:)
			)
		)
	)
)

(instance thePhone of Feature
	(properties
		description {the pay phone}
		sightAngle 40
	)
	
	(method (init)
		(self approachVerbs: verbUse verbDo verbTalk verbLook)
		(super init:)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 280 29)
			)
			(verbDo
				(if (ego has: iChange)
					(TimePrint 280 30)
					(curRoom setScript: sPhone)
				else
					(TimePrint 280 31)
				)
			)
			(verbUse
				(if (== theItem iChange)
					(curRoom setScript: sPhone)
				else
					(TimePrint 280 32)
				)
			)
			(verbTalk
				(TimePrint 280 31)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance phoneHandle of View
	(properties
		description {the pay phone}
		view 280
		cel 1
		priority 4
		signal (| ignrAct fixPriOn)
	)
	
	(method (init)
		(self
			approachVerbs: verbUse verbDo verbTalk verbLook
			approachX: (thePhone approachX?)
			approachY: (thePhone approachY?)
		)
		(super init:)
	)
	
	(method (doVerb)
		(thePhone doVerb: &rest)
	)
)

(instance p1 of Feature
	(properties
		x 153
		y 80
		nsTop 70
		nsLeft 145
		nsBottom 90
		nsRight 162
		description {the phone}
		sightAngle 40
	)
	
	(method (init)
		(self approachVerbs: verbLook verbDo verbUse)
		(super init:)
	)
	
	(method (doVerb)
		(p4 doVerb: &rest)
	)
)

(instance p2 of Feature
	(properties
		x 175
		y 80
		nsTop 70
		nsLeft 167
		nsBottom 90
		nsRight 183
		description {the phone}
		sightAngle 40
	)
	
	(method (init)
		(self approachVerbs: verbLook verbDo verbUse)
		(super init:)
	)
	
	(method (doVerb)
		(p4 doVerb: &rest)
	)
)

(instance p3 of Feature
	(properties
		x 199
		y 80
		nsTop 70
		nsLeft 193
		nsBottom 91
		nsRight 206
		description {the phone}
		sightAngle 40
	)
	
	(method (init)
		(self approachVerbs: verbLook verbDo verbUse)
		(super init:)
	)
	
	(method (doVerb)
		(p4 doVerb: &rest)
	)
)

(instance p4 of Feature
	(properties
		x 221
		y 80
		nsTop 70
		nsLeft 214
		nsBottom 91
		nsRight 228
		description {the phone}
		sightAngle 40
	)
	
	(method (init)
		(self approachVerbs: verbLook verbDo verbUse)
		(super init:)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 280 33)
			)
			(verbDo
				(TimePrint 280 34)
			)
			(verbUse
				(if (== theItem iChange)
					(TimePrint 280 35)
				else
					(TimePrint 280 32)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance ph1 of PicView
	(properties
		x 152
		y 85
		view 280
		cel 1
	)
	
	(method (init)
		(self approachVerbs: verbLook verbDo verbUse)
		(super init:)
	)
	
	(method (doVerb)
		(p4 doVerb: &rest)
	)
)

(instance ph2 of PicView
	(properties
		x 173
		y 85
		view 280
		cel 1
	)
	
	(method (init)
		(self approachVerbs: verbLook verbDo verbUse)
		(super init:)
	)
	
	(method (doVerb)
		(p4 doVerb: &rest)
	)
)

(instance ph3 of PicView
	(properties
		x 196
		y 85
		view 280
		cel 1
	)
	
	(method (init)
		(self approachVerbs: verbLook verbDo verbUse)
		(super init:)
	)
	
	(method (doVerb)
		(p4 doVerb: &rest)
	)
)

(instance ph4 of PicView
	(properties
		x 218
		y 85
		view 280
		cel 1
	)
	
	(method (init)
		(self approachVerbs: verbLook verbDo verbUse)
		(super init:)
	)
	
	(method (doVerb)
		(p4 doVerb: &rest)
	)
)

(instance ad1 of PicView
	(properties
		x 21
		y 9
		nsBottom 14
		nsRight 47
		description {the sign}
		sightAngle 90
		view 263
		priority 4
		signal (| ignrAct ignrHrz fixedLoop fixedCel fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(switch currentCity
					(LOS_ANGELES
						(TimePrint 280 36)
					)
					(NEW_YORK
						(TimePrint 280 37)
					)
					(ATLANTIC_CITY
						(TimePrint 280 38)
					)
					(MIAMI
						(TimePrint 280 39)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance ad2 of PicView
	(properties
		x 98
		y 9
		nsLeft 62
		nsBottom 15
		nsRight 134
		description {the sign}
		sightAngle 90
		view 263
		priority 4
		signal (| ignrAct ignrHrz fixedLoop fixedCel fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(switch currentCity
					(LOS_ANGELES
						(TimePrint 280 40)
					)
					(NEW_YORK
						(TimePrint 280 41
							#mode teJustCenter
						)
					)
					(ATLANTIC_CITY
						(TimePrint 280 42)
					)
					(MIAMI
						(SolvePuzzle 1 fLearnMiamiLimoNumber)
						(TimePrint 280 43)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance ad3 of PicView
	(properties
		x 183
		y 11
		nsLeft 148
		nsBottom 16
		nsRight 221
		description {the sign}
		sightAngle 90
		view 263
		priority 4
		signal (| ignrAct ignrHrz fixedLoop fixedCel fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(switch currentCity
					(LOS_ANGELES
						(TimePrint 280 44)
					)
					(NEW_YORK
						(TimePrint 280 45)
					)
					(ATLANTIC_CITY
						(TimePrint 280 46)
					)
					(MIAMI
						(TimePrint 280 47)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance ad4 of PicView
	(properties
		x 314
		y 23
		nsLeft 295
		nsBottom 31
		nsRight 319
		description {the sign}
		sightAngle 90
		view 263
		priority 4
		signal (| ignrAct ignrHrz fixedLoop fixedCel fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(switch currentCity
					(LOS_ANGELES
						(TimePrint 280 48)
					)
					(NEW_YORK
						(TimePrint 280 49)
					)
					(ATLANTIC_CITY
						(TimePrint 280 50)
					)
					(MIAMI
						(TimePrint 280 51)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance stepSound of Sound)

(instance announcement of Sound)

(instance phoneBook1 of Feature
	(properties
		x 169
		y 91
		nsTop 86
		nsLeft 160
		nsBottom 97
		nsRight 176
		description {the telephone directory}
		sightAngle 40
		onMeCheck cBLUE
		approachX 169
		approachY 112
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 280 52)
			)
			(verbDo
				(TimePrint 280 53)
				(TimePrint 280 54)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance phoneBook2 of Feature
	(properties
		x 207
		y 91
		nsTop 87
		nsLeft 200
		nsBottom 96
		nsRight 214
		description {the telephone directory}
		sightAngle 40
		onMeCheck cBLUE
		approachX 207
		approachY 112
	)
	
	(method (doVerb theVerb theItem)
		(phoneBook1 doVerb: theVerb &rest)
	)
)

(instance sFight of Script
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(= girlsFighting TRUE)
				(= cycles 2)
			)
			(1
				(HandsOff)
				(blonde setMotion: MoveTo -40 84 self)
			)
			(2
				(blonde
					view: 277
					posn: -40 82
					setScript: sBlonde
					ignoreActors: 1
					setLoop: 0
				)
				(redHead
					view: 276
					posn: -70 82
					setLoop: 0
					setScript: sRedHead
					ignoreActors: 1
					show:
				)
				(HandsOn)
				(= girlsFighting FALSE)
				(self dispose:)
			)
		)
	)
)

(instance sPhone of Script
	
	(method (changeState newState &tmp [temp0 2] [temp2 80])
		(switch (= state newState)
			(0
				(HandsOff)
				(= onPhone TRUE)
				(if (not register)
					(ego
						view: 281
						setLoop: 0
						setCel: 0
						cycleSpeed: 10
						setCycle: CycleTo 4 1 self
					)
				else
					(self changeState: register)
				)
			)
			(1
				(theMusic2 number: 36 loop: 1 play:)
				(phoneHandle hide:)
				(ego setCycle: EndLoop self)
			)
			(2
				(User canInput: TRUE)
				((ScriptID DIALER 0) init: 1)
			)
			(10
				(SolvePuzzle 3 fLimoHere)
				(Bset fLimoAvailable)
				(TimePrint 280 55 #at -1 20)
				(= ticks 30)
			)
			(11
				(TimePrint 280 56)
				(= ticks 30)
			)
			(12
				(TimePrint 280 57 #at -1 20)
				(= ticks 30)
			)
			(13
				(TimePrint 280 58)
				(= ticks 30)
			)
			(14
				(TimePrint 280 59 #at -1 20)
				(= ticks 30)
			)
			(15
				(TimePrint 280 60)
				(= ticks 30)
			)
			(16
				(TimePrint 280 61 #at -1 20)
				(= ticks 30)
			)
			(17
				(TimePrint 280 62 #at -1 185)
				(self changeState: 60)
			)
			(20
				(SolvePuzzle 3 fLimoHere)
				(Bset fLimoAvailable)
				(TimePrint 280 63 #at -1 20)
				(= ticks 30)
			)
			(21
				(TimePrint 280 64)
				(= ticks 30)
			)
			(22
				(TimePrint 280 65 #at -1 20)
				(= ticks 30)
			)
			(23
				(TimePrint 280 66)
				(= ticks 30)
			)
			(24
				(TimePrint 280 67 #at -1 20)
				(TimePrint 280 68 #at -1 20)
				(= ticks 30)
			)
			(25
				(TimePrint 280 62 #at -1 185)
				(self changeState: 60)
			)
			(30
				(SolvePuzzle 3 fLimoHere)
				(Bset fLimoAvailable)
				(TimePrint 280 69 #at -1 20)
				(= ticks 30)
			)
			(31
				(TimePrint 280 70)
				(= ticks 30)
			)
			(32
				(TimePrint 280 71 #at -1 20)
				(= ticks 30)
			)
			(33
				(TimePrint 280 62 #at -1 185)
				(self changeState: 60)
			)
			(40
				(SolvePuzzle 7 12)
				(Bset 38)
				(TimePrint 280 72)
				(= ticks 30)
			)
			(41
				(TimePrint 280 73)
				(= ticks 30)
			)
			(42
				(TimePrint 280 74)
				(= ticks 30)
			)
			(43
				(TimePrint 280 62 #at -1 185)
				(self changeState: 60)
			)
			(50
				(TimePrint 280 75)
				(= ticks 30)
			)
			(51
				(Format @temp2 280 76
					(switch numberCalled
						(3627 {Miami})
						(4668 {New York City})
						(4468 {Atlantic City})
						(8544 {Miami})
						(1272 {Miami})
					)
				)
				(TimePrint @temp2 #at -1 185)
				(= ticks 30)
			)
			(52 (self changeState: 60))
			(60 (= ticks 10))
			(61
				(ego setCycle: CycleTo 4 -1 self)
			)
			(62
				(phoneHandle show:)
				(ego setCycle: BegLoop self)
			)
			(63
				(theMusic2 number: 37 loop: 1 play:)
				(if (and callCued (not (-- numQuarters)))
					(= numQuarters -1)
					(ego put: iChange)
				)
				(ego setLoop: 3 normalize:)
				(= callCued 0)
				(= onPhone (= register 0))
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance phones of PicView
	(properties
		x 185
		y 1
		z -100
		description {the pay phones}
		approachX 180
		approachY 112
		view 280
		priority 0
		signal (| ignrAct fixPriOn)
	)
	
	(method (onMe)
		(return FALSE)
	)
)
