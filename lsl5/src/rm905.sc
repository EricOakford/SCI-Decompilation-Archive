;;; Sierra Script 1.0 - (do not remove this comment)
(script# 905)
(include game.sh)
(use Main)
(use LLRoom)
(use Door)
(use Intrface)
(use Talker)
(use PolyPath)
(use Polygon)
(use Feature)
(use Motion)
(use Invent)
(use User)
(use Actor)
(use System)

(public
	rm905 0
)

(local
	doilyMessage
	local1
	formQuestion
	sittingDown
	local4
	phoneNum
	local6
	local7
	cleanTeethMessage
	local9
	local10
)
(procedure (SetEgoLoop theLoop)
	(if (not theLoop)
		(= theLoop -1)
	)
	(if (== ((Inventory at: iDoily) state?) 1)
		(ego setLoop: theLoop normalize: 908)
	else
		(ego setLoop: theLoop normalize: 550)
	)
)

(instance rm905 of LLRoom
	(properties
		lookStr {You are inside the waiting room of Doc Pulliam's Dental Hygiene Heaven. You love the carpet motif; in fact, you feel sure you seen something a lot like it before... somewhere.}
		picture 905
		east 910
		south 900
	)
	
	(method (init)
		(InFirstPerson 0)
		(SetFFRoom 0)
		(Load VIEW 907)
		(Load VIEW 908)
		(Load VIEW 906)
		(ego init: normalize:)
		(HandsOn)
		(switch prevRoomNum
			(900
				(self setScript: sEnterFromOutside)
			)
			(910 (ego edgeHit: 0))
			(915
				(door entranceTo: 915)
				(ego edgeHit: 0)
			)
			(else 
				(= currentCity MIAMI)
				(ego posn: 172 160)
			)
		)
		(super init:)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						319 52
						205 117
						199 113
						180 119
						171 105
						151 106
						69 116
						62 120
						63 125
						56 134
						48 166
						37 173
						38 187
						317 187
						274 164
						282 160
						263 156
						211 129
						231 116
						319 114
						319 189
						0 189
						0 0
						319 0
					yourself:
				)
		)
		(nurseWindow init: approachVerbs: verbLook verbTalk verbDo)
		(glass init: approachVerbs: verbLook verbTalk verbDo stopUpd:)
		(if (not (Btst fDentistClosed))
			(girl init:)
		)
		(phone init: approachVerbs: verbDo)
		(toothTable init: approachVerbs: verbDo verbLook)
		(toothTable2 init: approachVerbs: verbDo verbLook)
		(degrees init:)
		(rTable init: approachVerbs: verbDo)
		(lTable init: approachVerbs: verbDo)
		(lChair init: approachVerbs: verbDo)
		(rChair init:)
		(rChair2 init:)
		(pic2 init:)
		(palm init: approachVerbs: verbDo verbLook)
		(palm2 init: approachVerbs: verbDo verbLook)
		(lipPic init:)
		(magazines init: approachVerbs: verbDo verbLook verbUse)
		(thePhone init: approachVerbs: verbDo)
		(if (and (not (Btst fFakedInjury)) (not (ego has: iDoily)))
			(laceDoily init: stopUpd: approachVerbs: verbDo)
		)
		(door init:)
		(theMusic number: 905 setLoop: -1 play:)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			(script)
			((IsObjectOnControl ego cBLUE)
				(cond 
					((== (ego view?) 550)
						(self setScript: sExitToOutside)
					)
					((not doilyMessage)
						(= doilyMessage TRUE)
						(TimePrint 905 2)
					)
				)
			)
			((IsObjectOnControl ego cCYAN)
				(= doilyMessage FALSE)
			)
		)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(if (OneOf (ego view?) 906 907)
					(TimePrint 905 0)
				else
					(super doVerb: theVerb theItem &rest)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
	
	(method (cue)
		(if cleanTeethMessage
			(TimePrint 905 1)
			(= cleanTeethMessage FALSE)
			(HandsOn)
		else
			(SetEgoLoop loopS)
		)
	)
	
	(method (notify param1)
		(= phoneNum param1)
	)
)

(instance magazines of Feature
	(properties
		x 54
		y 81
		nsTop 60
		nsLeft 41
		nsBottom 103
		nsRight 67
		description {the magazines}
		sightAngle 40
		approachX 62
		approachY 120
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbUse
				(switch theItem
					(iMagazine
						(TimePrint 905 3)
						(ego put: iMagazine)
						(Bset fPutMagazineOnTable)
					)
					(else 
						(super doVerb: theVerb theItem &rest)
					)
				)
			)
			(verbLook
				(TimePrint 905 4)
			)
			(verbDo
				(TimePrint 905 5)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance palm of Feature
	(properties
		x 17
		y 232
		z 100
		nsTop 75
		nsBottom 189
		nsRight 34
		description {the palm tree}
		sightAngle 40
		onMeCheck cGREEN
		approachX 40
		approachY 184
		lookStr {You wonder if that palm tree will ever require a "root canal?"}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 905 6)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance palm2 of Feature
	(properties
		x 184
		y 74
		nsTop 34
		nsLeft 163
		nsBottom 115
		nsRight 205
		sightAngle 40
		onMeCheck cGREEN
		approachX 177
		approachY 117
	)
	
	(method (doVerb)
		(palm doVerb: &rest)
	)
)

(instance lipPic of Feature
	(properties
		x 14
		y 67
		nsTop 41
		nsBottom 93
		nsRight 28
		description {the painting}
		sightAngle 40
		lookStr {You've always been a big fan of the "Happy Tooth School" of art.}
	)
)

(instance pic2 of Feature
	(properties
		x 277
		y 77
		nsTop 49
		nsLeft 257
		nsBottom 106
		nsRight 298
		description {the painting}
		sightAngle 40
		lookStr {You've always been a big fan of the "Happy Tooth School" of art.}
	)
)

(instance degrees of Feature
	(properties
		x 314
		y 84
		nsTop 59
		nsLeft 310
		nsBottom 110
		nsRight 319
		description {Doc's diplomas}
		sightAngle 40
		lookStr {Funny. You've never heard of a dentist specializing in "String and Doorknob" dentistry. And where exactly is the "School of Hard Knockers?"}
	)
)

(instance toothTable of Feature
	(properties
		x 190
		y 97
		nsTop 82
		nsLeft 177
		nsBottom 113
		nsRight 203
		description {the table}
		sightAngle 40
		approachX 177
		approachY 117
		lookStr {You can tell that Doc's wife decorated this place. She's suffering from terminal cuteness!}
	)
)

(instance lChair of Feature
	(properties
		x 43
		y 137
		nsTop 100
		nsLeft 28
		nsBottom 134
		nsRight 59
		description {the chair}
		sightAngle 40
		approachX 62
		approachY 127
		lookStr {This chair looks just right!}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(curRoom setScript: sChair)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance rChair of Feature
	(properties
		x 282
		y 135
		nsTop 112
		nsLeft 265
		nsBottom 159
		nsRight 300
		description {the chair}
		sightAngle 40
		lookStr {This chair looks too hard.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 905 7)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance rChair2 of Feature
	(properties
		x 246
		y 123
		nsTop 103
		nsLeft 225
		nsBottom 143
		nsRight 268
		description {the chair}
		sightAngle 40
		lookStr {This chair looks too soft.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 905 8)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance lTable of Feature
	(properties
		x 32
		y 147
		nsTop 125
		nsLeft 15
		nsBottom 170
		nsRight 50
		description {the table}
		sightAngle 40
		approachX 59
		approachY 129
		lookStr {This table contains a telephone, placed here for the convenience of Doc's customers.}
	)
	
	(method (doVerb)
		(phone doVerb: &rest)
	)
)

(instance rTable of Feature
	(properties
		x 301
		y 159
		nsTop 141
		nsLeft 284
		nsBottom 177
		nsRight 319
		description {the table}
		sightAngle 40
		approachX 287
		approachY 159
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(if (ego has: iDoily)
					(TimePrint 905 9)
				else
					(TimePrint 905 10)
				)
			)
			(verbDo
				(cond 
					((ego has: iDoily)
						(TimePrint 905 11)
					)
					((OneOf (ego view?) 906 907)
						(curRoom setScript: sChair 0 self)
					)
					((IsObject laceDoily)
						(laceDoily doVerb: theVerb)
					)
				)
			)
		)
	)
)

(instance glass of Prop
	(properties
		x 115
		y 84
		approachX 134
		approachY 109
		view 905
		priority 6
		signal (| ignrAct fixPriOn fixedLoop)
	)
	
	(method (doVerb)
		(nurseWindow doVerb: &rest)
	)
)

(instance girl of Prop
	(properties
		x 138
		y 75
		description {the receptionist}
		lookStr {You thought this might be the babe you've been searching for. Obviously, she's not. Not even you could consider this one sexy (and you know what low standards YOU have!).}
		view 909
	)
)

(instance phone of Prop
	(properties
		x 32
		y 156
		z 20
		nsTop 130
		nsLeft 24
		nsBottom 142
		nsRight 41
		description {the waiting room telephone}
		sightAngle 40
		approachX 59
		approachY 129
		lookStr {The long table contains a telephone, placed here for the convenience of Doc's customers.}
		view 905
		loop 2
		signal (| ignrAct fixPriOn fixedLoop)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(cond 
					((OneOf (ego view?) 906 907)
						(if (== (ego loop?) 3)
							(curRoom setScript: sHangUp)
						else
							((curRoom script?) cue:)
						)
					)
					((curRoom script?)
						(super doVerb: theVerb theItem &rest)
					)
					(else
						(HandsOff)
						(= sittingDown TRUE)
						(ego view: 550)
						(curRoom setScript: sChair)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance Alberta of Talker
	(properties
		nsTop 33
		nsLeft 22
		view 1909
		loop 3
		viewInPrint 1
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
		view 1909
		loop 1
	)
)

(instance talkerEyes of Prop
	(properties
		nsTop 35
		nsLeft 34
		view 1909
		loop 2
	)
)

(instance talkerMouth of Prop
	(properties
		nsTop 44
		nsLeft 33
		view 1909
	)
)

(instance sEnterFromOutside of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					posn: 163 237
					setHeading: 0
					setMotion: MoveTo 163 179 self
				)
			)
			(1
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sExitToOutside of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(HandsOff)
				(theMusic fade:)
				(ego setMotion: MoveTo (ego x?) (+ (ego y?) 50) self)
			)
			(1 (curRoom newRoom: 900))
		)
	)
)

(instance sHangUp of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					setLoop: 3
					setCel: 255
					cycleSpeed: 6
					setCycle: BegLoop self
				)
			)
			(1
				(ego setLoop: 1 setCel: 255)
				(HandsOn)
				(user canControl: FALSE)
				(theIconBar disable: ICON_WALK)
				(self dispose:)
			)
		)
	)
)

(instance sChair of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(== state 2)
				((User curEvent?) type?)
				((User curEvent?) message?)
				(== (theIconBar curIcon?) (theIconBar at: ICON_WALK))
				(or
					(== ((User curEvent?) message?) verbWalk)
					(& ((User curEvent?) type?) direction)
				)
			)
			(= local7 1)
			(self init:)
		)
	)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(HandsOff)
				(if (OneOf (ego view?) 906 907)
					(ego setLoop: 1 cycleSpeed: 6 setCycle: BegLoop self)
				else
					(if (not local10)
						(TimePrint 905 12 67 -1 20 108)
						(= local10 1)
					)
					(ego sitting: 1)
					(= state 1)
					(if (== ((Inventory at: iDoily) state?) 1)
						(ego view: 907)
					else
						(ego view: 906)
					)
					(ego
						heading: 90
						setLoop: 1
						cycleSpeed: 6
						setCycle: EndLoop self
					)
					(palm approachVerbs: verbNone)
					(palm2 approachVerbs: verbNone)
					(magazines approachVerbs: verbNone)
					(nurseWindow approachVerbs: verbNone)
					(glass approachVerbs: verbNone)
					(laceDoily approachVerbs: verbNone)
					(rTable approachVerbs: verbNone)
					(phone approachVerbs: verbNone)
					(girl approachVerbs: verbNone)
					(door approachVerbs: verbNone)
				)
			)
			(1
				(palm approachVerbs: verbDo verbLook verbUse)
				(palm2 approachVerbs: verbDo verbLook verbUse)
				(magazines approachVerbs: verbDo verbLook verbUse)
				(nurseWindow approachVerbs: verbDo verbTalk)
				(glass approachVerbs: verbDo verbTalk)
				(laceDoily approachVerbs: verbDo)
				(rTable approachVerbs: verbDo)
				(phone approachVerbs: verbDo)
				(girl approachVerbs: verbDo)
				(door approachVerbs: verbDo verbLook verbUse)
				(SetEgoLoop 0)
				(HandsOn)
				(if (IsObject register)
					(MouseClaimed register 3)
				)
				(if local7
					(= local7 0)
					(ego
						setMotion: PolyPath ((User curEvent?) x?) ((User curEvent?) y?)
					)
				)
				(= register 0)
				(ego sitting: 0)
				(self dispose:)
			)
			(2
				(HandsOn)
				(user canControl: FALSE)
				(= register 0)
				(if sittingDown (self setScript: sPhone))
			)
			(3
				(= state 1)
				(self setScript: sPhone)
				(= cycles 1)
			)
		)
	)
)

