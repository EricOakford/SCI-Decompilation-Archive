;;; Sierra Script 1.0 - (do not remove this comment)
(script# FALLING)
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
	[msgBuf 44]
	[titleBuf 22]
)
(instance rm41 of Region
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
	
	(method (notify thePri theY)
		(= egoPri thePri)
		(= jumpY theY)
		(FallScript changeState: 1)
	)
)

(instance FallScript of Script
	(method (doit)
		(if (and debugging (== currentStatus egoNORMAL))
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
					(Format @msgBuf 41 0 expletive)
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
			(2
				(ego setMotion: theJump)
			)
			(3
				(cls)
				(if debugging
					(NormalEgo)
					(= currentStatus egoNORMAL)
					(ego posn: egoX egoY)
				else
					(theGame setScript: (ScriptID DYING))
					((ScriptID DYING)
						caller: (+ 1 (ego view?))
						register: (Format @msgBuf 41 1 currentEgo)
						next: (Format @titleBuf 41 2)
					)
				)
			)
		)
	)
)

(instance theJump of Jump
	(method (init)
		(super init: ego FallScript)
		(self yStep: 5 y: jumpY)
	)
)
