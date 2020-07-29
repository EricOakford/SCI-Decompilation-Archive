;;; Sierra Script 1.0 - (do not remove this comment)
(script# 525)
(include game.sh)
(use Main)
(use LLRoom)
(use Door)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Sound)
(use Motion)
(use Invent)
(use User)
(use Actor)
(use System)

(public
	rm525 0
)

(local
	evtX
	evtY
)
(instance rm525 of LLRoom
	(properties
		picture 525
		east 530
		south 510
	)
	
	(method (init)
		(LoadMany VIEW 525 527 510 526)
		(Load SOUND 526)
		(ego normalize:)
		(daDoor init: approachVerbs: verbDo)
		(door init:)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 0
						319 0
						319 189
						235 189
						252 166
						229 155
						193 166
						166 155
						122 170
						114 164
						96 177
						64 189
						0 189
					yourself:
				)
		)
		(switch prevRoomNum
			(520
				(ego posn: 210 161 init:)
				(if (> ((Inventory at: iMembershipTape) state?) HDCgotTape)
					(HandsOff)
					(curRoom setScript: sToHollerith)
				else
					(HandsOn)
				)
			)
			(east
				(HandsOn)
				(ego posn: 238 162 setHeading: 270 edgeHit: 0)
			)
			(else 
				(= currentCity NEW_YORK)
				(ego posn: 146 237 setHeading: 45 get: 14 init:)
				(curRoom setScript: sEnter)
			)
		)
		(super init:)
		(fatLady init: setScript: sFatLadyEating)
		(tapeReader init: approachVerbs: verbDo verbUse)
		(cactus init:)
		(tables init:)
		(SprattFamilyTable init:)
		(larryTable init: approachVerbs: verbDo)
		(baldMan init:)
		(man1 init:)
		(man2 init:)
		(man3 init:)
		(woman1 init:)
		(addToPics doit:)
	)
	
	(method (doit)
		(super doit: &rest)
		(cond 
			(script)
			(
				(and
					(== (ego view?) 526)
					(ego mover?)
					(User canControl:)
					(User canInput:)
				)
				(= evtX ((User curEvent?) x?))
				(= evtY ((User curEvent?) y?))
				(HandsOff)
				(curRoom setScript: sStandUp)
			)
			((IsObjectOnControl ego cGREEN)
				(HandsOff)
				(self setScript: sExitSouth)
			)
		)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(if (!= (ego view?) 526)
					(TimePrint 525 0)
				else
					(TimePrint 525 1)
				)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
	
	(method (newRoom n)
		(super newRoom: n)
		(if (== n 530) (theMusic fade:))
	)
)

(instance sToHollerith of Script

	(method (changeState newState)
		(switch (= state newState)
			(0 (= ticks 123))
			(1
				(ego
					setMotion: PolyPath (door approachX?) (door approachY?) self
				)
			)
			(2
				((curRoom obstacles?) dispose:)
				(= cycles 2)
			)
			(3
				(curRoom
					addObstacle:
						((Polygon new:)
							type: PBarredAccess
							init:
								117 167
								117 179
								98 179
								98 167
							yourself:
						)
				)
				(door locked: FALSE doVerb: verbDo)
				(self dispose:)
			)
		)
	)
)

(instance sEnter of Script
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 196 185 self)
			)
			(1 (HandsOn) (self dispose:))
		)
	)
)

