;;; Sierra Script 1.0 - (do not remove this comment)
(script# 330)
(include sci.sh)
(use Main)
(use n078)
(use fileScr)
(use EgoDead)
(use LarryRoom)
(use PolyFeature)
(use Print)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Timer)
(use Sound)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm330 0
	gammie 1
	proc330_2 2
	clog 3
	explosion1 4
	explosion2 5
	proc330_6 6
	lever 7
	pistonShaft 8
	hoseWithHole 9
	filter 10
	lid 11
	spigot 12
)

(local
	local0
	[local1 2]
	local3
	local4
)
(procedure (proc330_2)
	(return
		(cond 
			((not (Btst 184)) 11)
			((not (Btst 185)) 12)
			((not (Btst 186)) 13)
			((not (Btst 187)) 14)
			(else 15)
		)
	)
)

(procedure (proc330_6)
	(if (cast contains: wheel) (wheel dispose:))
	(= local0 0)
	(if (Btst 177)
		(pistonShaft view: 336 setLoop: 2)
	else
		(pistonShaft view: 330 setLoop: 0)
	)
	(pistonShaft show: setCycle: 0 cel: 0 setScript: 0)
	(if (cast contains: arc) (arc dispose:))
	(if (cast contains: intArc) (intArc dispose:))
	(lever show: cel: 0)
	(if (timers contains: explosionTimer)
		(explosionTimer dispose:)
	)
	(if (cast contains: explosion1) (explosion1 dispose:))
	(if (cast contains: explosion2) (explosion2 dispose:))
	(boobs
		view: 330
		setLoop: 1
		setCel: 0
		setScript: 0
		setCycle: 0
	)
	(longArcFx stop:)
	(shortArcFx stop:)
	(leftBoobFx stop:)
	(rightBoobFx stop:)
	(machineHumFx stop:)
	(if (not (cast contains: hoseWithHole))
		(pipe1 dispose:)
		(pipe2 dispose:)
		(pipe3 dispose:)
		(hoseWithHole init:)
	else
		(pipe1 setCycle: 0 cel: 0)
		(pipe2 setCycle: 0 cel: 0)
		(pipe3 setCycle: 0 cel: 0)
		(hoseWithHole cel: 1 setCycle: 0 setScript: 0)
	)
	(machineHumFx number: 0 client: 0 stop:)
	(if (cast contains: clog)
		(clog setPri: -1 setLoop: 0 setCel: 0 dispose:)
	)
	(Bclr 33)
)

(instance rm330 of LarryRoom
	(properties
		noun 1
		picture 330
		horizon 0
		south 310
		autoLoad 0
	)
	
	(method (init)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init:
						234
						153
						233
						134
						289
						134
						289
						123
						243
						113
						243
						102
						227
						92
						160
						92
						159
						107
						126
						107
						113
						114
						93
						123
						74
						126
						59
						121
						50
						126
						58
						128
						27
						151
						128
						162
						129
						130
						167
						130
						166
						153
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 215 106 225 115 211 127 180 134 173 129 157 126 140 124 129 118
					yourself:
				)
		)
		(super init: &rest)
		(ego init: posn: 83 180 normalize:)
		(hoseWithHole init:)
		(if (== global171 8)
			(gammie init:)
			(if (Btst 186) (washCloth init:))
			(if (Btst 184) (siphons init:))
			(if (Btst 87)
				(lever cel: 1)
				(gammie setScript: suckingFatScr)
				(longArcFx play: setLoop: -1)
				(if (> howFast 3) (arc init: setCycle: Fwd))
				(if (> howFast 3) (intArc init:))
				(wheel init: setCycle: Fwd cycleSpeed: 0)
				(pistonShaft
					view: 336
					setLoop: 3 1
					setCycle: Fwd
					cycleSpeed: 0
				)
				(machineHumFx number: 335 loop: 1 play: changeHummScr)
			)
		)
		(self setScript: (ScriptID 331 3))
		(probes init:)
		(pistonShaft init: approachVerbs: 4 1 2 5 6 30)
		(lever init: approachVerbs: 4)
		(boobs init:)
		(electrode1 init:)
		(electrode2 init:)
		(if (Btst 33)
			(boobs
				view: 336
				setLoop: 4
				setCel: 0
				cycleSpeed: 0
				setScript: boobScr
			)
		)
		(filterTank init:)
		(if
			(and
				(Btst 30)
				(== ((inventory at: 13) owner?) curRoomNum)
			)
			(filter init:)
		)
		(lid init:)
		(pipes init:)
		(table init:)
		(tanks init:)
		(redPipe init:)
		(blueVagina init:)
		(spigot init: approachVerbs: 4 1 2 5 6 39)
		(if (and global110 (== global171 8)) (Bset 86))
	)
	
	(method (doit)
		(cond 
			(script)
			((and (== (ego edgeHit?) 3) (< (ego y?) 145)) (ego edgeHit: 0))
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(Bclr 86)
		(= useSortedFeatures 1)
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb)
		(return
			(if (not useSortedFeatures)
				(if (OneOf theVerb 4 1 2 5 6)
					(messager say: noun theVerb 15)
				else
					(messager say: noun 0 15)
				)
				(return 1)
			else
				(return (super doVerb: theVerb))
			)
		)
	)
	
	(method (cue)
		(= gGButtonBarGetCursor theCursor)
		(theGame setCursor: normalCursor)
		(SetCursor 180 105)
		(if
			(==
				(Print
					width: 150
					font: userFont
					addTitle: 25 0 41 1 330
					addText: 25 0 0 1 50 0
					addButton: 100 25 0 42 1 160 33
					addButton: 200 25 0 43 1 50 33
					x: 30
					y: 20
					addIcon: 1911 0 0 0 0
					init:
				)
				200
			)
			(proc330_6)
			(if (cast contains: siphons) (siphons dispose:))
			(if (cast contains: dummyView) (dummyView dispose:))
			(= useSortedFeatures 1)
			(if (ego has: 26) (ego put: 26 curRoomNum))
			(gammie dispose:)
			(Bset 20)
			(ego
				setMotion: 0
				normalize: 900 8
				setCel: 0
				posn: 161 129
			)
			(= global171 9)
			(curRoom setScript: cleanItUpScr)
		else
			(theIconBar enableIcon: (ScriptID 0 8) show:)
			(= gLarryRoom self)
		)
		(theGame setCursor: gGButtonBarGetCursor)
	)
	
	(method (edgeToRoom param1)
		(if (and (not script) (== param1 3))
			(cond 
				((and (Btst 33) (not (cast contains: gammie)))
					(if (not (theGame isHandsOff?))
						(self setScript: (ScriptID 331 2))
					)
				)
				((cast contains: gammie) (self setScript: (ScriptID 331 0)))
				(else (self setScript: (ScriptID 331 1)))
			)
		)
		(return 0)
	)
)

