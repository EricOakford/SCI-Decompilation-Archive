;;; Sierra Script 1.0 - (do not remove this comment)
(script# 305)
(include sci.sh)
(use Main)
(use Scaler)
(use Osc)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm305 0
)

(local
	[local0 3]
	local3
	local4
	local5
	local6
	local7
	[local8 6] = [160 161 161 160 158 156]
	[local14 6] = [-50 -5 17 44 73 88]
	[local20 6] = [218 189 157 140 128 128]
	local26
)
(instance rm305 of Rm
	(properties
		noun 21
		picture 62
		vanishingY 80
	)
	
	(method (init)
		(self setRegions: 350)
		(LoadMany 128 400 416 402 401 412 413 411 410 414 417 426)
		(NormalEgo 0)
		(ego actions: (ScriptID 350 1))
		(switch prevRoomNum
			(310
				(= style 12)
				(ego
					posn: 20 144
					loop: 0
					setScale: Scaler 100 65 172 105
					init:
					edgeHit: 0
				)
			)
			(315
				(if (Btst 21)
					(Bclr 21)
					(= style 13)
					(ego init: hide:)
					(sRogInTrunk state: 1)
					(curRoom setScript: sRogInTrunk)
				else
					(= style -32758)
					(curRoom setScript: sFrom315)
				)
			)
			(320
				(= style 11)
				(curRoom setScript: sFrom320)
			)
			(else 
				(ego
					posn: 20 144
					loop: 0
					setScale: Scaler 100 65 172 105
					init:
				)
			)
		)
		(if (not (Btst 10)) (treeBranch init: stopUpd:))
		(fruitGrabArea init:)
		(if (and (Btst 16) (not (Btst 210))) (wdHead init:))
		(vine init: stopUpd:)
		(fruit init: stopUpd:)
		(treeTrunk init: setOnMeCheck: 1 8192)
		(wd40 init:)
		(cave315 init: setOnMeCheck: 1 128)
		(cave320 init: setOnMeCheck: 1 512)
		(extraFruit init: setOnMeCheck: 1 2)
		(mountains init: setOnMeCheck: 1 256)
		(pond init: setOnMeCheck: 1 16)
		(roots init: setOnMeCheck: 1 64)
		(tree init: setOnMeCheck: 1 32)
		(wfall init: setOnMeCheck: 1 8)
		(rightLedge init:)
		(leftLedge init:)
		(super init:)
		(theMusic3
			number: 403
			owner: self
			flags: 1
			init:
			setLoop: -1
			play: 75
		)
		(walkHandler addToFront: pond tree)
		(if (not (Btst 10))
			(walkHandler addToFront: treeBranch)
		)
		(falls init: setCycle: Fwd)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init: 0 153 55 135 77 118 127 100 119 99 100 106 51 116 24 135 0 142
					yourself:
				)
				((Polygon new:)
					type: 3
					init: 73 123 97 135 156 140 160 133 104 130 80 119
					yourself:
				)
				((Polygon new:)
					type: 3
					init:
						282
						141
						249
						141
						244
						159
						274
						168
						317
						158
						312
						125
						304
						119
						301
						113
						296
						122
						295
						136
					yourself:
				)
				((Polygon new:)
					type: 3
					init: 255 103 255 106 270 106 270 103
					yourself:
				)
		)
		(if (not (curRoom script?)) (theGame handsOn:))
	)
	
	(method (doit &tmp temp0)
		(asm
			pushi    #doit
			pushi    0
			super    Rm,  4
			pushi    7
			pushi    6
			pushi    240
			pushi    248
			pushi    5
			pushi    249
			pushi    255
			pushi    65528
			callk    Palette,  14
			pushi    #onControl
			pushi    1
			pushi    1
			lag      ego
			send     6
			sat      temp0
			push    
			ldi      128
			eq?     
			bnt      code_04ad
			pushi    90
			pushi    #heading
			pushi    0
			lag      ego
			send     4
			lt?     
			bnt      code_048d
			pprev   
			ldi      270
			lt?     
code_048d:
			not     
			bnt      code_04ad
			pushi    #script
			pushi    0
			lag      curRoom
			send     4
			not     
			bnt      code_04ad
			pushi    #setScript
			pushi    3
			lofsa    sExitRight
			push    
			pushi    0
			pushi    1
			lag      curRoom
			send     10
			jmp      code_05c6
code_04ad:
			lst      temp0
			ldi      512
			eq?     
			bnt      code_04e6
			pushi    90
			pushi    #heading
			pushi    0
			lag      ego
			send     4
			lt?     
			bnt      code_04c6
			pprev   
			ldi      270
			lt?     
code_04c6:
			not     
			bnt      code_04e6
			pushi    #script
			pushi    0
			lag      curRoom
			send     4
			not     
			bnt      code_04e6
			pushi    #setScript
			pushi    3
			lofsa    sExitRight
			push    
			pushi    0
			pushi    0
			lag      curRoom
			send     10
			jmp      code_05c6
code_04e6:
			lst      temp0
			ldi      1024
			eq?     
			bnt      code_050d
			pushi    #script
			pushi    0
			lag      curRoom
			send     4
			push    
			lofsa    sRogSlip
			eq?     
			not     
			bnt      code_050d
			pushi    #setScript
			pushi    1
			lofsa    sRogSlip
			push    
			lag      curRoom
			send     6
			jmp      code_05c6
code_050d:
			lst      temp0
			ldi      2048
			eq?     
			bnt      code_053d
			pushi    #script
			pushi    0
			lag      curRoom
			send     4
			not     
			bnt      code_053d
			pushi    #heading
			pushi    0
			lag      ego
			send     4
			push    
			ldi      180
			lt?     
			bnt      code_053d
			pushi    #setScript
			pushi    1
			lofsa    sRogInTrunk
			push    
			lag      curRoom
			send     6
			jmp      code_05c6
code_053d:
			lst      temp0
			ldi      4096
			eq?     
			bnt      code_056c
			pushi    #script
			pushi    0
			lag      curRoom
			send     4
			not     
			bnt      code_056c
			pushi    #heading
			pushi    0
			lag      ego
			send     4
			push    
			ldi      180
			gt?     
			bnt      code_056c
			pushi    #setScript
			pushi    1
			lofsa    sRogInTrunk
			push    
			lag      curRoom
			send     6
			jmp      code_05c6
code_056c:
			lst      temp0
			ldi      16384
			eq?     
			bnt      code_05a8
			pushi    #script
			pushi    0
			lofsa    wd40
			send     4
			not     
			bnt      code_05a8
			lsg      prevRoomNum
			ldi      320
			ne?     
			bnt      code_05a8
			pushi    1
			pushi    14
			callb    Btst,  2
			not     
			bt       code_0599
			pushi    1
			pushi    15
			callb    Btst,  2
			bnt      code_05a8
code_0599:
			pushi    #setScript
			pushi    1
			lofsa    sWD40LandAndAttack
			push    
			lofsa    wd40
			send     6
			jmp      code_05c6
code_05a8:
			lst      temp0
			ldi      4
			eq?     
			bnt      code_05c6
			pushi    #script
			pushi    0
			lag      curRoom
			send     4
			not     
			bnt      code_05c6
			pushi    #setScript
			pushi    1
			lofsa    sExitViaScroll
			push    
			lag      curRoom
			send     6
code_05c6:
			pushi    5
			pushi    132
			pushi    125
			pushi    165
			pushi    145
			lsg      ego
			calle    InRect,  10
			bnt      code_060a
			pushi    #script
			pushi    0
			lag      curRoom
			send     4
			not     
			bnt      code_060a
			pushi    #script
			pushi    0
			lofsa    wd40
			send     4
			not     
			bnt      code_060a
			pushi    1
			pushi    14
			callb    Btst,  2
			not     
			bnt      code_060a
			pushi    #setScript
			pushi    1
			lofsa    sKnockRogOffTrunk
			push    
			lofsa    wd40
			send     6
code_060a:
			ret     
		)
	)
	
	(method (dispose)
		(theMusic3 dispose:)
		(walkHandler delete: pond tree leftLedge)
		(if (walkHandler contains: treeBranch)
			(walkHandler delete: treeBranch)
		)
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (Btst 16)
					(messager say: noun theVerb 2 0)
				else
					(messager say: noun theVerb 1 0)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance sFrom315 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					posn: 275 100
					scaleSignal: 1
					scaleX: 51
					scaleY: 51
					init:
					setPri: 1
					setMotion: MoveTo 262 105 self
				)
			)
			(1 (ego setHeading: 180 self))
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sExitViaScroll of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo -12 147 self)
			)
			(1 (curRoom newRoom: 310))
		)
	)
)