(instance sPhone of Script
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(HandsOff)
				(= local6 1)
				(if (== ((Inventory at: iDoily) state?) 1)
					(ego view: 907)
				else
					(ego view: 906)
				)
				(ego
					setLoop: 3
					setCel: 0
					cycleSpeed: 6
					setCycle: EndLoop self
				)
			)
			(1
				((ScriptID 20 0) init: 1 self)
			)
			(2 (= cycles 2))
			(3
				(HandsOff)
				(switch phoneNum
					(8544
						(TimePrint 905 13)
						(Say ego 905 14)
						(cond 
							((not (Btst fDentistClosed))
								(TimePrint 905 15)
							)
							((not (Btst fLimoHere))
								(Bset fLimoHere)
								(TimePrint 905 16)
							)
							(else
								(TimePrint 905 17)
							)
						)
					)
					(3627
						(if (not (Bset fCalledDentist))
							(Bset fCalledDentist)
							(if (Btst fDentistClosed)
								(Say Alberta 905 18 108)
							else
								(TimePrint 905 19)
								(Say ego 905 20)
							)
						else
							(TimePrint 905 21)
						)
					)
					(-1
						(TimePrint 905 22)
					)
					(else 
						(TimePrint 905 23)
						(Say ego 905 24)
					)
				)
				(self setScript: sHangUp self)
			)
			(4
				(= local6 0)
				(= sittingDown 0)
				(HandsOn)
				(user canControl: 0)
				(self dispose:)
			)
		)
	)
)