(instance explosionTimer of Timer
	(properties)
)

(instance cleanItUpScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1 (= cycles 2) (proc78_0))
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance boobScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client cel: 0 setCycle: CT 2 1 self)
			)
			(1
				(leftBoobFx play:)
				(client setCycle: End self)
			)
			(2
				(rightBoobFx play:)
				(= state -1)
				(= ticks (Max (client cycleSpeed?) 1))
			)
		)
	)
)

(instance pistonShaftScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (not register)
					(pistonFx play:)
					(++ state)
					(client cel: 0 setCycle: End self)
				else
					(client cel: 0 setCycle: CT 1 1 self)
				)
			)
			(1
				(pistonFx play:)
				(client setCycle: End self)
			)
			(2 (= state -1) (self cue:))
		)
	)
)

(instance hoseScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client cel: 0 setCycle: End self)
			)
			(1
				(hoseFlappingFx play:)
				(= state -1)
				(= cycles 2)
			)
		)
	)
)

(instance changeHummScr of Script
	(properties)
	
	(method (cue)
		(machineHumFx number: 339 loop: 1 play: self)
	)
)

(instance suckingFatScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= register 0)
				(pipe1 init: setCycle: Fwd)
				(pipe2 init: setCycle: Fwd)
				(pipe3 init: setCycle: Fwd)
				(hoseWithHole dispose:)
				(cond 
					((and (ego has: 27) (not (Bset 58))) (vat1 init: setCycle: End self) (= register 1))
					((Btst 58) (vat1 init: cel: (vat1 lastCel:)) (self cue:))
					(else (self dispose:))
				)
			)
			(1
				(if (!= (gammie cel?) 1) (gammie cel: 1))
				(cond 
					((and register (Btst 185)) (vat2 init: setCycle: End self) (= register 1))
					((Btst 185) (vat2 init: cel: (vat2 lastCel:)) (self cue:))
					(else (self dispose:))
				)
			)
			(2
				(if (!= (gammie cel?) 2) (gammie cel: 2))
				(cond 
					(
						(or
							(and
								(ego has: 39)
								((inventory at: 39) cue: 1)
								(not (Btst 59))
							)
							(and
								(== ((inventory at: 39) owner?) curRoomNum)
								local3
							)
						)
						(Bset 59)
						(vat3 init: setCycle: End self)
						(= register 1)
					)
					((Btst 59) (vat3 init: cel: (vat3 lastCel:)) (self cue:))
					(else (self dispose:))
				)
			)
			(3
				(if (!= (gammie cel?) 3) (gammie cel: 3))
				(cond 
					((and register (Btst 186)) (vat4 init: setCycle: End self))
					((Btst 186) (vat4 init: cel: (vat4 lastCel:)) (self cue:))
					(else (self dispose:))
				)
			)
			(4
				(if (!= (gammie cel?) 4) (gammie cel: 4))
				(cond 
					((and (ego has: 26) (not (Btst 60))) (Bset 60) (vat5 init: setCycle: End vat5))
					((Btst 60) (vat5 init: cel: (vat5 lastCel:)))
				)
				(self dispose:)
			)
		)
	)
)

(instance hookUpGammieScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(self setScript: navStairScr self 1)
			)
			(1 (= cycles 2))
			(2
				(messager sayRange: 11 4 11 1 3 self)
			)
			(3 (= cycles 2))
			(4
				(ego
					setSpeed: 12
					view: 335
					setLoop: 0
					setCel: 0
					setCycle: CT 1 1 self
				)
			)
			(5
				(theMusic2 number: 334 loop: 1 play:)
				(ego setCycle: CT 4 1 self)
			)
			(6
				(theMusic2 number: 334 loop: 1 play:)
				(ego setCycle: CT 7 1 self)
			)
			(7
				(theMusic2 number: 334 loop: 1 play:)
				(ego setCycle: End self)
			)
			(8
				(messager say: 11 4 11 4 self oneOnly: 0)
			)
			(9
				(siphons init:)
				(ego normalize: 900 8 setPri: 125 setCel: 3)
				(= cycles 2)
			)
			(10
				(self setScript: navStairScr self 0)
			)
			(11
				(ego normalize: 900 8 cel: 2 setPri: -1)
				(theGame changeScore: 2 184 handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance navStairScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setPri: 200 setSpeed: 6)
				(if register
					(ego setMotion: MoveTo 187 119 self)
				else
					(ego
						setMotion: MoveTo (gammie approachX?) (gammie approachY?) self
					)
				)
			)
			(1 (self dispose:))
		)
	)
)

(instance turnEgoScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setPri: -1 setHeading: register)
				(= register 0)
			)
		)
	)
)

(instance startWatching of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setPri: -1 setMotion: PolyPath 161 129 self)
			)
			(1 (ego setHeading: 90 self))
			(2 (= cycles 2))
			(3
				(theIconBar
					enableIcon:
						(ScriptID 0 7)
						(ScriptID 0 6)
						(ScriptID 0 5)
						(ScriptID 0 9)
						(ScriptID 0 4)
					curIcon: (ScriptID 0 4)
					show:
				)
				(theGame setCursor: ((theIconBar curIcon?) getCursor:))
				(User canInput: 1)
				(dummyView init: hide:)
				(cast delete: dummyView)
				(cast addToFront: dummyView)
				(= useSortedFeatures 0)
				(self dispose:)
			)
		)
	)
)

