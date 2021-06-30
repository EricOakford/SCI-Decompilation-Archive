;;; Sierra Script 1.0 - (do not remove this comment)
(script# 520)
(include game.sh) (include "520.shm")
(use Main)
(use LBRoom)
(use ExitFeature)
(use MuseumRgn)
(use Inset)
(use Scaler)
(use PolyPath)
(use Feature)
(use LoadMany)
(use StopWalk)
(use DPath)
(use Timer)
(use Sound)
(use Motion)
(use Invent)
(use User)
(use Actor)
(use System)

(public
	rm520 0
	cobraLoose 1
)

(local
	local0
	local1
	local2
	[local3 2] = [0 1]
	theSCobraLoose
	local6
	local7
	local8 =  1
	local9 =  1
	local10
	local11
	local12 =  100
	local13
)
(procedure (localproc_2000 param1 &tmp temp0 temp1 temp2 temp3 temp4 [temp5 200])
	(if (== param1 522)
		(Message MsgGet 520 60 0 10 1 @temp5)
	else
		(Message MsgGet 520 60 0 11 1 @temp5)
	)
	(= local0 0)
	(= local1 0)
	(= temp2 (StrLen @temp5))
	(= temp4 21)
	(= temp3 0)
	(while (< temp3 (+ (/ temp2 2) (mod temp2 2)))
		(= temp0 (& [temp5 temp3] $00ff))
		(= temp1 (>> [temp5 temp3] $0008))
		(localproc_2092 temp0 temp4)
		(if (>= temp1 32) (localproc_2092 temp1 temp4))
		(++ temp3)
	)
)

(procedure (localproc_2092 param1 param2 &tmp temp0 temp1 [temp2 54])
	(= [temp1 0] param1)
	(TextSize @[temp2 0] @temp1 1900 0 0)
	(if
	(> (+ local0 (= temp0 (- [temp2 3] [temp2 1]))) 320)
		(= local0 0)
		(= local1 (+ local1 param2))
	)
	(Display @temp1
		p_at local0 local1
		p_font 1900
		p_color 7
	)
	(+= local0 temp0)
)

(instance rm520 of LBRoom
	(properties
		noun N_ROOM
		picture 520
		north 666
		south 510
		vanishingX 0
	)
	
	(method (init)
		(LoadMany 129 521 780)
		(LoadMany 132 520 524 521 522 523 525 441 481 49 721)
		(ego init: normalize: 831 setScale: Scaler 120 100 190 0)
		(self setRegions: 90)
		(switch prevRoomNum
			(south
				(ego x: 160)
				(theGame handsOn:)
				(theIconBar disable: 7)
			)
			(456 (ego x: 209 y: 127))
			(else 
				(ego posn: 109 125 setHeading: 62)
			)
		)
		(theGame handsOn:)
		(theIconBar disable: 7)
		(super init:)
		(cond 
			(
				(and
					(or
						(> currentAct 4)
						(and (== currentAct 4) (TriggerEvent 16648 1))
					)
					(!= prevRoomNum 525)
					(!= prevRoomNum 456)
					(not (TriggerEvent 16648))
				)
				(theMusic2 number: 521 loop: -1 flags: 1 play:)
			)
			((!= prevRoomNum 456) (theMusic2 number: 520 loop: -1 flags: 1 play:))
		)
		(if
		(and (== prevRoomNum 525) (not (TriggerEvent 16648)))
			((Timer new:) setReal: self 2)
		)
		(if
			(or
				(> currentAct 4)
				(and (== currentAct 4) (TriggerEvent 16648 1))
			)
			(if (not (Btst 80))
				(cobraDoor init:)
				(= local8 0)
				(= theSCobraLoose sCobraLoose)
			else
				(cobra init: approachVerbs: 4 1 8 stopUpd:)
			)
		else
			(mountedSkull init: approachVerbs: 4 1 8 stopUpd:)
			(cobra init: approachVerbs: 4 1 8 stopUpd:)
		)
		(if (not (Btst 49))
			(rosettaCloth init: approachVerbs: 4 1 8 stopUpd:)
		)
		(if
			(and
				(or
					(< currentAct 4)
					(and (== currentAct 4) (not (TriggerEvent 12548)))
				)
				(not (Btst 48))
			)
			(snakeOil init: approachVerbs: 4 1 8 stopUpd:)
		)
		(secretDoor setPri: 11 init: stopUpd:)
		(if
			(or
				(> currentAct 4)
				(and (== currentAct 4) (TriggerEvent 16648 1))
			)
			(deadCountess init: stopUpd: approachVerbs: 1 8)
		else
			(intercom init: stopUpd: approachVerbs: 4 1 8)
		)
		(ratBack init: approachVerbs: 4 1 8)
		(ratFore init: approachVerbs: 4 1 8 setOnMeCheck: 1 4)
		(rosetta init: approachVerbs: 4 1 8 setOnMeCheck: 1 256)
		(hieroglyphics
			init:
			approachVerbs: 4 1 8
			setOnMeCheck: 1 2048
		)
		(certificate init:)
		(skeletonLegs init:)
		(cobraCage init:)
		(bookShelf init:)
		(jars init:)
		(chair init:)
		(skeletonFore init: setOnMeCheck: 1 2)
		(desk init: setOnMeCheck: 1 8)
		(windowView init: setOnMeCheck: 1 16)
		(drapes init: setOnMeCheck: 1 32)
		(roachTop init: setOnMeCheck: 1 64)
		(roachBottom init: setOnMeCheck: 1 -32768)
		(displayCase init: setOnMeCheck: 1 128)
		(cages init: setOnMeCheck: 1 512)
		(lizards init: setOnMeCheck: 1 1024)
		(bookcase init: setOnMeCheck: 1 4096)
		(lizardTable init: setOnMeCheck: 1 8192)
		(standRat init: setOnMeCheck: 1 16384)
		(if theSCobraLoose (curRoom setScript: theSCobraLoose))
		(southExitFeature init:)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			(script)
			((and local7 (IsObjectOnControl ego 16)) (self setScript: sDropCobraGetBitten))
			(
				(and
					(> (ego y?) 185)
					(or
						(== (ego view?) 522)
						(== (ego view?) 528)
						(== (ego view?) 529)
					)
				)
				(curRoom setScript: sDropCobraGetBitten)
			)
			((> (ego y?) 185) (= local7 1))
		)
	)
	
	(method (cue)
		(if local13
			(super newRoom: 26)
		else
			(theMusic2 number: 520)
			((ScriptID 22 0) doit: 16648)
		)
	)
	
	(method (newRoom n)
		(cond 
			((== n 456) (super newRoom: n))
			((and (== currentAct 4) (ego has: 31))
				(= n 26)
				(WrapMusic dispose:)
				((ScriptID 22 0) doit: 31)
				(theMusic2 number: 524 loop: 1 flags: 1 play: self)
				(= local13 1)
			)
			(else (theMusic2 fade:) (super newRoom: n))
		)
		((ScriptID 90 2) setPri: -1)
	)
)

