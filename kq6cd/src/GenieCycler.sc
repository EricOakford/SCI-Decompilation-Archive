;;; Sierra Script 1.0 - (do not remove this comment)
(script# 260)
(include sci.sh)
(use Main)
(use KQ6Room)
(use Kq6Procs)
(use Scaler)
(use MoveCyc)
(use PolyPath)
(use Polygon)
(use Feature)
(use MoveFwd)
(use LoadMany)
(use TimedCue)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm260 0
)

(local
	local0 =  1
	local1
	local2
	[local3 122] = [0 0 0 30 0 1 4 34 0 2 8 38 0 3 13 41 0 4 18 44 0 5 24 47 0 6 30 50 0 0 37 52 0 1 44 53 0 2 52 53 0 3 60 53 0 4 68 52 0 5 75 51 0 6 82 50 0 0 89 49 0 1 97 47 0 2 104 45 2 0 108 42 2 0 114 40 2 0 119 38 2 0 124 35 2 0 129 32 2 0 134 29 2 0 139 26 2 0 145 22 2 0 151 18 2 0 157 14 2 0 163 10 2 0 169 6 2 0 176 2 -32768 -32768]
	[local125 94] = [6 0 130 1 6 1 131 3 6 2 133 6 6 3 136 9 6 4 139 13 6 5 142 17 6 6 146 21 6 0 150 24 6 1 154 27 6 2 158 30 6 3 163 33 6 4 169 35 6 5 175 36 6 6 181 35 6 0 186 33 6 1 190 30 6 2 194 27 6 3 197 23 6 4 200 19 6 5 203 15 6 6 206 11 6 0 208 6 6 1 210 2 -32768 -32768]
	[local219 106] = [6 0 177 2 6 1 176 5 6 2 174 9 6 3 172 13 6 4 169 17 6 5 166 21 6 6 163 25 6 0 159 29 6 0 155 33 6 0 151 37 6 0 146 41 6 0 141 44 7 0 136 46 7 0 132 45 7 0 128 43 7 0 124 41 7 0 120 38 7 0 116 35 7 0 112 32 7 0 109 29 7 0 105 26 7 0 101 23 7 0 97 20 7 0 92 17 7 0 86 14 7 0 80 11 -32768 -32768]
	[local325 142] = [0 0 27 3 0 1 30 7 0 2 33 12 0 3 37 16 0 4 41 20 0 5 46 24 0 6 52 28 0 0 57 32 0 1 62 35 0 2 68 37 0 3 73 38 0 4 77 38 0 5 77 38 0 6 77 38 0 0 77 38 0 1 77 38 0 2 77 38 0 3 77 38 0 4 77 38 1 0 80 37 1 0 75 36 1 0 70 35 1 0 65 34 1 0 60 32 1 0 56 30 1 0 51 28 1 0 46 26 1 0 41 24 1 0 35 22 1 0 30 20 1 0 25 19 1 0 20 18 1 0 14 17 1 0 9 16 1 0 4 15 -32768 -32768]
	[local467 94] = [0 0 264 1 0 1 260 3 0 2 255 5 0 3 249 8 0 4 243 11 0 5 237 14 0 6 231 17 0 0 226 20 0 1 221 23 0 2 216 26 0 3 209 29 0 4 202 31 0 5 195 33 0 6 187 34 0 0 180 34 0 1 174 32 0 2 169 28 0 3 165 24 0 4 162 20 0 5 158 15 0 6 155 10 0 0 153 5 0 1 152 1 -32768 -32768]
	local561
)
(procedure (localproc_1e5d)
	(= local1 1)
	(ego
		observeControl: -32768
		setMotion: MoveTo mouseX (- mouseY 11)
	)
)

