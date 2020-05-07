;;; Sierra Script 1.0 - (do not remove this comment)
(script# 500)
(include sci.sh)
(use Main)
(use NewRoomCue)
(use Kq6Procs)
(use Conv)
(use PolyPath)
(use Polygon)
(use Feature)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm500 0
)

(local
	local0
	local1 =  1
	local2 =  1
	local3
	local4
)
(instance myConv of Conversation
	(properties)
)

(instance partSong of Sound
	(properties)
)

(instance rm500 of KQ6Room
	(properties
		noun 3
		picture 500
		north 520
	)
	
	(method (init &tmp temp0)
		(Load rsVIEW 308)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init: 319 189 245 189 245 162 266 147 228 105 255 94 264 79 319 82
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 0 189 0 0 259 78 251 90 225 102 210 96 149 118 156 145 93 177 37 189
					yourself:
				)
		)
		(water1 setCycle: Fwd init:)
		(water2 setCycle: Fwd init:)
		(water3 setCycle: Fwd init:)
		(water4 setCycle: Fwd init:)
		(water5 setCycle: Fwd init:)
		(plant setCycle: Fwd init:)
		(theMusic number: 915 loop: -1 flags: 1 play:)
		(theGlobalSound number: 917 flags: 1 loop: -1 play:)
		(super init: &rest)
		(ocean init:)
		(trees init:)
		(pathway init:)
		(stump init:)
		(rocks init:)
		(branch init:)
		(if (and (Btst 61) (not (Btst 45)))
			(dangle
				init:
				setScript: dangleScript
				approachVerbs: 5 2 85
			)
			(soundFx2 number: 500 flags: 1 loop: -1 play:)
		)
		(if (== prevRoomNum north)
			(ego init: reset: 2 posn: 256 91)
			(theGame handsOn:)
		)
		(ego actions: egoDoVerb)
		(if
			(and
				(<= temp0 (Random 1 1000))
				(<= (Random 1 1000) 500)
			)
			(deer init: setScript: deerScript)
		else
			(raccoon init: setLoop: 14 setCycle: End raccoon)
		)
	)
	
	(method (doit)
		(= local4 (ego onControl: 1))
		(cond 
			(script 0)
			((and (& local4 $0004) local1)
				(if (!= (ego view?) 308)
					(soundFx number: 922 loop: -1 flags: 1 play:)
					(ego
						ignoreActors: 1
						illegalBits: 0
						view: 308
						setPri: 15
						setLoop: 2
						setLoop: -1
					)
				)
			)
			((& local4 $0040)
				(if local2
					(= local1 0)
					(= local2 0)
					(messager say: 4 3 4)
					(ego setMotion: 0)
				)
			)
			((& local4 $0002)
				(if local1
					(ego setMotion: 0)
					(repeat
						(ego posn: (ego x?) (- (ego y?) 1))
						(breakif (& (ego onControl: 1) $0004))
					)
					(messager say: 4 3 4)
					(= local1 0)
				else
					(theGame handsOff:)
					(self setScript: wateryDeathScr)
				)
			)
			(
			(and (not (& local4 $0004)) (== (ego view?) 308)) (soundFx fade: 0 10 15 1) (ego reset: 3) (= local1 1))
			((& local4 $4000) (curRoom newRoom: north) (theMusic fade:))
		)
		(super doit: &rest)
	)
	
	(method (newRoom n)
		(theMusic fade:)
		(soundFx2 fade: 0 50 50 1)
		(super newRoom: n)
	)
)

(instance water1 of Actor
	(properties
		x 293
		y 164
		noun 4
		view 500
		signal $4000
		cycleSpeed 20
		detailLevel 1
	)
)

(instance water2 of Actor
	(properties
		x 262
		y 166
		noun 4
		view 500
		loop 1
		cel 2
		signal $4000
		cycleSpeed 25
		detailLevel 1
	)
)

(instance water3 of Actor
	(properties
		x 116
		y 152
		noun 4
		view 500
		loop 2
		cel 2
		signal $4000
		cycleSpeed 15
		detailLevel 1
	)
)

(instance water4 of Actor
	(properties
		x 57
		y 164
		noun 4
		view 500
		loop 3
		signal $4000
		cycleSpeed 30
		detailLevel 1
	)
)

(instance water5 of Actor
	(properties
		x 150
		y 155
		noun 4
		view 500
		loop 4
		cel 2
		signal $4000
		cycleSpeed 20
		detailLevel 1
	)
)

(instance dangle of Actor
	(properties
		x 182
		y 22
		noun 7
		approachX 189
		approachY 103
		view 479
		priority 6
		signal $6010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(85
				(if (not global162)
					(messager say: noun 0 10)
				else
					(theGame handsOff:)
					(self setScript: getDangPartScript)
				)
			)
			(2
				(theGame handsOff:)
				(self setScript: partKludgeScript 0 2)
			)
			(5
				(if (> global162 0)
					(theGame handsOff:)
					(self setScript: partKludgeScript 0 5)
				else
					(messager say: 7 5 10 1)
				)
			)
			(1 (messager say: 7 1 0 1))
			(else 
				(if global162
					(messager say: 7 0 11 0)
				else
					(messager say: 7 0 10 0)
				)
			)
		)
	)
)

