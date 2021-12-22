;;; Sierra Script 1.0 - (do not remove this comment)
(script# 280)
(include sci.sh)
(use Main)
(use fileScr)
(use OccCyc)
(use LarryRoom)
(use CycleBet)
(use Print)
(use PolyPath)
(use Polygon)
(use Feature)
(use ForCount)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm280 0
)

(local
	local0
	local1
)
(instance rm280 of LarryRoom
	(properties
		noun 1
		picture 280
		horizon 0
	)
	
	(method (init)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init: 178 61 214 65 229 71 269 72 269 66 286 56 214 61 183 56
					yourself:
				)
		)
		(proc79_6 1250)
		(super init: &rest)
		(narrator modeless: 2)
		(windSFx loop: -1 play: setVol: 127)
		(theMusic mute: 0 8)
		(theIconBar enableIcon: (ScriptID 0 8) show:)
		(= gLarryRoom curRoom)
		(ego view: 282 loop: 0 cel: 0 posn: 270 66 init:)
		(merrily init: approachVerbs: 4 2)
		(japan init:)
		(ladder init:)
		(beacon1 init: setPri: 199 cycleSpeed: 14 setCycle: Fwd)
		(beacon2 init: setPri: 199 cycleSpeed: 14 setCycle: Fwd)
		(rope1 init:)
		(rope2 init:)
		(harness init:)
		(theShip init:)
		(curRoom setScript: egoEnters)
	)
	
	(method (dispose)
		(theMusic stop:)
		(DisposeScript -580)
		(super dispose:)
	)
	
	(method (cue)
		(PalVary 8 280)
		(PalVary 0 280 0 64 1)
		(if (talkers size:) (messager cue: 1))
		(= gGButtonBarGetCursor theCursor)
		(theGame setCursor: normalCursor)
		(SetCursor 255 105)
		(if
			(Print
				width: 200
				font: userFont
				addTitle: 0 8 0 2 280
				addText: 0 8 0 1 50 1 280
				addIcon: 1911 0 0 0 0
				addButton: 0 10 8 0 1 50 33 280
				addButton: 1 9 8 0 1 200 33 280
				init:
			)
			(theMusic stop:)
			((inventory at: 35) owner: -1)
			((inventory at: 20) owner: -1)
			((inventory at: 2) owner: -1)
			(ego get: 40)
			(Bclr 74)
			(UpdateScreenItem ((ScriptID 92 3) view: 1900 loop: 1))
			(theIconBar disableIcon: (ScriptID 0 8) show:)
			(theGame
				handsOff:
				changeScore: 20 174
				setCursor: waitCursor 1
				hideControls:
			)
			(PalVary 3)
			(Bclr 8)
			(cast eachElementDo: #hide)
			(thePlane drawPic: -1)
			(curRoom newRoom: 620)
		else
			(= gLarryRoom curRoom)
			(theIconBar enableIcon: (ScriptID 0 8) show:)
			(theGame setCursor: gGButtonBarGetCursor)
		)
	)
)

(instance touchMerrily of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					view: 901
					loop: 3
					setCel: 0
					setSpeed: 12
					setCycle: CT 3 1 self
				)
			)
			(1
				(ego setCycle: CycleBet 3 4 -1)
				(= ticks 30)
			)
			(2
				(messager say: 6 4 register 0 self)
			)
			(3
				(switch local0
					(1
						(theMusic mute: 0 9 mute: 0 4)
					)
					(2
						(theMusic mute: 0 6 mute: 0 7)
					)
					(3
						(ego actions: egoActions)
						(theMusic mute: 0 5)
					)
				)
				(ego setCycle: End self)
			)
			(4
				(ego normalize: 900 1 setSpeed: 6)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance egoEnters of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Load rsVIEW 900)
				(= cycles 2)
			)
			(1 (messager say: 1 0 8 0 self))
			(2 (= ticks 30))
			(3
				(ego setSpeed: 12 setCycle: End self)
			)
			(4
				(ego
					normalize: 900 1
					setSpeed: 6
					setMotion: PolyPath 233 63 self
				)
			)
			(5
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance undress of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= local1 1)
				(merrily approachVerbs: 0)
				(ego setMotion: PolyPath 249 74 self)
			)
			(1
				(ego
					setSpeed: 12
					view: 282
					loop: 7
					setCel: 0
					setCycle: End self
				)
			)
			(2
				(ego view: 282 loop: 8 setCel: 0 setCycle: End self)
			)
			(3
				(UpdateScreenItem ((ScriptID 92 3) view: 1901))
				(merrily
					setLoop: 2
					setCel: 0
					cycleSpeed: 12
					setCycle: CT 2 1 self
				)
			)
			(4
				(harness dispose:)
				(merrily setCycle: End self)
			)
			(5
				(merrily loop: 4 setCel: 0 cycleSpeed: 6 posn: 207 58)
				(Bset 74)
				(UpdateScreenItem ((ScriptID 92 3) view: 1901))
				(theGame handsOn:)
				(theIconBar disableIcon: (ScriptID 0 3) show:)
				(self dispose:)
			)
		)
	)
)