(instance sWD40LandAndAttack of Script
	(properties)
	
	(method (doit)
		(if (and (> (ego y?) 136) (not local7))
			(= seconds 0)
			(= local7 1)
			(self cue:)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic1 number: 35 loop: -1 play:)
				(= local26 0)
				(if (Btst 15)
					(wd40
						view: 416
						loop: 3
						cel: 0
						scaleSignal: 1
						scaleX: [local20 local26]
						scaleY: [local20 local26]
						x: [local8 local26]
						y: [local14 local26]
					)
					(= ticks 10)
				else
					(++ state)
					(= ticks 1)
				)
			)
			(1
				(wd40
					scaleX: [local20 local26]
					scaleY: [local20 local26]
					x: [local8 local26]
					y: [local14 local26]
				)
				(if (> (++ local26) 5)
					(++ state)
					(= ticks 1)
				else
					(-- state)
					(= ticks 10)
				)
			)
			(2
				(wd40
					view: 416
					loop: 1
					cel: 0
					x: 161
					y: 133
					setCycle: End self
				)
			)
			(3
				(wd40
					view: 416
					loop: 2
					cel: 0
					x: 161
					y: 133
					setCycle: End self
				)
			)
			(4
				(Bset 66)
				(theMusic2 number: 402 setLoop: 1 play:)
				(fireBall
					view: 402
					loop: 0
					cel: 0
					x: 175
					y: 89
					setStep: 15 5
					moveSpeed: 0
					init:
					setMotion: MoveTo (+ (ego x?) 7) (- (ego y?) 10) self
				)
			)
			(5
				(theMusic2 number: 4021 setLoop: 1 play:)
				(fireBall view: 401 loop: 4 cel: 0 setCycle: End self)
			)
			(6 (= seconds 5))
			(7
				(theMusic2 number: 402 setLoop: 1 play:)
				(fireBall
					view: 402
					loop: 0
					cel: 0
					x: 175
					y: 89
					setStep: 15 5
					moveSpeed: 0
					setMotion: MoveTo (+ (ego x?) 2) (- (ego y?) 18) self
				)
			)
			(8
				(fireBall dispose:)
				(cond 
					(
					(and (<= 45 (ego heading?)) (<= (ego heading?) 135)) (ego loop: 3))
					(
					(and (<= 136 (ego heading?)) (<= (ego heading?) 225)) (ego loop: 0))
					(
					(and (<= 226 (ego heading?)) (<= (ego heading?) 315)) (ego loop: 2))
					(else (ego loop: 1))
				)
				(ego view: 401 cel: 0 setCycle: End self)
			)
			(9
				(EgoDead 17)
				(self dispose:)
			)
		)
	)
)