(instance dummyView of View
	(properties
		view 98
	)
	
	(method (handleEvent event)
		(if (not (gammie onMe: event))
			(curRoom doVerb: (event message?))
			(event claimed: 1)
			(return)
		)
	)
)

(instance filter of View
	(properties
		noun 7
		sightAngle 40
		approachX 152
		approachY 108
		x 139
		y 78
		priority 70
		fixPriority 1
		view 3311
		loop 4
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 4 1 2 5 6)
		(if (Btst 220) (= cel 0) else (= cel 1))
	)
	
	(method (doVerb theVerb)
		(cond 
			((and (== theVerb 5) (not (Btst 180))) (curRoom setScript: (ScriptID 333 3) 0 1))
			((OneOf theVerb 4 1 5) (messager say: noun theVerb (if (Btst 220) 5 else 0)))
			(else (super doVerb: theVerb))
		)
	)
)

(instance lid of Prop
	(properties
		noun 9
		sightAngle 40
		approachX 152
		approachY 108
		x 137
		y 71
		view 330
		loop 4
		cycleSpeed 13
	)
	
	(method (init)
		(super init: &rest)
		(if (Btst 30) (self view: 3311 loop: 0 cel: 3))
		(self approachVerbs: 4 1 2 5 6 64 33 32)
	)
	
	(method (doVerb theVerb)
		(cond 
			((not (Btst 30)) (filterTank doVerb: theVerb))
			((== theVerb 4) (curRoom setScript: (ScriptID 333 2)))
			(else (super doVerb: theVerb))
		)
	)
	
	(method (cue)
		(theMusic2 number: 367 loop: 1 play:)
		((ScriptID 333 2) cue:)
	)
)

(instance filterTank of Feature
	(properties
		noun 8
		sightAngle 40
		approachX 148
		approachY 108
		x 139
		y 78
	)
	
	(method (init)
		(super init: &rest)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 124 73 137 73 156 74 157 90 147 93 149 105 137 106 125 105 122 100
					yourself:
				)
			approachVerbs: 4 1 2 5 6 64 33 32
		)
	)
	
	(method (doVerb theVerb &tmp temp0)
		(= temp0
			(cond 
				((Btst 30) 8)
				((not (Btst 29)) 7)
				(else 17)
			)
		)
		(cond 
			((and (== theVerb 64) (not (Btst 33)))
				(if (OneOf temp0 7 17)
					(curRoom setScript: (ScriptID 333 1))
				else
					(messager say: noun theVerb 8)
				)
			)
			((== theVerb 64) (messager say: 0 0 32))
			((== theVerb 33)
				(if (== temp0 8)
					(curRoom setScript: (ScriptID 333 3) 0 0)
				else
					(messager say: noun theVerb temp0)
				)
			)
			((== theVerb 4)
				(if (!= temp0 7)
					(curRoom setScript: (ScriptID 333 2))
				else
					(messager say: noun theVerb temp0)
				)
			)
			((== theVerb 32) (messager say: noun theVerb temp0))
			((== theVerb 1)
				(messager
					say:
						noun
						1
						(if (== temp0 8)
							(cond 
								((Btst 180) 5)
								((Btst 179) 16)
								(else temp0)
							)
						else
							temp0
						)
				)
			)
			(else (super doVerb: theVerb))
		)
	)
)

(instance probes of Feature
	(properties
		noun 22
		sightAngle 40
		approachX 195
		approachY 131
		x 176
		y 119
	)
	
	(method (init)
		(super init: &rest)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 189 45 186 77 175 76 171 70 150 66 150 46
					yourself:
				)
			approachVerbs: 4
		)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(cond 
				((not (cast contains: gammie))
					(if (Btst 20)
						(messager say: noun theVerb 37)
					else
						(messager say: noun theVerb)
					)
				)
				((not (Btst 184)) (gammie doVerb: theVerb))
				((not (Btst 87)) (messager say: noun theVerb 36))
				(else (messager say: noun theVerb 32))
			)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance gammie of Actor
	(properties
		noun 11
		sightAngle 40
		approachX 195
		approachY 131
		x 175
		y 122
		z 27
		view 333
		loop 1
	)
	
	(method (init)
		(super init: &rest)
		(if (not global205) (proc79_8 2))
		(self approachVerbs: 4 34)
	)
	
	(method (dispose)
		(if global205 (proc79_7))
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(cond 
			((and (== theVerb 4) (not (Btst 184))) (curRoom setScript: hookUpGammieScr))
			((and (== theVerb 4) (not (Btst 87))) (messager say: noun theVerb 36))
			(
				(or
					(OneOf theVerb 1 2 4 6)
					(and (== (proc330_2) 13) (OneOf theVerb 35 36))
				)
				(messager say: noun theVerb (proc330_2))
			)
			((and (== theVerb 37) (== (proc330_2) 13)) (curRoom setScript: giveClothScr))
			((and (== theVerb 38) (== (proc330_2) 14)) (curRoom setScript: giveWaterScr))
			(
			(and (== theVerb 34) (OneOf (proc330_2) 11 12))
				(cond 
					((not (Btst 183)) (messager say: 11 34 12 1))
					((Btst 184) (curRoom setScript: giveOrangeScr))
					(else (messager say: noun theVerb))
				)
			)
			(else (super doVerb: theVerb))
		)
	)
)

