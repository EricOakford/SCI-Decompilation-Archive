;;; Sierra Script 1.0 - (do not remove this comment)
(script# CEMETERY) ;806
(include game.sh)
(use Main)
(use Game)

(public
	Cemetery 0
)

(instance Cemetery of Region
	(method (handleEvent event &tmp [temp0 54])
		(if (event claimed?) (return))
		(switch (event type?)
			(saidEvent
				(cond 
					((super handleEvent: event))
					((Said 'look>')
						(cond 
							((Said '[<at,around][/!*,forest,greenery]')
								(HighPrint 806 0)
							)
							((Said '/ghost')
								(if
									(or
										(!= ((ScriptID GHOSTS 5) script?) 0)
										(!= ((ScriptID GHOSTS 6) script?) 0)
										(!= ((ScriptID GHOSTS 7) script?) 0)
									)
									(HighPrint 806 1)
									(HighPrint 806 2)
								else
									(HighPrint 806 3)
								)
							)
							((Said '[<down][/ground,needle,moss,grass]')
								(HighPrint 806 4)
							)
							((Said '[<up][/sky,cloud,star,moon]')
								(if Night
									(HighPrint 806 5)
								else
									(HighPrint 806 6)
								)
							)
							((Said '/birch,tree')
								(HighPrint 806 7)
							)
							((Said '/bush')
								(HighPrint 806 8)
							)
							((Said '/boulder')
								(HighPrint 806 9)
							)
							((Said '/hill,cliff,peak,pass')
								(HighPrint 806 10)
							)
							((Said '/cave')
								(HighPrint 806 11)
							)
						)
					)
					((Said 'climb')
						(HighPrint 806 12)
					)
					((Said 'get/ghost')
						(if Night
							(HighPrint 806 13)
						else
							(HighPrint 806 14)
						)
					)
					(
					(Said 'drink/grease,(potion,grease<ghost,ghoul)')
						(if (ego has: iGhostOil)
							(HighPrint 806 15)
						else
							(HighPrint 806 16)
						)
					)
					((Said 'use,rub/grease,(potion,grease<ghost,ghoul)')
						(if (ego has: iGhostOil)
							(HighPrint 806 17)
							(Bset fGhostOil)
							(Bclr fGhostsAttack)
							(= ghostOilTimer GAMEHOUR)
							(ego use: iGhostOil)
							(ego get: iFlask)
							(if Night (SolvePuzzle f64UseGhostOil 2))
						else
							(HighPrint 806 16)
						)
					)
				)
			)
		)
	)
)
