;;; Sierra Script 1.0 - (do not remove this comment)
(script# 400)
(include sci.sh)
(use Main)
(use Scaler)
(use Polygon)
(use Feature)
(use LoadMany)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm400 0
	sUseComm 20
)

(local
	[local0 2]
)
(instance rm400 of Rm
	(properties
		noun 5
		picture 81
	)
	
	(method (init)
		(LoadMany 128 451)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init:
						0
						176
						301
						172
						301
						153
						193
						98
						168
						99
						157
						91
						144
						95
						113
						96
						79
						93
						81
						99
						95
						99
						119
						100
						99
						115
						63
						129
						33
						135
						0
						133
					yourself:
				)
		)
		(ego view: 0 init:)
		(theExit init:)
		(theCanister init: setOnMeCheck: 1 4)
		(theHeatwaves init:)
		(theMoon init:)
		(theRocks init: setOnMeCheck: 1 4096)
		(switch prevRoomNum
			(240
				(curRoom style: 7 setScript: sInitRoom)
			)
			(410
				(curRoom style: 12 setScript: sInitRoom)
			)
			(else 
				(curRoom style: 7 setScript: sInitRoom)
			)
		)
		(super init: &rest)
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(IsObjectOnControl ego 2)
				(== global131 1)
				(not (curRoom script?))
			)
			(Bclr 100)
			(curRoom newRoom: 410)
		)
		(if
		(and (IsObjectOnControl ego 8) (not (curRoom script?)))
			(Bset 100)
			(curRoom newRoom: 410)
		)
	)
	
	(method (dispose)
		(super dispose: &rest)
	)
)

(instance sInitRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(sun init:)
				(dust init:)
				(dust2 init:)
				(dust3 init:)
				(switch global131
					(1
						(canCoverL init: stopUpd:)
						(canCoverR init: stopUpd:)
						(if (Btst 100)
							(NormalEgo 0 0)
							(ego
								init:
								x: 79
								y: 93
								setScale: Scaler 100 48 163 99
								setMotion: MoveTo 124 101 self
							)
						else
							(NormalEgo 0 0)
							(ego
								init:
								x: (- (ego x?) 320)
								y: (ego y?)
								setScale: Scaler 100 48 163 99
								setMotion: MoveTo 60 145 self
							)
						)
					)
					(2
						(ego hide: dispose:)
						(roger init: x: -40 y: 75 hide:)
						(rogblink init: hide:)
						(self setScript: sCanister self)
					)
					(else  (= cycles 1))
				)
				(= seconds 1)
			)
			(1 (= seconds 3))
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sUseComm of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego view: 14 setLoop: 0 cel: 0 setCycle: End self)
				(theMusic2 number: 603 loop: 1 play:)
			)
			(1
				(messager say: 2 32 0 0 self 401)
			)
			(2 (ego setCycle: Beg self))
			(3
				(NormalEgo 0)
				(ego loop: 2)
				(= seconds 1)
			)
			(4
				(ego view: 6 setLoop: 0 cel: 15 setCycle: Beg self)
				(theMusic2 number: 260 loop: 1 play:)
			)
			(5
				(theMusic1 fade:)
				(curRoom newRoom: 240)
			)
		)
	)
)

(instance sCanister of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 3))
			(1
				(roger show: setPri: 14 setMotion: MoveTo 0 75 self)
				(rogblink show:)
			)
			(2 (self dispose:))
		)
	)
)

(instance sExit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(roger setMotion: MoveTo -50 75 self)
			)
			(1
				(roger hide:)
				(rogblink hide:)
				(= cycles 2)
			)
			(2 (curRoom newRoom: 410))
		)
	)
)

(instance roger of Actor
	(properties
		y 75
		view 451
		loop 2
		signal $6000
		illegalBits $0000
	)
	
	(method (init)
		(super init: &rest)
		(self setLoop: 2 setCel: 0)
	)
)

(instance rogblink of Prop
	(properties
		x 36
		y 112
		view 451
		loop 3
	)
	
	(method (init)
		(super init: &rest)
		(self setLoop: 3 cycleSpeed: 100 setPri: 15 setCycle: Fwd)
	)
	
	(method (doit)
		(self x: (+ (roger x?) 36) y: 112)
		(super doit: &rest)
	)
)

(instance sun of Prop
	(properties
		x 88
		y 57
		view 451
		detailLevel 2
	)
	
	(method (init)
		(self setLoop: 0 setCycle: Fwd cycleSpeed: 10)
		(super init: &rest)
	)
)

(instance dust of Prop
	(properties
		x 265
		y 28
		view 451
		loop 1
		detailLevel 2
	)
	
	(method (init)
		(self setLoop: 1 setCycle: Fwd cycleSpeed: 10)
		(super init: &rest)
	)
)

(instance dust2 of Prop
	(properties
		x 253
		y 36
		view 451
		loop 1
		detailLevel 2
	)
	
	(method (init)
		(self setLoop: 1 setCycle: Fwd cycleSpeed: 10)
		(super init: &rest)
	)
)

(instance dust3 of Prop
	(properties
		x 10
		y 11
		view 451
		loop 1
		detailLevel 2
	)
	
	(method (init)
		(self setLoop: 1 setCycle: Fwd cycleSpeed: 10)
		(super init: &rest)
	)
)

(instance canCoverL of View
	(properties
		x 191
		y 114
		view 451
		loop 4
		cel 1
		priority 15
		signal $0010
	)
)

(instance canCoverR of View
	(properties
		x 4
		y 126
		view 451
		loop 4
		priority 15
		signal $0010
	)
)

(instance theExit of Feature
	(properties
		x 10
		nsBottom 200
		nsRight 320
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3 (curRoom setScript: sExit))
			(4 (curRoom setScript: sExit))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance theCanister of Feature
	(properties
		x 200
		y 150
		noun 1
		onMeCheck $0004
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (== global131 2)
					(SolvePuzzle 222 50)
					(messager say: 1 1 0 0)
				else
					(messager say: 4 1 0 0)
				)
			)
			(3
				(if (== global131 2) (messager say: 1 3 0 0))
			)
			(4
				(if (== global131 2) (messager say: 1 4 0 0))
			)
			(else  (super doVerb: &rest))
		)
	)
)

(instance theMoon of Feature
	(properties
		x 100
		y 28
		noun 3
		nsTop 9
		nsLeft 66
		nsBottom 58
		nsRight 151
	)
)

(instance theHeatwaves of Feature
	(properties
		x 100
		y 100
		noun 2
		nsTop 60
		nsLeft 91
		nsBottom 70
		nsRight 142
	)
)

(instance theRocks of Feature
	(properties
		x 160
		y 100
		noun 6
		onMeCheck $1000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(messager say: 6 1 0 (Random 1 3) 0)
			)
			(else  (super doVerb: &rest))
		)
	)
)
