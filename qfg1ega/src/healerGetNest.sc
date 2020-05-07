;;; Sierra Script 1.0 - (do not remove this comment)
(script# 235)
(include game.sh)
(use Main)
(use Motion)
(use System)

(public
	climbIn 0
	intro54 1
	rockHitsIt 2
	youMissed 3
)

(instance climbIn of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 235)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Bset FLAG_277)
				(ego
					setLoop: 2
					setPri: 4
					posn: 130 100
					setMotion: MoveTo 130 87 self
				)
			)
			(1
				(ego
					setLoop: -1
					setPri: -1
					setMotion: MoveTo 130 100 self
				)
			)
			(2
				(HandsOn)
				(Bclr FLAG_277)
				(NormalEgo)
				(client setScript: 0)
			)
		)
	)
)

(instance intro54 of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 235)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 2))
			(1
				(HighPrint 235 0)
				;The bright smell of fresh herbs mingles with the aroma of wood smoke as you near a hut by the side of the road.
				(client setScript: 0)
			)
		)
	)
)

(instance rockHitsIt of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 235)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 54 1)
					setMotion: MoveTo ((ScriptID 54 2) x?) ((ScriptID 54 2) y?) self
				)
			)
			(1
				((ScriptID 54 1)
					setMotion: MoveTo -10 (- ((ScriptID 54 2) y?) 40) self
				)
				((ScriptID 54 2) setScript: (ScriptID 54 4))
			)
			(2 ((ScriptID 54 1) dispose:))
		)
	)
)

(instance youMissed of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 235)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 54 1)
					setMotion: MoveTo ((ScriptID 54 2) x?) ((ScriptID 54 2) y?) self
				)
			)
			(1
				((ScriptID 54 1)
					setMotion: MoveTo -10 (- ((ScriptID 54 1) y?) 40) self
				)
			)
			(2 (self dispose:))
		)
	)
)
