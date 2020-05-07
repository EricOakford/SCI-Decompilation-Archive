;;; Sierra Script 1.0 - (do not remove this comment)
(script# 480)
(include sci.sh)
(use Main)
(use CryBaby)
(use n482)
(use n483)
(use NewRoomCue)
(use Kq6Procs)
(use Print)
(use Conv)
(use Osc)
(use RandCyc)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Reverse)
(use Timer)
(use Sound)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm480 0
	hiw 1
	brat1 2
	drinkBottle 3
	myTeaCup 4
	wallFlowerDance 5
	myBottle 6
	gates 7
	rotTomato 8
)

(local
	local0
	local1
	local2
	local3
	local4
)
(instance myConv of Conversation
	(properties)
)

(instance rm480 of KQ6Room
	(properties
		noun 3
		picture 480
		walkOffEdge 1
		autoLoad 0
	)
	
	(method (init)
		(if
		(and (== prevRoomNum 99) (FileIO fiEXISTS {g}))
			(Bset 77)
			(ego get: 14)
			(ego get: 22)
			((inventory at: 33) owner: curRoomNum)
			(= currentAct 4)
		)
		(theGame handsOn:)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						0
						189
						0
						0
						319
						0
						319
						189
						148
						189
						216
						158
						228
						144
						291
						141
						297
						133
						232
						136
						216
						126
						214
						116
						313
						86
						302
						82
						178
						101
						116
						101
						74
						102
						63
						95
						3
						91
						3
						101
						38
						101
						38
						109
						25
						116
						2
						116
						2
						135
						34
						135
						34
						145
						26
						158
						1
						158
						1
						188
						59
						189
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 68 109 40 109 40 101 66 101 72 106
					yourself:
				)
				((Polygon new:)
					type: 2
					init:
						186
						125
						171
						138
						153
						141
						110
						141
						97
						136
						101
						127
						64
						127
						64
						116
						104
						116
						106
						110
						160
						110
						186
						120
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 147 160 84 160 84 144 158 144 163 155
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 16 123 36 112 54 112 62 120 62 130 20 130
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 27 161 37 145 82 145 82 161
					yourself:
				)
		)
		(super init: &rest)
		(theMusic number: 480 setLoop: -1 play:)
		((ScriptID 40 0) lampMsg: 15)
		(features
			add:
				sourGrapes
				chokers
				greenMaters
				pathway
				table
				lettuce
				chair
				pillars
				wall
			eachElementDo: #init
		)
		(if (== prevRoomNum 490)
			(gates cel: 4 signal: 26624 init:)
		else
			(gates cel: 0 signal: 16384 addToPic:)
		)
		(snap init:)
		(flower1 init:)
		(flower2 init:)
		(flower3 init:)
		(flower4 init:)
		(if (== ((inventory at: 49) owner?) curRoomNum)
			(rotTomato init:)
		)
		(if
			(and
				(== ((inventory at: 33) owner?) curRoomNum)
				(> currentAct 3)
			)
			(drinkBottle init:)
		)
		(brat1 init:)
		(brat2 init:)
		(brat3 init:)
		(brat4 init:)
		(if
			(and
				(Btst 77)
				(== ((inventory at: 46) owner?) curRoomNum)
			)
			(myTeaCup init: stopUpd:)
			(glint init: hide:)
			(glintTimer setReal: glint (Random 3 6))
		)
		(ego actions: fluteVerb init:)
		(curRoom setScript: egoEnters)
		(if (== ((inventory at: 18) owner?) curRoomNum)
			(hiw init:)
		)
		(Load rsMESSAGE 480)
	)
	
	(method (doit)
		(cond 
			((ego script?))
			((curRoom script?))
			((== (ego edgeHit?) 3) (curRoom setScript: egoExits))
			((ego inRect: 207 120 312 152)
				(ego setMotion: 0)
				(if (== (ego loop?) 3)
					((ScriptID 480 5) register: 1)
					(ego setScript: coverThatButtScr)
				else
					((ScriptID 480 5) register: 1)
					(curRoom setScript: stepOnSnaps)
				)
			)
			(
				(or
					(== (ego onControl: 1) 4096)
					(== (ego onControl: 1) 8192)
				)
				((ScriptID 480 5) register: 1)
				(ego setScript: hanging 0 3)
			)
			(
				(and
					(ego inRect: 194 80 300 100)
					(not ((ScriptID 40 0) flowerDance?))
				)
				((ScriptID 480 5) register: 1)
				(ego setScript: shyFlowers)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(LoadMany 0 481 482 483 939 969)
		((ScriptID 40 0) bottleSucker: 0)
		(super dispose:)
	)
	
	(method (cue param1)
		(switch param1
			(3 (wallFlowerDance cue:))
			(0
				((ScriptID 40 0) flowerDance: 0)
				(flower1 setLoop: 0 setCycle: Beg)
				(flower2 setLoop: 1 setCycle: Beg)
				(flower3 setLoop: 2 setCycle: Beg)
				(flower4 setLoop: 3 setCycle: Beg)
				(snap setCycle: Beg)
			)
			(else 
				(flower1 setCycle: Beg)
				(flower2 setCycle: Beg)
				(flower3 setCycle: Beg)
				(flower4 setCycle: Beg flower1)
				(snap setCycle: Beg)
			)
		)
	)
	
	(method (newRoom n)
		(glintTimer dispose: delete:)
		(roomTimer dispose: delete:)
		(super newRoom: n)
	)
	
	(method (notify)
		(flower1 view: 4851 stopUpd:)
		(flower2 view: 4851 stopUpd:)
		(flower3 view: 4851 stopUpd:)
		(flower4 view: 4851 stopUpd:)
		(UnLoad 128 4852)
		(snap setCycle: 0 stopUpd:)
	)
)

(instance hiw of Actor
	(properties
		view 482
		signal $6800
		cycleSpeed 12
		illegalBits $0000
	)
	
	(method (init)
		(if (Btst 159)
			(self
				setLoop: 4
				cel: 5
				posn: 274 57
				cycleSpeed: 6
				setCycle: Beg self
			)
			(soundFx2 number: 483 setLoop: 1 play:)
		else
			(self setLoop: 1 posn: 283 46 stopUpd:)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (Btst 159)
					(messager say: 21 1 7 1)
				else
					(wallFlowerDance register: 1)
					(= local4 1)
					(proc482_2)
				)
			)
			(5
				(cond 
					((== (curRoom script?) wallFlowerDance) (proc482_1))
					((Btst 159)
						((ScriptID 40 0) grabAtHidingHole: 1)
						(ego setScript: walkToHoleScr)
					)
					(else
						(Bset 159)
						(if (sounds contains: danceMusic)
							(danceMusic fade: 10 10 0 1)
						)
						(proc482_0)
					)
				)
			)
			(2
				(if (Btst 159)
					(messager say: 21 2 7 0)
				else
					(messager say: 21 2 8 0)
				)
			)
			(else  (messager say: 21 0 0 1))
		)
	)
	
	(method (cue)
		(self setLoop: 1 cycleSpeed: 12 posn: 238 70 stopUpd:)
	)
)

