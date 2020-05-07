;;; Sierra Script 1.0 - (do not remove this comment)
(script# 680)
(include sci.sh)
(use Main)
(use NewRoomCue)
(use Kq6Procs)
(use Scaler)
(use RandCyc)
(use MoveCyc)
(use PolyPath)
(use Polygon)
(use Feature)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm680 0
)

(local
	theMessager
	narratorX
	narratorY
	narratorTalkWidth
	[local4 49] = [3 0 147 10 3 1 147 5 3 2 146 14 3 3 147 25 3 4 147 35 3 5 147 45 3 6 147 53 3 7 147 60 3 8 147 65 3 9 147 68 3 10 147 71 3 11 147 73 -32768]
)
(instance rm680Messager of Kq6Messager
	(properties)
	
	(method (findTalker param1 &tmp temp0)
		(if (= temp0
			(switch param1
				(78 narrator)
			))
			(return)
		else
			(super findTalker: param1)
		)
	)
)

(instance rm680 of KQ6Room
	(properties
		noun 3
		picture 680
		north 690
		south 670
	)
	
	(method (init)
		(theIconBar enable:)
		(theGame handsOff:)
		(self
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						149
						108
						145
						122
						144
						147
						131
						173
						113
						183
						95
						189
						0
						189
						0
						-10
						319
						-10
						319
						189
						199
						189
						196
						184
						168
						160
						158
						132
						155
						108
						158
						86
						152
						86
					yourself:
				)
		)
		(super init: &rest)
		(UnLoad 128 670)
		(UnLoad 128 671)
		(UnLoad 128 672)
		(UnLoad 128 690)
		(UnLoad 128 691)
		(UnLoad 128 692)
		(= theMessager messager)
		(= messager rm680Messager)
		(flame1 init: setCycle: RandCycle)
		(flame2 init: setCycle: RandCycle)
		(flame3 init: setCycle: RandCycle)
		(flame4 init: setCycle: RandCycle)
		(flame5 init: setCycle: RandCycle)
		(flame6 init: setCycle: RandCycle)
		(flame7 init: setCycle: RandCycle)
		(flame8 init: setCycle: RandCycle)
		(flame9 init: setCycle: RandCycle)
		(flame10 init: setCycle: RandCycle)
		(flame11 init: setCycle: RandCycle)
		(flame12 init: setCycle: RandCycle)
		(if (== prevRoomNum 670)
			(theGame handsOn:)
			(if (> howFast 1)
				(lordGhoul init: setScript: lordGhoulScript)
				(poolGhoulRight
					init:
					ignoreActors: 1
					setScript: pgrScript
				)
				(poolGhoulLeft init: ignoreActors: 1 setScript: pglScript)
				(rArm init:)
				(lArm init:)
			)
			(rGuard view: 683 init: setScale: Scaler 100 69 189 90)
			(lGuard view: 683 init: setScale: Scaler 100 69 189 90)
			(ego
				init:
				reset: 3
				setScale: Scaler 100 69 189 90
				posn: 153 185
			)
			(lord init:)
			(path init:)
			(sea init:)
			(theMusic number: 680 loop: -1 play:)
		else
			(ego
				init:
				posn: 125 82
				setScale: Scaler 100 54 189 92
				setPri: 4
				setLoop: -1
				loop: 4
			)
			(rGuard
				view: 6804
				init:
				setPri: 7
				setLoop: 1
				setScale: Scaler 100 69 189 90
				addToPic:
			)
			(lGuard
				view: 6804
				init:
				setPri: 7
				setLoop: 0
				setScale: Scaler 100 69 189 90
				addToPic:
			)
			(self setScript: wonDeadScript)
		)
		(cast eachElementDo: #checkDetail)
	)
	
	(method (doit)
		(cond 
			(script 0)
			((<= (ego y?) 168) (theGame handsOff:) (self setScript: takeHimAway))
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(= messager theMessager)
		(super dispose: &rest)
		(DisposeScript 942)
	)
	
	(method (newRoom n)
		(if (== n 670)
			(theGame handsOff:)
			(self setScript: dontGoAlex)
		else
			(super newRoom: n)
		)
	)
)

(instance sfx of Sound
	(properties)
)

(instance lord of Feature
	(properties
		noun 4
		onMeCheck $0002
	)
)

(instance path of Feature
	(properties
		noun 7
		onMeCheck $0008
	)
)

(instance sea of Feature
	(properties
		noun 5
		onMeCheck $0004
	)
)

(instance flame1 of Prop
	(properties
		x 68
		y 70
		noun 9
		view 680
		cel 1
		priority 7
		signal $4010
		detailLevel 1
	)
)

(instance flame2 of Prop
	(properties
		x 89
		y 124
		noun 9
		view 680
		priority 7
		signal $4010
		detailLevel 1
	)
)

(instance flame3 of Prop
	(properties
		x 226
		y 86
		noun 9
		view 680
		cel 2
		priority 7
		signal $4010
		detailLevel 1
	)
)

(instance flame4 of Prop
	(properties
		x 239
		y 48
		noun 9
		view 680
		cel 2
		priority 7
		signal $4010
		detailLevel 1
	)
)

(instance flame5 of Prop
	(properties
		x 132
		y 94
		noun 9
		view 680
		loop 1
		priority 7
		signal $4010
		detailLevel 1
	)
)

(instance flame6 of Prop
	(properties
		x 189
		y 99
		noun 9
		view 680
		loop 1
		cel 2
		priority 7
		signal $4010
		detailLevel 1
	)
)

(instance flame7 of Prop
	(properties
		x 212
		y 89
		noun 9
		view 680
		loop 1
		cel 2
		priority 7
		signal $4010
		detailLevel 1
	)
)

(instance flame8 of Prop
	(properties
		x 100
		y 91
		noun 9
		view 680
		loop 1
		cel 3
		priority 7
		signal $4010
		detailLevel 1
	)
)

(instance flame9 of Prop
	(properties
		x 208
		y 64
		noun 9
		view 680
		loop 2
		cel 1
		priority 3
		signal $4010
		detailLevel 1
	)
)

(instance flame10 of Prop
	(properties
		x 106
		y 70
		noun 9
		view 680
		loop 2
		cel 3
		priority 3
		signal $4010
		detailLevel 1
	)
)

(instance flame11 of Prop
	(properties
		x 123
		y 56
		noun 9
		view 680
		loop 2
		cel 3
		priority 3
		signal $4010
		detailLevel 1
	)
)

(instance flame12 of Prop
	(properties
		x 200
		y 49
		noun 9
		view 680
		loop 2
		priority 3
		signal $4010
		detailLevel 1
	)
)

(instance rArm of Prop
	(properties
		x 172
		y 59
		noun 4
		view 682
		priority 5
		signal $4010
		cycleSpeed 20
	)
)

(instance lArm of Actor
	(properties
		x 151
		y 58
		noun 4
		view 682
		loop 1
		priority 5
		signal $6010
		cycleSpeed 20
	)
)

(instance lordGhoul of Prop
	(properties
		x 156
		y 38
		noun 8
		view 6802
		priority 14
		signal $4010
		cycleSpeed 10
		detailLevel 1
	)
)

(instance poolGhoulRight of Actor
	(properties
		x 158
		y 65
		noun 8
		view 682
		loop 4
		priority 5
		signal $6010
		cycleSpeed 10
		detailLevel 1
	)
)

(instance poolGhoulLeft of Prop
	(properties
		x 158
		y 65
		noun 8
		view 682
		loop 3
		priority 5
		signal $6010
		cycleSpeed 10
		detailLevel 1
	)
)

(instance rGuard of Actor
	(properties
		x 196
		y 163
		noun 6
		loop 2
		signal $6000
	)
	
	(method (doVerb theVerb)
		(cond 
			((OneOf theVerb 5 1) (super doVerb: theVerb &rest))
			((== theVerb 2) (theGame handsOff:) (curRoom setScript: talkGuards))
			(else (theGame handsOff:) (curRoom setScript: giveGuards))
		)
	)
)

(instance lGuard of Actor
	(properties
		x 114
		y 163
		noun 6
		loop 3
		signal $6000
	)
	
	(method (doVerb theVerb)
		(cond 
			((OneOf theVerb 5 1) (super doVerb: theVerb &rest))
			((== theVerb 2) (theGame handsOff:) (curRoom setScript: talkGuards))
			(else (theGame handsOff:) (curRoom setScript: giveGuards))
		)
	)
)

(instance pgrScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 3 10)))
			(1
				(client show: setLoop: 4 cel: 0 setCycle: End self)
			)
			(2 (= cycles (Random 5 10)))
			(3 (rArm setCycle: End self))
			(4
				(rArm cel: 0)
				(client setLoop: 5 cel: 0 setCycle: End self)
			)
			(5 (client hide:) (self init:))
		)
	)
)

