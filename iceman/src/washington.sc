;;; Sierra Script 1.0 - (do not remove this comment)
(script# rgWashington)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Game)
(use System)

(public
	washington 0
)

(class washington of Region
	(properties
		beenBriefed 0
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(cond 
			((not (ego has: iEarring))
				(event claimed: FALSE)
			)
			((Said 'look<in,in/earring')
				(cond 
					((ego has: iMicrofilm)
						(Print 302 0)
					)
					((== earRingState earringOPEN)
						(self setScript: taDaScript)
					)
					((== earRingState earringSEARCHED)
						(Print 302 1)
					)
					(else
						(Print 302 2)
					)
				)
			)
			((Said 'examine,look[<at]/earring')
				(Print 302 3)
			)
			(
				(or
					(Said 'open,detach,(get<off)/top')
					(Said 'open/earring')
				)
				(if (> earRingState earringINITIAL)
					(ItIs)
				else
					(Print 302 4)
					(= earRingState earringOPEN)
				)
			)
			((Said 'get/film')
				(cond 
					((ego has: iMicrofilm)
						(Print 302 5)
					)
					((== earRingState earringSEARCHED)
						(Print 302 6)
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
						(Print 302 7)
					)
					(else
						(event claimed: FALSE)
					)
				)
			)
		)
	)
	
	(method (newRoom roomNum)
		(= keep (OneOf roomNum 17 18 19 20 21))
		(= initialized FALSE)
		(ego illegalBits: cWHITE)
		(super newRoom: &rest)
	)
)

(instance taDaScript of Script
	
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
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Print 302 8 #at -1 5)
				(taDaSnd play: self)
				(Print 302 9 #dispose #width 220 #at -1 5)
				(= seconds 6)
			)
			(1
				(Print 302 10 #dispose #width 280 #at -1 5)
			)
			(2
				(Print 302 11 #dispose #width 260 #at -1 5)
			)
			(3
				(theGame changeScore: 1)
				(= earRingState earringSEARCHED)
				(taDaSnd dispose:)
				(self dispose:)
			)
		)
	)
)

(instance taDaSnd of Sound
	(properties
		number 99
		priority 10
	)
)
