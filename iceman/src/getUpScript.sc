;;; Sierra Script 1.0 - (do not remove this comment)
(script# 394)
(include game.sh)
(use Main)
(use Motion)
(use System)

(public
	getUpScript 0
)

(instance getUpScript of Script
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 394)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego view: 325 loop: 0 setCel: 16 setCycle: BegLoop self)
			)
			(1
				((ScriptID 25 4) init:)
				(ego posn: 187 80 view: 232 cel: 3 heading: 225 loop: 6)
				((ego looper?) doit: ego 225 self)
			)
			(2
				(ego loop: 5 setCycle: Walk setMotion: MoveTo 180 98 self)
			)
			(3 (self dispose:) (HandsOn))
		)
	)
)
