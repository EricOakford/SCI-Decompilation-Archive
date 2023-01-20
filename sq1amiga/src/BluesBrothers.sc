;;; Sierra Script 1.0 - (do not remove this comment)
(script# 300)
(include sci.sh)
(use Main)
(use Intrface)
(use Osc)
(use Motion)
(use Actor)
(use System)

(public
	bluesBrothers 0
	jakeBlue 1
	elwoodBlue 2
)

(instance bluesBrothers of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client setLoop: register cel: 0 setCycle: Fwd)
				(= cycles (Random 45 150))
			)
			(1 (client setCycle: End self))
			(2
				(client
					setLoop: (+ register 1)
					cel: 0
					setCycle: End self
				)
			)
			(3
				(if (not register)
					(theMusic send: 5 78 0)
				else
					(theMusic send: 4 78 0)
				)
				(client
					setLoop: (+ register 2)
					cel: 0
					setCycle: Osc (Random 2 15) self
				)
			)
			(4
				(if (not register)
					(theMusic send: 5 78 1)
				else
					(theMusic send: 4 78 1)
				)
				(= state -1)
				(client
					setLoop: (+ register 1)
					cel: (if (not register) 2 else 3)
					setCycle: Beg self
				)
			)
		)
	)
)

(instance jakeBlue of Prop
	(properties
		x 136
		y 95
		description {humanoids}
		lookStr {There are a couple of non-galactic looking humanoids cranking out some unfamiliar sounding tunes. They seem interested solely in the music they are performing.}
		view 434
		priority 6
		signal $4810
		cycleSpeed 4
		detailLevel 2
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(3 (Print 300 0))
			(11 (Print 300 1))
			(12 (Print 300 2))
			(5 (Print 300 3))
			(4
				(switch theItem
					(10 (Print 300 4))
					(0 (Print 300 5))
					(15 (Print 300 6))
					(2 (Print 300 7))
					(4 (Print 300 8))
					(5 (Print 300 9))
					(11 (Print 300 10))
					(17 (Print 300 11))
					(18 (Print 300 11))
					(else 
						(super doVerb: theVerb theItem)
					)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance elwoodBlue of Prop
	(properties
		x 163
		y 90
		description {humanoids}
		lookStr {There are a couple of non-galactic looking humanoids cranking out some unfamiliar sounding tunes. They seem interested solely in the music they are performing.}
		view 434
		loop 3
		priority 6
		signal $4810
		cycleSpeed 4
		detailLevel 2
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(3 (Print 300 12))
			(11 (Print 300 1))
			(12 (Print 300 2))
			(5 (Print 300 13))
			(4
				(switch theItem
					(10 (Print 300 4))
					(0 (Print 300 5))
					(15 (Print 300 6))
					(2 (Print 300 7))
					(4 (Print 300 8))
					(5 (Print 300 9))
					(11 (Print 300 10))
					(17 (Print 300 11))
					(18 (Print 300 11))
					(else 
						(super doVerb: theVerb theItem)
					)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)
