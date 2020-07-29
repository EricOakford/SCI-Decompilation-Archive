;;; Sierra Script 1.0 - (do not remove this comment)
(script# LARRY)
(include game.sh)
(use Main)
(use StopWalk)
(use Invent)
(use User)


(class Larry of Ego
	(properties
		description {Larry}
		view 550
		skating 0
		sitting 0
	)
	
	(method (doVerb theVerb theItem &tmp [str 80])
		(switch theVerb
			(verbLook
				(if skating
					(TimePrint 23 0)
				else
					(TimePrint 23 1)
				)
			)
			(verbTalk
				(TimePrint 23 2)
			)
			(verbDo
				(TimePrint 23 3)
				(TimePrint 23 4
					#at -1 185
				)
			)
			(verbZipper
				(TimePrint 23 5)
			)
			(verbUse
				(switch theItem
					(iCamcorder
						(TimePrint 23 6)
					)
					(iNapkin
						(TimePrint 23 7)
					)
					(iMoney
						(TimePrint 23 8)
					)
					(iMatchbook
						(TimePrint 23 9)
						(TimePrint 23 10
							#at -1 185
						)
					)
					(iRollerskates
						((Inventory at: iRollerskates) doVerb: verbDo)
					)
					(iPulliamCard
						(TimePrint 23 11)
					)
					(iDoily
						((Inventory at: iDoily) doVerb: verbDo)
					)
					(else 
						(Format @str 23 12 ((Inventory at: theItem) description?))
						(TimePrint @str)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
	
	(method (get what &tmp i)
		(= i 0)
		(while (< i argc)
			((inventory at: [what i]) moveTo: LARRY)
			(++ i)
		)
	)
	
	(method (has what &tmp theItem)
		(if (= theItem (inventory at: what))
			(theItem ownedBy: LARRY)
		)
	)
	
	(method (normalize theView)
		(= view (if argc theView else 550))
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
		((ScriptID LLINV 0) doit: LARRY)
	)
	
	(method (showInv)
		(if (Inventory firstTrue: #ownedBy LARRY)
			(Inventory showSelf: LARRY)
			(if (not (theIconBar curInvIcon?))
				(theIconBar curIcon: (theIconBar at: ICON_WALK) disable: ICON_ITEM)
				(theGame setCursor: ((theIconBar curIcon?) cursor?))
			)
		else
			(TimePrint 23 13)
		)
	)
)