(instance cobra of Actor
	(properties
		x 85
		y 58
		noun 16
		approachX 78
		approachY 122
		view 521
		loop 3
		priority 2
		signal $4010
		cycleSpeed 12
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (curRoom setInset: inCobra))
			(8 (curRoom setInset: inCobra))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance cobraLoose of Actor
	(properties
		x 77
		y 178
		view 521
		cycleSpeed 12
	)
	
	(method (doit)
		(if
			(and
				(not local7)
				(not (cobraDoor script?))
				(not local8)
			)
			(cond 
				(
					(and
						local11
						(or
							(< (ego distanceTo: self) 40)
							(> (ego distanceTo: self) local12)
						)
					)
					(cobraDoor setScript: sCobraStrike)
				)
				((and (< (ego x?) (self x?)) local9) (= local9 0) (self setScript: sCobraTurn))
				((and (> (ego x?) (self x?)) (not local9)) (= local9 1) (self setScript: sCobraTurn))
			)
		)
		(super doit:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(25
				(switch local6
					(0
						(if (< (ego y?) 152)
							(ego setMotion: MoveTo 158 130)
							(= local6 1)
						else
							(ego setMotion: MoveTo 160 189)
							(= local6 11)
						)
					)
					(else 
						(if (ego has: 14) (++ local6))
					)
				)
				(cond 
					(
					(or (> local6 13) (and (> local6 3) (< local6 9))) (= numSnakeOil 0) (-- local6) (messager say: 30 0 5))
					((> numSnakeOil 0) (-- numSnakeOil) (cobraDoor setScript: sLauraOil))
					(else (messager say: 30 0 5))
				)
			)
			(30
				(switch local6
					(3
						(ego setScript: 0)
						(= local7 1)
						(cobraDoor setScript: sLauraLasso3)
					)
					(13
						(ego setScript: 0)
						(= local7 1)
						(cobraDoor setScript: sLauraLasso13)
					)
					(else 
						(cobraDoor setScript: sCobraStrike)
					)
				)
			)
			(4
				(cobraDoor setScript: sCobraStrike)
			)
			(1
				(curRoom setScript: sShowCobraLoose)
			)
			(8
				(curRoom setScript: sShowCobraLoose)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance cobraDoor of Prop
	(properties
		x 108
		y 28
		noun 29
		view 521
		loop 8
		cel 6
		priority 2
		signal $0010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4 (self doVerb: 30))
			(30
				(cond 
					(local7 (self setScript: sPutCobraBack))
					((== (self cel?) 0) (self setCycle: EndLoop))
					(else (self setCycle: BegLoop))
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance bigCobra of View
	(properties
		x 34
		y 79
		view 521
		loop 13
		cel 3
		priority 15
		signal $0010
	)
)

(instance rosettaCloth of View
	(properties
		x 223
		y 105
		noun 21
		approachX 210
		approachY 131
		view 521
		loop 13
		priority 9
		signal $4010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if
					(or
						(== (ego view?) 522)
						(== (ego view?) 528)
						(== (ego view?) 529)
					)
					(messager say: 58 0 7)
				else
					(curRoom setScript: sRemoveCloth)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance snakeOil of View
	(properties
		x 113
		y 104
		z 10
		noun 30
		approachX 130
		approachY 145
		view 520
		loop 3
		priority 8
		signal $0010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(curRoom setInset: inSnakeOil)
			)
			(8 (self doVerb: 1))
			(4 (self doVerb: 1))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance mountedSkull of Prop
	(properties
		x 98
		y 74
		noun 31
		approachX 91
		approachY 141
		view 520
		loop 4
		priority 8
		signal $0010
		cycleSpeed 18
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(4
					(cond 
						(
							(or
								(== (ego view?) 522)
								(== (ego view?) 528)
								(== (ego view?) 529)
							)
							(messager say: 58 0 7)
						)
						((MuseumRgn nobodyAround:) (self setScript: sSecretDoor))
						(else (return 1))
					)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance secretDoor of Prop
	(properties
		x 294
		y 159
		view 524
		loop 1
		cycleSpeed 12
	)
)

(instance intercom of View
	(properties
		x 179
		y 96
		noun 32
		approachX 152
		approachY 135
		view 520
		loop 3
		cel 1
		priority 8
		signal $0010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(curRoom setInset: inIntercom)
			)
			(8 (self doVerb: 1))
			(4 (self doVerb: 1))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance deadCountess of View
	(properties
		x 111
		y 89
		heading 90
		noun 33
		approachX 109
		approachY 125
		view 524
		priority 8
		signal $4010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if local8
					(ego setHeading: 62)
					(theGame points: 1 163)
					(curRoom newRoom: 525)
				else
					(messager say: 58 0 7)
				)
			)
			(8 (self doVerb: 1))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance ratFore of Feature
	(properties
		x 281
		y 200
		noun 14
		approachX 225
		approachY 171
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(8
				(curRoom setInset: inRatFore)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance ratBack of Feature
	(properties
		x 88
		y 21
		noun 14
		nsTop 14
		nsLeft 74
		nsBottom 29
		nsRight 103
		approachX 80
		approachY 113
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(8
				(curRoom setInset: inRatBack)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance rosetta of Feature
	(properties
		x 293
		y 104
		heading 90
		noun 34
		sightAngle 40
		approachX 210
		approachY 131
	)
	
	(method (doVerb theVerb &tmp temp0)
		(switch theVerb
			(8
				(if
					(or
						(== (ego view?) 522)
						(== (ego view?) 528)
						(== (ego view?) 529)
					)
					(messager say: 58 0 7)
				else
					(theGame points: 1 137)
					((ScriptID 21 0) doit: 1025)
					(= temp0 14)
					(while (< temp0 27)
						((ScriptID 21 0) doit: (+ temp0 1088))
						(++ temp0)
					)
					(curRoom newRoom: 456)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance certificate of Feature
	(properties
		y 1
	)
	
	(method (onMe theObjOrX)
		(if (super onMe: theObjOrX)
			(cond 
				(
					(and
						(<= 188 mouseX)
						(<= mouseX 206)
						(<= 20 mouseY)
						(<= mouseY 43)
					)
					(= x 197)
					(= y 31)
					(= z 0)
					(= noun 2)
				)
				(
					(and
						(<= 185 mouseX)
						(<= mouseX 210)
						(<= 51 mouseY)
						(<= mouseY 72)
					)
					(= x 197)
					(= y 61)
					(= z 0)
					(= noun 3)
				)
				(
					(and
						(<= 222 mouseX)
						(<= mouseX 240)
						(<= 17 mouseY)
						(<= mouseY 37)
					)
					(= x 231)
					(= y 27)
					(= z 0)
					(= noun 4)
				)
				(
					(and
						(<= 246 mouseX)
						(<= mouseX 274)
						(<= 15 mouseY)
						(<= mouseY 38)
					)
					(= x 260)
					(= y 26)
					(= z 0)
					(= noun 5)
				)
			)
		)
	)
)

(instance skeletonLegs of Feature
	(properties
		x 302
		y 134
		noun 6
		nsLeft 285
		nsBottom 68
		nsRight 319
		sightAngle 40
	)
)

(instance cobraCage of Feature
	(properties
		x 93
		y 46
		noun 7
		nsTop 29
		nsLeft 78
		nsBottom 63
		nsRight 109
		sightAngle 40
		approachX 81
		approachY 116
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if local7
					(cobraDoor setScript: sPutCobraBack)
				else
					(super doVerb: theVerb &rest)
				)
			)
			(30 (self doVerb: 4))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance bookShelf of Feature
	(properties
		x 91
		y 70
		noun 8
		nsTop 65
		nsLeft 78
		nsBottom 76
		nsRight 104
		sightAngle 40
	)
)

(instance jars of Feature
	(properties)
	
	(method (onMe theObjOrX)
		(if (super onMe: theObjOrX)
			(cond 
				(
					(and
						(<= 81 mouseX)
						(<= mouseX 88)
						(<= 79 mouseY)
						(<= mouseY 90)
					)
					(= x 85)
					(= y 83)
					(= z 0)
					(= noun 9)
				)
				(
					(and
						(<= 92 mouseX)
						(<= mouseX 101)
						(<= 79 mouseY)
						(<= mouseY 87)
					)
					(= x 96)
					(= y 83)
					(= z 0)
					(= noun 10)
				)
				(
					(and
						(<= 80 mouseX)
						(<= mouseX 90)
						(<= 90 mouseY)
						(<= mouseY 97)
					)
					(= x 85)
					(= y 93)
					(= z 0)
					(= noun 11)
				)
			)
		)
	)
)

(instance chair of Feature
	(properties
		x 144
		y 82
		noun 12
		nsTop 76
		nsLeft 134
		nsBottom 88
		nsRight 154
		sightAngle 40
	)
)

(instance skeletonFore of Feature
	(properties
		x 260
		y 164
		noun 13
		sightAngle 40
	)
)

(instance desk of Feature
	(properties
		y 88
		noun 15
		sightAngle 40
	)
)

(instance windowView of Feature
	(properties
		y 47
		noun 28
		sightAngle 40
	)
)

(instance drapes of Feature
	(properties
		y 12
		noun 17
		sightAngle 40
	)
)

(instance roachTop of Feature
	(properties
		y 55
		noun 18
		sightAngle 40
	)
)

(instance roachBottom of Feature
	(properties
		y 102
		noun 19
		sightAngle 40
	)
)

(instance displayCase of Feature
	(properties
		x 320
		y 77
		noun 20
		sightAngle 40
	)
)

(instance cages of Feature
	(properties
		x 320
		y 128
		noun 22
		sightAngle 40
	)
)

(instance lizards of Feature
	(properties
		y 180
		noun 23
		sightAngle 40
	)
)

(instance bookcase of Feature
	(properties
		y 1
		noun 25
		sightAngle 40
	)
)

(instance lizardTable of Feature
	(properties
		y 183
		noun 26
		sightAngle 40
	)
)

(instance standRat of Feature
	(properties
		x 260
		y 180
		noun 27
		sightAngle 40
	)
)

(instance inCobra of Inset
	(properties
		view 521
		loop 13
		cel 1
		x 77
		y 23
		disposeNotOnMe 1
		noun 54
	)
	
	(method (init)
		(if
			(not
				(cond 
					((> currentAct 4))
					((== currentAct 4) (TriggerEvent 16648 1))
				)
			)
			(fang init:)
		)
		(super init: &rest)
	)
	
	(method (dispose)
		(fang dispose:)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if
					(or
						(> currentAct 4)
						(and (== currentAct 4) (TriggerEvent 16648 1))
					)
					(messager say: noun 1 8)
				else
					(messager say: noun 1 9)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance hieroglyphics of Feature
	(properties
		x 250
		y 65
		heading 90
		noun 24
		sightAngle 90
		approachX 210
		approachY 131
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if
					(or
						(== (ego view?) 522)
						(== (ego view?) 528)
						(== (ego view?) 529)
					)
					(messager say: 58 0 7)
				else
					(theGame handsOff:)
					(curRoom setScript: sHieroglyphics)
				)
			)
			(8 (self doVerb: 1))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance fang of View
	(properties
		x 93
		y 32
		view 521
		loop 13
		cel 2
		priority 15
		signal $0010
	)
)

(instance inSnakeOil of Inset
	(properties
		view 520
		loop 2
		x 127
		y 33
		priority 15
		disposeNotOnMe 1
		noun 38
	)
	
	(method (dispose)
		(curRoom setScript: sOlympiaOil)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4 (self dispose:))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance inIntercom of Inset
	(properties
		view 520
		x 162
		y 79
		disposeNotOnMe 1
		noun 39
	)
)

(instance inRatFore of Inset
	(properties
		view 520
		loop 1
		x 232
		y 147
		priority 15
		disposeNotOnMe 1
		noun 40
	)
)

(instance inRatBack of Inset
	(properties
		view 520
		loop 1
		x 72
		y 6
		disposeNotOnMe 1
		noun 41
	)
)

(instance inHieroglyphics of Inset
	(properties
		picture 780
		noun 24
	)
	
	(method (init)
		(= local2 522)
		(super init: &rest)
		(DrawPic 780)
		(localproc_2000 local2)
		(InFirstPerson 1)
		(walkHandler addToFront: self)
	)
	
	(method (dispose)
		(walkHandler delete: self)
		(InFirstPerson 0)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(= local2 (if (== local2 522) 523 else 522))
				(sFX number: 525 flags: 1 play:)
				(DrawPic 780 (if (== local2 522) 11 else 12))
				(localproc_2000 local2)
			)
			(13 (self dispose:))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance sHieroglyphics of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic2 fade: 80 20 20 0)
				(= cycles 1)
			)
			(1
				(cast eachElementDo: #hide)
				(theGame handsOff:)
				(User canInput: 1)
				(theIconBar enable: 0 2 1)
				(curRoom setInset: inHieroglyphics self)
			)
			(2
				(if (not (ego has: 14)) (snakeOil show:))
				(theGame handsOn:)
				(theIconBar disable: 7)
				(cast eachElementDo: #show)
				(= cycles 1)
			)
			(3
				(theMusic2 fade: 127 20 20 0)
				(self dispose:)
			)
		)
	)
)

(instance sRemoveCloth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 215 126 self)
			)
			(1
				(rosettaCloth dispose:)
				(Bset 49)
				(ego
					view: 521
					setLoop: 14
					setCel: 0
					setScale: Scaler 100 100 190 0
					setCycle: EndLoop self
				)
			)
			(2
				(ego
					normalize: 831
					setScale: Scaler 120 100 190 0
					setHeading: 90
				)
				(theGame handsOn:)
				(theIconBar disable: 7)
				(self dispose:)
			)
		)
	)
)

(instance sOlympiaOil of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego stopUpd:)
				(if (== ((ScriptID 90 2) room?) curRoomNum)
					((ScriptID 90 2)
						setPri: 13
						setMotion: DPath 128 183 102 160 95 125 self
					)
				else
					((ScriptID 90 2)
						moveTo: curRoomNum
						posn: 170 250
						setPri: 13
						setMotion: DPath 128 183 102 160 95 125 self
					)
				)
			)
			(1
				(Face (ScriptID 90 2) ego)
				(= cycles 1)
			)
			(2
				(Bset 48)
				((ScriptID 90 2)
					view: 525
					setLoop: 2
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(3 (= ticks 30))
			(4
				(snakeOil dispose:)
				((ScriptID 90 2) setCycle: BegLoop self)
			)
			(5
				((ScriptID 90 2)
					view: 820
					setLoop: -1
					setCycle: Walk
					setMotion:
						MoveTo
						(+ ((ScriptID 90 2) x?) 10)
						((ScriptID 90 2) y?)
						self
				)
			)
			(6
				((ScriptID 90 2) setHeading: 180)
				(= cycles 1)
			)
			(7
				((ScriptID 90 2) setCycle: StopWalk -1)
				(= ticks 120)
			)
			(8
				(messager say: 1 0 1 0 self 1520)
			)
			(9
				((ScriptID 90 2)
					view: 820
					setCycle: Walk
					setMotion: DPath 102 160 128 183 170 250 self
				)
			)
			(10
				((ScriptID 90 2) moveTo: -2)
				(ego startUpd:)
				((Inventory at: iSnakeOil) owner: 0)
				(theGame handsOn:)
				(theIconBar disable: 7)
				(self dispose:)
			)
		)
	)
)

(instance sCobraLoose of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1 (= ticks 180))
			(2
				(sFX number: 522 flags: 1 play:)
				(= cycles 2)
			)
			(3
				(ego setHeading: 270)
				(= ticks 60)
			)
			(4
				(cobraLoose
					init:
					setLoop: 0
					setCycle: Forward
					setMotion: MoveTo 86 158 self
				)
			)
			(5
				(Face ego cobraLoose)
				(cobraLoose setCycle: EndLoop self)
			)
			(6
				(cobraLoose
					setLoop: 9
					setCel: 0
					posn: 90 159
					setCycle: EndLoop self
				)
			)
			(7
				(cobraLoose
					setLoop: 11
					setCel: 0
					posn: 92 159
					setCycle: Forward
				)
				(= ticks 30)
			)
			(8
				(sFX number: 522 flags: 1 play:)
				(bigCobra init:)
				(= ticks 180)
			)
			(9
				(bigCobra dispose:)
				(= local11 1)
				(ego setScript: sCobraTimer)
				(theGame handsOn:)
				(theIconBar disable: 7)
				(self dispose:)
			)
		)
	)
)

(instance sShowCobraLoose of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(sFX number: 522 flags: 1 play:)
				(bigCobra
					init:
					x: (- (cobraLoose x?) 58)
					y: (- (cobraLoose y?) 80)
				)
				(= ticks 180)
			)
			(1
				(bigCobra dispose:)
				(= ticks 60)
			)
			(2
				(theGame handsOn:)
				(theIconBar disable: 7)
				(self dispose:)
			)
		)
	)
)

