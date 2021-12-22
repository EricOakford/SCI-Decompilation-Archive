;;; Sierra Script 1.0 - (do not remove this comment)
(script# 600)
(include sci.sh)
(use Main)
(use fileScr)
(use LarryRoom)
(use Print)
(use Inset)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use Timer)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm600 0
)

(local
	local0
	local1
	local2 =  5
)
(procedure (localproc_010a param1 param2 param3 param4 param5 param6)
	(cond 
		((not (ego has: param3))
			(theGame changeScore: param5 param6)
			(messager say: param1 param2)
			(ego get: param3)
			(if (and (ego has: 34) (== param3 37) (Btst 82))
				(Bclr 82)
				(Bclr 83)
			)
			(if (and (ego has: 37) (== param3 34) (Btst 82))
				(Bclr 82)
				(Bclr 83)
			)
			(if (and (== param3 34) (Btst 173))
				((inventory at: 34)
					view: 7
					message: 57
					noun: 27
					setCursor: 7 0 0
					show:
				)
				(inventory show:)
			)
			(++ global184)
		)
		((== param4 2) (messager say: param1 param2 param4))
		(else (messager say: param1 param2 param4))
	)
)

(procedure (localproc_020e)
	(asm
		pushi    #mover
		pushi    0
		lag      ego
		send     4
		bt       code_025d
		pushi    #size
		pushi    0
		lag      talkers
		send     4
		bt       code_025d
		pushi    #script
		pushi    0
		lag      curRoom
		send     4
		bt       code_025d
		pushi    #script
		pushi    0
		lag      ego
		send     4
		bt       code_025d
		pushi    #view
		pushi    0
		lag      ego
		send     4
		push    
		ldi      900
		ne?     
		bt       code_025d
		pushi    #script
		pushi    0
		lag      theGame
		send     4
		bt       code_025d
		pushi    #dialog
		pushi    0
		class    Print
		send     4
		bnt      code_025d
code_025d:
		ret     
	)
)

(instance rm600 of LarryRoom
	(properties
		noun 12
		picture 600
		horizon 0
		autoLoad 0
	)
	
	(method (init)
		(if global100 (= local0 2) else (= local0 4))
		(super init: &rest)
		(elevatorIndicator init: approachVerbs: 4 2 5 6)
		(larryDoor init:)
		(elevatorDoors init: approachVerbs: 4 2 5 6)
		(ego normalize: 900 setScaler: Scaler 100 85 95 78 init:)
		(cond 
			((== prevRoomNum 700)
				(theMusic number: 0 stop:)
				(theMusic2 number: 200 loop: -1 play: setVol: 60)
			)
			(
				(or
					(!= (theMusic2 number?) 200)
					(not (theMusic2 handle?))
				)
				(theMusic2 number: 200 loop: -1 play: setVol: 127)
			)
			(else (theMusic2 pause: 0 setVol: 127))
		)
		(switch prevRoomNum
			(620
				(larryDoor view: 600 cel: 7)
				(ego x: 303 y: 97 setLoop: 5)
				(self setScript: enterFrom620Scr)
			)
			(700
				(ego
					x: 223
					y: 73
					loop: 8
					cel: 5
					heading: 225
					setPri: 30
					cycleSpeed: 6
					moveSpeed: 6
				)
				(elevatorDoors view: 600 cel: 0)
				(elevatorIndicator view: 600 cel: 5)
				(self setScript: exitElevatorScr)
			)
			(605
				(ego
					posn: (maidCart approachX?) (maidCart approachY?)
					loop: 8
					cel: 2
				)
				(theGame handsOn:)
			)
			(else 
				(ego posn: 4 103 setPri: 80 setLoop: 4)
				(self setScript: enterFrom200Scr)
			)
		)
		(stairTrigger init:)
		(stairs init: approachVerbs: 4 2 5 6)
		(lPlant init: approachVerbs: 4 2 5 6)
		(theWindow init: approachVerbs: 4 2 5 6)
		(mPlant init: approachVerbs: 4 2 5 6)
		(rPlant init: approachVerbs: 4 2 5 6)
		(iceMachine init: approachVerbs: 4 2 5 6)
		(iceMachineTimer setReal: iceMachineTimer (Random 30 90))
		(elevatorButton init:)
		(if
			(or
				global189
				(Btst 83)
				(and (Btst 77) (< global184 7) (not (Random 0 2)))
			)
			(maidCart init:)
			(= global189 0)
			(self
				addObstacle:
					((Polygon new:)
						type: 3
						init:
							1
							127
							0
							175
							319
							175
							319
							123
							291
							107
							288
							106
							247
							106
							223
							102
							210
							83
							177
							89
							168
							85
							164
							89
							109
							89
							118
							74
							62
							93
							11
							66
							16
							95
							39
							86
							47
							97
						yourself:
					)
			)
		else
			(self
				addObstacle:
					((Polygon new:)
						type: 3
						init:
							1
							127
							0
							175
							319
							175
							319
							123
							291
							107
							288
							106
							247
							106
							223
							102
							210
							83
							175
							74
							118
							74
							62
							93
							11
							66
							16
							95
							39
							86
							47
							97
						yourself:
					)
			)
		)
	)
	
	(method (doit &tmp temp0)
		(cond 
			(script)
			((stairTrigger onMe: (ego x?) (ego y?)) (self setScript: downStairsScr))
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(stairTrigger dispose:)
		(super dispose:)
	)
	
	(method (newRoom n)
		(iceMachineTimer dispose: delete:)
		(super newRoom: n)
	)
)

(instance elevatorButton of Feature
	(properties
		noun 7
		sightAngle 10
		approachX 210
		approachY 83
		x 200
		y 51
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 194 45 206 45 206 58 194 58
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(curRoom setScript: pressButtonScr)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance stairs of Feature
	(properties
		noun 14
		sightAngle 10
		approachX 55
		approachY 98
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 31 41 60 39 58 85 32 95
					yourself:
				)
		)
		(super init:)
	)
)