(instance gates of Prop
	(properties
		x 142
		y 76
		noun 15
		approachX 148
		approachY 106
		view 4801
		cycleSpeed 12
	)
	
	(method (init)
		(self approachVerbs: 2 0)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(curRoom setScript: thruGate)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance snap of Prop
	(properties
		x 277
		y 129
		noun 11
		view 4801
		loop 1
		priority 8
		signal $6810
		cycleSpeed 1
	)
	
	(method (init)
		(self stopUpd:)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(super doVerb: theVerb &rest)
			)
			(5
				((ScriptID 480 5) register: 1)
				(ego setScript: snappy 0 5)
			)
			(2
				((ScriptID 480 5) register: 1)
				(ego setScript: talkToSnaps)
			)
			(else 
				((ScriptID 480 5) register: 1)
				(ego setScript: snappy 0 0)
			)
		)
	)
	
	(method (cue)
		(self setCycle: 0 cel: 0)
	)
)

(instance myBottle of Prop
	(properties
		view 4861
		signal $4010
	)
	
	(method (init)
		(self setCycle: RandCycle)
		((ScriptID 40 0) babyFed: 1)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5 (proc481_0))
			(43 (messager say: 9 43 17 1))
			(else  (brat1 doVerb: theVerb))
		)
	)
)