(instance sMichelle of Script
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(michelle setCycle: Forward setMotion: MoveTo 190 191 self)
			)
			(1
				(SolvePuzzle 3)
				(TimePrint 525 2)
				(michelle setMotion: MoveTo 216 163 self)
			)
			(2
				(michelle
					setLoop: 1
					posn: 207 162
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(3
				(screen init: setCycle: Forward)
				(= seconds (Random 3 6))
			)
			(4
				(michelle
					setCycle: BegLoop self
					illegalBits: 0
					ignoreActors: TRUE
				)
			)
			(5
				(screen dispose:)
				(michelle
					setLoop: 0
					setCycle: Walk
					setMotion: MoveTo 242 164 self
				)
			)
			(6
				(door setCycle: EndLoop self)
			)
			(7
				(michelle setMotion: MoveTo 308 144 self)
			)
			(8
				(door setCycle: BegLoop self)
			)
			(9
				(door stopUpd:)
				(michelle dispose:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sFatLadyEating of Script

	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(fatLady setCel: 0)
				(if (not (Random 0 2))
					(= seconds (Random 2 6))
				else
					(= cycles 1)
				)
			)
			(1 (fatLady setCycle: EndLoop self))
			(2
				(slobSound play:)
				(self init:)
			)
		)
	)
)

(instance sSitDown of Script
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(ego view: 526 setLoop: 0 setCycle: EndLoop self)
			)
			(1
				(if (Btst fMetMichelle)
					(HandsOn)
				else
					(Bset fMetMichelle)
					(User canInput: TRUE)
					(theIconBar enable: ICON_LOOK ICON_TALK)
					(theIconBar curIcon: (theIconBar at: ICON_LOOK))
					(theGame setCursor: ((theIconBar curIcon?) cursor?))
					(michelle init: setScript: sMichelle)
				)
				(self dispose:)
			)
		)
	)
)

(instance sStandUp of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (ego setCycle: BegLoop self))
			(1
				(HandsOn)
				(ego normalize:)
				(if (!= (CueObj client?) larryTable)
					(if
						(and
							((CueObj client?) approachX?)
							(!= ((theIconBar curIcon?) cursor?) 0)
						)
						(ego
							setMotion:
								PolyPath
								((CueObj client?) approachX?)
								(+ (ego z?) ((CueObj client?) approachY?))
								CueObj
						)
					else
						(ego setMotion: PolyPath evtX evtY)
					)
				)
				(self dispose:)
			)
		)
	)
)

(instance sExitSouth of Script
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(ego
					setMotion: MoveTo (- (ego x?) 80) (+ (ego y?) 50) self
				)
			)
			(1 (curRoom newRoom: 510))
		)
	)
)