(instance lPlant of Feature
	(properties
		noun 11
		sightAngle 10
		approachX 55
		approachY 98
		x 53
		y 13
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 83 0 98 15 74 15 73 22 63 38 27 35 27 19 12 17 11 10 16 0
					yourself:
				)
		)
		(super init:)
	)
)

(instance theWindow of Feature
	(properties
		noun 19
		sightAngle 10
		approachX 118
		approachY 74
		x 148
		y 11
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						121
						65
						112
						65
						111
						0
						184
						0
						183
						45
						177
						45
						178
						40
						181
						40
						180
						35
						170
						35
						158
						30
						152
						25
						122
						39
						119
						55
					yourself:
				)
		)
		(super init:)
	)
)

(instance mPlant of Feature
	(properties
		noun 11
		sightAngle 10
		approachX 210
		approachY 83
		x 210
		y 12
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 238 0 240 11 228 16 231 27 216 22 190 31 184 21 190 17 182 10 181 0
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb &tmp temp0)
		(if (and (== theVerb 4) (not (-- local0)))
			(ego get: 22 7)
			((inventory at: 17) owner: 590)
			(Bclr 24)
			(Bset 240)
			(Bset 68)
			(while (== (= temp0 (Random 1 61)) 9)
			)
			(DoAudio 2 611 1 4 temp0 1)
			(Print
				width: 160
				font: userFont
				addTitle: {Carlos, are you cheating again?}
				addText: 1 4 temp0 1 50 1 611
				fore: 67
				addText: {Shablee has the gown and you can get condom.} 50 17
				fore: 0
				addIcon: 1592 1 0 0 0
				init:
			)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance elevatorDoors of Prop
	(properties
		noun 6
		sightAngle 10
		approachX 210
		approachY 83
		x 207
		y 70
		priority 70
		fixPriority 1
		view 602
		loop 1
	)
)

(instance elevatorIndicator of Prop
	(properties
		noun 5
		sightAngle 10
		approachX 210
		approachY 83
		x 217
		y 31
		priority 90
		fixPriority 1
		view 602
		cel 9
		signal $6821
	)
)

(instance iceMachine of Prop
	(properties
		noun 10
		sightAngle 10
		approachX 199
		approachY 104
		x 269
		y 90
		priority 90
		fixPriority 1
		view 602
		loop 3
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(19
				(curRoom setScript: chillChampagneScr 0 0)
			)
			(20
				(curRoom setScript: chillChampagneScr 0 1)
			)
			(36
				(curRoom setScript: chillClothScr)
			)
			(1
				(if (== local1 2)
					(messager say: 10 1 3)
				else
					(super doVerb: theVerb)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance rPlant of Feature
	(properties
		noun 11
		sightAngle 10
		approachX 295
		approachY 107
		x 291
		y 19
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 319 0 319 13 314 22 313 38 283 39 267 30 268 17 246 16 261 0
					yourself:
				)
		)
		(super init:)
	)
)

(instance larryDoor of Prop
	(properties
		noun 4
		sightAngle 10
		approachX 293
		approachY 106
		x 283
		y 91
		priority 91
		fixPriority 1
		view 602
		loop 2
		signal $6821
	)
	
	(method (init)
		(self approachVerbs: 4 7)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(7
				(curRoom setScript: enterLarrysRoomScr 0 0)
			)
			(4
				(curRoom setScript: enterLarrysRoomScr 0 1)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance maidCart of View
	(properties
		noun 2
		sightAngle 10
		approachX 150
		approachY 125
		x 169
		y 82
		priority 80
		fixPriority 1
		view 607
		signal $6821
	)
	
	(method (doVerb theVerb)
		(cond 
			((!= theVerb 1) (super doVerb: theVerb))
			((> mouseX 153) (curRoom setScript: insetScr 0 1))
			(else (curRoom setScript: insetScr 0 2))
		)
	)
)

(instance stairTrigger of Polygon
	(properties)
	
	(method (init)
		(super init: 63 82 33 95 22 93 52 80)
	)
)

(instance chillClothScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 227 103 self)
			)
			(1
				((inventory at: 39) message: 37)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance enterFrom200Scr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= ticks 90))
			(1
				(theMusic2 fade: 127 10 10 0)
				(ego setMotion: MoveTo 45 89 self)
			)
			(2
				(ego setMotion: MoveTo 61 96 self)
			)
			(3
				(ego normalize: 900 0 1)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance enterFrom620Scr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= ticks 10))
			(1
				(ego setMotion: MoveTo 289 109 self)
			)
			(2 (ego setHeading: 45 self))
			(3
				(larryDoor setCycle: Beg self)
			)
			(4
				(sfx number: 33 loop: 1 play:)
				(larryDoor view: 602)
				(ego setHeading: 180 self)
			)
			(5
				(ego normalize: 900 2 1)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance iceMachineTimer of Timer
	(properties)
	
	(method (cue)
		(asm
			pushi    0
			call     localproc_020e,  0
			not     
			bnt      code_0d3e
			pushi    #size
			pushi    0
			lag      talkers
			send     4
			bt       code_0c24
			pushi    #dialog
			pushi    0
			class    Print
			send     4
			bnt      code_0c24
code_0c24:
			not     
			bnt      code_0d3e
			+al      local1
			push    
			dup     
			ldi      1
			eq?     
			bnt      code_0c6b
			pushi    #number
			pushi    1
			pushi    605
			pushi    15
			pushi    1
			pushi    65535
			pushi    51
			pushi    0
			lofsa    iceShaking
			send     16
			pushi    #view
			pushi    1
			pushi    601
			pushi    254
			pushi    1
			pushi    0
			pushi    236
			pushi    1
			class    Fwd
			push    
			lofsa    iceMachine
			send     18
			pushi    #setReal
			pushi    2
			pushSelf
			pushi    3
			self     8
			jmp      code_0d3b
code_0c6b:
			dup     
			ldi      2
			eq?     
			bnt      code_0cb8
			pushi    0
			calle    proc79_14,  0
			not     
			bnt      code_0caa
			pushi    1
			pushi    63
			calle    Btst,  2
			not     
			bnt      code_0caa
			pushi    #handsOff
			pushi    0
			lag      theGame
			send     4
			pushi    #ignoreActors
			pushi    1
			pushi    1
			lag      ego
			send     6
			pushi    3
			lsg      ego
			lofsa    iceMachine
			push    
			pushSelf
			calle    proc79_3,  6
			jmp      code_0d3b
code_0caa:
			ldi      4
			sal      local1
			pushi    #cue
			pushi    0
			self     4
			jmp      code_0d3b
code_0cb8:
			dup     
			ldi      3
			eq?     
			bnt      code_0cca
			pushi    #setReal
			pushi    2
			pushSelf
			pushi    2
			self     8
			jmp      code_0d3b
code_0cca:
			dup     
			ldi      4
			eq?     
			bnt      code_0ced
			pushi    1
			pushi    63
			calle    Bset,  2
			pushi    #say
			pushi    5
			pushi    10
			pushi    1
			pushi    3
			pushi    0
			pushSelf
			lag      messager
			send     14
			jmp      code_0d3b
code_0ced:
			dup     
			ldi      5
			eq?     
			bnt      code_0d3b
			pushi    #ignoreActors
			pushi    1
			pushi    0
			lag      ego
			send     6
			pushi    #stop
			pushi    0
			lofsa    iceShaking
			send     4
			pushi    #number
			pushi    1
			pushi    604
			pushi    15
			pushi    1
			pushi    1
			pushi    51
			pushi    0
			lofsa    sfx
			send     16
			pushi    #view
			pushi    1
			pushi    602
			pushi    254
			pushi    1
			pushi    3
			pushi    236
			pushi    1
			pushi    0
			lofsa    iceMachine
			send     18
			pushi    #handsOn
			pushi    0
			lag      theGame
			send     4
code_0d3b:
			toss    
			jmp      code_0d48
code_0d3e:
			pushi    #setReal
			pushi    2
			pushSelf
			pushi    3
			self     8
code_0d48:
			ret     
		)
	)
)

(instance theToiletPaper of Feature
	(properties
		noun 17
		y 6
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 82 79 118 70 116 85 80 96
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(cond 
			((== theVerb 5)
				(if (not (ego has: 38)) (Bset 96))
				(localproc_010a noun theVerb 37 7 2 227)
			)
			((not (OneOf theVerb 4 1 2 5 6)) (frontInset doVerb: theVerb))
			(else (super doVerb: theVerb))
		)
	)
)

(instance theLotion of Feature
	(properties
		noun 3
		y 6
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 118 52 118 62 109 65 99 59 100 48
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(cond 
			((== theVerb 5) (localproc_010a noun theVerb 19 1 2 230))
			((not (OneOf theVerb 4 1 2 5 6)) (frontInset doVerb: theVerb))
			(else (super doVerb: theVerb))
		)
	)
)

(instance sfx of Sound
	(properties
		flags $0005
	)
)

(instance iceShaking of Sound
	(properties
		flags $0005
		loop -1
	)
)

(instance downStairsScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo 20 94 self setPri: 80 setLoop: 7)
				(theMusic2 fade: 90 10 10 0)
			)
			(1 (= ticks 30))
			(2
				(if
					(and
						(not (Btst 52))
						(or (Btst 80) (and (not (Btst 12)) (Btst 34)))
					)
					(Bset 52)
				)
				(if (Btst 240) (Bset 68))
				(theMusic number: 0 stop:)
				(Bclr 73)
				(curRoom newRoom: 200)
			)
		)
	)
)

