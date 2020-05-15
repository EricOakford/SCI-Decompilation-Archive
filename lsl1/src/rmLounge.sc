;;; Sierra Script 1.0 - (do not remove this comment)
(script# rmLounge) ;320
(include game.sh)
(use Main)
(use Intrface)
(use LLRoom)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm320 0
)

(local
	trickTimer
	mcOnStage
	egoSitting
)
(procedure (pickAJoke &tmp joke)
	(jokes
		delete: (= joke (jokes at: (Random 1 (jokes size?))))
	)
	(switch joke
		(1
			(Print 320 37)
			(Print 320 38)
		)
		(2
			(Print 320 39)
			(Print 320 40)
		)
		(3
			(Print 320 41)
			(Print 320 42)
			(Print 320 43)
		)
		(4
			(Print 320 44)
			(Print 320 45)
		)
		(5
			(Print 320 46)
			(Print 320 47)
		)
		(6
			(Print 320 48)
			(Print 320 49)
		)
		(7
			(Print 320 50)
			(Print 320 51)
		)
		(8 (Print 320 52))
		(9
			(Print 320 53)
			(Print 320 54)
		)
		(10 (Print 320 55))
		(11 (Print 320 56))
		(12
			(Print 320 57)
			(Print 320 58)
		)
		(13
			(Print 320 59)
			(Print 320 60)
		)
		(14
			(Print 320 61)
			(Print 320 62)
		)
		(15
			(Print 320 63)
			(Print 320 64)
			(Print 320 65)
			(Print 320 66)
			(Print 320 67)
		)
		(16
			(Print 320 68)
			(Print 320 69)
		)
		(17
			(Print 320 70)
			(Print 320 71)
		)
		(18
			(Print 320 72)
			(Print 320 73)
		)
		(19
			(Print 320 74)
			(Print 320 75)
		)
		(20 (Print 320 76))
		(21
			(Print 320 77)
			(Print 320 78)
		)
		(22
			(Print 320 79)
			(Print 320 80)
		)
		(23 (Print 320 81))
		(24 (Print 320 82))
		(25
			(Print 320 83)
			(Print 320 84)
		)
		(26
			(Print 320 85)
			(Print 320 86)
		)
		(27
			(Print 320 87)
			(Print 320 88)
		)
		(28
			(Print 320 89)
			(Print 320 90)
		)
		(29
			(Print 320 91)
			(Print 320 92)
		)
		(30
			(Print 320 93)
			(Print 320 94)
		)
		(31
			(Print 320 95)
			(Print 320 96)
		)
		(32
			(Print 320 97)
			(Print 320 98)
		)
		(33
			(Print 320 99)
			(Print 320 100)
		)
		(34
			(Print 320 101)
			(Print 320 102)
		)
		(35
			(Print 320 103)
			(Print 320 104)
		)
		(36
			(Print 320 105)
			(Print 320 106)
		)
		(37
			(Print 320 107)
			(Print 320 108)
		)
		(38
			(Print 320 109)
			(Print 320 110)
		)
	)
)

(procedure (pickAZinger)
	(switch (Random 0 6)
		(0 (Print 320 111))
		(1 (Print 320 112))
		(2 (Print 320 113))
		(3 (Print 320 114))
		(4 (Print 320 115))
		(5 (Print 320 116))
		(6 (Print 320 117))
	)
)

