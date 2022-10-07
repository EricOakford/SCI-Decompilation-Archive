;;; Sierra Script 1.0 - (do not remove this comment)
(script# 600)
(include sci.sh)
(use Main)
(use KQ6Room)
(use Kq6Procs)
(use Conv)
(use Scaler)
(use MoveCyc)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use DPath)
(use Motion)
(use Actor)
(use System)

(public
	rm600 0
)

(local
	[local0 58] = [2 0 175 140 2 1 175 140 2 2 175 140 2 3 175 140 2 4 175 140 2 5 175 140 2 6 175 140 2 7 175 140 2 8 175 140 0 0 175 140 0 1 175 140 0 2 175 140 0 3 175 140 0 4 175 140 -32768 600]
	local58
	deadGuyMover
	deadGuy2Mover
	local61
)
(instance rm600 of KQ6Room
	(properties
		noun 4
		picture 605
		horizon 88
		east 630
	)
	
	(method (init)
		(self
			addObstacle:
				((Polygon new:)
					type: 2
					init: 0 189 0 0 319 0 319 138 268 137 232 102 144 102 102 128 15 141
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 319 163 319 189 224 189
					yourself:
				)
		)
		(super init: &rest)
		(Bset 91)
		(theGame handsOff:)
		(Bset 15)
		(switch prevRoomNum
			(630
				(theGame handsOn:)
				(ego init: setScale: Scaler 100 67 189 84 posn: 303 160)
			)
			(else 
				(if (!= (theGlobalSound number?) 155)
					(theGlobalSound number: 155 play:)
				)
				(self setScript: horseToon)
			)
		)
		(deadGuy init: setScript: deadOneScript)
		(deadGuy2 init: setScript: deadTwoScript)
		(queen init: ignoreActors: 1 setScript: queenScript)
	)
	
	(method (dispose)
		(super dispose:)
		(LoadMany 0 964 942)
	)
	
	(method (newRoom n)
		(deadGuy setMotion: 0 setCycle: 0)
		(deadGuy2 setMotion: 0 setCycle: 0)
		(super newRoom: n)
	)
)

(instance theSky of Feature
	(properties
		noun 7
		onMeCheck $4000
	)
)

(instance horse of Actor
	(properties
		x 196
		y 160
		view 606
		loop 1
	)
)

(instance queen of Actor
	(properties
		x 175
		y 140
		noun 3
		view 626
		loop 4
		cycleSpeed 30
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (ego has: 28)
					(messager say: noun theVerb 1)
				else
					(messager say: noun theVerb 2)
				)
			)
			(2
				(if (ego has: 28)
					(messager say: noun theVerb 1)
				else
					(theGame handsOff:)
					(script register: 1)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance deadGuy of Actor
	(properties
		x -20
		y 116
		noun 6
		view 600
	)
	
	(method (doit)
		(if
			(and
				(not local61)
				mover
				(not (curRoom script?))
				(<= (ego distanceTo: self) 10)
			)
			(theGame handsOff:)
			(= local58 1)
			(curRoom setScript: egoIsDead 0 self)
		)
		(super doit:)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 5)
			(theGame handsOff:)
			(curRoom setScript: egoIsDead 0 self)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance deadGuy2 of Actor
	(properties
		x 341
		y 159
		noun 6
		view 602
	)
	
	(method (doit)
		(if
			(and
				(not local61)
				mover
				(not (curRoom script?))
				(<= (ego distanceTo: self) 10)
			)
			(theGame handsOff:)
			(= local58 1)
			(curRoom setScript: egoIsDead 0 self)
		)
		(super doit:)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 5)
			(theGame handsOff:)
			(curRoom setScript: egoIsDead 0 self)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance deadOneScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 8))
			(1
				(if (curRoom script?) (self init:) else (= cycles 1))
			)
			(2
				(while
					(==
						(= register (+ (Random 0 4) 600))
						(deadGuy2 view?)
					)
					0
				)
				(client
					view: register
					posn: 38 116
					ignoreActors: 1
					init:
					setScale: Scaler 100 67 189 84
					setCycle: Walk
					setMotion: DPath -20 116 38 116 148 122 112 158 -15 152 self
				)
			)
			(3 (self init:))
		)
	)
)

(instance deadTwoScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 8))
			(1
				(if (curRoom script?) (self init:) else (= cycles 1))
			)
			(2
				(while
				(== (= register (+ (Random 0 4) 600)) (deadGuy view?))
					0
				)
				(client
					view: register
					posn: 341 167
					ignoreActors: 1
					init:
					setScale: Scaler 100 67 189 84
					setCycle: Walk
					setMotion: DPath 331 167 149 151 108 107 -21 100 self
				)
			)
			(3 (self init:))
		)
	)
)