(class GenieCycler of Forward
	(properties
		client 0
		caller 0
		cycleDir 1
		cycleCnt 0
		completed 0
		waitCounter 3
	)
	
	(method (doit &tmp genieCyclerNextCel)
		(cond 
			(
				(>
					(= genieCyclerNextCel (self nextCel:))
					(client lastCel:)
				)
				(self cycleDone:)
			)
			((OneOf (client loop?) 1 5)
				(if
					(and
						(== genieCyclerNextCel 4)
						(-- waitCounter)
						(= waitCounter 3)
						(Random 0 1)
					)
					(self cycleDone:)
				else
					(client cel: genieCyclerNextCel)
				)
			)
			(else (client cel: genieCyclerNextCel))
		)
	)
)

(instance rm260 of KQ6Room
	(properties
		noun 4
		picture 260
		horizon 0
		walkOffEdge 1
	)
	
	(method (init)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						186
						76
						199
						89
						199
						95
						190
						95
						188
						102
						179
						108
						84
						87
						50
						97
						3
						88
						3
						103
						36
						110
						90
						93
						171
						111
						158
						115
						157
						123
						139
						123
						133
						126
						134
						189
						0
						189
						0
						-10
						274
						-10
						274
						36
						259
						53
						226
						60
						197
						71
					yourself:
				)
				((Polygon new:)
					type: 2
					init:
						232
						93
						199
						74
						210
						69
						227
						63
						267
						55
						290
						38
						290
						-10
						319
						-10
						319
						105
						260
						105
					yourself:
				)
				((Polygon new:)
					type: 0
					init:
						260
						107
						318
						107
						317
						189
						137
						189
						136
						127
						218
						127
						218
						120
						232
						120
						232
						109
						220
						109
						220
						102
						231
						102
						230
						94
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 220 125 220 122 239 122 239 125
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 222 104 236 104 236 107 222 107
					yourself:
				)
		)
		(super init: &rest)
		(genericFeatures init:)
		(ego
			setScale: Scaler 97 20 150 30
			init:
			reset: 2
			actions: egoDoVerb
		)
		((ScriptID 10 4)
			onMeCheck: 128
			setOnMeCheck: 1 128
			init:
		)
		(hatch init:)
		(sail1 init:)
		(sail2 init:)
		(sail3 init:)
		(sail4 init:)
		(if (> (theGame _detailLevel?) 0)
			(BeachBird init:)
			(if (>= (theGame _detailLevel?) 2)
				(sail1 setScript: (Clone sailScr))
				(sail2 setScript: (Clone sailScr))
				(sail3 setScript: sailScr)
			)
		)
		(switch prevRoomNum
			(290
				(theGame handsOn:)
				(ego posn: 18 105)
			)
			(else 
				(curRoom setScript: enterVillage2Scr)
			)
		)
		(if
			(and
				(Btst 16)
				(not ((ScriptID 10 0) isSet: 32))
				(== currentAct 1)
			)
			((ScriptID 10 0) setIt: 32)
			(genieBoy init: setScript: genieScr)
			(theMusic number: 260 loop: -1 play:)
		else
			(theMusic number: 915 loop: -1 play:)
			(theGlobalSound number: 916 loop: -1 play:)
		)
		(ocean init:)
		(boatDoor init:)
		(holeInBoat init:)
		(reflect1 init: setCycle: Forward)
		(reflect2 init: setCycle: Forward)
		(reflect3 init: setCycle: Forward)
		(reflect4 init: setCycle: Forward)
		(reflect5 init: setCycle: Forward)
		(if
			(and
				(not (Random 0 2))
				(== prevRoomNum 250)
				(not (cast contains: genieBoy))
			)
			(smFerryman init: setScript: ferrymanScr)
		)
		(mouseDownHandler addToFront: self)
		(keyDownHandler addToFront: self)
	)
	
	(method (doit &tmp temp0)
		(cond 
			(
				(and
					(& (= temp0 (ego onControl: 1)) $2000)
					(not local561)
				)
				(ego setPri: 5)
				(= local561 1)
			)
			((and local561 (not (& temp0 $2000))) (ego setPri: -1) (= local561 0))
			(script 0)
			((ego inRect: 242 46 288 55) (curRoom setScript: bailScr))
			((& temp0 $0224)
				(ego setMotion: 0)
				(= local1 0)
				(if (> (ego y?) 124)
					(self setScript: egoFallForwardScr)
				else
					(self setScript: egoFallRightScr)
				)
			)
			(
			(and (== temp0 8192) (cast contains: genieBoy)) (curRoom setScript: genieBailScr))
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(theMusic fade:)
		(if (not (cast contains: genieBoy))
			(theGlobalSound fade:)
		)
		(mouseDownHandler delete: self)
		(keyDownHandler delete: self)
		(super dispose:)
		(DisposeScript 964)
		(DisposeScript 231)
		(DisposeScript 960)
		(DisposeScript 942)
		(DisposeScript 951)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return 1))
		(return
			(if
				(and
					(not (curRoom script?))
					(!= (event type?) evVERB)
					(& (event type?) evMOUSEKEYBOARD)
					(not (event modifiers?))
					(> (event y?) 56)
					(or
						(and
							(== (theIconBar curIcon?) (theIconBar at: 0))
							(OneOf (OnControl 4 (event x?) (event y?)) 4 32 512)
							(cast contains: genieBoy)
						)
						(and
							(== (OnControl 4 (event x?) (event y?)) 4)
							(== (theIconBar curIcon?) (theIconBar at: 1))
						)
					)
				)
				(self setScript: diveIntoWaterScr)
				(return (event claimed: 1))
			else
				(super handleEvent: event &rest)
			)
		)
	)
	
	(method (scriptCheck)
		(if (== (boatDoor script?) knockOnDoorScr)
			(messager say: 7 0 16 0 0 0)
			(return 0)
		)
		(return 1)
	)
)