(instance hoseWithHole of Prop
	(properties
		noun 6
		nsLeft 204
		nsTop 67
		nsRight 244
		nsBottom 82
		sightAngle 40
		approachX 214
		approachY 97
		x 224
		y 79
		z 4
		view 3310
		loop 2
		cel 1
	)
	
	(method (init)
		(super init: &rest)
		(if (Btst 182) (self cue:))
		(self approachVerbs: 4 1 2 5 6 11)
	)
	
	(method (doVerb theVerb)
		(cond 
			((and (== theVerb 11) (not (Btst 33))) (curRoom setScript: (ScriptID 333 0)))
			((== theVerb 11) (messager say: 0 0 32))
			((Btst 182) (messager say: noun 0 33))
			(else (super doVerb: theVerb))
		)
	)
	
	(method (cue)
		(self view: 330 loop: 3 cel: 0 x: 224 y: 79 z: 4)
	)
)

(instance wheel of Prop
	(properties
		x 12
		y 110
		priority 92
		fixPriority 1
		view 336
		loop 1
		cycleSpeed 3
	)
	
	(method (onMe)
		(return 0)
	)
)

(instance intArc of Actor
	(properties
		noun 12
		x 262
		y 149
		z 50
		priority 200
		fixPriority 1
		view 336
		loop 8
	)
	
	(method (init)
		(super init: &rest)
		(self hide: setScript: arcScr)
	)
)

(instance arc of Actor
	(properties
		noun 18
		x 284
		y 38
		view 336
		cycleSpeed 10
	)
)

(instance boobs of Prop
	(properties
		noun 24
		sightAngle 40
		x 91
		y 71
		view 330
		loop 1
		signal $5021
	)
)

(instance table of Feature
	(properties
		noun 21
		sightAngle 40
		approachX 176
		approachY 132
		x 184
		y 121
	)
	
	(method (init)
		(super init: &rest)
		(self
			setPolygon:
				((Polygon new:)
					init:
						150
						120
						144
						92
						180
						88
						189
						82
						195
						82
						217
						89
						217
						96
						211
						97
						210
						125
						181
						131
						165
						125
					yourself:
				)
			approachVerbs: 4 1 2 5 6 30
		)
	)
)

