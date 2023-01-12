;;; Sierra Script 1.0 - (do not remove this comment)
(script# 42)
(include game.sh)
(use Main)
(use Intrface)
(use SQRoom)
(use Talker)
(use PChase)
(use Osc)
(use Polygon)
(use Feature)
(use LoadMany)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm42 0
)

(local
	stickUp
)
(instance rm42 of SQRoom
	(properties
		lookStr {This is the back of the bar at the southeast corner of Ulence Flats.}
		picture 42
		horizon 140
		north 47
		west 41
	)
	
	(method (init)
		(self
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 130
						192 131
						213 137
						209 146
						223 148
						220 153
						197 153
						181 161
						136 161
						107 166
						62 171
						0 170
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 319 189 294 189 286 181 291 174 319 174
					yourself:
				)
		)
		(LoadMany 128 10 142)
		(switch prevRoomNum
			(north
				(= style FADEOUT)
				(ego posn: 230 (+ horizon 5))
			)
			(west
				(= style SCROLLLEFT)
				(ego x: 20)
				(if (Btst fMuggerPloy)
					(LoadMany VIEW 29 432 515)
					(mugger init:)
					(self setScript: mugEgo)
				else
					(HandsOn)
				)
			)
			(43
				(curRoom setScript: dumpDust 0 1)
			)
			(else 
				(= style DISSOLVE)
				(ego posn: 220 180)
				(HandsOn)
			)
		)
		(self setRegions: ULENCE)
		(if (!= prevRoomNum 43) (ego init:))
		(super init:)
		(robotArm init: hide:)
		(hole init: stopUpd: setScript: dumpDust)
		(radar1 init: setCycle: Forward)
		(radar2 init: setCycle: Forward)
		(pile init:)
		(pickup init:)
		(toDBU init:)
		(droidStore init:)
		(barBack init:)
		(scribbles init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbDo
				(Print 42 0)
			)
			(verbTalk
				(Print 42 1)
			)
			(verbSmell
				(Print 42 2)
			)
			(verbTaste
				(Print 42 3)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance dumpDust of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if register
					(= cycles 6)
				else
					(= seconds (Random 10 35))
				)
			)
			(1
				(if (and (curRoom script?) (not register))
					(self init:)
				else
					(HandsOff)
					(soundFx number: 603 loop: 1 play:)
					(hole cel: 0 setCycle: EndLoop self)
				)
			)
			(2
				(soundFx stop:)
				(Face ego hole)
				(robotArm show: loop: 1 cel: 0 setCycle: EndLoop self)
			)
			(3 (= cycles 24))
			(4
				(robotArm loop: 2 cel: 0 setCycle: CycleTo 3 1 self)
			)
			(5
				(theMusic2 number: 604 loop: 1 play:)
				(robotArm setCycle: EndLoop self)
			)
			(6
				(robotArm loop: 6 cel: 0 setCycle: EndLoop self)
			)
			(7
				(soundFx number: 603 loop: 1 play:)
				(hole setCycle: BegLoop self)
			)
			(8
				(soundFx stop:)
				(hole stopUpd:)
				(robotArm hide:)
				(= seconds 2)
			)
			(9
				(if register
					(EgoDead 78 0 0 42 4)
				else
					(HandsOn)
					(self init:)
				)
			)
		)
	)
)