(instance drinkBottle of View
	(properties
		x 240
		y 156
		z 20
		noun 8
		approachX 215
		approachY 155
		view 4811
		loop 4
		priority 12
		signal $6810
	)
	
	(method (init)
		(self stopUpd: approachVerbs: 0 2 1)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(theGame givePoints: 1)
				(proc483_0 0)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance myTeaCup of View
	(properties
		x 305
		y 86
		z 22
		noun 22
		view 4801
		loop 2
		priority 2
		signal $6810
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(theGame givePoints: 1)
				(proc483_0 1)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance rotTomato of View
	(properties
		x 132
		y 152
		noun 6
		approachX 180
		approachY 160
		view 4801
		loop 3
		priority 12
		signal $6810
	)
	
	(method (init)
		(self approachVerbs: 2 1 0 stopUpd:)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if ((ScriptID 40 0) tomoTalk?)
					(messager say: 6 1 12 1)
				else
					(myConv
						add: -1 6 1 11 1
						add: -1 6 1 11 2
						add: -1 6 1 11 3
						init:
					)
				)
			)
			(2
				(if ((ScriptID 40 0) tomoTalk?)
					(messager say: 6 2 28 1)
				else
					((ScriptID 40 0) tomoTalk: 1)
					(myConv
						add: -1 6 2 27 1
						add: -1 6 2 27 2
						add: -1 6 2 27 3
						add: -1 6 2 27 4
						add: -1 6 2 27 5
						add: -1 6 2 27 6
						init:
					)
				)
			)
			(5
				(ego get: 49)
				(proc483_2 self)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance lettuce of Feature
	(properties
		noun 10
		onMeCheck $0100
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (Btst 84)
					(messager say: 10 1 29 0)
				else
					(Bset 84)
					(messager say: 10 1 30 0)
				)
			)
			(5
				(cond 
					((not (ego has: 21)) (proc483_3 self))
					((< (- (GetTime 1) global157) 150) (messager say: 10 5 32 1))
					((< (- (GetTime 1) global157) 300) (proc483_3 self))
					(else (proc483_3 self))
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance chair of Feature
	(properties
		noun 17
		onMeCheck $4000
	)
)

(instance flower1 of Prop
	(properties
		x 215
		y 91
		noun 5
		approachX 225
		approachY 95
		view 4851
		signal $7800
		cycleSpeed 7
	)
	
	(method (init)
		(self approachVerbs: 5)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(31
				(if ((ScriptID 40 0) flowerDance?)
					(messager say: 5 5 10 1)
				else
					(curRoom setScript: wallFlowerDance)
				)
			)
			(1
				(super doVerb: theVerb &rest)
			)
			(2
				(super doVerb: theVerb &rest)
			)
			(5
				(if ((ScriptID 40 0) flowerDance?)
					(messager say: 5 5 10 1)
				else
					(super doVerb: theVerb &rest)
				)
			)
			(else 
				(self setScript: doFlowerInv)
			)
		)
	)
	
	(method (cue)
		(snap setCycle: 0 stopUpd:)
		(flower1 view: 4852)
		(flower2 view: 4852)
		(flower3 view: 4852)
		(flower4 view: 4852)
	)
)

(instance doFlowerInv of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 180 106 self)
			)
			(1
				(ego setHeading: 90)
				(= cycles 8)
			)
			(2 (messager say: 5 0 0 0 self))
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance flower2 of Prop
	(properties
		x 229
		y 93
		noun 5
		approachX 225
		approachY 95
		view 4851
		loop 1
		signal $7800
		cycleSpeed 7
	)
	
	(method (init)
		(self approachVerbs: 5)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(flower1 doVerb: theVerb &rest)
	)
)

(instance flower3 of Prop
	(properties
		x 252
		y 84
		noun 5
		approachX 225
		approachY 95
		view 4851
		loop 2
		signal $7800
		cycleSpeed 7
	)
	
	(method (init)
		(self approachVerbs: 5)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(flower1 doVerb: theVerb &rest)
	)
)