(instance bungeeJump of Script
	(properties)
	
	(method (doit)
		(cond 
			((not (== (self state?) 20)))
			((== (ego cel?) 0) (sFx number: 291 play:))
			(
			(and (== (sFx number?) 291) (== (ego cel?) 5)) (sFx number: 292 play:))
		)
		(super doit: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 6 6 4 0 self)
			)
			(1 (messager say: 6 6 7 1 self))
			(2
				(ego
					view: 282
					loop: 1
					setCel: 0
					setSpeed: 6
					setCycle: Walk
					setMotion: PolyPath 195 63 self
				)
			)
			(3
				(ego loop: 2 setCel: 0)
				(= cycles 2)
			)
			(4
				(PalVary 8 280)
				(PalVary 0 280 0 64 1)
				(messager sayRange: 6 6 7 2 8 self)
			)
			(5
				(merrily
					view: 281
					setLoop: 5 1
					setCel: 0
					setCycle: End self
				)
			)
			(6
				(theGame changeScore: 20 174)
				(ego get: 40)
				(= ticks 60)
			)
			(7 (merrily setCycle: Beg self))
			(8
				(ego
					setCycle: Walk
					setCel: 0
					setLoop: 9 1
					setSpeed: 6
					setMotion: MoveTo 178 60 self
				)
			)
			(9 (messager say: 6 6 7 9 self))
			(10
				(ego setMotion: MoveTo 162 64 self)
			)
			(11
				(theMusic number: 338 loop: 1 play: setVol: 127)
				(merrily setLoop: 6 1 setCel: 0 setCycle: End)
				(ego setLoop: 3 1 setCel: 0 setCycle: End self)
			)
			(12 (self cue:))
			(13
				(ego loop: 4 setCel: 0 setSpeed: 4 setCycle: Fwd)
				(= seconds 4)
			)
			(14 (self cue:))
			(15
				(rope1 dispose:)
				(ego loop: 5 setSpeed: 12 setCel: 0 setCycle: End self)
				(sFx number: 271 loop: 1 play: setVol: 127)
			)
			(16
				(ego loop: 6 setCel: 0 setCycle: Fwd)
				(merrily
					setLoop: 3 1
					setCycle: Walk
					setMotion: MoveTo 163 62 self
				)
			)
			(17
				(merrily setLoop: 7 1 setCel: 0 setCycle: End)
				(= seconds 5)
			)
			(18
				(messager say: 6 6 7 11 self)
			)
			(19
				(theMusic number: 295 play: setLoop: -1)
				(ego view: 290 setCel: 0 dispose:)
				(merrily dispose:)
				(rope2 dispose:)
				(beacon1 dispose:)
				(beacon2 dispose:)
				(theShip dispose:)
				(PalVary 3)
				(curRoom drawPic: 290)
				(windSFx stop:)
				(= cycles 1)
			)
			(20
				(ego
					init:
					setCycle: 0
					setCel: 0
					setSpeed: 12
					posn: 165 21
				)
				(sFx2 number: 385 loop: 1 play: setVol: 127)
				(= cycles 1)
			)
			(21
				(sFx number: 291 play:)
				(ego setCycle: ForwardCounter 4 self)
				(messager say: 6 6 7 12 self)
			)
			(22)
			(23 (= ticks 30))
			(24
				(ego dispose:)
				(curRoom drawPic: 295)
				(= cycles 2)
			)
			(25
				(self setScript: lightsOn)
				(spotLight
					init:
					cycleSpeed: 14
					setCycle: OccCyc self 1 1 10
				)
				(spotLight2
					init:
					cycleSpeed: 16
					setCycle: OccCyc self 1 1 10
				)
				(ego
					init:
					view: 295
					loop: 3
					posn: 152 1
					setPri: 50
					setScale: 0
					setSpeed: 8
					setCel: 0
					setCycle: Fwd
				)
				(larryLight
					init:
					ignoreActors: 1
					cycleSpeed: 8
					setCel: 0
					setCycle: Fwd
				)
				(= seconds 5)
			)
			(26
				(messager say: 6 6 7 13 self)
			)
			(27 (= seconds 5))
			(28
				(theIconBar disableIcon: (ScriptID 0 8) show:)
				(= cycles 1)
			)
			(29
				((inventory at: 35) owner: -1)
				((inventory at: 20) owner: -1)
				(ego put: 2)
				(Bclr 74)
				(UpdateScreenItem ((ScriptID 92 3) view: 1900))
				(theGame hideControls:)
				(= cycles 2)
			)
			(30
				(theMusic fade:)
				(= gLarryRoom 0)
				(cast eachElementDo: #dispose)
				(thePlane drawPic: -1)
				(= ticks 180)
			)
			(31
				(messager say: 6 6 7 14 self)
			)
			(32 (= ticks 180))
			(33
				(Bclr 8)
				(curRoom newRoom: 620)
			)
		)
	)
)