(instance searchDust of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 10
					setLoop: 5
					cel: 0
					cycleSpeed: 18
					setCycle: CycleTo 2 1 self
				)
			)
			(1 (ego setCycle: CycleTo 1 -1 self))
			(2 (ego setCycle: CycleTo 2 1 self))
			(3 (ego setCycle: CycleTo 1 -1 self))
			(4 (ego setCycle: CycleTo 6 1 self))
			(5 (= cycles 12))
			(6
				(if (>= buckazoidsInDust 3)
					(= buckazoidsInDust
						(-
							buckazoidsInDust
							(= temp0 (Random 2 buckazoidsInDust))
						)
					)
					(= buckazoids (+ buckazoids temp0))
					(Printf 42 5 temp0)
				else
					(Print 42 6)
				)
				(= seconds 2)
			)
			(7 (ego setCycle: EndLoop self))
			(8
				(NormalEgo 0 1 61)
				(if register
					((ScriptID ULENCE 1) setMotion: PFollow ego 55)
				)
				(ego loop: 1)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance mugEgo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 125 171 self)
				(= cycles 30)
			)
			(1 (mugger setCycle: Oscillate 1))
			(2
				(muggerTalker
					init:
						muggerBust muggerEyes muggerMouth
						{"Come on over here, pal. Wait'll ya see this!"}
						0 0 1
						self
				)
			)
			(3 (ego setHeading: 90 self))
			(4
				(mugger setLoop: 4 cel: 0 setCycle: EndLoop self)
			)
			(5
				(ego view: 29 cel: 0 setCycle: EndLoop self)
			)
			(6
				(mugger setLoop: 5 cel: 0 setCycle: EndLoop self)
			)
			(7
				(muggerTalker
					init:
						muggerBust muggerEyes muggerMouth
						{"Hold it right there, pal! This is a stick-up! I'll just relieve you of your cash, thank you very much."}
						0 0 0
						self
				)
			)
			(8
				(= stickUp 1)
				(HandsOn)
				(theIconBar disable: ICON_WALK ICON_LOOK ICON_TALK ICON_SMELL)
				(= seconds 4)
			)
			(9
				(muggerTalker
					say: {"C'mon, c'mon, I ain't got all day."} 0 0 0 self
				)
			)
			(10 (= seconds 5))
			(11
				(mugger setLoop: 6 cel: 2 setCycle: BegLoop self)
			)
			(12
				(ego setLoop: 2 cel: 0)
				(mugger cel: 1)
				(= cycles 3)
			)
			(13
				(ego cel: 1)
				(mugger cel: 0)
				(= cycles 3)
			)
			(14
				(ego cel: 0)
				(mugger cel: 1)
				(= cycles 3)
			)
			(15
				(ego cel: 1)
				(mugger cel: 0)
				(= cycles 3)
			)
			(16
				(ego cel: 0)
				(mugger cel: 1)
				(= cycles 3)
			)
			(17
				(ego cycleSpeed: 15 setCycle: EndLoop self)
				(mugger cel: 0 setCycle: EndLoop)
			)
			(18 (EgoDead 940 1 0 42 7))
		)
	)
)