(instance blueVagina of Feature
	(properties
		noun 3
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					init: 64 82 64 76 74 74 106 76 115 72 123 72 120 107 98 113 65 111
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance redPipe of Feature
	(properties
		noun 23
		sightAngle 40
		x 137
		y 100
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					init:
						145
						0
						145
						16
						154
						16
						155
						30
						147
						30
						147
						35
						141
						41
						141
						56
						133
						56
						133
						40
						126
						40
						126
						32
						120
						29
						120
						16
						130
						16
						130
						0
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance tanks of PolyFeature
	(properties
		noun 19
		sightAngle 40
		variableX 1
		variableY 1
	)
	
	(method (init)
		(super init: &rest)
		(self
			addPolygon:
				((Polygon new:)
					init:
						0
						21
						119
						21
						124
						34
						133
						41
						133
						56
						123
						71
						113
						71
						114
						62
						74
						62
						74
						73
						64
						76
						54
						89
						40
						98
						41
						107
						31
						112
						31
						100
						16
						86
						0
						86
					yourself:
				)
				((Polygon new:)
					init:
						140
						42
						147
						29
						155
						19
						319
						18
						319
						101
						309
						100
						313
						64
						298
						63
						309
						36
						278
						58
						257
						35
						257
						44
						263
						64
						248
						67
						252
						98
						238
						96
						231
						89
						217
						91
						200
						82
						185
						83
						184
						88
						157
						89
						159
						75
						141
						57
					yourself:
				)
		)
	)
)

(instance electrode1 of Feature
	(properties
		noun 26
		sightAngle 40
		x 274
		y 109
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					init: 265 65 257 46 256 35 278 58 309 36 298 65 280 67
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance electrode2 of Feature
	(properties
		noun 26
		sightAngle 40
		x 274
		y 139
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					init:
						231
						104
						246
						110
						267
						127
						282
						123
						290
						92
						296
						77
						305
						93
						310
						126
						319
						135
						319
						139
						240
						139
						230
						116
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance lever of Prop
	(properties
		noun 2
		sightAngle 40
		approachX 248
		approachY 122
		x 266
		y 109
		z 21
		priority 115
		fixPriority 1
		view 330
		loop 2
	)
	
	(method (init)
		(super init: &rest)
		(self
			setPolygon:
				((Polygon new:)
					init: 297 64 313 64 310 111 282 119 254 113 249 67
					yourself:
				)
		)
	)
	
	(method (doVerb theVerb)
		(cond 
			((== theVerb 4)
				(cond 
					((Btst 187) (messager say: noun theVerb 15))
					((and (Btst 179) (not (Btst 180))) (messager say: noun theVerb 16))
					((Btst 29) (messager say: noun theVerb 22))
					(
					(and (not (Btst 184)) (cast contains: gammie)) (ego setHeading: 310) (messager say: noun theVerb 38))
					((and (cast contains: gammie) (not (Btst 87))) (self setScript: startSuckScr))
					((Btst 87) (messager say: noun theVerb 30))
					((not (Btst 33)) (self setScript: turnOnMachineScr))
					(else (self setScript: turnOffMachineScr))
				)
			)
			((and (== theVerb 1) (not (Btst 33))) (messager say: noun theVerb 19))
			((== theVerb 6) (messager say: noun theVerb))
			(else (messager say: 2 0 (if (Btst 33) 20 else 19)))
		)
	)
)

(instance pistonShaft of Prop
	(properties
		noun 13
		sightAngle 40
		approachX 58
		approachY 125
		x 60
		y 93
		view 330
		signal $4021
		cycleSpeed 11
	)
	
	(method (init)
		(super init: &rest)
		(if (Btst 177) (self view: 336 loop: 2))
	)
	
	(method (doVerb theVerb)
		(cond 
			((OneOf theVerb 4 1)
				(messager
					say: noun theVerb (cond 
						((Btst 177) 2)
						(local0 18)
						(else 1)
					)
				)
			)
			((== theVerb 30)
				(cond 
					((Btst 177) (messager say: noun theVerb 2))
					((Btst 33) (messager say: 0 0 32))
					(local0 (messager say: noun 4 18))
					(else (curRoom setScript: (ScriptID 333 4) 0 theVerb))
				)
			)
			((== theVerb 17)
				(cond 
					((Btst 177) (messager say: noun theVerb 2))
					((Btst 33) (messager say: 0 0 32))
					(local0 (messager say: noun 4 18))
					(else (curRoom setScript: (ScriptID 333 4) 0 theVerb))
				)
			)
			(else (super doVerb: theVerb))
		)
	)
)

(instance explosion2 of Prop
	(properties
		x 138
		y 71
		priority 100
		fixPriority 1
		view 334
		loop 2
	)
)

(instance explosion1 of Prop
	(properties
		x 76
		y 88
		view 334
	)
	
	(method (cue)
		(= local0 0)
		(self dispose:)
	)
)

(instance clog of Prop
	(properties
		x 123
		y 96
		view 3310
	)
)

(instance siphons of Prop
	(properties
		sightAngle 40
		approachX 195
		approachY 131
		x 172
		y 119
		z 65
		priority 125
		fixPriority 1
		view 330
		loop 5
		cel 1
		signal $5021
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 4)
	)
	
	(method (doVerb theVerb)
		(probes doVerb: theVerb)
	)
)

(instance pipe1 of Prop
	(properties
		x 34
		y 57
		view 336
		loop 5
		cel 3
		cycleSpeed 30
	)
	
	(method (cue)
		(pipe1 dispose:)
		(pipe2 dispose:)
		(pipe3 dispose:)
		(hoseWithHole init:)
	)
)

(instance pipe2 of Prop
	(properties
		x 123
		y 96
		view 336
		loop 6
		cel 2
		cycleSpeed 30
	)
)

(instance pipe3 of Prop
	(properties
		x 225
		y 75
		view 336
		loop 7
		cel 1
		cycleSpeed 20
	)
)

(instance vat1 of Prop
	(properties
		x 25
		y 91
		view 3313
		cycleSpeed 150
	)
)

(instance vat2 of Prop
	(properties
		x 50
		y 84
		view 3313
		loop 1
		cycleSpeed 150
	)
)

(instance vat3 of Prop
	(properties
		x 247
		y 74
		view 3313
		loop 2
		cycleSpeed 150
	)
)

(instance vat4 of Prop
	(properties
		x 203
		y 74
		view 3313
		loop 3
		cycleSpeed 150
	)
)

(instance vat5 of Prop
	(properties
		x 125
		y 67
		view 3313
		loop 4
		cycleSpeed 150
	)
	
	(method (cue)
	)
)

(instance washCloth of View
	(properties
		x 202
		y 79
		priority 125
		fixPriority 1
		view 335
		loop 2
	)
)

(instance trySpigot of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego view: 901 loop: 2 cel: 0 setCycle: CT 3 1 self)
			)
			(1
				(messager say: 4 4 (if (Btst 20) 31 else 0) 0 self)
			)
			(2 (ego setCycle: End self))
			(3
				(ego normalize: 900 8 cel: 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance spigot of View
	(properties
		noun 4
		sightAngle 40
		approachX 131
		approachY 140
		x 138
		y 158
		z 56
		view 330
		loop 6
		cel 1
	)
	
	(method (doVerb theVerb)
		(cond 
			((and (== theVerb 39) (Btst 20)) (curRoom setScript: fillLampScr))
			((and (OneOf theVerb 63 1 5 2) (Btst 20)) (messager say: noun theVerb 31))
			((== theVerb 4) (curRoom setScript: trySpigot))
			(else (super doVerb: theVerb))
		)
	)
	
	(method (onMe theObjOrX)
		(return
			(if (super onMe: theObjOrX)
				(if (== (theObjOrX message?) 4)
					(= approachX 130)
					(= approachY 137)
				else
					(= approachX 131)
					(= approachY 140)
				)
				(return 1)
			else
				0
			)
		)
	)
)

(instance pipes of PolyFeature
	(properties
		noun 20
		sightAngle 40
		variableX 1
		variableY 1
	)
	
	(method (init)
		(super init: &rest)
		(self
			addPolygon:
				((Polygon new:)
					init: 122 90 130 88 144 88 143 95 119 103 104 96 109 89
					yourself:
				)
				((Polygon new:)
					init: 13 56 36 52 54 56 49 65 36 61 20 65 14 59
					yourself:
				)
		)
	)
)

(instance machineHumFx of Sound
	(properties
		flags $0005
		number 335
	)
)

(instance leftBoobFx of Sound
	(properties
		flags $0005
		number 363
	)
)

(instance rightBoobFx of Sound
	(properties
		flags $0005
		number 364
	)
)

(instance hoseFlappingFx of Sound
	(properties
		flags $0005
		number 365
	)
)

(instance longArcFx of Sound
	(properties
		flags $0005
		number 331
	)
)

(instance shortArcFx of Sound
	(properties
		flags $0005
		number 332
	)
)

(instance steamFx of Sound
	(properties
		flags $0005
		number 336
	)
)

(instance pistonFx of Sound
	(properties
		flags $0005
		number 333
	)
)

(instance startSuckScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= ticks 30)
			)
			(1 (ego setHeading: 310 self))
			(2 (= cycles 2))
			(3
				(messager sayRange: 2 4 11 1 2 self)
			)
			(4 (ego setHeading: 45 self))
			(5 (= cycles 2))
			(6
				(self setScript: turnOnMachineScr self)
			)
			(7
				(if (not (lever cel?))
					(self dispose:)
				else
					(self setScript: suckingFatScr)
					(= cycles 2)
				)
			)
			(8
				(Bset 87)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance startWheelScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (timers contains: explosionTimer)
					(explosionTimer dispose:)
				)
				(= register 25)
				(= cycles 2)
			)
			(1
				(boobs
					view: 336
					loop: 4
					cel: 0
					setScript: boobScr
					cycleSpeed: register
				)
				(wheel init: setCycle: Fwd cycleSpeed: register)
				(if (Btst 177)
					(pistonShaftScr register: 1)
					(pistonFx number: 334)
					(pistonShaft loop: 2)
				else
					(pistonShaftScr register: 0)
					(pistonFx number: 333)
					(pistonShaft loop: 3)
				)
				(pistonShaft
					view: 336
					cycleSpeed: register
					setScript: pistonShaftScr
				)
				(= cycles 2)
			)
			(2
				(if
					(and
						(== register 15)
						(not (Btst 183))
						(Btst 177)
						(Btst 180)
						(Btst 182)
					)
					(messager say: 2 4 10 1 self)
				)
				(if
					(or
						(and (not (Btst 177)) (> (-- register) 18))
						(and (Btst 177) (-- register))
					)
					(wheel cycleSpeed: register)
					(pistonShaft cycleSpeed: register)
					(boobs cycleSpeed: register)
					(-- state)
					(if (not (Btst 177)) (= ticks 45) else (= ticks 30))
				else
					(= cycles 2)
				)
			)
			(3
				(if (not (Btst 177))
					(if (not (cast contains: explosion1))
						(= local0 1)
						(explosion1 init: loop: 0 setCycle: Fwd)
					)
					(= ticks 240)
				else
					(self dispose:)
				)
			)
			(4
				(theGame handsOff:)
				(self setScript: (ScriptID 332 1) self)
			)
			(5 (EgoDead 9 self))
			(6
				(proc330_6)
				(ego
					view: 900
					setLoop: -1
					posn: (lever approachX?) (lever approachY?)
					loop: 8
					cel: 6
				)
				(theGame handsOn:)
				(client dispose:)
			)
		)
	)
)

