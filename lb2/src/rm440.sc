;;; Sierra Script 1.0 - (do not remove this comment)
(script# 440)
(include game.sh) (include "440.shm")
(use Main)
(use LbDoor)
(use LBRoom)
(use ExitFeature)
(use MuseumRgn)
(use PursuitRgn)
(use Scaler)
(use PolyPath)
(use Feature)
(use MoveFwd)
(use LoadMany)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm440 0
	sOutTapestry 1
	rm440Door 2
	noise 3
	bolt 4
)

(instance rm440 of LBRoom
	(properties
		noun N_ROOM
		picture 440
		horizon 135
		north 448
		east 430
		south 490
		vanishingY 90
	)
	
	(method (init)
		(LoadMany RES_VIEW 432 424 423 858 831 426 442 440 443)
		(LoadMany RES_SOUND 442 440)
		(ego
			init:
			normalize: (if (== currentAct 5) 426 else 831)
			setScale: Scaler 155 0 190 90
		)
		(if (== currentAct 5)
			(self setRegions: 94)
			(curRoom obstacles: (List new:))
			((ScriptID 2440 0) doit: (curRoom obstacles?))
			(if (not (Btst 45)) ((ScriptID 94 1) seconds: 1))
		else
			(self setRegions: 90)
		)
		(switch prevRoomNum
			(north
				(ego x: 171 y: 148)
				(if (Btst 47) (curRoom setScript: (ScriptID 444 0)))
				(theGame handsOn:)
			)
			(south
				(ego x: 160 y: 210 loop: 3)
				(self setScript: sEnterSouth)
			)
			(east
				(ego x: 197 y: 143)
				(self setScript: sEnterEast)
			)
			(else 
				(ego posn: 160 160)
				(theGame handsOn:)
			)
		)
		(super init:)
		(if (and (== currentAct 4) (TriggerEvent 16648 1))
			((ScriptID 443 1) addToPic:)
			(if (not (ego has: 12))
				((ScriptID 443 0) init: approachVerbs: 4 1 8)
			)
		)
		(if
		(and (> currentAct 2) (not (== currentAct 5)))
			((ScriptID 443 4)
				init:
				setOnMeCheck: 1 8192
				approachVerbs: 4 1 8
			)
		else
			(armorPippin
				init:
				approachVerbs: (if (== currentAct 5) 0 else 4 1 8)
			)
		)
		(rm440Door
			init:
			doubleDoor: otherHalf
			stopUpd:
			approachVerbs: 4 1 8
		)
		(otherHalf init: approachVerbs: 4 1 8)
		(bolt init:)
		(if (Btst 41)
			(rm440Door cel: 0)
			(otherHalf cel: 0)
			(bolt cel: 3)
		)
		(chest init:)
		(tapestry init: approachVerbs: 4 1 8)
		(painting init:)
		(dogArmor init: setOnMeCheck: 1 16384)
		(genericArmor init: setOnMeCheck: 1 8192)
		(genericFlag init: setOnMeCheck: 1 4096)
		(rightDoorway init:)
		(rearDoorway init:)
		(roundWin init:)
		(southExitFeature init:)
		((ScriptID 1881 2) x: 12 y: 85 textX: 125 textY: 0)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			(script)
			((IsObjectOnControl ego 2)
				(otherHalf setPri: 10)
				(bolt setPri: 11)
				(curRoom setScript: sExitEast)
			)
			((IsObjectOnControl ego 8) (curRoom setScript: sExitSouth))
		)
	)
	
	(method (dispose)
		(DisposeScript 441)
		(DisposeScript 442)
		(DisposeScript 443)
		(DisposeScript 444)
		(if (== currentAct 5) (DisposeScript 2440))
		(walkHandler delete: self)
		(directionHandler delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(return
			(cond 
				(inset (inset handleEvent: event))
				(
					(and
						(& (event type?) direction)
						(== (theIconBar curIcon?) (theIconBar walkIconItem?))
						(!= (event message?) dirStop)
						(== (ego view?) 443)
					)
					(event claimed: TRUE)
					(ego setScript: sOutTapestry)
				)
				((& (event type?) walkEvent)
					(super handleEvent: event)
				)
				(else
					(return FALSE)
				)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3
				(if (== (ego view?) 443)
					(ego setScript: sOutTapestry)
				else
					((ScriptID 441 4) seconds: 1)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (notify)
		(cond 
			((== currentAct 5)
				(if
					(and
						(curRoom script?)
						(not (== (curRoom script?) (ScriptID 444 0)))
					)
					((curRoom script?) next: (ScriptID 444 0))
				else
					(curRoom setScript: (ScriptID 444 0))
				)
			)
			((and (== currentAct 3) (TriggerEvent 8224 1)) (self setScript: sMeetingNo2))
			((and (== currentAct 3) (TriggerEvent 4104 1))
				(LoadMany 128 444 825)
				(if (== (ego view?) 443) (theIconBar disable: 1 2 6 5))
				(theGame handsOff:)
				(if (== (ego view?) 443)
					(theGame points: 1 149)
					(self setScript: (ScriptID 441 0))
				else
					(self setScript: (ScriptID 441 1))
				)
			)
		)
	)
)

(instance sMeetingNo2 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 1))
			(1
				(if (== (ego view?) 443) (theIconBar disable: 1 2 6 5))
				(theGame handsOff:)
				(LoadMany 128 820 814)
				((ScriptID 90 2) moveTo: 440)
				(= cycles 1)
			)
			(2
				((ScriptID 90 2)
					loop: 1
					setScale: Scaler 155 0 190 90
					x: 228
					y: 133
				)
				(if ((ScriptID 90 2) scaler?)
					(((ScriptID 90 2) scaler?) doit:)
				)
				(= cycles 1)
			)
			(3
				((ScriptID 90 2) view: 820)
				(= cycles 3)
			)
			(4
				(if (== (ego view?) 443)
					((ScriptID 90 2) setScript: (ScriptID 442 0) self)
				else
					((ScriptID 90 2) setScript: (ScriptID 442 1) self)
				)
			)
			(5
				((ScriptID 90 2) setScript: (ScriptID 442 2) self)
			)
			(6 (= cycles 3))
			(7
				(DisposeScript 442)
				(if (== (ego view?) 443)
					(theGame handsOn:)
					(theIconBar disable: 1 2 6 5)
				else
					(theGame handsOn:)
				)
				((ScriptID 90 2) moveTo: 430 wandering: 1)
				(self dispose:)
			)
		)
	)
)