(instance queenScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 1))
			(1 (queen setCycle: EndLoop self))
			(2
				(queen setCycle: MoveCycle @local0 self)
			)
			(3
				(= cycles (/ (queen cycleSpeed?) 2))
			)
			(4
				(cond 
					((curRoom script?) (self start: 2 init:))
					(register
						(queen setLoop: 4 cel: 0)
						(curRoom setScript: egoGetTicket)
					)
					(else (self start: 2 init:))
				)
			)
		)
	)
)

(instance egoGetTicket of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= deadGuyMover (deadGuy mover?))
				(deadGuy mover: 0)
				(= deadGuy2Mover (deadGuy2 mover?))
				(deadGuy2 mover: 0)
				(ego setMotion: PolyPath 149 157 self)
			)
			(1 (ego setHeading: 315 self))
			(2
				(theConv
					add: -1 3 2 2 1
					add: -1 3 2 2 2
					add: -1 3 2 2 3
					add: -1 3 2 2 4
					add: -1 3 2 2 5
					add: -1 3 2 2 6
					add: -1 3 2 2 7
					add: -1 3 2 2 8
					add: -1 3 2 2 9
					add: -1 3 2 2 10
					add: -1 3 2 2 11
					init: self
				)
			)
			(3
				(theConv
					add: -1 3 2 2 12
					add: -1 3 2 2 13
					add: -1 3 2 2 14
					add: -1 3 2 2 15
					add: -1 3 2 2 16
					add: -1 3 2 2 17
					init: self
				)
			)
			(4
				(queen setLoop: 4 cel: 0 setCycle: EndLoop self)
				(ego normal: 0 view: 626 setLoop: 5 cel: 0)
			)
			(5
				(ego get: 28)
				(theGame givePoints: 1)
				(queen setCycle: BegLoop self)
			)
			(6 (ego reset: 7) (= cycles 1))
			(7
				(messager say: 3 2 2 18 self)
			)
			(8
				(messager say: 3 2 2 19 self)
			)
			(9
				(if deadGuyMover (deadGuy mover: deadGuyMover))
				(if deadGuy2Mover (deadGuy2 mover: deadGuy2Mover))
				(= deadGuyMover (= deadGuy2Mover 0))
				(= local61 0)
				(queen setScript: queenScript 0 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance theConv of Conversation
	(properties)
)

(instance egoIsDead of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(deadGuy setMotion: 0)
				(deadGuy2 setMotion: 0)
				(if local58
					(messager say: 5 0 3 1 self)
				else
					(messager say: 6 5 0 1 self)
				)
			)
			(1
				(cond 
					(local58 (= cycles 1))
					((> (ego x?) (register x?))
						(ego
							setMotion: PolyPath (+ (register x?) 20) (register y?) self
						)
					)
					(else
						(ego
							setMotion: PolyPath (- (register x?) 20) (register y?) self
						)
					)
				)
				(theMusic stop:)
				(theGlobalSound number: 601 play:)
			)
			(2
				(if local58
					(messager say: 5 0 3 2 self)
				else
					(messager say: 6 5 0 2 self)
				)
			)
			(3
				(ego
					view: 606
					normal: 0
					setLoop: 0
					cel: 0
					cycleSpeed: 15
					setCycle: EndLoop self
				)
			)
			(4 (EgoDead 38))
		)
	)
)

(instance horseToon of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(horse
					init:
					setScale: Scaler 100 67 189 84
					cel: 0
					setCycle: EndLoop self
				)
				(if (== prevRoomNum 155)
					(SetCursor 1)
				else
					(theGame setCursor: theCursor)
				)
			)
			(1
				(ego
					init:
					normal: 0
					setScale: Scaler 100 67 189 84
					view: 606
					setLoop: 2
					cel: 0
					setPri: (+ (horse priority?) 1)
					posn: (+ (horse x?) 38) (- (horse y?) 39)
					setCycle: EndLoop self
				)
				(horse
					view: 607
					setLoop: 0
					cel: 0
					posn: (+ (horse x?) 40) (+ (horse y?) 5)
				)
			)
			(2
				(ego reset: 7 posn: (+ (ego x?) 17) (+ (ego y?) 47))
				(= cycles 2)
			)
			(3 (messager say: 5 0 4 1 self))
			(4
				(horse setCycle: EndLoop)
				(theGlobalSound fade: 0 10 20 1 self)
			)
			(5
				(theMusic number: 600 flags: 1 play:)
				(= cycles 1)
			)
			(6 (messager say: 5 0 4 2 self))
			(7
				(horse dispose:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)