(instance arcScr of Script
	(properties)
	
	(method (doit)
		(if
			(or
				(!= (ego view?) 900)
				(and
					(or (talkers size:) (Print dialog?))
					(Print dialog?)
				)
			)
			(shortArcFx number: 0 loop: 1 stop:)
			(client hide:)
		)
		(super doit:)
	)
	
	(method (dispose)
		(if (shortArcFx handle?) (shortArcFx number: 0 stop:))
		(super dispose:)
	)
	
	(method (changeState newState)
		(asm
			lap      newState
			aTop     state
			push    
			dup     
			ldi      0
			eq?     
			bnt      code_23e1
			lal      local4
			bnt      code_23cd
			ldi      0
			sal      local4
			jmp      code_23d1
code_23cd:
			ldi      1
			sal      local4
code_23d1:
			pushi    2
			pushi    180
			pushi    360
			callk    Random,  4
			aTop     ticks
			jmp      code_2499
code_23e1:
			dup     
			ldi      1
			eq?     
			bnt      code_2466
			pushi    #show
			pushi    0
			pushi    236
			pushi    1
			class    Fwd
			push    
			pToa     client
			send     10
			pushi    #size
			pushi    0
			lag      talkers
			send     4
			bt       code_240d
			pushi    #dialog
			pushi    0
			class    Print
			send     4
			bnt      code_240d
code_240d:
			not     
			bnt      code_245f
			lsl      local4
			dup     
			ldi      1
			eq?     
			bnt      code_243d
			pushi    #number
			pushi    1
			pushi    651
			pushi    15
			pushi    1
			pushi    65535
			pushi    51
			pushi    0
			lofsa    shortArcFx
			send     16
			pushi    2
			pushi    60
			pushi    180
			callk    Random,  4
			aTop     ticks
			jmp      code_245b
code_243d:
			dup     
			ldi      0
			eq?     
			bnt      code_245b
			pushi    #number
			pushi    1
			pushi    651
			pushi    15
			pushi    1
			pushi    1
			pushi    51
			pushi    0
			lofsa    shortArcFx
			send     16
			ldi      60
			aTop     ticks
code_245b:
			toss    
			jmp      code_2499
code_245f:
			ldi      90
			aTop     ticks
			jmp      code_2499
code_2466:
			dup     
			ldi      2
			eq?     
			bnt      code_2499
			pushi    #number
			pushi    1
			pushi    0
			pushi    15
			pushi    1
			pushi    1
			pushi    248
			pushi    0
			lofsa    shortArcFx
			send     16
			pushi    #hide
			pushi    0
			pushi    236
			pushi    1
			pushi    0
			pToa     client
			send     10
			pushi    #state
			pushi    1
			pushi    65535
			pushi    174
			pushi    1
			pushi    2
			self     12
code_2499:
			toss    
			ret     
		)
	)
)

(instance giveOrangeScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(theGame changeScore: 6 185)
				(cond 
					((!= (gammie script?) suckingFatScr)
						(gammie
							setScript: (suckingFatScr start: 1 register: 1 yourself:)
						)
					)
					((!= (gammie cel?) 1) (gammie cel: 1))
				)
				(= cycles 2)
			)
			(1
				(ego setScript: navStairScr self 1)
			)
			(2 (= cycles 2))
			(3
				(ego put: 27)
				(messager say: 11 34 12 0 self)
			)
			(4
				(turnEgoScr client: turnEgoScr register: 0)
				(ego setScript: navStairScr turnEgoScr 0)
				(= cycles 2)
			)
			(5
				(self setScript: eatOrangeScr self)
			)
			(6
				(ego normalize: 900 8 cel: 3)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance eatOrangeScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles (= register 2)))
			(1
				(gammie loop: 2 cel: 0 setCycle: End self)
			)
			(2 (= cycles 2))
			(3 (gammie setCycle: Beg self))
			(4
				(gammie loop: 3 cel: 0 cycleSpeed: 18 setCycle: Fwd)
				(= ticks 180)
			)
			(5
				(if (-- register) (= state 0))
				(= cycles 2)
			)
			(6
				(gammie setCycle: 0 loop: 1 cel: 1)
				(self dispose:)
			)
		)
	)
)

