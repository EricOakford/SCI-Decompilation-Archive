;;; Sierra Script 1.0 - (do not remove this comment)
(script# 4)
(include sci.sh)
(use Main)
(use Intrface)
(use Arcada)
(use SQRoom)
(use Elevator)
(use Osc)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use DPath)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm4 0
	beginGame 1
)

(local
	local0
	inCloset
	panicDroidPassingBy
	local3
	local4
	local5
)
(instance rm4 of SQRoom
	(properties
		picture 4
		east 5
		west 3
	)
	
	(method (init &tmp [temp0 90])
		(LoadMany 128 104 4 400)
		(Load rsSOUND 311)
		(= local5 6)
		(ego init: setStep: 4 2)
		(body init:)
		(if (== currentFloor 1)
			(self
				addObstacle:
					((Polygon new:)
						type: 2
						init: 0 0 319 0 319 52 162 78 121 78 103 69 103 48 99 48 99 68 82 77 0 77
						yourself:
					)
					((Polygon new:)
						type: 2
						init: 187 94 319 72 319 189 0 189 0 94
						yourself:
					)
			)
			((ScriptID 700 0)
				sarienEntryDir: 2
				s1startY: 54
				s2startY: 67
				s1gotoX: 229
				s1gotoY: 69
				s2gotoX: 231
				s2gotoY: 85
			)
		else
			(body approachVerbs: 2 3 5)
			(if (== prevRoomNum 3) (ego setPri: 3))
			(self
				addObstacle:
					((Polygon new:)
						type: 2
						init: 0 156 60 156 66 152 112 152 118 158 187 187 319 187 319 189 0 189
						yourself:
					)
					((Polygon new:)
						type: 2
						init:
							0
							0
							319
							0
							319
							160
							299
							160
							297
							172
							279
							172
							276
							156
							260
							156
							260
							172
							243
							172
							233
							163
							182
							163
							103
							133
							0
							133
						yourself:
					)
			)
			((ScriptID 700 0)
				sarienEntryDir: 1
				s1startY: 138
				s2startY: 154
				s1gotoX: 88
				s1gotoY: 140
				s2gotoX: 66
				s2gotoY: 155
			)
		)
		(self setRegions: 700)
		((ScriptID 700 0) safeCode: egoSafe)
		(alertSign init: setCycle: Fwd)
		(switch prevRoomNum
			(3 (= style 12))
			(5 (= style 11))
			(8
				(ego posn: 270 164)
				(= style 10)
			)
			(else 
				(= style -32759)
				(LoadMany 132 998 983 309 357)
				(LoadMany 128 414)
				(ego loop: 2 posn: 100 65 heading: 180)
				(if (not howFast)
					(= selfDestructTimer 1801)
				else
					(= selfDestructTimer 901)
				)
				(alertSign cel: 0 setCycle: 0)
				(self setScript: beginGame)
			)
		)
		(closetDoor init: stopUpd:)
		(= local0 0)
		(super init:)
		(lowerDoor
			exiting: (== prevRoomNum 8)
			light: elevatorLight
			init:
			whereTo: 8
		)
		(if (OneOf prevRoomNum west east) (HandsOn))
	)
	
	(method (doit)
		(super doit:)
		(= local4 (ego onControl: 1))
		(if (== currentFloor 2)
			(cond 
				((< (ego x?) 186) (if (not (& (ego signal?) $0010)) (ego setPri: 3)))
				((& $0010 (ego signal?)) (ego setPri: -1))
			)
		)
		(cond 
			(script 0)
			((and inCloset (ego mover?)) (self setScript: walkOutCloset))
			((lowerDoor inFront:) (lowerDoor open:))
			((and (& local4 $0002) (not inCloset)) (= inCloset 1))
			(
			(and (ego inRect: 50 40 150 99) (& local4 $4000)) (self setScript: walkInCloset))
		)
	)
	
	(method (handleEvent event)
		(if
			(and
				(User controls?)
				inCloset
				(== (theIconBar curIcon?) (theIconBar at: 0))
				(or
					(== (event message?) JOY_UP)
					(& (event type?) evJOYSTICK)
				)
			)
			(self setScript: walkOutCloset)
			(event claimed: 1)
		else
			(super handleEvent: event)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(2
				(if inCloset
					(Print
						{That's not possible. It's so dark that you couldn't find your own navel lint. Surely you MUST remember that this was the criteria by which you came to choose this location for your nap... er... work station.}
					)
				else
					(Print 4 0)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (notify)
		(if (== prevRoomNum 8) (HandsOff))
	)
)

(instance searchBody of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setHeading: 180 self)
			)
			(1
				(if register (Print 4 1 (= state 2)))
				(ego
					view: 4
					loop: (if (< (ego x?) ((CueObj client?) x?)) 1 else 0)
					cycleSpeed: 5
					moveSpeed: 5
					cel: register
					setCycle: (if register Beg else End) self
				)
			)
			(2
				(= state 0)
				(= register 3)
				(ego
					loop: (+ (ego loop?) 2)
					cel: 0
					setCycle: Osc 2 self
				)
			)
			(3
				(HandsOn)
				(= state -1)
				(= register 0)
				(NormalEgo 2 0 60)
				(self dispose:)
			)
		)
	)
)

