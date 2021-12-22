;;; Sierra Script 1.0 - (do not remove this comment)
(script# 710)
(include sci.sh)
(use Main)
(use LarryRoom)
(use PolyFeature)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use Motion)
(use Actor)
(use System)

(public
	rm710 0
)

(local
	local0
	local1
	local2
)
(instance rm710 of LarryRoom
	(properties
		noun 1
		picture 710
		horizon 0
		east 700
	)
	
	(method (init)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init:
						0
						139
						319
						139
						319
						72
						271
						72
						185
						57
						114
						57
						46
						69
						18
						60
						0
						64
						0
						69
						26
						65
						30
						70
						0
						77
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 128 68 162 68 176 75 176 84 163 91 128 91 115 83 115 75
					yourself:
				)
		)
		(super init: &rest)
		(ego
			normalize: 900 8
			init:
			setScaler: Scaler 100 80 78 57
			posn: 325 75
		)
		(if (== prevRoomNum 720)
			(= local2 1)
			(ego
				posn: (shamra approachX?) (shamra approachY?)
				cel: 6
			)
			(theMusic
				number: 700
				loop: -1
				play:
				setVol: 50
				fade: 127 10 10 0
			)
			(theGame handsOn:)
		else
			(self setScript: walkEgoInScr)
		)
		(chimney init:)
		(fishTank init:)
		(wallFlower init:)
		(roses init:)
		(shamra
			setCycle: Fwd
			init:
			setPri: 25
			approachVerbs: 4 1 2 5 6 13
			cycleSpeed: 12
		)
		(if (>= (theGame detailLevel:) 3)
			(fish1
				init:
				setCycle: Fwd
				setScript: (fishScr new:)
				setPri: 25
			)
			(fish2
				init:
				setCycle: Fwd
				setScript: (fishScr new:) 0 1
				setPri: 25
			)
			(fish3
				init:
				setCycle: Fwd
				setScript: (fishScr new:)
				setPri: 25
			)
			(fish4
				init:
				setCycle: Fwd
				setScript: (fishScr new:) 0 1
				setPri: 25
			)
			(fish5
				init:
				setCycle: Fwd
				setScript: fishScr 0 1
				setPri: 25
			)
		)
		(fire setCycle: Fwd init: approachVerbs: 42)
		(balconyPoly init:)
	)
	
	(method (doit)
		(super doit: &rest)
		(cond 
			(script 0)
			((balconyPoly onMe: (ego x?) (ego y?)) (if (not local2) (self setScript: toShamra)))
			(local2 (= local2 0) (ego setPri: -1))
		)
	)
	
	(method (dispose)
		(balconyPoly dispose:)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(return
			(if (== theVerb 2)
				(self setScript: turnAndTalkScr)
				(return 1)
			else
				(return (super doVerb: theVerb))
			)
		)
	)
	
	(method (edgeToRoom param1)
		(if (== param1 2) (self setScript: toPenthouseScr))
		(return 0)
	)
)

(instance turnAndTalkScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					setHeading: (GetAngle (ego x?) (ego y?) (shamra x?) (shamra y?)) self
				)
			)
			(1 (= cycles 2))
			(2 (messager say: 1 2 0 0 self))
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance toPenthouseScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo (+ (ego x?) 10) (ego y?) self)
			)
			(1 (curRoom newRoom: 700))
		)
	)
)

(instance toShamra of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					setMotion: PolyPath (shamra approachX?) (shamra approachY?) self
				)
			)
			(1 (ego setHeading: 45 self))
			(2 (= cycles 2))
			(3 (curRoom newRoom: 720))
		)
	)
)

(instance walkEgoInScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(ego setMotion: MoveTo 312 79 self)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance roses of PolyFeature
	(properties
		noun 5
		sightAngle 40
		y 141
		variableX 1
	)
	
	(method (init)
		(super init: &rest)
		(self
			addPolygon:
				((Polygon new:)
					type: 0
					init: 116 109 122 123 117 138 110 138 104 123 104 108 110 104
					yourself:
				)
				((Polygon new:)
					type: 0
					init: 148 101 154 101 161 121 155 138 151 138 145 121 145 108
					yourself:
				)
				((Polygon new:)
					type: 0
					init: 207 118 204 128 196 138 186 122 198 105 210 111
					yourself:
				)
		)
	)
)