(instance giveClothScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(cond 
					((!= (gammie script?) suckingFatScr) (gammie setScript: (suckingFatScr start: 2 yourself:)))
					((!= (gammie cel?) 3) (gammie cel: 3))
				)
				(= cycles 2)
			)
			(1
				(ego setScript: navStairScr self 1)
			)
			(2 (= cycles 2))
			(3
				(messager sayRange: 11 37 13 1 2 self)
			)
			(4 (= ticks 40))
			(5
				(ego
					view: 335
					setLoop: 1
					setCel: 0
					setCycle: 0
					cycleSpeed: 12
				)
				(= cycles 2)
			)
			(6 (ego setCycle: CT 1 1 self))
			(7
				(theGame changeScore: 6 186)
				(ego put: 39 curRoomNum)
				(washCloth init:)
				(ego setCycle: End self)
			)
			(8
				(ego normalize: 900 8 setPri: 200 setCel: 3)
				(= ticks 45)
			)
			(9
				(messager
					say:
						0
						0
						(switch global185
							(0 21)
							(1 24)
							(2 27)
							(3 23)
							(4 26)
							(5 40)
							(6 25)
							(8 39)
							(7 28)
						)
						0
						self
				)
			)
			(10 (= ticks 60))
			(11
				(messager sayRange: 11 37 13 3 4 self)
			)
			(12
				(ego setScript: navStairScr self 0)
			)
			(13
				(ego normalize: 900 8 setCel: 2)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance turnOffMachineScr of Script
	(properties)
	
	(method (dispose)
		(= register 0)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 2)
			)
			(1
				(if (cast contains: clog) (clog setCycle: 0))
				(self setScript: flipLeverScr self 0)
			)
			(2
				(longArcFx stop:)
				(machineHumFx stop:)
				(machineHumFx number: 369 loop: 1 play:)
				(if register (register cue:))
				(Bclr 33)
				(if (cast contains: arc) (arc dispose:))
				(if (cast contains: intArc) (intArc dispose:))
				(= cycles 2)
			)
			(3
				(if
				(or (pistonShaft cycler?) (pistonShaft script?))
					(self setScript: stopWheelScr self)
				else
					(= cycles 2)
				)
			)
			(4
				(if (hoseWithHole script?)
					((hoseWithHole script?) dispose:)
				)
				(if (cast contains: clog)
					(clog setCycle: Beg self)
				else
					(if (not register) (theGame handsOn:))
					(self dispose:)
				)
			)
			(5
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance flipLeverScr of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(cond 
					((ResCheck 140 383) (Load 140 383))
					((ResCheck 141 383) (Load 141 383))
					((ResCheck 132 383) (Load rsSOUND 383))
				)
				(ego setCycle: 0 view: 331 loop: 4 setSpeed: 10)
				(ego cel: (if (not register) (ego lastCel:) else 0))
				(= cycles 2)
			)
			(1
				(lever hide:)
				(ego setCycle: (if register End else Beg) self)
			)
			(2
				(theMusic2 number: 383 loop: 1 play: self)
				(if register
					(lever show: cel: 1)
					(Bset 33)
				else
					(lever show: cel: 0)
					(Bclr 33)
				)
				(ego normalize: 900 8 cel: 6)
				(= cycles 2)
			)
			(3 (self dispose:))
		)
	)
)

(instance stopWheelScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (cast contains: explosion1)
					(explosionTimer setReal: explosion1 20)
				)
				(= register (wheel cycleSpeed?))
				(= cycles 2)
			)
			(1
				(if (hoseWithHole script?)
					(hoseWithHole cycleSpeed: (+ register 6))
				)
				(boobs cycleSpeed: register)
				(wheel cycleSpeed: register)
				(if (Btst 177)
					(pistonShaft loop: 2)
				else
					(pistonShaft loop: 3)
				)
				(pistonShaft cycleSpeed: register)
				(= cycles 2)
			)
			(2
				(if (<= (++ register) 25)
					(if (hoseWithHole script?)
						(hoseWithHole cycleSpeed: (+ register 6))
					)
					(wheel cycleSpeed: register)
					(pistonShaft cycleSpeed: register)
					(boobs cycleSpeed: register)
					(-- state)
					(= ticks 10)
				else
					(= cycles 2)
				)
			)
			(3
				(pistonShaft setCycle: End self)
			)
			(4
				(wheel dispose:)
				(boobs view: 330 loop: 1 cel: 0 setCycle: 0 setScript: 0)
				(if (Btst 177)
					(pistonShaft view: 336 loop: 2 cel: 0)
				else
					(pistonShaft view: 330 loop: 0)
				)
				(self dispose:)
			)
		)
	)
)

(instance turnOnMachineScr of Script
	(properties)
	
	(method (dispose)
		(= register 0)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(LoadMany 128 336 334)
				(= cycles 2)
			)
			(1
				(self setScript: flipLeverScr self 1)
			)
			(2
				(machineHumFx number: 335 loop: 1 play: changeHummScr)
				(Bset 33)
				(arc init: setCycle: Fwd)
				(longArcFx play: setLoop: -1)
				(if (> howFast 3) (intArc init:))
				(cond 
					(register (self dispose:))
					((cast contains: gammie)
						(ego setHeading: 310)
						(messager sayRange: 2 4 11 3 4 self)
					)
					(else (= state (+ state 2)) (self cue:))
				)
			)
			(3
				(if
				(and (not (Btst 112)) (OneOf (proc330_2) 11 12))
					(Bset 112)
					(messager say: 2 4 11 5 self)
				else
					(++ state)
					(self cue:)
				)
			)
			(4
				(messager sayRange: 2 4 11 6 8 self)
			)
			(5
				(if (!= (lever script?) startSuckScr)
					(theGame handsOn:)
				)
				(= cycles 2)
			)
			(6
				(self setScript: startWheelScr self)
			)
			(7 (= cycles 2))
			(8
				(if (not (Btst 182))
					(hoseWithHole cycleSpeed: 6 setScript: hoseScr)
					(= ticks 180)
				else
					(++ state)
					(= cycles 2)
				)
			)
			(9 (ego setHeading: 310 self))
			(10
				(if (not (Btst 182))
					(messager sayRange: 2 4 3 1 2 self)
				else
					(= cycles 2)
				)
			)
			(11
				(cond 
					((not (Btst 180)) (self setScript: (ScriptID 332 0)))
					((not (Btst 182)) (self setScript: turnOffMachineScr self))
					(else (++ state) (= cycles 2))
				)
			)
			(12
				(theGame handsOn:)
				(self dispose:)
			)
			(13
				(if (Btst 183) (self dispose:) else (= ticks 120))
			)
			(14
				(if (theGame changeScore: 10 183)
					(= cycles 2)
				else
					(self dispose:)
				)
			)
			(15
				(Bset 183)
				(if (and (cast contains: gammie) (Btst 21))
					(messager sayRange: 2 4 10 2 3 self)
				else
					(messager sayRange: 2 4 10 2 4 self)
				)
			)
			(16 (self dispose:))
		)
	)
)