(instance flower4 of Prop
	(properties
		x 253
		y 85
		noun 5
		approachX 225
		approachY 95
		view 4851
		loop 3
		signal $7800
		cycleSpeed 7
	)
	
	(method (init)
		(self approachVerbs: 5)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(flower1 doVerb: theVerb &rest)
	)
)

(instance table of Feature
	(properties
		x 250
		y 150
		noun 14
		nsTop 133
		nsLeft 231
		nsBottom 147
		nsRight 274
	)
)

(instance chokers of Feature
	(properties
		x 60
		y 90
		noun 13
		onMeCheck $0040
		approachX 173
		approachY 109
	)
	
	(method (init)
		(self approachVerbs: 0)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5 (proc483_4 5))
			(2
				((ScriptID 480 5) register: 1)
				(ego setScript: talkToVines 0 self)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance sourGrapes of Feature
	(properties
		x 16
		y 85
		noun 12
		onMeCheck $0020
		approachX 16
		approachY 95
	)
	
	(method (init)
		(self approachVerbs: 0)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(2
				((ScriptID 480 5) register: 1)
				(ego setScript: talkToVines 0 self)
			)
			(5 (proc483_1 self))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance greenMaters of Feature
	(properties
		x 120
		y 150
		noun 7
		onMeCheck $1080
		approachX 180
		approachY 160
	)
	
	(method (init)
		(self approachVerbs: 2)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(2
				(myConv add: -1 7 2 0 1 add: -1 7 2 0 2 init:)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance pathway of Feature
	(properties
		noun 18
		onMeCheck $0002
	)
)

(instance pillars of Feature
	(properties
		noun 19
		onMeCheck $0008
	)
)

(instance wall of Feature
	(properties
		noun 16
		onMeCheck $0004
	)
)

(class Brat of Feature
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
		bottleNum 0
		walkToX 0
		walkToY 0
		stoopX 0
		stoopY 0
	)
	
	(method (init)
		(self approachVerbs: 2)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(14
				(messager say: 9 14 0 1 0 480)
			)
			(5
				(cond 
					(
					(== ((ScriptID 40 0) bottleSucker?) (self bottleNum?))
						((ScriptID 480 5) register: 1)
						(ego setScript: (ScriptID 481 2) 0 myBottle)
					)
					((== ((ScriptID 40 0) lampMsg?) 15) (messager say: 9 5 15 1 0 480))
					(else (messager say: 9 5 18 1 0 480))
				)
			)
			(43
				(cond 
					(
					(== ((ScriptID 40 0) bottleSucker?) (self bottleNum?)) (messager say: 9 43 17 1 0 480))
					((not (Btst 77)) (messager say: 9 43 21 1 0 480))
					((or (& global161 $0004) (Btst 144)) (messager say: 9 43 20 1 0 480))
					((& global161 $0001) (messager say: 9 43 13 1 0 480))
					((& global161 $0002) (proc481_3 (self bottleNum?)))
					((== ((ScriptID 40 0) lampMsg?) 15) (messager say: 9 43 15 1 0 480))
					(else (proc481_3 (self bottleNum?)))
				)
			)
			(1
				(if (== ((ScriptID 40 0) lampMsg?) 15)
					(messager say: 9 1 ((ScriptID 40 0) lampMsg?) 1 0 480)
				else
					(messager say: 9 1 16 1 0 480)
				)
			)
			(2
				(if (== ((ScriptID 40 0) lampMsg?) 15)
					(messager say: 9 2 ((ScriptID 40 0) lampMsg?) 0 0 480)
				else
					(messager say: 9 2 16 0 0 480)
				)
			)
			(62
				(ego put: 22 480)
				(proc481_1 (self bottleNum?))
			)
			(44
				(cond 
					(
					(== ((ScriptID 40 0) bottleSucker?) (self bottleNum?)) (messager say: 9 44 17 1 0 480))
					((not (Btst 77)) (messager say: 9 44 21 1 0 480))
					(else (messager say: 9 44 22 1 0 480))
				)
			)
			(24
				(if (== ((ScriptID 40 0) lampMsg?) 15)
					(messager say: 9 24 15 1 0 480)
				else
					(messager say: 9 24 16 1 0 480)
				)
			)
			(else 
				(if (OneOf theVerb 57 58 59 60 96)
					(if
					(== ((ScriptID 40 0) bottleSucker?) (self bottleNum?))
						(messager say: 9 56 17 0 0 480)
					else
						(messager say: 9 56 ((ScriptID 40 0) lampMsg?) 0 0 480)
					)
				else
					((ScriptID 480 5) register: 1)
					(ego setScript: inventOnBaby 0 self)
				)
			)
		)
	)
)

(instance inventOnBaby of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					setMotion: PolyPath (register walkToX?) (register walkToY?) self
				)
			)
			(1
				(= temp0
					(GetAngle
						(ego x?)
						(ego y?)
						(register x?)
						(register y?)
					)
				)
				(ego setHeading: temp0 self)
			)
			(2 (= cycles 6))
			(3
				(if (== ((ScriptID 40 0) lampMsg?) 15)
					(messager
						say: 9 0 ((ScriptID 40 0) lampMsg?) 0 self 480
					)
				else
					(messager say: 9 0 16 0 self 480)
				)
			)
			(4
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance brat1 of Brat
	(properties
		x 58
		y 152
		noun 9
		onMeCheck $0200
		approachX 95
		approachY 159
		bottleNum 1
		walkToX 95
		walkToY 159
		stoopX 83
		stoopY 162
	)
)

(instance brat2 of Brat
	(properties
		x 10
		y 147
		noun 9
		onMeCheck $0010
		approachX 55
		approachY 153
		bottleNum 2
		walkToX 55
		walkToY 153
		stoopX 36
		stoopY 157
	)
)

(instance brat3 of Brat
	(properties
		x 39
		y 122
		noun 9
		onMeCheck $0400
		approachX 81
		approachY 131
		bottleNum 3
		walkToX 81
		walkToY 131
		stoopX 63
		stoopY 135
	)
)

(instance brat4 of Brat
	(properties
		x 19
		y 107
		noun 9
		onMeCheck $0800
		approachX 62
		approachY 116
		bottleNum 4
		walkToX 62
		walkToY 116
		stoopX 43
		stoopY 119
	)
)

(instance egoEnters of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (== prevRoomNum 490)
					(ego loop: 2 posn: 139 100 setMotion: MoveTo 139 107 self)
				else
					(ego
						setLoop: 6
						posn: 51 245
						setMotion: PolyPath 108 185 self
					)
				)
			)
			(1
				(if (== prevRoomNum 490)
					(gates cycleSpeed: 4 setCycle: Beg self)
				else
					(self cue:)
				)
			)
			(2
				(if (== prevRoomNum 490)
					(soundFx2 number: 907 setLoop: 1 play:)
					(gates addToPic:)
				)
				(theGame handsOn:)
				(ego reset:)
				(self dispose:)
			)
		)
	)
)

