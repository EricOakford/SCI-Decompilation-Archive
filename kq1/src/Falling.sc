;;; Sierra Script 1.0 - (do not remove this comment)
(script# FALLING)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use System)

(public
	fallRight 0
	fallLeft 1
	fallSEast 2
	fallSWest 3
	fallNorth 4
	longFallBack 5
	longFallFront 6
	longFallSWest 7
	longFallLeft 8
	longFallRight 9
)

(procedure (FallingEgo theLoop)
	(HandsOff)
	(ego
		view: 66
		illegalBits: 0
		loop: theLoop
		cel: 0
		setMotion: 0
		cycleSpeed: 1
	)
)

(procedure (FallingSound)
	((ScriptID 0 21) number: 17 loop: 1 init: play:)
)

(instance fallRight of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(FallingEgo 7)
				(ego setCycle: EndLoop self)
			)
			(1
				(ego loop: 0 cel: 2 setCycle: EndLoop self)
			)
			(2
				(FallingSound)
				(ego loop: 3 cel: 0 setCycle: EndLoop self)
			)
			(3
				(self setScript: fellOffTree)
			)
		)
	)
)

(instance fallLeft of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(FallingEgo 8)
				(ego setCycle: EndLoop self)
			)
			(1
				(ego loop: 1 cel: 2 setCycle: EndLoop self)
			)
			(2
				(FallingSound)
				(ego loop: 4 cel: 0 setCycle: EndLoop self)
			)
			(3
				(self setScript: fellOffTree)
			)
		)
	)
)

(instance fallSEast of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(FallingEgo 0)
				(ego setCycle: EndLoop self)
			)
			(1
				(FallingSound)
				(ego loop: 3 cel: 0 setCycle: EndLoop self)
			)
			(2
				(self setScript: fellOffTree)
			)
		)
	)
)

(instance fallSWest of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(FallingEgo 1)
				(ego setCycle: EndLoop self)
			)
			(1
				(FallingSound)
				(if (== curRoomNum 67)
					(ego loop: 4 cel: 0 setPri: 10 setCycle: EndLoop self)
				else
					(ego loop: 4 cel: 0 setCycle: EndLoop self)
				)
			)
			(2
				(self setScript: fellOffTree)
			)
		)
	)
)

(instance fallNorth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(FallingEgo 2)
				(ego setCycle: EndLoop self)
			)
			(1
				(FallingSound)
				(ego
					setPri: (ego priority?)
					posn: (- (ego x?) 8) (- (ego y?) 22)
					loop: 3
					cel: 0
					setCycle: EndLoop self
				)
			)
			(2
				(self setScript: fellOffTree)
			)
		)
	)
)

(instance longFallBack of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(FallingEgo 2)
				(ego setCycle: EndLoop self)
			)
			(1
				(FallingSound)
				(ego setPri: 1 loop: 5 cel: 0 setCycle: EndLoop self)
			)
			(2
				(self setScript: fellOffTree)
			)
		)
	)
)

(instance longFallFront of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(FallingEgo 1)
				(ego setCycle: EndLoop self)
			)
			(1
				(FallingSound)
				(ego loop: 5 cel: 0 setCycle: EndLoop self)
			)
			(2
				(self setScript: fellOffTree)
			)
		)
	)
)

(instance longFallSWest of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(FallingEgo 2)
				(ego setCycle: EndLoop self)
			)
			(1
				(FallingSound)
				(ego setPri: 1 loop: 5 cel: 0 setCycle: EndLoop self)
			)
			(2
				(self setScript: fellOffTree)
			)
		)
	)
)

(instance longFallLeft of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(FallingEgo 8)
				(ego setCycle: EndLoop self)
			)
			(1
				(FallingSound)
				(ego setPri: 1 loop: 5 cel: 0 setCycle: EndLoop self)
			)
			(2
				(self setScript: fellOffTree)
			)
		)
	)
)

(instance longFallRight of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(FallingEgo 7)
				(ego setCycle: EndLoop self)
			)
			(1
				(FallingSound)
				(ego setPri: 1 loop: 6 cel: 0 setCycle: EndLoop self)
			)
			(2
				(self setScript: fellOffTree)
			)
		)
	)
)

(instance fellOffTree of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (Btst fInvisible)
					(Print 613 0)
					(theGame changeScore: -3)
				)
				(self cue:)
			)
			(1
				((ScriptID 0 21) fade:)
				(EgoDead {WHOOPS!})
				(self dispose:)
			)
		)
	)
)