(instance sWD40LandOverRog of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 4))
			(1
				(theMusic2
					number: 401
					setLoop: 1
					play: 0
					fade: 127 10 10 0
				)
				(= local26 0)
				(wd40
					view: 416
					loop: 3
					cel: 0
					scaleSignal: 1
					scaleX: [local20 local26]
					scaleY: [local20 local26]
					x: [local8 local26]
					y: [local14 local26]
				)
				(= ticks 10)
			)
			(2
				(wd40
					scaleX: [local20 local26]
					scaleY: [local20 local26]
					x: [local8 local26]
					y: [local14 local26]
				)
				(if (> (++ local26) 5)
					(= ticks 1)
				else
					(-- state)
					(= ticks 10)
				)
			)
			(3
				(theMusic2 stop:)
				(wd40
					view: 416
					loop: 0
					cel: 0
					x: 164
					y: 132
					setCycle: End self
				)
			)
			(4
				(if (not (Btst 19))
					(Bset 19)
					(messager say: 26 0 0 0 self)
				else
					(= cycles 1)
				)
			)
			(5
				(theMusic3 play: 75)
				(= seconds 5)
			)
			(6
				(theIconBar enable: 0)
				(theMusic2 number: 401 setLoop: 1 play:)
				(wd40
					view: 400
					setStep: 5 10
					moveSpeed: 0
					signal: (| (wd40 signal?) $6000)
					setMotion: MoveTo 250 -40 self
				)
			)
			(7
				(theMusic2 fade:)
				(theMusic3 play: 75)
				(if (Btst 21)
					(curRoom newRoom: 315)
				else
					(self dispose:)
				)
			)
		)
	)
)

