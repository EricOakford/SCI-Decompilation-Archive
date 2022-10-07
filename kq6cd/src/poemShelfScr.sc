;;; Sierra Script 1.0 - (do not remove this comment)
(script# 272)
(include sci.sh)
(use Main)
(use Conv)
(use Motion)
(use Actor)
(use System)

(public
	poemShelfScr 0
)

(local
	local0 =  -1
	local1
)
(instance roomConv of Conversation
	(properties)
)

(instance turnPageScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (ego setCycle: CycleTo 1 -1 self))
			(1 (= cycles 2))
			(2 (ego setCycle: EndLoop self))
			(3 (= cycles 2))
			(4 (self dispose:))
		)
	)
)

(instance poemShelfScr of Script
	(properties)
	
	(method (dispose)
		(= register 0)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (!= (ego loop?) 3) (ego loop: 3))
				(= cycles 2)
			)
			(1
				(ego
					setSpeed: 6
					view: 279
					loop: 2
					cel: 0
					normal: 0
					setCycle: EndLoop self
				)
			)
			(2
				(ego loop: 3 cel: 0 setCycle: EndLoop self)
			)
			(3
				(if (!= ((inventory at: 47) owner?) 270)
					(= register 1)
					(roomConv
						add: -1 13 5 18 1
						add: -1 13 5 18 2
						add: -1 13 5 18 3
						add: turnPageScr
						add: -1 13 5 18 4
						add: -1 13 5 18 5
						init: self
					)
				else
					(roomConv
						add: -1 13 5 17 1
						add: -1 13 5 17 2
						add: -1 13 5 17 3
						add: turnPageScr
						add: -1 13 5 17 4
						add: -1 13 5 17 5
						add: turnPageScr
						add: -1 13 5 17 6
						add: turnPageScr
						add: -1 13 5 17 7
						add: turnPageScr
						add: -1 13 5 17 8
						add: -1 13 5 17 9
						add: -1 13 5 17 10
						init: self
					)
				)
			)
			(4
				(if (not register)
					((inventory at: 47) owner: -1)
					(poem
						init:
						posn: 303 92
						view: 279
						setLoop: 6
						cel: 0
						setCycle: Forward
						setMotion: MoveTo 281 120 poem
					)
				)
				(= cycles 2)
			)
			(5
				(ego loop: 2 cel: (ego lastCel:))
				(= cycles 2)
			)
			(6 (ego setCycle: BegLoop self))
			(7
				(ego reset: 3)
				(if (not register) (theGame givePoints: 1))
				(= cycles 1)
			)
			(8
				(if (and (not local1) (not register))
					(-- state)
					(= ticks 12)
				else
					(= cycles 2)
				)
			)
			(9
				(UnLoad 128 279)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance takePoemScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(theGame givePoints: 1)
				(ego
					setSpeed: 6
					view: 2701
					loop: 1
					cel: 0
					normal: 0
					setCycle: CycleTo 3 1 self
				)
			)
			(1
				(poem dispose:)
				(ego get: 47)
				(= cycles 2)
			)
			(2 (ego setCycle: EndLoop self))
			(3 (= cycles 2))
			(4 (ego reset: 7) (= cycles 2))
			(5
				(UnLoad 128 2701)
				(messager say: 6 5 0 1 self)
			)
			(6 (messager say: 6 5 0 2 self))
			(7 (messager say: 6 5 0 3 self))
			(8 (ego setHeading: 180 self))
			(9 (= cycles 2))
			(10
				(messager say: 6 5 0 4 self)
			)
			(11
				(theGame handsOn:)
				(self dispose:)
				(DisposeScript 272)
			)
		)
	)
)

(instance poem of Actor
	(properties
		x 281
		y 120
		noun 6
		approachX 292
		approachY 128
		view 270
		loop 2
		cel 1
		priority 1
		signal $4811
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 5)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(curRoom setScript: takePoemScr)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (cue)
		(if (not (++ local0))
			(self setCycle: EndLoop self)
		else
			(self view: 270 setLoop: 2 cel: 1)
			(= local1 1)
		)
	)
)