(instance walkInCloset of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(soundFx number: 311 loop: 1 play:)
				(closetDoor startUpd: setCycle: End self)
			)
			(1
				(soundFx stop:)
				(ego setMotion: PolyPath 101 54 self)
			)
			(2
				(= inCloset 1)
				(soundFx number: 311 loop: 1 play:)
				(closetDoor stopUpd: setCycle: Beg self)
			)
			(3
				(soundFx stop:)
				(if (not (ArcadaCheck 551 8))
					(soundFx number: 310 loop: 1 play: self)
				else
					(HandsOn)
					(self dispose:)
				)
			)
			(4
				(Print 4 2)
				((ScriptID 700 0)
					rFlag1: (| ((ScriptID 700 0) rFlag1?) $0008)
				)
				(= cycles 6)
			)
			(5 (HandsOn) (self dispose:))
		)
	)
)

(instance walkOutCloset of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(soundFx number: 311 loop: 1 play:)
				(closetDoor startUpd: setCycle: End self)
			)
			(1
				(soundFx stop:)
				(ego setMotion: MoveTo (ego x?) 78 self)
			)
			(2
				(= inCloset 0)
				(soundFx number: 311 loop: 1 play:)
				(closetDoor stopUpd: setCycle: Beg self)
			)
			(3
				(soundFx stop:)
				(if (not (client isKindOf: Script)) (HandsOn))
				(self dispose:)
			)
		)
	)
)

