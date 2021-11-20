;;; Sierra Script 1.0 - (do not remove this comment)
(script# 46)
(include sci.sh)
(use Main)
(use Array)
(use Sound)
(use Motion)
(use System)

(public
	staffScript 0
)

(local
	local0
)
(instance staffScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local0 (IntArray with: 0 1 0 4 0 1 4 5))
				(theGame handsOff:)
				(if (ego looper?)
					((ego looper?) dispose:)
					(ego looper: 0)
				)
				(ego
					setMotion: 0
					view: 20
					setLoop: 0
					setLoop: (local0 at: (ego loop?))
					setCel: 0
					setCycle: End self
				)
				(sFx number: 934 play:)
			)
			(1
				(ego loop: (+ (ego loop?) 2) cel: 0 setCycle: End self)
			)
			(2
				(if (== curRoomNum 580)
					(curRoom cue:)
				else
					(theGame handsOn:)
				)
				(self dispose:)
			)
		)
	)
)

(instance sFx of Sound
	(properties)
)
