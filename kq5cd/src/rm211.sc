;;; Sierra Script 1.0 - (do not remove this comment)
(script# 211)
(include sci.sh)
(use Main)
(use Intrface)
(use KQ5Room)
(use Motion)
(use Actor)
(use System)

(public
	rm211 0
)

(instance rm211 of KQ5Room
	(properties
		picture 14
		horizon 90
	)
	
	(method (init)
		(super init:)
		(HandsOff)
		(trees init:)
		(ego init:)
		(scorpion init:)
		(roomScript register: (if (< (ego x?) 160) 1 else 0))
		(self setScript: roomScript)
	)
	
	(method (doit &tmp temp0)
		(if script (script doit:))
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			(script (return))
		)
	)
)

(instance roomScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
		(and (== state 4) (== (theMusic prevSignal?) -1))
			(self cue:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(scorpion
					setLoop: (+ 8 register)
					x: (if register (+ (ego x?) 45) else (- (ego x?) 45))
					y: (- (ego y?) 3)
					setCycle: Fwd
					setMotion: MoveTo (ego x?) (+ (ego y?) 4) self
				)
			)
			(1
				(scorpion
					setLoop: (+ 12 register)
					cel: 0
					setCycle: End self
				)
				(theMusic number: 39 loop: 1 vol: 127 play:)
			)
			(2
				(scorpion setCycle: Beg self)
			)
			(3
				(ego view: 358 cycleSpeed: 2 normal: 0 setCycle: End self)
				((ego head?) hide:)
				(scorpion
					setLoop: (+ 8 (not register))
					setCycle: Fwd
					setMotion: MoveTo (if register 330 else -10) (+ (scorpion y?) 30)
				)
			)
			(4 0)
			(5
				(= deathMessage 760)
				(EgoDead 244)
			)
		)
	)
)

(instance scorpion of Actor
	(properties
		view 352
		signal $6000
		illegalBits $0000
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (MousedOn self event))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(proc0_29 762)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(proc0_29 761)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance trees of Prop
	(properties
		x 294
		y 53
		view 352
		cel 3
		priority 3
		signal $0001
	)
)
