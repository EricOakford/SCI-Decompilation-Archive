;;; Sierra Script 1.0 - (do not remove this comment)
(script# 410)
(include game.sh)
(use Main)
(use Intrface)
(use System)

(public
	InvSaids 0
)

(instance InvSaids of Script

	(method (dispose)
		(super dispose:)
		(DisposeScript 410)
	)
	
	(method (handleEvent event &tmp cantDo [str 50])
		(if (ego has: iOilcan)
			(cond 
				((Said 'get,get/oil<from/can')
					(Print 410 0)
				)
				((Said '(examine<in),open/can[<oil]')
					(Print 410 1)
				)
				((Said 'oil[/*]')
					(Print 410 2)
				)
			)
		)
		(if
			(and
				(or
					(Said '/monocle>')
					(Said '//monocle>')
					(Said 'examine<use<monocle>')
				)
				(not (Said 'examine[<at]/monocle>'))
			)
			(cond 
				((not (ego has: iMonocle))
					(event claimed: TRUE)
					(Print 410 3)
				)
				((Said 'wear,(attach<on)')
					(Print 410 4)
				)
				((Said 'examine>')
					(cond 
						((Said '<in')
							(Print 410 5)
						)
						((Said '/fingerprint')
							(event claimed: FALSE)
							(client setScript: 0)
							(return)
						)
						((not haveInvItem)
							(DontHave)
						)
						(else
							(switch whichItem
								(iHandkerchief
									(Print 410 6 #icon 634 0 0)
									(Bset fExaminedHandkerchief)
								)
								(iRollingPin
									(Print 410 7 #icon 639 0 0)
									(Bset fExaminedRollingPin)
								)
								(iPoker
									(Print 410 8 #icon 632 0 0)
									(Bset fExaminedPoker)
								)
								(iBrokenRecord
									(Print 410 9 #icon 636 0 0)
									(Bset fExaminedRecord)
								)
								(iDiary
									(Print 410 10 #icon 637 0 0)
									(Bset fExaminedDiary)
								)
								(else
									(Print 410 11)
								)
							)
						)
					)
					(event claimed: TRUE)
				)
			)
		)
		(if (event claimed?)
			(client setScript: 0)
			(return)
		)
		(= cantDo 0)
		(cond 
			(
				(or
					(Said 'load/derringer')
					(Said 'attach,load/bullet/derringer')
				)
				(cond 
					(gunIsLoaded
						(Print 410 12)
					)
					((ego has: iDerringer)
						(if (ego has: iBullet)
							(Ok)
							(ego put: iBullet 99)
							(= gunIsLoaded TRUE)
						else
							(Print 410 13)
						)
					)
					(else
						(Print 410 14)
					)
				)
			)
			(
				(or
					(Said 'detach,unload/bullet[/derringer<from]')
					(Said 'unload/derringer')
				)
				(cond 
					(gunIsLoaded
						(Print 410 15)
					)
					((ego has: iDerringer)
						(Print 410 16)
					)
					(else
						(Print 410 14)
					)
				)
			)
			((Said 'open,(examine<in)/derringer')
				(cond 
					(gunIsLoaded
						(Print 410 17)
					)
					((ego has: iDerringer)
						(Print 410 18)
					)
					(else
						(Print 410 14)
					)
				)
			)
			((and haveInvItem (Said 'examine/fingerprint>'))
				(cond 
					((Said '//diary')
						(Print 410 10 #icon 637 0 0)
						(Bset fExaminedDiary)
					)
					((Said '//record')
						(Print 410 9 #icon 636 0 0)
						(Bset fExaminedRecord)
					)
				)
			)
			((Said 'smoke/butt')
				(Print 410 19)
			)
			((Said 'get>')
				(cond 
					(haveInvItem
						(AlreadyTook)
						(event claimed: TRUE)
					)
					((!= (theInvItem owner?) curRoomNum)
						(DontSee)
						(event claimed: TRUE)
					)
				)
			)
			((or (Said 'ask/*<for') (Said 'ask//*<for'))
				(if haveInvItem
					(AlreadyTook)
				else
					(Print 410 20)
				)
			)
			((Said 'ask,tell>')
				(client setScript: 0)
				(return)
			)
			((not haveInvItem)
				(event claimed: TRUE)
				(DontHave)
			)
			((Said 'drop')
				(Print 410 21)
			)
			((Said 'throw')
				(Print 410 22)
			)
			((and (not (Said 'examine<in>')) (Said 'examine'))
				(theInvItem showSelf:)
			)
			((Said 'use')
				(Format @str 410 23 (theInvItem name?))
				(Format (- (StrEnd @str) 1) 410 24)
				(Print @str)
			)
			((Said 'deliver,hold>')
				(client setScript: 0)
				(return)
			)
			(else
				(switch whichItem
					(iDiary
						(cond 
							((Said 'write') (Print 410 25))
							((Said 'open,read,(examine<in)')
								(Print 410 26)
								(Print 410 27)
								(Print 410 28)
								(Print 410 29)
								(Bset fReadDiary)
							)
							(else
								(= cantDo TRUE)
							)
						)
					)
					(iPouch
						(if (Said 'open,(examine<in)')
							(Print 410 30)
						else
							(= cantDo TRUE)
						)
					)
					(iCrackers
						(cond 
							((Said 'open,(examine<in)/box')
								(switch numCrackers
									(0
										(Print 410 31)
									)
									(1
										(Print 410 32)
									)
									(else 
										(Print (Format @str 410 33 numCrackers))
									)
								)
							)
							((Said 'eat')
								(if (> numCrackers 0)
									(Print 410 34)
									(-- numCrackers)
								else
									(Print 410 35)
								)
							)
							(else
								(= cantDo TRUE)
							)
						)
					)
					(iMatches
						(if (Said 'ignite')
							(Print 410 36)
							(Print 410 37)
						else
							(= cantDo TRUE)
						)
					)
					(iCarrot
						(if (Said 'eat>')
							(Print 410 38)
							(ego put: iCarrot 0)
							(event claimed: TRUE)
						else
							(= cantDo TRUE)
						)
					)
					(iNecklace
						(cond 
							((Said 'wear,(attach<on)')
								(Print 410 39)
							)
							((Said 'polish')
								(Print 410 40)
							)
							(else
								(= cantDo TRUE)
							)
						)
					)
					(iLantern
						(cond 
							((Said 'open,(examine<in)')
								(Print 410 41)
							)
							((Said 'ignite,(rotate<on)')
								(if (ego has: iMatches)
									(if lanternIsLit
										(Print 410 42)
									else
										(Print 410 43)
										(= lanternIsLit TRUE)
									)
								else
									(Print 410 44)
								)
							)
							((Said 'extinguish,extinguish,(rotate<off)')
								(if lanternIsLit
									(= lanternIsLit FALSE)
									(Print 410 45)
								else
									(Print 410 46)
								)
							)
							(else
								(= cantDo TRUE)
							)
						)
					)
					(iNotebook
						(cond 
							(
								(or
									(Said 'read,open,(examine<in)')
									(Said 'rotate/page')
								)
								(Print 410 47)
							)
							((Said 'close')
								(Print 410 48)
							)
							((Said 'write')
								(Print 410 49)
							)
							(else
								(= cantDo TRUE)
							)
						)
					)
				)
			)
		)
		(if cantDo
			(CantDo)
			(event claimed: 1)
		)
		(client setScript: 0)
	)
)