(instance myMoveCycle of MoveCycle
	(properties)
	
	(method (nextCel &tmp temp0)
		(cond 
			((== (= temp0 (WordAt points value)) -4095)
				(client setPri: (WordAt points (+ value 1)))
				(= value (+ value (* cycleDir 2)))
				(= temp0 (WordAt points value))
			)
			((== temp0 -4094)
				(client setPri: -1)
				(= value (+ value cycleDir))
				(= temp0 (WordAt points value))
			)
		)
		(client
			loop: (WordAt points value)
			cel: (WordAt points (+ value 1))
			x: (WordAt points (+ value 2))
			y: (WordAt points (+ value 3))
		)
		(= value (+ value (* cycleDir 4)))
		(if
			(or
				(and (== cycleDir 1) (>= value size))
				(and (== cycleDir -1) (< value 0))
			)
			(self cycleDone:)
		)
	)
)

(instance birdTimedCue of TimedCue
	(properties
		register 1
	)
)

(instance bailScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 2)
			)
			(1 (curRoom newRoom: 250))
		)
	)
)

(instance sailScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client cycleSpeed: (Random 10 19) setCycle: EndLoop self)
			)
			(1 (= cycles 2))
			(2
				(client cycleSpeed: (Random 10 19) setCycle: BegLoop self)
				(= state -1)
			)
		)
	)
)

(instance ferrymanScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client loop: 1 cel: 0 posn: 117 77 setCycle: EndLoop self)
			)
			(1 (= ticks (Random 45 90)))
			(2 (client setCycle: EndLoop self))
			(3 (= ticks 30))
			(4
				(client loop: 2 cel: 0 posn: 104 77 setCycle: EndLoop self)
			)
			(5 (= cycles 2))
			(6
				(theGame handsOn:)
				(client dispose:)
			)
		)
	)
)

(instance snakeScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 3 5)))
			(1 (client setCycle: EndLoop self))
			(2 (= cycles 2))
			(3 (client dispose:))
		)
	)
)

(instance snake of Prop
	(properties
		x 313
		y 64
		view 264
		loop 2
		scaleSignal $0001
		scaleX 64
		scaleY 64
	)
)

(instance smFerryman of Actor
	(properties
		x 117
		y 77
		view 263
		loop 1
		signal $4000
	)
)