(instance sLauraOil of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setScript: 0)
				(if (< local6 9)
					(ego setMotion: PolyPath 158 130 self)
				else
					(ego setMotion: PolyPath 160 179 self)
				)
			)
			(1
				(switch local6
					(1 (= local10 0))
					(2 (= local10 0))
					(3 (= local10 1))
					(11 (= local10 2))
					(12 (= local10 2))
					(13 (= local10 3))
				)
				(ego
					view: 522
					setLoop: local10
					setScale: Scaler 100 100 190 0
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(2
				(switch local6
					(1
						(cobraLoose setLoop: 0 setMotion: MoveTo 147 177 self)
					)
					(2
						(cobraLoose setLoop: 0 setMotion: MoveTo 213 171 self)
					)
					(3
						(= local12 120)
						(cobraLoose setLoop: 0 setMotion: MoveTo 260 150 self)
					)
					(11
						(cobraLoose setLoop: 0 setMotion: MoveTo 128 134 self)
					)
					(12
						(cobraLoose setLoop: 0 setMotion: MoveTo 187 133 self)
					)
					(13
						(cobraLoose setLoop: 15 setMotion: PolyPath 207 107 self)
					)
				)
			)
			(3
				(ego normalize: 831 setScale: Scaler 120 100 190 0)
				(Face ego cobraLoose)
				(= cycles 1)
			)
			(4
				(cobraLoose setLoop: 4 setCel: 0 setCycle: EndLoop self)
			)
			(5
				(if (< (cobraLoose x?) (ego x?))
					(cobraLoose setLoop: 9 setCel: 0 setCycle: EndLoop self)
				else
					(cobraLoose setLoop: 10 setCel: 0 setCycle: EndLoop self)
				)
			)
			(6
				(if (< (cobraLoose x?) (ego x?))
					(cobraLoose setLoop: 11 setCel: 0 setCycle: Forward)
				else
					(cobraLoose setLoop: 12 setCel: 0 setCycle: Forward)
				)
				(= cycles 1)
			)
			(7
				(if (< (ego x?) (cobraLoose x?))
					(= local9 0)
				else
					(= local9 1)
				)
				(ego setScript: sCobraTimer)
				(theGame handsOn:)
				(theIconBar disable: 7)
				(self dispose:)
			)
		)
	)
)