(instance chimney of Feature
	(properties
		noun 3
		sightAngle 40
		x 147
		y 81
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 159 0 149 50 152 59 139 59 144 49 136 0
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance fishTank of Feature
	(properties
		noun 4
		sightAngle 40
		approachX 220
		approachY 64
		x 226
		y 58
	)
	
	(method (init)
		(self
			setPolygon: ((Polygon new:)
				init: 257 33 256 53 202 47 202 29
				yourself:
			)
			approachVerbs: 4
		)
		(super init: &rest)
	)
)

(instance wallFlower of Feature
	(properties
		noun 2
		sightAngle 40
		x 291
		y 63
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 302 39 295 53 288 53 281 37 291 24
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance shamra of Prop
	(properties
		sightAngle 40
		approachX 17
		approachY 63
		x 41
		y 63
		view 710
		cel 5
		cycleSpeed 10
		detailLevel 2
	)
	
	(method (doVerb)
		(curRoom newRoom: 720)
	)
)

(instance fishScr of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1)
		(asm
			lap      newState
			aTop     state
			push    
			dup     
			ldi      0
			eq?     
			bnt      code_06f9
			pushi    325
			pushi    2
			pushi    #centerIn
			pushi    2
			pushi    0
			pushi    51
			callk    Random,  4
			add     
			push    
			pushi    33
			pushi    2
			pushi    0
			pushi    14
			callk    Random,  4
			add     
			push    
			pToa     client
			send     8
			ldi      2
			aTop     cycles
			jmp      code_07e9
code_06f9:
			dup     
			ldi      1
			eq?     
			bnt      code_07ca
			pushi    203
			pushi    2
			pushi    0
			pushi    51
			callk    Random,  4
			add     
			sal      local0
			pushi    33
			pushi    2
			pushi    0
			pushi    14
			callk    Random,  4
			add     
			sal      local1
			ldi      100
			sat      temp1
code_071f:
			pushi    4
			pushi    #x
			pushi    0
			pToa     client
			send     4
			push    
			pushi    #y
			pushi    0
			pToa     client
			send     4
			push    
			lsl      local0
			lsl      local1
			callk    GetAngle,  8
			sat      temp0
			push    
			ldi      330
			gt?     
			bt       code_0759
			pushi    1
			lst      temp0
			ldi      180
			sub     
			push    
			callk    Abs,  2
			push    
			ldi      30
			lt?     
			bt       code_0759
			lst      temp0
			ldi      30
			lt?     
code_0759:
			not     
			bnt      code_075c
code_075c:
			-at      temp1
			not     
			bnt      code_0761
code_0761:
			jmp      code_071f
			lst      temp0
			ldi      180
			ge?     
			bnt      code_0791
			pushi    #heading
			pushi    0
			pToa     client
			send     4
			push    
			ldi      180
			lt?     
			bnt      code_07b5
			pushi    15
			pushi    #x
			pushi    #loop
			pushi    0
			pToa     client
			send     4
			push    
			ldi      1
			add     
			push    
			pToa     client
			send     6
			jmp      code_07b5
code_0791:
			pushi    #heading
			pushi    0
			pToa     client
			send     4
			push    
			ldi      180
			ge?     
			bnt      code_07b5
			pushi    15
			pushi    #x
			pushi    #loop
			pushi    0
			pToa     client
			send     4
			push    
			ldi      1
			sub     
			push    
			pToa     client
			send     6
code_07b5:
			pushi    #setMotion
			pushi    4
			class    MoveTo
			push    
			lsl      local0
			lsl      local1
			pushSelf
			pToa     client
			send     12
			jmp      code_07e9
code_07ca:
			dup     
			ldi      2
			eq?     
			bnt      code_07e9
			ldi      0
			aTop     state
			pToa     register
			bnt      code_07e5
			pushi    2
			pushi    20
			pushi    80
			callk    Random,  4
			aTop     ticks
			jmp      code_07e9
code_07e5:
			ldi      2
			aTop     cycles
code_07e9:
			toss    
			ret     
		)
	)
)

(instance lightMatchScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setHeading: 90 self)
			)
			(1
				(ego view: 901 loop: 4 cel: 0 setCycle: CT 3 1 self)
			)
			(2
				(theGame changeScore: 12 157)
				((inventory at: 25)
					message: 43
					noun: 21
					view: 41
					setCursor: 41 0 0
					yourself:
				)
				(inventory show:)
				(narrator y: 97)
				(messager say: 9 42)
				(= cycles 2)
			)
			(3
				(narrator y: -1)
				(ego setCycle: End self)
			)
			(4
				(ego normalize: 900 8 1 cel: 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance fish1 of Actor
	(properties
		heading 90
		y -1
		priority 55
		fixPriority 1
		view 712
		signal $4821
		cycleSpeed 25
		moveSpeed 25
	)
)

(instance fish2 of Actor
	(properties
		heading 90
		y -1
		priority 55
		fixPriority 1
		view 712
		loop 2
		signal $4821
		cycleSpeed 20
		moveSpeed 20
	)
)

(instance fish3 of Actor
	(properties
		heading 90
		y -1
		priority 55
		fixPriority 1
		view 712
		loop 4
		signal $4821
		cycleSpeed 10
		moveSpeed 10
	)
)

(instance fish4 of Actor
	(properties
		heading 90
		y -1
		priority 55
		fixPriority 1
		view 712
		loop 6
		signal $4821
		cycleSpeed 10
		moveSpeed 10
	)
)

(instance fish5 of Actor
	(properties
		heading 90
		y -1
		priority 55
		fixPriority 1
		view 712
		loop 8
		signal $4821
		cycleSpeed 5
		moveSpeed 5
	)
)

(instance fire of Prop
	(properties
		noun 9
		approachX 114
		approachY 82
		x 144
		y 87
		view 713
		cycleSpeed 12
		detailLevel 2
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 42)
			(self setScript: lightMatchScr)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance balconyPoly of Polygon
	(properties)
	
	(method (init)
		(super init: 14 54 44 66 28 69 0 73 0 60)
	)
)