(instance beginGame of Script
	(properties)
	
	(method (init)
		(super init: &rest)
		(keyDownHandler addToFront: self)
		(mouseDownHandler addToFront: self)
	)
	
	(method (doit &tmp temp0)
		(super doit:)
		(ego moveHead: (not panicDroidPassingBy))
		(if panicDroidPassingBy
			(cond 
				((< (panicDroid x?) 90) ((ego _head?) cel: 5))
				((> (panicDroid x?) 110) ((ego _head?) cel: 2))
				(else ((ego _head?) cel: 4))
			)
		)
	)
	
	(method (dispose)
		(keyDownHandler delete: self)
		(mouseDownHandler delete: self)
		(super dispose:)
	)
	
	(method (changeState newState &tmp [temp0 150])
		(switch (= state newState)
			(0
				(theIconBar enable:)
				(HandsOff)
				((ScriptID 700 0) cartNumber: (Random 1 5))
				(= selfDestructCode
					(+
						(* (Random 1 9) 1000)
						(* (Random 1 9) 100)
						(* (Random 1 9) 10)
						(* (Random 1 9) 1)
					)
				)
				(= cycles 12)
			)
			(1
				(theMusic number: 307 loop: -1 flags: 1 play:)
				(alertSign setCycle: Fwd)
				(= currentFloor 1)
				(= inCloset 1)
				(= seconds 3)
			)
			(2
				(self setScript: walkOutCloset self)
			)
			(3 (= seconds 2))
			(4
				(if
				(< (theGame detailLevel:) (alertSign detailLevel:))
					(alertSign setCycle: 0)
				)
				(ShakeScreen 3 (Random 1 3))
				(theMusic number: 339 loop: 1 play: self)
			)
			(5
				(= panicDroidPassingBy 1)
				(HandsOn)
				(theIconBar disable: 0 2 3 4 5 6 7)
				(panicDroid init: setCycle: Fwd)
				(theMusic number: 300 loop: -1 play:)
			)
			(6
				(HandsOff)
				(theIconBar curIcon: (theIconBar at: 0))
				(= panicDroidPassingBy 0)
				(Print 4 3)
				(= cycles 3)
			)
			(7
				(Format @temp0 4 4 (/ selfDestructTimer 60))
				(Print @temp0 #mode 1 #at 123 24 #dispose)
				(theMusic2 number: 309 loop: 1 play: self)
			)
			(8
				(= seconds 0)
				((ScriptID 700 0)
					inGame: 1
					rFlag1: (| ((ScriptID 700 0) rFlag1?) $0004)
				)
			)
			(9
				(if modelessDialog
					(modelessDialog caller: self seconds: 10)
				)
				(= register 1)
				0
			)
			(10
				(= register 0)
				(= cycles 1)
			)
			(11 (HandsOn) (self dispose:))
		)
	)
	
	(method (handleEvent event)
		(if (and register (event type?))
			(if modelessDialog
				(modelessDialog seconds: 0 caller: 0 dispose:)
			)
			(= register 0)
			(= cycles 1)
			(event claimed: 1)
		)
	)
)

(instance egoSafe of Code
	(properties)
	
	(method (doit)
		(return
			(if
			(or (lowerDoor busy?) (!= currentFloor sarienFloor))
			else
				(& local4 local5)
			)
		)
	)
)

(instance elevatorLight of View
	(properties
		description {elevator light}
		sightAngle 45
		view 104
		loop 2
	)
	
	(method (doVerb theVerb)
		(if (== currentFloor 2)
			(switch theVerb
				(2 (Print 4 5))
				(12 (Print 4 6))
				(11 (Print 4 6))
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		else
			(Print {You aren't on that level.})
		)
	)
)

(instance body of View
	(properties
		x 88
		y 158
		description {body}
		sightAngle 45
		approachX 97
		approachY 153
		view 400
		priority 14
		signal $4011
	)
	
	(method (doVerb theVerb theItem)
		(if (== currentFloor 2)
			(switch theVerb
				(2 (Print 4 7))
				(3
					(cond 
						(inCloset (Print 4 8))
						((!= currentFloor 2) (Print 4 9))
						(else (curRoom setScript: searchBody))
					)
				)
				(5 (Print 4 10))
				(12 (Print 4 11))
				(11 (Print 4 12))
				(4
					(switch theItem
						(10 (Print 4 13))
						(0 (Print 4 14))
						(1 (Print 4 15))
						(15 (Print 4 16))
						(2 (Print 4 17))
						(else 
							(super doVerb: theVerb theItem)
						)
					)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		else
			(Print {You aren't on that level.})
		)
	)
)

(instance lowerDoor of Elevator
	(properties
		x 269
		y 167
		description {elevator door}
		sightAngle 45
		view 104
		loop 1
		cycleSpeed 4
		level 2
	)
	
	(method (doVerb theVerb)
		(if (== currentFloor 2)
			(switch theVerb
				(2 (Print 4 18))
				(12 (Print 4 19))
				(11 (Print 4 20))
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		else
			(Print {You aren't on that level.})
		)
	)
)

(instance closetDoor of Prop
	(properties
		x 101
		y 68
		description {closet}
		sightAngle 90
		view 104
		priority 3
		signal $4010
		cycleSpeed 8
	)
	
	(method (doVerb theVerb)
		(if (== currentFloor 1)
			(switch theVerb
				(2
					(if inCloset (Print 4 21) else (Print 4 22))
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		else
			(Print {You aren't on that level.})
		)
	)
)

(instance alertSign of Prop
	(properties
		x 40
		y 46
		description {warning light}
		sightAngle 90
		view 104
		loop 3
		cycleSpeed 8
		detailLevel 2
	)
	
	(method (doVerb theVerb)
		(if (== currentFloor 1)
			(switch theVerb
				(2
					(if inCloset (Print 4 21) else (Print 4 23))
				)
				(3 (Print 4 24))
				(12 (Print 4 25))
				(11 (Print 4 26))
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		else
			(Print {You aren't on that level.})
		)
	)
)

(instance panicDroid of Actor
	(properties
		x 337
		y 57
		description {panicking droid}
		lookStr {A somewhat spastic research droid blows by in a tizz. Perhaps you could provide some relaxation therapy instruction to reduce its level of tension.}
		yStep 6
		view 414
		cycleSpeed 5
		xStep 10
		moveSpeed 4
	)
	
	(method (init)
		(super init: &rest)
		(theMusic2 number: 357 loop: -1 play: 0)
		(self
			setLoop: 0
			ignoreActors: 1
			setMotion: DPath 159 89 -27 89 self
		)
	)
	
	(method (doit &tmp temp0 temp1 temp2)
		(super doit:)
		(if
		(< (= temp0 (Abs (- (panicDroid x?) (ego x?)))) 0)
			(= temp0 0)
		)
		(if (> temp0 90) (= temp0 90))
		(if
			(>
				(= temp1
					(Abs (- (+ (- (ego x?) (panicDroid x?)) 63) 127))
				)
				127
			)
			(= temp1 127)
		)
		(if (< temp1 0) (= temp1 0))
		(theMusic2
			send: 7 10 temp1
			send: 8 10 temp1
			send: 9 10 temp1
			send: 11 10 temp1
			setVol: (- 127 temp0)
		)
		(if (< (- x (ego x?)) 0)
			(if (< (= local3 (- local3 6)) -512) (= local3 -512))
			(theMusic2
				send: 7 224 local3
				send: 8 224 local3
				send: 9 224 local3
				send: 11 224 local3
			)
		)
	)
	
	(method (dispose)
		(theMusic2 loop: 0 stop:)
		(super dispose:)
	)
	
	(method (cue)
		(beginGame cue:)
		(self dispose:)
	)
)
