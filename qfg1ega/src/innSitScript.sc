;;; Sierra Script 1.0 - (do not remove this comment)
(script# 170)
(include game.sh)
(use Main)
(use Motion)
(use User)
(use System)

(public
	sitScript 0
)

(instance sitScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(cond 
			((> serveFoodCountdown 1) (-- serveFoodCountdown))
			((== serveFoodCountdown 1)
				(= serveFoodCountdown 0)
				(if (not ((ScriptID 301 2) script?))
					((ScriptID 301 2) setScript: (ScriptID 168 0))
				)
			)
		)
	)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 170)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (Btst EATEN_AT_INN) (Bclr EATEN_AT_INN) else (self cue:))
			)
			(1
				(HandsOff)
				((ScriptID 301 8) ignoreActors:)
				(if (< (ego x?) 158)
					(ego illegalBits: 0 setMotion: MoveTo 145 (ego y?) self)
				else
					(ego illegalBits: 0 setMotion: MoveTo (ego x?) 157)
					(= cycles 5)
				)
			)
			(2
				(if (> (ego x?) 145)
					(ego setMotion: MoveTo 145 (ego y?) self)
				else
					(self cue:)
				)
			)
			(3
				(ego setMotion: MoveTo (ego x?) 154)
				(= cycles 2)
			)
			(4
				((ScriptID 301 8) posn: 156 154)
				(= cycles 3)
			)
			(5
				(ego ignoreActors: hide:)
				((ScriptID 301 8)
					setLoop: 6
					setCel: 0
					setPri: 12
					posn: 141 154
					ignoreActors: 0
				)
				(= cycles 2)
			)
			(6
				((ScriptID 301 8) setCycle: EndLoop)
				(Bset HERO_SITTING)
				(= cycles 15)
			)
			(7
				(cond 
					((not (cast contains: (ScriptID 301 2)))
						((ScriptID 301 2) init:)
						(cast delete: (ScriptID 301 2))
						(cast addToFront: (ScriptID 301 2))
					)
					(
						(and
							(not (Btst SHEMA_ASKS_ORDER))
							(not ((ScriptID 301 2) script?))
						)
						((ScriptID 301 2) setScript: (ScriptID 168 0))
					)
				)
			)
			(9
				(User canInput: FALSE)
				((ScriptID 301 8) setCycle: BegLoop self)
			)
			(10
				((ScriptID 301 8)
					setLoop: 5
					setCel: 4
					setPri: 6
					posn: 141 154
					stopUpd:
				)
				(ego loop: 1 show:)
				(= cycles 3)
			)
			(11
				(ego setMotion: MoveTo (ego x?) 160 self)
			)
			(12
				((ScriptID 301 8) setPri: -1)
				(Bclr HERO_SITTING)
				(= foodOrdered mealNOTHING)
				(= teaOrdered mealNOTHING)
				(HandsOn)
				(ego illegalBits: cWHITE ignoreActors: 0 setScript: 0)
			)
		)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(saidEvent
				(if (or (Said 'stand') (Said 'get<up'))
					(cond 
						(
							(and
								(Btst SHEMA_ASKS_ORDER)
								(< ((ScriptID 301 2) distanceTo: ego) 30)
							)
							(self changeState: 8)
							((ScriptID 301 2) setScript: (ScriptID 169 0) self)
						)
						((or (== foodOrdered mealORDERED) (== teaOrdered mealORDERED))
							(HighPrint 170 0)
							;You should wait for Shema to bring you what you ordered.
							)
						((== foodOrdered mealATTABLE)
							(HighPrint 170 1)
							;Eat your meal first.
							)
						((== teaOrdered mealATTABLE)
							(HighPrint 170 2)
							;Eat your meal first.
							)
						(else (self changeState: 9))
					)
				)
			)
		)
	)
)