(instance sEnterEast of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 1)
			)
			(1
				(ego setMotion: MoveFwd 20 self)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sExitEast of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 1)
			)
			(1
				(ego setMotion: MoveTo 236 136 self)
			)
			(2
				(if
					(and
						(== currentAct 3)
						(TriggerEvent -20222 1)
						(not (Btst 72))
					)
					(curRoom newRoom: 435)
				else
					(curRoom newRoom: 430)
				)
			)
		)
	)
)

(instance sEnterSouth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 1)
			)
			(1
				(ego setMotion: MoveTo (ego x?) 170 self)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sExitSouth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 1)
			)
			(1
				(ego setMotion: MoveTo (ego x?) 275 self)
			)
			(2 (curRoom newRoom: 490))
		)
	)
)

(instance sHideInTapestry of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theIconBar disable: 1 2 6 5)
				(theGame handsOff:)
				(if (not (== currentAct 5))
					(WrapMusic pause: 1)
					(theMusic2 number: 5 flags: 1 loop: -1 play:)
				)
				(= cycles 1)
			)
			(1
				(walkHandler addToFront: curRoom)
				(directionHandler addToFront: curRoom)
				(ego
					view: 443
					setScale: Scaler 100 100 190 90
					loop: 1
					cel: 0
					posn: 11 147
					setCycle: CycleTo 5 1 self
				)
			)
			(2
				(noise number: 442 flags: 1 play:)
				(ego setCycle: EndLoop self)
			)
			(3
				(ego setLoop: 0 cel: 0)
				(= cycles 1)
			)
			(4
				(if
					(or
						(and
							(== currentAct 3)
							(TriggerEvent 4104 1)
							((ScriptID 90 15) seconds?)
						)
						(and
							(== currentAct 3)
							(TriggerEvent 8224 1)
							((ScriptID 90 15) seconds?)
						)
					)
					((ScriptID 90 15) seconds: 1)
				else
					(theGame handsOn: 1)
				)
				(southExitFeature dispose:)
				(self dispose:)
			)
		)
	)
)