(instance sLauraLasso3 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo 216 151 self)
			)
			(1 (= cycles 2))
			(2
				(ego view: 522 setCycle: 0 setLoop: 5 setCel: 0)
				(= ticks 30)
			)
			(3
				(cobraLoose setLoop: 3 setCel: 0 setCycle: 0)
				(= cycles 1)
			)
			(4 (ego setCycle: CycleTo 3 1 self))
			(5
				(cobraLoose hide: dispose:)
				(ego setCycle: EndLoop self)
			)
			(6
				(ego
					view: 528
					setLoop: 0
					setCel: 0
					posn: 220 152
					setLoop: -1
					setCycle: StopWalk 529
				)
				(= ticks 60)
			)
			(7
				(theGame handsOn:)
				(theIconBar disable: 7)
				(self dispose:)
			)
		)
	)
)

(instance sLauraLasso13 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= local7 1)
				(ego
					normalize: 831
					setScale: Scaler 120 100 190 0
					setMotion: MoveTo 209 127 self
				)
			)
			(1
				(ego
					view: 522
					setScale: Scaler 110 90 190 0
					setLoop: 6
					setCel: 0
					setCycle: StopWalk
				)
				(= cycles 1)
			)
			(2
				(cobraLoose hide: dispose:)
				(ego view: 522 setLoop: 6 cel: 0 setCycle: EndLoop self)
			)
			(3 (ego setCycle: BegLoop self))
			(4
				(ego
					view: 528
					setLoop: 3
					setCel: 6
					posn: 211 128
					setLoop: -1
					setCycle: StopWalk 529
				)
				(= ticks 60)
			)
			(5
				(theGame handsOn:)
				(theIconBar disable: 7)
				(self dispose:)
			)
		)
	)
)