(instance nurseWindow of Feature
	(properties
		x 112
		y 63
		nsTop 45
		nsLeft 71
		nsBottom 82
		nsRight 153
		description {the sliding glass window}
		sightAngle 40
		approachX 134
		approachY 109
	)
	
	(method (doVerb theVerb theItem)
		(cond 
			((OneOf theVerb verbDo verbTalk)
				(cond 
					((OneOf (ego view?) 906 907)
						(if (== (ego loop?) 3)
							(TimePrint 905 25)
						else
							(curRoom setScript: sChair 0 self)
						)
					)
					((Btst fDentistClosed)
						(TimePrint 905 26)
					)
					((and (not (Btst fPassedDentishQuiz)) (not local1))
						(curRoom setScript: sWindow)
					)
					((== ((Inventory at: iDoily) state?) 1)
						(curRoom setScript: sWindow)
					)
					(else
						(TimePrint 905 27)
					)
				)
			)
			((== theVerb verbLook)
				(TimePrint 905 28)
			)
			((and (== theVerb verbUse) (== theItem iPulliamCard))
				(TimePrint 905 29)
			)
			(else
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance door of Door
	(properties
		x 234
		y 115
		description {the door}
		sightAngle 40
		approachX 194
		approachY 120
		lookStr {This door leads to the inner offices of Doc Pulliam, including (you hope) the cubicle of Chi Chi Lambada, the woman you traveled all this way to "interview."}
		view 905
		loop 3
		entranceTo 910
		locked 1
		openSnd 0
		closeSnd 0
		moveToX 250
		moveToY 108
		enterType 0
		exitType 0
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(if (OneOf (ego view?) 906 907)
					(curRoom setScript: sChair 0 self)
				else
					(super doVerb: theVerb theItem &rest)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
	
	(method (close)
		(super close:)
		(= cleanTeethMessage TRUE)
		(StartTimer 3 2 curRoom)
	)
)

(instance sWindow of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2] [str 60])
		(switch (= state newState)
			(0
				(HandsOff)
				(if (== ((Inventory at: iDoily) state?) 1)
					(ego
						view: 907
						setLoop: 0
						cycleSpeed: 6
						setCycle: EndLoop self
					)
				else
					(ego
						view: 906
						setLoop: 0
						cycleSpeed: 6
						setCycle: EndLoop self
					)
				)
				(= cycles 2)
			)
			(1
				(theMusic2 number: 911 setLoop: 1 play:)
			)
			(2
				(SetEgoLoop 3)
				(TimePrint 905 30 #dispose)
				(= ticks 90)
			)
			(3
				(theMusic2 number: 171 setLoop: -1 play:)
				(glass cycleSpeed: 12 setCycle: EndLoop self)
			)
			(4
				(theMusic2 stop:)
				(girl setCycle: Forward)
				(= ticks 123)
			)
			(5
				(switch (++ local9)
					(1
						(Say Alberta 905 31 #dispose #caller self)
					)
					(else 
						(Say Alberta 905 32 #dispose #caller self)
					)
				)
				(if (or (Btst fCalledDentist) (== (ego view?) 908))
					(= state 15)
				)
			)
			(6
				(switch local9
					(1 (Say ego 905 33))
					(else  (Say ego 905 34))
				)
				(= ticks 123)
			)
			(7
				(switch local9
					(1
						(Say Alberta 905 35 #dispose #caller self)
					)
					(else 
						(= state 9)
						(= formQuestion 0)
						(Say Alberta 905 36 #dispose #caller self)
					)
				)
			)
			(8
				(theMusic2 number: 171 setLoop: -1 play:)
				(glass setCycle: BegLoop self)
			)
			(9
				(theMusic2 stop:)
				(Say ego 905 37)
				(= start (= register 0))
				(HandsOn)
				(SetEgoLoop 3)
				(self dispose:)
			)
			(10
				(if modelessDialog
					(modelessDialog dispose:)
				)
				(TimePrint 905 38)
				(= ticks 123)
			)
			(11
				(Say Alberta 905 39 #dispose #caller self)
			)
			(12
				(girl setCel: 0)
				(Format @str 905 40 (++ formQuestion))
				(switch formQuestion
					(0 0)
					(1
						(= register
							(Print 905 41
								#button {Yes} 0
								#button {No} 1
								#title @str
							)
						)
					)
					(2
						(= register
							(Print 905 42
								#button {Yes} 0
								#button {No} 1
								#title @str
							)
						)
					)
					(3
						(= register
							(Print 905 43
								#button {Yes} 1
								#button {No} 0
								#title @str
							)
						)
					)
					(4
						(= register
							(Print 905 44
								#button {Yes} 1
								#button {No} 0
								#title @str
							)
						)
					)
					(5
						(= register
							(Print 905 45
								#button {Yes} 0
								#button {No} 1
								#title @str
							)
						)
					)
					(6
						(= register
							(Print 905 46
								#button {Yes} 0
								#button {No} 1
								#title @str
							)
						)
					)
					(7
						(= register
							(Print 905 47
								#button {Yes} 0
								#button {No} 1
								#title @str
							)
						)
					)
					(8
						(= register
							(Print 905 48
								#button {Yes} 0
								#button {No} 1
								#title @str
							)
						)
					)
					(9
						(= register
							(Print 905 49
								#button {Yes} 0
								#button {No} 1
								#title @str
							)
						)
					)
					(10
						(= register
							(Print 905 50
								#button {Yes} 0
								#button {No} 1
								#title @str
							)
						)
					)
					(11
						(= register
							(Print 905 51
								#button {Yes} 1
								#button {No} 0
								#title @str
							)
						)
					)
					(12
						(= register
							(Print 905 52
								#button {Yes} 1
								#button {No} 0
								#title @str
							)
						)
					)
					(13
						(= register
							(Print 905 53
								#button {Yes} 1
								#button {No} 0
								#title @str
							)
						)
					)
					(14
						(SolvePuzzle 13 fPassedDentishQuiz)
						(= state 17)
						(= local1 1)
						(Say Alberta 905 54 #dispose #caller self)
					)
				)
				(cond 
					(register
						(girl setCycle: Forward)
					)
					((< formQuestion 14) (-- state))
				)
				(= ticks 123)
			)
			(13
				(Say Alberta 905 55 #dispose #caller self)
			)
			(14
				(glass cycleSpeed: 4 setCycle: BegLoop self)
				(theMusic2 number: 171 setLoop: -1 play:)
			)
			(15
				(theMusic2 stop:)
				(TimePrint 905 37)
				(= start (= register 0))
				(HandsOn)
				(SetEgoLoop 3)
				(self dispose:)
			)
			(16
				(girl setCel: 0)
				(cond 
					((== (ego view?) 908)
						(Say ego 905 56)
						(SolvePuzzle 17 fEmergencyAppointment)
						(Bset fFakedInjury)
						(Say Alberta 905 57 #dispose #caller self)
					)
					((Btst fCalledDentist)
						(Say ego 905 58)
						(SolvePuzzle 13 fPhoneAppointment)
						(Say Alberta 905 59 #dispose #caller self)
					)
				)
			)
			(17
				(Bset fDentistClosed)
				(theMusic fade: 0 5 1 1)
				(SetEgoLoop 3)
				(door setPri: 8 locked: 0 open:)
			)
			(18
				(theMusic2 number: 171 setLoop: 1 play:)
				(glass cycleSpeed: 12 setCycle: BegLoop)
				(HandsOn)
				(SetEgoLoop 3)
				(self dispose:)
			)
		)
	)
)

(instance laceDoily of Prop
	(properties
		x 307
		y 160
		nsTop 144
		nsLeft 20
		nsBottom 157
		nsRight 37
		description {the doily}
		sightAngle 40
		approachX 287
		approachY 159
		view 905
		loop 1
		priority 13
		signal (| ignrAct ignrHrz fixedLoop fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 905 10)
			)
			(verbDo
				(cond 
					((OneOf (ego view?) 906 907)
						(curRoom setScript: sChair 0 self)
					)
					((not (ego has: iDoily))
						(HandsOff)
						(curRoom setScript: sGetDoily)
					)
					(else
						(TimePrint 905 60)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance legs of View
	(properties
		view 906
		loop 4
		priority 12
		signal (| ignrAct fixPriOn fixedLoop)
	)
)

(instance thePhone of Feature
	(properties
		x 31
		y 331
		z 200
		nsTop 126
		nsLeft 25
		nsBottom 137
		nsRight 38
		description {the phone}
		sightAngle 40
		approachX 62
		approachY 127
	)
	
	(method (doVerb)
		(phone doVerb: &rest)
	)
)

(instance toothTable2 of Feature
	(properties
		x 19
		y 379
		z 200
		nsTop 170
		nsBottom 189
		nsRight 39
		description {the table}
		sightAngle 40
		approachX 47
		approachY 183
		lookStr {You can tell that Doc's wife decorated this place. She's suffering from terminal cuteness!}
	)
)

(instance sGetDoily of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setHeading: 125 self)
			)
			(1
				(legs init: posn: 287 159)
				(ego
					view: 906
					setLoop: 4
					setCel: 1
					cycleSpeed: 8
					posn: 286 159
					setPri: 13
					setCycle: EndLoop self
				)
			)
			(2
				(laceDoily dispose:)
				(= cycles 2)
			)
			(3
				(rTable approachVerbs: verbNone)
				(SolvePuzzle 5 fTookDoily)
				(legs dispose:)
				(SetEgoLoop 4)
				(ego setPri: -1 get: 22)
				(= ticks 10)
			)
			(4
				(TimePrint 905 61 108 self)
			)
			(5
				(HandsOn)
				(self dispose:)
			)
		)
	)
)
