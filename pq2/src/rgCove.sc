;;; Sierra Script 1.0 - (do not remove this comment)
(script# regCove)
(include game.sh)
(use Main)
(use Intrface)
(use Game)

(public
	rgCove 0
)

(instance rgCove of Locale
	(method (handleEvent event)
		(switch (event type?)
			(saidEvent
				(cond 
					((Said 'chat/friend')
						(if (and (cast contains: keith) (keith inRect: 0 0 320 200))
							(if (> bainsInCoveTimer 800)
								(Print 155 0)
							else
								(Print 155 1)
							)
						else
							(Print 155 2)
						)
					)
					((Said 'chat,tell>')
						(Print 155 3)
						(event claimed: TRUE)
					)
					((Said 'follow')
						(Print 155 4)
					)
					((Said 'look,frisk/blood,mark,clue')
						(Print 155 5)
					)
					((Said 'look>')
						(cond 
							((Said '/woman<purse')
								(Print 155 6)
							)
							((or (Said '/willow[<pussy]') (Said '/tail<cat'))
								(Print 155 7)
							)
							((Said '/tree')
								(Print 155 8)
							)
							((Said '/bush')
								(Print 155 9)
							)
							((Said '[<up,at][/air,cloud]')
								(Print 155 10)
							)
							((Said '[<at,down][/dirt,grass,path,dirt]')
								(if (< (ego y?) 163)
									(Print 155 11)
								else
									(Print 155 12)
								)
							)
							((Said '/lake')
								(Print 155 13)
							)
							((Said '/rock')
								(Print 155 14)
							)
							((Said '[<at,across][/bank,cliff,(side<other)]')
								(if (== (ego loop?) 3)
									(Print 155 15)
								else
									(Print 155 16)
								)
							)
							((Said '/clearwater,water')
								(if (== (ego loop?) 3)
									(if (< (ego y?) 95)
										(Print 155 17)
									else
										(Print 155 18)
									)
								else
									(Print 155 16)
								)
							)
							((Said '/culdesac,ave')
								(if (or (== (ego loop?) 2) (> (ego y?) 158))
									(Print 155 19)
								else
									(Print 155 16)
								)
							)
							((Said '/dude,person')
								(Print 155 20)
							)
							((Said '[<around,up][/noword,cove,chamber]')
								(event claimed: TRUE)
								(if (Random 0 1)
									(Print 155 21)
								else
									(Print 155 22)
								)
								(if (and (== gamePhase 5) (not global183))
									(Print 155 23)
								)
							)
						)
					)
					((Said 'climb/tree')
						(Print 155 24)
					)
					((Said 'cross,(jump<in)/water,clearwater')
						(Print 155 25)
					)
					((Said 'get,hoist,move/rock')
						(Print 155 26)
					)
					((Said 'get/grass')
						(Print 155 27)
					)
					((Said 'enter,swim,fish[/clearwater,water]')
						(if (< (ego y?) 97)
							(Print 155 28)
						else
							(Print 155 29)
						)
					)
					((Said 'frisk[/billfold]')
						(event claimed: FALSE)
					)
					((Said 'frisk>')
						(event claimed: TRUE)
						(Print 155 30)
					)
				)
			)
		)
	)
)