(instance backInset of Inset
	(properties
		view 606
		x 144
		y 0
		noun 1
	)
	
	(method (init)
		(self picture: -2 priority: 200)
		(super init: &rest)
		(theFloss init:)
		(theSeatCovers init:)
		(theTowels init:)
		(UpdatePlane plane)
		(FrameOut)
	)
	
	(method (dispose)
		(theSeatCovers dispose:)
		(theTowels dispose:)
		(theFloss dispose:)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(cond 
			((not (OneOf theVerb 4 1 2 5 6)) (messager say: noun 15))
			(((theTowels onMeCheck?) onMe: mouseX mouseY) (theTowels doVerb: theVerb))
			(
			((theSeatCovers onMeCheck?) onMe: mouseX mouseY) (theSeatCovers doVerb: theVerb))
			(((theFloss onMeCheck?) onMe: mouseX mouseY) (theFloss doVerb: theVerb))
			(else (super doVerb: theVerb))
		)
	)
)

(instance frontInset of Inset
	(properties
		view 605
		x 0
		y 2
		noun 9
	)
	
	(method (init)
		(self priority: 200 picture: -2)
		(super init: &rest)
		(theToiletPaper init:)
		(theSoap init:)
		(theWashCloths init:)
		(theLotion init:)
		(UpdatePlane plane)
		(FrameOut)
	)
	
	(method (dispose)
		(theToiletPaper dispose:)
		(theSoap dispose:)
		(theWashCloths dispose:)
		(theLotion dispose:)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(if (not (OneOf theVerb 4 1 2 5 6))
			(messager say: noun 15)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance pressButtonScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 198 86 self)
			)
			(1
				(ego setMotion: MoveTo 189 78 self)
			)
			(2
				(ego view: 901 setLoop: 7 setCel: 0 setCycle: CT 3 1 self)
			)
			(3 (= ticks 30))
			(4 (messager say: 7 4 0 0 self))
			(5
				(narrator y: -1)
				(ego setCycle: Beg self)
			)
			(6
				(ego normalize: 900 3 1 setHeading: 180 self)
			)
			(7
				(ego setMotion: MoveTo 198 86 self)
			)
			(8
				(ego normalize: 900 2 1)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance enterLarrysRoomScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (not register) (theGame changeScore: 5 234))
				(ego setHeading: 360 self)
			)
			(1 (= ticks 60))
			(2
				(ego view: 901 setLoop: 6 cel: 0 setCycle: End self)
			)
			(3
				(if register
					(sfx number: 34 loop: 1 play: self)
				else
					(= state (+ state 2))
					(= cycles 2)
				)
			)
			(4 (messager say: 4 4 0 0 self))
			(5
				(ego normalize: 900 3 1)
				(theGame handsOn:)
				(self dispose:)
			)
			(6
				(sfx number: 603 loop: 1 play:)
				(larryDoor view: 600 cel: 0 setCycle: End self)
			)
			(7 (ego setCycle: Beg self))
			(8
				(ego
					view: 900
					setLoop: 6
					setCycle: Fwd
					setMotion: MoveTo 318 104 self
				)
				(theMusic2 fade: 0 10 10 0)
			)
			(9
				(ego setCycle: 0 hide:)
				(= ticks 120)
			)
			(10
				(theMusic2 pause: 1)
				(= cycles 2)
			)
			(11 (curRoom newRoom: 620))
		)
	)
)