(class BeachBird of Prop
	(properties
		x 0
		y 0
		z 0
		heading 0
		noun 0
		modNum -1
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		sightAngle 26505
		actions 0
		onMeCheck $6789
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		yStep 2
		view 267
		loop 0
		cel 0
		priority 0
		underBits 0
		signal $4000
		lsTop 0
		lsLeft 0
		lsBottom 0
		lsRight 0
		brTop 0
		brLeft 0
		brBottom 0
		brRight 0
		scaleSignal $0000
		scaleX 128
		scaleY 128
		maxScale 128
		cycleSpeed 6
		script 0
		cycler 0
		timer 0
		detailLevel 0
		scaler 0
		previous 0
	)
	
	(method (init)
		(super init: &rest)
		(self fly:)
	)
	
	(method (cue param1)
		(cond 
			((== param1 1) (BeachBird show:) (self fly:))
			((== param1 4660) (self setScript: birdTimedCue (Random 4 12)))
			(else
				(BeachBird hide:)
				(if (not cuees) (= cuees (Set new:)))
				(cuees
					add: ((Cue new:)
						cuee: self
						cuer: self
						register: 4660
						yourself:
					)
				)
			)
		)
	)
	
	(method (fly &tmp thePrevious)
		(while (== previous (= thePrevious (Random 0 3)))
		)
		(switch (= previous thePrevious)
			(0
				(self setPri: 2 cycleSpeed: 5 setCycle: MoveCycle @local3 self)
			)
			(1
				(self
					setPri: 1
					cycleSpeed: 10
					setCycle: MoveCycle @local125 self
				)
			)
			(2
				(self
					setPri: 1
					cycleSpeed: 9
					setCycle: MoveCycle @local219 self
				)
			)
			(3
				(self
					setPri: 1
					cycleSpeed: 7
					setCycle: MoveCycle @local325 self
				)
			)
			(4
				(self
					setPri: 2
					cycleSpeed: 6
					setCycle: MoveCycle @local467 self
				)
			)
		)
	)
)

(instance toWaterNotFromPierScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (& (ego onControl: 1) $2000)
					(ego setMotion: PolyPath 196 73 self)
				else
					(ego setMotion: PolyPath 181 109 self)
				)
			)
			(1
				(theGame handsOn:)
				(localproc_1e5d)
				(self dispose:)
			)
		)
	)
)

(instance egoFallRightScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(LoadMany 128 296 269)
				(ego
					normal: 0
					setSpeed: 6
					view: 296
					loop: 2
					cel: 0
					setScale:
				)
				(= cycles 2)
			)
			(1 (ego setCycle: EndLoop self))
			(2 (= ticks 45))
			(3
				(ego loop: 3 cel: 0)
				(= ticks 15)
			)
			(4
				(soundFx2 number: 923 loop: 1 play:)
				(ego setPri: 1 setCycle: EndLoop self)
			)
			(5
				(theMusic stop:)
				(soundFx number: 921 loop: 1 play:)
				(ego
					view: 269
					posn: (+ (ego x?) 21) (+ (ego y?) 48)
					setLoop: 0
					setSpeed: 5
					setStep: 5 5
					cel: 0
					setScale: Scaler 100 (/ (* (ego scaleX?) 100) 128) 190 (ego y?)
					setMotion: MoveTo 320 210 self
				)
				(self setScript: egoDrowningScr)
			)
			(6 (curRoom newRoom: 135))
		)
	)
)

(instance egoFallForwardScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(LoadMany 128 296 269)
				(ego
					setSpeed: 6
					normal: 0
					view: 296
					setPri: 15
					posn: (- (ego x?) 5) (+ (ego y?) 4)
					loop: 0
					cel: 0
					scaleX: 109
					scaleY: 109
					setScale:
				)
				(= cycles 2)
			)
			(1 (ego setCycle: EndLoop self))
			(2 (= ticks 10))
			(3
				(ego loop: 1 cel: 0)
				(= ticks 30)
			)
			(4
				(soundFx2 number: 923 loop: 1 play:)
				(ego
					cel: 1
					posn: (+ (ego x?) 3) (+ (ego y?) 4)
					setCycle: EndLoop self
				)
			)
			(5
				(theMusic stop:)
				(soundFx number: 921 loop: 1 play:)
				(ego
					view: 269
					setLoop: 0
					cel: 3
					posn: (ego x?) 186
					setScale: Scaler 100 (/ (* (ego scaleX?) 100) 128) 190 (ego y?)
					setMotion: MoveTo (ego x?) 210 self
				)
				(self setScript: egoDrowningScr)
			)
			(6 (curRoom newRoom: 135))
		)
	)
)

