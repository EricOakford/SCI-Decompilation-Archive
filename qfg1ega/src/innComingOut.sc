;;; Sierra Script 1.0 - (do not remove this comment)
(script# 168)
(include game.sh)
(use Main)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	comingOut 0
)

(instance comingOut of Script
	(method (dispose)
		(super dispose:)
		(DisposeScript 168)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 301 6) stop: number: 50 play:)
				(HandsOff)
				(if (or (== foodOrdered mealORDERED) (== teaOrdered mealORDERED))
					((ScriptID 301 2) view: vSheemaFood startUpd:)
				)
				((ScriptID 301 2) setMotion: MoveTo 192 99 self)
			)
			(1
				((ScriptID 301 2) loop: 2)
				((ScriptID 301 5) loop: 2 cel: 0 setCycle: EndLoop self)
			)
			(2
				((ScriptID 301 2) setMotion: MoveTo 192 122 self)
				(Bset fShemaAsks)
			)
			(3
				((ScriptID 301 5) setCycle: BegLoop self)
				((ScriptID 301 2) setMotion: MoveTo 130 144)
			)
			(4
				((ScriptID 301 5) loop: 1 cel: 0)
				(= cycles 2)
			)
			(5
				((ScriptID 301 5) cel: 2)
				(= cycles 2)
			)
			(6
				((ScriptID 301 5) cel: 0)
				(= cycles 1)
			)
			(7
				((ScriptID 301 5) cel: 1)
				(= cycles 1)
			)
			(8
				((ScriptID 301 5) cel: 0 stopUpd:)
				(= cycles 14)
			)
			(9
				((ScriptID 301 2) loop: 2 ignoreActors: 0)
				(= cycles 2)
			)
			(10
				(if (and (!= foodOrdered mealORDERED) (!= teaOrdered mealORDERED))
					(HighPrint 168 0)
					;"I am Shema.  Allow me to serve you, Wanderer from Afar.  Do you wish food or drink?"
					(= yesNoTimer 50)
					(HandsOn)
					(User canControl: FALSE)
				else
					(HighPrint 168 1)
					;"I bring you that which you ordered.  May it please and satisfy you."
					(cond 
						((== foodOrdered mealORDERED)
							(= foodOrdered mealATTABLE)
							(= teaOrdered mealATTABLE)
						)
						((== teaOrdered mealORDERED)
							(= teaOrdered mealATTABLE)
						)
					)
					(self cue:)
				)
			)
			(11
				((ScriptID 301 2) view: vSheema)
				((View new:)
					view: vInn
					loop: 5
					cel: 2
					posn: 130 131
					init:
					ignoreActors:
					setPri: 12
					stopUpd:
				)
				(if (== foodOrdered mealATTABLE)
					((ScriptID 301 7)
						ignoreActors:
						setPri: 12
						init:
						stopUpd:
					)
				)
				(HighPrint 168 2)
				;You thank Shema and pay her.
				(Bset fShemaBringsOrder)
				(= cycles 5)
			)
			(12
				((ScriptID 301 2) setScript: (ScriptID 169 0))
			)
		)
	)
)