(instance egoGivesMoney of Script
	(properties)
	
	(method (changeState newState &tmp [str 80])
		(switch (= state newState)
			(0
				(Bclr fMuggerPloy)
				(Bset fGotMugged)
				(ego setLoop: 1 cel: 0 cycleSpeed: 15 setCycle: EndLoop self)
			)
			(1
				(mugger setCycle: CycleTo 2 -1 self)
			)
			(2
				(muggerTalker
					say: (Format @str 42 8 buckazoids) 0 0 0 self
				)
			)
			(3 (mugger setCycle: EndLoop self))
			(4
				(ego cel: 5 setCycle: CycleTo 7 1 self)
				(= buckazoids 5)
			)
			(5 (ego setCycle: CycleTo 5 -1 self))
			(6
				(NormalEgo 0 1 61)
				(mugger setCycle: BegLoop self)
			)
			(7
				(muggerTalker
					say:
						{"Well, you've got to be leaving now. Go straight back the way you came in. Deviate from that path and you're Grell kibble!"}
						0 0 1
						self
				)
			)
			(8
				(mugger setLoop: 4 cel: 5 setCycle: BegLoop self)
			)
			(9
				(mugger setCycle: CycleTo 3 1 self)
			)
			(10
				(muggerTalker
					init:
						muggerBust muggerEyes muggerMouth
						{"And if ya try followin' me, you'll get a nasty taste of my blaster. Got it? Good."}
						0 0 1
						self
				)
			)
			(11 (mugger setCycle: BegLoop self))
			(12
				(mugger
					setLoop: 0
					setCycle: Walk
					setMotion: MoveTo 216 159 self
				)
			)
			(13
				(mugger setMotion: MoveTo 258 150 self)
			)
			(14
				(mugger setLoop: 1 setMotion: MoveTo 215 122 self)
			)
			(15
				(mugger setMotion: MoveTo 152 114 self)
			)
			(16
				(mugger dispose:)
				(NormalEgo 0 1 61)
				(ego loop: 0)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance mugger of Actor
	(properties
		x 170
		y 166
		description {alien wolf-creature}
		view 432
		loop 8
		cel 7
		signal (| ignrAct ignrHrz)
		cycleSpeed 6
		illegalBits $0000
		xStep 4
		moveSpeed 6
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(if stickUp
					(Print 42 9)
				else
					(Print 42 10)
				)
			)
			(verbTalk
				(Print 42 11)
			)
			(verbUse
				(if (== theItem iBuckazoid)
					(cond 
						((Btst fGotMugged)
							(Print 42 12)
						)
						(stickUp
							(curRoom setScript: egoGivesMoney)
						)
						(else
							(Print 42 13)
						)
					)
				else
					(Print 42 14)
					(Print 42 15)
				)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance hole of Prop
	(properties
		x 175
		y 97
		description {waste disposal port}
		lookStr {This waste disposal port looks as though it's used quite often.}
		view 142
		priority 10
		signal fixPriOn
		cycleSpeed 10
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(Print 42 16)
			)
			(verbSmell
				(Print 42 17)
			)
			(verbTaste
				(Print 42 18)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance robotArm of Prop
	(properties
		x 176
		y 108
		description {robot arm}
		lookStr {A robot arm reaches out to dump another load of dust into an already large pile. That must be one very dirty bar.}
		view 142
		loop 1
		priority 10
		signal (| ignrAct ignrHrz fixPriOn)
		cycleSpeed 10
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(Print 42 16)
			)
			(verbSmell
				(Print 42 19)
			)
			(verbTalk
				(Print 42 20)
			)
			(verbTaste
				(Print 42 19)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance radar1 of Prop
	(properties
		x 27
		y 173
		description {force field sensor}
		lookStr {The force field's sensors detect any moving, ground-based animals to prevent them from entering - or leaving - Ulence Flats.}
		view 142
		loop 3
		cel 7
		priority 15
		signal $4010
		cycleSpeed 4
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(Print 42 21)
			)
			(verbTalk
				(Print 42 22)
			)
			(verbSmell
				(Print 42 21)
			)
			(verbTaste
				(Print 42 21)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance radar2 of Prop
	(properties
		x 302
		y 137
		lookStr {The force field's sensors detect any moving, ground-based animals to prevent them from entering - or leaving - Ulence Flats.}
		view 142
		loop 4
		cel 6
		priority 15
		signal (| ignrAct fixPriOn)
		cycleSpeed 4
	)
	
	(method (doVerb theVerb)
		(radar1 doVerb: theVerb &rest)
	)
)

(instance pile of Feature
	(properties
		x 200
		y 147
		description {pile of dust}
		onMeCheck $0040
		approachX 231
		approachY 147
		lookStr {There is a strange pile of very fine white powder here. It has a strange, burnt smell to it.}
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: verbDo)
	)
	
	(method (doVerb theVerb)
		(= x ((User curEvent?) x?))
		(= y ((User curEvent?) y?))
		(switch theVerb
			(verbDo
				(if (curRoom script?)
					(super doVerb: theVerb &rest)
				else
					(curRoom setScript: searchDust)
				)
			)
			(verbSmell
				(Print 42 23)
			)
			(verbTaste
				(Print 42 24)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance pickup of Feature
	(properties
		description {pickup building}
		onMeCheck FARCHECK
		lookStr {It's a Droids-B-Us store.}
	)
	
	(method (doVerb theVerb)
		(droidStore doVerb: theVerb &rest)
	)
)

(instance toDBU of Feature
	(properties
		description {way to the Droids-B-Us store}
		onMeCheck NEARCHECK
		lookStr {That's the way to the Droids-B-Us store.}
	)
	
	(method (doVerb theVerb)
		(droidStore doVerb: theVerb &rest)
	)
)

(instance droidStore of Feature
	(properties
		description {Droids-B-Us store}
		onMeCheck $0010
		lookStr {In the distance you see the familiar shape of a Droids-B-Us store.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(Print 42 25)
			)
			(verbSmell
				(Print {You can't do that from here.})
			)
			(verbTaste
				(Print {You can't do that from here.})
			)
			(verbTalk
				(Print {You can't do that from here.})
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance barBack of Feature
	(properties
		description {bar building}
		onMeCheck ISNOTHIDDEN
		lookStr {The back of the bar looks about as dingy as the front did.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(Print 42 25)
			)
			(verbSmell
				(Print 42 17)
			)
			(verbTaste
				(Print 42 18)
			)
			(verbTalk
				(Print 42 26)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance scribbles of Feature
	(properties
		description {graffiti}
		onMeCheck cLRED
		lookStr {It appears to be in Sarienese. Your Sarienese is a little rusty, but you decide it must say "Xenon Bites!"}
	)
	
	(method (doVerb theVerb)
		(barBack doVerb: theVerb &rest)
	)
)

(instance muggerTalker of Talker
	(properties
		x 7
		y 46
		nsTop 5
		nsLeft 207
		view 515
	)
)

(instance muggerBust of View
	(properties
		view 515
		cel 1
	)
)

(instance muggerMouth of Prop
	(properties
		nsTop 44
		nsLeft 19
		view 515
		loop 2
		cycleSpeed 8
	)
)

(instance muggerEyes of Prop
	(properties
		nsTop 32
		nsLeft 15
		view 515
		loop 1
		cycleSpeed 16
	)
)
