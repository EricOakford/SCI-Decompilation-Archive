;;; Sierra Script 1.0 - (do not remove this comment)
(script# 520)
(include sci.sh)
(use Main)
(use NewRoomCue)
(use Kq6Procs)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm520 0
)

(local
	local0 =  1
	local1
	local2
)
(instance rm520 of KQ6Room
	(properties
		noun 5
		picture 520
		horizon 70
		north 510
		south 500
	)
	
	(method (init &tmp temp0)
		(Load rsVIEW 308)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						223
						166
						217
						175
						139
						173
						103
						169
						109
						163
						164
						153
						166
						134
						166
						103
						155
						94
						130
						66
						129
						65
						319
						13
						319
						189
						285
						189
						269
						165
					yourself:
				)
				((Polygon new:)
					type: 2
					init:
						0
						189
						0
						8
						122
						65
						98
						80
						129
						90
						114
						102
						122
						112
						97
						141
						79
						160
						47
						165
						31
						173
						45
						183
						71
						189
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 199 189 168 189 176 185 194 186
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 158 186 144 186 141 183 145 180 157 180 164 183
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 120 181 104 181 99 176 108 174 119 174 127 177
					yourself:
				)
		)
		(super init: &rest)
		(if (== prevRoomNum north)
			(ego init: reset: 2 posn: 130 90)
		else
			(ego init: reset: 3 posn: 105 187)
		)
		(if (not (Btst 13))
			(boilingPond
				init:
				signal: (| (boilingPond signal?) $1000)
				setCycle: Fwd
				setPri: 9
				ignoreActors: 1
				cycleSpeed: 12
			)
			(boilFx play:)
		else
			(boilingPond
				init:
				view: 525
				setLoop: 0
				setPri: 9
				posn: 141 125
				cycleSpeed: 12
				ignoreActors: 1
				setCycle: Fwd
			)
		)
		(finishedPond init:)
		(mushrooms init:)
		(frontPath init:)
		(backPath init:)
		(banks init:)
		(rocks init:)
		(trees init:)
		(if
			(and
				(<= temp0 (Random 1 100))
				(<= (Random 1 100) 500)
			)
			(bunny init: setScript: bunnyScript)
		else
			(squirrel init:)
		)
		(if (== ((inventory at: 19) owner?) curRoomNum)
			(theHuntersLamp init:)
		)
		(ego setScale: Scaler 100 50 184 72)
		((ego scaler?) doit:)
		(theGame handsOn:)
	)
	
	(method (doit &tmp temp0)
		(if (and (GameIsRestarting) (not (Btst 13)))
			(boilFx play:)
		)
		(super doit: &rest)
		(= temp0 (ego onControl: 1))
		(cond 
			(script 0)
			((& temp0 $4000) (curRoom newRoom: north))
			((and (& temp0 $0002) local0) (theGame handsOff:) (self setScript: bravePond))
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (Btst 13)
					(messager say: noun theVerb 4)
					1
				else
					(messager say: noun theVerb 3)
					1
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance boilFx of Sound
	(properties
		flags $0001
		number 520
		loop -1
	)
)

(instance boilDeath of Sound
	(properties
		flags $0001
		number 521
	)
)

(instance theHuntersLamp of Prop
	(properties
		x 111
		y 65
		noun 4
		view 520
		loop 3
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (messager say: noun theVerb))
			(5
				(if (Btst 13)
					(theGame handsOff:)
					(= local1 1)
					(ego setScript: getLamp)
				else
					(messager say: noun theVerb 3)
				)
			)
			(2
				(super doVerb: theVerb &rest)
			)
			(else 
				(if (Btst 13)
					(messager say: noun 0 4)
				else
					(messager say: noun 0 3)
				)
			)
		)
	)
)

(instance splash of Prop
	(properties
		view 520
		loop 4
	)
	
	(method (init)
		(self x: (+ (ego x?) 16) y: (- (ego y?) 32))
		(super init:)
	)
)