(instance rm320 of LLRoom
	(properties
		picture rmLounge
		south rmElevatorBottom
	)
	
	(method (init &tmp i)
		(LoadMany VIEW 320)
		(LoadMany SOUND 133 320 321 322 323 324 325)
		(globalSound setVol: 127 setLoop: 1 flags: mNOPAUSE)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 189 0 0 319 0 319 189 252 189 252 179 271 172 296 172
						296 179 317 179 317 142 267 114 135 111 32 117 13 147
						53 147 53 156 2 156 2 189
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 19 189 19 178 43 168 74 168 97 178 97 189
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 148 189 148 176 167 165 195 165 219 176 219 189
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 39 133 103 133 103 138 83 138 83 143 39 143
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 100 144 172 144 172 156 100 156
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 170 123 243 123 243 135 219 135 219 138 193 138 193 135 170 135
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 232 158 302 158 302 168 232 168
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 226 142 298 142 298 150 226 150
					yourself:
				)
		)
		(ego init: x: 126 actions: egoActions)
		(if (!= prevRoomNum rmElevatorBottom) (ego y: 180))
		(if (Btst 98)
			(Bclr 98)
			(LoadMany VIEW 321 322 324)
			(= trickTimer (Random 1200 2000))
			(= mcOnStage TRUE)
			(comedian cycleSpeed: howFast init: setScript: sComedian)
			(head cycleSpeed: howFast init:)
			(drummer cycleSpeed: (+ howFast 1) init:)
			(= i 1)
			(while (<= i 38)
				(jokes addToEnd: i)
				(++ i)
			)
		else
			(Bset 98)
			(LoadMany 128 323)
			(theMusic number: 320 loop: -1 vol: 127 flags: mNOPAUSE play:)
			(= i 0)
			(while (< i (+ (theGame detailLevel:) 2))
				((dancer new:)
					cycleSpeed: howFast
					init:
					x: (+ (dancer x?) (* i 22))
					y: (- (dancer y?) (* i 2))
					setCycle: Forward
					approachVerbs: 3 4 10 11 5 2
					approachX: (+ (dancer x?) (* i 22))
					approachY: (- (dancer y?) (* i 2))
				)
				(++ i)
			)
		)
		(super init:)
		(light1 cycleSpeed: howFast setCycle: Forward init:)
		(light2 cycleSpeed: howFast setCycle: Forward init:)
		(light3 cycleSpeed: howFast setCycle: Forward init:)
		(light4 cycleSpeed: howFast setCycle: Forward init:)
		(light5 cycleSpeed: howFast setCycle: Forward init:)
		(light6 cycleSpeed: howFast setCycle: Forward init:)
		(table1 init:)
		(table2 init:)
		(table3 init:)
		(table4 init:)
		(table5 init: approachVerbs: verbDo verbUse verbZipper verbTaste)
		(table6 init:)
		(theStage init:)
	)
	
	(method (doit)
		(super doit: &rest)
		(if (> trickTimer 1) (-- trickTimer))
		(cond 
			(script)
			((and (ego mover?) (== (ego view?) 321)) (HandsOff) (curRoom setScript: sStand))
		)
	)
	
	(method (dispose)
		(jokes dispose:)
		(super dispose:)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(Print 320 0)
				(Print 320 1)
				(Print 320 2 #at -1 140)
			)
			(verbUse
				(switch theItem
					(iRose
						(ego put: iRose 0)
						(Print 320 3)
					)
					(else 
						(super doVerb: theVerb theItem)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
	
	(method (newRoom n)
		(if (== (theMusic number?) 320) (theMusic fade:))
		(super newRoom: n)
	)
)

(instance egoActions of Code	;EO: Was a class, but not in class table
	(properties)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbDo
				(if mcOnStage
					(Print 320 4)
					(Print 320 5)
				else
					(Print 320 6)
				)
			)
		)
	)
)

(instance sComedian of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 3))
			(1
				(drummer setLoop: 0 setCel: 0 stopUpd:)
				(comedian setLoop: (Random 1 2) setCycle: Forward)
				(head setCycle: Forward)
				(= seconds 2)
			)
			(2
				(comedian setLoop: (Random 1 2))
				(= seconds 2)
			)
			(3
				(head setCel: (if (Random 0 1) 7 else 0) stopUpd:)
				(comedian stopUpd:)
				(= cycles 1)
			)
			(4
				(cond 
					((== trickTimer 1) (= trickTimer 0) (comedian setScript: sMagicTrick))
					((== (jokes size?) 1) (Print 320 7) (self dispose:))
					(else (pickAJoke) (= cycles 3))
				)
			)
			(5
				(globalSound number: (Random 321 325) play:)
				(drummer setCycle: EndLoop self)
			)
			(6 (= seconds 1))
			(7
				(pickAZinger)
				(self init:)
			)
		)
	)
)

