;;; Sierra Script 1.0 - (do not remove this comment)
(script# FOREST) ;804
(include game.sh)
(use Main)
(use LoadMany)
(use Game)

(public
	Forest 0
)

(local
	atClearing
)
(instance Forest of Locale
	(method (init &tmp pic soundNum)
		(super init: &rest)
		(Load VIEW vEgoThrowing)
		(LoadMany SCRIPT 103 102)
		(if
			(and
				(>= (= pic (curRoom picture?)) 704)
				(<= pic 707)
			)
			(= atClearing TRUE)
		)
		(= soundNum (if Night 32 else 25))
		(if
			(or
				(== (cSound state?) 0)
				(!= (cSound number?) soundNum)
			)
			(cSound stop: number: soundNum loop: -1 priority: 0 play:)
		)
		(if
			(and
				(<= howFast medium)
				(>= pic 700)
				(<= pic 707)
			)
			(= fastEgo TRUE)
			(ChangeGait -1 FALSE)
		)
	)
	
	(method (dispose)
		(= fastEgo FALSE)
		(super dispose: &rest)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(switch (event type?)
			(saidEvent
				(cond 
					((super handleEvent: event))
					((Said 'look>')
						(cond 
							((Said '[<at,around][/!*,forest,greenery,clearing]')
								(if atClearing
									(HighPrint 804 0)
								else
									(HighPrint 804 1)
								)
							)
							((Said '[<down][/ground,needle,moss,grass]')
								(if atClearing
									(HighPrint 804 2)
								else
									(HighPrint 804 3)
								)
							)
							((Said '[<up][/sky,cloud,star,moon]')
								(if Night
									(HighPrint 804 4)
								else
									(HighPrint 804 5)
								)
							)
							((Said '/birch,tree')
								(HighPrint 804 6)
							)
							((Said '/bush')
								(HighPrint 804 7)
							)
							((Said '/boulder')
								(if atClearing
									(HighPrint 804 8)
								else
									(HighPrint 804 9)
								)
							)
							((Said '/hill,cliff,peak,pass')
								(if atClearing
									(HighPrint 804 8)
								else
									(HighPrint 804 10)
								)
							)
							((Said '/cave')
								(HighPrint 804 11)
							)
						)
					)
					((Said 'climb')
						(HighPrint 804 12)
					)
					(
						(or
							(Said 'get/boulder,brick')
							(Said 'lockpick<up,boulder,brick')
						)
						(ego setScript: (ScriptID 103 0))
					)
					((Said 'kiss/tree')
						(HighPrint 804 13)
					)
				)
			)
		)
	)
)