(instance sFruitUpWD40 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(rogEyes hide:)
				(ego put: 18)
				(ego
					view: 412
					loop: 4
					cel: 0
					x: 173
					y: 140
					show:
					setCycle: CT 7 1 self
				)
			)
			(1
				(theMusic2 number: 227 setLoop: 1 play:)
				(ego setCycle: End self)
			)
			(2
				(Bset 21)
				(SolvePuzzle 209 200)
				(rogEyes show:)
				(self dispose:)
			)
		)
	)
)

(instance sSwingFruit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 306 141 self)
			)
			(1
				(ego
					view: 413
					setLoop: 0
					cel: 1
					x: 306
					y: 141
					setCycle: End self
				)
			)
			(2
				(NormalEgo 0 1)
				(theGame handsOn:)
				(= local5 1)
				(fruit setCycle: CT 0 -1 self)
			)
			(3 (fruit setCycle: Osc 3 self))
			(4
				(fruit setCycle: End self)
				(= local5 0)
				(if local4
					(ego view: 413 setLoop: 1 cel: 0 setCycle: CT 3 1)
				)
			)
			(5
				(if local4
					(ego get: 18 setCycle: End)
					(SolvePuzzle 207 25)
				)
				(fruit setCycle: CT 1 -1 self)
			)
			(6
				(if local4
					(NormalEgo 0 1)
					(theGame handsOn:)
					(messager say: 13 0 0 0)
				)
				(= local4 0)
				(fruit setCycle: CT 5 1 self)
			)
			(7
				(fruit setCycle: CT 2 -1 self)
			)
			(8
				(fruit setCycle: CT 4 1 self)
			)
			(9
				(fruit setCel: 3 stopUpd:)
				(self dispose:)
			)
		)
	)
)

(instance sVineSwing of Script
	(properties)
	
	(method (doit)
		(if (and (== (vine loop?) 0) (== state 5))
			(ego cel: (vine cel?))
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 161 137 self)
			)
			(1
				(ego
					view: 411
					loop: 1
					cel: 0
					x: 166
					y: 132
					setScale: 0
					scaleX: 128
					scaleY: 128
					setCycle: End self
				)
			)
			(2
				(ego loop: 2 cel: 0 setCycle: CT 2 1 self)
			)
			(3
				(ego setCycle: End self)
				(vine setCycle: End self)
			)
			(4 0)
			(5
				(ego loop: 3 cel: 0 x: 207 y: 93 setCycle: End self)
				(vine loop: 0 cel: 0 setCycle: End self)
			)
			(6 0)
			(7
				(NormalEgo 0)
				(ego
					x: 264
					y: 106
					setPri: 1
					scaleSignal: 1
					scaleX: 51
					scaleY: 51
				)
				(= local6 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sExitRight of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if register
					(ego setMotion: MoveTo 278 101 self)
				else
					(ego setMotion: MoveTo 319 118 self)
				)
			)
			(1
				(if register
					(curRoom newRoom: 315)
				else
					(ego hide:)
					(curRoom newRoom: 320)
				)
				(self dispose:)
			)
		)
	)
)