(instance sCobraStrike of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Face ego cobraLoose)
				(= local7 1)
				(cobraLoose illegalBits: 0 ignoreActors: 1)
				(if local9
					(cobraLoose
						setLoop: 0
						setCycle: Forward
						setMotion: PolyPath (- (ego x?) 34) (+ (ego y?) 10) self
					)
				else
					(cobraLoose
						setLoop: 1
						setCycle: Forward
						setMotion: PolyPath (+ (ego x?) 35) (+ (ego y?) 11) self
					)
				)
			)
			(1
				(theMusic2 stop:)
				(sFX number: 523 flags: 1 play:)
				(if local9
					(cobraLoose
						setLoop: 2
						setCel: 0
						posn: (- (ego x?) 21) (+ (ego y?) 6)
						setCycle: CycleTo 4 1 self
					)
				else
					(cobraLoose
						setLoop: 3
						setCel: 0
						posn: (+ (ego x?) 21) (+ (ego y?) 7)
						setCycle: CycleTo 4 1 self
					)
				)
			)
			(2
				(cobraLoose setCycle: EndLoop self)
				(sFX number: 481 flags: 1 setLoop: 1 play:)
			)
			(3
				(ego
					view: 523
					setLoop: 0
					setCel: 0
					posn: (+ (ego x?) 15) (+ (ego y?) 1)
					setCycle: EndLoop self
				)
			)
			(4
				(= deathReason 11)
				(curRoom newRoom: 99)
				(self dispose:)
			)
		)
	)
)