(instance pglScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 3 10)))
			(1
				(client show: setLoop: 3 cel: 0 setCycle: End self)
			)
			(2 (= cycles (Random 5 10)))
			(3 (lArm setCycle: End self))
			(4
				(lArm cel: 0)
				(client setLoop: 6 cel: 0 setCycle: End self)
			)
			(5 (client hide:) (self init:))
		)
	)
)

(instance lordGhoulScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 10 30)))
			(1
				(client show: setLoop: 0 cel: 0 setCycle: End self)
			)
			(2
				(client setLoop: 1 cel: 0 setCycle: End self)
			)
			(3 (client hide:) (self init:))
		)
	)
)

(instance dontGoAlex of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (messager say: 1 0 5 1 self))
			(1
				(ego setMotion: PolyPath (ego x?) (- (ego y?) 10) self)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance talkGuards of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (messager say: 6 2 0 1 self))
			(1 (messager say: 6 2 0 2 self))
			(2
				(self setScript: takeHimAway)
			)
		)
	)
)

(instance giveGuards of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (messager say: 6 0 0 1 self))
			(1 (messager say: 6 0 0 2 self))
			(2
				(self setScript: takeHimAway)
			)
		)
	)
)

(instance takeHimAway of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== client curRoom)
					(messager say: 3 3 4 0 self)
				else
					(= cycles 1)
				)
			)
			(1
				(ego setMotion: PolyPath 155 153 self)
			)
			(2
				(rGuard setCycle: End self)
				(lGuard setCycle: End self)
			)
			(3 0)
			(4
				(rGuard
					setLoop: 0
					setCycle: Walk
					setMotion: MoveTo (- (rGuard x?) 20) (- (rGuard y?) 20)
				)
				(lGuard
					setLoop: 0
					setCycle: Walk
					setMotion: MoveTo (+ (lGuard x?) 20) (- (lGuard y?) 20)
				)
				(ego setMotion: PolyPath (ego x?) (- (ego y?) 20) self)
			)
			(5 (curRoom newRoom: 690))
		)
	)
)

