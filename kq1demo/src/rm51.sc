;;; Sierra Script 1.0 - (do not remove this comment)
(script# rDragonCave)
(include game.sh)
(use Main)
(use Intrface)
(use LoadMany)
(use DPath)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm51 0
)

(local
	local0
)
(instance rm51 of Room
	(properties
		picture rDragonCave
		style WIPELEFT
		east dmDragon
	)
	
	(method (init)
		(LoadMany VIEW
			vEgoBurned
			vEgoLookAround
			vDragonSmoke
			vDragonAGI
			vDragonBreath
			vDragonCaveStuff
			vDragonHead
			vEgoDucking
			vEgo
		)
		(super init:)
		(ego init: x: 218 y: 140)
		(NormalEgo)
		(HandsOff)
		(dragHead init:)
		(dragonBody init: setPri: 1 ignoreActors: TRUE stopUpd:)
		(if (!= howFast slow)
			(smoke init: setCycle: Forward)
		)
		(oldDragon init: setCycle: Walk)
		(self setScript: removeSmallDragon)
	)
)

(instance removeSmallDragon of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(oldDragon setMotion: DPath 10 131 -25 131 self)
				(dragHead setCycle: EndLoop self)
				(smoke hide:)
			)
			(1
				(DisplayNewGraphics)
				(dragHead stopUpd:)
				(smoke posn: 119 98 show:)
				(= cycles 1)
			)
			(2
				(ego
					view: vEgoBurned
					setLoop: loopW
					setCycle: 0
				)
				(= cycles 6)
			)
			(3
				(ego setCycle: Walk setMotion: MoveTo 255 138)
				(Print 51 0
					#at 25 20
					#width 260
					#mode teJustCenter
					#dispose
				)
			)
			(4
				(ego view: vEgo)
				(= cycles 1)
			)
			(5
				(cls)
				(self dispose:)
				(curRoom setScript: flameEgo)
			)
		)
	)
)

(instance flameEgo of Script

	(method (doit)
		(super doit:)
		(cond 
			(
				(and
					(== state 1)
					(== (ego view?) vEgo)
					(> (fireBall x?) 120)
				)
				(ego
					view: vEgoDucking
					setLoop: loopW
					setCycle: EndLoop
				)
			)
			((and (> (fireBall x?) 255) (== (ego view?) vEgoDucking))
				(ego
					setLoop: loopW
					setCycle: CycleTo BegLoop 0
				)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(smoke hide:)
				(dragHead stopUpd:)
				(fireHead
					init:
					posn: 92 105
					ignoreActors:
					cycleSpeed: 2
					cel: 0
					setPri: 12
					setCycle: EndLoop self
				)
			)
			(1
				(fireHead hide:)
				(fireBall
					init:
					setLoop: (if (< (ego y?) 142) 3 else 2)
					ignoreActors:
					ignoreHorizon:
					setPri: 4
					illegalBits: 0
					setCycle: Forward
				)
				(fireBall posn: 126 102)
				(fireBall
					xStep: 12
					moveSpeed: 0
					setMotion: MoveTo 350 (- (ego y?) 40) self
				)
			)
			(2
				(ego
					view: vEgoLookAround
					loop: 2
					setCycle: CycleTo 1 1
					self
				)
			)
			(3
				(fireHead show: setCycle: EndLoop self)
			)
			(4
				(fireHead hide:)
				(fireBall
					init:
					setLoop: (if (< (ego y?) 142) 3 else 2)
					ignoreActors:
					ignoreHorizon:
					setPri: 4
					illegalBits: 0
					setCycle: Forward
				)
				(fireBall posn: 126 102)
				(fireBall
					xStep: 12
					moveSpeed: 0
					setMotion: MoveTo (+ (ego x?) 5) (- (ego y?) 20) self
				)
			)
			(5
				(fireBall hide:)
				(ego
					view: vEgoLookAround
					setLoop: 3
					setCel: 0
				)
				(= cycles 6)
			)
			(6
				(ego
					view: vEgoBurned
					setLoop: 2
					setCycle: Walk
					xStep: 9
					moveSpeed: 0
					setMotion: DPath 10 128 -40 128 self
				)
			)
			(7
				((ScriptID 0 6) loop: 1 fade:)
				(= cycles 1)
			)
			(8
				(curRoom newRoom: dmBird)
			)
		)
	)
)

(instance raiseHead of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(dragHead setCycle: EndLoop self)
				(smoke hide:)
			)
			(1
				(dragHead stopUpd:)
				(smoke posn: 119 98 show:)
				(DisposeScript raiseHead)
			)
		)
	)
)

(instance dragonBody of Actor
	(properties
		x 117
		y 126
		view vDragonCaveStuff
		loop 3
	)
)

(instance dragonHead of Actor
	(properties
		x 36
		y 87
		view vDragonHead
		cel 3
		priority 9
		signal (| ignrHrz fixedLoop blocked fixedLoop stopUpdOn) ;$2c11
		illegalBits $0000
	)
)

(instance oldDragon of Actor
	(properties
		x 160
		y 143
		view vDragonAGI
		loop 1
		cel 2
	)
)

(instance smoke of Actor
	(properties
		x 114
		y 93
		view vDragonSmoke
		loop 1
		cel 2
		priority 10
		signal fixPriOn
	)
)

(instance puff of Actor
	(properties
		view vDragonCaveStuff
		loop 1
		moveSpeed 1
	)
)

(instance fireHead of Prop
	(properties
		view vDragonHead
		loop 1
		signal notUpd
		cycleSpeed 1
	)
)

(instance fireBall of Actor
	(properties
		yStep 6
		view vDragonSmoke
		xStep 8
	)
)

(instance dragHead of Prop
	(properties
		x 41
		y 90
		view vDragonHead
		cycleSpeed 1
	)
)
