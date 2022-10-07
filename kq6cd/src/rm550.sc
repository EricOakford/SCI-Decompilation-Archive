;;; Sierra Script 1.0 - (do not remove this comment)
(script# 550)
(include sci.sh)
(use Main)
(use KQ6Room)
(use Kq6Procs)
(use Conv)
(use Scaler)
(use Osc)
(use PolyPath)
(use Polygon)
(use Feature)
(use StopWalk)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm550 0
)

(local
	local0 =  1
	[local1 2]
	local3 =  1
	local4
	local5
	theMessager
	narratorX
	narratorTalkWidth
	local9
)
(procedure (localproc_009c param1)
	(if param1
		(= narratorX (narrator x?))
		(= narratorTalkWidth (narrator talkWidth?))
		(narrator x: 20 talkWidth: 100)
	else
		(narrator x: narratorX talkWidth: narratorTalkWidth)
	)
)

(procedure (localproc_00d1 param1)
	(if param1
		(= narratorX (narrator x?))
		(= narratorTalkWidth (narrator talkWidth?))
		(narrator x: 200 talkWidth: 100)
	else
		(narrator x: narratorX talkWidth: narratorTalkWidth)
	)
)

(procedure (localproc_0107)
	(return
		(cond 
			(
			(and (> (ego heading?) 45) (< (ego heading?) 135)) 0)
			(
			(and (> (ego heading?) 134) (< (ego heading?) 225)) 2)
			(
			(and (> (ego heading?) 224) (< (ego heading?) 315)) 1)
			(
			(or (> (ego heading?) 314) (< (ego heading?) 45)) 3)
		)
	)
)

(instance rm550Messager of Kq6Messager
	(properties)
	
	(method (findTalker param1 &tmp temp0)
		(if
		(= temp0
			(switch param1
				(59 narrator)
				(60 narrator)
			))
			(return)
		else
			(super findTalker: param1)
		)
	)
)

(instance oceanSound of Sound
	(properties)
)

(instance rm550 of KQ6Room
	(properties
		noun 3
		picture 550
		horizon 0
		west 560
	)
	
	(method (init)
		(super init: &rest)
		(theGame handsOff:)
		(= theMessager messager)
		(= messager rm550Messager)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						319
						106
						259
						107
						224
						84
						222
						92
						189
						86
						170
						95
						168
						89
						171
						83
						133
						87
						130
						83
						143
						78
						165
						68
						155
						63
						142
						60
						124
						48
						140
						0
						319
						0
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 260 127 237 129 217 132 186 128 181 119 218 112 233 101 255 121
					yourself:
				)
				((Polygon new:)
					type: 2
					init:
						152
						112
						140
						112
						139
						120
						118
						120
						108
						118
						100
						111
						85
						110
						86
						106
						115
						101
						129
						103
						153
						109
					yourself:
				)
				((Polygon new:)
					type: 2
					init:
						93
						76
						81
						70
						65
						71
						57
						67
						30
						68
						20
						58
						0
						58
						0
						0
						93
						0
						110
						51
						132
						59
						143
						67
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 132 144 164 170 119 189 0 189 0 132 50 126 75 129 96 135
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 319 165 291 165 282 169 247 168 229 162 243 158 294 149 319 153
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 240 189 136 189 166 185 206 185
					yourself:
				)
		)
		(water1 init: setCycle: Forward)
		(water2 init: setCycle: Forward)
		(water3 init: setCycle: Forward)
		(water4 init: setCycle: Forward)
		(oceanSound number: 915 loop: -1 flags: 1 play:)
		(theGlobalSound number: 550 flags: 1 loop: -1 play:)
		(ocean1 init:)
		(ocean2 init:)
		(trees1 init:)
		(trees2 init:)
		(rocks init:)
		(nePath init:)
		(nwPath init:)
		(ego
			actions: egoDoVerb
			setScale: Scaler 100 70 190 53
			setPri: -1
		)
		(if (and (Btst 25) (not (Btst 14)))
			(druid1 init: setScale: -1 ego)
			(druid2 init: setScale: -1 ego setScript: waitForCapture)
			(= local5 1)
		)
		(if (OneOf prevRoomNum 560 580)
			(self setScript: egoEnters)
		)
	)
	
	(method (doit &tmp temp0 temp1)
		(= temp0 (ego onControl: 1))
		(cond 
			(script 0)
			((& temp0 $2000) (theGame handsOff:) (curRoom setScript: walkNorthScript))
			((& temp0 $0010)
				(if (!= (ego view?) 308)
					(= temp1 (localproc_0107))
					(ego
						ignoreActors: 1
						illegalBits: 0
						view: 308
						setPri: 15
						setLoop: temp1
						setLoop: -1
					)
					(= local0 0)
				)
			)
			((& temp0 $0020)
				(if (not local9)
					(= local9 1)
					(soundFx number: 922 loop: -1 flags: 1 play:)
				)
				(if local3
					(= local0 0)
					(= local3 0)
					(messager say: 4 3 6)
					(ego setMotion: 0)
				)
			)
			((& temp0 $0040)
				(if (!= (ego view?) 3081)
					(= temp1 (localproc_0107))
					(ego
						ignoreActors: 1
						illegalBits: 0
						view: 3081
						setPri: 15
						setLoop: temp1
						setLoop: -1
					)
				)
			)
			((& temp0 $0100)
				(if (not local4)
					(ego setMotion: 0)
					(repeat
						(ego posn: (- (ego x?) 1) (- (ego y?) 1))
						(breakif (& (ego onControl: 1) $0080))
					)
					(messager say: 4 3 6)
					(= local0 0)
					(= local4 1)
				else
					(theGame handsOff:)
					(self setScript: wateryDeathScr)
				)
			)
			(
				(and
					(not (& temp0 $01f0))
					(== (ego view?) 308)
					(not local0)
				)
				(soundFx fade:)
				(= local9 0)
				(ego reset: 3)
				(= local4 0)
				(= local0 1)
			)
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(= messager theMessager)
		(super dispose:)
		(DisposeScript 939)
	)
	
	(method (newRoom n)
		(if (not (OneOf n 560 580))
			(theGlobalSound stop: number: 0)
		)
		(super newRoom: n)
	)
)

