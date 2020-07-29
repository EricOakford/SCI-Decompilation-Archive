;;; Sierra Script 1.0 - (do not remove this comment)
(script# PATTI)
(include game.sh)
(use Main)
(use StopWalk)
(use Invent)
(use User)


(class Patti of Ego
	(properties
		description {Patti}
		view 570
	)
	
	(method (doVerb theVerb theItem &tmp [str 80])
		(if (and (IsObject actions) (actions doit: theVerb theItem))
			(return TRUE)
		)
		(return
			(switch theVerb
				(verbLook
					(TimePrint 24 0)
				)
				(verbTalk
					(Say ego 24 1)
				)
				(verbDo
					(if (Btst fWearingHooterShooter)
						(TimePrint 24 2)
					else
						(TimePrint 24 3)
					)
				)
				(verbZipper
					(TimePrint 24 4)
				)
				(verbUse
					(switch theItem
						(iChampagne
							(TimePrint 24 5)
						)
						(iGoldRecord
							(TimePrint 24 6)
						)
						(iLetterOpener
							(TimePrint 24 7)
							(Say ego 24 8)
						)
						(iHooterShooter
							(if (Btst fWearingHooterShooter)
								(SolvePuzzle -5)
								(TimePrint 24 9)
								(Bclr fWearingHooterShooter)
							else
								(SolvePuzzle 5)
								(TimePrint 24 10)
								(Say ego 24 11)
								(Bset fWearingHooterShooter)
							)
						)
						(else 
							(Format @str 24 12 ((Inventory at: theItem) description?))
							(TimePrint @str)
						)
					)
				)
				(else 
					(super doVerb: theVerb theItem &rest)
				)
			)
		)
	)
	
	(method (get what &tmp i)
		(= i 0)
		(while (< i argc)
			((inventory at: [what i]) moveTo: PATTI)
			(++ i)
		)
	)
	
	(method (has what &tmp theItem)
		(if (= theItem (inventory at: what)) (theItem ownedBy: PATTI))
	)
	
	(method (normalize theView)
		(= view (if argc theView else 570))
		(self
			setLoop: -1
			setCel: -1
			setPri: -1
			setMotion: 0
			setCycle: StopWalk -1
			setStep: 3 2
			z: 0
			illegalBits: cWHITE
			ignoreActors: 0
			moveSpeed: (theGame egoMoveSpeed?)
			cycleSpeed: (theGame egoMoveSpeed?)
		)
	)
	
	(method (setUpInv)
		((ScriptID LLINV 0) doit: PATTI)
	)
	
	(method (showInv)
		(if (Inventory firstTrue: #ownedBy PATTI)
			(Inventory showSelf: PATTI)
			(if (not (theIconBar curInvIcon?))
				(theIconBar curIcon: (theIconBar at: ICON_WALK) disable: ICON_ITEM)
				(theGame setCursor: ((theIconBar curIcon?) cursor?))
			)
		else
			(TimePrint 24 13)
		)
	)
)