(instance finishedPond of Feature
	(properties
		noun 3
		onMeCheck $0002
	)
	
	(method (doVerb theVerb)
		(cond 
			((== theVerb 5)
				(theGame handsOff:)
				(= local2 1)
				(ego setScript: feelPond)
			)
			((OneOf theVerb 1 2)
				(if (Btst 13)
					(messager say: noun theVerb 4)
				else
					(messager say: noun theVerb 3)
				)
			)
			((OneOf theVerb 28 25 43 94 44 34) (messager say: noun theVerb 0))
			((== theVerb 54)
				(if (Btst 13)
					(messager say: noun theVerb 4)
				else
					(messager say: noun theVerb 3)
				)
			)
			((OneOf theVerb 52 53) (ego setScript: throwLettuceInPond 0 theVerb))
			((Btst 13) (messager say: noun 0 4))
			(else (messager say: noun 0 3))
		)
	)
)

(instance boilingPond of Prop
	(properties
		x 85
		y 150
		noun 3
		view 524
	)
	
	(method (doVerb theVerb)
		(finishedPond doVerb: theVerb &rest)
	)
)

(instance bunny of Actor
	(properties
		x 143
		y 176
		noun 13
		view 503
		loop 2
	)
	
	(method (init)
		(self ignoreActors: 1 setLoop: (self loop?))
		(super init: &rest)
	)
)

(instance feelPond of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 99 148 self)
			)
			(1
				(ego
					view: 521
					normal: 0
					cycleSpeed: 10
					setLoop: 3
					posn: 110 154
					cel: 0
					setCycle: End self
				)
			)
			(2
				(ego reset: posn: 99 148)
				(= cycles 2)
			)
			(3
				(if (Btst 13)
					(messager say: 3 5 4 0 self)
				else
					(messager say: 3 5 3 0 self)
				)
			)
			(4
				(= local2 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance getLamp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 119 99 self)
			)
			(1
				(ego setHeading: 315)
				(theHuntersLamp dispose:)
				(ego
					normal: 0
					view: 523
					setLoop: 0
					cel: 0
					cycleSpeed: 10
					setCycle: End self
				)
			)
			(2 (messager say: 4 5 4 1 self))
			(3
				(theGame handsOn:)
				(ego reset: 1)
				(ego get: 19)
				(theGame givePoints: 1)
				(= local1 0)
				(self dispose:)
			)
		)
	)
)

(instance throwLettuceInPond of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 113 159 self)
			)
			(1
				(ego setHeading: 0)
				(= ticks 6)
			)
			(2
				(if (Btst 13)
					(messager say: 3 52 4 1 self)
				else
					(messager say: 3 52 3 1 self)
				)
			)
			(3
				(ego
					view: 521
					normal: 0
					setLoop: 1
					cel: 0
					cycleSpeed: 8
					setCycle: End self
				)
			)
			(4
				(splash init: setPri: 10 setCycle: CT 3 1 self)
			)
			(5
				(pondWalk number: 923 loop: 1 play:)
				(splash setCycle: End self)
			)
			(6
				(splash dispose:)
				(ego put: 21 curRoomNum reset: 3)
				((ScriptID 0 7) dispose:)
				(= ticks 12)
			)
			(7
				(theGame handsOn:)
				(if (Btst 13)
					(messager say: 3 52 4 2 self)
					(self dispose:)
				else
					(Bset 13)
					(theGame givePoints: 4)
					(boilingPond
						setLoop: 1
						cel: 0
						posn: 92 150
						cycleSpeed: 24
						setCycle: End self
					)
					(boilFx stop:)
				)
			)
			(8
				(messager say: 3 52 3 2 self)
			)
			(9
				(boilingPond
					view: 525
					setLoop: 0
					posn: 141 125
					setCycle: Fwd
				)
				(self dispose:)
			)
		)
	)
)