(instance giveWaterScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (!= (gammie cel?) 4) (gammie cel: 4))
				(= register 4)
				(= cycles 2)
			)
			(1
				(theGame changeScore: 6 187)
				(theIconBar enableIcon: (ScriptID 0 8) show:)
				(= gLarryRoom curRoom)
				(messager sayRange: 11 38 14 1 2 self)
			)
			(2
				(ego
					setMotion: PolyPath (lever approachX?) (lever approachY?) self
				)
			)
			(3
				(lever setScript: turnOffMachineScr pipe1 self)
				(vat1 setCycle: 0)
				(vat2 setCycle: 0)
				(vat3 setCycle: 0)
				(vat4 setCycle: 0)
				(vat5 setCycle: 0)
			)
			(4 (= cycles 2))
			(5
				(ego
					setMotion: PolyPath (gammie approachX?) (gammie approachY?) self
				)
			)
			(6
				(ego setScript: navStairScr self 1)
			)
			(7 (= cycles 2))
			(8
				(siphons dispose:)
				(ego
					setSpeed: 12
					view: 335
					cel: 8
					loop: 0
					setCycle: Beg self
				)
			)
			(9 (= cycles 2))
			(10
				(ego put: 26 curRoomNum)
				(ego normalize: 900 8 setPri: 125 cel: 3)
				(= ticks 30)
			)
			(11
				(ego setScript: navStairScr self 0)
			)
			(12
				(ego setScript: startWatching)
				(washCloth dispose:)
				(gammie
					view: 3312
					setLoop: 0
					setCel: 0
					setCycle: End self
				)
			)
			(13
				(gammie z: 0 posn: 187 120 setLoop: 1 setCel: 2)
				(= cycles 2)
			)
			(14
				(if (-- register)
					(theMusic2 number: 517 loop: 1 play:)
					(-- state)
				else
					(= register 4)
				)
				(= ticks 60)
			)
			(15 (gammie setCycle: Beg self))
			(16
				(gammie
					view: 332
					setSpeed: 8
					posn: 187 121
					setCycle: Walk
					setMotion: MoveTo 199 135 self
				)
			)
			(17 (= ticks 30))
			(18
				(messager sayRange: 11 38 14 3 4 self)
			)
			(19
				(siphons priority: 9)
				(gammie view: 339 loop: 1 setSpeed: 10 setCycle: End self)
			)
			(20 (= ticks 60))
			(21
				(gammie setSpeed: 8 setCycle: CT 4 -1 self)
			)
			(22
				(gammie loop: 0 cel: 0 setSpeed: 10 setCycle: End self)
			)
			(23 (= ticks 30))
			(24
				(messager say: 11 38 14 5 self)
			)
			(25 (= ticks 120))
			(26 (gammie setCycle: Beg self))
			(27
				(gammie setSpeed: 8 loop: 1 cel: 4 setCycle: Beg self)
			)
			(28
				(gammie loop: 2 cel: 0 setCycle: CT 1 1 self)
			)
			(29 (= ticks 45))
			(30 (gammie setCycle: End self))
			(31 (= ticks 45))
			(32
				(gammie setCycle: CT 1 -1 self)
			)
			(33 (= ticks 20))
			(34
				(messager sayRange: 11 38 14 6 8 self)
			)
			(35
				(ego setHeading: 180)
				(messager say: 11 38 14 9)
				(gammie
					view: 332
					loop: 2
					setCycle: Walk
					setSpeed: 7
					setMotion: MoveTo (gammie x?) 190 self
				)
				(Bset 20)
				(= global171 9)
			)
			(36
				(dummyView dispose:)
				(= useSortedFeatures 1)
				(gammie dispose:)
				(= gLarryRoom 0)
				(theIconBar disableIcon: (ScriptID 0 8) show:)
				(= cycles 2)
			)
			(37
				(messager sayRange: 11 38 14 10 11 self)
			)
			(38 (= cycles 2) (proc78_0))
			(39
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance fillLampScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					view: 338
					setLoop: 0 1
					cel: 0
					setCycle: 0
					cycleSpeed: 12
					posn: 140 138
				)
				(= register 0)
				(= ticks 30)
			)
			(1
				(ego ignoreActors: 1 setCycle: End self)
			)
			(2
				(ego loop: 1 cel: 0)
				(= ticks 10)
			)
			(3
				(++ register)
				(if (< register 9)
					(theMusic2 number: 334 loop: 1 play:)
				)
				(if (< register 10) (-- state))
				(ego cel: register)
				(= ticks 30)
			)
			(4
				(theGame changeScore: 15 188)
				((inventory at: 23) cue:)
				(ego
					posn: (spigot approachX?) (spigot approachY?)
					normalize: 900 8
					cel: 6
				)
				(= cycles 2)
			)
			(5
				(messager say: 4 39 31 0 self)
			)
			(6
				(ego setMotion: PolyPath 114 136 self)
			)
			(7
				(theGame handsOn:)
				((theIconBar curIcon?) select:)
				(self dispose:)
			)
		)
	)
)