(instance egoExits of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo (- (ego x?) 55) 240 self)
			)
			(1
				(theGame handsOn:)
				(curRoom newRoom: 470)
			)
		)
	)
)

(instance thruGate of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 160 106 self)
			)
			(1
				(ego setHeading: 335)
				(= ticks 8)
			)
			(2
				(ego
					normal: 0
					view: 481
					cel: 0
					setLoop: 1
					posn: 142 107
					cycleSpeed: 8
					setCycle: CT 1 1 self
				)
				(UnLoad 128 900)
			)
			(3
				(gates dispose:)
				(curRoom drawPic: 480 (if global169 15 else 100))
				(if (== ((inventory at: 49) owner?) curRoomNum)
					(rotTomato addToPic:)
				)
				(soundFx2 number: 906 setLoop: 1 play:)
				(ego setCycle: End self)
				(gates signal: 26624 cycleSpeed: 3 init: setCycle: End)
			)
			(4 (curRoom newRoom: 490))
		)
	)
)

(instance shyFlowers of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: 0)
				(= cycles 10)
			)
			(1
				(soundFx number: 484 setLoop: 1 play: self)
				(flower1 view: 4852 cel: 2 setCycle: CT 6 1)
				(flower2 view: 4852 cel: 2 setCycle: CT 6 1)
				(flower3 view: 4852 cel: 2 setCycle: CT 6 1)
				(flower4 view: 4852 cel: 2 setCycle: CT 6 1)
			)
			(2
				(Face ego snap)
				(snap setCycle: Fwd)
				(soundFx2 number: 482 setLoop: 2 play: self)
			)
			(3
				(snap setCycle: 0 cel: 0 stopUpd:)
				(= cycles 4)
			)
			(4
				(if (== ((ScriptID 40 0) grabAtHidingHole?) 1)
					((ScriptID 40 0) grabAtHidingHole: 0)
					(messager say: 21 5 9 1 self)
				else
					(messager say: 5 3 0 1 self)
				)
			)
			(5
				(ego setMotion: PolyPath 197 116 self)
				(flower1 view: 4851 stopUpd:)
				(flower2 view: 4851 stopUpd:)
				(flower3 view: 4851 stopUpd:)
				(flower4 view: 4851 stopUpd:)
				(UnLoad 128 4852)
				(snap setCycle: Beg)
			)
			(6
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance hanging of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: 0)
				(= cycles 2)
			)
			(1 (proc483_4 3))
		)
	)
)

