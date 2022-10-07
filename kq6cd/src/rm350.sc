;;; Sierra Script 1.0 - (do not remove this comment)
(script# 350)
(include sci.sh)
(use Main)
(use KQ6Room)
(use Kq6Procs)
(use Conv)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use StopWalk)
(use Motion)
(use Actor)
(use System)

(public
	rm350 0
)

(local
	local0
)
(instance myConv of Conversation
	(properties)
)

(instance rm350 of KQ6Room
	(properties
		noun 3
		picture 350
	)
	
	(method (init)
		(super init: &rest)
		(theMusic number: 350 setLoop: -1 play:)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						304
						189
						268
						167
						233
						162
						169
						162
						169
						150
						148
						150
						148
						162
						79
						162
						47
						169
						14
						189
						0
						189
						0
						0
						319
						0
						319
						189
					yourself:
				)
		)
		(if (not (Btst 2))
			(LoadMany 128 343 344 348 3481 349 3491)
		)
		(features
			add: nests palace oracleMtn gate
			eachElementDo: #init
		)
		(leftGuard init:)
		(riteGuard init:)
		(ego reset: 3 posn: 160 188 init:)
		(if (not (Btst 2))
			(theIconBar
				enable:
				disable: 0 1 2 3 4 5 6
				height: -100
				activateHeight: -100
			)
			(Cursor showCursor: 0)
			(curRoom setScript: egoEnters)
		else
			(theGame handsOn:)
		)
	)
	
	(method (doit)
		(cond 
			((curRoom script?))
			((== (ego onControl: 1) 64) (curRoom setScript: stayOut))
			((== (ego edgeHit?) 3) (curRoom setScript: walkOut))
		)
		(super doit:)
	)
	
	(method (dispose)
		(ego setScale: 0)
		(super dispose:)
	)
	
	(method (scriptCheck param1 &tmp temp0)
		(= temp0 1)
		(if (== param1 87)
			(messager say: 0 0 94 1 0 899)
			(= temp0 0)
		)
		(return temp0)
	)
)

(instance gate of Feature
	(properties
		noun 5
		onMeCheck $0002
	)
)

(instance nests of Feature
	(properties
		noun 6
		onMeCheck $0004
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (messager say: 6 1 0 1))
			(2 (messager say: 6 2 0 1))
			(else  (messager say: 6 5 0 1))
		)
	)
)

(instance palace of Feature
	(properties
		noun 8
		onMeCheck $0010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (messager say: 8 1 0 1))
			(2 (messager say: 6 2 0 1))
			(else  (messager say: 6 5 0 1))
		)
	)
)

(instance oracleMtn of Feature
	(properties
		noun 7
		onMeCheck $0020
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (messager say: 7 1 0 1))
			(2 (messager say: 7 2 0 1))
			(else  (messager say: 7 5 0 1))
		)
	)
)

(instance stayOut of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: 0)
				(leftGuard
					view: 345
					setLoop: 1
					cel: 0
					cycleSpeed: 8
					setCycle: EndLoop
				)
				(riteGuard
					view: 345
					setLoop: 0
					cel: 0
					cycleSpeed: 8
					setCycle: EndLoop self
				)
			)
			(1 (messager say: 4 2 8 2 self))
			(2
				(ego
					setLoop: (ego cel?)
					setMotion: MoveTo (ego x?) (+ (ego y?) 5) self
				)
			)
			(3
				(ego setLoop: -1)
				(leftGuard setCycle: BegLoop)
				(riteGuard setCycle: BegLoop self)
			)
			(4
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance dummyEgo of Actor
	(properties)
)

(instance egoEnters of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo 160 160 self)
			)
			(1 (= cycles 6))
			(2 (messager say: 1 0 1 1 self))
			(3
				(leftGuard
					setCycle: StopWalk -1
					setMotion: MoveTo 134 158
				)
				(riteGuard
					setCycle: StopWalk -1
					setMotion: MoveTo 189 158 self
				)
			)
			(4
				(leftGuard setHeading: 90 self)
				(riteGuard setHeading: 270 self)
			)
			(5 0)
			(6 (= cycles 2))
			(7
				(myConv
					add: -1 1 0 1 2
					add: -1 1 0 1 3
					add: -1 1 0 1 4
					add: -1 1 0 1 5
					add: -1 1 0 1 6
					add: -1 1 0 1 7
					init: self
				)
			)
			(8
				(leftGuard dispose:)
				(riteGuard dispose:)
				(ego hide:)
				(dummyEgo
					view: 351
					posn: 160 164
					init:
					setCycle: EndLoop self
				)
				(UnLoad 128 900)
			)
			(9
				(dummyEgo
					view: 352
					ignoreHorizon:
					cycleSpeed: 6
					moveSpeed: 6
					posn: 160 164
					setCycle: EndLoop self
				)
				(UnLoad 128 351)
			)
			(10
				(dummyEgo
					view: 353
					posn: 162 103
					setCycle: Forward
					cycleSpeed: 3
					setStep: 15 12
					setMotion: MoveTo (ego x?) -50 self
				)
				(UnLoad 128 352)
			)
			(11
				(dummyEgo
					view: 353
					setScale: Scaler 50 49 190 0
					setLoop: 1
					setStep: 3 2
					posn: 230 -20
					setCycle: Forward
					setMotion: MoveTo 139 7 self
				)
			)
			(12
				(theMusic fade: 0 20 15)
				(curRoom newRoom: 370)
			)
		)
	)
)

(instance walkOut of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo (ego x?) (+ (ego y?) 50) self)
			)
			(1
				(theMusic fade: 0 15 15)
				(curRoom newRoom: 340)
			)
		)
	)
)

(instance leftGuard of Actor
	(properties
		x 120
		y 150
		noun 4
		approachX 120
		approachY 160
		yStep 3
		view 344
		loop 2
		signal $6000
		xStep 5
	)
	
	(method (init)
		(self approachVerbs: 0)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(2
				(if (Btst 101)
					(curRoom setScript: talkToGuards)
				else
					(Bset 101)
					(super doVerb: theVerb &rest)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance riteGuard of Actor
	(properties
		x 200
		y 150
		noun 4
		approachX 200
		approachY 160
		yStep 3
		view 343
		loop 2
		signal $6000
		xStep 5
	)
	
	(method (init)
		(self approachVerbs: 0)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(2
				(if (Btst 101)
					(curRoom setScript: talkToGuards)
				else
					(Bset 101)
					(super doVerb: theVerb &rest)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance talkToGuards of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 160 160 self)
			)
			(1 (= cycles 4))
			(2 (messager say: 4 2 8 0 self))
			(3
				(ego setLoop: 3 setMotion: MoveTo 160 175 self)
			)
			(4
				(ego reset: 3)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)