(instance sMagicTrick of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Print 320 8)
				(Print 320 9)
				(Print 320 10)
				(Print 320 11 #at -1 140)
				(Print 320 12)
				(Print 320 13)
				(drummer setLoop: 1)
				(drummerHead cycleSpeed: howFast init: setCycle: Forward)
				(Print 320 14 #at 15 80 #dispose)
				(= seconds 5)
			)
			(1
				(if modelessDialog (modelessDialog dispose:))
				(Print 320 15)
				(Print 320 16 #at 15 80 #dispose)
				(= seconds 5)
			)
			(2
				(if modelessDialog (modelessDialog dispose:))
				(Printf 320 17 dollars
					#at 15 80
					#dispose
				)
				(= seconds 5)
			)
			(3
				(if modelessDialog (modelessDialog dispose:))
				(drummer setLoop: 0)
				(drummerHead dispose:)
				(Print 320 18)
				(Print 320 19)
				((inventory at: iWallet) doVerb: verbLook)
				(comedian setScript: sComedian)
			)
		)
	)
)

(instance sSit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					view: 321
					normal: 0
					setLoop: 0
					setCel: 0
					setPri: 15
					setCycle: EndLoop self
				)
			)
			(1
				(if (and mcOnStage (not egoSitting))
					(soundFx number: 133 loop: 1 vol: 127 flags: mNOPAUSE play:)
					(= egoSitting 1)
					(whoopyCushion
						cycleSpeed: (+ howFast 1)
						init:
						setCycle: EndLoop self
					)
				else
					(HandsOn)
					(self dispose:)
				)
			)
			(2
				(whoopyCushion z: 1000 dispose:)
				(ego setLoop: 2 setCel: 0 setCycle: EndLoop self)
			)
			(3
				(Print 320 20)
				(SolvePuzzle fSitAtLounge 1)
				(ego setLoop: 0 setCel: 255)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sStand of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(table5 approachX: 296 approachY: 178)
				(ego setPri: 14 setCycle: BegLoop self)
			)
			(1
				(HandsOn)
				(NormalEgo loopW)
				(if
					(and
						(!= (CueObj client?) table5)
						((CueObj client?) approachX?)
					)
					(ego
						setMotion:
							PolyPath
							((CueObj client?) approachX?)
							(+ (ego z?) ((CueObj client?) approachY?))
							CueObj
					)
				else
					(ego
						setMotion: PolyPath ((User curEvent?) x?) ((User curEvent?) y?)
					)
				)
				(self dispose:)
			)
		)
	)
)