(instance stepOnSnaps of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: 0)
				(Face ego snap)
				(= cycles 10)
			)
			(1
				(soundFx2 number: 482 setLoop: 1 play: self)
				(snap setCycle: Fwd)
			)
			(2
				(snap setCycle: 0 cel: 0 stopUpd:)
				(= cycles 4)
			)
			(3
				(messager say: 11 3 0 1 self)
			)
			(4
				(ego
					setLoop: (ego cel?)
					setCycle: Rev
					setMotion: PolyPath (- (ego x?) 5) (ego y?) self
				)
			)
			(5
				(ego setCycle: Walk setLoop: -1)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance coverThatButtScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: 0)
				(soundFx2 number: 482 setLoop: 1 play: self)
				(snap setCycle: Fwd)
				(= ticks 12)
			)
			(1
				(ego
					normal: 0
					setSpeed: 6
					view: 483
					loop: 0
					cel: 0
					posn: (- (ego x?) 13) (+ (ego y?) 2)
					setCycle: End self
				)
			)
			(2 (= cycles 2))
			(3
				(ego reset: 0 posn: (- (ego x?) 13) (- (ego y?) 6))
				(= cycles 2)
			)
			(4
				(snap setCycle: 0 cel: 0 stopUpd:)
				(= cycles 4)
			)
			(5
				(messager say: 11 3 0 1 self)
			)
			(6
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance talkToVines of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if
					(or
						(> (ego distanceTo: register) 130)
						(> (ego y?) 115)
					)
					(ego setMotion: PolyPath 173 109 self)
				else
					(self cue:)
				)
			)
			(1
				(Face ego register)
				(= cycles 8)
			)
			(2
				(switch register
					(chokers
						(if ((ScriptID 40 0) vineTalk?)
							(messager say: 13 2 26 1 self)
						else
							((ScriptID 40 0) vineTalk: 1)
							(myConv
								add: -1 13 2 25 1
								add: -1 13 2 25 2
								add: -1 13 2 25 3
								add: -1 13 2 25 4
								add: -1 13 2 25 5
								add: -1 13 2 25 6
								add: -1 13 2 25 7
								add: -1 13 2 25 8
								init: self
							)
						)
					)
					(else 
						(if ((ScriptID 40 0) grapeTalk?)
							(messager say: 12 2 24 1 self)
						else
							(myConv
								add: -1 12 2 23 1
								add: -1 12 2 23 2
								add: -1 12 2 23 3
								add: -1 12 2 23 4
								add: -1 12 2 23 5
								add: -1 12 2 23 6
								add: -1 12 2 23 7
								add: -1 12 2 23 8
								init: self
							)
						)
					)
				)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance snappy of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (== register 5)
					(messager say: 11 5 0 1 self)
				else
					(self cue:)
				)
			)
			(1
				(ego setMotion: PolyPath 220 130 self)
			)
			(2
				(ego setHeading: 90)
				(= cycles 6)
			)
			(3
				(if (== register 5)
					(self cue:)
				else
					(messager say: 11 0 0 1 self)
				)
			)
			(4
				(soundFx2 number: 482 setLoop: 1 play: self)
				(snap setCycle: Fwd)
			)
			(5
				(snap setCycle: 0 cel: 0 stopUpd:)
				(= cycles 4)
			)
			(6
				(if (== register 5)
					(messager say: 11 5 0 2 self)
				else
					(messager say: 11 0 0 2 self)
				)
			)
			(7
				(ego
					setLoop: (ego cel?)
					setCycle: Rev
					setMotion: PolyPath (- (ego x?) 15) (ego y?) self
				)
			)
			(8
				(ego setCycle: Walk setLoop: -1)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance danceMusic of Sound
	(properties
		number 486
	)
)