(instance sRogInTrunk of Script
	(properties)
	
	(method (doit)
		(if (and (not (ego isNotHidden:)) (ego mover?))
			(self cue:)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (ego looper?) ((ego looper?) dispose:))
				(ego setCycle: 0 setLoop: -1)
				(if (Btst 15) (wd40 setScript: sWD40LandOverRog))
				(if (< (ego x?) 160)
					(ego
						view: 410
						setLoop: 2
						cel: 0
						scaleX: 128
						scaleY: 128
						setScale: 0
						x: 69
						y: 135
						setCycle: End self
					)
				else
					(ego
						view: 410
						setLoop: 0
						setPri: -1
						cel: 0
						scaleX: 128
						scaleY: 128
						setScale: 0
						x: 249
						y: 163
						setCycle: End self
					)
				)
			)
			(1 (ego hide:) (= seconds 3))
			(2
				(rogEyes init: setScript: sEyes)
				(theGame handsOn:)
				(if (wd40 script?) (theIconBar disable: 0))
			)
			(3
				(theGame handsOff:)
				(rogEyes dispose:)
				(= seconds 3)
			)
			(4
				(if (< mouseX 160)
					(ego
						view: 410
						setLoop: 3
						cel: 0
						x: 64
						y: 136
						show:
						setCycle: End self
					)
				else
					(ego
						view: 410
						setLoop: 1
						cel: 0
						x: 255
						y: 162
						show:
						setCycle: End self
					)
				)
			)
			(5
				(NormalEgo 0)
				(if (< (ego x?) 160)
					(ego
						x: 64
						y: 136
						setLoop: -1
						loop: 1
						setScale: Scaler 100 65 172 105
						setMotion: MoveTo 36 131 self
					)
				else
					(ego
						x: 255
						y: 162
						setLoop: -1
						loop: 0
						setScale: Scaler 95 57 148 111
						setMotion: MoveTo 291 152 self
					)
				)
			)
			(6
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sRogSlip of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (Btst 10)
					(messager say: 1 0 0 0)
					(ego setMotion: MoveTo 93 112 self)
				else
					(++ state)
					(= cycles 1)
				)
			)
			(1
				(theGame handsOn:)
				(self dispose:)
			)
			(2
				(theMusic2 number: 102 setLoop: 1 play:)
				(ego
					view: 414
					setLoop: -1
					setLoop: 0
					cel: 0
					x: 118
					y: 102
					setPri: 9
					ignoreActors:
					scaleX: 128
					scaleY: 128
					setScale: 0
					setCycle: End self
				)
			)
			(3
				(theMusic2 number: 417 setLoop: 1 play:)
				(= seconds 2)
			)
			(4
				(ego
					view: 414
					setLoop: -1
					setLoop: 0
					cel: (ego lastCel:)
					scaleX: 128
					scaleY: 128
					setScale: 0
					setStep: 2 6
					moveSpeed: 0
					setMotion: MoveTo 118 150 self
				)
				(treeBranch
					setLoop: -1
					setLoop: 1
					setCel: 0
					setCycle: 0
					setStep: 2 6
					moveSpeed: 0
					setMotion: MoveTo 127 150
				)
			)
			(5
				(theMusic2 number: 411 setLoop: 1 play:)
				(ego
					view: 417
					setLoop: -1
					setLoop: 2
					cel: 0
					x: 146
					y: 158
					setCycle: Fwd
					setStep: 4 6
					setMotion: MoveTo 63 172 self
					cycleSpeed: 6
					moveSpeed: 6
				)
			)
			(6
				(ego
					view: 417
					setLoop: -1
					setLoop: 1
					cel: 0
					x: 47
					y: 169
					setStep: 3 7
					setCycle: 0
					setMotion: MoveTo 47 205 self
				)
			)
			(7
				(theMusic2 number: 411 setLoop: 1 play: self)
				(treeBranch
					setLoop: -1
					setLoop: 1
					setCel: 0
					setCycle: 0
					setStep: 4 6
					setMotion: MoveTo 40 169 self
					moveSpeed: 6
				)
			)
			(8
				(treeBranch
					setLoop: 1
					setCel: 0
					setCycle: 0
					setStep: 4 6
					setMotion: MoveTo 40 210 self
				)
			)
			(9
				(treeBranch dispose:)
				(Bset 11)
				(Bset 10)
				(curRoom newRoom: 300)
				(self dispose:)
			)
		)
	)
)