(instance egoDrowningScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (ego setCycle: CycleTo 7 1 self))
			(1 (= cycles 2))
			(2 (ego setCycle: CycleTo 3 -1 self))
			(3 (= state -1) (= cycles 2))
		)
	)
)

(instance enterVillage2Scr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego posn: 266 53 setMotion: MoveTo 226 61 self)
			)
			(1
				(ego setMotion: MoveTo 197 74 self)
			)
			(2
				(ego loop: 2)
				(if
					(and
						(not (cast contains: genieBoy))
						(not (cast contains: smFerryman))
					)
					(theGame handsOn:)
				)
				(= cycles 1)
			)
			(3 (self dispose:))
		)
	)
)

(instance genieBailScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: 0)
				(= cycles 2)
			)
			(1 (ego setHeading: 180 self))
			(2
				(if (< (genieScr state?) 25)
					(genieScr state: 26 seconds: 0 ticks: 0)
					(if (not (genieScr cycles?)) (genieScr cycles: 1))
				)
				((genieBoy script?) caller: self)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance changeMusicScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic number: 915 loop: -1 play: 0 fade: 127 10 25 0)
				(theGlobalSound number: 916 loop: -1 play:)
				(= state -1)
				(= client 0)
			)
		)
	)
)

(instance eye of Prop
	(properties
		x 234
		y 90
		view 902
		priority 15
		signal $0010
	)
)

(instance genieScr of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(cond 
			((not local2) 0)
			(
			(and (< (ego y?) 87) (!= (genieBoy loop?) 7)) (genieBoy loop: 7))
			(
				(and
					(>= (ego y?) 87)
					(<= (ego y?) 111)
					(!= (genieBoy loop?) 1)
				)
				(genieBoy loop: 1)
			)
			(
			(and (>= (ego y?) 111) (!= (genieBoy loop?) 5)) (genieBoy loop: 5))
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= ticks 12))
			(1
				(eye init: setCycle: EndLoop self)
			)
			(2 (= ticks 30))
			(3 (eye setCycle: BegLoop self))
			(4
				(eye dispose:)
				(genieBoy setCycle: CycleTo 7 1 self)
			)
			(5 (= cycles 2))
			(6
				(soundFx2 number: 923 loop: 1 play:)
				(genieBoy cel: 8 posn: 233 118)
				(= cycles 2)
			)
			(7
				(genieBoy setCycle: EndLoop self)
			)
			(8 (= ticks 48))
			(9
				(genieBoy
					view: 262
					loop: 7
					cel: 0
					posn: 267 145
					setCycle: GenieCycler
				)
				(= ticks 12)
			)
			(10
				(genieBoy setMotion: MoveTo 267 131 self)
			)
			(11
				(genieBoy cycleSpeed: 9 loop: 9 cel: 0 setCycle: EndLoop self)
			)
			(12
				(genieBoy cel: 0 setCycle: EndLoop self)
			)
			(13
				(genieBoy cycleSpeed: 6 setCycle: GenieCycler loop: 7)
				(= cycles 2)
			)
			(14
				(messager say: 1 0 2 0 self)
			)
			(15
				(= local2 1)
				(if (curRoom script?)
					((curRoom script?) caller: self)
				else
					(= cycles 2)
				)
			)
			(16
				(ego setMotion: MoveTo 221 97 self)
			)
			(17 (ego setHeading: 90 self))
			(18
				(theGame handsOn:)
				(= seconds 6)
			)
			(19
				(= local2 0)
				(genieBoy
					setLoop: (if (== (genieBoy loop?) 1) 10 else 9)
					cel: 0
				)
				(= ticks 72)
			)
			(20 (= local2 1) (= ticks 12))
			(21
				(messager say: 1 0 3 0 self)
			)
			(22 (= seconds 6))
			(23
				(= local2 0)
				(genieBoy
					setLoop: (if (== (genieBoy loop?) 1) 10 else 9)
					cel: 0
				)
				(= ticks 72)
			)
			(24 (= local2 1) (= cycles 2))
			(25
				(messager say: 1 0 4 0 self)
			)
			(26 (= seconds 7))
			(27
				(messager say: 1 0 5 1 self)
			)
			(28
				(= local2 0)
				(genieBoy
					view: 262
					loop: 11
					cel: 0
					noun: 7
					setCycle: EndLoop self
				)
				(soundFx2 number: 943 loop: 1 play:)
			)
			(29 (= cycles 2))
			(30
				(changeMusicScr client: self)
				(theMusic client: changeMusicScr fade:)
				(messager say: 1 0 5 2 self)
			)
			(31
				(= local2 0)
				(client dispose:)
			)
		)
	)
)