(instance comedian of Person
	(properties
		x 166
		y 70
		description {the comedian}
		lookStr {Evidently, HE thinks he's funny!}
		view 322
		loop 1
		signal ignrAct
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbTalk
				(Print 320 21)
				(Print 320 22)
			)
			(verbDo
				(Print 320 23)
				(Print 320 24)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance head of Prop
	(properties
		z 26
		description {the comedian}
		lookStr {Evidently, HE thinks he's funny!}
		view 322
		signal ignrAct
	)
	
	(method (init)
		(= y (comedian y?))
		(super init:)
	)
	
	(method (doit)
		(super doit: &rest)
		(if (== (comedian loop?) 2)
			(= x (+ (comedian x?) 5))
		else
			(= x (- (comedian x?) 1))
		)
	)
	
	(method (doVerb theVerb theItem)
		(comedian doVerb: theVerb theItem)
	)
)

(instance drummerHead of Prop
	(properties
		z 30
		description {Paul, the drummer}
		lookStr {It's a living!}
		view 324
		loop 2
		signal ignrAct
	)
	
	(method (init)
		(= y (drummer y?))
		(= x (drummer x?))
		(super init:)
	)
	
	(method (doVerb theVerb theItem)
		(drummer doVerb: theVerb theItem)
	)
)

(instance drummer of Prop
	(properties
		x 101
		y 70
		description {Paul, the drummer}
		lookStr {It's a living!}
		view 324
		cycleSpeed 1
	)
	
	(method (doVerb)
		(if (!= (comedian script?) sMagicTrick)
			(drummerHead cycleSpeed: howFast init: setCycle: BegLoop self)
		)
	)
	
	(method (cue)
		(super cue:)
		(Print 320 25 #at 15 80)
		(drummerHead dispose:)
	)
)

(instance dancer of Actor
	(properties
		x 120
		y 76
		description {the dancer}
		view 323
		signal ignrAct
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook (Print 320 26))
			(verbDo
				(Print 320 27)
				(Print 320 28)
			)
			(verbTalk
				(Print 320 27)
				(Print 320 28)
			)
			(verbZipper (Print 320 29))
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance whoopyCushion of Prop
	(properties
		x 299
		y 172
		view 321
		loop 1
		priority 15
		signal (| ignrAct fixPriOn)
	)
)

(instance theStage of Feature
	(properties
		x 156
		y 108
		z 31
		nsTop 76
		nsLeft 50
		nsBottom 98
		nsRight 262
		description {the stage}
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook (Print 320 30))
			(verbDo (Print 320 31))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance light1 of Prop
	(properties
		x 70
		y 141
		z 15
		description {the candle in a glass}
		view 320
		priority 10
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(Print 320 32)
				(Print 320 33)
			)
			(verbDo (Print 320 34))
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance table1 of Feature
	(properties
		x 68
		y 122
		nsTop 109
		nsLeft 51
		nsBottom 135
		nsRight 86
		description {the table}
		sightAngle 40
		lookStr {What wonderful decor! You just love furniture with a sense of humor.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo (Print 320 35))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance light2 of Prop
	(properties
		x 131
		y 153
		z 15
		description {the candle in a glass}
		view 320
		loop 2
		priority 11
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(light1 doVerb: theVerb theItem)
	)
)

(instance table2 of Feature
	(properties
		x 136
		y 137
		nsTop 125
		nsLeft 116
		nsBottom 149
		nsRight 156
		description {the table}
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem)
		(table1 doVerb: theVerb theItem)
	)
)

(instance light3 of Prop
	(properties
		x 178
		y 178
		z 15
		description {the candle in a glass}
		view 320
		loop 4
		priority 15
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(light1 doVerb: theVerb theItem)
	)
)

(instance table3 of Feature
	(properties
		x 182
		y 165
		nsTop 152
		nsLeft 165
		nsBottom 178
		nsRight 200
		description {the table}
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem)
		(table1 doVerb: theVerb theItem)
	)
)

(instance light4 of Prop
	(properties
		x 207
		y 137
		z 15
		description {the candle in a glass}
		view 320
		loop 3
		priority 9
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(light1 doVerb: theVerb theItem)
	)
)

(instance table4 of Feature
	(properties
		x 206
		y 121
		nsTop 111
		nsLeft 188
		nsBottom 131
		nsRight 225
		description {the table}
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem)
		(table1 doVerb: theVerb theItem)
	)
)

(instance light5 of Prop
	(properties
		x 283
		y 180
		z 15
		description {the candle in a glass}
		view 320
		loop 5
		cel 1
		priority 15
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(light1 doVerb: theVerb theItem)
	)
)

(instance table5 of Feature
	(properties
		x 292
		y 166
		nsTop 149
		nsLeft 266
		nsBottom 184
		nsRight 319
		description {the table}
		sightAngle 40
		approachX 296
		approachY 178
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(table1 doVerb: verbLook)
			)
			(verbDo
				(if (== (ego view?) 321)
					(Print 320 36)
				else
					(self approachX: 0)
					(self approachY: 0)
					(HandsOff)
					(curRoom setScript: sSit)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance light6 of Prop
	(properties
		x 63
		y 187
		z 15
		description {the candle in a glass}
		view 320
		loop 1
		cel 1
		priority 15
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(light1 doVerb: theVerb theItem)
	)
)

(instance table6 of Feature
	(properties
		x 61
		y 173
		nsTop 160
		nsLeft 40
		nsBottom 187
		nsRight 82
		description {the table}
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem)
		(table1 doVerb: theVerb theItem)
	)
)

(instance jokes of List
	(properties)
)