(instance wonDeadScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 1))
			(1
				(= narratorX (narrator x?))
				(= narratorY (narrator y?))
				(= narratorTalkWidth (narrator talkWidth?))
				(narrator x: 10 y: 10 talkWidth: 100)
				(lArm
					init:
					view: 684
					loop: 3
					cel: 0
					x: 147
					y: 10
					setPri: 6
					setScale: 0
					cycleSpeed: 10
					ignoreActors: 1
					ignoreHorizon: 1
					setCycle: MCyc @local4 self 1
				)
			)
			(2 (= cycles 5))
			(3 (messager say: 1 0 2 1 self))
			(4 (= cycles 5))
			(5
				(sfx number: 687 loop: 1 play:)
				(rArm
					init:
					view: 684
					setLoop: 5
					cel: 0
					posn: 161 56
					ignoreActors: 1
				)
				(lordGhoul
					init:
					view: 684
					setLoop: 2
					cel: 0
					setPri: 5
					posn: 147 80
					ignoreActors: 1
					setCycle: Fwd
				)
				(= seconds 1)
			)
			(6
				(lArm setLoop: 4 cel: 0 setCycle: End self)
			)
			(7
				(Bclr 91)
				(lordGhoul dispose:)
				(rArm dispose:)
				(= cycles 2)
			)
			(8 (messager say: 1 0 2 2 self))
			(9 (messager say: 1 0 2 3 self))
			(10
				(poolGhoulRight
					init:
					view: 6801
					setLoop: 0
					cel: 0
					setPri: 8
					posn: -33 68
					cycleSpeed: 10
					moveSpeed: 10
					setStep: 10 10
					ignoreActors: 1
					setCycle: Fwd
					setMotion: MoveTo 73 129 self
				)
			)
			(11
				(sfx number: 346 loop: 1 play:)
				(poolGhoulRight setMotion: MoveTo 124 166 self)
			)
			(12
				(poolGhoulRight view: 685 setLoop: 0 setCycle: End self)
				(UnLoad 128 6801)
			)
			(13
				(poolGhoulRight
					setLoop: 4
					cel: 0
					posn: (+ (poolGhoulRight x?) 34) (+ (poolGhoulRight y?) 7)
				)
				(= seconds 1)
			)
			(14
				(messager say: 1 0 2 4 self)
			)
			(15
				(messager say: 1 0 2 5 self)
			)
			(16
				(messager say: 1 0 2 6 self)
			)
			(17
				(messager say: 1 0 2 7 self)
			)
			(18
				(lArm
					view: 687
					setLoop: 0
					setCycle: Walk
					setScale: -1 ego
					setPri: (- (poolGhoulRight priority?) 1)
					setSpeed: (ego currentSpeed?)
					setStep: 5 4
					posn: (lArm x?) (+ (lArm y?) 20)
					setMotion:
						PolyPath
						(- (poolGhoulRight x?) 10)
						(- (poolGhoulRight y?) 10)
						self
				)
				(ego
					setPri: (- (poolGhoulRight priority?) 1)
					setMotion:
						PolyPath
						(- (poolGhoulRight x?) 20)
						(- (poolGhoulRight y?) 20)
				)
			)
			(19
				(poolGhoulRight setLoop: 2 setCycle: End self)
			)
			(20
				(poolGhoulRight setLoop: 3 cel: 0 setCycle: End self)
				(lArm dispose:)
			)
			(21
				(poolGhoulRight setLoop: 5 cel: 0)
				(ego
					setPri: 7
					ignoreActors: 1
					setMotion:
						MoveTo
						(- (poolGhoulRight x?) 11)
						(- (poolGhoulRight y?) 10)
						self
				)
			)
			(22
				(ego
					normal: 0
					view: 685
					setLoop: 1
					cel: 0
					setPri: (+ (poolGhoulRight priority?) 1)
					posn: (poolGhoulRight x?) (poolGhoulRight y?)
					setScale: 0
					setCycle: End self
				)
			)
			(23
				(poolGhoulRight dispose:)
				(narrator
					x: narratorX
					y: narratorY
					talkWidth: narratorTalkWidth
				)
				(theMusic number: 155 loop: -1 play:)
				(= seconds 3)
			)
			(24 (curRoom newRoom: 155))
		)
	)
)