(instance genieBoy of Actor
	(properties
		x 229
		y 110
		noun 6
		approachX 221
		approachY 97
		view 266
		loop 1
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 2)
	)
	
	(method (doVerb theVerb)
		(if (== noun 7)
			(super doVerb: theVerb &rest)
		else
			(switch theVerb
				(2
					(if (not (cast contains: self)) (return))
					(if local0
						(= local0 0)
						(messager say: noun theVerb 11)
					else
						(messager say: noun theVerb 12)
					)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
)

(instance eyeGlint1 of Prop
	(properties
		x 275
		y 142
		view 262
		loop 3
		priority 15
		signal $0010
	)
	
	(method (init)
		(super init: &rest)
		(self setCycle: EndLoop genieScr)
	)
)

(instance diveIntoWaterScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= register (cast contains: genieBoy))
				(if register (Bset 79) (genieBoy setScript: 0))
				(= cycles 1)
			)
			(1
				(messager say: 7 3 13 1 self)
			)
			(2
				(ego setMotion: PolyPath 232 114 self)
			)
			(3
				(ego
					normal: 0
					setPri: 8
					setSpeed: 6
					view: 265
					loop: 0
					cel: 0
					posn: 227 115
					setScale: 0
					scaleX: 98
					scaleY: 98
					scaleSignal: 1
					setCycle: CycleTo 7 1 self
				)
				(if register (genieBoy loop: 5))
			)
			(4
				(soundFx2 number: 923 loop: 1 play:)
				(ego posn: 229 124 setCycle: EndLoop self)
				(if register (genieBoy loop: 2))
			)
			(5
				(if register
					(messager say: 7 3 13 2 self)
				else
					(self cue:)
				)
			)
			(6
				(messager say: 7 3 13 3 self)
			)
			(7
				(theMusic stop:)
				(soundFx number: 921 loop: 1 play:)
				(if register
					(genieBoy setHeading: 180 setMotion: MoveFwd 100)
				)
				(ego
					view: 269
					moveSpeed: 3
					setLoop: 0
					cel: 3
					posn: 282 168
					setScale: Scaler 100 (/ (* (ego scaleX?) 100) 128) 190 (ego y?)
					setMotion: MoveTo 305 210 self
				)
				(self setScript: egoDrowningScr)
			)
			(8 (curRoom newRoom: 135))
		)
	)
)

(instance ocean of Feature
	(properties
		noun 7
		sightAngle 40
		onMeCheck $0004
		approachX 228
		approachY 110
	)
	
	(method (doVerb theVerb)
		(if (OneOf theVerb 5 1)
			(super doVerb: theVerb &rest)
		else
			(= noun 7)
			(super doVerb: theVerb &rest)
		)
	)
	
	(method (onMe event &tmp temp0)
		(ocean x: (event x?) y: (event y?))
		(if (= temp0 (super onMe: event &rest))
			(if (> (event y?) 56)
				(= x 320)
				(= y 91)
				(= noun 7)
			else
				(= x 61)
				(= y 54)
				(= noun 29)
			)
		)
		(return temp0)
	)
)

