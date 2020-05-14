;;; Sierra Script 1.0 - (do not remove this comment)
(script# 41)
(include game.sh)
(use Main)
(use Intrface)
(use Jump)
(use Motion)
(use Game)
(use System)

(public
	rm41 0
)

(local
	egoPri
	jumpY
	egoX
	egoY
	[str 44]
	[deadStr 22]
)
(instance rm41 of Region
	(properties)
	
	(method (init)
		(Load SOUND 4)
		(if playingAsPatti
			(Load VIEW 813)
		else
			(Load VIEW 713)
		)
		(super init:)
		(self setScript: FallScript)
	)
	
	(method (notify param1 param2)
		(= egoPri param1)
		(= jumpY param2)
		(FallScript changeState: 1)
	)
)

(instance FallScript of Script
	(properties)
	
	(method (doit)
		(if (and debugging (== currentStatus 0))
			(= egoX (ego x?))
			(= egoY (ego y?))
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0)
			(1
				(HandsOff)
				(soundFX number: 4 loop: 1 play:)
				(Print
					(Format @str 41 0 expletive)
					#at -1 10
					#dispose
				)
				(= currentStatus egoFALLING)
				(ego
					view: (if playingAsPatti 813 else 713)
					setLoop: 0
					cel: 0
					illegalBits: 0
					ignoreActors:
					setPri: egoPri
					setCycle: EndLoop self
				)
			)
			(2 (ego setMotion: theJump))
			(3
				(cls)
				(if debugging
					(NormalEgo)
					(= currentStatus egoNORMAL)
					(ego posn: egoX egoY)
				else
					(theGame setScript: (ScriptID 40))
					((ScriptID 40)
						caller: (+ 1 (ego view?))
						register: (Format @str 41 1 currentEgo)
						next: (Format @deadStr 41 2)
					)
				)
			)
		)
	)
)

(instance theJump of Jump
	(properties)
	
	(method (init)
		(super init: ego FallScript)
		(self yStep: 5 y: jumpY)
	)
)
