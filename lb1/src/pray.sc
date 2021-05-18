;;; Sierra Script 1.0 - (do not remove this comment)
(script# 279)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	pray 0
)
(synonyms
	(celie person girl)
)

(local
	talkCount
)
(instance Celie of Prop
	(properties)
	
	(method (handleEvent event)
		(cond 
			(
			(and (MousedOn self event shiftDown) (not (& global207 $0002)))
				(|= global207 $0002)
				(= theTalker talkCELIE)
				(event claimed: TRUE)
				(Say 0 279 0)
			)
			(
				(and
					(& global207 $0002)
					(or (MousedOn self event shiftDown) (Said 'examine/celie'))
				)
				(event claimed: TRUE)
				(Print 279 1)
			)
			((Said 'ask,tell//*<about')
				(Print 279 2)
			)
			((Said 'deliver,hold/*')
				(if (and theInvItem haveInvItem)
					(Print 279 3)
				else
					(DontHave)
				)
			)
			((Said '/celie>')
				(cond 
					((Said 'converse')
						(= theTalker talkCELIE)
						(switch talkCount
							(0 (Say 1 279 4))
							(1 (Say 1 279 5))
							(else  (Print 279 6))
						)
						(++ talkCount)
					)
					((Said 'get') (Print 279 7))
					((Said 'kill') (Print 279 8))
					((Said 'kiss') (Print 279 9))
					((Said 'embrace') (Print 279 10))
				)
			)
		)
	)
)

(instance pray of Region
	
	(method (init)
		(super init:)
		(Load FONT 4)
		(= global195 2)
		(if (not (& global118 $0008))
			(Load FONT 41)
			(LoadMany SOUND 29 94 95 96)
			(Load SCRIPT 406)
			(Load VIEW 642)
		)
		(Celie view: 481 loop: 0 posn: 142 105 init:)
		(self setScript: praying)
	)
	
	(method (doit)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp temp0)
		(super handleEvent: event)
		(return (if (event claimed?) (return TRUE) else FALSE))
	)
)

(instance praying of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cond 
					((not global216)
						(= state -1)
					)
					((not (& global118 $0008))
						(|= global118 $0008)
						(self setScript: (ScriptID 406 0))
						(= state -1)
					)
					((self script?)
						(= state -1)
					)
				)
				(= cycles 1)
			)
			(1
				(cls)
				(User canInput: TRUE)
				(Celie loop: 0 setCycle: EndLoop)
			)
			(2
				(User canInput: FALSE)
				(Celie setCycle: BegLoop self)
			)
			(3
				(Celie loop: 1 setCycle: Forward)
				(= state 0)
				(= seconds 5)
			)
		)
	)
)