(instance knockOnDoorScr of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(if (and seconds (not (ego inRect: 5 94 33 109)))
			(= seconds 0)
			(self cue:)
		)
	)
	
	(method (dispose &tmp curRoomScript)
		(= seconds 0)
		(if
			(and
				(= curRoomScript (curRoom script?))
				(not
					(OneOf curRoomScript talkReferenceScr talkOrItemScr)
				)
			)
			(curRoomScript dispose:)
		)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Load rsSOUND 908)
				(Load rsVIEW 261)
				(ego setHeading: 45 self)
			)
			(1
				(if (cast contains: genieBoy)
					(if (< (genieScr state?) 25)
						(genieScr state: 26 seconds: 0 ticks: 0 cycles: 0)
						(if (not (genieScr cycles?)) (genieScr cycles: 1))
					)
					((genieBoy script?) caller: self)
				else
					(= ticks 20)
				)
			)
			(2
				(ego
					normal: 0
					view: 261
					posn: 20 108
					loop: 2
					cel: 0
					scaleX: 128
					scaleY: 128
					setScale:
					setMotion: 0
					setSpeed: 6
				)
				(= cycles 2)
			)
			(3
				(ego setCycle: EndLoop self)
				(= register 5)
			)
			(4
				(-- register)
				(ego cel: 0 loop: 3 setCycle: EndLoop self)
			)
			(5
				(soundFx2 number: 908 loop: 1 play:)
				(if register
					(= state (- state 2))
					(= ticks 25)
				else
					(self cue:)
				)
			)
			(6 (ego setCycle: BegLoop self))
			(7 (= cycles 2))
			(8
				(ego
					posn: (boatDoor approachX?) (boatDoor approachY?)
					reset: 6
					setScale: Scaler 97 20 150 30
				)
				(= cycles 2)
			)
			(9
				(UnLoad 128 261)
				(= ticks 60)
			)
			(10
				(ferryMan view: 263 loop: 2 cel: 8 posn: 23 96 init:)
				(soundFx2 number: 901 loop: 1 play:)
				(boatDoor setCycle: EndLoop self)
			)
			(11 (= ticks 60))
			(12
				(if (Btst 17)
					(= state (+ state 4))
					(messager say: 2 5 8 0 self)
				else
					(messager say: 2 5 (if (Bset 106) 18 else 7) 0 self)
				)
			)
			(13
				(theGame handsOn:)
				(= seconds 10)
			)
			(14
				(theGame handsOff:)
				(messager say: 1 0 6 1 self)
			)
			(15
				(boatDoor setCycle: BegLoop self)
			)
			(16
				(ferryMan dispose:)
				(soundFx2 number: 902 loop: 1 play:)
				(boatDoor stopUpd:)
				(theGame handsOn:)
				(self dispose:)
			)
			(17 (curRoom newRoom: 290))
		)
	)
)

(instance boatDoor of Prop
	(properties
		x 22
		y 80
		noun 2
		approachX 12
		approachY 107
		view 260
	)
	
	(method (init)
		(super init: &rest)
		(self sightAngle: 26505 approachVerbs: 5 stopUpd:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(self setScript: knockOnDoorScr)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance talkReferenceScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(boatDoor setScript: 0)
				(messager say: 5 2 10 0 self)
			)
			(1 (curRoom newRoom: 290))
		)
	)
)