(instance pauseForMusic of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 5))
			(1
				(theMusic number: 480 setLoop: -1 play:)
				(self dispose:)
			)
		)
	)
)

(instance fluteVerb of Actions
	(properties)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(31
					(curRoom setScript: wallFlowerDance)
					(return 1)
				)
				(else  (return 0))
			)
		)
	)
)

(instance glint of Prop
	(properties
		x 303
		y 63
		view 902
		signal $6800
		cycleSpeed 1
	)
	
	(method (cue)
		(super cue:)
		(switch (++ local2)
			(1
				(if (not (cast contains: myTeaCup))
					(self dispose:)
				else
					(self
						show:
						cel: 0
						setLoop: (Random 0 1)
						setCycle: End self
					)
				)
			)
			(2
				(self hide:)
				(= local2 0)
				(glintTimer setReal: self (Random 3 6))
			)
		)
	)
)

(instance glintTimer of Timer
	(properties)
)

(instance roomTimer of Timer
	(properties)
)

(instance wallFlowerDance of Script
	(properties)
	
	(method (init)
		(super init: &rest)
		(= local4 0)
		(if (not (Btst 117))
			(Bset 117)
			(theGame givePoints: 2)
		)
		(walkHandler addToFront: self)
		(mouseDownHandler addToFront: self)
		(keyDownHandler addToFront: self)
	)
	
	(method (doit)
		(cond 
			((== (ego edgeHit?) 3) (curRoom setScript: egoExits))
			((and register (== state 10)) (self cue:))
			(
				(and
					(> state 6)
					(< state 11)
					(== (danceMusic prevSignal?) -1)
				)
				(danceMusic prevSignal: 0)
				(= state 10)
				(theGame handsOn:)
				(self cue:)
			)
			((== (danceMusic prevSignal?) 20)
				(self cue:)
				(danceMusic prevSignal: 0)
				(= local1 7)
				(flower1 cycleSpeed: (- (flower1 cycleSpeed?) 1))
				(flower2 cycleSpeed: (- (flower2 cycleSpeed?) 1))
				(flower3 cycleSpeed: (- (flower3 cycleSpeed?) 1))
				(flower4 cycleSpeed: (- (flower4 cycleSpeed?) 1))
				(snap cycleSpeed: (- (snap cycleSpeed?) 1))
			)
			(
				(and
					(== (danceMusic prevSignal?) 10)
					(<= state 11)
					(>= state 6)
				)
				(danceMusic prevSignal: 0)
				(snap setCycle: Osc local1 snap)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(walkHandler delete: self)
		(mouseDownHandler delete: self)
		(keyDownHandler delete: self)
		(if local3 (proc481_7) (myBottle setCycle: RandCycle))
		(theMusic number: 480 setLoop: -1 play:)
		(danceMusic prevSignal: 0)
		(curRoom cue: 0)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= local1 9)
				(ego
					ignoreActors: 1
					illegalBits: 0
					setMotion: PolyPath 190 110 self
				)
			)
			(1
				(if ((ScriptID 40 0) bottleSucker?)
					(proc481_6)
					(myBottle setCycle: 0)
					(= local3 1)
				)
				(ego setHeading: 45)
				(= cycles 4)
			)
			(2
				(messager say: 5 31 0 1 self)
			)
			(3
				(messager say: 5 31 0 2 self)
			)
			(4
				(= register 0)
				(if
					(and
						(cast contains: hiw)
						(Btst 159)
						(!= (hiw x?) 283)
						(!= (hiw y?) 46)
					)
					(hiw setLoop: 1 setCycle: Walk setMotion: MoveTo 238 70)
				)
				((ScriptID 40 0) flowerDance: 1)
				(flower1 view: 4852 cel: 2 setCycle: Fwd)
				(flower2 view: 4852 cel: 2 setCycle: Fwd)
				(flower3 view: 4852 cel: 6 setCycle: Fwd)
				(flower4 view: 4852 cel: 6 setCycle: Fwd)
				(if
					(and
						(== ((inventory at: 18) owner?) curRoomNum)
						(Btst 159)
					)
					(soundFx2 number: 483 setLoop: 1 play:)
					(hiw setLoop: 5 setCycle: Walk setMotion: MoveTo 259 49)
				)
				(= ticks 6)
			)
			(5
				(if (== (hiw loop?) 5)
					(soundFx2 stop:)
					(hiw setLoop: 1)
				)
				(ego
					view: 920
					setLoop: 0
					cel: 0
					posn: (ego x?) (+ (ego y?) 2)
					normal: 0
					cycleSpeed: 6
				)
				(= ticks 6)
			)
			(6
				(theMusic stop:)
				(danceMusic number: 486 setLoop: 1 flags: 0 play:)
				(ego setCycle: Fwd)
			)
			(7 (ego setCycle: End self))
			(8
				(ego reset: 0 posn: (ego x?) (- (ego y?) 2))
				(= cycles 6)
			)
			(9
				(danceMusic flags: 1)
				(if (& msgType $0002)
					(messager say: 5 31 0 3 self)
				else
					(Print
						font: 4
						addText: 5 31 0 3
						posn: 10 6
						width: 289
						init:
					)
					(= ticks 12)
				)
			)
			(10 (theGame handsOn:))
			(11
				(if modelessDialog (modelessDialog dispose:))
				(danceMusic fade:)
				(= seconds 3)
			)
			(12
				((ScriptID 40 0) flowerDance: 0)
				(if
				(and (cast contains: hiw) (Btst 159) (not local4))
					(hiw setLoop: 1 setCycle: Walk setMotion: MoveTo 238 70)
				)
				(self dispose:)
				(flower1 view: 4851 stopUpd:)
				(flower2 view: 4851 stopUpd:)
				(flower3 view: 4851 stopUpd:)
				(flower4 view: 4851 stopUpd:)
				(snap cel: 0 stopUpd:)
			)
		)
	)
	
	(method (handleEvent event)
		(if (OneOf (event type?) 1 2 4 256)
			(cond 
				((== state 9)
					(if modelessDialog (modelessDialog dispose:))
					(self cue:)
					(event localize:)
					(event claimed: 1)
				)
				(
					(and
						(== state 10)
						(User controls?)
						(& (event type?) evMOVE)
					)
					(event claimed: 1)
					(self cue:)
				)
				(else (event claimed: 0))
			)
		)
		(event claimed?)
	)
)

(instance walkToHoleScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (and (!= (hiw x?) 238) (!= (hiw y?) 70))
					(hiw
						setLoop: 5
						setCycle: Walk
						setMotion: MoveTo 238 70 hiw
					)
				)
				(ego setMotion: PolyPath 242 93 self)
			)
			(1 (self dispose:))
		)
	)
)

(instance talkToSnaps of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 198 130 self)
			)
			(1
				(ego setHeading: 90)
				(= cycles 8)
			)
			(2
				(messager say: 11 2 0 1 self)
			)
			(3
				(soundFx2 number: 482 setLoop: 1 play: self)
				(snap setCycle: Fwd)
			)
			(4
				(snap setCycle: 0 cel: 0 stopUpd:)
				(= cycles 3)
			)
			(5
				(messager say: 11 2 0 2 self)
			)
			(6
				(theGame handsOn:)
				(snap setCycle: 0 cel: 0 stopUpd:)
				(self dispose:)
			)
		)
	)
)