(instance sPutCobraBack of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= local7 0)
				(ego setCycle: Walk setMotion: PolyPath 132 95 self)
			)
			(1
				(ego
					view: 522
					setLoop: 4
					setCel: 0
					posn: 126 98
					setCycle: CycleTo 6 1 self
				)
			)
			(2
				(cobraDoor setCycle: EndLoop self)
			)
			(3
				(theMusic2 fade:)
				(ego setCycle: CycleTo 9 1 self)
			)
			(4
				(cobra init: stopUpd:)
				(= cycles 1)
			)
			(5
				(ego setCycle: EndLoop)
				(cobraDoor setCycle: BegLoop self)
			)
			(6
				(sFX number: 441 flags: 1 setLoop: 1 play:)
				(theGame points: 1 164)
				(= local8 1)
				(Bset 80)
				(cobraLoose hide:)
				(ego
					normalize: 831
					setScale: Scaler 120 100 190 0
					signal: 16384
					setHeading: 270
				)
				(UnLoad 128 528)
				(UnLoad 128 529)
				(theMusic2 number: 520 loop: -1 flags: 1 play:)
				(theIconBar disable: 7)
				(= cycles 1)
			)
			(7
				(cobraDoor stopUpd:)
				(theGame handsOn:)
				(theIconBar disable: 7)
				(self dispose:)
			)
		)
	)
)