(instance chillChampagneScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 225 103 self)
			)
			(1 (ego setHeading: 45 self))
			(2 (= ticks 90))
			(3
				(narrator x: 10 y: 20)
				(if register
					(messager say: 20 0 10 0 self)
				else
					(messager say: 20 0 9 0 self)
				)
			)
			(4
				(ego setMotion: PolyPath 199 104 self)
			)
			(5 (ego setHeading: 90 self))
			(6
				(ego loop: 8 cel: 0)
				(= cycles 2)
			)
			(7
				(ego view: 603 setLoop: 0 cel: 0)
				(= ticks 10)
			)
			(8
				(iceShaking number: 605 loop: -1 play:)
				(iceMachine view: 601 setLoop: 0 setCycle: Fwd)
				(ego cel: 1)
				(= seconds 3)
			)
			(9
				(iceMachine view: 601 setLoop: 1 cel: 0 setCycle: Fwd)
				(ego cycleSpeed: 8 setCycle: CT 4 1 self)
			)
			(10 (= ticks 180))
			(11
				(theGame changeScore: 12 235)
				(ego normalize: 900 0 1)
				(iceMachine setLoop: 0 setCycle: End self)
			)
			(12
				(iceShaking stop:)
				(iceMachine view: 602 setLoop: 3)
				((inventory at: 7) cue: -32768)
				(narrator x: -1 y: -1)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance exitElevatorScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= register 6)
				(= seconds 2)
			)
			(1
				(if (-- register) (-- state))
				(elevatorIndicator cel: register)
				(= ticks 30)
			)
			(2
				(sfx number: 602 loop: 1 play:)
				(= ticks 90)
			)
			(3
				(sfx number: 600 loop: 1 play:)
				(elevatorDoors cycleSpeed: 4 setCycle: CT 2 1 self)
			)
			(4
				(theMusic2 fade: 127 10 10 0)
				(elevatorDoors setCycle: End self)
			)
			(5
				(ego setLoop: 5 setPri: -1 setMotion: MoveTo 206 86 self)
			)
			(6
				(ego setHeading: 180)
				(= ticks 30)
			)
			(7
				(elevatorDoors setCycle: Beg self)
				(sfx number: 601 loop: 1 play:)
			)
			(8
				(elevatorIndicator
					cycleSpeed: 60
					setCycle: End elevatorIndicator
				)
				(elevatorDoors view: 602)
				(ego normalize: 900 2 1)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance theFloss of Feature
	(properties
		noun 8
		x 258
		y 80
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 2
					init: 240 74 267 69 276 80 241 86
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(cond 
			((Btst 156) (messager say: 8 5 13))
			((== theVerb 5) (localproc_010a noun theVerb 15 2 4 232))
			((not (OneOf theVerb 4 1 2 5 6)) (backInset doVerb: theVerb))
			(else (super doVerb: theVerb))
		)
	)
)

