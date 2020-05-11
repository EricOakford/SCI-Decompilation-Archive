;;; Sierra Script 1.0 - (do not remove this comment)
(script# 307)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	openPbox 0
)

(local
	local0
)
(instance openPbox of Script
	(properties)
	
	(method (init param1)
		(Load VIEW 40)
		(Load VIEW 21)
		(Load VIEW 519)
		(Load VIEW 43)
		(Load VIEW 685)
		(Load VIEW 684)
		(super init: param1)
		(HandsOff)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Print 307 0 #at -1 10 #time 6)
				(ego viewer: 0)
				(if isIndoors (ego view: 40) else (ego view: 21))
				(ego cel: 0 loop: 0 setCycle: EndLoop self)
			)
			(1
				((Sound new:) number: 58 play:)
				(ego
					view: 43
					loop: (if isIndoors 0 else 2)
					cel: 0
					setCycle: EndLoop
				)
				(aBox
					loop: (if isIndoors 1 else 3)
					cel: 0
					x: (if isIndoors (+ (ego x?) 30) else (+ (ego x?) 24))
					y: (ego y?)
					init:
					setCycle: EndLoop self
				)
				(ills
					posn: (aBox x?) (- (aBox y?) 5)
					ignoreHorizon: TRUE
					ignoreActors: TRUE
					setPri: (aBox priority?)
					init:
					setLoop: 6
					setCycle: EndLoop
				)
				(Print 307 1 #font smallFont #at 5 10 #width 120 #dispose)
			)
			(2
				(smokeFirst
					posn: (aBox x?) (- (aBox y?) 5)
					ignoreHorizon: TRUE
					ignoreActors: TRUE
					setPri: (aBox priority?)
					init:
					setLoop: 0
					moveSpeed: 1
					setCycle: EndLoop self
				)
			)
			(3
				(ills hide:)
				((ills new:)
					posn: (aBox x?) (- (aBox y?) 5)
					ignoreHorizon: TRUE
					ignoreActors: TRUE
					setPri: (aBox priority?)
					init:
					setLoop: 0
					setCycle: Forward
					setMotion: MoveTo (* 10 (Random 0 32)) -10
				)
				((ills new:)
					posn: (aBox x?) (- (aBox y?) 5)
					ignoreHorizon: TRUE
					ignoreActors: TRUE
					setPri: (aBox priority?)
					init:
					setLoop: 1
					setCycle: Forward
					setMotion: MoveTo (Random 0 320) -10
				)
				((ills new:)
					posn: (aBox x?) (- (aBox y?) 5)
					ignoreHorizon: TRUE
					ignoreActors: TRUE
					setPri: (aBox priority?)
					init:
					setLoop: 2
					setCycle: Forward
					setMotion: MoveTo (Random 0 320) -10
				)
				((ills new:)
					posn: (aBox x?) (- (aBox y?) 5)
					ignoreHorizon: TRUE
					ignoreActors: TRUE
					setPri: (aBox priority?)
					init:
					setLoop: 3
					setCycle: Forward
					setMotion: MoveTo (Random 0 320) -10
				)
				((ills new:)
					posn: (aBox x?) (- (aBox y?) 5)
					ignoreHorizon: TRUE
					ignoreActors: TRUE
					setPri: (aBox priority?)
					init:
					setLoop: 4
					setCycle: Forward
					setMotion: MoveTo (Random 0 320) -10
				)
				((ills new:)
					posn: (aBox x?) (- (aBox y?) 5)
					ignoreHorizon: TRUE
					ignoreActors: TRUE
					setPri: (aBox priority?)
					init:
					setLoop: 5
					setCycle: Forward
					setMotion: MoveTo (Random 0 320) -10
				)
				(smokeFirst
					setLoop: 1
					setCycle: Forward
					setMotion: MoveTo (aBox x?) (- (aBox y?) 20) self
				)
			)
			(4
				((smokes new:)
					posn: (smokeFirst x?) (smokeFirst y?)
					setPri: (aBox priority?)
					init:
					setLoop: 2
					setMotion: MoveTo (* 10 (Random 10 20)) -10
				)
				((smokes new:)
					posn: (smokeFirst x?) (smokeFirst y?)
					setPri: (aBox priority?)
					init:
					setLoop: 3
					moveSpeed: 1
					setMotion: MoveTo (* 10 (Random 10 20)) -10
				)
				((smokes new:)
					posn: (smokeFirst x?) (smokeFirst y?)
					setPri: (aBox priority?)
					init:
					setLoop: 4
					setMotion: MoveTo (* 10 (Random 10 20)) -10
				)
				((smokes new:)
					posn: (smokeFirst x?) (smokeFirst y?)
					setPri: (aBox priority?)
					init:
					setLoop: 5
					setMotion: MoveTo (* 10 (Random 10 20)) -10
				)
				(smokeFirst hide:)
				((ScriptID 0 6) setReal: self 10)
			)
			(5
				(ego
					loop: (if isIndoors 1 else 3)
					cycleSpeed: 2
					setCycle: EndLoop
				)
				(cls)
				((ScriptID 0 6) setReal: self 10)
			)
			(6 (= dead TRUE))
		)
	)
)

(instance aBox of Prop
	(properties
		view 519
	)
)

(instance smokeFirst of Actor
	(properties
		view 685
		illegalBits $0000
	)
	
	(method (init param1)
		(super init: param1)
		(self
			ignoreHorizon: TRUE
			ignoreActors: TRUE
			moveSpeed: 1
			setCycle: Forward
		)
	)
)

(instance smokes of Actor
	(properties
		view 685
		illegalBits $0000
	)
	
	(method (init param1)
		(super init: param1)
		(self
			ignoreHorizon: TRUE
			ignoreActors: TRUE
			moveSpeed: 1
			setCycle: Forward
		)
	)
)

(instance ills of Actor
	(properties
		view 684
		illegalBits $0000
	)
)