(instance plant of Prop
	(properties
		x 142
		y 82
		view 500
		loop 5
		cel 2
		cycleSpeed 30
		detailLevel 1
	)
)

(instance deer of Prop
	(properties
		x 20
		y 116
		noun 10
		view 503
		loop 5
		detailLevel 2
	)
)

(instance raccoon of Actor
	(properties
		x 282
		y 114
		noun 11
		view 503
		loop 13
		detailLevel 2
	)
	
	(method (cue)
		(self dispose:)
	)
)

(instance ocean of Feature
	(properties
		noun 4
		onMeCheck $0006
	)
)

(instance trees of Feature
	(properties
		noun 5
		onMeCheck $0010
	)
)

(instance pathway of Feature
	(properties
		noun 8
		onMeCheck $0008
	)
)

(instance branch of Feature
	(properties
		noun 5
		onMeCheck $0020
	)
	
	(method (doVerb theVerb)
		(trees doVerb: theVerb)
	)
)

(instance stump of Feature
	(properties
		noun 12
		onMeCheck $0080
	)
)

(instance rocks of Feature
	(properties
		noun 2
		onMeCheck $0100
	)
	
	(method (doVerb theVerb)
		(cond 
			((== theVerb 1) (messager say: noun theVerb 2 0 0 0))
			((OneOf theVerb 1 2 5) (messager say: noun theVerb 0 0 0 0))
			(else (messager say: noun 0 0 0 0 0))
		)
	)
)

(instance partKludgeScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client setCycle: 0 setLoop: 0)
				(= cycles 2)
			)
			(1
				(if (== register 2)
					(switch global162
						(0
							(myConv
								add: -1 7 2 12 1
								add: -1 7 2 12 2
								add: -1 7 2 12 3
								add: -1 7 2 12 4
								init: self
							)
							(++ global162)
						)
						(1
							(myConv
								add: -1 7 2 13 1
								add: -1 7 2 13 2
								add: -1 7 2 13 3
								add: -1 7 2 13 4
								add: -1 7 2 13 5
								init: self
							)
							(++ global162)
						)
						(2
							(myConv add: -1 7 2 14 1 add: -1 7 2 14 2 init: self)
							(++ global162)
						)
						(else 
							(messager say: 7 2 15 0 self)
						)
					)
				else
					(myConv add: -1 7 5 11 1 add: -1 7 5 11 2 init: self)
				)
			)
			(2
				(client setLoop: -1 setScript: dangleScript)
				(theGame handsOn:)
			)
		)
	)
)

(instance getDangPartScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(dangle setLoop: 0 setCycle: 0)
				(= cycles 1)
			)
			(1
				(myConv
					add: -1 7 85 0 1
					add: -1 7 85 0 2
					add: -1 7 85 0 3
					add: -1 7 85 0 4
					add: -1 7 85 0 5
					init: self
				)
			)
			(2
				(ego hide:)
				(dangle
					view: 479
					setLoop: 4
					posn: 189 102
					cel: 0
					cycleSpeed: 10
					setPri: 14
					ignoreHorizon: 1
					setCycle: End self
				)
			)
			(3
				(dangle
					view: 478
					setLoop: 1
					cel: 0
					posn: (- (dangle x?) 1) (dangle y?)
					setCycle: End self
				)
			)
			(4
				(dangle setLoop: 0 cel: 0 setCycle: End self)
			)
			(5 (dangle setCycle: End self))
			(6
				(dangle hide:)
				(ego put: 50 500 get: 29 reset: 4 show:)
				(soundFx2 fade:)
				(theGame givePoints: 2)
				(Bset 45)
				(= cycles 2)
			)
			(7
				(messager say: 7 85 0 6 self)
			)
			(8
				(theGame handsOn:)
				(client dispose:)
				(self dispose:)
			)
		)
	)
)

(instance wateryDeathScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (messager say: 4 3 7 0 self))
			(1
				(curRoom walkOffEdge: 1)
				(soundFx stop: number: 921 flags: 1 loop: 1 play:)
				(ego
					view: 269
					setLoop: 1
					cel: 0
					normal: 0
					cycleSpeed: 6
					setCycle: End self
					setMotion: PolyPath (ego x?) 190 self
				)
			)
			(2 0)
			(3 (curRoom newRoom: 135))
		)
	)
)

(instance dangleScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 2))
			(1
				(dangle setLoop: (Random 1 3) setCycle: End self)
			)
			(2 (dangle setCycle: End self))
			(3 (self init:))
		)
	)
)

(instance deerScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 2 5)))
			(1 (deer setCycle: End self))
			(2
				(deer setCel: 0)
				(= cycles 1)
			)
			(3 (self init:))
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