(instance talkOrItemScr of Script
	(properties)
	
	(method (dispose)
		(= register 0)
		(super dispose:)
	)
	
	(method (changeState newState &tmp temp0 temp1)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(boatDoor setScript: 0)
				(if (== register 2)
					(if (Bset 105) (= temp0 17) else (= temp0 9))
				else
					(= temp0 0)
				)
				(messager say: 5 register temp0 0 self)
			)
			(1 (= cycles 2))
			(2
				(boatDoor setCycle: BegLoop self)
			)
			(3
				(ferryMan dispose:)
				(soundFx2 number: 902 loop: 1 play:)
				(boatDoor stopUpd:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance ferryMan of View
	(properties
		x 23
		y 96
		noun 5
		view 263
		loop 2
		cel 8
		priority 3
		signal $0010
	)
	
	(method (init)
		(super init: &rest)
		(self stopUpd:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(2
				(if (Btst 16)
					(curRoom setScript: talkReferenceScr)
				else
					(curRoom setScript: talkOrItemScr 0 theVerb)
				)
			)
			(70
				(if (Bset 104)
					(messager say: noun theVerb 16)
				else
					(messager say: noun theVerb 15)
				)
			)
			(40
				(curRoom setScript: talkOrItemScr 0 theVerb)
			)
			(else 
				(if (== (approachCode doit: theVerb) -32768)
					(curRoom setScript: talkOrItemScr 0 0)
				else
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
)

(instance hatch of Feature
	(properties
		x 100
		y 78
		noun 24
		nsTop 74
		nsLeft 91
		nsBottom 81
		nsRight 109
		sightAngle 45
	)
)

(instance genericFeatures of Feature
	(properties
		sightAngle 45
	)
	
	(method (onMe event &tmp temp0)
		(= temp0 (OnControl 4 (event x?) (event y?)))
		(genericFeatures x: (event x?) y: (event y?))
		(= noun
			(switch temp0
				(1024 10)
				(2048 15)
				(8 14)
				(16
					(if (< (event x?) 170) 17 else 0)
				)
				(512 23)
				(256 19)
				(2 8)
				(4096 13)
				(64
					(if (> (event y?) 74) 16 else 22)
				)
				(else 
					(if (OneOf temp0 16384 8192) 18 else 0)
				)
			)
		)
	)
)

(instance sail1 of Prop
	(properties
		x 80
		y 83
		z 83
		noun 14
		view 260
		loop 2
		priority 15
		signal $5010
	)
	
	(method (cue)
		(self
			cycleSpeed: (- (sailScr register?) 1)
			setCycle: (ScriptID 231) (- (self lastCel:) 1)
		)
	)
)

(instance sail2 of Prop
	(properties
		x 54
		y 83
		z 75
		noun 14
		view 260
		loop 3
		priority 15
		signal $5010
	)
)

(instance sail3 of Prop
	(properties
		x 21
		y 83
		z 57
		noun 14
		view 260
		loop 4
		cel 3
		priority 15
		signal $1010
	)
)

(instance sail4 of View
	(properties
		x 9
		y 83
		z 52
		noun 14
		view 260
		loop 5
		priority 15
		signal $1010
	)
)

(instance egoDoVerb of Actions
	(properties)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(12
					(curRoom setScript: 130)
					(return 1)
				)
				(else  (return 0))
			)
		)
	)
)

(instance reflect1 of Prop
	(properties
		x 65
		y 176
		noun 7
		view 268
		cel 2
		signal $4000
		cycleSpeed 10
		detailLevel 3
	)
)

(instance reflect2 of Prop
	(properties
		x 146
		y 178
		noun 7
		view 268
		loop 1
		cel 1
		priority 15
		signal $4010
		cycleSpeed 10
		detailLevel 3
	)
)

(instance reflect3 of Prop
	(properties
		x 210
		y 186
		noun 7
		view 268
		loop 2
		cel 2
		priority 15
		signal $4010
		cycleSpeed 10
		detailLevel 3
	)
)

(instance reflect4 of Prop
	(properties
		x 178
		y 136
		noun 7
		view 268
		loop 3
		signal $4000
		cycleSpeed 10
		detailLevel 3
	)
)

(instance reflect5 of Prop
	(properties
		x 277
		y 113
		noun 7
		view 268
		loop 4
		cel 1
		priority 1
		signal $4010
		cycleSpeed 20
		detailLevel 3
	)
)

(instance holeInBoat of Feature
	(properties
		x 91
		y 100
		noun 11
		nsTop 112
		nsLeft 83
		nsBottom 123
		nsRight 100
		sightAngle 45
	)
)