(instance sDropCobraGetBitten of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= local7 0)
				(ego
					view: 522
					setLoop: 7
					setCel: 0
					setScale: Scaler 100 100 190 0
					setCycle: EndLoop self
				)
			)
			(1 (= ticks 60))
			(2
				(cobraLoose
					show:
					setLoop: 4
					setCel: 3
					x: (+ (ego x?) 4)
					y: (+ (ego y?) 3)
				)
				(ego view: 523 setLoop: 0 setCel: 0 setCycle: EndLoop self)
			)
			(3
				(= deathReason 11)
				(curRoom newRoom: 99)
				(self dispose:)
			)
		)
	)
)

(instance sCobraTurn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if local9
					(cobraLoose setLoop: 5 setCel: 0 setCycle: EndLoop self)
				else
					(cobraLoose setLoop: 4 setCel: 0 setCycle: EndLoop self)
				)
			)
			(1
				(if local9
					(cobraLoose setLoop: 11 setCel: 0 setCycle: EndLoop self)
				else
					(cobraLoose setLoop: 12 setCel: 0 setCycle: EndLoop self)
				)
			)
			(2 (self dispose:))
		)
	)
)

(instance sCobraTimer of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 15))
			(1
				(ego setScript: sCobraStrike)
				(self dispose:)
			)
		)
	)
)