(instance door of Door
	(properties
		x 264
		y 165
		description {the door}
		sightAngle 90
		approachX 231
		approachY 164
		view 525
		priority 3
		signal (| ignrAct fixPriOn)
		entranceTo 530
		locked 1
		openSnd 0
		closeSnd 0
		moveToX 292
		moveToY 154
		exitType 0
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(if (Btst fMetMichelle)
					(TimePrint 525 3)
				else
					(TimePrint 525 4)
				)
			)
			(verbDo
				(if locked
					(TimePrint 525 5)
				else
					(super doVerb: verbDo)
				)
			)
			(verbTalk
				(Say ego 525 6)
			)
			(verbUse
				(switch theItem
					(iMembershipTape
						(TimePrint 525 7)
					)
					(iGoldCard
						(TimePrint 525 8)
					)
					(else 
						(super doVerb: theVerb theItem &rest)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance michelle of Actor
	(properties
		x 128
		y 247
		description {Michelle Milken}
		lookStr {It's her, Larry! Watch her carefully; perhaps you could follow her!}
		view 527
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbTalk
				(Say ego 525 9)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance fatLady of Prop
	(properties
		x 39
		y 146
		description {the fat lady}
		sightAngle 40
		lookStr {You presume she's the one who sings when it's over.}
		view 525
		loop 7
		priority 1
		signal (| ignrAct fixedLoop fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 525 10)
			)
			(verbTalk
				(Say ego 525 11)
				(TimePrint 525 12)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance screen of Prop
	(properties
		x 192
		y 132
		description {the monitor}
		sightAngle 40
		view 510
		loop 2
		priority 15
		signal (| ignrAct fixedLoop fixPriOn)
	)
)

(instance man1 of PicView
	(properties
		x 159
		y 70
		description {the bald man}
		sightAngle 90
		lookStr {That's one of the few people in this huge cafe.}
		view 525
		loop 5
		priority 4
		signal (| ignrAct ignrHrz fixedLoop fixPriOn)
	)
)

(instance man2 of PicView
	(properties
		x 73
		y 70
		description {the lonely man}
		sightAngle 90
		lookStr {That's one of the few people in this huge cafe.}
		view 525
		loop 5
		cel 1
		priority 4
		signal (| ignrAct ignrHrz fixedLoop fixPriOn)
	)
)

(instance man3 of PicView
	(properties
		x 116
		y 86
		description {the lonely man}
		sightAngle 90
		lookStr {That's one of the few people in this huge cafe.}
		view 525
		loop 6
		priority 4
		signal (| ignrAct ignrHrz fixedLoop fixPriOn)
	)
)

(instance woman1 of PicView
	(properties
		x 21
		y 92
		description {the lonely woman}
		sightAngle 90
		lookStr {That's one of the few people in this huge cafe.}
		view 525
		loop 6
		cel 1
		priority 5
		signal (| ignrAct ignrHrz fixedLoop fixPriOn)
	)
)

(instance baldMan of PicView
	(properties
		x 62
		y 161
		description {Jack}
		sightAngle 90
		view 525
		loop 2
		priority 4
		signal (| ignrAct ignrHrz fixedLoop fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 525 13)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance SprattFamilyTable of Feature
	(properties
		x 52
		y 160
		nsTop 143
		nsLeft 14
		nsBottom 177
		nsRight 90
		description {their table}
		sightAngle 40
		lookStr {This table seems to be occupied. Extremely occupied!}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 525 14)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance larryTable of Feature
	(properties
		x 133
		y 148
		nsTop 124
		nsLeft 90
		nsBottom 162
		nsRight 170
		description {your table}
		sightAngle 40
		approachX 114
		approachY 163
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(switch ((Inventory at: iMembershipTape) state?)
					(HDCgotTape
						(TimePrint 525 15)
					)
					(else 
						(TimePrint 525 16)
						(TimePrint 525 17 #at -1 185)
					)
				)
			)
			(verbDo
				(if (and (User canInput:) (User canControl:))
					(HandsOff)
					(curRoom setScript: sSitDown)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance tables of Feature
	(properties
		x 102
		y 57
		nsBottom 115
		nsRight 204
		description {the tables}
		sightAngle 40
		lookStr {What a huge cafe to have so few customers.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 525 18)
			)
			(verbTalk
				(TimePrint 525 19)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance cactus of Feature
	(properties
		x 294
		y 162
		nsTop 137
		nsLeft 271
		nsBottom 188
		nsRight 318
		description {the cactus}
		sightAngle 40
		lookStr {Insert your favorite cactus and "prick" joke here.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(Say ego 525 20)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance tapeReader of Feature
	(properties
		x 195
		y 142
		nsTop 120
		nsLeft 176
		nsBottom 158
		nsRight 215
		description {the tape reader}
		sightAngle 40
		approachX 210
		approachY 161
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 525 21)
			)
			(verbDo
				(if (and (User canInput:) (User canControl:))
					(HandsOff)
					(curRoom newRoom: 520)
				)
			)
			(verbUse
				(switch theItem
					(iMembershipTape
						(self doVerb: verbDo)
					)
					(else 
						(super doVerb: theVerb theItem &rest)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance daDoor of Feature
	(properties
		x 246
		y 125
		nsTop 92
		nsLeft 227
		nsBottom 159
		nsRight 266
		description {the door}
		sightAngle 40
		onMeCheck cBLUE
		approachX 231
		approachY 164
	)
	
	(method (doVerb theVerb theItem)
		(door doVerb: theVerb theItem)
	)
)

(instance slobSound of Sound
	(properties
		flags mNOPAUSE
		number 526
	)
)
