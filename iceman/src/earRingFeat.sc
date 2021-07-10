;;; Sierra Script 1.0 - (do not remove this comment)
(script# 317)
(include game.sh)
(use Main)
(use Intrface)
(use ChangeScriptState)
(use Sound)
(use System)

(public
	earRingFeat 0
)

(instance earRingFeat of Script

	(method (doit)
		(super doit:)
		(if (and (< state 3) (== (taDaSnd prevSignal?) -1))
			(self cue:)
		)
	)
	
	(method (dispose)
		(cls)
		(HandsOn)
		(super dispose:)
		(DisposeScript 317)
		(DisposeScript 822)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Print 317 1 #at -1 5)
				(taDaSnd play: self)
				(Print 317 8 #dispose #width 220 #at -1 5)
				(= seconds 6)
			)
			(1
				(Print 317 9 #dispose #width 280 #at -1 5)
			)
			(2
				(Print 317 10 #dispose #width 260 #at -1 5)
			)
			(3
				(theGame changeScore: 1)
				(= earRingState earringSEARCHED)
				(taDaSnd dispose:)
				(self dispose:)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look<in,in/earring')
				(cond 
					((ego has: iMicrofilm)
						(Print 317 0)
					)
					((== earRingState earringOPEN)
						(ChangeScriptState theGame self)
					)
					((== earRingState earringSEARCHED)
						(Print 317 1)
					)
					(else
						(Print 317 2)
					)
				)
			)
			((Said 'examine,look[<at]/earring')
				(Print 317 3)
			)
			(
				(or
					(Said 'open,detach,(get<off)/top')
					(Said 'open/earring')
				)
				(if (> earRingState earringINITIAL)
					(ItIs)
				else
					(Print 317 4)
					(= earRingState earringOPEN)
				)
			)
			((Said 'get/film')
				(cond 
					((ego has: iMicrofilm)
						(Print 317 5)
					)
					((== earRingState earringSEARCHED)
						(Print 317 6)
						(ego get: iMicrofilm)
					)
					(else
						(event claimed: FALSE)
					)
				)
			)
			((Said 'look/film')
				(cond 
					((ego has: iMicrofilm)
						(event claimed: FALSE)
					)
					((== earRingState earringSEARCHED)
						(Print 317 7)
					)
					(else
						(event claimed: FALSE)
					)
				)
			)
		)
		(if (not client)
			(DisposeScript 317)
		)
	)
)

(instance taDaSnd of Sound
	(properties
		number 99
		priority 10
	)
)
