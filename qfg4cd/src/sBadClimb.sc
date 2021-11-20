;;; Sierra Script 1.0 - (do not remove this comment)
(script# 805)
(include sci.sh)
(use Main)
(use Motion)
(use System)

(public
	sBadClimb 0
)

(instance sBadClimb of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					view: 7
					posn: 242 144
					setLoop: 1 1
					cycleSpeed: 0
					setCel: 0
					setCycle: End self
				)
			)
			(1
				(ego setLoop: 0 1)
				(ego
					setCel: (ego lastCel:)
					cycleSpeed: 8
					setCycle: Beg self
				)
			)
			(2
				(messager say: 5 33 5 0 self)
			)
			(3
				((ScriptID 809 2) posn: 230 130)
				(= ticks 6)
			)
			(4
				((ScriptID 809 2) posn: 230 138)
				(= ticks 6)
			)
			(5
				((ScriptID 809 2) hide: dispose:)
				(ego
					normalize:
					setPri: 152
					cycleSpeed: register
					setMotion: MoveTo 150 165 self
				)
			)
			(6
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)