(instance sRogClimbOnOffTrunk of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (not register)
					(ego setMotion: PolyPath 78 121 self)
				else
					(ego setMotion: PolyPath 65 132 self)
				)
			)
			(1
				(if (not register) (= state 3))
				(= cycles 1)
			)
			(2
				(walkHandler addToFront: leftLedge)
				(ego
					view: 410
					setLoop: -1
					setLoop: 4
					cel: 0
					x: 74
					y: 132
					scaleX: 128
					scaleY: 128
					setScale: 0
					setCycle: End self
				)
			)
			(3
				(NormalEgo 0)
				(ego x: 78 y: 121 loop: 4 setPri: 15 setScale: myScaler)
				(= local6 1)
				(theGame handsOn:)
				(self dispose:)
			)
			(4
				(ego
					view: 410
					setLoop: -1
					setLoop: 4
					cel: (ego lastCel:)
					x: 74
					y: 132
					scaleX: 128
					scaleY: 128
					setScale: 0
					setPri: -1
					setCycle: Beg self
				)
				(walkHandler delete: leftLedge)
			)
			(5
				(NormalEgo 0)
				(ego
					x: 70
					y: 126
					setScale: Scaler 100 65 172 105
					setMotion: MoveTo 41 130 self
				)
				(= local6 0)
			)
			(6
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sEyes of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== local3 0)
					(rogEyes setLoop: (Random 0 3) setCel: 0)
				else
					(rogEyes setLoop: 0 setCel: 0)
				)
				(= ticks (Random 25 40))
			)
			(1
				(rogEyes setCel: 1)
				(= ticks 10)
			)
			(2 (= state -1) (= cycles 1))
		)
	)
)

(instance sKnockRogOffTrunk of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 5))
			(1
				(if (not (InRect 132 125 165 145 ego))
					(self dispose:)
				else
					(theGame handsOff:)
					(ego
						view: 411
						setLoop: -1
						setLoop: 4
						cel: 0
						x: 140
						y: 143
						setCycle: End self
					)
					(theMusic2 number: 416 setLoop: 1 play:)
				)
			)
			(2
				(ego
					setLoop: -1
					setLoop: 4
					setCycle: 0
					setStep: 3 10
					setMotion: MoveTo 140 215 self
				)
			)
			(3 (curRoom newRoom: 300))
		)
	)
)

(instance sGetHead of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 37 134 self)
			)
			(1 (ego setHeading: 270 self))
			(2
				(ego view: 19 loop: 1 cel: 0 setCycle: End self)
			)
			(3
				(ego get: 20)
				(SolvePuzzle 210 25)
				(wdHead dispose:)
				(ego setCycle: Beg self)
			)
			(4
				(NormalEgo 0)
				(ego loop: 1)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sWalkToTree of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 113 98 self)
			)
			(1 (self dispose:))
		)
	)
)