(instance theSeatCovers of Feature
	(properties
		noun 15
		x 223
		y 74
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 2
					init: 205 59 206 78 240 87 239 74 254 71 252 58
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(cond 
			((== theVerb 5) (localproc_010a noun theVerb 36 5 2 231))
			((not (OneOf theVerb 4 1 2 5 6)) (backInset doVerb: theVerb))
			(else (super doVerb: theVerb))
		)
	)
)

(instance theTowels of Feature
	(properties
		noun 16
		x 224
		y 98
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 2
					init: 190 78 200 101 240 116 235 86
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(cond 
			((== theVerb 5) (localproc_010a noun theVerb 38 6 3 233))
			((not (OneOf theVerb 4 1 2 5 6)) (backInset doVerb: theVerb))
			(else (super doVerb: theVerb))
		)
	)
)

(instance theSoap of Feature
	(properties
		noun 13
		y 6
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 83 57 105 64 83 68 83 59
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(cond 
			((== theVerb 5) (localproc_010a noun theVerb 34 4 2 228))
			((not (OneOf theVerb 4 1 2 5 6)) (frontInset doVerb: theVerb))
			(else (super doVerb: theVerb))
		)
	)
)

(instance theWashCloths of Feature
	(properties
		noun 18
		y 6
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 65 66 66 62 75 58 82 61 82 66 77 70
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(cond 
			((== theVerb 5)
				(cond 
					((OneOf global171 9 10) (messager say: 18 5 14))
					((== ((inventory at: 39) owner?) 560) (messager say: 18 5 11))
					((== ((inventory at: 39) owner?) 530) (messager say: 18 5 12))
					(else (localproc_010a noun theVerb 39 8 4 229))
				)
			)
			((not (OneOf theVerb 4 1 2 5 6)) (frontInset doVerb: theVerb))
			(else (super doVerb: theVerb))
		)
	)
)

(instance insetScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(switch register
					(1
						(ego setMotion: PolyPath 168 85 self)
					)
					(2
						(ego setMotion: PolyPath 116 76 self)
					)
				)
			)
			(1
				(switch register
					(1 (ego setHeading: 310 self))
					(2 (ego setHeading: 125 self))
				)
			)
			(2 (= cycles 2))
			(3
				(Bset 86)
				(gGraphMenuBar state: (| (gGraphMenuBar state?) $0004))
				(switch register
					(1
						(curRoom setInset: frontInset)
					)
					(2
						(curRoom setInset: backInset)
					)
				)
				(= cycles 2)
			)
			(4 (messager say: 2 1 0 0 self))
			(5
				(theGame handsOn:)
				((ScriptID 0 11) init: self)
			)
			(6
				(Bclr 86)
				(gGraphMenuBar state: (& (gGraphMenuBar state?) $fffb))
				((curRoom inset?) dispose:)
				(curRoom drawPic: 600 0)
				(maidCart init:)
				((ScriptID 0 11) dispose:)
				(= register 0)
				(self dispose:)
			)
		)
	)
)
