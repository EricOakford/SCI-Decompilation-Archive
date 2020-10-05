;;; Sierra Script 1.0 - (do not remove this comment)
(script# 781)
(include game.sh)
(use Main)
(use Intrface)
(use Wander)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	dock 0
)

(local
	[local0 2]
)
(instance dock of Room
	(properties
		picture 1
	)
	
	(method (init)
		(= horizon 84)
		(= talkTimer 0)
		(super init:)
		(HandsOff)
		(Load FONT 4)
		(skiff
			view: 202
			loop: 0
			posn: -51 147
			setStep: 1 1
			setMotion: MoveTo 121 147 self
			setScript: Polling
			init:
		)
		(skiff cel: (- (NumCels skiff) 1))
		(Lillian
			view: 202
			loop: 3
			x: 95
			y: 115
			init:
			hide:
			stopUpd:
		)
		(Laura
			view: 202
			loop: 4
			x: 106
			y: 118
			init:
			hide:
			stopUpd:
		)
		(ripple
			view: 202
			loop: 1
			cel: 0
			setCycle: Forward
			setStep: 1 1
			init:
		)
		(Turtle
			view: 101
			loop: 2
			cel: 0
			posn: 61 187
			setPri: 13
			init:
			stopUpd:
			setScript: turtleScript
		)
		(Frog
			view: 101
			loop: 3
			cel: 0
			posn: 74 123
			priority: 8
			init:
			stopUpd:
			setScript: frogScript
		)
		(flyCage left: -2 right: 321 bottom: 191 top: -2 init:)
		(fly1
			view: 101
			setLoop: 7
			cel: 0
			posn: (Random 60 260) (Random 40 150)
			setStep: 3 3
			observeBlocks: flyCage
			ignoreActors: TRUE
			ignoreHorizon: TRUE
			setMotion: Wander 5
			cycleSpeed: 2
			setCycle: Forward
			setPri: 15
			init:
		)
		(fly2
			view: 101
			setLoop: 7
			cel: 1
			posn: (Random 60 260) (Random 40 150)
			setStep: 3 3
			observeBlocks: flyCage
			ignoreActors: TRUE
			ignoreHorizon: TRUE
			setMotion: Wander 5
			cycleSpeed: 2
			setCycle: Forward
			setPri: 15
			init:
		)
		(fly3
			view: 101
			setLoop: 7
			cel: 0
			posn: (Random 60 260) (Random 40 150)
			setStep: 3 3
			observeBlocks: flyCage
			ignoreActors: TRUE
			ignoreHorizon: TRUE
			setMotion: Wander 5
			cycleSpeed: 2
			setCycle: Forward
			setPri: 15
			init:
		)
		(fly4
			view: 101
			setLoop: 7
			cel: 1
			posn: (Random 60 260) (Random 40 150)
			setStep: 3 3
			observeBlocks: flyCage
			ignoreActors: TRUE
			ignoreHorizon: TRUE
			setMotion: Wander 5
			cycleSpeed: 2
			setCycle: Forward
			setPri: 15
			init:
		)
		(fly5
			view: 101
			setLoop: 7
			cel: 0
			posn: (Random 60 260) (Random 40 150)
			setStep: 3 3
			observeBlocks: flyCage
			ignoreActors: TRUE
			ignoreHorizon: TRUE
			setMotion: Wander 5
			cycleSpeed: 2
			setCycle: Forward
			setPri: 15
			init:
		)
		(fly6
			view: 101
			setLoop: 7
			cel: 1
			posn: (Random 60 260) (Random 40 150)
			setStep: 3 3
			observeBlocks: flyCage
			ignoreActors: TRUE
			ignoreHorizon: TRUE
			setMotion: Wander 5
			cycleSpeed: 2
			setCycle: Forward
			setPri: 15
			init:
		)
		(wave1
			view: 101
			loop: 0
			cel: 0
			posn: 261 123
			ignoreActors: TRUE
			cycleSpeed: 2
			setCycle: Forward
			setPri: 2
			init:
		)
		(wave2
			view: 101
			loop: 1
			cel: 2
			posn: 260 186
			priority: 14
			ignoreActors: TRUE
			cycleSpeed: 2
			setCycle: Forward
			setPri: 2
			init:
		)
	)
	
	(method (doit)
		(if (== talkTimer 1)
			(cSound stop:)
			(curRoom newRoom: 782)
		)
		(ripple posn: (+ (skiff x?) 37) (+ (skiff y?) 1))
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(switch (event type?)
			(keyDown
				(if
					(or
						(== (event message?) `S)
						(== (event message?) `s)
					)
					(cSound stop:)
					(curRoom newRoom: 782)
				)
			)
		)
	)
	
	(method (cue)
		(ShakeScreen 5 shakeSRight)
		(skiff loop: 2 stopUpd: setScript: Heads)
		(ripple stopUpd: hide:)
		(Lillian show:)
		(Laura show:)
	)
	
	(method (newRoom n)
		(super newRoom: n)
	)
)

(instance turtleScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 5 20)))
			(1 (Turtle setCycle: EndLoop self))
			(2 (client setScript: 0))
		)
	)
)

(instance Polling of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles (Random 45 65)))
			(1 (skiff setCycle: BegLoop self))
			(2
				(skiff setCycle: EndLoop self)
				(client setScript: 0)
			)
		)
	)
)

(instance frogScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (Frog setCycle: EndLoop self))
			(1 (= seconds (Random 3 8)))
			(2
				(Frog
					loop: 6
					posn: 112 106
					illegalBits: cBLACK
					setCycle: BegLoop self
				)
			)
			(3
				(Frog loop: 5 setCycle: Forward)
				(= seconds (Random 3 10))
			)
			(4
				(Frog loop: 6 setCycle: EndLoop self)
			)
			(5
				(Frog hide:)
				(client setScript: 0)
			)
		)
	)
)

(instance Heads of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Lillian cycleSpeed: 2 setCycle: EndLoop self)
			)
			(1
				(Print
					781 0
					#at 10 65
					#font 4
					#width 100
					#mode teJustCenter
					#draw
					#dispose
				)
				(= cycles 1)
			)
			(2
				(Laura cycleSpeed: 2 setCycle: EndLoop self)
				(= talkTimer 25)
				(client setScript: 0)
			)
		)
	)
)

(instance wave1 of Prop)

(instance wave2 of Prop)

(instance Lillian of Prop)

(instance Laura of Prop)

(instance Turtle of Prop)

(instance Frog of Actor)

(instance skiff of Actor)

(instance ripple of Actor)

(instance fly1 of Actor)

(instance fly2 of Actor)

(instance fly3 of Actor)

(instance fly4 of Actor)

(instance fly5 of Actor)

(instance fly6 of Actor)

(instance flyCage of Cage)