(instance sOutTapestry of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 1)
			)
			(1
				(if (== (curRoom script?) (ScriptID 441 0))
					(curRoom script: 0)
				)
				(ego setCycle: EndLoop self)
				(noise number: 442 flags: 1 play:)
			)
			(2
				(ego
					normalize: (if (== currentAct 5) 426 else 831)
					setScale: Scaler 155 0 190 90
					posn: 20 151
				)
				(if
					(and
						(== ((ScriptID 90 1) room?) 440)
						(TriggerEvent 4104)
						(not (TriggerEvent 4880))
						(not (Btst 120))
					)
					(ego setScript: (ScriptID 441 3) self)
					(self dispose:)
				else
					(walkHandler delete: curRoom)
					(directionHandler delete: curRoom)
					(= cycles 1)
				)
			)
			(3
				(theMusic2 fade:)
				(if (not (== currentAct 5)) (WrapMusic pause: 0))
				(theGame handsOn: 1)
				(southExitFeature init:)
				(theIconBar enable: 1 2 6 5)
				(self dispose:)
			)
		)
	)
)

(instance sBoltDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 1)
			)
			(1
				(ego setMotion: PolyPath 213 146 self)
			)
			(2
				(ego
					view: 442
					loop: 2
					cel: 3
					setPri: 11
					posn: 207 144
					setScale: Scaler 100 100 190 90
					cycleSpeed: 12
					setCycle: BegLoop self
				)
				(bolt setPri: (- (ego priority?) 1))
				(bolt setCycle: EndLoop)
			)
			(3
				(noise number: 446 flags: 1 loop: 1 play:)
				(ego
					view: 831
					loop: 8
					setPri: -1
					cel: 6
					setScale: Scaler 155 0 190 90
					posn: 213 146
				)
				(= cycles 1)
			)
			(4
				(ego normalize: (if (== currentAct 5) 426 else 831))
				(= cycles 1)
			)
			(5
				(bolt stopUpd:)
				(if (== currentAct 5)
					(= cycles 1)
				else
					(client setScript: sUnBoltDoor)
				)
			)
			(6
				(theGame handsOn:)
				(rm440Door locked: 1)
				(if (== currentAct 5) (PursuitRgn increaseTime:))
				(Bset 41)
				(self dispose:)
			)
		)
	)
)

(instance sUnBoltDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 1)
			)
			(1
				(ego setMotion: PolyPath 213 146 self)
			)
			(2
				(ego
					view: 442
					loop: 2
					cel: 0
					setPri: 11
					posn: 207 144
					setScale: Scaler 100 100 190 90
					cycleSpeed: 12
					setCycle: EndLoop self
				)
				(bolt setCycle: BegLoop)
				(noise number: 446 flags: 1 loop: 1 play:)
			)
			(3
				(ego view: 831 loop: 8 setPri: -1 cel: 6 posn: 213 146)
				(= cycles 1)
			)
			(4
				(ego
					normalize: (if (== currentAct 5) 426 else 831)
					setScale: Scaler 155 0 190 90
				)
				(= cycles 1)
			)
			(5
				(bolt stopUpd:)
				(if (== currentAct 5)
					(= cycles 1)
				else
					(messager say: 12 4 3 0 self)
				)
			)
			(6
				(theGame handsOn:)
				(rm440Door locked: 0)
				(if (== currentAct 5) (PursuitRgn decreaseTime:))
				(Bclr 41)
				(self dispose:)
			)
		)
	)
)