(instance sFX of Sound
	(properties
		flags $0001
	)
)

(instance southExitFeature of ExitFeature
	(properties
		nsTop 184
		nsLeft 115
		nsBottom 189
		nsRight 245
		cursor 11
		exitDir 3
		noun 57
	)
)

(instance sSecretDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 93 122 self)
			)
			(1
				(Face ego mountedSkull)
				(= cycles 4)
			)
			(2
				(ego
					view: 561
					setScale: Scaler 100 100 190 0
					setLoop: 0
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(3
				(ego cycleSpeed: 12 setCycle: BegLoop self)
				(sFX number: 49 flags: 1 setLoop: 1 play:)
				(mountedSkull setCycle: EndLoop self)
			)
			(4 0)
			(5
				(ego normalize: 831 setScale: Scaler 120 100 190 0)
				(Face ego secretDoor)
				(sFX number: 721 flags: 1 setLoop: 1 play:)
				(secretDoor setCycle: EndLoop self)
			)
			(6
				(sFX stop:)
				(ego setMotion: PolyPath 315 167 self)
			)
			(7
				(ego setMotion: MoveTo 330 167 self)
			)
			(8
				(ego setPri: 2)
				(sFX number: 721 flags: 1 setLoop: 1 play:)
				(secretDoor setCycle: BegLoop self)
			)
			(9
				(sFX stop:)
				(secretDoor stopUpd:)
				(curRoom newRoom: (curRoom north?))
				(theGame handsOn:)
				(theIconBar disable: 7)
				(self dispose:)
			)
		)
	)
)
