;;; Sierra Script 1.0 - (do not remove this comment)
(script# 104)
(include game.sh)
(use Main)
(use Sound)
(use Motion)
(use System)

(public
	CastCalm 0
)

(local
	egoSignal
	egoPriority
	egoIllegalBits
	newSound
)
(procedure (CastCalm param1 param2)
	(switch argc
		(0 (ego setScript: castCalm))
		(1 (param1 setScript: castCalm))
		(else 
			(param1 setScript: castCalm param2)
		)
	)
)

(instance castCalm of Script
	(properties)
	
	(method (dispose)
		(newSound stop: dispose:)
		(NormalEgo)
		(HandsOn)
		(ego
			loop: 2
			priority: egoPriority
			illegalBits: egoIllegalBits
			signal: egoSignal
		)
		(super dispose:)
		(DisposeScript 104)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((= newSound (Sound new:))
					number: (SoundFX 39)
					priority: 6
					init:
				)
				(= egoSignal (ego signal?))
				(= egoPriority (ego priority?))
				(= egoIllegalBits (ego illegalBits?))
				(HandsOff)
				(NormalEgo)
				(ego setLoop: 2)
				(= cycles 1)
			)
			(1
				(ego
					view: vEgoMagicDetect
					setLoop: 1
					setCel: 0
					setPri: (ego priority?)
					cycleSpeed: 2
					setCycle: EndLoop self
				)
				(newSound play:)
			)
			(2
				(TimePrint 6 104 0)
				;Suddenly a feeling of peace and tranquility permeates the area.
				(= seconds 6)
			)
			(3 (ego setCycle: BegLoop self))
			(4 (self dispose:))
		)
	)
)
