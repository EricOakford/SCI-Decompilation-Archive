;;; Sierra Script 1.0 - (do not remove this comment)
(script# 28)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm28 0
)

(local
	local0
	shore
	seagull
	sparkle
)
(instance theSound of Sound
	(properties
		number 116
	)
)

(instance rm28 of Room
	(properties
		picture 28
		horizon 1
	)
	
	(method (init)
		(Load VIEW 239)
		(Load SOUND 116)
		(theSound init:)
		(super init:)
		((= sparkle (Prop new:))
			view: 815
			setLoop: 0
			setPri: 15
			posn: 222 110
			init:
			hide:
			setScript: sparkleScript
		)
		((= shore (Actor new:))
			view: 239
			setLoop: 1
			setPri: 1
			posn: 55 37
			setStep: 1 1
			moveSpeed: 2
			init:
		)
		((= seagull (Actor new:))
			view: 239
			setLoop: 0
			setPri: 4
			posn: 222 918
			ignoreActors:
			init:
			setCycle: Forward
		)
		(HandsOff)
		(= currentStatus egoBoardedShip)
		(self setScript: rm28Script)
	)
)

(instance rm28Script of Script
	(properties)
	
	(method (doit)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theSound play: self)
				(shore setMotion: MoveTo -63 38 self)
				(= seconds 5)
			)
			(1
				(Print 28 0)
				(Print 28 1)
				(Print 28 2 #at -1 152)
				(= seconds 60)
			)
			(2 (= seconds 2))
			(3
				(seagull posn: 222 18 setMotion: MoveTo -23 19 self)
				(= seconds 60)
			)
			(5
				(IncrementGamePhase phaseSHIP 9 30)
				(curRoom newRoom: 31)
			)
		)
	)
)

(instance sparkleScript of Script
	(properties)
	
	(method (changeState newState &tmp theX theY)
		(switch (= state newState)
			(0 (= cycles (Random 3 10)))
			(1
				(switch (Random 1 10)
					(1 (= theX 212) (= theY 47))
					(2 (= theX 269) (= theY 46))
					(3 (= theX 193) (= theY 13))
					(4 (= theX 239) (= theY 99))
					(5 (= theX 238) (= theY 104))
					(6 (= theX 198) (= theY 116))
					(7 (= theX 318) (= theY 112))
					(8 (= theX 43) (= theY 120))
					(9 (= theX 163) (= theY 155))
					(else 
						(= theX 169)
						(= theY 8)
					)
				)
				(sparkle
					posn: theX theY
					cel: 0
					cycleSpeed: (Random 0 2)
					show:
					setCycle: EndLoop self
				)
			)
			(2
				(sparkle hide:)
				(self changeState: 0)
			)
		)
	)
)