(instance myConv of Conversation
	(properties)
)

(instance trees2 of Feature
	(properties
		noun 5
		onMeCheck $1e00
	)
)

(instance ocean1 of Feature
	(properties
		noun 4
		onMeCheck $00f0
	)
)

(instance ocean2 of Feature
	(properties
		noun 4
		onMeCheck $0100
	)
)

(instance trees1 of Feature
	(properties
		noun 5
		onMeCheck $0002
	)
)

(instance rocks of Feature
	(properties
		noun 2
		onMeCheck $000c
	)
	
	(method (doVerb theVerb)
		(cond 
			((== theVerb 1) (messager say: noun theVerb 10 0 0 0))
			((OneOf theVerb 2 5) (messager say: noun theVerb 0 0 0 0))
			(else (messager say: noun 0 0 0 0 0))
		)
	)
)

(instance nePath of Feature
	(properties
		noun 7
		onMeCheck $2000
	)
)

(instance nwPath of Feature
	(properties
		noun 8
		onMeCheck $4000
	)
)

(instance druid1 of Actor
	(properties
		x 68
		y 105
		view 553
		signal $4000
	)
	
	(method (init)
		(super init: &rest)
		(self setCycle: StopWalk -1)
	)
)

(instance druid2 of Actor
	(properties
		x 131
		y 82
		view 553
		loop 2
		signal $4000
	)
	
	(method (init)
		(super init: &rest)
		(self setCycle: StopWalk -1)
	)
)

(instance water1 of Prop
	(properties
		x 217
		y 136
		noun 4
		view 550
		signal $4000
		cycleSpeed 10
		detailLevel 3
	)
)

(instance water2 of Prop
	(properties
		x 278
		y 172
		noun 4
		view 550
		loop 1
		signal $4000
		cycleSpeed 10
		detailLevel 3
	)
)

(instance water3 of Prop
	(properties
		x 291
		y 89
		noun 4
		view 550
		loop 2
		signal $4000
		cycleSpeed 10
		detailLevel 3
	)
)

(instance water4 of Prop
	(properties
		x 279
		y 110
		noun 4
		view 550
		loop 3
		signal $4000
		cycleSpeed 10
		detailLevel 3
	)
)

(instance walkNorthScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 138 58 self)
			)
			(1
				(ego setMotion: PolyPath 122 47 self)
			)
			(2 (ego setHeading: 45 self))
			(3 (curRoom newRoom: 580))
		)
	)
)

(instance egoEnters of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(switch prevRoomNum
					(580
						(ego init: posn: 126 55 setMotion: PolyPath 127 87 self)
					)
					(else 
						(ego init: posn: 1 95 setMotion: PolyPath 40 95 self)
					)
				)
			)
			(1
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance waitForCapture of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (and (not state) (not (curRoom script?)))
			(theGame handsOff:)
			(curRoom setScript: captured)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 0)
		)
	)
)

(instance captured of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (ego setHeading: 315 self))
			(1
				(localproc_009c 1)
				(messager say: 1 0 2 1 self)
				(localproc_009c 0)
			)
			(2
				(localproc_00d1 1)
				(messager say: 1 0 2 2 self)
				(localproc_00d1 0)
			)
			(3
				(localproc_009c 1)
				(messager say: 1 0 2 3 self)
				(localproc_009c 0)
			)
			(4
				(druid1
					setMotion: MoveTo (- (ego x?) 25) (+ (ego y?) 3) self
					setCycle: Walk
				)
				(druid2
					setMotion: MoveTo (+ (ego x?) 24) (+ (ego y?) 4) self
					setCycle: Walk
				)
			)
			(5
				(druid1 dispose:)
				(druid2 dispose:)
				(ego
					normal: 0
					view: 554
					cycleSpeed: 10
					cel: 0
					setLoop: 0
					setCycle: EndLoop self
				)
			)
			(6 (messager say: 1 0 2 4 self))
			(7
				(localproc_009c 1)
				(messager say: 1 0 2 5 self)
				(localproc_009c 0)
			)
			(8
				(ego
					view: 5806
					setLoop: 0
					cycleSpeed: 6
					setCycle: Walk
					setMotion: PolyPath 126 85 self
				)
			)
			(9
				(ego setMotion: PolyPath 150 66 self)
			)
			(10 (curRoom newRoom: 580))
		)
	)
)

(instance wateryDeathScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 4 3 9 0 self)
			)
			(1
				(curRoom walkOffEdge: 1)
				(soundFx stop: number: 921 loop: 1 play:)
				(ego
					normal: 0
					view: 269
					setLoop: 1
					cel: 0
					cycleSpeed: 6
					setCycle: Oscillate
				)
				(if (> (ego y?) 137)
					(ego setMotion: PolyPath 267 220 self)
				else
					(ego setMotion: PolyPath 340 118 self)
				)
			)
			(2 (curRoom newRoom: 135))
		)
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
