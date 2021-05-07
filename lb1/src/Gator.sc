;;; Sierra Script 1.0 - (do not remove this comment)
(script# 405)
(include game.sh)
(use Main)
(use Intrface)
(use DCIcon)
(use Chase)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Gator 0
)

(local
	local0
)
(instance Gator of Region
	
	(method (init)
		(super init:)
		(Load SOUND 10)
		(if
			(= local0
				(if (and howFast (> (ego y?) 161))
					(== (Random 1 3) 2)
				)
			)
			(if (< (ego x?) 160)
				(gatorHead setLoop: 0 posn: -28 173)
				(gatorBody setLoop: 2 posn: -40 173)
			else
				(gatorHead setLoop: 1 posn: 349 173)
				(gatorBody setLoop: 3 posn: 361 173)
			)
			(gatorHead init: hide:)
			(gatorBody init: hide:)
		)
	)
	
	(method (doit)
		(if (cast contains: gatorHead)
			(gatorHead
				posn:
					(if (& (gatorHead loop?) 1)
						(- (gatorBody x?) 12)
					else
						(+ (gatorBody x?) 12)
					)
					(gatorBody y?)
			)
		)
		(if
			(and
				local0
				(or
					(and (gatorHead loop?) (< (ego x?) 275))
					(and (not (gatorHead loop?)) (> (ego x?) 55))
				)
			)
			(gatorHead setCycle: Forward show:)
			(gatorBody setCycle: Walk setScript: GrabEgo show:)
			(= local0 0)
		)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return TRUE))
		(return
			(if
				(and
					(== (event type?) saidEvent)
					(Said 'examine/alligator')
				)
				(if (cast contains: gatorBody)
					(Print 405 0)
				else
					(DontSee)
				)
			else
				0
			)
		)
	)
)

(instance GrabEgo of Script
	
	(method (changeState newState &tmp myIconLastCel)
		(switch (= state newState)
			(0
				(gatorBody setMotion: Chase ego 35 self)
			)
			(1
				(HandsOff)
				(gatorBody stopUpd:)
				(gatorHead
					loop: (+ (gatorHead loop?) 4)
					cel: 0
					setCycle: EndLoop self
				)
				(ego hide:)
			)
			(2
				(scream play:)
				(gatorHead
					loop: (+ (gatorHead loop?) 2)
					cel: 0
					setCycle: EndLoop self
				)
			)
			(3
				(gatorHead
					loop: (+ (gatorHead loop?) 2)
					cel: 0
					setCycle: Forward
				)
				(gatorBody
					startUpd:
					setMotion:
						MoveTo
						(if (& (gatorHead loop?) $0001) -80 else 400)
						(gatorBody y?)
						self
				)
			)
			(4
				(scream dispose:)
				(= myIconLastCel (myIcon lastCel:))
				(= cIcon myIcon)
				(= deathLoop 0)
				(= deathCel myIconLastCel)
				(= cyclingIcon TRUE)
				(EgoDead 405 1)
				(client setScript: 0)
			)
		)
	)
)

(instance gatorHead of Actor
	(properties
		view 208
		signal ignrAct
	)
)

(instance gatorBody of Actor
	(properties
		yStep 4
		view 208
		signal ignrAct
		xStep 6
	)
)

(instance myIcon of DCIcon
	(properties
		view 650
		cycleSpeed 16
	)
	
	(method (init)
		((= cycler (EndLoop new:)) init: self)
	)
)

(instance scream of Sound
	(properties
		number 53
		priority 15
	)
)