(instance otherHalf of Prop
	(properties
		x 225
		y 139
		noun 12
		approachX 199
		approachY 145
		view 440
		loop 4
		cel 7
		signal $4001
	)
)

(instance rm440Door of LbDoor
	(properties
		x 211
		y 137
		noun 12
		approachX 199
		approachY 145
		view 440
		loop 3
		cel 7
		forceOpen 1
		forceClose 0
		moveToX 236
		moveToY 136
	)
	
	(method (cue)
		(super cue:)
		(bolt setPri: 11)
		(otherHalf stopUpd:)
		(bolt stopUpd:)
	)
	
	(method (open)
		(bolt setPri: 15)
		(super open:)
	)
	
	(method (close)
		(bolt setPri: 15)
		(super close:)
	)
	
	(method (createPoly)
		(super createPoly: 205 130 230 134 229 144 206 138)
	)
)

(instance bolt of Prop
	(properties
		x 225
		y 160
		z 45
		noun 12
		view 440
		loop 5
		priority 9
		signal $4011
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(cond 
					((rm440Door locked?) (curRoom setScript: sUnBoltDoor))
					((== (rm440Door doorState?) 2) (rm440Door close:))
					(else (curRoom setScript: sBoltDoor))
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance leftDoor of Feature
	(properties
		x 94
		y 88
		noun 6
		nsTop 89
		nsLeft 90
		nsBottom 134
		nsRight 99
		sightAngle 40
	)
)

(instance chest of Feature
	(properties
		x 295
		y 140
		noun 4
		nsTop 118
		nsLeft 271
		nsBottom 163
		nsRight 319
		sightAngle 40
	)
)

(instance tapestry of Feature
	(properties
		x 28
		y 91
		noun 9
		nsTop 35
		nsLeft 5
		nsBottom 147
		nsRight 51
		sightAngle 40
		approachX 20
		approachY 151
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(4
					(if
						(and
							(not (== (ego view?) 443))
							(or
								(== currentAct 5)
								(== (== ((ScriptID 90 1) room?) 440) 0)
							)
						)
						(if
						(or (== currentAct 5) (MuseumRgn nobodyAround:))
							(curRoom setScript: sHideInTapestry)
						else
							(return 1)
						)
					else
						(super doVerb: theVerb &rest)
					)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
)

(instance painting of Feature
	(properties
		x 265
		y 95
		noun 5
		nsTop 79
		nsLeft 255
		nsBottom 111
		nsRight 276
		sightAngle 40
	)
)

(instance dogArmor of Feature
	(properties
		y 100
		noun 3
	)
)

(instance genericArmor of Feature
	(properties
		y 160
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4 (messager say: 1 4))
			(8 (messager say: 1 8))
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (onMe theObjOrX)
		(if (super onMe: theObjOrX)
			(cond 
				(
					(and
						(< 59 mouseX)
						(< mouseX 70)
						(< 83 mouseY)
						(< mouseY 143)
					)
					(= noun 24)
				)
				(
					(and
						(< 72 mouseX)
						(< mouseX 84)
						(< 91 mouseY)
						(< mouseY 143)
					)
					(= noun 25)
				)
				(
					(and
						(< 86 mouseX)
						(< mouseX 100)
						(< 93 mouseY)
						(< mouseY 143)
					)
					(= noun 26)
				)
				(
					(and
						(< 103 mouseX)
						(< mouseX 110)
						(< 105 mouseY)
						(< mouseY 134)
					)
					(= noun 27)
				)
				(
					(and
						(< 115 mouseX)
						(< mouseX 130)
						(< 104 mouseY)
						(< mouseY 149)
					)
					(= noun 28)
				)
				(
					(and
						(< 171 mouseX)
						(< mouseX 185)
						(< 96 mouseY)
						(< mouseY 135)
					)
					(= noun 30)
				)
				(
					(and
						(< 187 mouseX)
						(< mouseX 201)
						(< 91 mouseY)
						(< mouseY 137)
					)
					(= noun 31)
				)
				(
					(and
						(< 225 mouseX)
						(< mouseX 256)
						(< 97 mouseY)
						(< mouseY 188)
					)
					(= noun 32)
				)
			)
		)
	)
)

(instance genericFlag of Feature
	(properties
		y 50
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4 (messager say: 2 4))
			(8 (messager say: 2 8))
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (onMe theObjOrX)
		(if (super onMe: theObjOrX)
			(cond 
				(
					(and
						(<= 45 mouseX)
						(<= mouseX 130)
						(<= 0 mouseY)
						(<= mouseY 23)
					)
					(= noun 14)
				)
				(
					(and
						(<= 76 mouseX)
						(<= mouseX 116)
						(<= 25 mouseY)
						(<= mouseY 43)
					)
					(= noun 15)
				)
				(
					(and
						(<= 92 mouseX)
						(<= mouseX 117)
						(<= 44 mouseY)
						(<= mouseY 54)
					)
					(= noun 16)
				)
				(
					(and
						(<= 95 mouseX)
						(<= mouseX 119)
						(<= 56 mouseY)
						(<= mouseY 72)
					)
					(= noun 17)
				)
				(
					(and
						(<= 99 mouseX)
						(<= mouseX 118)
						(<= 72 mouseY)
						(<= mouseY 82)
					)
					(= noun 18)
				)
				(
					(and
						(<= 106 mouseX)
						(<= mouseX 123)
						(<= 83 mouseY)
						(<= mouseY 95)
					)
					(= noun 19)
				)
				(
					(and
						(<= 154 mouseX)
						(<= mouseX 177)
						(<= 64 mouseY)
						(<= mouseY 77)
					)
					(= noun 20)
				)
				(
					(and
						(<= 148 mouseX)
						(<= mouseX 191)
						(<= 39 mouseY)
						(<= mouseY 62)
					)
					(= noun 21)
				)
				(
					(and
						(<= 139 mouseX)
						(<= mouseX 198)
						(<= 0 mouseY)
						(<= mouseY 38)
					)
					(= noun 22)
				)
				(
					(and
						(<= 215 mouseX)
						(<= mouseX 270)
						(<= 0 mouseY)
						(<= mouseY 20)
					)
					(= noun 23)
				)
			)
		)
	)
)

(instance rightDoorway of Feature
	(properties
		x 218
		y 112
		noun 7
		nsTop 85
		nsLeft 214
		nsBottom 139
		nsRight 223
		sightAngle 40
	)
)

(instance rearDoorway of Feature
	(properties
		x 140
		y 116
		noun 13
		nsTop 101
		nsLeft 111
		nsBottom 131
		nsRight 169
		sightAngle 40
	)
)

(instance roundWin of Feature
	(properties
		x 138
		y 82
		noun 33
		nsTop 73
		nsLeft 125
		nsBottom 91
		nsRight 151
		sightAngle 40
	)
)

(instance armorPippin of Feature
	(properties
		x 151
		y 128
		noun 10
		nsTop 96
		nsLeft 140
		nsBottom 160
		nsRight 164
		sightAngle 40
		approachX 128
		approachY 165
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (== currentAct 5)
					(messager say: 38)
				else
					(messager say: 10 1 2)
				)
			)
			(4
				(if (== currentAct 5)
					(messager say: 38)
				else
					(messager say: 10 4 2)
				)
			)
			(8
				(if (== currentAct 5)
					(messager say: 38)
				else
					(messager say: 10 8 2)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance southExitFeature of ExitFeature
	(properties
		nsTop 184
		nsBottom 189
		nsRight 319
		cursor 11
		exitDir 3
		noun 35
	)
)

(instance noise of Sound
	(properties
		flags $0001
	)
)