(instance pondWalk of Sound
	(properties
		number 920
		loop -1
	)
)

(instance bravePond of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cond 
					((not (Btst 13)) (= cycles 1))
					((not (Btst 83)) (messager say: 3 3 5 1 self))
					(else (= cycles 1))
				)
			)
			(1
				(if (Btst 13)
					(ego
						illegalBits: 0
						view: 521
						setPri: 14
						setSpeed: 6
						ignoreActors: 1
					)
					(if (< (ego y?) 110)
						(ego setLoop: 5 setMotion: PolyPath 110 143 self)
					else
						(ego setLoop: 4 setMotion: PolyPath 137 107 self)
					)
					(pondWalk number: 920 loop: -1 play:)
				else
					(self setScript: egoBoilsScript)
				)
			)
			(2
				(pondWalk stop:)
				(if (== (ego loop?) 5)
					(ego reset: setMotion: PolyPath 94 154 self)
				else
					(ego reset: setMotion: PolyPath 139 102 self)
				)
			)
			(3
				(if (Btst 83)
					(= cycles 1)
				else
					(messager say: 3 3 5 2 self)
				)
			)
			(4
				(ego reset:)
				(if (not (Btst 83))
					(Bset 83)
					(messager say: 3 3 5 3 self)
				else
					(= cycles 1)
				)
			)
			(5
				(cond 
					(local1 (theGame handsOff:) (ego setScript: getLamp))
					(local2 (theGame handsOff:) (ego setScript: feelPond))
					(else (theGame handsOn:))
				)
				(self dispose:)
			)
		)
	)
)

(instance egoBoilsScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (messager say: 3 3 3 1 self))
			(1
				(ego
					view: 521
					normal: 0
					setLoop: 4
					cycleSpeed: 12
					setMotion: PolyPath (+ (ego x?) 2) (- (ego y?) 3) self
				)
			)
			(2
				(boilDeath play:)
				(ego setLoop: 0 cel: 0 cycleSpeed: 24 setCycle: End self)
			)
			(3
				(ego hide:)
				(messager say: 3 3 3 2 self)
			)
			(4 (= local0 0) (EgoDead 5))
		)
	)
)

(instance mushrooms of Feature
	(properties
		noun 8
		onMeCheck $0004
	)
)

(instance frontPath of Feature
	(properties
		noun 6
		onMeCheck $0010
	)
)

(instance backPath of Feature
	(properties
		noun 7
		onMeCheck $0008
	)
)

(instance banks of Feature
	(properties
		noun 10
		onMeCheck $0020
	)
)

(instance rocks of Feature
	(properties
		noun 2
		onMeCheck $0040
	)
	
	(method (doVerb theVerb)
		(cond 
			((== theVerb 1) (messager say: noun theVerb 2 0 0 0))
			((OneOf theVerb 1 2 5) (messager say: noun theVerb 0 0 0 0))
			(else (messager say: noun 0 0 0 0 0))
		)
	)
)

(instance trees of Feature
	(properties
		noun 9
		onMeCheck $0080
	)
)

(instance squirrel of Prop
	(properties
		x 204
		y 179
		view 520
		loop 5
	)
	
	(method (init)
		(super init: &rest)
		(if (Btst 13)
			(self setScript: squirrelScript)
		else
			(self stopUpd:)
		)
	)
)

(instance squirrelScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 10))
			(1 (client setCycle: End self))
			(2
				(client dispose:)
				(self dispose:)
			)
		)
	)
)

(instance bunnyScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(bunny setCycle: Fwd setMotion: MoveTo 206 179 self)
			)
			(1
				(bunny setLoop: 3 setCycle: End self)
			)
			(2 (bunny setCycle: End self))
			(3
				(bunny
					setLoop: 2
					setCycle: Fwd
					setMotion: MoveTo 244 207 self
				)
			)
			(4 (client dispose:))
		)
	)
)