(instance sFrom320 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					posn: 318 118
					setScale: Scaler 95 57 148 111
					init:
					setPri: 8
					setMotion: MoveTo 296 125 self
				)
			)
			(1
				(ego setPri: -1 setMotion: MoveTo 306 140 self)
			)
			(2 (ego setHeading: 180 self))
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance wd40 of Actor
	(properties
		x 20
		y 20
		noun 4
		modNum 301
		view 400
		loop 8
		signal $4000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(22
				(if
					(and
						(not (Btst 21))
						(== (wd40 x?) 164)
						(== (wd40 y?) 132)
					)
					(ego setScript: sFruitUpWD40)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance rogEyes of Prop
	(properties
		x 158
		y 142
		view 412
		priority 15
		signal $4010
	)
)

(instance fireBall of Actor
	(properties
		x 175
		y 89
		view 402
		signal $4000
	)
)

(instance treeBranch of Actor
	(properties
		x 127
		y 99
		noun 2
		view 414
		loop 1
		priority 8
		signal $4010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (< (ego x?) 128)
					(curRoom setScript: sWalkToTree)
				else
					(messager say: 12 0 0 0)
				)
			)
			(3
				(if (< (ego x?) 128)
					(curRoom setScript: sWalkToTree)
				else
					(messager say: 12 0 0 0)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance falls of Prop
	(properties
		x 40
		y 169
		noun 16
		view 417
		priority 1
		signal $4010
	)
)

(instance vine of Prop
	(properties
		x 220
		y 8
		noun 24
		view 415
		loop 1
		signal $4000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if local6
					(curRoom setScript: sVineSwing)
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

(instance fruit of Prop
	(properties
		x 280
		y 48
		noun 15
		view 413
		loop 2
		cel 3
		priority 8
		signal $4010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(21
				(cond 
					(
					(and (not (Btst 207)) (InRect 280 122 319 189 ego)) (curRoom setScript: sSwingFruit))
					((Btst 207) (messager say: 14 0 0 0))
					(else (messager say: 6 0 0 0))
				)
			)
			(4
				(if (fruit cycler?)
					(fruitGrabArea doVerb: theVerb)
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

(instance wdHead of Prop
	(properties
		x 19
		y 137
		noun 27
		view 426
		loop 3
		cel 2
		priority 1
		signal $4010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (and (not local6) (InRect 0 0 120 189 ego))
					(curRoom setScript: sGetHead)
				else
					(messager say: 6 0 0 0)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance treeTrunk of Feature
	(properties
		x 200
		y 155
		noun 17
		onMeCheck $2000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(cond 
					(local6 (curRoom setScript: sRogClimbOnOffTrunk 0 0))
					((> (ego x?) 60) (messager say: 7 0 0 0))
					(else (curRoom setScript: sRogClimbOnOffTrunk 0 1))
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance fruitGrabArea of Feature
	(properties
		x 280
		noun 15
		nsTop 89
		nsLeft 256
		nsBottom 100
		nsRight 300
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (fruit cycler?)
					(if
						(and
							(== local5 1)
							(== (ego x?) 306)
							(== (ego y?) 141)
						)
						(= local4 1)
						(theGame handsOff:)
					else
						(messager say: 11 0 0 0)
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

(instance cave315 of Feature
	(properties
		x 262
		y 98
		noun 3
		onMeCheck $0080
	)
)

(instance cave320 of Feature
	(properties
		x 304
		y 110
		noun 4
		onMeCheck $0200
	)
)

(instance extraFruit of Feature
	(properties
		x 293
		y 70
		noun 8
		onMeCheck $0002
	)
)

(instance mountains of Feature
	(properties
		x 229
		y 111
		noun 18
		onMeCheck $0100
	)
)

(instance pond of Feature
	(properties
		x 111
		y 162
		noun 20
		onMeCheck $0010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3 (messager say: noun 4 0 0))
			(4
				(messager say: noun theVerb 0 0)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance roots of Feature
	(properties
		x 267
		y 139
		noun 22
		onMeCheck $0040
	)
)

(instance tree of Feature
	(properties
		x 115
		y 103
		noun 23
		onMeCheck $0020
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (< (ego x?) 128)
					(curRoom setScript: sWalkToTree)
				else
					(messager say: 12 0 0 0)
				)
			)
			(3
				(if (< (ego x?) 128)
					(curRoom setScript: sWalkToTree)
				else
					(messager say: 12 0 0 0)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance wfall of Feature
	(properties
		x 146
		y 81
		noun 25
		onMeCheck $0008
	)
)

(instance leftLedge of Feature
	(properties
		x 25
		y 112
		nsTop 112
		nsBottom 189
		nsRight 50
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(cond 
					((InRect 265 119 319 189 ego) (messager say: 19 1 0 0))
					((Btst 16) (messager say: 21 theVerb 2 0))
					(else (messager say: 21 theVerb 1 0))
				)
			)
			(4
				(if local6
					(curRoom setScript: sRogClimbOnOffTrunk 0 0)
				else
					(super doVerb: theVerb &rest)
				)
			)
			(3
				(if local6
					(curRoom setScript: sRogClimbOnOffTrunk 0 0)
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

(instance rightLedge of Feature
	(properties
		x 295
		y 119
		nsTop 119
		nsLeft 265
		nsBottom 189
		nsRight 319
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(cond 
					((InRect 0 112 50 189 ego) (messager say: 19 1 0 0))
					((Btst 16) (messager say: 21 theVerb 2 0))
					(else (messager say: 21 theVerb 1 0))
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance myScaler of Scaler
	(properties)
	
	(method (init theClient)
		(if argc (= client theClient))
		(self doit:)
	)
	
	(method (doit)
		(if (> (ego x?) 142)
			(ego scaleSignal: 1 scaleX: 128 scaleY: 128)
		else
			(ego
				scaleSignal: 1
				scaleX: (- 128 (/ (- 142 (ego x?)) 2))
				scaleY: (ego scaleX?)
			)
		)
	)
)

(instance theMusic3 of Sound
	(properties
		flags $0001
	)
)