(instance lightsOn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(windowLight init:)
				(= ticks (Random 10 30))
			)
			(1
				(windowLight2 init:)
				(= ticks (Random 10 30))
			)
			(2
				(windowLight3 init:)
				(= ticks (Random 10 30))
			)
			(3
				(windowLight4 init:)
				(= ticks (Random 10 30))
			)
			(4
				(windowLight5 init:)
				(= ticks (Random 10 30))
			)
			(5
				(windowLight6 init:)
				(= ticks (Random 10 30))
			)
			(6
				(windowLight7 init:)
				(= ticks (Random 10 30))
			)
			(7 (self dispose:))
		)
	)
)

(instance windowLight of View
	(properties
		x 189
		y 95
		view 295
		loop 4
	)
)

(instance windowLight2 of View
	(properties
		x 162
		y 98
		view 295
		loop 4
	)
)

(instance windowLight3 of View
	(properties
		x 163
		y 69
		view 295
		loop 4
	)
)

(instance windowLight4 of View
	(properties
		x 162
		y 55
		view 295
		loop 4
	)
)

(instance windowLight5 of View
	(properties
		x 134
		y 86
		view 295
		loop 4
	)
)

(instance windowLight6 of View
	(properties
		x 167
		y 62
		view 295
		loop 4
	)
)

(instance windowLight7 of View
	(properties
		x 118
		y 98
		view 295
		loop 4
	)
)

(instance theShip of View
	(properties
		noun 4
		x 65
		y 123
		view 280
		loop 4
	)
)

(instance spotLight of Prop
	(properties
		x 136
		y 101
		view 295
		loop 2
		cycleSpeed 12
	)
)

(instance spotLight2 of Prop
	(properties
		x 138
		y 105
		view 295
		loop 1
		cel 11
		cycleSpeed 8
	)
)

(instance larryLight of Prop
	(properties
		x 187
		y 104
		view 295
		loop 5
	)
)

(instance rope1 of View
	(properties
		noun 13
		x 170
		y 74
		view 280
	)
)

(instance rope2 of View
	(properties
		noun 13
		x 208
		y 55
		view 280
		loop 1
	)
)

(instance harness of View
	(properties
		noun 14
		x 205
		y 58
		view 280
		loop 2
	)
)

(instance beacon1 of Prop
	(properties
		noun 2
		x 252
		y 36
		view 283
	)
	
	(method (doit)
		(cond 
			((== (self cel?) 3) (PalVary 8 282) (PalVary 0 282 0 64 1))
			((== (self cel?) 4) (PalVary 8 280) (PalVary 0 280 0 64 1))
		)
		(super doit: &rest)
	)
)

(instance beacon2 of Prop
	(properties
		noun 2
		x 300
		y 26
		view 283
		loop 1
	)
)

(instance merrily of Actor
	(properties
		noun 6
		approachX 233
		approachY 63
		x 215
		y 58
		view 281
		loop 1
		cel 4
	)
	
	(method (doVerb theVerb &tmp temp0)
		(switch local0
			(0 (= temp0 1))
			(1 (= temp0 2))
			(else  (= temp0 3))
		)
		(return
			(switch theVerb
				(4
					(if local1
						(curRoom setScript: bungeeJump)
						(return 1)
					else
						(++ local0)
						(curRoom setScript: touchMerrily 0 temp0)
						(return 1)
					)
				)
				(5
					(if local1
						(messager say: 6 2 4)
					else
						(switch local0
							(0 (messager say: 6 5 1))
							(1 (messager say: 6 5 2))
							(else  (messager say: 6 5 3))
						)
					)
					(return 1)
				)
				(1
					(switch local0
						(0 (messager say: 6 1 1))
						(1 (messager say: 6 1 2))
						(else  (messager say: 6 1 3))
					)
					(return 1)
				)
				(2
					(if local1
						(messager say: 6 2 4)
					else
						(messager say: 6 2 temp0)
					)
					(return 1)
				)
				(6
					(if local1
						(curRoom setScript: bungeeJump)
						(return 1)
					else
						(messager say: 6 theVerb temp0)
						(return 1)
					)
				)
				(else 
					(super doVerb: theVerb)
					(return 1)
				)
			)
		)
	)
)

(instance japan of Feature
	(properties
		noun 3
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 48 87 105 64 99 88 37 118
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance ladder of Feature
	(properties
		noun 8
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 264 136 259 108 278 68 286 61 264 107 264 135
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance egoActions of Actions
	(properties)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(4
					(if local1
						(messager say: 7 6)
					else
						(curRoom setScript: undress)
					)
					(return 1)
				)
				(6
					(if local1
						(messager say: 7 6)
					else
						(curRoom setScript: undress)
					)
					(return 1)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance sFx of Sound
	(properties)
)

(instance sFx2 of Sound
	(properties)
)

(instance windSFx of Sound
	(properties
		number 270
	)
)
