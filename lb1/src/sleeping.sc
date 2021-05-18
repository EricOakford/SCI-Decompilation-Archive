;;; Sierra Script 1.0 - (do not remove this comment)
(script# 224)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use Actor)

(public
	sleeping 0
)
(synonyms
	(gertie person girl)
)

(local
	talkCount
)
(instance snoring of Sound)

(instance sleeping of Region
	
	(method (init)
		(super init:)
		(= global195 1)
		(LoadMany FONT 41)
		(LoadMany VIEW 642 900)
		(LoadMany SOUND 29 94 95 96 114 115)
		(LoadMany 143 406)
		(Gertie setPri: 6 setCycle: Forward init:)
		(snores setPri: 6 init:)
	)
	
	(method (doit)
		(super doit:)
		(if (and global216 (not (& global118 $0004)))
			(|= global118 $0004)
			(self setScript: (ScriptID 406 0))
		)
		(if
			(and
				(== (snoring prevSignal?) -1)
				(== (snoring number?) 114)
			)
			(snoring number: 115 loop: 1 prevSignal: 0 play:)
		)
		(if (== (Gertie cel?) 0)
			(snoring number: 114 loop: 1 play:)
			(snores cel: 0 setCycle: EndLoop)
		)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if (event claimed?) (return))
		(if (== (event type?) saidEvent)
			(= theTalker talkGERTIE)
			(cond 
				((Said 'deliver,hold/*')
					(if (and theInvItem haveInvItem)
						(Print 224 0)
					else
						(DontHave)
					)
				)
				((or (Said 'ask,tell//*<about') (Said 'converse'))
					(switch talkCount
						(0
							(Say 1 224 1)
							(++ talkCount)
						)
						(1
							(Print 224 0)
						)
					)
					(event claimed: TRUE)
				)
				((Said '/gertie>')
					(cond 
						((Said 'examine')
							(if (& global207 $0001)
								(Print 224 2)
							else
								(|= global207 $0001)
								(Say 0 224 3)
							)
						)
						((Said 'hear')
							(Print 224 4)
						)
						((Said 'awaken,kill,embrace,kiss,get,hit,move')
							(Print 224 5)
						)
					)
				)
			)
		)
	)
)

(instance Gertie of Prop
	(properties
		y 86
		x 214
		view 344
		cycleSpeed 16
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'examine[<at]/bed')
				(Print 224 2)
			)
			((MousedOn self event shiftDown)
				(event claimed: TRUE)
				(ParseName {gertie})
			)
		)
	)
)

(instance snores of Prop
	(properties
		y 68
		x 228
		view 344
		loop 1
		cycleSpeed 1
	)
)
